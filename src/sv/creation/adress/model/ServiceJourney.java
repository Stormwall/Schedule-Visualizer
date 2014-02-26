package sv.creation.adress.model;

public class ServiceJourney {
	
	private int iD;
	private int serviceJourneyID;
	private int lineID;
	private int fromStopID;
	private int toStopID;
	private String depTime;
	private String arrTime;
	private int minAheadTime;
	private int minLayoverTime;
	private int vehTypeGroupID;
	private int maxShiftBackwardSeconds;
	private int maxShiftForwardSeconds;
	private int fromStopBreakFacility;
	private int toStopBreakFacility;
	private String code;
	private int fahrplanID;
	
	public ServiceJourney(int iD, int serviceJourneyID, int lineID, int fromStopID, int toStopID, String depTime, String arrTime, int minAheadTime, int minLayoverTime, int vehTypeGroupID, int maxShiftBackwardSeconds, int maxShiftForwardSeconds, int fromStopBreakFacility, int toStopBreakFacility, String code, int fahrplanID){
		super();
		this.iD=iD;
		this.serviceJourneyID = serviceJourneyID;
		this.lineID=lineID;
		this.fromStopID=fromStopID;
		this.toStopID=toStopID;
		this.depTime=depTime;
		this.arrTime=arrTime;
		this.minAheadTime=minAheadTime;
		this.minLayoverTime=minLayoverTime;
		this.vehTypeGroupID=vehTypeGroupID;
		this.maxShiftBackwardSeconds=maxShiftBackwardSeconds;
		this.maxShiftForwardSeconds=maxShiftForwardSeconds;
		this.fromStopBreakFacility=fromStopBreakFacility;
		this.toStopBreakFacility=toStopBreakFacility;
		this.code=code;
		this.fahrplanID = fahrplanID;
	}
	
	//Getter methods

	public int getiD() {
		return iD;
	}

	public int getLineID() {
		return lineID;
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

	public int getMinAheadTime() {
		return minAheadTime;
	}

	public int getMinLayoverTime() {
		return minLayoverTime;
	}

	public int getVehTypeGroupID() {
		return vehTypeGroupID;
	}

	public int getMaxShiftBackwardSeconds() {
		return maxShiftBackwardSeconds;
	}

	public int getMaxShiftForwardSeconds() {
		return maxShiftForwardSeconds;
	}

	public int getFromStopBreakFacility() {
		return fromStopBreakFacility;
	}

	public int getToStopBreakFacility() {
		return toStopBreakFacility;
	}

	public String getCode() {
		return code;
	}

	public int getFahrplanID() {
		return fahrplanID;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public int getServiceJourneyID() {
		return serviceJourneyID;
	}

	public void setServiceJourneyID(int serviceJourneyID) {
		this.serviceJourneyID = serviceJourneyID;
	}
	
	
 

}
