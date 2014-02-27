package sv.creation.adress.model;

public class VehicleTypeToVehicleTypeGroup {

	private int vehTypeID;
	private int vehTypeGroupID;
	private int fahrplanID;

	public VehicleTypeToVehicleTypeGroup(int vehTypeID, int vehTypeGroupID,
			int fahrplanID) {
		super();
		this.vehTypeID = vehTypeID;
		this.vehTypeGroupID = vehTypeGroupID;
		this.fahrplanID = fahrplanID;
	}

	// Getter- und setter-Methoden
	public int getVehTypeID() {
		return vehTypeID;
	}

	public void setVehTypeID(int vehTypeID) {
		this.vehTypeID = vehTypeID;
	}

	public int getVehTypeGroupID() {
		return vehTypeGroupID;
	}

	public void setVehTypeGroupID(int vehTypeGroupID) {
		this.vehTypeGroupID = vehTypeGroupID;
	}

	public int getFahrplanID() {
		return fahrplanID;
	}

	public void setFahrplanID(int fahrplanID) {
		this.fahrplanID = fahrplanID;
	}

}
