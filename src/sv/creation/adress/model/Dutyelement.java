package sv.creation.adress.model;

public class Dutyelement {
	private int id;
	private String dutyID;
	private int blockID;
	private String serviceJourneyID;
	private int fromStopID;
	private int toStopID;
	private String depTime;
	private String arrTime;
	private int elementType;
	private String elementTypeName;
	// private String servicejourneycode;
	private int dienstplanID;
	private String driveTime;
	private int dutyHilfsID;

	/**
	 * @param id
	 * @param linienID
	 * @param fromStopID
	 * @param toStopID
	 * @param depTime
	 * @param arrivalTime
	 * @param minAheadTime
	 * @param minLayoverTime
	 * @param fahrzeugtypGruppenID
	 * @param maxShiftBackwardSeconds
	 * @param maxShiftForwardSeconds
	 * @param fromStopBreakFacility
	 * @param toStopBreakFacility
	 * @param code
	 */
	public Dutyelement(int id, String dutyID, int blockID,
			String serviceJourneyID, int fromStopID, int toStopID,
			String depTime, String arrTime, int elementType, String elementTypeName, int dienstplanID,
			int dutyHilfsID) {
		super();
		this.id = id;
		this.dutyID = dutyID;
		this.blockID = blockID;
		this.serviceJourneyID = serviceJourneyID;
		this.fromStopID = fromStopID;
		this.toStopID = toStopID;
		this.depTime = depTime;
		this.arrTime = arrTime;
		this.elementType = elementType;
		this.elementTypeName = elementTypeName;
		// this.servicejourneycode = servicejourneycode;
		this.dienstplanID = dienstplanID;
		this.dutyHilfsID = dutyHilfsID;
	}

	public int getId() {
		return id;
	}

	public int getBlockID() {
		return blockID;
	}

	public String getServiceJourneyID() {
		return serviceJourneyID;
	}

	public int getFromStopID() {
		return fromStopID;
	}

	public int getToStopID() {
		return toStopID;
	}

	public String getDepTime() {
		return depTime;
	}

	public String getArrTime() {
		return arrTime;
	}

	public int getElementType() {
		return elementType;
	}

	public String getElementTypeName() {
		return elementTypeName;
	}

	public String getDutyID() {
		return dutyID;
	}

	public int getDienstplanID() {
		return dienstplanID;
	}

	public String getDriveTime() {
		return driveTime;
	}

	public void setDriveTime(String driveTime) {
		this.driveTime = driveTime;
	}

	public int getDutyHilfsID() {
		return dutyHilfsID;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}

	// public String getServicejourneycode() {
	// return servicejourneycode;
	// }

}