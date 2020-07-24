package com.test.wesure;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Controller {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String d() {
		return "index 1";
		
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView jsp1() {
		System.out.println("jsp---call");
		return new ModelAndView("webpage");
		
	}
	
}
