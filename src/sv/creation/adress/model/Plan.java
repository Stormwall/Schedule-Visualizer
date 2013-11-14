package sv.creation.adress.model;

public abstract class Plan {
	
	private int blockID;
	private int serviceJourney;
	private int fromStopID;
	private int toStopID;
	private String depTime;
	private String arrivalTime;
	private int elementType;
	
	/**
	 * @param blockID
	 * @param serviceJourney
	 * @param fromStopID
	 * @param toStopID
	 * @param depTime
	 * @param arrivalTime
	 * @param elementType
	 */
	public Plan(int blockID, int serviceJourney, int fromStopID, int toStopID,
			String depTime, String arrivalTime, int elementType) {
		super();
		this.blockID = blockID;
		this.serviceJourney = serviceJourney;
		this.fromStopID = fromStopID;
		this.toStopID = toStopID;
		this.depTime = depTime;
		this.arrivalTime = arrivalTime;
		this.elementType = elementType;
	}
	
	public int getBlockID() {
		return blockID;
	}
	public void setBlockID(int blockID) {
		this.blockID = blockID;
	}
	public int getServiceJourney() {
		return serviceJourney;
	}
	public void setServiceJourney(int serviceJourney) {
		this.serviceJourney = serviceJourney;
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
	public int getElementType() {
		return elementType;
	}
	public void setElementType(int elementType) {
		this.elementType = elementType;
	}
	
	// Returned den Namen des Element Types
	
	public String returnTypeName (int elementType){
		
		String TypeName= null;
		
		if(elementType==1){
			TypeName ="Servicefahrt";
		}
		if(elementType==2){
			TypeName ="Leerfahrt Haltestellen";
		}
		if(elementType==3){
			TypeName ="Fahrt ins Depot";
		}
		if(elementType==4){
			TypeName ="Fahrt aus dem Depot";
		}
		if(elementType==5){
			TypeName ="Vorbereitung";
		}
		if(elementType==6){
			TypeName ="Nachbereitung";
		}
		if(elementType==7){
			TypeName ="Transfer";
		}
		if(elementType==8){
			TypeName ="Pause";
		}
		if(elementType==9){
			TypeName ="Warten";
		}
		if(elementType==9){
			TypeName ="LayoverTime";
		}		
		return TypeName;
		}
	
	

}
