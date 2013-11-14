package sv.creation.adress.model;

public class Linie {
	
	private int id;
	private int code;
	private String name;
	
	/**
	 * @param id
	 * @param code
	 * @param name
	 */
	public Linie(int id, int code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
