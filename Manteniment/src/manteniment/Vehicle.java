package manteniment;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vehicle implements Serializable{

	private static final long serialVersionUID = 1;
	
	@Id @GeneratedValue
	private long id;
	
	private Date dataImportacio;
	private String nomModel;
	private double preu;
	private boolean arreglat;
	
	public Vehicle(){
		
	}
	
	public Vehicle(String dataImportacio, String nomModel, String preu, String arreglat) throws ParseException{

		this.dataImportacio = new SimpleDateFormat("dd/MM/yyyy").parse(dataImportacio);
		this.nomModel = nomModel;
		this.preu = Double.parseDouble(preu);
		this.arreglat = Boolean.parseBoolean(arreglat);
	}

	public long getId() {
		return id;
	}

	public Date getDataImportacio() {
		return dataImportacio;
	}

	public String getNomModel() {
		return nomModel;
	}

	public double getPreu() {
		return preu;
	}

	public boolean isArreglat() {
		return arreglat;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", dataImportacio=" + dataImportacio + ", nomModel=" + nomModel + ", preu=" + preu
				+ ", arreglat=" + arreglat + "]";
	}
	
}

