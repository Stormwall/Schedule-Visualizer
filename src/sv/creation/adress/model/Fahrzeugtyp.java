package sv.creation.adress.model;

public class Fahrzeugtyp {

	private int ID;
	private int code;
	private String name;
	private int fahrzeugkosten;
	private int kilometerkosten;
	private int stundenkosten;
	private int kapazit�t;
	/**
	 * @param iD
	 * @param code
	 * @param name
	 * @param fahrzeugkosten
	 * @param kilometerkosten
	 * @param stundenkosten
	 * @param kapazit�t
	 */
	public Fahrzeugtyp(int iD, int code, String name, int fahrzeugkosten,
			int kilometerkosten, int stundenkosten, int kapazit�t) {
		super();
		ID = iD;
		this.code = code;
		this.name = name;
		this.fahrzeugkosten = fahrzeugkosten;
		this.kilometerkosten = kilometerkosten;
		this.stundenkosten = stundenkosten;
		this.kapazit�t = kapazit�t;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
	public int getFahrzeugkosten() {
		return fahrzeugkosten;
	}
	public void setFahrzeugkosten(int fahrzeugkosten) {
		this.fahrzeugkosten = fahrzeugkosten;
	}
	public int getKilometerkosten() {
		return kilometerkosten;
	}
	public void setKilometerkosten(int kilometerkosten) {
		this.kilometerkosten = kilometerkosten;
	}
	public int getStundenkosten() {
		return stundenkosten;
	}
	public void setStundenkosten(int stundenkosten) {
		this.stundenkosten = stundenkosten;
	}
	public int getKapazit�t() {
		return kapazit�t;
	}
	public void setKapazit�t(int kapazit�t) {
		this.kapazit�t = kapazit�t;
	}
	
	
}
