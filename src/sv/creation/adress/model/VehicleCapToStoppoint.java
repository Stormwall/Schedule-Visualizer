package sv.creation.adress.model;

public class VehicleCapToStoppoint {
	
	private int vehTypeID;
	private int stoppointID;
	private int min;
	private int max;
	private int fahrplanID;
	
	
	public VehicleCapToStoppoint(int vehTypeID, int stoppointID, int min,
			int max, int fahrplanID) {
		super();
		this.vehTypeID = vehTypeID;
		this.stoppointID = stoppointID;
		this.min = min;
		this.max = max;
		this.fahrplanID = fahrplanID;
	}


	public int getVehTypeID() {
		return vehTypeID;
	}


	public void setVehTypeID(int vehTypeID) {
		this.vehTypeID = vehTypeID;
	}


	public int getStoppointID() {
		return stoppointID;
	}


	public void setStoppointID(int stoppointID) {
		this.stoppointID = stoppointID;
	}


	public int getMin() {
		return min;
	}


	public void setMin(int min) {
		this.min = min;
	}


	public int getMax() {
		return max;
	}


	public void setMax(int max) {
		this.max = max;
	}


	public int getFahrplanID() {
		return fahrplanID;
	}


	public void setFahrplanID(int fahrplanID) {
		this.fahrplanID = fahrplanID;
	}
	 
	
	

}
