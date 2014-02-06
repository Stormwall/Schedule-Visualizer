package sv.creation.adress.model;

import java.util.ArrayList;

public class Dienstplan {

	private int id;
	private String name;
	private ArrayList<Duty> duty = new ArrayList<Duty>();
	private ArrayList<Dutyelement> dutyelement = new ArrayList<Dutyelement>();
	private int fahrplanID;
	private String date;

	/**
	 * @param dienstplan
	 */
	public Dienstplan(int id, ArrayList<Duty> duty,
			ArrayList<Dutyelement> dutyelement, int fahrplanID, String date) {
		// super();
		this.id = id;
		this.duty = duty;
		this.dutyelement = dutyelement;
		this.fahrplanID = fahrplanID;
		this.date = date;
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
	
	

}