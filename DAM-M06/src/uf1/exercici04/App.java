package uf1.exercici04;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class App {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub

		File file = new File("C:\\Users\\adidu\\git\\UF6\\DAM-M06\\src\\uf1\\exercici04\\covid.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);

		Node nodeArrel = doc.getDocumentElement();

		pillarDatos(nodeArrel);

	}

	public static void pillarDatos(Node elements) {

		Node elemento = elements;

		NamedNodeMap atributos = elemento.getAttributes();
		int rowsLength = elemento.getChildNodes().getLength();

		if (elemento.getNodeName() != null) {
			System.out.println("Elemento: " + elemento.getNodeName());
		}

//		if(atributos != null) {
			System.out.println("------Atributos: ");
			for (int i = 0; i < atributos.getLength(); i++) {
				System.out.println("------------" + atributos.item(i));
			}
			System.out.println("\n");
//		}

		for (int i = 0; i < rowsLength; i++) {

			if(elemento.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
				pillarDatos(elemento.getChildNodes().item(i));
			}

		}

	}

}
