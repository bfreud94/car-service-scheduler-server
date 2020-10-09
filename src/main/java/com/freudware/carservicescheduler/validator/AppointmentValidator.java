package com.freudware.carservicescheduler.validator;

import java.time.LocalDateTime;

import com.freudware.carservicescheduler.model.Appointments;

import org.springframework.stereotype.Component;

@Component
public class AppointmentValidator {
	
	/**
	 * Validation for creating and updating an appointment
	 * @param Appointment
	 * @return boolean
	 */
	public boolean isValidAppointment(Appointments Appointment)	{
		String firstName = Appointment.getPerson().getFirstName();
		String lastName = Appointment.getPerson().getLastName();
		String title = Appointment.getTitle();
		int severity = Appointment.getSeverity();
		double cost = Appointment.getCost();
		LocalDateTime start = Appointment.getStart();
		LocalDateTime end = Appointment.getEnd();
		
		// Empty field check
		if(firstName.isEmpty() || lastName.isEmpty() || title.isEmpty() || Integer.toString(severity).isEmpty() || start.toString().isEmpty() || end.toString().isEmpty() || (cost + "").toString().isEmpty())	{
			return false;
		} else {
			// Severity must be in between 0 and 10, inclusive
			if(severity < 0 || severity > 10)	{
				return false;
			// Start date must be before end
			} else if(start.isAfter(end))	{
				return false;
			} else if((cost + "").matches("^[a-zA-Z]*$") || cost < 0)	{
				return false;
			}
		}
		return true;
	}
}
