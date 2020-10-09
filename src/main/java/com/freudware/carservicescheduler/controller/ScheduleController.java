package com.freudware.carservicescheduler.controller;

import java.util.logging.Logger;

import com.freudware.carservicescheduler.constants.Constants;
import com.freudware.carservicescheduler.constants.JspConstants;
import com.freudware.carservicescheduler.factory.AppointmentFactory;
import com.freudware.carservicescheduler.model.Appointments;
import com.freudware.carservicescheduler.service.ScheduleService;
import com.freudware.carservicescheduler.validator.AppointmentValidator;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/carServiceScheduler/schedule")
public class ScheduleController {
	private final static Logger LOGGER = Logger.getLogger(ScheduleController.class.getName());
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private AppointmentValidator appointmentValidator;
	
	@Autowired
	private AppointmentFactory appointmentFactory;

	/**
	 * Entry point for home page
	 * @return ModelAndView
	 */
	@RequestMapping("")
	public ModelAndView scheduleHomeFromNavbar()	{
		ModelAndView returnValue = new ModelAndView(JspConstants.SCHEDULE);
		LOGGER.info("Going to schedule home page");
		return returnValue;
	}
	
	/**
	 * API call for get all fullCalendarJs appointments
	 * Method is used exclusively for fullCalendarJs calendar on home page
	 * @param ResponseEntity
	 */
	@RequestMapping(value="/fullCalendarJs", method=RequestMethod.GET)
	public ResponseEntity<String> populateFullCalendarJsRequest()	{
		LOGGER.info("Getting data to be filled into fullCalendarJS request");
		JsonArray json = scheduleService.getFullCalendarJSON();
		return ResponseEntity.status(HttpStatus.CREATED).body(json.toString());
	}
	
	/**
	 * API call for getAppointmentByFirstName
	 * @param firstName
	 * @return ResponseEntity
	 */
	@RequestMapping("/getAppointment/firstName/{firstName}")
	public ResponseEntity<String> getAppointmentByFirstName(@PathVariable String firstName)	{
		LOGGER.info("Getting all appointments by first name");
		JsonArray json = scheduleService.getAppointmentByPersonFirstName(firstName);
		return ResponseEntity.status(HttpStatus.CREATED).body(json.toString());
	}
	
	/**
	 * API call for getAppointmentByLastName
	 * @param lastName
	 * @return ResponseEntity
	 */
	@RequestMapping("/getAppointment/lastName/{lastName}")
	public ResponseEntity<String> getAppointmentByLastName(@PathVariable String lastName)	{
		LOGGER.info("Getting all appointments by last name");
		JsonArray json = scheduleService.getAppointmentByPersonLastName(lastName);
		return ResponseEntity.status(HttpStatus.CREATED).body(json.toString());
	}
	
	/**
	 * API call for getAppointmentByTitle
	 * @param title
	 * @return ResponseEntity
	 */
	@RequestMapping("/getAppointment/title/{title}")
	public ResponseEntity<String> getAppointmentByTitle(@PathVariable String title)	{
		LOGGER.info("Getting all appointments by title");
		JsonArray json = scheduleService.getAppointmentByTitle(title);
		return ResponseEntity.status(HttpStatus.CREATED).body(json.toString());
	}
	
	/**
	 * API call for getAppointmentByStart
	 * @param start
	 * @return ResponseEntity
	 */
	@RequestMapping("/getAppointment/start/{start}")
	public ResponseEntity<String> getAppointmentByStart(@PathVariable String start)	{
		LOGGER.info("Getting all appointments by start");
		JsonArray json = scheduleService.getAppointmentByStart(start);
		return ResponseEntity.status(HttpStatus.CREATED).body(json.toString());
	}
	
	/**
	 * API call for getAppointmentByEnd
	 * @param end
	 * @return ResponseEntity
	 */
	@RequestMapping("/getAppointment/end/{end}")
	public ResponseEntity<String> getAppointmentByEnd(@PathVariable String end)	{
		LOGGER.info("Getting all appointments by end");
		JsonArray json = scheduleService.getAppointmentByEnd(end);
		return ResponseEntity.status(HttpStatus.CREATED).body(json.toString());
	}
	
	/**
	 * API call for getAppointmentBySeverity
	 * @param severity
	 * @return ResponseEntity
	 */
	@RequestMapping("/getAppointment/severity/{severity}")
	public ResponseEntity<String> getAppointmentBySeverity(@PathVariable int severity)	{
		LOGGER.info("Getting all appointments by severity");
		JsonArray json = scheduleService.getAppointmentBySeverity(severity);
		return ResponseEntity.status(HttpStatus.CREATED).body(json.toString());
	}
	
	/**
	 * API call for getAppointmentById
	 * @param id
	 * @return ResponseEntity
	 */
	@RequestMapping("/getAppointment/id/{id}")
	public ResponseEntity<String> getAppointmentById(@PathVariable String id)	{
		LOGGER.info("Getting all appointments by id");
		JsonArray json = scheduleService.getAppointmentById(id);
		return ResponseEntity.status(HttpStatus.CREATED).body(json.toString());
	}
	
	/**
	 * API call for scheduleAppointment
	 * @param json
	 * @return ResponseEntity
	 */
	@RequestMapping(value="/scheduleAppointment", method=RequestMethod.POST, produces="application/json")
	public ResponseEntity<String> scheduleAppointment(@RequestBody String json)	{
		Appointments Appointment = appointmentFactory.createAppointmentForPost(json);
		if(appointmentValidator.isValidAppointment(Appointment))	{
			JsonObject jsonObject = scheduleService.createAppointment(Appointment);
			return ResponseEntity.status(HttpStatus.CREATED).body(jsonObject.toString());
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.INVALID_FIELD);
		}
	}
}
