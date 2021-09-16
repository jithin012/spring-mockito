package com.mckabs.mongo.basics;

import java.util.Arrays;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class ConnectDB {
	public static void main(String[] args) throws Exception {
		// connecting
		MongoClient mongoClient = new MongoClient(/* new MongoClientURI("mongodb://localhost:27017") */ );
		System.out.println(mongoClient.getDatabaseNames());

		// selecting a DB. If DB not exist then create an get the DB
		DB database = mongoClient.getDB("demo");
		System.out.println(database.getCollectionNames());

		// selecting a collection(table)
		DBCollection collection = database.getCollection("post");
		System.out.println(collection.find());

		// inserting a document(row) into a collection
		List<Integer> books = Arrays.asList(123, 345, 567, 789);
		// .append("_id", "jo")
		DBObject person = new BasicDBObject("name", "Jo Bloogs")
				.append("address", new BasicDBObject("street", "Nellippara").append("city", "Alakode")
						.append("state", "Kerala").append("zip", "670571"))
				.append("book", books);
		collection.insert(person);

		// getting the documents
		DBObject query = new BasicDBObject("name", "Jo Bloogs");
		DBCursor results = collection.find(query);
		showResults(results);


		// getting the document and selecting field
//		DBCursor results = collection.find(new BasicDBObject("name", "Jo Bloogs"), new BasicDBObject("name", 1));
//		showResults(results);

		// querying sub-document
//		DBCursor findAllMalayalies = collection.find(new BasicDBObject("address.state", "Kerala"));
//		showResults(findAllMalayalies);

		// update: default behaves update the first document it finds.
//		collection.update(new BasicDBObject("_id", "jo"),
//				new BasicDBObject("$set", new BasicDBObject("name", "NEW JO")));
//		DBCursor results = collection.find();
//		showResults(results);
		// .update(query, document, doesUpdateMultiple, doesUpsert)

		// delete
//		collection.remove(new BasicDBObject("address.street", "Nellippara"));
//		DBCursor results = collection.find();
//		showResults(results);
	}
	
	public static void showResults(DBCursor results) {
		// sort: -1 -> descending order, 1 indicate ascending order
		for (DBObject result : results) {
			System.out.println(result);
		}
	}
}
