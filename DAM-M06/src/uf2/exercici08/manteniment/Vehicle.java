package uf2.exercici08.manteniment;

import java.io.Serializable;
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
	
	public Vehicle(Date dataImportacio, String nomModel, double preu, boolean arreglat){
		this.dataImportacio = dataImportacio;
		this.nomModel = nomModel;
		this.preu = preu;
		this.arreglat = arreglat;
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
