package sv.creation.adress.model;

public class PrimeDelay {
	
	private String dutyID;
	private String vehicleID;
	private String serviceJourneyID;
	private String depTime;
	private int delay;
	private int szenarioID;
	
	public PrimeDelay(String dutyID, String vehicleID, String serviceJourneyID,
			String depTime, int delay, int szenarioID) {
		super();
		this.dutyID = dutyID;
		this.vehicleID = vehicleID;
		this.serviceJourneyID = serviceJourneyID;
		this.depTime = depTime;
		this.delay = delay;
		this.szenarioID = szenarioID;
	}

	//getter and setter methods
	public String getDutyID() {
		return dutyID;
	}

	public void setDutyID(String dutyID) {
		this.dutyID = dutyID;
	}

	public String getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(String vehicleID) {
		this.vehicleID = vehicleID;
	}

	public String getServiceJourneyID() {
		return serviceJourneyID;
	}

	public void setServiceJourneyID(String serviceJourneyID) {
		this.serviceJourneyID = serviceJourneyID;
	}

	public String getDepTime() {
		return depTime;
	}

	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public int getSzenarioID() {
		return szenarioID;
	}

	public void setSzenarioID(int szenarioID) {
		this.szenarioID = szenarioID;
	}
	


}
