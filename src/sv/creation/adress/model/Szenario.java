package sv.creation.adress.model;

import java.util.ArrayList;

public class Szenario {
	
	private int id;
	private ArrayList<PrimeDelay> primeDelay = new ArrayList<PrimeDelay>();
	private int fahrplanID;
	
	
	public Szenario(int id, ArrayList<PrimeDelay> primeDelay, int fahrplanID) {
		super();
		this.id = id;
		this.primeDelay = primeDelay;
		this.fahrplanID = fahrplanID;
	}
	
	//getter and setter methods
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<PrimeDelay> getPrimeDelay() {
		return primeDelay;
	}
	public void setPrimeDelay(ArrayList<PrimeDelay> primeDelay) {
		this.primeDelay = primeDelay;
	}
	public int getFahrplanID() {
		return fahrplanID;
	}
	public void setFahrplanID(int fahrplanID) {
		this.fahrplanID = fahrplanID;
	}
	


	
	

}
