package br.com.dotcom.db;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DBMongo {

	private static MongoClient mongoClient;
	private static MongoDatabase database;
	private static MongoCollection<Document> collection;

	private static String errMsg;
	private static boolean local = false;
	

	public static boolean getConnection() {
		try {
			if (local) {
				System.out.println("Conectando Local DataBase...  (MongoDB)");
				mongoClient = new MongoClient("localhost:27017");
				database = mongoClient.getDatabase("local");
				collection = database.getCollection("planetas");
			} else {
				System.out.println("Conectando Cloud DataBase...  (MongoDB)");
				String url = "mongodb+srv://b2w:b2w@cluster0-is1wv.mongodb.net";
				MongoClientURI uri = new MongoClientURI(url);
				mongoClient = new MongoClient(uri);
				database = mongoClient.getDatabase("b2w");
				collection = database.getCollection("planetas");
			}
			return true;
		} catch (Exception e) {
			errMsg = e.getMessage();
		}
		return false;
	}
	
	public static MongoClient getMongoClient() {
		return mongoClient;
	}

	public static MongoDatabase getDatabase() {
		return database;
	}

	public static MongoCollection<Document> getCollection() {
		return collection;
	}

	public static String getErrMsg() {
		return errMsg;
	}

	
}
