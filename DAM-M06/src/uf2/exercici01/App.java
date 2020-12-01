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

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		Scanner teclado =  new Scanner(System.in);
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

	private static void delete(int selected) {
		// TODO Auto-generated method stub

	}

	private static void insert(int selected) {
		// TODO Auto-generated method stub

	}

	private static void update(int selected) {
		// TODO Auto-generated method stub

	}

	private static void selectOne(int selected) throws SQLException {

		ResultSet rs = selectStmt.executeQuery("SELECT * FROM alumnes WHERE id = " + selected);
		
		while (rs.next()) {
			System.out.println("------DADES ALUMNES ID = " + selected + "------");
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

		ResultSet rs = selectStmt.executeQuery("SELECT nom FROM alumnes");

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
