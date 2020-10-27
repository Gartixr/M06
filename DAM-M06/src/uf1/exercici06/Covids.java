package uf1.exercici06;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Covids {

	private Covid[] covids;
	
	public Covid[] getCovids() {
		return covids;
	}
	
	public void setCovids(Covid[] covids) {
		this.covids = covids;
	}
	
}
