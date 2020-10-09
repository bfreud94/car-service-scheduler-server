package com.freudware.carservicescheduler.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Appointments {
	private Person person;
	private String id;
	private String title;
	private int severity;
	private LocalDateTime start;
	private LocalDateTime end;
	private double cost;

	public Appointments()	{
	}
	
	public Appointments(Person person, String title, int severity, LocalDateTime start)	{
		this.person = person;
		this.title = title;
		this.severity = severity;
		this.start = start;
		this.end = start.plusDays(severity);
		this.cost = severity * 100;
	}

	public Appointments(Person person, String title, int severity, LocalDateTime start, LocalDateTime end, double cost)	{
		this.person = person;
		this.title = title;
		this.severity = severity;
		this.start = start;
		this.end = start.plusDays(severity);
		this.cost = severity * 100;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public String getId()	{
		return this.id;
	}
	
	public void setId(String id)	{
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public int getSeverity() {
		return this.severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}
	
	public LocalDateTime getStart() {
		return this.start;
	}
	
	public LocalDateTime getEnd() {
		return this.end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public double getCost() {
		return this.cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
    }
    
    @Override
    public String toString() {
        return "{" +
            "\"person\": " + (this.person == null ? "" : this.person.toString()) + ", " +
            "\"id\": \"" + (this.id == null ? "" : this.id) + "\", " +
            "\"title\": \"" + this.title + "\", " +
            "\"severity\": \"" + this.severity + "\", " +
            "\"start\": \"" + (this.start == null ? "" : this.start.toString()) + "\", " +
            "\"end\": \"" + (this.end == null ? "" : this.end.toString()) + "\", " +
            "\"cost\": \"" + this.cost + "\"" +
        "}";
    }
}
