package sv.creation.adress.model;

import java.util.ArrayList;

public class Umlaufplan {
	
	private int id;
	private String name;
	private ArrayList<Block> umlauf = new ArrayList<Block>();
	private ArrayList<Blockelement> fahrtZuUmlauf = new ArrayList<Blockelement>();

	/**
	 * @param umlaufplan
	 */
	public Umlaufplan(int id,ArrayList<Block> umlauf, ArrayList<Blockelement> fahrtZuUmlauf) {
		// super();
		this.id=id;
		this.umlauf = umlauf;
		this.fahrtZuUmlauf=fahrtZuUmlauf;
	}

	public int getId() {
		return id;
	}

	public ArrayList<Block> getUmlauf() {
		return umlauf;
	}

	public ArrayList<Blockelement> getFahrtZuUmlauf() {
		return fahrtZuUmlauf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	

	
	

}
