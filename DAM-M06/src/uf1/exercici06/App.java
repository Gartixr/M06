package uf1.exercici06;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class App {

	public static final String COVID_XML_FILE = "covid.xml";
	
	public static void main(String[] args) throws JAXBException, IOException  {
		// TODO Auto-generated method stub

		JAXBContext context = JAXBContext.newInstance(Covids.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		Covids covids = ompleCovids();
		
		marshaller.marshal(covids, System.out);
		
		FileOutputStream fos = new FileOutputStream(COVID_XML_FILE);
		marshaller.marshal(covids, fos);
		
		fos.close();
		
		Unmarshaller unmarshaller = context.createUnmarshaller();

		Covids covidsAux = (Covids) unmarshaller.unmarshal(new File(COVID_XML_FILE));
		System.out.println("********* Covids carregat desde fitxer XML***************");

		marshaller.marshal(covidsAux, System.out);
	}

	private static Covids ompleCovids() {
		
		String[] address = {"Addres0", "Addres1", "Addres2", "Address3"};
		int[] position = {0, 1, 2, 3};
		String[] uuid = {"uuid0", "uuid1", "uuid2", "uuid3"};
		String[] data = {"data0", "data1", "data2", "data3"};
		int[] casosDiaris = {0, 1, 2, 3};
		int[] defuncionsDiaris = {0, 1, 2, 3};
		int[] totalCasosDiaris = {0, 1, 2, 3};
		int[] totalDefuncionsDiaris = {0, 1, 2, 3};
		Covid[] arrayCovids = new Covid[4];
		
		
		for (int i = 0; i < arrayCovids.length; i++) {
			arrayCovids[i] = new Covid();
			arrayCovids[i].setAddress(address[i]);
			arrayCovids[i].setPosition(position[i]);
			arrayCovids[i].setUuid(uuid[i]);
			arrayCovids[i].setData(data[i]);
			arrayCovids[i].setCasosDiaris(casosDiaris[i]);
			arrayCovids[i].setDefuncionsDiaris(defuncionsDiaris[i]);
			arrayCovids[i].setTotalCasosDiaris(totalCasosDiaris[i]);
			arrayCovids[i].setTotalDefuncionsDiaris(totalDefuncionsDiaris[i]);
			arrayCovids[i].setId(i);
		}
		
		Covids covids = new Covids();
		covids.setCovids(arrayCovids);
		
		return covids;
	}

}
