package br.com.dotcom.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.dotcom.dao.PlanetaDAO;
import br.com.dotcom.entidade.Planeta;

@Path("/planeta")
public class PlanetaService {

	private PlanetaDAO planetaDAO;
	private static final String CHARSET_UTF8 = ";charset=utf-8";

	@PostConstruct
	private void init() {
		System.out.println("PlanetaService.init()");
		planetaDAO = new PlanetaDAO();
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public List<Planeta> listarPlanetas() {
		System.out.println("PlanetaService.GET()");
		List<Planeta> lista = null;
		try {
			lista = planetaDAO.listarPlanetas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String addPlaneta(Planeta planeta) {
		String msg = "";
		System.out.println("PlanetaService.addPlaneta(" + planeta.getNome() + ")");
		try {
			long idGerado = planetaDAO.addPlaneta(planeta);
			msg = String.valueOf("Planeta Gerado id:" + idGerado);
		} catch (Exception e) {
			msg = "Erro ao add o planeta!";
			e.printStackTrace();
		}
		return msg;
	}

	@GET
	@Path("/get/id/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public Planeta buscarPorId(@PathParam("id") int idPlaneta) {
		System.out.println("PlanetaService.GET(" + idPlaneta + ")");
		Planeta planeta = null;
		try {
			planeta = planetaDAO.buscarPlanetaPorId(idPlaneta);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return planeta;
	}

	@GET
	@Path("/get/nome/{nome}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public List<Planeta> buscarPorNome(@PathParam("nome") String nome) {
		System.out.println("PlanetaService.GET(" + nome + ")");
		List<Planeta> lista = null;
		try {
			lista = planetaDAO.buscarPlanetaPorNome(nome);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarPlaneta(Planeta planeta, @PathParam("id") Integer idPlaneta) {
		System.out.println("PlanetaService.editaPlaneta(" + idPlaneta + ")");
		String msg = "";
		//System.out.println(planeta.getNome());
		try {
			planeta.setId(idPlaneta);
			if (planetaDAO.editarPlaneta(planeta)) {
				msg = "Planeta editada com sucesso!";
			} else {
				msg = "Planeta não encontrado!";
			}
		} catch (Exception e) {
			msg = "Erro ao editar a planeta!";
			e.printStackTrace();
		}

		return msg;
	}

	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removerPlaneta(@PathParam("id") int idPlaneta) {
		System.out.println("PlanetaService.removePlaneta(" + idPlaneta + ")");
		String msg = "";

		try {
			if (planetaDAO.removerPlaneta(idPlaneta)) {
				msg = "Planeta removido com sucesso!";
			} else {
				msg = "Planeta não encontrado!";
			}
		} catch (Exception e) {
			msg = "Erro ao remover a planeta!";
			e.printStackTrace();
		}

		return msg;
	}

}
