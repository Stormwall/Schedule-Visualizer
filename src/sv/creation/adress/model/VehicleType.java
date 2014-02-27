package sv.creation.adress.model;

public class VehicleType {

	private int ID;
	private int code;
	private int name;
	private int vehCost;
	private int kmCost;
	private int hourCost;
	private int capacity;
	private int fahrplanID;

	/**
	 * @param iD
	 * @param code
	 * @param name
	 * @param fahrzeugkosten
	 * @param kilometerkosten
	 * @param stundenkosten
	 * @param kapazit
	 *            ���t
	 */
	public VehicleType(int iD, int code, int name, int fahrzeugkosten,
			int kilometerkosten, int stundenkosten, int kapazitaet,
			int fahrplanID) {
		super();
		this.ID = iD;
		this.code = code;
		this.name = name;
		this.vehCost = fahrzeugkosten;
		this.kmCost = kilometerkosten;
		this.hourCost = stundenkosten;
		this.capacity = kapazitaet;
		this.fahrplanID = fahrplanID;
	}

	// Getter- und setter-Methoden
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

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public int getVehCost() {
		return vehCost;
	}

	public void setVehCost(int vehCost) {
		this.vehCost = vehCost;
	}

	public int getKmCost() {
		return kmCost;
	}

	public void setKmCost(int kmCost) {
		this.kmCost = kmCost;
	}

	public int getHourCost() {
		return hourCost;
	}

	public void setHourCost(int hourCost) {
		this.hourCost = hourCost;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getFahrplanID() {
		return fahrplanID;
	}

	public void setFahrplanID(int fahrplanID) {
		this.fahrplanID = fahrplanID;
	}

}
