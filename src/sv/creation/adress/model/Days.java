package sv.creation.adress.model;

public class Days {
	
	private int tripID;
	private int d1;
	private int d2;
	private int d3;
	private int d4;
	private int d5;
	private int d6;
	private int d7;
	private int fahrplanID;
	
	 
	public Days(int tripID, int d1, int d2, int d3, int d4, int d5, int d6, int d7, int fahrplanID) {
		super();
		this.tripID = tripID;
		this.d1 = d1;
		this.d2 = d2;
		this.d3 = d3;
		this.d4 = d4;
		this.d5 = d5;
		this.d6 = d6;
		this.d7 = d7;
		this.fahrplanID = fahrplanID;
	}

	//Getter- und setter-Methoden
	public int getTripID() {
		return tripID;
	}


	public void setTripID(int tripID) {
		this.tripID = tripID;
	}


	public int getD1() {
		return d1;
	}


	public void setD1(int d1) {
		this.d1 = d1;
	}


	public int getD2() {
		return d2;
	}


	public void setD2(int d2) {
		this.d2 = d2;
	}


	public int getD3() {
		return d3;
	}


	public void setD3(int d3) {
		this.d3 = d3;
	}


	public int getD4() {
		return d4;
	}


	public void setD4(int d4) {
		this.d4 = d4;
	}


	public int getD5() {
		return d5;
	}


	public void setD5(int d5) {
		this.d5 = d5;
	}


	public int getD6() {
		return d6;
	}


	public void setD6(int d6) {
		this.d6 = d6;
	}


	public int getD7() {
		return d7;
	}


	public void setD7(int d7) {
		this.d7 = d7;
	}


	public int getFahrplanID() {
		return fahrplanID;
	}


	public void setFahrplanID(int fahrplanID) {
		this.fahrplanID = fahrplanID;
	}
	
	

}
