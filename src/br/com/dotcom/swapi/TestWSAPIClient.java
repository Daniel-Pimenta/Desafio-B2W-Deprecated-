package br.com.dotcom.swapi;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestWSAPIClient {

	public static void main(String[] args) {
		try {
			System.out.println("Conectando...");
			Client client = ClientBuilder.newClient();
			//WebTarget target = client.target("https://samples.openweathermap.org/data/2.5/weather?id=2172797&appid=b6907d289e10d714a6e88b30761fae22");
			WebTarget target = client.target("https://swapi.co/api/planets/?format=json&search=Tatooine");
			String json = target.request().get(String.class);
			//
			ObjectMapper mapper = new ObjectMapper();
			//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			//mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
			//mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
			Planeta planeta = mapper.readValue(json, Planeta.class);
			System.out.println(json);
      System.out.println(planeta.getCount());
		} catch (javax.ws.rs.NotAuthorizedException e) {
			System.out.println("HTTP 401 Unauthorized");			
		} catch (JsonParseException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (JsonMappingException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
