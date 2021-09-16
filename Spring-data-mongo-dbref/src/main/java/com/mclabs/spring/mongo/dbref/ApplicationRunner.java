package com.mclabs.spring.mongo.dbref;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.mclabs.spring.mongo.dbref.db.AirportRepository;
import com.mclabs.spring.mongo.dbref.db.FlightInformationRepository;
import com.mclabs.spring.mongo.dbref.domain.Airport;
import com.mclabs.spring.mongo.dbref.domain.FlightInformation;
import com.mclabs.spring.mongo.dbref.domain.FlightPrinter;

@Service
@Order(2)
public class ApplicationRunner implements CommandLineRunner {
	private FlightInformationRepository flightRepository;
	private AirportRepository airportRepository;

	public ApplicationRunner(FlightInformationRepository flightRepository, AirportRepository airportRepository) {
		this.flightRepository = flightRepository;
		this.airportRepository = airportRepository;
	}

	public void run(String... args) {
		// Single update point
		Airport rome = this.airportRepository.findById("1d1aab22-670b-48cb-a027-721e2055731f").get();
		rome.setName("Leonardo da Vinci (Fiumicino)");
		this.airportRepository.save(rome);

		System.out.println("-> AFTER UPDATE TO ROME AIRPORT");
		List<FlightInformation> flights = this.flightRepository.findAll();
		FlightPrinter.print(flights);
	}
}
