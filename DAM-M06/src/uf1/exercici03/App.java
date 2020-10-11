package uf1.exercici03;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class App {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		File fitxer = new File("C:\\Users\\adidu\\git\\UF6\\DAM-M06\\src\\uf1\\exercici03\\personas");

		RandomAccessFile aleatoriFile = new RandomAccessFile(fitxer, "rw");

		String arrNoms[] = {"Adrian", "Roberto", "Laura"};
		String arrCognoms[] = {"Dumi", "Fernandez", "Bosque"};
		int arrTel[] = {680975738, 643842950, 612492045};
		String arrDNI[] = {"8191518E", "3859648B", "9574895A"};
		String arrDireccio[] = {"Prat de la Riba", "Joan Ferran", "Carrer Lleo"};
		
		StringBuffer buffer = null;

		for (int i=0; i<arrNoms.length; i++) {
			aleatoriFile.writeInt(i+1);
	
			buffer = new StringBuffer(arrNoms[i]);
			buffer.setLength(50);
			aleatoriFile.writeChars(buffer.toString());
			
			buffer = new StringBuffer(arrCognoms[i]);
			buffer.setLength(50);
			aleatoriFile.writeChars(buffer.toString());
			
			aleatoriFile.writeInt(arrTel[i]);
			
			buffer = new StringBuffer(arrDNI[i]);
			buffer.setLength(9);
			aleatoriFile.writeChars(buffer.toString());
			
			buffer = new StringBuffer(arrDireccio[i]);
			buffer.setLength(200);
			aleatoriFile.writeChars(buffer.toString());
		}
		
		aleatoriFile.close();
				
		aleatoriFile = new RandomAccessFile(fitxer, "r");
		int apuntador = 0;
		char nom[] = new char[50];
		char cognom[] = new char[50];
		int telefon;
		char dni[] = new char[9];
		char direccio[] = new char[200];
		
		char aux;
		
		for (;;) {
			aleatoriFile.seek(apuntador);

			for(int i = 0; i<nom.length; i++) {
				aux = aleatoriFile.readChar();
				nom[i] = aux;
			}
			String noms = new String(nom);
			
			for(int i = 0; i<cognom.length; i++) {
				aux = aleatoriFile.readChar();
				cognom[i] = aux;
			}
			String cognoms = new String(cognom);
			
			telefon = aleatoriFile.readInt();
			
			for(int i = 0; i<dni.length; i++) {
				aux = aleatoriFile.readChar();
				dni[i] = aux;
			}
			String dnis = new String(dni);
			
			for(int i = 0; i<direccio.length; i++) {
				aux = aleatoriFile.readChar();
				direccio[i] = aux;
			}
			String direccions = new String(direccio);
			
			System.out.println(noms + cognoms + telefon + dnis + direccions);

			apuntador += 100+100+4+18+400+4;
			
			if(aleatoriFile.getFilePointer()==aleatoriFile.length()) break;
		}
		aleatoriFile.close();
		
	}
}
