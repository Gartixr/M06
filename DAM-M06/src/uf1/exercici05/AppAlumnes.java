package uf1.exercici05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AppAlumnes {

	static Scanner teclado = new Scanner(System.in);
	static boolean guardado = true;

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		// TODO Auto-generated method stub

		File file = new File("C:\\Users\\adidu\\git\\UF6\\DAM-M06\\src\\uf1\\exercici05\\alumnes.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);

		Node nodeArrel = doc.getDocumentElement();

		NodeList child = nodeArrel.getChildNodes();
		NamedNodeMap atributs;
		for (int i = 0; i < child.getLength(); i++) {
			if (child.item(i).getNodeName().equals("alumne") && child.item(i).hasAttributes()) {
				atributs = child.item(i).getAttributes();
				for (int j = 0; j < atributs.getLength(); j++) {
					if (atributs.item(j).getNodeName().equals("id")) {
						String numId = atributs.item(j).getNodeValue();
						System.out.println(numId);
						((Element) child.item(i)).setIdAttribute("id", true);
					}
				}
			}
		}

		System.out.println("[1] - Crear Alumne\n"
				+ "[2] - Modificar Alumne\n"
				+ "[3] - Eliminar Alumne\n"
				+ "[4] - Guardar xml\n"
				+ "[5] - Salir");

		int input = teclado.nextInt();

		switch (input) {
		// Crear
		case 1:
			crearAlumne(nodeArrel, doc);
			guardado = false;
			break;
			// Eliminar
		case 2:
			modificarAlumne();
			guardado = false;
			break;
			// Modificar
		case 3:
			eliminarAlumne(doc);
			guardado = false;
			break;

			// Guardar xml
		case 4:
			saveXML(doc);
			guardado = true;

			// Sortir
		default:
			if(!guardado) {
				System.out.println("Tienes cambios sin guardar. Deseas salir?");
			}
			break;
		}

	}

	private static void eliminarAlumne(Document doc) {
		// TODO Auto-generated method stub
		System.out.println("ID:");
		String id = teclado.next();
		Element element = doc.getElementById(id);
		System.out.println(element.getNodeName());
		doc.removeChild(element);
	}

	private static void modificarAlumne() {
		// TODO Auto-generated method stub

	}

	private static void crearAlumne(Node nodeArrel, Document doc) throws FileNotFoundException, TransformerFactoryConfigurationError, TransformerException {
		// TODO Auto-generated method stub

		Element nodeToAdd;

		// Pillar la id del ultimo hijo de alumnes
		//String idUltimoHijo = nodeArrel.getChildNodes().item(nodeArrel.getChildNodes().getLength()-2).getAttributes().item(0).getNodeValue();

		int ultimoId = getUltimoId(nodeArrel);

		int id = ultimoId + 1;
		System.out.println("Nom:");
		String nom = teclado.next();
		System.out.println("Cognom:");
		String cognom1 = teclado.next();
		System.out.println("Cognom2:");
		String cognom2 = teclado.next();
		System.out.println("Nota final:");
		String notaFinal = teclado.next();

		nodeToAdd = doc.createElement("alumne");
		nodeToAdd.setAttribute("id", String.valueOf(id));
		nodeToAdd.setIdAttribute("id", true);

		Element elemNom = doc.createElement("nom");
		Element elemCognom1 = doc.createElement("cognom1");
		Element elemCognom2 = doc.createElement("cognom2");
		Element elemNotaFinal = doc.createElement("notaFinal");

		elemNom.appendChild(doc.createTextNode(nom));
		elemCognom1.appendChild(doc.createTextNode(cognom1));
		elemCognom2.appendChild(doc.createTextNode(cognom2));
		elemNotaFinal.appendChild(doc.createTextNode(notaFinal));	

		nodeToAdd.appendChild(elemNom);
		nodeToAdd.appendChild(elemCognom1);
		nodeToAdd.appendChild(elemCognom2);
		nodeToAdd.appendChild(elemNotaFinal);

		nodeArrel.appendChild(nodeToAdd);

	}

	private static int getUltimoId(Node nodeArrel) {

		NodeList hijos = nodeArrel.getChildNodes();
		String id = "0";

		for (int i = 0; i < hijos.getLength(); i++) {

			if(hijos.item(i).getNodeType() == Node.ELEMENT_NODE) {
				id = hijos.item(i).getAttributes().item(0).getNodeValue();
			}

		}

		return Integer.parseInt(id);
	}

	private static void saveXML(Document doc) throws TransformerFactoryConfigurationError, FileNotFoundException, TransformerException {
		// TODO Auto-generated method stub
		Transformer tr = TransformerFactory.newInstance().newTransformer();
		tr.transform(new DOMSource(doc), new StreamResult(new FileOutputStream("C:\\Users\\adidu\\git\\UF6\\DAM-M06\\src\\uf1\\exercici05\\alumnes.xml")));
	}

}
