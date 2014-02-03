package sv.creation.adress.model;

public class Transfertime {

	private int fromtStopID;
	private int toStopID;
	private String fromTime;
	private String toTime;
	private int runtime;
	private int fahrplanID;
	
	public Transfertime(int fromtStopID, int toStopID, String fromTime,
			String toTime, int runtime, int fahrplanID) {
		super();
		this.fromtStopID = fromtStopID;
		this.toStopID = toStopID;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.runtime = runtime;
		this.fahrplanID = fahrplanID;
	}

	public int getFromtStopID() {
		return fromtStopID;
	}

	public void setFromtStopID(int fromtStopID) {
		this.fromtStopID = fromtStopID;
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

	public int getRuntime() {
		return runtime;
	}

	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}

	public int getFahrplanID() {
		return fahrplanID;
	}

	public void setFahrplanID(int fahrplanID) {
		this.fahrplanID = fahrplanID;
	}
	
	 
	
	
}
