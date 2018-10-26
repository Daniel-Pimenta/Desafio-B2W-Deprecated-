package br.com.dotcom.swapi;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SWAPIClient {

	private Integer id;
	private String nome;
	private String clima;
	private String terreno;
	private String qtdAparicao;
	
	
	public void buscarPlanetaPorNome(String nome) {
		System.out.println("SWAPIClient.buscarPlanetaPorNome("+nome+")");
		try {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("https://swapi.co/api/planets/?format=json&search=" + nome);
			String json = target.request().get(String.class);
			ObjectMapper mapper = new ObjectMapper();
			Planeta planeta = mapper.readValue(json, Planeta.class);
			
			if (planeta.getCount() == 1) {
				List<Result> resultados = planeta.getResults();
				Result r = resultados.get(0);
				String id = r.getUrl().replaceAll("[^0-9]*", "");
				this.nome = r.getName();
				this.clima = r.getClimate();
				this.terreno = r.getTerrain();
				this.qtdAparicao = String.valueOf(r.getFilms().size()) ;
				System.out.println("Dados do HOLOCRON: ID:"+id+" Apari��es em filmes:"+this.qtdAparicao);
			}else{
				System.out.println("Planeta n�o consta no HOLOCRON");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public String getQtdAparicao() {
		return qtdAparicao;
	}

	public void setQtdAparicao(String qtdAparicao) {
		this.qtdAparicao = qtdAparicao;
	}
	
}
