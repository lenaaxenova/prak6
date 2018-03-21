package journeys.controller;

import java.util.List;

import javax.validation.Valid;

import journeys.dao.CompanyDAO;
import journeys.entity.Company;
import journeys.entity.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/company")
public class CompanyController {
	@Autowired
	CompanyDAO companydao;

	@RequestMapping(value = {"", "/", "list"}, method = RequestMethod.GET)
	public ModelAndView companyList() {
		ModelAndView model = new ModelAndView("Company/list");
		model.addObject("companies", companydao.findAll());

		return model;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView companyInfo(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView("Company/info");

		Company company = companydao.findById(id);
		model.addObject("company", company);

		if (company != null) {
            List<Client> clients = companydao.findAllClients(company);
			model.addObject("clients", clients);
		}

		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView companyNewGET() {
		ModelAndView model = new ModelAndView("Company/new");

		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView companyNewPOST(@Valid Company company, BindingResult result) {
		ModelAndView model = new ModelAndView("Company/new");

		if (!result.hasErrors()) {
			try {
				companydao.saveOrUpdate(company);
			} catch (Exception e) {
				result.reject("db", "Произошла ошибка при добавлении транспортной компании.");
			}

			if (!result.hasErrors()) {
				model.addObject("message", "Транспортная компания " + company.getCompany_name() + " успешно добавлена.");
			}
		}

		model.addObject("errors", result);

		return model;
	}

	@RequestMapping(value = "/{company_id}/edit", method = RequestMethod.GET)
	public ModelAndView companyEditGET(@PathVariable("company_id") Long company_id) {
		ModelAndView model = new ModelAndView("Company/edit");

		Company company = companydao.findById(company_id);
		model.addObject("company", company);

		return model;
	}

	@RequestMapping(value = "/{company_id}/edit", method = RequestMethod.POST)
	public ModelAndView companyEditPOST(@PathVariable("company_id") Long company_id, @Valid Company company, BindingResult result) {
		ModelAndView model = new ModelAndView("Company/edit");

		if (!result.hasErrors()) {
			try {
				companydao.saveOrUpdate(company);
			} catch (Exception e) {
				result.reject("db", "Произошла ошибка при редактировании транспортной компании.");
			}

			if (!result.hasErrors()) {
				model.addObject("message", "Транспортная компания " + company.getCompany_name() + " успешно отредактирована.");
			}
		}

		model.addObject("errors", result);

		return model;
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public ModelAndView companyDelete(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView("Company/delete");

		Company company = companydao.findById(id);
		if (company != null) {
			boolean deleted = true;
			try {
				companydao.delete(company);
			} catch (Exception e) {
				deleted = false;
			}

			if (deleted) {
				model.addObject("message", "Транспортная компания " + company.getCompany_name() + " успешно удалена.");
			} else {
				model.addObject("message", "Произошла ошибка при удалении транспортной компании.");
			}
		}

		return model;
	}
}
