/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uf1.exercici06;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
 
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
 
public class JAXBSerializationExample2 {
	
	private static final String ALUMNES_XML_FILE = "alumnes.xml";
 
	public static void main(String[] args) throws JAXBException, IOException {
		
		JAXBContext context = JAXBContext.newInstance(Alumnes.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		Alumnes alumnes = ompleAlumnes();
		
		//Mostrem el document XML generat por la sortida estandard
		marshaller.marshal(alumnes, System.out);
		
		FileOutputStream fos = new FileOutputStream(ALUMNES_XML_FILE);
		//guardem l'objecte serializat en un document XML
		marshaller.marshal(alumnes, fos);
		fos.close();
		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		//Deserialitzem a partir de un document XML
		Alumnes alumnesAux = (Alumnes) unmarshaller.unmarshal(new File(ALUMNES_XML_FILE));
		System.out.println("********* Alumnes carregat desde fitxer XML***************");
		//Mostrem l'objeto Java obtingut
		marshaller.marshal(alumnesAux, System.out);
		
	}
	
	private static Alumnes ompleAlumnes(){
		
		String[] nomsAlumnes = {"Alex", "Nuria", "Fede"};
                String[] cognoms1Alumnes = {"Grange", "Grange", "Grange"};
                String[] cognoms2Alumnes = {"Borras", "Borras", "Borras"};
		int[] notesAlumnes = {5, 10, 4};
		Alumne[] ArrayAlumnes = new Alumne[3];
		
		for(int i=0; i<3; i++){
			ArrayAlumnes[i] = new Alumne();
                        ArrayAlumnes[i].setNom(nomsAlumnes[i]);
                        ArrayAlumnes[i].setCognom1(cognoms1Alumnes[i]);
                        ArrayAlumnes[i].setCognom2(cognoms2Alumnes[i]);
                        ArrayAlumnes[i].setNotaFinal(notesAlumnes[i]);
			ArrayAlumnes[i].setId(i);			
		}
		
		Alumnes alumnes = new Alumnes();
		alumnes.setAlumnes(ArrayAlumnes);
		
		return alumnes;
	}
 
}