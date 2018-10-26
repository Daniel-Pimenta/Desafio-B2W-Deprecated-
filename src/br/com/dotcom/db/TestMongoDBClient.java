package br.com.dotcom.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;

public class TestMongoDBClient {

	public static void main(String[] args) {
		MongoClient mongoClient;
		MongoDatabase database;
		MongoCollection<Document> collection;
		System.out.println("Conectando...");
		try {
			if (DBMongo.getConnection()) {
				System.out.println("  ..OK");
				mongoClient = DBMongo.getMongoClient();
				database = DBMongo.getDatabase();
				collection = DBMongo.getCollection();
				//
				MongoIterable<String> dbNames = mongoClient.listDatabaseNames();
				Iterator i = dbNames.iterator();
				System.out.println("Listando Databases...");
				while (i.hasNext()) {
					String dbName = i.next().toString();
					System.out.println(dbName);
					database = mongoClient.getDatabase(dbName);

					// --

					MongoIterable<String> colNames = database.listCollectionNames();
					Iterator j = colNames.iterator();
					System.out.println(" Listando Coleções...");
					try {
						while (j.hasNext()) {
							String collName = j.next().toString();
							System.out.println(" - " + collName);
						}
					} catch (Exception e) {
						System.out.println(" sem Coleções");
					}
				}
				database = mongoClient.getDatabase("b2w");
				collection = database.getCollection("planetas");
				
				System.out.println("  ..Teste Planetas");
				List<Document> documents = (List<Document>) collection.find().into(new ArrayList<Document>());
				for (Document document : documents) {
					System.out.println("-------------------------");
					System.out.println("ID          :" + document.getInteger("id"));
					System.out.println("NOME        :" + document.getString("nome"));
					System.out.println("CLIMA       :" + document.getString("clima"));
					System.out.println("TERRENO     :" + document.getString("terreno"));
					System.out.println("QTD APARICAO:" + document.getString("qtdAparicao"));
				}
				mongoClient.close();
			}else {
				System.out.println(DBMongo.getErrMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
