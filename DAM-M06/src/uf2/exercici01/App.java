package uf2.exercici01;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection con = null; 
		Driver driver = null;
		String url = "jdbc:mysql://10.32.24.210:3306";
		String usuari = "remote"; 
		String password = ">9d&pfLYdx.(";

		System.out.println("provaDeConnexio()"); 
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException ex) {
			System.out.println("No s'he trobat el controlador JDSC (" 
					+ ex.getMessage() + ")");
			//Si no tenim controlador no podem far res mes. Sortim. 
			return;
		}

		try {
			//Obtenim una connexi6 des de DriverManager
			con = DriverManager.getConnection(url, usuari, password); 
			System.out.println("connexió realitzada usant DriverManeger"); 
			con.close();
		}
		catch (SQLException ex) {
			System.out.println("Error " + ex.getMessage());
		}

	}
}
