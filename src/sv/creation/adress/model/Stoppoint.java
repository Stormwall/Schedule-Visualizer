package sv.creation.adress.model;

public class Stoppoint {

	private int id;
	private int code;
	private String name;
	private int fahrplanID;

	public Stoppoint(int id, int code, String name, int fahrplanID) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.fahrplanID = fahrplanID;
	}

	// Getter- und setter-Methoden
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

	public int getFahrplanID() {
		return fahrplanID;
	}

	public void setFahrplanID(int fahrplanID) {
		this.fahrplanID = fahrplanID;
	}

}
