package com.mclabs.spring.mongo.dbref.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mclabs.spring.mongo.dbref.domain.Airport;

@Repository
public interface AirportRepository extends MongoRepository<Airport, String> {
}