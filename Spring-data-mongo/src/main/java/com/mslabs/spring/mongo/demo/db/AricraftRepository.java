package com.mslabs.spring.mongo.demo.db;

/*
 * //@Document class Airport { // @Id String id; int flightsPerDay; String name;
 * boolean closed; Location location; } class Location { String city; String
 * country; String email; }
 * 
 * 
 * @Repository public interface AricraftRepository extends
 * MongoRepository<Airport, String> {
 * 
 * ((((RETURN_TYPE + METHOD_PREFIX + PROPERTY_NAME + FILTERS)))
 * 
 * List<Airport> findByFlightsPerDayGreaterThan(int value)
 * 
 * List<Airport> findByFlightsPerDayBetween(int min, int max)
 * 
 * List<Airport> findByFlightsPerDayGreaterThanEqual(int value)
 * 
 * List<Airport> findByFlightsPerDayLessThanEqual(int value) }
 * 
 * 
 * 
 * <<<Query Methods for String Properties>>>
 * 
 * List<Airport> findByNameLike(String airportName)
 * 
 * List<Airport> findByNameNotNull()
 * 
 * List<Airport> findByNameNull()
 * 
 * 
 * 
 * <<<Exact Value>>>
 * 
 * Optional<Airport> findByName(String airportName)
 * 
 * Airport findByName(String airportName)
 * 
 * 
 * 
 * <<<Query Method for Boolean Value>>>
 * 
 * List<Airport> findByClosedTrue()
 * 
 * List<Airport> findByClosedFalse()
 * 
 * 
 * 
 * <<<Complex Query Method>>>
 * 
 * List<Airport> findByClosedTrueAndFlightsPerDayGreaterThan(int minFlights)
 * 
 * 
 * <<<Custome Queries>>>
 * 
 * @Query("{'location.city': ?0}") List<Airport> findByCity(String city)
 * 
 * @Query("{'flightsPerDay': {$lte: 50}}") List<Airport> findSmallAirports();
 */

/*
 * Here is the formula for Build query method
 * 
 * RETURN_TYPE + METHOD_PREFIX + PROPERTY_NAME + FILTERS
 */

// eg.
// repo.findByFlightsPerDayGreaterThan(200)

