package com.mslabs.spring.mongo.demo.db;

import org.springframework.core.convert.converter.Converter;

import com.mslabs.spring.mongo.demo.domain.Aircraft;

//convert from JAVA to DB
public class AircraftDbWriteConverter implements Converter<Aircraft, String> {
	public String convert(Aircraft aircraft) {
		return aircraft.getModel() + "/" + aircraft.getNbSeats();
	}
}
