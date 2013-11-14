package sv.creation.adress.model;

public class Abloesepunkte {

	private int ID;
	private int serviceJourneyID;
	private int haltestellenID;
	private String wechselZeit;
	
	/**
	 * @param iD
	 * @param serviceJourneyID
	 * @param haltestellenID
	 * @param wechselZeit
	 */
	public Abloesepunkte(int iD, int serviceJourneyID, int haltestellenID,
			String wechselZeit) {
		super();
		ID = iD;
		this.serviceJourneyID = serviceJourneyID;
		this.haltestellenID = haltestellenID;
		this.wechselZeit = wechselZeit;
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
	
	
	
	
	
}
