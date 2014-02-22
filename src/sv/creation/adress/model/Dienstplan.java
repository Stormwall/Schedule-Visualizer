package sv.creation.adress.model;

import java.util.ArrayList;

public class Dienstplan {

	private int id;
	private String name;
	private ArrayList<Duty> duty = new ArrayList<Duty>();
	private ArrayList<Dutyelement> dutyelement = new ArrayList<Dutyelement>();
	private int fahrplanID;
	private String date;
	private String bezeichnung;
	private int umlaufplanID;
	private int dayID;

	/**
	 * @param dienstplan
	 */
	public Dienstplan(int id,String bezeichnung, ArrayList<Duty> duty,
			ArrayList<Dutyelement> dutyelement, int fahrplanID, int umlaufplanID, String name, String date, int dayID) {
		// super();
		this.id = id;
		this.bezeichnung = bezeichnung;
		this.duty = duty;
		this.dutyelement = dutyelement;
		this.fahrplanID = fahrplanID;
		this.umlaufplanID = umlaufplanID;
		this.name = name;
		this.date = date;
		this.dayID = dayID;
		
	}

	public int getId() {
		return id;
	}

	public ArrayList<Duty> getDuty() {
		return duty;
	}

	public ArrayList<Dutyelement> getDutyelement() {
		return dutyelement;
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

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public int getDayID() {
		return dayID;
	}

	public void setDayID(int dayID) {
		this.dayID = dayID;
	}

	public int getUmlaufplanID() {
		return umlaufplanID;
	}

	public void setUmlaufplanID(int umlaufplanID) {
		this.umlaufplanID = umlaufplanID;
	}
	
	

}