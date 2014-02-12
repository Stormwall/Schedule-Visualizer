package sv.creation.adress.model;

import java.util.ArrayList;

public class Fahrplan {
	
	private int id;
	private ArrayList <Stoppoint> haltestellen = new ArrayList<Stoppoint>();
	private ArrayList<Line> line = new ArrayList<Line>();
	private ArrayList <VehicleType> fahrzeugtypen = new ArrayList<VehicleType>();
	private ArrayList <VehicleTypeGroup> fahrzeugtypgruppen = new ArrayList<VehicleTypeGroup>();
	private ArrayList <VehicleTypeToVehicleTypeGroup> fahrzeugtypzufahrzeugtypgruppen = new ArrayList<VehicleTypeToVehicleTypeGroup>();
	private ArrayList <VehicleCapToStoppoint> fahrzeugKapazitaeZuHaltestellen = new ArrayList<VehicleCapToStoppoint>();
	private ArrayList <ServiceJourney> servicejourney = new ArrayList<ServiceJourney>();
	private ArrayList <Deadruntime> verbindungen = new ArrayList<Deadruntime>();
	private ArrayList <Reliefpoint> reliefpoint = new ArrayList<Reliefpoint>();
	private ArrayList <Transfertime> transfertime = new ArrayList<Transfertime>();
	private ArrayList <Days> days = new ArrayList<Days>();
	private String bezeichnung;
	private String date;
	
	
	/**
	 * @param haltestellen
	 * @param Blockn
	 * @param fahrzeugtypen
	 * @param fahrzeugtypgruppen
	 * @param Linienfahrten
	 * @param verbindungen
	 */ 

	public Fahrplan(int id,
			ArrayList<Stoppoint> haltestellen,
			ArrayList<Line> line,
			ArrayList<VehicleType> fahrzeugtypen,
			ArrayList<VehicleTypeGroup> fahrzeugtypgruppen,
			ArrayList<VehicleTypeToVehicleTypeGroup> fahrzeugtypzufahrzeugtypgruppen,
			ArrayList<VehicleCapToStoppoint> fahrzeugKapazitaeZuHaltestellen,
			ArrayList<ServiceJourney> servicejourney,
			ArrayList<Deadruntime> verbindungen,
			ArrayList<Reliefpoint> reliefpoint,
			ArrayList<Transfertime> transfertime,
			ArrayList<Days> days,
			String bezeichnung, 
			String date) {
		super();
		this.id = id;
		this.haltestellen = haltestellen;
		this.line = line;
		this.fahrzeugtypen = fahrzeugtypen;
		this.fahrzeugtypgruppen = fahrzeugtypgruppen;
		this.fahrzeugtypzufahrzeugtypgruppen = fahrzeugtypzufahrzeugtypgruppen;
		this.fahrzeugKapazitaeZuHaltestellen = fahrzeugKapazitaeZuHaltestellen;
		this.servicejourney = servicejourney;
		this.verbindungen = verbindungen;
		this.reliefpoint = reliefpoint;
		this.transfertime = transfertime;
		this.days = days;
		this.bezeichnung = bezeichnung;
		this.date=date;
		
	}


	//Getter an setter methods
	
	public ArrayList<Stoppoint> getHaltestellen() {
		return haltestellen;
	}


	public void setHaltestellen(ArrayList<Stoppoint> haltestellen) {
		this.haltestellen = haltestellen;
	}


	public ArrayList<Line> getLine() {
		return line;
	}


	public void setLine(ArrayList<Line> line) {
		this.line = line;
	}


	public ArrayList<VehicleType> getFahrzeugtypen() {
		return fahrzeugtypen;
	}


	public void setFahrzeugtypen(ArrayList<VehicleType> fahrzeugtypen) {
		this.fahrzeugtypen = fahrzeugtypen;
	}


	public ArrayList<VehicleTypeGroup> getFahrzeugtypgruppen() {
		return fahrzeugtypgruppen;
	}


	public void setFahrzeugtypgruppen(ArrayList<VehicleTypeGroup> fahrzeugtypgruppen) {
		this.fahrzeugtypgruppen = fahrzeugtypgruppen;
	}


	public ArrayList<VehicleTypeToVehicleTypeGroup> getFahrzeugtypzufahrzeugtypgruppen() {
		return fahrzeugtypzufahrzeugtypgruppen;
	}


	public void setFahrzeugtypzufahrzeugtypgruppen(
			ArrayList<VehicleTypeToVehicleTypeGroup> fahrzeugtypzufahrzeugtypgruppen) {
		this.fahrzeugtypzufahrzeugtypgruppen = fahrzeugtypzufahrzeugtypgruppen;
	}


	public ArrayList<VehicleCapToStoppoint> getFahrzeugKapazitaeZuHaltestellen() {
		return fahrzeugKapazitaeZuHaltestellen;
	}


	public void setFahrzeugKapazitaeZuHaltestellen(
			ArrayList<VehicleCapToStoppoint> fahrzeugKapazitaeZuHaltestellen) {
		this.fahrzeugKapazitaeZuHaltestellen = fahrzeugKapazitaeZuHaltestellen;
	}


	public ArrayList<ServiceJourney> getServicejourney() {
		return servicejourney;
	}


	public void setServicejourney(ArrayList<ServiceJourney> servicejourney) {
		this.servicejourney = servicejourney;
	}


	public ArrayList<Deadruntime> getVerbindungen() {
		return verbindungen;
	}


	public void setVerbindungen(ArrayList<Deadruntime> verbindungen) {
		this.verbindungen = verbindungen;
	}


	public ArrayList<Reliefpoint> getReliefpoint() {
		return reliefpoint;
	}


	public void setReliefpoint(ArrayList<Reliefpoint> reliefpoint) {
		this.reliefpoint = reliefpoint;
	}


	public ArrayList<Transfertime> getTransfertime() {
		return transfertime;
	}


	public void setTransfertime(ArrayList<Transfertime> transfertime) {
		this.transfertime = transfertime;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getBezeichnung() {
		return bezeichnung;
	}


	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public ArrayList<Days> getDays() {
		return days;
	}


	public void setDays(ArrayList<Days> days) {
		this.days = days;
	}


	
	
	
	
	

}
