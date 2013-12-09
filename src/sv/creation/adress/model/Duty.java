package sv.creation.adress.model;

public class Duty {
	
	private int id;
	private String type;
	
	/**
	 * @param id
	 * @param code
	 * @param name
	 */
	public Duty(int id, String type) {
		super();
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
	

}
