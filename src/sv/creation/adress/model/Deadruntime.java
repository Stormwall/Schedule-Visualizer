package sv.creation.adress.model;

public class Deadruntime {
	
	private int fromStopID;
	private int toStopID;
	private String fromTime;
	private String toTime;
	private int distanz;
	private int fahrzeit;
	private int fahrplanID;
	
	/**
	 * @param fromStopID
	 * @param toStopID
	 * @param fromTime
	 * @param toTime
	 * @param distanz
	 * @param fahrzeit
	 */
	public Deadruntime(int fromStopID, int toStopID, String fromTime,
			String toTime, int distanz, int fahrzeit, int fahrplanID) {
		super();
		this.fromStopID = fromStopID;
		this.toStopID = toStopID;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.distanz = distanz;
		this.fahrzeit = fahrzeit;
		this.fahrplanID = fahrplanID;
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

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public int getDistanz() {
		return distanz;
	}

	public void setDistanz(int distanz) {
		this.distanz = distanz;
	}

	public int getFahrzeit() {
		return fahrzeit;
	}

	public void setFahrzeit(int fahrzeit) {
		this.fahrzeit = fahrzeit;
	}

	public int getFahrplanID() {
		return fahrplanID;
	}
 
	public void setFahrplanID(int fahrplanID) {
		this.fahrplanID = fahrplanID;
	}
	
	
	

}
