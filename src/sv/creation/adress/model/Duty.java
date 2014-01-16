package sv.creation.adress.model;

public class Duty {
	
	private int id;
	private int hilfsID;
	private String type;
	
	/**
	 * @param id
	 * @param code
	 * @param name
	 */
	public Duty(int hilfsID,int id, String type) {
		super();
		this.hilfsID = hilfsID;
		this.id = id;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getHilfsID() {
		return hilfsID;
	}

	public void setHilfsID(int hilfsID) {
		this.hilfsID = hilfsID;
	}
	

}
