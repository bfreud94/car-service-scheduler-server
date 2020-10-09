package com.freudware.carservicescheduler.transformer;

import com.freudware.carservicescheduler.constants.Constants;
import com.freudware.carservicescheduler.model.Appointments;
import com.freudware.carservicescheduler.model.Person;

import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class AppointmentTransformer {
	
	/**
	 * Converts Appointment into Document
	 * @param Appointment
	 * @return Document
	 */
	public Document convertFromAppointmentToDocument(Appointments Appointment)	{
		Document personDocument = getPersonDocument(Appointment.getPerson());
		Document appointmentDocument = new Document()
				.append(Constants.person, personDocument)
				.append(Constants.title, Appointment.getTitle())
				.append(Constants.start, Appointment.getStart().toString())
				.append(Constants.end, Appointment.getEnd().toString())
				.append(Constants.severity, Appointment.getSeverity())
				.append(Constants.cost, Appointment.getCost());
		return appointmentDocument;
	}
	
	/**
	 * Converts PersonDTO into Document
	 * @param personDTO
	 * @return Document
	 */
	public Document getPersonDocument(Person person)	{
		Document personDocument = new Document()
				.append(Constants.firstName, person.getFirstName())
				.append(Constants.lastName, person.getLastName());
		return personDocument;
	}
}
