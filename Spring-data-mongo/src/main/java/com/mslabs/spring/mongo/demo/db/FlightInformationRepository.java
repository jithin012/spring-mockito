package com.mslabs.spring.mongo.demo.db;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mslabs.spring.mongo.demo.domain.FlightInformation;

/**
 *
 * Repositories
 * 
 * 1) Great by abstracting the persistence layer. Improved ty[e safety and
 * cleaner code. Not suitable for complex queries or projectoions.
 *
 * 2) Great for inserting and deleting data
 *
 * 3) Easy to use for most queries, esp. when you can use query method
 * 
 * Mongo Template
 * 
 * 1) More flexible. Can tackle any database operation. But the rely on strings
 * and are more error prone. However, they allow us to access low level database
 * APIs.
 * 
 * 2) Can built extremely complex queries
 * 
 * 3) Exhaustive support for batch update and partial update
 * 
 * 
 */

@Repository
public interface FlightInformationRepository extends MongoRepository<FlightInformation, String> {

	List<FlightInformation> findByDepartureCityAndDestinationCity(String dep, String dst);

	@Query("{'aircraft.nbSeats' : {$gte: ?0}}")
	List<FlightInformation> findByMinAircraftNbSeats(int minNbSeats);
}
