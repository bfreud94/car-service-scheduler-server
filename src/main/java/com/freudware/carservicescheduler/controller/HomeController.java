package com.freudware.carservicescheduler.controller;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class HomeController {
    private final static Logger LOGGER = Logger.getLogger(HomeController.class.getName());

	/**
     * Base route
	 * @param ResponseEntity
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<String> baseRoute()	{
		LOGGER.info("Base Route");
		return ResponseEntity.status(HttpStatus.OK).body("Base Route");
    }
    
	/**
     * Base API route
	 * @param ResponseEntity
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<String> baseAPIRoute()	{
		LOGGER.info("Base API Route");
		return ResponseEntity.status(HttpStatus.OK).body("Base API Route");
	}
}
