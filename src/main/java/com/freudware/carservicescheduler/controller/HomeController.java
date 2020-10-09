package com.freudware.carservicescheduler.controller;

import java.util.logging.Logger;

import com.freudware.carservicescheduler.constants.JspConstants;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class HomeController {

    private final static Logger LOGGER = Logger.getLogger(ScheduleController.class.getName());

	/**
	 * Entry point for home page
	 * @return ModelAndView
	 */
	@RequestMapping("/")
	public ModelAndView homePageRedirect()	{
		ModelAndView returnValue = new ModelAndView(JspConstants.HOME);
		LOGGER.info("Redirecting to home page");
		return returnValue;
	}
	
	/**
	 * Entry point for home page
	 * @return ModelAndView
	 */
	@RequestMapping("/home")
	public ModelAndView homePage()	{
		ModelAndView returnValue = new ModelAndView(JspConstants.HOME);
		LOGGER.info("Going to home page");
		return returnValue;
	}
}
