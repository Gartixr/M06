/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uf1.exercici06;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement
@XmlRootElement(name = "Alumne")

//@XmlAccessorType(XmlAccessType.FIELD) // This line was added
class Alumne {

	private int id;
	private int notaFinal;	
	private String nom;
	private String cognom1;
	private String cognom2;

	@XmlAttribute(name="id")        
	public int getId() {
		return this.id;
	}        

	public void setId(int id) {
		this.id = id;
	}

	//@XmlAttribute(name="Nom")    
	public String getNom() {
		return nom;
	}        

	public void setNom(String nom) {
		this.nom = nom;
	}

	//@XmlAttribute(name="Cognom1")    
	public String getCognom1() {
		return cognom1;
	}
	public void setCognom1(String Cognom1) {
		this.cognom1 = Cognom1;
	}        

	//@XmlAttribute(name="Cognom2")    
	public String getCognom2() {
		return cognom2;
	}
	public void setCognom2(String Cognom2) {
		this.cognom2 = Cognom2;
	}        

	//@XmlAttribute(name="NotaFinal")    
	public int getNotaFinal() {
		return this.notaFinal;
	}
	public void setNotaFinal(int notaFinal) {
		this.notaFinal = notaFinal;
	}

}
