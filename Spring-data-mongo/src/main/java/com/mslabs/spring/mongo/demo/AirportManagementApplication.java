package com.mslabs.spring.mongo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AirportManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportManagementApplication.class, args);
	}

//	@Bean
//	public MongoCustomConversions customConversion() {
//		List<Converter<?, ?>> converters = new ArrayList();
//		converters.add(new AircraftDbReadConverter());
//		converters.add(new AircraftDbWriteConverter());
//		return new MongoCustomConversions(converters);
//
//	}

}

/**
 * @Document = @Table
 * @Id = @Id
 * @Field = @Column
 * 
 * @Transient = exclude a property from being persisted
 * @Indexed
 * @TextIndexed = full text search
 * @CompoundIndex = allow us to create a indexed from multiple field within our
 *                document
 * @DbRef = acts something like a joint in our case.
 * 
 * 
 * 
 *        Without Indexes
 *        <p>
 *        1. Collection Scan, each Document is evaluated
 * 
 *        2. Slow Searches
 * 
 *        3.Fast Inserts, updates
 * 
 * 
 *        With Indexes
 *        <p>
 *        1. Does not scan every document in collection
 * 
 *        2. Fast searches
 * 
 *        3. Slower inserts/updated
 * 
 * 
 *        Mongo Filter Operators
 * 
 *        1. is/ne - equalty
 * 
 *        2. lt/lte - less than
 * 
 *        3. gt/gte - greater than
 * 
 *        4. in - value in list
 * 
 *        5. exist - has value
 * 
 *        6. regex - patterns
 * 
 * 
 *        Mongo Converter
 *        <p>
 *        Is a feature used for mapping all java types to/from DBObject when
 *        storing or retrieving these object.
 *        <p>
 *        java || MongoDB
 *        <p>
 *        UUID/String || Object ID
 * 
 *        String || String
 * 
 *        int || Number
 * 
 *        double || Number
 * 
 *        boolean || Boolean
 * 
 *        Object || Object(Embedded)
 * 
 */