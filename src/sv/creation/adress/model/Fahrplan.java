package sv.creation.adress.model;

import java.util.ArrayList;

public class Fahrplan {
	
	private ArrayList <Haltestellen> haltestellen = new ArrayList<Haltestellen>();
	private ArrayList <Block> Blockn = new ArrayList<Block>();
	private ArrayList <Fahrzeugtyp> fahrzeugtypen = new ArrayList<Fahrzeugtyp>();
	private ArrayList <Fahrzeugtypgruppen> fahrzeugtypgruppen = new ArrayList<Fahrzeugtypgruppen>();
	private ArrayList <Blockelement> Linienfahrten = new ArrayList<Blockelement>();
	private ArrayList <Verbindung> verbindungen = new ArrayList<Verbindung>();
	
	/**
	 * @param haltestellen
	 * @param Blockn
	 * @param fahrzeugtypen
	 * @param fahrzeugtypgruppen
	 * @param Linienfahrten
	 * @param verbindungen
	 */
	public Fahrplan(ArrayList<Haltestellen> haltestellen,
			ArrayList<Block> Blockn, ArrayList<Fahrzeugtyp> fahrzeugtypen,
			ArrayList<Fahrzeugtypgruppen> fahrzeugtypgruppen,
			ArrayList<Blockelement> Linienfahrten,
			ArrayList<Verbindung> verbindungen) {
		super();
		this.haltestellen = haltestellen;
		this.Blockn = Blockn;
		this.fahrzeugtypen = fahrzeugtypen;
		this.fahrzeugtypgruppen = fahrzeugtypgruppen;
		this.Linienfahrten = Linienfahrten;
		this.verbindungen = verbindungen;
	}

	public ArrayList<Haltestellen> getHaltestellen() {
		return haltestellen;
	}

	public void setHaltestellen(ArrayList<Haltestellen> haltestellen) {
		this.haltestellen = haltestellen;
	}

	public ArrayList<Block> getBlockn() {
		return Blockn;
	}

	public void setBlockn(ArrayList<Block> Blockn) {
		this.Blockn = Blockn;
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

	public ArrayList<Blockelement> getLinienfahrten() {
		return Linienfahrten;
	}

	public void setLinienfahrten(ArrayList<Blockelement> Linienfahrten) {
		this.Linienfahrten = Linienfahrten;
	}

	public ArrayList<Verbindung> getVerbindungen() {
		return verbindungen;
	}

	public void setVerbindungen(ArrayList<Verbindung> verbindungen) {
		this.verbindungen = verbindungen;
	}
	
	
	
	
	
	

}
