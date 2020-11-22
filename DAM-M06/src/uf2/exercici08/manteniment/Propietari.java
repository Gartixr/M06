package uf2.exercici08.manteniment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Propietari implements Serializable{

	private static final long serialVersionUID = 1;

	@Id @GeneratedValue
	private long id;

	private Date dataPeticio;
	private String nomPropietari;
	private int taller;
	private boolean premium;

	public Propietari(){

	}

	public Propietari(Date dataPeticio, String nomPropietari, int taller, boolean premium){
		this.dataPeticio = dataPeticio;
		this.nomPropietari = nomPropietari;
		this.taller = taller;
		this.premium = premium;
	}

	public long getId() {
		return id;
	}

	public Date getDataPeticio() {
		return dataPeticio;
	}

	public String getNomPropietari() {
		return nomPropietari;
	}

	public int getTaller() {
		return taller;
	}

	public boolean isPremium() {
		return premium;
	}

	@Override
	public String toString() {
		return "Propietari [id=" + id + ", dataPeticio=" + dataPeticio + ", nomPropietari=" + nomPropietari
				+ ", taller=" + taller + ", premium=" + premium + "]";
	}

}
