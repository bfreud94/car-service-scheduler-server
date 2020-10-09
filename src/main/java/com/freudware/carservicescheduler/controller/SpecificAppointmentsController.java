package com.freudware.carservicescheduler.controller;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import com.freudware.carservicescheduler.constants.JspConstants;
import com.freudware.carservicescheduler.service.ScheduleService;
import com.freudware.carservicescheduler.util.JsonUtil;
import com.google.gson.JsonArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/carServiceScheduler/specificAppointments")
public class SpecificAppointmentsController {
	
	private final static Logger LOGGER = Logger.getLogger(SpecificAppointmentsController.class.getName());
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private JsonUtil jsonUtil;
	
	/**
	 * Entry point for Specific Appointments page
	 * @return ModelAndView
	 */
	@RequestMapping("")
	public ModelAndView specificAppointmentsHome()	{
		ModelAndView returnValue = new ModelAndView(JspConstants.SPECIFIC_APPOINTMENTS);
		LOGGER.info("Entering the specific appointments page");
		return returnValue;
	}
	
	/**
	 * API Call for specificAppointments
	 * @param stringStart
	 * @param stringEnd
	 * @return ResponseEntity<String>
	 */
	@RequestMapping("/specificAppointments")
	public ResponseEntity<String> getSpecificAppointments(@RequestParam("start") String stringStart, @RequestParam("end") String stringEnd)	{
		LocalDateTime start = LocalDateTime.parse(stringStart);
		LocalDateTime end = LocalDateTime.parse(stringEnd);
        JsonArray json = scheduleService.getAllAppointments();
		json = jsonUtil.filterByDate(json, start, end);
		return ResponseEntity.ok(json.toString());
	}
}
