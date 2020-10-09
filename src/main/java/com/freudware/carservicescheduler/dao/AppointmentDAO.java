package com.freudware.carservicescheduler.dao;

import com.freudware.carservicescheduler.model.Appointments;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public interface AppointmentDAO {
	
    public JsonObject createAppointment(Appointments Appointment);

    public JsonArray getAllAppointments();
    
    public JsonArray getAppointmentById(String id);

    public JsonArray getAppointmentByPersonFirstName(String firstName);

    public JsonArray getAppointmentByPersonLastName(String lastName);

    public JsonArray getAppointmentByTitle(String title);

    public JsonArray getAppointmentByStart(String start);

    public JsonArray getAppointmentByEnd(String end);

    public JsonArray getAppointmentBySeverity(int severity);
	
	public JsonObject updateAppointment(Appointments Appointment);
	
	public JsonObject deleteAppointment(String id);
	
	public JsonArray getFullCalendarJSON();
}
