package sv.creation.adress.model;

public class Umlaufplan extends Plan {
	
	private int ID;
	private int fahrzeugTypID;
	private int halteStellenID;
	
	/**
	 * @param blockID
	 * @param serviceJourney
	 * @param fromStopID
	 * @param toStopID
	 * @param depTime
	 * @param arrivalTime
	 * @param elementType
	 * @param iD
	 * @param fahrzeugTypID
	 * @param halteStellenID
	 */
	public Umlaufplan(int blockID, int serviceJourney, int fromStopID,
			int toStopID, String depTime, String arrivalTime, int elementType,
			int iD, int fahrzeugTypID, int halteStellenID) {
		super(blockID, serviceJourney, fromStopID, toStopID, depTime,
				arrivalTime, elementType);
		ID = iD;
		this.fahrzeugTypID = fahrzeugTypID;
		this.halteStellenID = halteStellenID;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getFahrzeugTypID() {
		return fahrzeugTypID;
	}

	public void setFahrzeugTypID(int fahrzeugTypID) {
		this.fahrzeugTypID = fahrzeugTypID;
	}

	public int getHalteStellenID() {
		return halteStellenID;
	}

	public void setHalteStellenID(int halteStellenID) {
		this.halteStellenID = halteStellenID;
	}
	
	
	
	

}
