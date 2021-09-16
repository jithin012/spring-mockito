package com.mslabs.spring.mongo.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.mslabs.spring.mongo.demo.db.FlightInformationRepository;
import com.mslabs.spring.mongo.demo.domain.FlightInformation;
import com.mslabs.spring.mongo.demo.domain.FlightPrinter;

/*
In Spring Boot, a class that implements CommandLineRunner
is executed after the application is bootstrapped
 */
@Component
@Order(2)
public class ApplicationRunner implements CommandLineRunner {

	private FlightInformationRepository repository;

	public ApplicationRunner(FlightInformationRepository repository) {
		this.repository = repository;
	}

	public void run(String... args) throws Exception {

		printById("4d23fd8b-47a7-45f8-958c-94d0e21488b2");

		delayFlight("4d23fd8b-47a7-45f8-958c-94d0e21488b2", 30);

		removeFlight("4d23fd8b-47a7-45f8-958c-94d0e21488b2");

		printByDepartureAndDestination("Madrid", "Barcelona");

		printByMinNbSeats(200);

	}

	private void printByMinNbSeats(int minNbSeats) {
		System.out.println("Flights by min nb seats " + minNbSeats);
		List<FlightInformation> flights = this.repository.findByMinAircraftNbSeats(minNbSeats);
		FlightPrinter.print(flights);
	}

	private void printByDepartureAndDestination(String dep, String dst) {
		System.out.println("Flights from " + dep + " to " + dst);
		List<FlightInformation> flights = this.repository.findByDepartureCityAndDestinationCity(dep, dst);
		FlightPrinter.print(flights);
	}

	private void removeFlight(String id) {
		this.repository.deleteById(id);
		System.out.println("deleted flight " + id);

	}

	private void delayFlight(String id, int duration) {
		FlightInformation flight = this.repository.findById(id).get();
		flight.setDurationMin(flight.getDurationMin() + duration);
		this.repository.save(flight);
		System.out.println("updated flight " + id + " \n");
	}

	private void printById(String id) {
		System.out.println("Flight " + id);
		FlightInformation flight = this.repository.findById(id).get();
		FlightPrinter.print(Arrays.asList(flight));
	}


}
