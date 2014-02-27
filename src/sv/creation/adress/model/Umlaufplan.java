package sv.creation.adress.model;

import java.util.ArrayList;

public class Umlaufplan {

	private int id;
	private String name;
	private String bezeichnung;
	private ArrayList<Block> umlauf = new ArrayList<Block>();
	private ArrayList<Blockelement> fahrtZuUmlauf = new ArrayList<Blockelement>();
	private int fahrplanID;
	private String date;
	private int dayID;

	/**
	 * @param umlaufplan
	 */
	public Umlaufplan(int id, String bezeichnung, ArrayList<Block> umlauf,
			ArrayList<Blockelement> fahrtZuUmlauf, int dayID, int fahrplanID,
			String name, String date) {
		super();
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.umlauf = umlauf;
		this.fahrtZuUmlauf = fahrtZuUmlauf;
		this.dayID = dayID;
		this.fahrplanID = fahrplanID;
		this.name = name;
		this.date = date;
	}

	// Getter- und setter-Methoden
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

	public int getFahrplanID() {
		return fahrplanID;
	}

	public void setFahrplanID(int fahrplanID) {
		this.fahrplanID = fahrplanID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getDayID() {
		return dayID;
	}

	public void setDayID(int dayID) {
		this.dayID = dayID;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

}
