package sv.creation.adress.model;

import java.util.ArrayList;

public class Szenario {

	private int id;
	private ArrayList<PrimeDelay> primeDelay = new ArrayList<PrimeDelay>();
	private String bezeichnung;
	private int fahrplanID;
	private String date;

	public Szenario(int id, ArrayList<PrimeDelay> primeDelay, int fahrplanID,
			String bezeichnung, String date) {
		super();
		this.id = id;
		this.primeDelay = primeDelay;
		this.fahrplanID = fahrplanID;
		this.bezeichnung = bezeichnung;
		this.date = date;
	}

	// getter and setter methods
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

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
