package sv.creation.adress.model;

public class Dienstplan extends Plan {
	
	
	private int ID;
	private String diensttyp;
		
	/**
	 * @param blockID
	 * @param serviceJourney
	 * @param fromStopID
	 * @param toStopID
	 * @param depTime
	 * @param arrivalTime
	 * @param elementType
	 * @param iD
	 * @param diensttyp
	 */
	public Dienstplan(int blockID, int serviceJourney, int fromStopID,
			int toStopID, String depTime, String arrivalTime, int elementType,
			int iD, String diensttyp) {
		super(blockID, serviceJourney, fromStopID, toStopID, depTime,
				arrivalTime, elementType);
		ID = iD;
		this.diensttyp = diensttyp;
	}
		
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getDiensttyp() {
		return diensttyp;
	}
	public void setDiensttyp(String diensttyp) {
		this.diensttyp = diensttyp;
	}

	

}
