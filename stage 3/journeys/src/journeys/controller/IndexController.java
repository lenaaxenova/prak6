package journeys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class IndexController {
	@RequestMapping(method = RequestMethod.GET)

	public ModelAndView index() {
		ModelAndView model = new ModelAndView("Index/index");
		return model;
	}
}

