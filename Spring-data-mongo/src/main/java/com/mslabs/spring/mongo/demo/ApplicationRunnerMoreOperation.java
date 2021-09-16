package com.mslabs.spring.mongo.demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;

import com.mslabs.spring.mongo.demo.domain.FlightInformation;
import com.mslabs.spring.mongo.demo.domain.FlightPrinter;
import com.mslabs.spring.mongo.demo.queries.FlightInformationQueries;

// disable execution: Will not work below code
//@Component
//@Order(3)
public class ApplicationRunnerMoreOperation implements CommandLineRunner {
	private FlightInformationQueries flightInformationQueries;

	public ApplicationRunnerMoreOperation(FlightInformationQueries flightInformationQueries) {
		this.flightInformationQueries = flightInformationQueries;
	}


	public void run(String... args) throws Exception {

		System.out.println("Application started...");
		System.out.println("-----\nQUERY: All flights ordered by departure");
		List<FlightInformation> allFlightsOrdered = this.flightInformationQueries.findAll("departure", 0, 10);
		FlightPrinter.print(allFlightsOrdered);

		System.out.println("-----\nQUERY: Depart at New York");
		List<FlightInformation> newYorkDepartures = this.flightInformationQueries.findByDeparture("New York");
		FlightPrinter.print(newYorkDepartures);

		System.out.println("-----\nQUERY: Delayed departure at given Madrid");
		List<FlightInformation> delayedAtAirport = this.flightInformationQueries.findDelayedAtDeparture("Madrid");
		FlightPrinter.print(delayedAtAirport);

		System.out.println("-----\nQUERY: Duration between 60 and 120 minutes");
		List<FlightInformation> durationBetweenOneAndTwoHours = this.flightInformationQueries.findByDurationBetween(60,
				120);
		FlightPrinter.print(durationBetweenOneAndTwoHours);

		System.out.println("-----\nQUERY: Using a 737 aircraft");
		List<FlightInformation> flightWith737Aircraft = this.flightInformationQueries.findByAircraft("737");
		FlightPrinter.print(flightWith737Aircraft);

//		System.out.println("-----\nQUERY: Free text search: Rome");
//		List<FlightInformation> flightsByFreeText = this.flightInformationQueries.findByFreeText("Rome");
//		FlightPrinter.print(flightsByFreeText);

//		markAllFlightsToRomeAsDelayed();
//		removeFlightsWithDurationLessThanTwoHours();
//		

	}

//	private MongoTemplate mongoTemplate;
//
//	public ApplicationRunnerMoreOperation(MongoTemplate mongoTemplate) {
//		this.mongoTemplate = mongoTemplate;
//	}

//	void markAllFlightsToRomeAsDelayed() {
//		Query departingFromRome = Query.query(Criteria.where("destination").is("Rome"));
//
//		Update setDelayed = Update.update("isDelayed", true);
//
//		this.mongoTemplate.updateMulti(departingFromRome, setDelayed, FlightInformation.class);
//	}
//
//	void removeFlightsWithDurationLessThanTwoHours() {
//		Query lessThanTwoHours = Query.query(Criteria.where("duration").lt(120));
//
//		mongoTemplate.findAllAndRemove(lessThanTwoHours, FlightInformation.class);
//	}
}
