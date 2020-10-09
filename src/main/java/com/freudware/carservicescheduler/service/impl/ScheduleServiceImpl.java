package com.freudware.carservicescheduler.service.impl;

import com.freudware.carservicescheduler.dao.AppointmentDAO;
import com.freudware.carservicescheduler.factory.AppointmentFactory;
import com.freudware.carservicescheduler.model.Appointments;
import com.freudware.carservicescheduler.service.ScheduleService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService	{
	
	@Autowired
	private AppointmentDAO appointmentDAO;
	
	@Autowired
	private AppointmentFactory appointmentFactory;
	
	/**
	 * Calls the DAO layer
	 * @param Appointment
	 * @return JsonObject
	 */
	public JsonObject createAppointment(Appointments Appointment)	{
		JsonObject json = appointmentDAO.createAppointment(Appointment);
		return json;
	}
	
	/**
	 * Calls the DAO layer
	 * @return JsonArray
	 */
	public JsonArray getAllAppointments()	{
		JsonArray json = appointmentDAO.getAllAppointments();
		return json;
    }
    
	/**
	 * Calls the DAO layer
	 * @return JsonArray
	 */
	public JsonArray getAppointmentById(String id)	{
		JsonArray json = appointmentDAO.getAppointmentById(id);
		return json;
    }

	/**
	 * Calls the DAO layer
	 * @return JsonArray
	 */
	public JsonArray getAppointmentByPersonFirstName(String firstName)	{
		JsonArray json = appointmentDAO.getAppointmentByPersonFirstName(firstName);
		return json;
    }

	/**
	 * Calls the DAO layer
	 * @return JsonArray
	 */
	public JsonArray getAppointmentByPersonLastName(String lastName)	{
		JsonArray json = appointmentDAO.getAppointmentByPersonLastName(lastName);
		return json;
    }

	/**
	 * Calls the DAO layer
	 * @return JsonArray
	 */
	public JsonArray getAppointmentByTitle(String title)	{
		JsonArray json = appointmentDAO.getAppointmentByTitle(title);
		return json;
    }

	/**
	 * Calls the DAO layer
	 * @return JsonArray
	 */
	public JsonArray getAppointmentByStart(String start)	{
		JsonArray json = appointmentDAO.getAppointmentByStart(start);
		return json;
    }

	/**
	 * Calls the DAO layer
	 * @return JsonArray
	 */
	public JsonArray getAppointmentByEnd(String end)	{
		JsonArray json = appointmentDAO.getAppointmentByEnd(end);
		return json;
    }

	/**
	 * Calls the DAO layer
	 * @return JsonArray
	 */
	public JsonArray getAppointmentBySeverity(int severity)	{
		JsonArray json = appointmentDAO.getAppointmentBySeverity(severity);
		return json;
    }
	
	/**
	 * Calls the DAO layer
	 * @param json
	 * @return JsonObject
	 */
	public JsonObject updateAppointment(String json)	{
		Appointments Appointment = appointmentFactory.createAppointmentForPut(json);
		JsonObject jsonObject = appointmentDAO.updateAppointment(Appointment);
		return jsonObject;
	}
	
	/**
	 * Calls the DAO layer
	 * @param id
	 * @return JsonObject
	 */
	public JsonObject deleteAppointment(String id)	{
		JsonObject json = appointmentDAO.deleteAppointment(id);
		return json;
	}
	
	/**
	 * Calls the DAO layer
	 * @return JsonArray
	 */
	public JsonArray getFullCalendarJSON()	{
		JsonArray json = appointmentDAO.getFullCalendarJSON();
		return json;
	}
	
}
