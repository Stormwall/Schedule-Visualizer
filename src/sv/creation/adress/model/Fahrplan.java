package sv.creation.adress.model;

import java.util.ArrayList;

public class Fahrplan {
	
	private ArrayList <Haltestellen> haltestellen = new ArrayList<Haltestellen>();
	private ArrayList <Linie> linien = new ArrayList<Linie>();
	private ArrayList <Fahrzeugtyp> fahrzeugtypen = new ArrayList<Fahrzeugtyp>();
	private ArrayList <Fahrzeugtypgruppen> fahrzeugtypgruppen = new ArrayList<Fahrzeugtypgruppen>();
	private ArrayList <Linienfahrt> linienfahrten = new ArrayList<Linienfahrt>();
	private ArrayList <Verbindung> verbindungen = new ArrayList<Verbindung>();
	
	/**
	 * @param haltestellen
	 * @param linien
	 * @param fahrzeugtypen
	 * @param fahrzeugtypgruppen
	 * @param linienfahrten
	 * @param verbindungen
	 */
	public Fahrplan(ArrayList<Haltestellen> haltestellen,
			ArrayList<Linie> linien, ArrayList<Fahrzeugtyp> fahrzeugtypen,
			ArrayList<Fahrzeugtypgruppen> fahrzeugtypgruppen,
			ArrayList<Linienfahrt> linienfahrten,
			ArrayList<Verbindung> verbindungen) {
		super();
		this.haltestellen = haltestellen;
		this.linien = linien;
		this.fahrzeugtypen = fahrzeugtypen;
		this.fahrzeugtypgruppen = fahrzeugtypgruppen;
		this.linienfahrten = linienfahrten;
		this.verbindungen = verbindungen;
	}

	public ArrayList<Haltestellen> getHaltestellen() {
		return haltestellen;
	}

	public void setHaltestellen(ArrayList<Haltestellen> haltestellen) {
		this.haltestellen = haltestellen;
	}

	public ArrayList<Linie> getLinien() {
		return linien;
	}

	public void setLinien(ArrayList<Linie> linien) {
		this.linien = linien;
	}

	public ArrayList<Fahrzeugtyp> getFahrzeugtypen() {
		return fahrzeugtypen;
	}

	public void setFahrzeugtypen(ArrayList<Fahrzeugtyp> fahrzeugtypen) {
		this.fahrzeugtypen = fahrzeugtypen;
	}

	public ArrayList<Fahrzeugtypgruppen> getFahrzeugtypgruppen() {
		return fahrzeugtypgruppen;
	}

	public void setFahrzeugtypgruppen(
			ArrayList<Fahrzeugtypgruppen> fahrzeugtypgruppen) {
		this.fahrzeugtypgruppen = fahrzeugtypgruppen;
	}

	public ArrayList<Linienfahrt> getLinienfahrten() {
		return linienfahrten;
	}

	public void setLinienfahrten(ArrayList<Linienfahrt> linienfahrten) {
		this.linienfahrten = linienfahrten;
	}

	public ArrayList<Verbindung> getVerbindungen() {
		return verbindungen;
	}

	public void setVerbindungen(ArrayList<Verbindung> verbindungen) {
		this.verbindungen = verbindungen;
	}
	
	
	
	
	
	

}
