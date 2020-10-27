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

public class AppCovid {

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
			if (child.item(i).getNodeName().equals("row") && child.item(i).hasAttributes()) {
				atributs = child.item(i).getAttributes();
				for (int j = 0; j < atributs.getLength(); j++) {
					if (atributs.item(j).getNodeName().equals("_id")) {
						String numId = atributs.item(j).getNodeValue();
						((Element) child.item(i)).setIdAttribute("_id", true);
					}
				}
			}
		}

		boolean menu = true;
		while (menu) {
			System.out.println("[1] - Crear Row\n"
					+ "[2] - Modificar Row\n"
					+ "[3] - Eliminar Row\n"
					+ "[4] - Veure Info\n"
					+ "[5] - Guardar XML\n"
					+ "[6] - Salir");
			int input = teclado.nextInt();

			switch (input) {
			// Crear
			case 1:
				crearRow(nodeArrel, doc);
				guardado = false;
				break;
				// Eliminar
			case 2:
				modificarRow(doc);
				guardado = false;
				break;
				// Modificar
			case 3:
				eliminarRow(doc, nodeArrel);
				guardado = false;
				break;

				// Guardar xml
			case 5:
				saveXML(doc);
				guardado = true;
				break;
				// Sortir
			case 6:
				if(!guardado) {
					System.out.println("Tienes cambios sin guardar. Deseas salir?\n[1] - Si\n[2] - No");
					int salir = teclado.nextInt();
					
					if(salir == 2) {
						menu = true;
						break;
					}else {
						menu = false;
						break;
					}
				}else {
					menu = false;
					break;
				}
			case 4:
				veureInfo(nodeArrel);
				break;
			}
		}
	}

	private static void veureInfo(Node nodeArrel) {		
		NodeList nodeList = nodeArrel.getChildNodes();
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			
			if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				if(nodeList.item(i).hasAttributes()) {
					System.out.println("Row: " + nodeList.item(i).getAttributes().item(0));
				}else {
					System.out.println("\t" + nodeList.item(i).getNodeName() + ": " + nodeList.item(i).getTextContent());
				}
				if(nodeList.item(i).hasChildNodes()) {
					veureInfo(nodeList.item(i));
				}
			}
		}
	}

	private static void eliminarRow(Document doc, Node nodeArrel) {
		// TODO Auto-generated method stub
		System.out.println("ID:");
		String id = teclado.next();
		Element element = doc.getElementById(id);
		nodeArrel.removeChild(element);
	}

	private static void modificarRow(Document doc) {
		// TODO Auto-generated method stub

		// Pedir id alumno
		System.out.println("ID:");
		String id = teclado.next();
		Element element = doc.getElementById(id);
		
		
		System.out.println("[1] - Afegir node\n"
				+ "[2] - Modificar node\n"
				+ "[3] - Eliminar node\n"
				+ "[4] - Sortir");
		
		int input = teclado.nextInt();
		
		switch (input) {
		case 1:
			afegirNode(id, doc);
			break;
		case 2:
			modificarNode(id, doc);
			break;
		case 3:
			eliminarNode(id, doc);
			break;

		default:
			break;
		}
		
	}

	private static void eliminarNode(String id, Document doc) {
		// TODO Auto-generated method stub
		System.out.println("Nom node a eliminar");
		String nodeElim = teclado.next();
		
		NodeList selected = doc.getElementById(id).getChildNodes();
		
		for (int i = 0; i < selected.getLength(); i++) {
			if(selected.item(i).getNodeName().equalsIgnoreCase(nodeElim)) {
				((Node) selected).removeChild(selected.item(i));
			}
		}
	}

	private static void modificarNode(String id, Document doc) {
		// TODO Auto-generated method stub
		System.out.println("Nom node a modificar:");
		String nodeMod = teclado.next();
		
		System.out.println("Nou valor:");
		String valor = teclado.next();
		
		NodeList selected = doc.getElementById(id).getChildNodes();
		
		for (int i = 0; i < selected.getLength(); i++) {
			if(selected.item(i).getNodeName().equalsIgnoreCase(nodeMod)) {
				selected.item(i).setTextContent(valor);;
			}
		}
	}

	private static void afegirNode(String id, Document doc) {
		// TODO Auto-generated method stub
		
		Element nouNode;
		Element selected = doc.getElementById(id);
		
		System.out.println("Nom node:");
		String nomNode = teclado.next();
		System.out.println("Valor:");
		String valorNode = teclado.next();
		
		nouNode = doc.createElement(nomNode);
		nouNode.appendChild(doc.createTextNode(valorNode));
		selected.appendChild(nouNode);
		
	}

	private static void crearRow(Node nodeArrel, Document doc) throws FileNotFoundException, TransformerFactoryConfigurationError, TransformerException {
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
