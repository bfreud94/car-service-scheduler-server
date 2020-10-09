package com.freudware.carservicescheduler.factory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.freudware.carservicescheduler.constants.Constants;
import com.freudware.carservicescheduler.model.Appointments;
import com.freudware.carservicescheduler.model.Person;
import com.freudware.carservicescheduler.util.JsonUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentFactory {
	
	@Autowired
	private JsonUtil jsonUtil;
	
	/**
	 * Creates Appointment for POST call (scheduleAppointment)
	 * Method used exclusively for scheduleAppointment
	 * @param json
	 * @return Appointment
	 */
	public Appointments createAppointmentForPost(String json)	{
		Appointments Appointment = new Appointments();
		Person person = new Person();
		JsonArray jsonArray = jsonUtil.convertStringToJsonArray("[" + json + "]");
		for(JsonElement jsonElement: jsonArray)	{
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			for(String key: jsonObject.keySet())	{
				switch(key)	{
					case Constants.id:
						Appointment.setId(jsonObject.get(key).getAsString());
						break;
					case Constants.firstName:
						person.setFirstName(jsonObject.get(key).getAsString());
						break;
					case Constants.lastName:
						person.setLastName(jsonObject.get(key).getAsString());
						break;
					case Constants.title:
						Appointment.setTitle(jsonObject.get(key).getAsString());
						break;
					case Constants.severity:
						Appointment.setSeverity(jsonObject.get(key).getAsInt());
						break;
					case Constants.start:
						Appointment.setStart(LocalDateTime.parse(jsonObject.get(key).getAsString()));
						break;
				}
			}
		}
		Appointment.setPerson(person);
		Appointment.setCost(Appointment.getSeverity() * 100);
		Appointment.setEnd(Appointment.getStart().plusDays(Appointment.getSeverity()));
		return Appointment;
	}
	
	/**
	 * Creates Appointment for PUT call (updateAppointment)
	 * Method used exclusively for updateAppointment
	 * @param json
	 * @return Appointment
	 */
	public Appointments createAppointmentForPut(String json)	{
		Appointments Appointment = new Appointments();
		Person person = new Person();
		JsonArray jsonArray = jsonUtil.convertStringToJsonArray("[" + json + "]");
		for(JsonElement jsonElement: jsonArray)	{
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			for(String key: jsonObject.keySet())	{
				switch(key)	{
					case Constants.id:
						Appointment.setId(jsonObject.get(key).getAsString());
						break;
					case Constants.person:
						person.setFirstName(jsonObject.get(key).getAsJsonObject().get(Constants.firstName).getAsString());
						person.setLastName(jsonObject.get(key).getAsJsonObject().get(Constants.lastName).getAsString());
						Appointment.setPerson(person);
						break;
					case Constants.title:
						Appointment.setTitle(jsonObject.get(key).getAsString());
						break;
					case Constants.start:
						Appointment.setStart(LocalDateTime.parse(jsonObject.get(key).getAsString()));
						break;
					case Constants.end:
						Appointment.setEnd(LocalDateTime.parse(jsonObject.get(key).getAsString()));
						break;
					case Constants.severity:
						Appointment.setSeverity(jsonObject.get(key).getAsInt());
						break;
					case Constants.cost:
						Appointment.setCost(Double.parseDouble(jsonObject.get(key).getAsString()));
						break;
				}
			}
		}
		return Appointment;
	}
	
	/**
	 * Creates a list of Appointments for GET getAllAppointments (getAllAppointments)
	 * Method used exclusively for getAllAppointments
	 * @param jsonArray
	 * @return List<Appointment>
	 */
	public List<Appointments> createAppointmentsFromJsonArray(JsonArray jsonArray)	{
		List<Appointments> list = new ArrayList<Appointments>();
		for(JsonElement jsonElement: jsonArray)	{
			Appointments Appointment = new Appointments();
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			for(String key: jsonObject.keySet())	{
				switch(key)	{
					case Constants.id:
						Appointment.setId(jsonObject.get(Constants.id).getAsJsonObject().get(Constants.id).getAsString());
						break;
					case Constants.person:
						Appointment.setPerson(createPerson(jsonObject.get(key).getAsJsonObject()));
						break;
					case Constants.title:
						Appointment.setTitle(jsonObject.get(key).getAsString());
						break;
					case Constants.start:
						Appointment.setStart(LocalDateTime.parse(jsonObject.get(key).getAsString()));
						break;
					case Constants.end:
						Appointment.setEnd(LocalDateTime.parse(jsonObject.get(key).getAsString()));
						break;
					case Constants.severity:
						Appointment.setSeverity(jsonObject.get(key).getAsInt());
						break;
					case Constants.cost:
						Appointment.setCost(jsonObject.get(key).getAsDouble());
						break;
				}
			}
			list.add(Appointment);
		}
		return list;
	}
	
	/**
	 * Helper method for creating PersonDTO
	 * @param jsonObject
	 * @return PersonDTO
	 */
	public Person createPerson(JsonObject jsonObject)	{
		Person personDTO = new Person();
		personDTO.setFirstName(jsonObject.get(Constants.firstName).getAsString());
		personDTO.setLastName(jsonObject.get(Constants.lastName).getAsString());
		return personDTO;
	}
}
