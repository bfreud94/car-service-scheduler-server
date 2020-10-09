package com.freudware.carservicescheduler.repository;

import java.util.List;

import com.freudware.carservicescheduler.model.Appointments;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AppointmentRepository extends MongoRepository<Appointments, String> {

    @Query(value = "{ '_id' : ?0 }")
    List<Appointments> findByUserId(String id);

    @Query(value = "{ 'person.firstName' : ?0 }")
    List<Appointments> findByFirstName(String firstName);

    @Query(value = "{ 'person.lastName' : ?0 }")
    List<Appointments> findByLastName(String lastName);

    @Query(value = "{ 'title' : ?0 }")
    List<Appointments> findByTitle(String title);

    @Query(value = "{ 'start' : ?0 }")
    List<Appointments> findByStart(String start);

    @Query(value = "{ 'end' : ?0 }")
    List<Appointments> findByEnd(String end);

    @Query(value = "{ 'severity' : ?0 }")
    List<Appointments> findBySeverity(int severity);

}