package journeys.controller;

import java.util.List;
import java.util.Date;

import javax.validation.Valid;

import journeys.dao.ClientDAO;
import journeys.dao.JourneyDAO;
import journeys.dao.OrderDAO;
import journeys.dao.RouteDAO;
import journeys.entity.Client;
import journeys.entity.Journey;
import journeys.entity.Order;
import journeys.entity.Route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
	@Autowired
	ClientDAO clientdao;

	@Autowired
	JourneyDAO journeydao;

	@Autowired
	OrderDAO orderdao;

	@Autowired
	RouteDAO routedao;

	@RequestMapping(value = "/{order_id}", method = RequestMethod.GET)
	public ModelAndView orderInfo(@PathVariable("order_id") Long order_id) {
		ModelAndView model = new ModelAndView("Order/info");

		Order order = orderdao.findById(order_id);
		model.addObject("order", order);

		if (order != null) {
			model.addObject("cost", orderdao.getCost(order));
		}

		return model;
	}

	@RequestMapping(value = "/new/{journey_id}", method = RequestMethod.GET)
	public ModelAndView orderNewGET(@PathVariable("journey_id") Long journey_id) {
		ModelAndView model = new ModelAndView("Order/new");

		Journey journey = journeydao.findById(journey_id);
		model.addObject("journey", journey);

		if (journey != null) {
			List<Client> clients = clientdao.findAll();
			model.addObject("clients", clients);

			List<Route> routes = journeydao.findAllRoutes(journey);
			model.addObject("routes", routes);
		}

		return model;
	}

	@RequestMapping(value = "/new/{journey_id}", method = RequestMethod.POST)
	public ModelAndView orderNewPOST(@PathVariable("journey_id") Long journey_id,
    @RequestParam Long client_id, @RequestParam Long route_start_id,
    @RequestParam Long route_end_id, @Valid Order order, BindingResult result) {
		ModelAndView model = new ModelAndView("Order/new");

		order.setOrder_id(null);
		order.setDate_of_order(new Date());

		Journey journey = journeydao.findById(journey_id);
		order.setJourney(journey);
		model.addObject("journey", journey);

		if (journey == null) {
			result.reject("db", "Вы должны выбрать существующий рейс.");
		} else {
			List<Client> clients = clientdao.findAll();
			model.addObject("clients", clients);

			List<Route> routes = journeydao.findAllRoutes(journey);
			model.addObject("routes", routes);
		}

		Client client = clientdao.findById(client_id);
		order.setClient(client);

		if (client == null) {
			result.reject("clientIdError", "Вы должны выбрать существующего клиента.");
		}

		Route routeStart = routedao.findById(route_start_id);
		order.setRoute_start(routeStart);

		if (routeStart == null) {
			result.reject("routeStartIdError", "Вы должны выбрать существующую станцию отправления.");
		}

		Route routeEnd = routedao.findById(route_end_id);
		order.setRoute_end(routeEnd);

		if (routeEnd == null) {
			result.reject("routeEndIdError", "Вы должны выбрать существующую станцию прибытия.");
		}

		if (routeStart.getTime_offset() >= routeEnd.getTime_offset()) {
			result.reject("db", "Станция прибытия должна быть дальше станции отправления.");
		}

		if (journey.getNumber_of_places() < journeydao.getOrders(journey).size() + 1) {
			result.reject("db", "На выбранный участок маршрута нет мест.");
		}

		if (!result.hasErrors()) {
			try {
				orderdao.saveOrUpdate(order);
			} catch (Exception e) {
				result.reject("db", "Произошла ошибка при добавлении заказа." + e.getMessage());
			}

			if (!result.hasErrors()) {
				model.addObject("message", "Заказ успешно добавлен.");
			}
		}

		model.addObject("errors", result);
		model.addObject("client_id", client_id);
		model.addObject("route_start_id", route_start_id);
		model.addObject("route_end_id", route_end_id);

		return model;
	}

	@RequestMapping(value = "/{order_id}/delete", method = RequestMethod.GET)
	public ModelAndView orderDelete(@PathVariable("order_id") Long order_id) {
		ModelAndView model = new ModelAndView("Order/delete");

		Order order = orderdao.findById(order_id);
		if (order != null) {
			boolean deleted = true;
			try {
				orderdao.delete(order);
			} catch (Exception e) {
				deleted = false;
			}

			if (deleted) {
				model.addObject("message", "Заказ успешно удален.");
			} else {
				model.addObject("message", "Произошла ошибка при удалении заказа.");
			}
		}

		return model;
	}
}
