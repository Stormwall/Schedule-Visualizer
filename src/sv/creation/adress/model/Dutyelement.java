package sv.creation.adress.model;

public class Dutyelement {

	private int id;
	private int dutyID;
	private int blockID;
	private String serviceJourneyID;
	private int fromStopID;
	private int toStopID;
	private String depTime;
	private String arrTime;
	private int elementType;
	
	
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
	public Dutyelement(int id, int dutyID, int blockID, String serviceJourneyID, int fromStopID, int toStopID,
			String depTime, String arrTime, int elementType) {
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


	public int getDutyID() {
		return dutyID;
	}
	
	


}
