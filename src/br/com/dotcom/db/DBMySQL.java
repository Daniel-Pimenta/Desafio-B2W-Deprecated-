package br.com.dotcom.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBMySQL {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		System.out.println("DBMySQL.getConnection()");
		//Class.forName("com.mysql.jdbc.Driver");
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/planeta_db?useSSL=false&useTimezone=true&serverTimezone=UTC", "root", "wendy001");
	}

}
