package models;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class DBMongoConnection {

	private static DB mongoDB;

	private DBMongoConnection() {
		Mongo mongo;
		try {
			mongo = new Mongo("localhost", 27017);
			mongoDB = mongo.getDB("things-api-db");

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}

	public static DB getInstance() {
		if (mongoDB == null) {
			new DBMongoConnection();
		}
		return mongoDB;
	}

}
