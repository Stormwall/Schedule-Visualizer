package sv.creation.adress.model;

public class Block {
	
	private int id;
	private int vehTypeID;
	private int depotID;
	
	
	/**
	 * @param id
	 * @param vehTypeID
	 * @param depotID
	 */
	public Block(int id, int vehTypeID, int depotID) {
		super();
		this.id = id;
		this.vehTypeID = vehTypeID;
		this.depotID = depotID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getvehTypeID() {
		return vehTypeID;
	}

	public void setvehTypeID(int vehTypeID) {
		this.vehTypeID = vehTypeID;
	}

	public int getdepotID() {
		return depotID;
	}

	public void setdepotID(int depotID) {
		this.depotID = depotID;
	}
	
	
	
	

}
