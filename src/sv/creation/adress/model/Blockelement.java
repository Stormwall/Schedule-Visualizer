package sv.creation.adress.model;

public class Blockelement {
	
	private int id;
	private int blockID;
	private int serviceJourneyID;
	private int fromStopID;
	private int toStopID;
	private String depTime;
	private String arrTime;
	private int elementType;
	private int serviceJourneyCode;
	
	
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
	public Blockelement(int id, int blockID, int serviceJourneyID, int fromStopID, int toStopID,
			String depTime, String arrTime, int elementType, int serviceJourneyCode) {
		super();
		this.id = id;
		this.blockID = blockID;
		this.serviceJourneyID=serviceJourneyID;
		this.fromStopID = fromStopID;
		this.toStopID = toStopID;
		this.depTime = depTime;
		this.arrTime = arrTime;
		this.elementType = elementType;
		this.serviceJourneyCode = serviceJourneyCode;
	}


	public int getId() {
		return id;
	}


	public int getBlockID() {
		return blockID;
	}


	public int getServiceJourneyID() {
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


	public int getServiceJourneyCode() {
		return serviceJourneyCode;
	}




}
