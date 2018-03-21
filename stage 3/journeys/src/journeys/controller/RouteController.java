package journeys.controller;

import java.util.List;

import javax.validation.Valid;

import journeys.dao.JourneyDAO;
import journeys.dao.RouteDAO;
import journeys.dao.StationDAO;
import journeys.entity.Journey;
import journeys.entity.Route;
import journeys.entity.Station;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/route")
public class RouteController {
	@Autowired
	JourneyDAO journeydao;

	@Autowired
	RouteDAO routedao;

	@Autowired
	StationDAO stationdao;

    @RequestMapping(value = "/{id}/shedule_of_stops", method = RequestMethod.GET)
    public ModelAndView routeStopSheduleGET(@PathVariable("id") Long id) {
        ModelAndView model = new ModelAndView("Route/shedule_of_stops");

        Journey journey = journeydao.findById(id);
        model.addObject("shedule", routedao.getSheduleOfStops(journey));

        return model;
    }

    @RequestMapping(value = "/{id1}/{id2}/cost", method = RequestMethod.GET)
    public ModelAndView routeCostGET(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2) {
        ModelAndView model = new ModelAndView("Route/cost");

        Route startRoute = routedao.findById(id1);
        Route endRoute = routedao.findById(id2);
        model.addObject("cost", routedao.getCost(startRoute, endRoute));

        return model;
    }

	@RequestMapping(value = "/new/{journey_id}", method = RequestMethod.GET)
	public ModelAndView routeNewGET(@PathVariable("journey_id") Long journey_id) {
		ModelAndView model = new ModelAndView("Route/new");

		Journey journey = journeydao.findById(journey_id);
		model.addObject("journey", journey);

		List<Station> stations = stationdao.findAll();
		model.addObject("stations", stations);

		return model;
	}

	@RequestMapping(value = "/new/{journey_id}", method = RequestMethod.POST)
	public ModelAndView routeNewPOST(@PathVariable("journey_id") Long journey_id, @RequestParam Long station_id,
    @RequestParam Long time_of_stop, @RequestParam double cost_offset,
    @RequestParam Long time_offset, @Valid Route route, BindingResult result) {
		ModelAndView model = new ModelAndView("Route/new");

		route.setRoute_id(null);

		List<Station> stations = stationdao.findAll();
		model.addObject("stations", stations);

		Journey journey = journeydao.findById(journey_id);
		route.setJourney(journey);
		model.addObject("journey", journey);

		if (journey == null) {
			result.reject("db", "Вы должны выбрать существующий рейс.");
		}

		Station station = stationdao.findById(station_id);
		route.setStation(station);

		if (station == null) {
			result.reject("db", "Вы должны выбрать существующую станцию.");
		}

        route.setTime_of_stop(time_of_stop.intValue());
        route.setTime_offset(time_offset.intValue());
        route.setCost_offset(cost_offset);

		if (!result.hasErrors()) {
			try {
				routedao.saveOrUpdate(route);
			} catch (Exception e) {
				result.reject("db", "Произошла ошибка при добавлении станции маршрута. " + e.getMessage());
			}

			if (!result.hasErrors()) {
				model.addObject("message", "Станция " + route.getStation().getStation_name() + " маршрута успешно добавлена.");
			}
		}

		model.addObject("errors", result);
		model.addObject("stationId", station_id);

		return model;
	}

	@RequestMapping(value = "/{route_id}/edit", method = RequestMethod.GET)
	public ModelAndView routeEditGET(@PathVariable("route_id") Long route_id) {
		ModelAndView model = new ModelAndView("Route/edit");

		Route route = routedao.findById(route_id);
		model.addObject("route", route);

		Journey journey = journeydao.findById(route.getJourney().getJourney_id());
		model.addObject("journey", journey);

		List<Station> stations = stationdao.findAll();
		model.addObject("stations", stations);

		model.addObject("station_id", route.getStation().getStation_id());

		return model;
	}

	@RequestMapping(value = "/{route_id}/edit", method = RequestMethod.POST)
	public ModelAndView routeEditPOST(@PathVariable("route_id") Long route_id, @RequestParam Long station_id, @RequestParam Long time_of_stop,
    @RequestParam double cost_offset, @RequestParam Long time_offset,
    @Valid Route route, BindingResult result) {
		ModelAndView model = new ModelAndView("Route/edit");

		List<Station> stations = stationdao.findAll();
		model.addObject("stations", stations);

		Journey journey = journeydao.findById(routedao.findById(route.getRoute_id()).getJourney().getJourney_id());
		route.setJourney(journey);
		model.addObject("journey", journey);

		Station station = stationdao.findById(station_id);
		route.setStation(station);

		if (station == null) {
			result.reject("db", "Вы должны выбрать существующую станцию.");
		}

        route.setTime_of_stop(time_of_stop.intValue());
        route.setTime_offset(time_offset.intValue());
        route.setCost_offset(cost_offset);

		if (!result.hasErrors()) {
			try {
				routedao.saveOrUpdate(route);
			} catch (Exception e) {
				result.reject("db", "Произошла ошибка при редактировании станции маршрута. " + e.getMessage());
			}

			if (!result.hasErrors()) {
				model.addObject("message", "Станция " + route.getStation().getStation_name() + " маршрута успешно отредактирована.");
			}
		}

		model.addObject("errors", result);
		model.addObject("station_id", station_id);

		return model;
	}

	@RequestMapping(value = "/{route_id}/delete", method = RequestMethod.GET)
	public ModelAndView routeDelete(@PathVariable("route_id") Long route_id) {
		ModelAndView model = new ModelAndView("Route/delete");

		Route route = routedao.findById(route_id);
		if (route != null) {
			boolean deleted = true;
			try {
				routedao.delete(route);
			} catch (Exception e) {
				deleted = false;
			}

			if (deleted) {
				model.addObject("message", "Станция " + route.getStation().getStation_name() + " маршрута успешно удалена.");
			} else {
				model.addObject("message", "Произошла ошибка при удалении станции маршрута.");
			}
		}

		return model;
	}
}
