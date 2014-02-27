package sv.creation.adress.model;

public class Verbindung {

	private int fromStopID;
	private int toStopID;
	private String fromTime;
	private String toTime;
	private int distanz;
	private int fahrzeit;

	/**
	 * @param fromStopID
	 * @param toStopID
	 * @param fromTime
	 * @param toTime
	 * @param distanz
	 * @param fahrzeit
	 */
	public Verbindung(int fromStopID, int toStopID, String fromTime,
			String toTime, int distanz, int fahrzeit) {
		super();
		this.fromStopID = fromStopID;
		this.toStopID = toStopID;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.distanz = distanz;
		this.fahrzeit = fahrzeit;
	}

	// Getter- und setter-Methoden
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

}
