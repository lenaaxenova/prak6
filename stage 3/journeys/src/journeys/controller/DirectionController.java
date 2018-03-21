package journeys.controller;

import javax.validation.Valid;

import journeys.dao.DirectionDAO;
import journeys.dao.JourneyDAO;
import journeys.entity.Direction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/direction")
public class DirectionController {
	@Autowired
	DirectionDAO directiondao;
	
	@Autowired
	JourneyDAO journeydao;

	@RequestMapping(value = {"", "/", "list"}, method = RequestMethod.GET)
	public ModelAndView directionList() {
		ModelAndView model = new ModelAndView("Direction/list");
		model.addObject("directions", directiondao.findAll());

		return model;
	}
	
	@RequestMapping(value = "/{direction_id}/shedule", method = RequestMethod.GET)
	public ModelAndView directionShedule(@PathVariable("direction_id") Long direction_id) {
        Direction direction = directiondao.findById(direction_id);
		ModelAndView model = new ModelAndView("Direction/shedule");
		model.addObject("journeys", directiondao.getJourneys(direction));
		model.addObject("journeydao", journeydao);
		
		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView directionNewGET() {
		ModelAndView model = new ModelAndView("Direction/new");

		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView directionNewPOST(@Valid Direction direction, BindingResult result) {
		ModelAndView model = new ModelAndView("Direction/new");

		if (!result.hasErrors()) {
			try {
				directiondao.saveOrUpdate(direction);
			} catch (Exception e) {
				result.reject("db", "Произошла ошибка при добавлении направления.");
			}

			if (!result.hasErrors()) {
				model.addObject("message", "Направление " + direction.getDirection_name() + " успешно добавлено.");
			}
		}

		model.addObject("errors", result);

		return model;
	}

	@RequestMapping(value = "/{direction_id}/edit", method = RequestMethod.GET)
	public ModelAndView directionEditGET(@PathVariable("direction_id") Long direction_id) {
		ModelAndView model = new ModelAndView("Direction/edit");

		Direction direction = directiondao.findById(direction_id);
		model.addObject("direction", direction);

		return model;
	}

	@RequestMapping(value = "/{direction_id}/edit", method = RequestMethod.POST)
	public ModelAndView directionEditPOST(@PathVariable("direction_id") Long direction_id, @Valid Direction direction, BindingResult result) {
		ModelAndView model = new ModelAndView("Direction/edit");

		if (!result.hasErrors()) {
			try {
				directiondao.saveOrUpdate(direction);
			} catch (Exception e) {
				result.reject("db", "Произошла ошибка при редактировании направления.");
			}

			if (!result.hasErrors()) {
				model.addObject("message", "Направление " + direction.getDirection_name() + " успешно отредактировано.");
			}
		}

		model.addObject("errors", result);

		return model;
	}

	@RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
	public ModelAndView directionDelete(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView("Direction/delete");

		Direction direction = directiondao.findById(id);
		if (direction != null) {
			boolean deleted = true;
			try {
				directiondao.delete(direction);
			} catch (Exception e) {
				deleted = false;
			}

			if (deleted) {
				model.addObject("message", "Направление " + direction.getDirection_name() + " успешно удалено.");
			} else {
				model.addObject("message", "Произошла ошибка при удалении направления.");
			}
		}

		return model;
	}
}
