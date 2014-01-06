package sv.creation.adress.model;
public class Blockelement {
	
	private int id;
	private int blockID;
	private String serviceJourneyID;
	private int fromStopID;
	private int toStopID;
	private String depTime;
	private String arrTime;
	private int elementType;
	private String driveTime;
	private int umlaufplanID;
	
	
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
	public Blockelement(int id, int blockID, String serviceJourneyID, int fromStopID, int toStopID,
			String depTime, String arrTime, int elementType, int umlaufplanID) {
		super();
		this.id = id;
		this.blockID = blockID;
		this.serviceJourneyID=serviceJourneyID;
		this.fromStopID = fromStopID;
		this.toStopID = toStopID;
		this.depTime = depTime;
		this.arrTime = arrTime;
		this.elementType = elementType;
		this.umlaufplanID=umlaufplanID;
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
	public String getDriveTime() {
		return driveTime;
	}
	public void setDriveTime(String driveTime) {
		this.driveTime = driveTime;
	}
	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}
	public void setArrTime(String arrTime) {
		this.arrTime = arrTime;
	}
	public int getUmlaufplanID() {
		return umlaufplanID;
	}
	
}