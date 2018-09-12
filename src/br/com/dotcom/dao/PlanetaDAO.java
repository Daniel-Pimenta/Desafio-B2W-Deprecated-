package br.com.dotcom.dao;

import java.util.List;
import br.com.dotcom.dao.MySqlDAO;
import br.com.dotcom.entidade.Planeta;
import br.com.dotcom.swapi.SWAPIClient;

public class PlanetaDAO {

	//MySqlDAO dao = new MySqlDAO();
	MongoDAO dao = new MongoDAO();
	
	public List<Planeta> listarPlanetas() throws Exception {
		return dao.listarPlanetas();
	}

	public Planeta buscarPlanetaPorId(int idPlaneta) throws Exception {
		return dao.buscarPlanetaPorId(idPlaneta);
	}

	public List<Planeta> buscarPlanetaPorNome(String nome) throws Exception {
		return dao.buscarPlanetaPorNome(nome);
	}	
	
	public long addPlaneta(Planeta planeta) throws Exception {
		SWAPIClient swapi = new SWAPIClient();
		swapi.buscarPlanetaPorNome(planeta.getNome());
		//planeta.setClima(swapi.getClima());
		//planeta.setTerreno(swapi.getTerreno());
		planeta.setQtdAparicao(swapi.getQtdAparicao());
		return dao.addPlaneta(planeta);
	}

	public boolean editarPlaneta(Planeta planeta)  throws Exception  {
		SWAPIClient swapi = new SWAPIClient();
		swapi.buscarPlanetaPorNome(planeta.getNome());
		//planeta.setClima(swapi.getClima());
		//planeta.setTerreno(swapi.getTerreno());
		planeta.setQtdAparicao(swapi.getQtdAparicao());
		return dao.editarPlaneta(planeta);
	}

	public boolean removerPlaneta(int idPlaneta) throws Exception  {	
		return dao.removerPlaneta(idPlaneta);
	}

}
