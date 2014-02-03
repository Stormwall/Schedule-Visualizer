package sv.creation.adress.model;

public class Reliefpoint {

	private int ID;
	private int serviceJourneyID;
	private int haltestellenID;
	private String wechselZeit;
	private int fahrplanID;
	
	/**
	 * @param iD
	 * @param serviceJourneyID
	 * @param haltestellenID
	 * @param wechselZeit
	 */
	public Reliefpoint(int iD, int serviceJourneyID, int haltestellenID,
			String wechselZeit, int fahrplanID) {
		super();
		ID = iD;
		this.serviceJourneyID = serviceJourneyID;
		this.haltestellenID = haltestellenID;
		this.wechselZeit = wechselZeit;
		this.fahrplanID = fahrplanID;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getServiceJourneyID() {
		return serviceJourneyID;
	}

	public void setServiceJourneyID(int serviceJourneyID) {
		this.serviceJourneyID = serviceJourneyID;
	}

	public int getHaltestellenID() {
		return haltestellenID;
	}

	public void setHaltestellenID(int haltestellenID) {
		this.haltestellenID = haltestellenID;
	}

	public String getWechselZeit() {
		return wechselZeit;
	}

	public void setWechselZeit(String wechselZeit) {
		this.wechselZeit = wechselZeit;
	}

	public int getFahrplanID() {
		return fahrplanID;
	}

	public void setFahrplanID(int fahrplanID) {
		this.fahrplanID = fahrplanID;
	}
	
	 
	
	
	
}
