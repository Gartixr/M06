package uf2.exercici01;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {

	static Connection con = null;
	static Statement selectStmt;
	static Scanner teclado =  new Scanner(System.in);

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		Driver driver = null;
		String url = "jdbc:mysql://10.32.24.210:3306/alumnes";
		String usuari = "remote"; 
		String password = ">9d&pfLYdx.(";
		boolean menu = true;

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
			selectStmt = con.createStatement();

		}
		catch (SQLException ex) {
			System.out.println("Error " + ex.getMessage());
		}


		while (menu) {

			System.out.println("[1] - Select all\n"
					+ "[2] - Select one\n"
					+ "[3] - Actualizar\n"
					+ "[4] - Insertar\n"
					+ "[5] - Eliminar\n"
					+ "[6] - Salir");

			int input = teclado.nextInt();
			int selected = 0;

			switch (input) {
			case 1:
				selectAll();
				break;
			case 2:
				System.out.print("ID a mostrar: ");
				selected = teclado.nextInt();
				selectOne(selected);
				break;
			case 3:
				System.out.print("ID a actualizar: ");
				selected = teclado.nextInt();
				update(selected);
				break;
			case 4:
				insert(selected);
				break;
			case 5:
				System.out.print("ID a eliminar:");
				selected = teclado.nextInt();
				delete(selected);
				break;

			default:
				System.out.println("Desconectado.");
				menu = false;
				con.close();
				break;
			}

		}

	}

	private static void delete(int selected) throws SQLException {
		// TODO Auto-generated method stub

		System.out.println("Estas seguro que deseas eliminar el alumno con id = " + selected + "? (Y/N)");
		
		if(teclado.next().equalsIgnoreCase("y")) {
			selectStmt.execute("DELETE FROM alumnes WHERE ID = " + selected);
			System.out.println("Alumne eliminado correctamente");
		}
		
	}

	private static void insert(int selected) throws SQLException {
		// TODO Auto-generated method stub

		System.out.print("Nom: ");
		String nom = teclado.next();
		System.out.print("Dni: ");
		String dni = teclado.next();
		System.out.print("Data naixement (yyyy/mm/dd): ");
		String dataNaixement = teclado.next();
		System.out.print("Adreça postal: ");
		String adrecaPostal = teclado.next();
		System.out.print("Sexe: ");
		String sexe = teclado.next();
		System.out.print("Codi postal: ");
		String codiPostal = teclado.next();
		System.out.print("Població: ");
		String poblacio = teclado.next();
		
		selectStmt.execute("INSERT INTO alumnes (nom, dni, dataNaixement, adrecaPostal, sexe, codiPostal, poblacio) "
				+ "VALUES ('" + nom + "', '" + dni + "', '" + dataNaixement + "', '" 
				+ adrecaPostal + "', '" + sexe + "', '" + codiPostal + "', '" + poblacio + "');");
		
	}

	private static void update(int selected) throws SQLException {
		// TODO Auto-generated method stub

		ResultSet rs = selectStmt.executeQuery("SELECT * FROM alumnes WHERE id = " + selected);

		while (rs.next()) {
			System.out.print("Nom: ");
			String nom = teclado.next();
			if(nom.length() <= 0) {
				nom = rs.getString(2);
			}
			System.out.print("Dni: ");
			String dni = teclado.next();
			if(dni.length() <= 0) {
				dni = rs.getString(3);
			}
			System.out.print("Data naixement (yyyy/mm/dd): ");
			String dataNaixement = teclado.next();
			if(dataNaixement.length() <= 0) {
				dataNaixement = rs.getString(4);
			}
			System.out.print("Adreça postal: ");
			String adrecaPostal = teclado.next();
			if(adrecaPostal.length() <= 0) {
				adrecaPostal = rs.getString(5);
			}
			System.out.print("Sexe: ");
			String sexe = teclado.next();
			if(sexe.length() <= 0) {
				sexe = rs.getString(6);
			}
			System.out.print("Codi postal: ");
			String codiPostal = teclado.next();
			if(codiPostal.length() <= 0) {
				codiPostal = rs.getString(7);
			}
			System.out.print("Població: ");
			String poblacio = teclado.next();
			if(poblacio.length() <= 0) {
				poblacio = rs.getString(8);
			}
			
			System.out.println("Segur que vols actualitzar el alumne amb id = " + selected + "? (Y/N)");
			
			if(teclado.next().equalsIgnoreCase("y")) {
				selectStmt.execute("UPDATE alumnes "
						+ "SET nom = "+nom+", dni = "+dni+", dataNaixement = "+dataNaixement+", sexe = "+sexe+", codiPostal = "+codiPostal+", poblacio = "+poblacio+" "
						+ "WHERE id = "+selected+";");
				System.out.println("Alumne actualizat correctament");
			}
			
		}
		
	}

	private static void selectOne(int selected) throws SQLException {

		ResultSet rs = selectStmt.executeQuery("SELECT * FROM alumnes WHERE id = " + selected);
		
		while (rs.next()) {
			System.out.println("------------------------");
			System.out.println("ID: " + rs.getString(1));
			System.out.println("\tNom: " + rs.getString(2));
			System.out.println("\tDNI: " + rs.getString(3));
			System.out.println("\tData naixement: " + rs.getString(4));
			System.out.println("\tAdreça postal: " + rs.getString(5));
			System.out.println("\tSexe: " + rs.getString(6));
			System.out.println("\tCodi postal: " + rs.getString(7));
			System.out.println("\tPoblació: " + rs.getString(8));
			System.out.println("------------------------");
		}
	}

	private static void selectAll() throws SQLException {

		ResultSet rs = selectStmt.executeQuery("SELECT * FROM alumnes");

		while (rs.next()) {
			System.out.println("------------------------");
			System.out.println("ID: " + rs.getString(1));
			System.out.println("\tNom: " + rs.getString(2));
			System.out.println("\tDNI: " + rs.getString(3));
			System.out.println("\tData naixement: " + rs.getString(4));
			System.out.println("\tAdreça postal: " + rs.getString(5));
			System.out.println("\tSexe: " + rs.getString(6));
			System.out.println("\tCodi postal: " + rs.getString(7));
			System.out.println("\tPoblació: " + rs.getString(8));
			System.out.println("------------------------");
		}
	}
}
