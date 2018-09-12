package br.com.dotcom.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;

import br.com.dotcom.db.DBMongo;
import br.com.dotcom.entidade.Planeta;

public class MongoDAO {

	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> collection;

	private String errMsg;

	public boolean getConnection() {
		if (DBMongo.getConnection()) {
			mongoClient = DBMongo.getMongoClient();
			database = DBMongo.getDatabase();
			collection = DBMongo.getCollection();
			System.out.println("Conexão feita !!!");
			return true;
		}
		errMsg = DBMongo.getErrMsg();
		return false;
	}

	public List<Planeta> listarPlanetas() {
		System.out.println("MongoDAO.listarPlanetas()");
		List<Planeta> lista = new ArrayList<>();
		if (getConnection()) {
			List<Document> documents = (List<Document>) collection.find().into(new ArrayList<Document>());
			for (Document document : documents) {
				Planeta planeta = new Planeta();
				planeta.setId(document.getLong("id"));
				planeta.setNome(document.getString("nome"));
				planeta.setClima(document.getString("clima"));
				planeta.setTerreno(document.getString("terreno"));
				planeta.setQtdAparicao(document.getString("qtdAparicao"));
				lista.add(planeta);
			}
			mongoClient.close();
		}
		return lista;
	}

	public Planeta buscarPlanetaPorId(long id) {
		System.out.println("MongoDAO.buscarPlanetaPorId("+id+")");
		List<Planeta> lista = new ArrayList<>();
		if (getConnection()) {
			List<Document> documents = (List<Document>) collection
			  .find(Filters.eq("id", id)).into(new ArrayList<Document>()
			);
			for (Document document : documents) {
				Planeta planeta = new Planeta();
				planeta.setId(document.getLong("id"));
				planeta.setNome(document.getString("nome"));
				planeta.setClima(document.getString("clima"));
				planeta.setTerreno(document.getString("terreno"));
				planeta.setQtdAparicao(document.getString("qtdAparicao"));
				lista.add(planeta);
			}
			mongoClient.close();
		}
		return lista.get(0);
	}

	public List<Planeta> buscarPlanetaPorNome(String nome) {
		System.out.println("MongoDAO.buscarPlanetaPorNome("+nome+")");
		List<Planeta> lista = new ArrayList<>();
		if (getConnection()) {
			List<Document> documents = (List<Document>) collection.find(Filters.eq("nome", nome)).into(new ArrayList<Document>());
			for (Document document : documents) {
				Planeta planeta = new Planeta();
				planeta.setId(document.getLong("id"));
				planeta.setNome(document.getString("nome"));
				planeta.setClima(document.getString("clima"));
				planeta.setTerreno(document.getString("terreno"));
				planeta.setQtdAparicao(document.getString("qtdAparicao"));
				lista.add(planeta);
			}
			mongoClient.close();
		}
		return lista;
	}
	
	public long addPlaneta(Planeta planeta) {
		System.out.println("MongoDAO.addPlaneta()");
		long idGerado = 0;
		try {
			if (getConnection()) {
				
				List<Document> documents = (List<Document>) collection
						.find()
						.sort(Sorts.descending("id"))
						.into(new ArrayList<Document>())
				;
				if (documents.size() > 0) {
					idGerado = documents.iterator().next().getLong("id") + 1;
				}else {
					idGerado = 1;
				}
				
				Document doc = new Document();
				doc.append("id", idGerado);
				doc.append("nome", planeta.getNome());
				doc.append("clima", planeta.getClima());
				doc.append("terreno", planeta.getTerreno());
				doc.append("qtdAparicao", planeta.getQtdAparicao());
				collection.insertOne(doc);
				mongoClient.close();
			}
		} catch (Exception e) {
			errMsg = e.getMessage();
			e.printStackTrace();
		}
		return idGerado;
	}

	public boolean editarPlaneta(Planeta planeta) {
		System.out.println("MongoDAO.editarPlaneta("+planeta.getId()+")");
		try {
			if (getConnection()) {
				List<Document> documents = (List<Document>) collection.find(Filters.eq("id", planeta.getId())).into(new ArrayList<Document>());
				if (documents.size() > 0) {
					update("nome", planeta.getNome(), planeta.getId());
					update("clima", planeta.getClima(), planeta.getId());
					update("terreno", planeta.getTerreno(), planeta.getId());
					update("qtdAparicao", planeta.getQtdAparicao()+""  , planeta.getId());
					mongoClient.close();
					return true;
				}
				mongoClient.close();
				System.out.println("Planeta não encontrado no DB Local");
			}
		} catch (Exception e) {
			errMsg = e.getMessage();
			e.printStackTrace();
		}
		return false;
	}

	public boolean removerPlaneta(long id) {
		System.out.println("MongoDAO.removerPlanetas("+id+")");
		try {
			if (getConnection()) {
				// $eq -> igual
				List<Document> documents = (List<Document>) collection.find(Filters.eq("id", id)).into(new ArrayList<Document>());
				if (documents.size() > 0){
					Bson condition = new Document("$eq", id);
					Bson filter = new Document("id", condition);
					collection.deleteOne(filter);
					mongoClient.close();
					return true;
				}
				System.out.println("Planeta não encontrado no DB Local");
				mongoClient.close();
			}
		} catch (Exception e) {
			errMsg = e.getMessage();
			e.printStackTrace();
		}
		return false;
	}

	private void update(String key, String obj, long id) {
		Bson filter = new Document("id", id);
		Bson newValue = new Document(key, obj);
		Bson updateOperationDocument = new Document("$set", newValue);
		collection.updateMany(filter, updateOperationDocument);
	}

}
