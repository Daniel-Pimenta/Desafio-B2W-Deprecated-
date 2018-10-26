package br.com.dotcom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.dotcom.db.DBMySQL;
import br.com.dotcom.entidade.Planeta;

public class MySqlDAO {

	boolean ok = false;

	public List<Planeta> listarPlanetas() throws Exception {
		System.out.println("MySqlDAO.listarPlanetas()");
		List<Planeta> lista = new ArrayList<>();

		Connection conexao = DBMySQL.getConnection();

		String sql = "SELECT * FROM TB_Planeta";
		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			Planeta planeta = new Planeta();
			planeta.setId(rs.getInt("ID"));
			planeta.setNome(rs.getString("NOME"));
			planeta.setClima(rs.getString("CLIMA"));
			planeta.setTerreno(rs.getString("TERRENO"));
			planeta.setQtdAparicao(String.valueOf(rs.getInt("QTD_APARICAO")));
			lista.add(planeta);
		}
		System.out.println("Qtd :" + lista.size());
		return lista;
	}

	public Planeta buscarPlanetaPorId(int idPlaneta) throws Exception {
		System.out.println("MySqlDAO.buscarPlanetaPorId(" + idPlaneta + ")");
		Planeta planeta = null;
		Connection conexao = DBMySQL.getConnection();
		String sql = "SELECT * FROM TB_Planeta WHERE ID = ?";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, idPlaneta);
		ResultSet rs = statement.executeQuery();
		if (rs.next()) {
			planeta = new Planeta();
			planeta.setId(rs.getInt("ID"));
			planeta.setNome(rs.getString("NOME"));
			planeta.setClima(rs.getString("CLIMA"));
			planeta.setTerreno(rs.getString("TERRENO"));
			planeta.setQtdAparicao(String.valueOf(rs.getInt("QTD_APARICAO")));
		}
		return planeta;
	}

	public List<Planeta> buscarPlanetaPorNome(String nome) throws Exception {
		System.out.println("MySqlDAO.buscarPlanetaPorNome(" + nome + ")");
		List<Planeta> lista = new ArrayList<>();
		Connection conexao = DBMySQL.getConnection();
		String sql = "SELECT * FROM TB_Planeta WHERE NOME = ?";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, nome);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			Planeta planeta = new Planeta();
			planeta.setId(rs.getInt("ID"));
			planeta.setNome(rs.getString("NOME"));
			planeta.setClima(rs.getString("CLIMA"));
			planeta.setTerreno(rs.getString("TERRENO"));
			planeta.setQtdAparicao(String.valueOf(rs.getInt("QTD_APARICAO")));
			lista.add(planeta);
		}
		System.out.println("Qtd :" + lista.size());
		return lista;
	}

	public int addPlaneta(Planeta Planeta) {
		int idGerado = 0;
		try {
			System.out.println("MySqlDAO.addPlaneta(" + Planeta.getNome() + ")");
			Connection conexao = DBMySQL.getConnection();
			String sql = "INSERT INTO TB_Planeta(NOME, CLIMA, TERRENO, QTD_APARICAO) VALUES(?, ?, ?, ?)";
			
			PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, Planeta.getNome());
			statement.setString(2, Planeta.getClima());
			statement.setString(3, Planeta.getTerreno());
			statement.setInt(4, Integer.parseInt(Planeta.getQtdAparicao()) );
			ok = statement.execute();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				idGerado = rs.getInt(1);
			}
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("MySqlDAO.addPlaneta(" + idGerado + ")");
		return idGerado;
	}

	public boolean editarPlaneta(Planeta planeta) {
		try {
			System.out.println("MySqlDAO.editarPlaneta(" + planeta.getId() + ")");
			Connection conexao = DBMySQL.getConnection();
			String sql = "UPDATE TB_Planeta SET NOME = ?, CLIMA = ? , TERRENO = ?, QTD_APARICAO = ? WHERE ID = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, planeta.getNome());
			statement.setString(2, planeta.getClima());
			statement.setString(3, planeta.getTerreno());
			statement.setInt(4, Integer.parseInt(planeta.getQtdAparicao()) );
			statement.setLong(5, planeta.getId());
			System.out.println(statement.toString());
			ok = statement.executeUpdate() > 0 ? true : false;
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	public boolean removerPlaneta(int idPlaneta) {
		try {
			System.out.println("MySqlDAO.removerPlaneta(" + idPlaneta + ")");
			Connection conexao = DBMySQL.getConnection();
			String sql = "DELETE FROM TB_Planeta WHERE ID = ?";
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, idPlaneta);
			ok = statement.executeUpdate() > 0 ? true : false;
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

}
