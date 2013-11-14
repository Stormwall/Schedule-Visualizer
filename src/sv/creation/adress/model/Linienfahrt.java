package sv.creation.adress.model;

public class Linienfahrt {
	
	private int id;
	private int LinienID;
	private int fromStopID;
	private int toStopID;
	private String depTime;
	private String arrivalTime;
	private int minAheadTime;
	private int minLayoverTime;
	private int fahrzeugtypGruppenID;
	private int maxShiftBackwardSeconds;
	private int maxShiftForwardSeconds;
	private int fromStopBreakFacility;
	private int toStopBreakFacility;
	private int code;
	
	
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
	public Linienfahrt(int id, int linienID, int fromStopID, int toStopID,
			String depTime, String arrivalTime, int minAheadTime,
			int minLayoverTime, int fahrzeugtypGruppenID,
			int maxShiftBackwardSeconds, int maxShiftForwardSeconds,
			int fromStopBreakFacility, int toStopBreakFacility, int code) {
		super();
		this.id = id;
		LinienID = linienID;
		this.fromStopID = fromStopID;
		this.toStopID = toStopID;
		this.depTime = depTime;
		this.arrivalTime = arrivalTime;
		this.minAheadTime = minAheadTime;
		this.minLayoverTime = minLayoverTime;
		this.fahrzeugtypGruppenID = fahrzeugtypGruppenID;
		this.maxShiftBackwardSeconds = maxShiftBackwardSeconds;
		this.maxShiftForwardSeconds = maxShiftForwardSeconds;
		this.fromStopBreakFacility = fromStopBreakFacility;
		this.toStopBreakFacility = toStopBreakFacility;
		this.code = code;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getLinienID() {
		return LinienID;
	}


	public void setLinienID(int linienID) {
		LinienID = linienID;
	}


	public int getFromStopID() {
		return fromStopID;
	}


	public void setFromStopID(int fromStopID) {
		this.fromStopID = fromStopID;
	}


	public int getToStopID() {
		return toStopID;
	}


	public void setToStopID(int toStopID) {
		this.toStopID = toStopID;
	}


	public String getDepTime() {
		return depTime;
	}


	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}


	public String getArrivalTime() {
		return arrivalTime;
	}


	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}


	public int getMinAheadTime() {
		return minAheadTime;
	}


	public void setMinAheadTime(int minAheadTime) {
		this.minAheadTime = minAheadTime;
	}


	public int getMinLayoverTime() {
		return minLayoverTime;
	}


	public void setMinLayoverTime(int minLayoverTime) {
		this.minLayoverTime = minLayoverTime;
	}


	public int getFahrzeugtypGruppenID() {
		return fahrzeugtypGruppenID;
	}


	public void setFahrzeugtypGruppenID(int fahrzeugtypGruppenID) {
		this.fahrzeugtypGruppenID = fahrzeugtypGruppenID;
	}


	public int getMaxShiftBackwardSeconds() {
		return maxShiftBackwardSeconds;
	}


	public void setMaxShiftBackwardSeconds(int maxShiftBackwardSeconds) {
		this.maxShiftBackwardSeconds = maxShiftBackwardSeconds;
	}


	public int getMaxShiftForwardSeconds() {
		return maxShiftForwardSeconds;
	}


	public void setMaxShiftForwardSeconds(int maxShiftForwardSeconds) {
		this.maxShiftForwardSeconds = maxShiftForwardSeconds;
	}


	public int getFromStopBreakFacility() {
		return fromStopBreakFacility;
	}


	public void setFromStopBreakFacility(int fromStopBreakFacility) {
		this.fromStopBreakFacility = fromStopBreakFacility;
	}


	public int getToStopBreakFacility() {
		return toStopBreakFacility;
	}


	public void setToStopBreakFacility(int toStopBreakFacility) {
		this.toStopBreakFacility = toStopBreakFacility;
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}
	
	
	

}
