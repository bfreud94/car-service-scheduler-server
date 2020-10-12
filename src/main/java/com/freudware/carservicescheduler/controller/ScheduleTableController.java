package com.freudware.carservicescheduler.controller;

import java.util.logging.Logger;

import com.freudware.carservicescheduler.constants.Constants;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/scheduleTable")
public class ScheduleTableController {
	private final static Logger LOGGER = Logger.getLogger(ScheduleTableController.class.getName());
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private AppointmentFactory appointmentFactory;
	
	@Autowired
	private AppointmentValidator appointmentValidator;
	
	/**
	 * API call for getAllAppointments
	 * @return ResponseEntity
	 */
	@RequestMapping("/getAllAppointments")
	public ResponseEntity<String> getAllAppointments()	{
		LOGGER.info("Getting all appointments");
		JsonArray json = scheduleService.getAllAppointments();
		return ResponseEntity.ok(json.toString());
	}
	
	/**
	 * API call for updateAppointment
	 * @param json
	 * @return ResponseEntity
	 */
	@RequestMapping(value="/updateAppointment", method=RequestMethod.PUT, produces="application/json")
	public ResponseEntity<String> updateAppointment(@RequestBody String json)	{
        LOGGER.info("Updating appointment");
		Appointments Appointment = appointmentFactory.createAppointmentForPut(json);
		if(appointmentValidator.isValidAppointment(Appointment))	{
            JsonObject jsonObject = scheduleService.updateAppointment(json);
            jsonObject.addProperty("updated", true);
			return ResponseEntity.ok(jsonObject.toString());
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Constants.INVALID_FIELD);
		}
	}
	
	/**
	 * API call for deleteAppointment
	 * @param id
	 * @return ResponseEntity
	 */
	@RequestMapping(value="/deleteAppointment", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteAppointment(@RequestBody String id)	{
        LOGGER.info("Deleting appointment");
        JsonObject json = scheduleService.deleteAppointment(id);
        json.addProperty("deleted", true);
		return ResponseEntity.ok(json.toString());
	}
}
