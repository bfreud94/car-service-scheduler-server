package com.freudware.carservicescheduler.dao.impl;

import java.util.List;

import com.freudware.carservicescheduler.constants.Constants;
import com.freudware.carservicescheduler.dao.AppointmentDAO;
import com.freudware.carservicescheduler.model.Appointments;
import com.freudware.carservicescheduler.repository.AppointmentRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentDAOImpl implements AppointmentDAO {

    @Autowired
    private AppointmentRepository appointmentRepository;
	
	/**
	 * Creates an appointment
	 * @param Appointment
	 * @return JsonArray
	 */
	public JsonObject createAppointment(Appointments appointment)	{
        appointmentRepository.insert(appointment);
        return new JsonParser().parse(appointment.toString()).getAsJsonObject();
	}

	/**
	 * Gets all appointments
	 * @return JsonArray
	 */
    public JsonArray getAllAppointments() {
        JsonArray jsonArray = new JsonArray();
        List<Appointments> appointments = appointmentRepository.findAll();
        for(int i = 0; i < appointments.size(); i++)	{
            Appointments appointment = appointments.get(i);
            JsonElement jsonElement = new JsonParser().parse(appointment.toString());
            jsonArray.add(jsonElement);
        }
        return jsonArray;
    }
    
	/**
	 * Gets appointment by id
	 * @param id
	 * @return JsonArray
	 */
    public JsonArray getAppointmentById(String id) {
        JsonArray jsonArray = new JsonArray();
        List<Appointments> appointments = appointmentRepository.findByUserId(id);
        for(Appointments appointment: appointments) {
            jsonArray.add(new JsonParser().parse(appointment.toString()));
        }
        return jsonArray;
    }

	/**
	 * Gets appointment by a person's first name
	 * @param firstName
	 * @return JsonArray
	 */
    public JsonArray getAppointmentByPersonFirstName(String firstName) {
        JsonArray jsonArray = new JsonArray();
        List<Appointments> appointments = appointmentRepository.findByFirstName(firstName);
        for(Appointments appointment: appointments) {
            jsonArray.add(new JsonParser().parse(appointment.toString()));
        }
        return jsonArray;
    }

	/**
	 * Gets appointment by a person's last name
	 * @param lastName
	 * @return JsonArray
	 */
    public JsonArray getAppointmentByPersonLastName(String lastName) {
        JsonArray jsonArray = new JsonArray();
        List<Appointments> appointments = appointmentRepository.findByLastName(lastName);
        for(Appointments appointment: appointments) {
            jsonArray.add(new JsonParser().parse(appointment.toString()));
        }
        return jsonArray;
    }

	/**
	 * Gets appointment by title
	 * @param title
	 * @return JsonArray
	 */
    public JsonArray getAppointmentByTitle(String title) {
        JsonArray jsonArray = new JsonArray();
        List<Appointments> appointments = appointmentRepository.findByTitle(title);
        for(Appointments appointment: appointments) {
            jsonArray.add(new JsonParser().parse(appointment.toString()));
        }
        return jsonArray;
    }

	/**
	 * Gets appointment by start
	 * @param start
	 * @return JsonArray
	 */
    public JsonArray getAppointmentByStart(String start) {
        JsonArray jsonArray = new JsonArray();
        List<Appointments> appointments = appointmentRepository.findByStart(start);
        for(Appointments appointment: appointments) {
            jsonArray.add(new JsonParser().parse(appointment.toString()));
        }
        return jsonArray;
    }

	/**
	 * Gets appointment by end
	 * @param end
	 * @return JsonArray
	 */
    public JsonArray getAppointmentByEnd(String end) {
        JsonArray jsonArray = new JsonArray();
        List<Appointments> appointments = appointmentRepository.findByEnd(end);
        for(Appointments appointment: appointments) {
            jsonArray.add(new JsonParser().parse(appointment.toString()));
        }
        return jsonArray;
    }

	/**
	 * Gets appointment by severity
	 * @param severity
	 * @return JsonArray
	 */
    public JsonArray getAppointmentBySeverity(int severity) {
        JsonArray jsonArray = new JsonArray();
        List<Appointments> appointments = appointmentRepository.findBySeverity(severity);
        for(Appointments appointment: appointments) {
            jsonArray.add(new JsonParser().parse(appointment.toString()));
        }
        return jsonArray;
    }
	
	/**
	 * Update an appointment
	 * @param Appointment
	 * @return JsonObject
	 */
    public JsonObject updateAppointment(Appointments appointment)	{
        appointmentRepository.save(appointment);
        return new JsonParser().parse(appointment.toString()).getAsJsonObject();
	}
	
	/**
	 * Deletes an appointment
	 * @param id
	 * @return JsonObject
	 */
	public JsonObject deleteAppointment(String id)	{
        List<Appointments> appointments = appointmentRepository.findByUserId(id);
        if(appointments.size() != 0) {
            Appointments appointment = appointments.size() != 0 ? appointments.get(0) : new Appointments();
            appointmentRepository.deleteById(appointments.get(0).getId());
            return new JsonParser().parse(appointment.toString()).getAsJsonObject();
        } else {
            return new JsonParser().parse(Constants.EMPTY_JSON).getAsJsonObject();
        }
	}
	
	/**
	 * Get for fullCalendarJs API call
	 * Catered specifically for fullCalendarJs
	 * @return JsonArray
	 */
	public JsonArray getFullCalendarJSON()	{
		JsonArray fullCalendarJsonArray = new JsonArray();
		try	{
			JsonArray appointments = getAllAppointments();
			for(JsonElement jsonElement: appointments)	{
				JsonObject mLabJsonObject = jsonElement.getAsJsonObject();
				JsonObject fullCalendarJsonObject = new JsonObject();
				for(String key: mLabJsonObject.keySet())	{
					switch(key)	{
						case Constants.start:
						case Constants.end:
						case Constants.title:
							String value = mLabJsonObject.get(key).getAsString();
							fullCalendarJsonObject.addProperty(key, value);
					}
				}
				fullCalendarJsonArray.add(fullCalendarJsonObject);
			}
		} catch(Exception e)	{
			e.printStackTrace();
		}
		return fullCalendarJsonArray;
	}
}
