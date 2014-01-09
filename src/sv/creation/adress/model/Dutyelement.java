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
//	private String servicejourneycode;
	private int dienstplanID;
	
	
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
	public Dutyelement(int id, String dutyID, int blockID, String serviceJourneyID, int fromStopID, int toStopID,
			String depTime, String arrTime, int elementType, int dienstplanID) {
		super();
		this.id = id;
		this.dutyID = dutyID;
		this.blockID = blockID;
		this.serviceJourneyID=serviceJourneyID;
		this.fromStopID = fromStopID;
		this.toStopID = toStopID;
		this.depTime = depTime;
		this.arrTime = arrTime;
		this.elementType = elementType;
//		this.servicejourneycode = servicejourneycode;
		this.dienstplanID = dienstplanID;
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


	public String getDutyID() {
		return dutyID;
	}


	public int getDienstplanID() {
		return dienstplanID;
	}


//	public String getServicejourneycode() {
//		return servicejourneycode;
//	}
	
	


}
