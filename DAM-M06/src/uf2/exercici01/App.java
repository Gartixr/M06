package uf2.exercici01;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner teclado =  new Scanner(System.in);
		Connection con = null; 
		Driver driver = null;
		String url = "jdbc:mysql://10.32.24.210:3306";
		String usuari = "remote"; 
		String password = ">9d&pfLYdx.(";
		boolean menu = true;

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
		
		
		while (menu) {
			
			System.out.println("[1] - Select all\n"
					+ "[2] - Select one"
					+ "[3] - Actualizar\n"
					+ "[4] - Insertar\n"
					+ "[5] - Eliminar+"
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
			case 6:
				System.out.println("Desconectado.");
				menu = false;
				break;

			default:
				System.out.println("Desconectado.");
				menu = false;
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

	private static void selectOne(int selected) {
		// TODO Auto-generated method stub
		
	}

	private static void selectAll() {
		// TODO Auto-generated method stub
		
	}
}
