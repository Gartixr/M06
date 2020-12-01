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
		String url = "jdbc:mysql://10:32.24.210:3306";
		String usuari = "root"; 
		String password = "";

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
			System.out.println("connexi6 realitzada usant DriverManeger"); 
			con.close();
		}
		catch (SQLException ex) {
			System.out.println("Error " + ex.getMessage());
		}


		try {
			//Obtenim el Driver del controlador des de DriverMana. 
			driver = DriverManager.getDriver(url);

			//configurem l'usuari :a contrasenya
			Properties properties = new Properties(); 
			properties.setProperty("usuari", usuari); 
			properties.setProperty("password", password);

			con = driver.connect(url, properties); 
			System.out.println("Connexió realitzada usant Driver"); 
			con.close();
		}
		catch (SQLException ex) {
			System.out.println("Err" + ex.getMessage());
		}

	}
}
