package com.mslabs.spring.mongo.demo.queries;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import com.mslabs.spring.mongo.demo.domain.FlightInformation;
import com.mslabs.spring.mongo.demo.domain.FlightType;

/**
 * 
 * Mongo Template
 * 
 * More flexible. Can tackle any database operation. But the rely on strings and
 * are more error prone. However, they allow us to access low level database
 * APIs.
 * 
 * Repositories
 * 
 * Great by abstracting the persistence layer. Improved ty[e safety and cleaner
 * code. Not suitable for complex queries or projectoions.
 *
 */
@Service
public class FlightInformationQueries {
	private MongoTemplate mongoTemplate;

	public FlightInformationQueries(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	/*
	 * 1) Find all flights with paging and sorting
	 * 
	 * 2) Find by flight id
	 * 
	 * 3) Count all international flight
	 * 
	 * 4) Find all flights by departure city
	 * 
	 * 5) Find all flights by duration between min and max
	 * 
	 * 6) Find all flights delayed at a particular departure city
	 * 
	 * 7) Find all flights that are on time and relate to a city
	 * 
	 * 8) Find by aircraft model
	 */

	public List<FlightInformation> findAll(String field, int pageNb, int pageSize) {
		Query allPagedAndOrdered = new Query().with(Sort.by(Sort.Direction.ASC, field))
				.with(PageRequest.of(pageNb, pageSize));

		return this.mongoTemplate.find(allPagedAndOrdered, FlightInformation.class);
	}

	public FlightInformation findSingleById(String id) {
		return this.mongoTemplate.findById(id, FlightInformation.class);
	}

	public long countInternational() {
		Query international = Query.query(Criteria.where("type").is(FlightType.International));
		return this.mongoTemplate.count(international, FlightInformation.class);
	}

	public List<FlightInformation> findByDeparture(String departure) {
		Query byDeparture = new Query().addCriteria(Criteria.where("departure").is(departure));
		return this.mongoTemplate.find(byDeparture, FlightInformation.class);
	}

	public List<FlightInformation> findByDurationBetween(int minMinute, int maxMinute) {
		Query byDurationBetween = Query.query(Criteria.where("durationMin").gte(minMinute).lte(maxMinute))
				.with(Sort.by(Sort.Direction.DESC, "durationMin"));
		return this.mongoTemplate.find(byDurationBetween, FlightInformation.class);
	}

	public List<FlightInformation> findDelayedAtDeparture(String departure) {
		Query delayedAtDeparture = Query.query(Criteria.where("isDelayed").is(true).and("departure").is(departure));

		return this.mongoTemplate.find(delayedAtDeparture, FlightInformation.class);
	}

	public List<FlightInformation> findRelatedToCityAndNotDelayed(String city) {
		Query byCity = Query.query(
				new Criteria().orOperator(Criteria.where("departure").is(city), Criteria.where("destination").is(city))
						.andOperator(Criteria.where("isDelayed").is(false)));
		return this.mongoTemplate.find(byCity, FlightInformation.class);
	}

	public List<FlightInformation> findByAircraft(String aircraft) {
		Query byAircraft = Query.query(Criteria.where("aircraft.model").is(aircraft));
		return this.mongoTemplate.find(byAircraft, FlightInformation.class);
	}

	public List<FlightInformation> findByFreeText(String text) {
		TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matching(text);

		Query byFreeText = TextQuery.queryText(textCriteria).sortByScore().with(PageRequest.of(0, 3));

		return this.mongoTemplate.find(byFreeText, FlightInformation.class);

	}
}

