package journeys.controller;

import javax.validation.Valid;

import journeys.dao.JourneyDAO;
import journeys.dao.StationDAO;
import journeys.entity.Station;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/station")
public class StationController {
	@Autowired
	StationDAO stationdao;
	
	@Autowired
	JourneyDAO journeydao;

	@RequestMapping(value = {"", "/", "list"}, method = RequestMethod.GET)
	public ModelAndView stationList() {
		ModelAndView model = new ModelAndView("Station/list");
		model.addObject("stations", stationdao.findAll());
		return model;
	}
	
	@RequestMapping(value = "/{station_id}/shedule", method = RequestMethod.GET)
	public ModelAndView stationShedule(@PathVariable("station_id") Long station_id) {
        Station station = stationdao.findById(station_id);
		ModelAndView model = new ModelAndView("Station/shedule");
		model.addObject("journeys", stationdao.getJourneys(station));
		model.addObject("journeydao", journeydao);
		
		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView stationNewGET() {
		ModelAndView model = new ModelAndView("Station/new");

		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView stationNewPOST(@Valid Station station, BindingResult result) {
		ModelAndView model = new ModelAndView("Station/new");

		if (!result.hasErrors()) {
			try {
				stationdao.saveOrUpdate(station);
			} catch (Exception e) {
				result.reject("db", "Произошла ошибка при добавлении станции.");
			}

			if (!result.hasErrors()) {
				model.addObject("message", "Станция " + station.getStation_name() + " успешно добавлена.");
			}
		}

		model.addObject("errors", result);

		return model;
	}

	@RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
	public ModelAndView stationEditGET(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView("Station/edit");

		Station station = stationdao.findById(id);
		model.addObject("station", station);

		return model;
	}

	@RequestMapping(value = "/{station_id}/edit", method = RequestMethod.POST)
	public ModelAndView stationEditPOST(@PathVariable("station_id") Long station_id, @Valid Station station, BindingResult result) {
		ModelAndView model = new ModelAndView("Station/edit");

		if (!result.hasErrors()) {
			try {
				stationdao.saveOrUpdate(station);
			} catch (Exception e) {
				result.reject("db", "Произошла ошибка при редактировании станции.");
			}

			if (!result.hasErrors()) {
				model.addObject("message", "Станция " + station.getStation_name() + " успешно отредактирована.");
			}
		}

		model.addObject("errors", result);

		return model;
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public ModelAndView stationDelete(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView("Station/delete");

		Station station = stationdao.findById(id);
		if (station != null) {
			boolean deleted = true;
			try {
				stationdao.delete(station);
			} catch (Exception e) {
				deleted = false;
			}

			if (deleted) {
				model.addObject("message", "Станция " + station.getStation_name() + " успешно удалена.");
			} else {
				model.addObject("message", "Произошла ошибка при удалении станции.");
			}
		}

		return model;
	}
}
