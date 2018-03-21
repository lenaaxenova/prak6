package journeys.controller;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

import javax.validation.Valid;

import journeys.dao.DirectionDAO;
import journeys.dao.StationDAO;
import journeys.dao.CompanyDAO;
import journeys.dao.JourneyDAO;
import journeys.dao.RouteDAO;
import journeys.entity.Direction;
import journeys.entity.Journey;
import journeys.entity.Route;
import journeys.entity.Station;
import journeys.entity.Client;
import journeys.entity.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/journey")
public class JourneyController {
    @Autowired
	JourneyDAO journeydao;

    @Autowired
	RouteDAO routedao;


	@Autowired
	DirectionDAO directiondao;

	@Autowired
	StationDAO stationdao;

    @Autowired
	CompanyDAO companydao;


    @RequestMapping(value = {"", "/", "/shedule"}, method = RequestMethod.GET)
	public ModelAndView journeyShedule() {
		ModelAndView model = new ModelAndView("Journey/shedule");
		model.addObject("journeys", journeydao.findAll());
		model.addObject("journeydao", journeydao);
		DateFormat start_date = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat start_time = new SimpleDateFormat("HH:mm"); 
		model.addObject("start_date", start_date);
		model.addObject("start_time", start_time);
		return model;
	}

    @RequestMapping(value = "/direction/{id}/shedule", method = RequestMethod.GET)
	public ModelAndView journeySheduleWithDirection(@PathVariable("id") Long id) {
        Direction direction = directiondao.findById(id);
		ModelAndView model = new ModelAndView("Journey/DirectionShedule");
		model.addObject("direction_shedule", journeydao.getSheduleWithDirection(direction));
		model.addObject("journeydao", journeydao);

		return model;
	}

    @RequestMapping(value = "/station/{id}/shedule", method = RequestMethod.GET)
	public ModelAndView journeySheduleWithStation(@PathVariable("id") Long id) {
        Station station = stationdao.findById(id);
		ModelAndView model = new ModelAndView("Journey/StationShedule");
		model.addObject("station_shedule", journeydao.getSheduleWithStation(station));

		return model;
	}

	@RequestMapping(value = {"/{journey_id}", "/shedule/{journey_id}"}, method = RequestMethod.GET)
	public ModelAndView journeyInfo(@PathVariable("journey_id") Long journey_id) {
		ModelAndView model = new ModelAndView("Journey/info");

		Journey journey = journeydao.findById(journey_id);
		model.addObject("journey", journey);

		if (journey != null) {
            model.addObject("places", journey.getNumber_of_places());
            model.addObject("free_places", journey.getNumber_of_places() - journeydao.getOrders(journey).size());
            List<Route> routes = journeydao.findAllRoutes(journey);
			model.addObject("routes", routes);
		}

		return model;
	}

    @RequestMapping(value = "/shedule/{id}/clients", method = RequestMethod.GET)
	public ModelAndView journeyClients(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView("Journey/clients");

		Journey journey = journeydao.findById(id);
		model.addObject("journey", journey);

		if (journey != null) {
			List<Client> clients = journeydao.getClients(journey);
			model.addObject("clients", clients);
		}

		return model;
	}

	@RequestMapping(value = {"/shedule/new", "/new"}, method = RequestMethod.GET)
	public ModelAndView companyNewGET() {
		ModelAndView model = new ModelAndView("Journey/new");

		List<Company> companies = companydao.findAll();
		model.addObject("companies", companies);

        List<Direction> directions = directiondao.findAll();
		model.addObject("directions", directions);

		return model;
	}

	@RequestMapping(value = {"/shedule/new", "/new"}, method = RequestMethod.POST)
	public ModelAndView companyNewPOST(@RequestParam Long company_id, @RequestParam Long direction_id,
    @RequestParam Long number_of_places, @RequestParam String start_date_str, @RequestParam String start_time_str, @Valid Journey journey, BindingResult result) {
		ModelAndView model = new ModelAndView("Journey/new");

		List<Company> companies = companydao.findAll();
		model.addObject("companies", companies);

		Company company = companydao.findById(company_id);
		journey.setCompany(company);
		model.addObject("company_id", company_id);

		if (company == null) {
			result.reject("db", "Вы должны выбрать существующую транспортную компанию.");
		}

        List<Direction> directions = directiondao.findAll();
		model.addObject("directions", directions);

		Direction direction = directiondao.findById(direction_id);
		journey.setDirection(direction);
		model.addObject("direction_id", direction_id);

		if (direction == null) {
			result.reject("db", "Вы должны выбрать существующее направление.");
		}
		
		DateFormat start_date_format = new SimpleDateFormat("dd/MM/yyyy");
		Date start_date = new Date();
		try {
			start_date = start_date_format.parse(start_date_str);
		} catch (ParseException e1) {
			result.reject("start_date_str", "Введите дату в формате дд/мм/гггг.");
		}
		
		DateFormat start_time_format = new SimpleDateFormat("HH:mm");
		Date start_time = new Date();
		try {
			start_time = start_time_format.parse(start_time_str);
		} catch (ParseException e1) {
			result.reject("start_time_str", "Введите время в формате чч:мм.");
		}

        journey.setNumber_of_places(number_of_places);
        journey.setStart_date(start_date);
        journey.setStart_time(start_time);
        
        model.addObject("start_date_str", start_date_str);
        model.addObject("start_time_str", start_time_str);

		if (!result.hasErrors()) {
			try {
				journeydao.saveOrUpdate(journey);
			} catch (Exception e) {
				result.reject("db", "Произошла ошибка при добавлении маршрута." + e.getMessage());
			}

			if (!result.hasErrors()) {
				model.addObject("message", "Маршрут №" + journey.getJourney_id()
                 + " (" + journey.getCompany().getCompany_name() + ") по направлению "
                 + journey.getDirection().getDirection_name() + " успешно добавлен.");
			}
		}

		model.addObject("errors", result);

		return model;
	}

	@RequestMapping(value = {"/shedule/{journey_id}/edit", "/{journey_id}/edit"}, method = RequestMethod.GET)
	public ModelAndView journeyEditGET(@PathVariable("journey_id") Long journey_id) {
		ModelAndView model = new ModelAndView("Journey/edit");

		Journey journey = journeydao.findById(journey_id);
		model.addObject("journey", journey);

		List<Company> companies = companydao.findAll();
		model.addObject("companies", companies);

		model.addObject("company_id", journey.getCompany().getCompany_id());

        List<Direction> directions = directiondao.findAll();
		model.addObject("directions", directions);

        model.addObject("direction_id", journey.getDirection().getDirection_id());
        
		DateFormat start_date = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat start_time = new SimpleDateFormat("HH:mm"); 
		model.addObject("start_date", start_date);
		model.addObject("start_time", start_time);

		return model;
	}

	@RequestMapping(value = {"/shedule/{journey_id}/edit", "/{journey_id}/edit"}, method = RequestMethod.POST)
	public ModelAndView journeyEditPOST(@RequestParam Long company_id,  @RequestParam Long direction_id,
    @RequestParam Long number_of_places, @RequestParam String start_date_str, @RequestParam String start_time_str, @Valid Journey journey, BindingResult result) {
		ModelAndView model = new ModelAndView("Journey/edit");

		List<Company> companies = companydao.findAll();
		model.addObject("companies", companies);

		Company company = companydao.findById(company_id);
		journey.setCompany(company);

		if (company == null) {
			result.reject("db", "Вы должны выбрать существующую транспортную компанию.");
		}

        List<Direction> directions = directiondao.findAll();
		model.addObject("directions", directions);

		Direction direction = directiondao.findById(direction_id);
		journey.setDirection(direction);

		if (direction == null) {
			result.reject("db", "Вы должны выбрать существующее направление.");
		}
		
		DateFormat start_date_format = new SimpleDateFormat("dd/MM/yyyy");
		Date start_date = new Date();
		try {
			start_date = start_date_format.parse(start_date_str);
		} catch (ParseException e1) {
			result.reject("start_date_str", "Введите дату в формате дд/мм/гггг.");
		}
		
		DateFormat start_time_format = new SimpleDateFormat("HH:mm");
		Date start_time = new Date();
		try {
			start_time = start_time_format.parse(start_time_str);
		} catch (ParseException e1) {
			result.reject("start_time_str", "Введите время в формате чч:мм.");
		}

        journey.setNumber_of_places(number_of_places);
        journey.setStart_date(start_date);
        journey.setStart_time(start_time);

		if (!result.hasErrors()) {
			try {
				journeydao.saveOrUpdate(journey);
			} catch (Exception e) {
				result.reject("db", "Произошла ошибка при редактировании маршрута." + e.getMessage());
			}

			if (!result.hasErrors()) {
				model.addObject("message", "Маршрут №" + journey.getJourney_id()
                 + " (" + journey.getCompany().getCompany_name() + ") по направлению "
                 + journey.getDirection().getDirection_name() + " успешно отрекдактирован.");
			}
		}
		
		model.addObject("start_date", start_date_format);
		model.addObject("start_time", start_time_format);

		model.addObject("errors", result);
		return model;
	}

	@RequestMapping(value = {"/{journey_id}/delete", "/shedule/{journey_id}/delete"}, method = RequestMethod.GET)
	public ModelAndView journeyDelete(@PathVariable("journey_id") Long journey_id) {
		ModelAndView model = new ModelAndView("Journey/delete");

		Journey journey = journeydao.findById(journey_id);
		if (journey != null) {
			boolean deleted = true;
			try {
				List<Route> routes = journeydao.findAllRoutes(journey);
                for (Route r: routes) {

                    routedao.delete(r);
                }
				journeydao.delete(journey);
			} catch (Exception e) {
				deleted = false;
			}

			if (deleted) {
				model.addObject("message", "Маршрут №" + journey.getJourney_id() + ") успешно удален.");
			} else {
				model.addObject("message", "Произошла ошибка при удалении маршрута.");
			}
		}

		return model;
	}
}
