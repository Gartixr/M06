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
		String url = "jdbc:mysql://192.168.1.138:3306/alumnes";
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
			System.out.println("Errror " + ex.getMessage());
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
				selectOne(selected, true);
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

		//		ResultSet rs = selectStmt.executeQuery("SELECT * FROM alumnes WHERE id = " + selected);

		String[] arrResults = selectOne(selected, false);		

		System.out.print("Nom: ");
		String nom = teclado.next();
		if(nom.length() <= 0) {
			nom = arrResults[1];
		}
		System.out.print("Dni: ");
		String dni = teclado.next();
		if(dni.length() <= 0) {
			dni = arrResults[2];
		}
		System.out.print("Data naixement (yyyy/mm/dd): ");
		String dataNaixement = teclado.next();
		if(dataNaixement.length() <= 0) {
			dataNaixement = arrResults[3];
		}
		System.out.print("Adreça postal: ");
		String adrecaPostal = teclado.next();
		if(adrecaPostal.length() <= 0) {
			adrecaPostal = arrResults[4];
		}
		System.out.print("Sexe: ");
		String sexe = teclado.next();
		if(sexe.length() <= 0) {
			sexe = arrResults[5];
		}
		System.out.print("Codi postal: ");
		String codiPostal = teclado.next();
		if(codiPostal.length() <= 0) {
			codiPostal = arrResults[6];
		}
		System.out.print("Població: ");
		String poblacio = teclado.next();
		if(poblacio.length() <= 0) {
			poblacio = arrResults[7];
		}

		System.out.println("Segur que vols actualitzar el alumne amb id = " + selected + "? (Y/N)");

		if(teclado.next().equalsIgnoreCase("y")) {
			selectStmt.execute("UPDATE alumnes "
					+ "SET nom = '"+nom+"', dni = '"+dni+"', dataNaixement = '"+dataNaixement+"', sexe = '"+sexe+"', codiPostal = '"+codiPostal+"', poblacio = '"+poblacio+"' "
					+ "WHERE id = "+selected+";");
			System.out.println("Alumne actualizat correctament");
		}

	}


	private static String[] selectOne(int selected, boolean show) throws SQLException {

		ResultSet rs = selectStmt.executeQuery("SELECT * FROM alumnes WHERE id = " + selected + ";");
		String[] arrResults = new String[8];

		if (show) {
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

				arrResults[0] = rs.getString(1);
				arrResults[1] = rs.getString(2);
				arrResults[2] = rs.getString(3);
				arrResults[3] = rs.getString(4);
				arrResults[4] = rs.getString(5);
				arrResults[5] = rs.getString(6);
				arrResults[6] = rs.getString(7);
				arrResults[7] = rs.getString(8);
			}
		}

		return arrResults;
	}

	private static void selectAll() throws SQLException {

		ResultSet rs = selectStmt.executeQuery("SELECT * FROM alumnes;");

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
