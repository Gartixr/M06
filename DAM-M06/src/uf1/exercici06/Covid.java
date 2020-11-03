package uf1.exercici06;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Covid")
public class Covid {

	private int id;
	private String address;
	private int position;
	private String uuid;
	private String data;
	private int casosDiaris;
	private int defuncionsDiaris;
	private int totalCasosDiaris;
	private int totalDefuncionsDiaris;
	
	@XmlAttribute(name="_id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@XmlAttribute(name="_address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@XmlAttribute(name="_position")
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	@XmlAttribute(name="_uuid")
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public int getCasosDiaris() {
		return casosDiaris;
	}
	public void setCasosDiaris(int casosDiaris) {
		this.casosDiaris = casosDiaris;
	}
	
	public int getDefuncionsDiaris() {
		return defuncionsDiaris;
	}
	public void setDefuncionsDiaris(int defuncionsDiaris) {
		defuncionsDiaris = defuncionsDiaris;
	}
	
	public int getTotalCasosDiaris() {
		return totalCasosDiaris;
	}
	public void setTotalCasosDiaris(int totalCasosDiaris) {
		this.totalCasosDiaris = totalCasosDiaris;
	}
	
	public int getTotalDefuncionsDiaris() {
		return totalDefuncionsDiaris;
	}
	public void setTotalDefuncionsDiaris(int totalDefuncionsDiaris) {
		this.totalDefuncionsDiaris = totalDefuncionsDiaris;
	}
}
