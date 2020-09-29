package uf1.exercici02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class App {

	public static void main(String[] args) throws IOException {

		Cotxe cotxe = new Cotxe();
		
		File fitxer = new File("C:\\Users\\adidu\\git\\UF6\\DAM-M06\\src\\uf1\\exercici02\\cotxesObject.txt");
		FileOutputStream fileout = new FileOutputStream(fitxer);
		FileInputStream filein = new FileInputStream(fitxer);
		
		ObjectOutputStream dataOutCotxe = new ObjectOutputStream(fileout);
		ObjectInputStream dataInCotxe = new ObjectInputStream(filein);
		
		EscriureFitxerObject(cotxe, fileout, dataOutCotxe);
		LlegirFitxerObject(cotxe, fileout, dataInCotxe);
				
	}

	public static void EscriureFitxerObject(Cotxe cotxe, FileOutputStream fileout, ObjectOutputStream dataOutCotxe) throws IOException {
		String arrMarca[] = {"Opel", "Ferrari", "BMW"};
		String arrModel[] = {"Seat", "X3", "M3"};
		int arrAny[] = {2016, 2018, 2020};
		String arrMatricula[] = {"3859MXE", "8563KFY", "9865LOD"};

		for (int i=0; i < arrMarca.length; i++){
			cotxe = new Cotxe(arrMarca[i], arrModel[i], arrAny[i], arrMatricula[i]);
			dataOutCotxe.writeObject(cotxe);
		}
		dataOutCotxe.close();
	}
	
	public static void LlegirFitxerObject(Cotxe cotxe, FileOutputStream fileout, ObjectInputStream dataInCotxe) throws IOException {
		try {
			while(true) {
				cotxe = (Cotxe)dataInCotxe.readObject();
				System.out.println(cotxe.toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		dataInCotxe.close();
	}
}
