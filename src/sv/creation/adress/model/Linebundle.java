package sv.creation.adress.model;

public class Linebundle {
	
	private int id;
	private int linebundleID;
	private int lineID;
	private int fahrplanID;
	
	//Konstruktor
	public Linebundle(int id, int linebundleID, int lineID, int fahrplanID) {
		super();
		this.id = id;
		this.linebundleID = linebundleID;
		this.lineID = lineID;
		this.fahrplanID = fahrplanID;
	}

	//Getter und setter Methoden
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getLinebundleID() {
		return linebundleID;
	}


	public void setLinebundleID(int linebundleID) {
		this.linebundleID = linebundleID;
	}


	public int getLineID() {
		return lineID;
	}


	public void setLineID(int lineID) {
		this.lineID = lineID;
	}


	public int getFahrplanID() {
		return fahrplanID;
	}


	public void setFahrplanID(int fahrplanID) {
		this.fahrplanID = fahrplanID;
	}
	
	
	
	

}
