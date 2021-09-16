package com.mslabs.spring.mongo.demo.db;

import org.springframework.core.convert.converter.Converter;

import com.mslabs.spring.mongo.demo.domain.Aircraft;

// convert from DB to Java POJO
public class AircraftDbReadConverter implements Converter<String, Aircraft> {

	public Aircraft convert(String s) {
		if (s == null) {
			return null;
		}
		String[] parts = s.split("/");
		Aircraft aircraft = new Aircraft(parts[0], Integer.parseInt(parts[1]));
		return aircraft;
	}
}
