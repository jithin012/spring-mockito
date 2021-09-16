package com.mclabs.spring.mongo.dbref.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mclabs.spring.mongo.dbref.domain.FlightInformation;

@Repository
public interface FlightInformationRepository extends MongoRepository<FlightInformation, String> {

}