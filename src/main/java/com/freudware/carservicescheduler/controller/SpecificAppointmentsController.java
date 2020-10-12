package com.freudware.carservicescheduler.controller;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import com.freudware.carservicescheduler.service.ScheduleService;
import com.freudware.carservicescheduler.util.JsonUtil;
import com.google.gson.JsonArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/specificAppointments")
public class SpecificAppointmentsController {
	
	private final static Logger LOGGER = Logger.getLogger(SpecificAppointmentsController.class.getName());
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private JsonUtil jsonUtil;
	
	/**
	 * API Call for specificAppointments
	 * @param stringStart
	 * @param stringEnd
	 * @return ResponseEntity<String>
	 */
	@RequestMapping("/specificAppointments")
	public ResponseEntity<String> getSpecificAppointments(@RequestParam("start") String stringStart, @RequestParam("end") String stringEnd)	{
        LOGGER.info("Getting specific appointments");
		LocalDateTime start = LocalDateTime.parse(stringStart);
		LocalDateTime end = LocalDateTime.parse(stringEnd);
        JsonArray json = scheduleService.getAllAppointments();
		json = jsonUtil.filterByDate(json, start, end);
		return ResponseEntity.ok(json.toString());
	}
}
