/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uf1.exercici06;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
class Alumnes {

	private Alumne[] alumnes;


	public Alumne[] getAlumnes() {
		return alumnes;
	}
	public void setAlumnes(Alumne[] alumnes) {
		this.alumnes = alumnes;
	}

}
