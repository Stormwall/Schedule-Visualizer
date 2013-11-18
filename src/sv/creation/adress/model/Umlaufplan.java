package sv.creation.adress.model;

import java.util.ArrayList;

public class Umlaufplan {
	
	private ArrayList<Umlaufplanteil> umlaufplan = new ArrayList<Umlaufplanteil>();

	/**
	 * @param umlaufplan
	 */
	public Umlaufplan(ArrayList<Umlaufplanteil> umlaufplan) {
		super();
		this.umlaufplan = umlaufplan;
	}

	
	public ArrayList<Umlaufplanteil> getUmlaufplan() {
		return umlaufplan;
	}

	public void setUmlaufplan(ArrayList<Umlaufplanteil> umlaufplan) {
		this.umlaufplan = umlaufplan;
	}
	
	

}
