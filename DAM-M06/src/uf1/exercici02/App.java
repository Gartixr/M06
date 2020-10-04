package uf1.exercici02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Scanner teclado = new Scanner(System.in);
		boolean activo = true;
		
		Cotxe cotxe = new Cotxe();
		
		File fitxer = new File("C:\\Users\\adidu\\git\\UF6\\DAM-M06\\src\\uf1\\exercici02\\cotxesObject.txt");
		FileOutputStream fileout = new FileOutputStream(fitxer);
		FileInputStream filein = new FileInputStream(fitxer);
		
		ObjectOutputStream dataOutCotxe = new ObjectOutputStream(fileout);
		ObjectInputStream dataInCotxe = new ObjectInputStream(filein);
		
		EscriureFitxerObject(cotxe, dataOutCotxe);
		
		while (activo) {
			
			System.out.println("\n\n[1] - Llegir Fitxer\n[2] - Afegir Cotxe\n[3] - Recuperar Cotxe\n[0 - Salir]");
			
			int input = teclado.nextInt();
			
			if(input == 1) {
				LlegirFitxerObject(cotxe, dataInCotxe, filein, fitxer);
			}else if(input == 2) {
				AfegirObject(dataOutCotxe, teclado);
			}else if(input == 3) {
				RecuperarObject(cotxe, dataInCotxe, teclado, filein, fitxer);
			}else if(input == 0) {
				activo = false;
			}
		}
		dataOutCotxe.close();
		dataInCotxe.close();
	}

	public static void EscriureFitxerObject(Cotxe cotxe, ObjectOutputStream dataOutCotxe) throws IOException {
		String arrMarca[] = {"Opel", "Ferrari", "BMW"};
		String arrModel[] = {"Seat", "X3", "M3"};
		int arrAny[] = {2016, 2018, 2020};
		String arrMatricula[] = {"3859MXE", "8563KFY", "9865LOD"};

		for (int i=0; i < arrMarca.length; i++){
			cotxe = new Cotxe(arrMarca[i], arrModel[i], arrAny[i], arrMatricula[i]);
			dataOutCotxe.writeObject(cotxe);
		}
	}
	
	public static void LlegirFitxerObject(Cotxe cotxe, ObjectInputStream dataInCotxe, FileInputStream filein, File fitxer) throws IOException {
		filein = new FileInputStream(fitxer);
		dataInCotxe = new ObjectInputStream(filein);
		
		try {
			while(true) {
				cotxe = (Cotxe)dataInCotxe.readObject();
				System.out.println(cotxe.toString());
			}
		} catch (Exception e) {
			dataInCotxe.close();
			filein.close();
		}
	}
	
	public static void AfegirObject(ObjectOutputStream dataOutCotxe, Scanner teclado) throws IOException {
		Cotxe cotxe = new Cotxe();
		teclado.nextLine();
		System.out.println("Marca: ");
		cotxe.setMarca(teclado.nextLine());
		System.out.println("Model: ");
		cotxe.setModel(teclado.nextLine());
		System.out.println("Any: ");
		cotxe.setAny(teclado.nextInt());
		teclado.nextLine();
		System.out.println("Matricula: ");
		cotxe.setMatricula(teclado.nextLine());
		
		dataOutCotxe.writeObject(cotxe);
	}
	
	public static void RecuperarObject(Cotxe cotxe, ObjectInputStream dataInCotxe, Scanner teclado, FileInputStream filein, File fitxer) throws ClassNotFoundException, IOException {
		teclado.nextLine();
		
		filein = new FileInputStream(fitxer);
		dataInCotxe = new ObjectInputStream(filein);
		
		System.out.print("Valor: ");
		String valor = teclado.nextLine();
		
		try {
			while(true) {
				cotxe = (Cotxe)dataInCotxe.readObject();
				
				if(cotxe.checkExistencia(valor)) {
					System.out.println(cotxe.toString());
				}
				
			}
		} catch (Exception e) {

			dataInCotxe.close();
			filein.close();
		}
	}
}
