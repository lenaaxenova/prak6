package journeys.controller;

import java.util.List;

import javax.validation.Valid;

import journeys.dao.ClientDAO;
import journeys.entity.Client;
import journeys.entity.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/client")
public class ClientController {
	@Autowired
	ClientDAO clientdao;

	@RequestMapping(value = {"", "/", "list"}, method = RequestMethod.GET)
	public ModelAndView clientList() {
		ModelAndView model = new ModelAndView("Client/list");
		model.addObject("clients", clientdao.findAll());

		return model;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView clientInfo(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView("Client/info");

		Client client = clientdao.findById(id);
		model.addObject("client", client);

		if (client != null) {
			List<Order> orders = clientdao.findAllOrders(client);
			model.addObject("orders", orders);
		}

		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView clientNewGET() {
		ModelAndView model = new ModelAndView("Client/new");

		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView clientNewPOST(@Valid Client client, BindingResult result) {
		ModelAndView model = new ModelAndView("Client/new");
		client.setIs_admin(false);

		if (!result.hasErrors()) {
			try {
				clientdao.saveOrUpdate(client);
			} catch (Exception e) {
				result.reject("db", "Произошла ошибка при добавлении клиента.");
			}

			if (!result.hasErrors()) {
				model.addObject("message", "Клиент успешно добавлен.");
			}
		}

		model.addObject("errors", result);

		return model;
	}

	@RequestMapping(value = "/{client_id}/edit", method = RequestMethod.GET)
	public ModelAndView clientEditGET(@PathVariable("client_id") Long client_id) {
		ModelAndView model = new ModelAndView("Client/edit");

		Client client = clientdao.findById(client_id);
		model.addObject("client", client);

		return model;
	}

	@RequestMapping(value = "/{client_id}/edit", method = RequestMethod.POST)
	public ModelAndView clientEditPOST(@PathVariable("client_id") Long client_id, @Valid Client client, BindingResult result) {
		ModelAndView model = new ModelAndView("Client/edit");
		
		Client client_old = clientdao.findById(client_id);
		client.setIs_admin(client_old.getIs_admin());
		client.setPassword(client_old.getPassword());

		if (!result.hasErrors()) {
			try {
				clientdao.saveOrUpdate(client);
			} catch (Exception e) {
				result.reject("db", "Произошла ошибка при редактировании клиента.");
			}

			if (!result.hasErrors()) {
				model.addObject("message", "Клиент успешно отредактирован.");
			}
		}

		model.addObject("errors", result);

		return model;
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public ModelAndView clientDelete(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView("Client/delete");

		Client client = clientdao.findById(id);
		if (client != null) {
			boolean deleted = true;
			try {
				clientdao.delete(client);
			} catch (Exception e) {
				deleted = false;
			}

			if (deleted) {
				model.addObject("message", "Клиент успешно удален.");
			} else {
				model.addObject("message", "Произошла ошибка при удалении клиента.");
			}
		}

		return model;
	}
}
