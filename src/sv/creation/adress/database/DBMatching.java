package sv.creation.adress.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sv.creation.adress.model.Block;
import sv.creation.adress.model.Blockelement;
import sv.creation.adress.model.Days;
import sv.creation.adress.model.Deadruntime;
import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Duty;
import sv.creation.adress.model.Dutyelement;
import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.model.Line;
import sv.creation.adress.model.Linebundle;
import sv.creation.adress.model.PrimeDelay;
import sv.creation.adress.model.Reliefpoint;
import sv.creation.adress.model.ServiceJourney;
import sv.creation.adress.model.Stoppoint;
import sv.creation.adress.model.Szenario;
import sv.creation.adress.model.Transfertime;
import sv.creation.adress.model.Umlaufplan;
import sv.creation.adress.model.VehicleCapToStoppoint;
import sv.creation.adress.model.VehicleType;
import sv.creation.adress.model.VehicleTypeGroup;
import sv.creation.adress.model.VehicleTypeToVehicleTypeGroup;

/**
 * 
 * This class implements the sql queries which will sent to the database
 * 
 */
public class DBMatching {

	// Erstellt DBConnection-Objekt
	private DBConnection db = new DBConnection();

	// **********************************************************************
	// ****** Array lists to create objects of the schedule elements *******
	// ****** *******
	// **********************************************************************
	ArrayList<Block> umlauf = new ArrayList<Block>();
	ArrayList<Blockelement> blockelement = new ArrayList<Blockelement>();
	ArrayList<Duty> duty = new ArrayList<Duty>();
	ArrayList<Dutyelement> dutyelement = new ArrayList<Dutyelement>();
	ArrayList<Stoppoint> stoppoint = new ArrayList<Stoppoint>();
	ArrayList<Line> line = new ArrayList<Line>();
	ArrayList<Linebundle> linebundle = new ArrayList<Linebundle>();
	ArrayList<VehicleType> vehType = new ArrayList<VehicleType>();
	ArrayList<VehicleTypeGroup> vehTypeGroup = new ArrayList<VehicleTypeGroup>();
	ArrayList<VehicleTypeToVehicleTypeGroup> vehTypeToVehTypeGroup = new ArrayList<VehicleTypeToVehicleTypeGroup>();
	ArrayList<VehicleCapToStoppoint> vehCapToStoppoint = new ArrayList<VehicleCapToStoppoint>();
	ArrayList<ServiceJourney> serviceJourney = new ArrayList<ServiceJourney>();
	ArrayList<Deadruntime> deadruntime = new ArrayList<Deadruntime>();
	ArrayList<Reliefpoint> reliefpoint = new ArrayList<Reliefpoint>();
	ArrayList<Days> days = new ArrayList<Days>();
	ArrayList<Transfertime> transfertime = new ArrayList<Transfertime>();
	ArrayList<Integer> fahrplanIDList = new ArrayList<Integer>();
	ArrayList<String> fahrplanBezeichnungList = new ArrayList<String>();
	ArrayList<String> fahrplanDateList = new ArrayList<String>();
	ArrayList<String> umlaufplanDateList = new ArrayList<String>();
	ArrayList<String> dienstplanDateList = new ArrayList<String>();
	ArrayList<Integer> umlaufplanDayList = new ArrayList<Integer>();
	ArrayList<Integer> dienstplanDayList = new ArrayList<Integer>();
	ArrayList<String> fahrplanNameList = new ArrayList<String>();
	ArrayList<String> umlaufplanplanNameList = new ArrayList<String>();
	ArrayList<String> dienstplanNameList = new ArrayList<String>();
	ArrayList<String> dienstplanBezeichnungList = new ArrayList<String>();
	ArrayList<String> umlaufplanBezeichnungList = new ArrayList<String>();
	ArrayList<Integer> diesntplanUplanIDList = new ArrayList<Integer>();
	ArrayList<String> szenarioBezeichnungList = new ArrayList<String>();
	ArrayList<String> szenarioDateList = new ArrayList<String>();

	ArrayList<Days> daysList = new ArrayList<Days>();
	// *************************************************************
	// ****** Array lists to create objects of the szenarios *******
	// ****** *******
	// *************************************************************
	ArrayList<PrimeDelay> primeDelay = new ArrayList<PrimeDelay>();
	// ********************************
	// ****** Plan objects *******
	// ********************************
	Umlaufplan umlaufplan;
	Dienstplan dienstplan;
	Fahrplan fahrplan;
	// *********************************
	// ****** Szenario object *******
	// *********************************
	Szenario szenario;
	// *********************************
	// ****** ArrayList of plans *******
	// *********************************
	ArrayList<Umlaufplan> umlaufplanliste = new ArrayList<Umlaufplan>();
	ArrayList<Dienstplan> dienstplanliste = new ArrayList<Dienstplan>();
	ArrayList<Fahrplan> fahrplanliste = new ArrayList<Fahrplan>();
	// Object of a database statement
	Statement stmt;
	Statement stmt2;
	Statement stmt3;
	Statement stmt4;

	public DBMatching() {
		System.out.println("DBMatching");
	}

	// **********************************************************************
	// ****** In this method an block object will be created *******
	// ****** *******
	// **********************************************************************
	public void createBlock() {
		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt
					.executeQuery("SELECT  DISTINCT * FROM Block");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int id = Integer.parseInt(rest1.getString("BlockID"));
				int vehTypeID = Integer.parseInt(rest1.getString("VehTypeID"));
				int depotID = Integer.parseInt(rest1.getString("DepotID"));
				umlauf.add(new Block(id, vehTypeID, depotID));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// **********************************************************************
	// ****** In this method an blockelement object will be created *******
	// ****** *******
	// **********************************************************************
	public void createBlockelement() {

		this.db.initDBConnection();
		try {
			// Creating separated statements for the sql queries
			stmt = this.db.getConnection().createStatement();
			stmt2 = this.db.getConnection().createStatement();
			stmt3 = this.db.getConnection().createStatement();
			// Two different datasets will be created
			ResultSet rest3 = stmt
					.executeQuery("SELECT ID, BlockID, ServiceJourneyID, ElementType, UmlaufplanID, MatchingPos FROM Blockelement UNION SELECT ID, BlockID,ServiceJourneyID, ElementType, UmlaufplanID, MatchingPos FROM ExceptionalBlockelement ORDER BY UmlaufplanID, MatchingPos ASC ;");
			ResultSet rest2;
			// All resulted datasets of the sql query will be added to the
			// blockelement array list
			while (rest3.next()) {
				int zahl = Integer.parseInt(rest3.getString("ElementType"));
				// if the blockelement is a service journey the first sql query
				// will be execute
				if (zahl == 1) {
					rest2 = stmt2
							.executeQuery("SELECT be.ID,b.BlockID, be.ServiceJourneyID, sj.FromStopID, sj.ToStopID, sj.DepTime, sj.ArrTime,be.ElementType, be.UmlaufplanID, be.MatchingPos FROM Blockelement AS be, ServiceJourney AS sj, Block AS b WHERE sj.ID='"
									+ rest3.getString("ServiceJourneyID")
									+ "' AND be.ID='"
									+ rest3.getString("ID")
									+ "' AND b.ID='"
									+ rest3.getString("BlockID") + "';");
					// if the read blockelement is a exceptional service journey
					// the second sql query will be execute
				} else {
					rest2 = stmt3
							.executeQuery("SELECT ex.ID, b.BlockID, ex.ServiceJourneyID, ex.FromStopID, ex.ToStopID, ex.DepTime, ex.ArrTime, ex.ElementType, ex.UmlaufplanID, ex.MatchingPos FROM ExceptionalBlockelement AS ex, Block AS b WHERE ex.ServiceJourneyID='"
									+ rest3.getString("ServiceJourneyID")
									+ "' AND ex.ID='"
									+ rest3.getString("ID")
									+ "' AND b.ID='"
									+ rest3.getString("BlockID") + "';");
				}
				// the attributes will be read and saved in variables
				int id = Integer.parseInt(rest2.getString("MatchingPos"));
				int blockID = Integer.parseInt(rest2.getString("BlockID"));
				String serviceJourneyID = rest2.getString("ServiceJourneyID");
				int fromStopID = Integer
						.parseInt(rest2.getString("FromStopID"));
				int toStopID = Integer.parseInt(rest2.getString("ToStopID"));
				String depTime = rest2.getString("DepTime");
				String arrTime = rest2.getString("ArrTime");
				int elementType = zahl;
				int umlaufplanID = Integer.parseInt(rest2
						.getString("UmlaufPlanID"));
				// Bezeichnung für den Elementtyp wird entsprechend hinzugefügt
				String elementTypeName = "";
				switch (zahl) {
				case 1:
					elementTypeName = "Servicefahrt";
					break;
				case 2:
					elementTypeName = "Leerfahrt";
					break;
				case 3:
					elementTypeName = "Einrückfahrt";
					break;
				case 4:
					elementTypeName = "Ausrückfahrt";
					break;
				case 5:
					elementTypeName = "Vorbereitung";
					break;
				case 6:
					elementTypeName = "Nachbereitung";
					break;
				case 7:
					elementTypeName = "Transfer";
					break;
				case 8:
					elementTypeName = "Pause";
					break;
				case 9:
					elementTypeName = "Warten";
					break;
				case 10:
					elementTypeName = "Layover";
					break;
				}
				// all variables will be summed up to an umlaufelement
				blockelement.add(new Blockelement(id, blockID,
						serviceJourneyID, fromStopID, toStopID, depTime,
						arrTime, elementType, elementTypeName, umlaufplanID));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// ***********************************************************************************************
	// ****** In this method both array lists will be integrated in an
	// umlaufplan object. *******
	// ****** This method returns an completely object of a umlaufplan, which
	// can be modified. ******* *******
	// ***********************************************************************************************

	public ArrayList<Umlaufplan> createUmlaufplanObject() {

		createBlock();
		createBlockelement();
		createUmlaufplanDayAndDate();
		this.umlaufplanBezeichnungList = createUmlaufplanBezeichnunglist();
		ArrayList<Integer> idList = createUmlaufplanIDs();
		this.umlaufplanplanNameList = createUmlaufplanNameList();
		// Anzahl der UmlaufplÃƒÂ¤ne wird ausgelesen
		// Strukturvariablen
		int anzahlPlan = 1;
		int zaehlerUmlauf = 0;
		for (int i = 0; i < blockelement.size(); i++) {
			if (i >= 1
					&& blockelement.get(i).getUmlaufplanID() > blockelement
							.get(i - 1).getUmlaufplanID()) {
				anzahlPlan++;
			}
		}
		// Umlaufplanliste wird erzeugt
		for (int i = 1; i <= anzahlPlan; i++) {
			ArrayList<Blockelement> blockelementList = new ArrayList<Blockelement>();
			ArrayList<Block> blockList = new ArrayList<Block>();

			for (int j = 0; j < this.blockelement.size(); j++) {
				if (this.blockelement.get(j).getUmlaufplanID() == idList
						.get(i - 1)) {
					blockelementList.add(blockelement.get(j));
				}
			}

			for (int j2 = zaehlerUmlauf; j2 < this.umlauf.size() - 1; j2++) {
				if (this.umlauf.get(j2).getId() < this.umlauf.get(j2 + 1)
						.getId()) {
					blockList.add(this.umlauf.get(j2));
					zaehlerUmlauf = zaehlerUmlauf + 1;
				}
				if (this.umlauf.get(j2).getId() > this.umlauf.get(j2 + 1)
						.getId()) {
					blockList.add(this.umlauf.get(j2));
					j2 = this.umlauf.size() - 1;
				}
				if (j2 == this.umlauf.size() - 2) {
					blockList.add(this.umlauf.get(j2 + 1));
				}

			}
			int fahrplanID = getFahrplanzugehoerigkeitUmlaufplan(idList
					.get(i - 1));
			zaehlerUmlauf = zaehlerUmlauf + 1;
			Umlaufplan umlaufplanAdd = new Umlaufplan(i,
					this.umlaufplanBezeichnungList.get(i - 1), blockList,
					blockelementList, umlaufplanDayList.get(i - 1), fahrplanID,
					umlaufplanplanNameList.get(i - 1),
					changeDateFormat(umlaufplanDateList.get(i - 1)));
			umlaufplanliste.add(umlaufplanAdd);
		}
		blockelement.clear();
		return umlaufplanliste;
	}

	private ArrayList<String> createUmlaufplanBezeichnunglist() {

		this.db.initDBConnection();
		ArrayList<String> bezeichnunglist = new ArrayList<String>();
		ResultSet rest1;
		try {
			rest1 = stmt.executeQuery("SELECT Bezeichnung FROM Umlaufplan;");
			while (rest1.next()) {
				bezeichnunglist.add(rest1.getString("Bezeichnung"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return bezeichnunglist;
	}

	// Erstellt eine Liste
	private ArrayList<String> createUmlaufplanNameList() {

		this.db.initDBConnection();
		ArrayList<String> nameList = new ArrayList<String>();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT Name FROM Umlaufplan");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				nameList.add(rest1.getString("Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nameList;

	}

	// Erstellt eine Liste mit allen IDs zu den jeweiligen Umlaufplänen
	private ArrayList<Integer> createUmlaufplanIDs() {

		this.db.initDBConnection();
		ArrayList<Integer> idList = new ArrayList<Integer>();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT ID FROM Umlaufplan");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				idList.add(rest1.getInt("ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idList;

	}

	// Überprüft alle DayIDs und Upladdaten zu den jeweiligen Umlaufplänen
	private void createUmlaufplanDayAndDate() {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt
					.executeQuery("SELECT DayID,Datum FROM Umlaufplan");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int dayID;
				if (rest1.getString("DayID") != null) {
					dayID = Integer.parseInt(rest1.getString("DayID"));
				} else {
					dayID = -1;
				}
				umlaufplanDayList.add(dayID);
				String datum = rest1.getString("Datum").toString();
				umlaufplanDateList.add(datum);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// **********************************************************************
	// ****** In this method an duty object will be created *******
	// ****** *******
	// **********************************************************************
	public void createDuty() {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT  DISTINCT * FROM Duty");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int hilfsID = Integer.parseInt(rest1.getString("ID"));
				String id = rest1.getString("DutyID");
				String type = rest1.getString("DutyTypeID");
				duty.add(new Duty(hilfsID, id, type));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// **********************************************************************
	// ****** In this method an dutyelement object will be created *******
	// ****** *******
	// **********************************************************************
	public void createDutyelement() {

		this.db.initDBConnection();
		try {
			// Creating separated statements for the sql queries
			stmt = this.db.getConnection().createStatement();
			stmt2 = this.db.getConnection().createStatement();
			stmt3 = this.db.getConnection().createStatement();
			stmt4 = this.db.getConnection().createStatement();
			// Two different datasets will be created
			ResultSet rest3 = stmt
					.executeQuery("SELECT ID, DutyID, BlockID, ServiceJourneyID, ElementType, DienstplanID, MatchingPos FROM Dutyelement UNION SELECT ID, DutyID, BlockID,ServiceJourneyID, ElementType, DienstplanID, MatchingPos FROM ExceptionalDutyelement ORDER BY DienstplanID,MatchingPos ASC;");
			ResultSet rest2;
			// All resulted datasets of the sql query will be added to the
			// blockelement array list
			while (rest3.next()) {
				int zahl = Integer.parseInt(rest3.getString("ElementType"));
				// if the blockelement is a service journey the first sql query
				// will be execute
				if (zahl == 1) {
					rest2 = stmt2
							.executeQuery("SELECT de.ID, d.DutyID, b.BlockID, de.ServiceJourneyID, sj.FromStopID, sj.ToStopID, sj.DepTime, sj.ArrTime, de.elementType, de.DienstplanID, de.MatchingPos FROM Dutyelement AS de, ServiceJourney AS sj, Block AS b, Duty AS d WHERE sj.ID='"
									+ rest3.getString("ServiceJourneyID")
									+ "'  AND de.ID='"
									+ rest3.getString("ID")
									+ "' AND b.ID='"
									+ rest3.getString("BlockID")
									+ "' AND d.ID='"
									+ rest3.getString("DutyID") + "';");

					// if the read blockelement is a exceptional servie journey
					// the second sql query will be execute
				} else {
					rest2 = stmt3
							.executeQuery("SELECT ex.ID, d.DutyID, ex.BlockID, ex.ServiceJourneyID, ex.FromStopID, ex.ToStopID, ex.DepTime, ex.ArrTime, ex.ElementType, ex.DienstplanID, ex.MatchingPos FROM Duty AS d, ExceptionalDutyelement AS ex WHERE ex.ServiceJourneyID='"
									+ rest3.getString("ServiceJourneyID")
									+ "' AND ex.ID='"
									+ rest3.getString("ID")
									// + "' AND b.BlockID='"
									// + rest3.getString("BlockID")
									+ "' AND d.ID='"
									+ rest3.getString("DutyID") + "';");
				}
				// the attributes will be read and save in variables
				int id = Integer.parseInt(rest2.getString("MatchingPos"));
				String dutyID = rest2.getString("DutyID");
				int blockID = Integer.parseInt(rest2.getString("BlockID"));
				String serviceJourneyID = rest2.getString("ServiceJourneyID");
				int fromStopID = Integer
						.parseInt(rest2.getString("FromStopID"));
				int toStopID = Integer.parseInt(rest2.getString("ToStopID"));
				String depTime = rest2.getString("DepTime");
				String arrTime = rest2.getString("ArrTime");
				int elementType = Integer.parseInt(rest2
						.getString("ElementType"));
				int dienstplanID = Integer.parseInt(rest2
						.getString("DienstplanID"));
				// Bezeichnung für den Elementtyp wird entsprechend hinzugefügt
				String elementTypeName = "";
				switch (elementType) {
				case 1:
					elementTypeName = "Servicefahrt";
					break;
				case 2:
					elementTypeName = "Leerfahrt";
					break;
				case 3:
					elementTypeName = "Einrückfahrt";
					break;
				case 4:
					elementTypeName = "Ausrückfahrt";
					break;
				case 5:
					elementTypeName = "Vorbereitung";
					break;
				case 6:
					elementTypeName = "Nachbereitung";
					break;
				case 7:
					elementTypeName = "Transfer";
					break;
				case 8:
					elementTypeName = "Pause";
					break;
				case 9:
					elementTypeName = "Warten";
					break;
				case 10:
					elementTypeName = "Layover";
					break;
				}
				// rest4=stmt4.executeQuery("SELECT ID FROM Duty WHERE DutyID ="+rest3.getString("DutyID"));
				int dutyHilfsID = Integer.parseInt(rest3.getString("DutyID"));

				// all variables will be sum up to an umlaufelement
				dutyelement.add(new Dutyelement(id, dutyID, blockID,
						serviceJourneyID, fromStopID, toStopID, depTime,
						arrTime, elementType,elementTypeName, dienstplanID, dutyHilfsID));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// ***********************************************************************************************
	// ****** In this method both array lists will be integrated in an
	// umlaufplan object. *******
	// ****** This method returns an completely object of a umlaufplan, which
	// can be modified. ******* *******
	// ***********************************************************************************************
	public ArrayList<Dienstplan> createDienstplanObject() {
		createDuty();
		createDutyelement();
		createDienstplanDate();
		this.dienstplanDayList = createDienstplanDayIDList();
		this.diesntplanUplanIDList = createDienstplanUplanIDList();
		this.dienstplanBezeichnungList = createDienstplanBezeichnungList();
		this.dienstplanNameList = createDienstplanNameList();
		// Anzahl der DienstplÃƒÂ¤ne wird ausgelesen
		// Strukturvariablen
		int anzahlPlan = 1;
		int zaehlerDienst = 0;
		ArrayList<Integer> idList = createDienstplanIDs();
		// Zählt wieviele Dienstpläne in der Datenbank vorhanden sind
		for (int i = 0; i < dutyelement.size(); i++) {
			if (i >= 1
					&& dutyelement.get(i).getDienstplanID() > dutyelement.get(
							i - 1).getDienstplanID()) {
				anzahlPlan++;
			}
		}
		// Dienstplanliste wird erzeugt
		for (int i = 1; i <= anzahlPlan; i++) {
			ArrayList<Dutyelement> dutyelementList = new ArrayList<Dutyelement>();
			ArrayList<Duty> dutyList = new ArrayList<Duty>();
			for (int j = 0; j < this.dutyelement.size(); j++) {
				if (this.dutyelement.get(j).getDienstplanID() == idList
						.get(i - 1)) {
					dutyelementList.add(dutyelement.get(j));
				}
			}

			// Buchstaben werden bei DutyID abgeschnitten
			for (int j2 = zaehlerDienst; j2 < this.duty.size() - 1; j2++) {

				// prüft ob zwei duties mit einem Buchstaben beginnen
				if (this.duty.get(j2).getId().matches("^[a-z].*")
						&& this.duty.get(j2 + 1).getId().matches("^[a-z].*")) {

					String[] string = this.duty.get(j2).getId().split("[a-z]");
					String[] string2 = this.duty.get(j2 + 1).getId()
							.split("[a-z]");
					int id1 = Integer.parseInt(string[1]);
					int id2 = Integer.parseInt(string2[1]);

					if (id1 < id2) {
						dutyList.add(this.duty.get(j2));
						zaehlerDienst = zaehlerDienst + 1;
					}
					if (id1 > id2) {
						dutyList.add(this.duty.get(j2));
						j2 = this.duty.size() - 1;
					}

				} else if (this.duty.get(j2).getId().matches("^[a-z].*")
						&& !this.duty.get(j2 + 1).getId().matches("^[a-z].*")) {

					String[] string = this.duty.get(j2).getId().split("[a-z]");
					int id1 = Integer.parseInt(string[1]);

					if (id1 < Integer.parseInt(this.duty.get(j2 + 1).getId())) {
						dutyList.add(this.duty.get(j2));
						zaehlerDienst = zaehlerDienst + 1;
					}
					if (id1 > Integer.parseInt(this.duty.get(j2 + 1).getId())) {
						dutyList.add(this.duty.get(j2));
						zaehlerDienst = zaehlerDienst + 1;
					}
					// erste Duty beginnt nicht mit Buchstaben, zweite Duty
					// beginnt mit Buchstaben
				} else if (!this.duty.get(j2).getId().matches("^[a-z].*")
						&& this.duty.get(j2 + 1).getId().matches("^[a-z].*")) {

					String[] string = this.duty.get(j2 + 1).getId()
							.split("[a-z]");
					int id2 = Integer.parseInt(string[1]);

					if (Integer.parseInt(this.duty.get(j2).getId()) < id2) {
						dutyList.add(this.duty.get(j2));
						zaehlerDienst = zaehlerDienst + 1;
					}
					if (Integer.parseInt(this.duty.get(j2).getId()) > id2) {
						dutyList.add(this.duty.get(j2));
						j2 = this.duty.size() - 1;
					}
					// Beide Duties enden mit Buchstaben
				} else if (this.duty.get(j2).getId()
						.substring(this.duty.get(j2).getId().length() - 1)
						.matches("[a-z]*")
						&& this.duty
								.get(j2 + 1)
								.getId()
								.substring(
										this.duty.get(j2 + 1).getId().length() - 1)
								.matches("[a-z]*")) {

					String[] string = this.duty.get(j2).getId().split("[a-z]");
					String[] string2 = this.duty.get(j2 + 1).getId()
							.split("[a-z]");
					int id1 = Integer.parseInt(string[0]);
					int id2 = Integer.parseInt(string2[0]);

					if (id1 < id2) {
						dutyList.add(this.duty.get(j2));
						zaehlerDienst = zaehlerDienst + 1;
					}
					if (id1 > id2) {
						dutyList.add(this.duty.get(j2));
						j2 = this.duty.size() - 1;
					}
					// Erste Duty endet Buchstaben, zweite Duty nicht
				} else if (this.duty.get(j2).getId()
						.substring(this.duty.get(j2).getId().length() - 1)
						.matches("[a-z]*")
						&& !this.duty
								.get(j2 + 1)
								.getId()
								.substring(
										this.duty.get(j2 + 1).getId().length() - 1)
								.matches("[a-z]*")) {

					String[] string = this.duty.get(j2).getId().split("[a-z]");
					int id1 = Integer.parseInt(string[0]);

					if (id1 < Integer.parseInt(this.duty.get(j2 + 1).getId())) {
						dutyList.add(this.duty.get(j2));
						zaehlerDienst = zaehlerDienst + 1;
					}
					if (id1 > Integer.parseInt(this.duty.get(j2 + 1).getId())) {
						dutyList.add(this.duty.get(j2));
						zaehlerDienst = zaehlerDienst + 1;
						j2 = this.duty.size() - 1;
					}
					// Erste Duty endet nicht mit Buchstaben, zweite endet mit
					// Buchstaben
				} else if (!this.duty.get(j2).getId()
						.substring(this.duty.get(j2).getId().length() - 1)
						.matches("[a-z]*")
						&& this.duty
								.get(j2 + 1)
								.getId()
								.substring(
										this.duty.get(j2 + 1).getId().length() - 1)
								.matches("[a-z]*")) {

					String[] string = this.duty.get(j2 + 1).getId()
							.split("[a-z]");
					int id2 = Integer.parseInt(string[0]);

					if (Integer.parseInt(this.duty.get(j2).getId()) < id2) {
						dutyList.add(this.duty.get(j2));
						zaehlerDienst = zaehlerDienst + 1;
					}
					if (Integer.parseInt(this.duty.get(j2).getId()) > id2) {
						dutyList.add(this.duty.get(j2));
						j2 = this.duty.size() - 1;
					}
					// keine der beiden Duties enthält ein Buchstaben
				} else {

					if (Integer.parseInt(this.duty.get(j2).getId()) < Integer
							.parseInt(this.duty.get(j2 + 1).getId())) {
						dutyList.add(this.duty.get(j2));
						zaehlerDienst = zaehlerDienst + 1;
					}
					if (Integer.parseInt(this.duty.get(j2).getId()) > Integer
							.parseInt(this.duty.get(j2 + 1).getId())) {
						dutyList.add(this.duty.get(j2));
						j2 = this.duty.size() - 1;
					}
				}
				if (j2 == this.duty.size() - 2) {
					dutyList.add(this.duty.get(j2 + 1));
				}
			}
			zaehlerDienst = zaehlerDienst + 1;
			// Dienstplanobjekt wird erzeugt
			Dienstplan dienstplanAdd = new Dienstplan(i,
					this.dienstplanBezeichnungList.get(i - 1), dutyList,
					dutyelementList,
					getFahrplanzugehoerigkeitDienstPlan(idList.get(i - 1)),
					this.diesntplanUplanIDList.get(i - 1),
					this.dienstplanNameList.get(i - 1),
					changeDateFormat(dienstplanDateList.get(i - 1)),
					this.dienstplanDayList.get(i - 1));
			// Erzeugtes Dienstplanobjekt wird einer Liste mit allen
			// Dienstplänen hinzugefügt
			dienstplanliste.add(dienstplanAdd);
		}
		dutyelement.clear();
		return dienstplanliste;
	}

	// Erstellt eine Liste mit allen IDs der jeweiligen Umlaufpläne
	private ArrayList<Integer> createDienstplanUplanIDList() {
		this.db.initDBConnection();
		ArrayList<Integer> idList = new ArrayList<Integer>();
		ResultSet rest1;
		try {
			rest1 = stmt.executeQuery("SELECT UmlaufplanID FROM Dienstplan;");
			while (rest1.next()) {
				idList.add(rest1.getInt("UmlaufplanID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idList;
	}

	// Erstellt eine List mit den Bezeichungen zu den vorhandenen Dienstplänen
	private ArrayList<String> createDienstplanBezeichnungList() {
		this.db.initDBConnection();
		ArrayList<String> bezeichnungList = new ArrayList<String>();
		ResultSet rest1;
		try {
			rest1 = stmt.executeQuery("SELECT Bezeichnung FROM Dienstplan;");
			while (rest1.next()) {
				bezeichnungList.add(rest1.getString("Bezeichnung"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bezeichnungList;
	}
	
	// Erstellt eine List mit den Bezeichungen zu den vorhandenen Szenarien
	private ArrayList<String> createSzenarioBezeichnungList() {
		this.db.initDBConnection();
		ArrayList<String> bezeichnungList = new ArrayList<String>();
		ResultSet rest1;
		try {
			rest1 = stmt.executeQuery("SELECT Bezeichnung FROM Szenario;");
			while (rest1.next()) {
				bezeichnungList.add(rest1.getString("Bezeichnung"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bezeichnungList;
	}

	// Erstellt eine Liste von Tagen an den die jeweiligen Dienstpläne gültig
	// sind
	private ArrayList<Integer> createDienstplanDayIDList() {
		this.db.initDBConnection();
		ArrayList<Integer> dayList = new ArrayList<Integer>();
		try {
			ResultSet rest1 = stmt
					.executeQuery("SELECT DayID FROM Dienstplan;");
			while (rest1.next()) {
				dayList.add(rest1.getInt("DayID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dayList;
	}

	// Erstellt eine Liste mit allen Namen zu den vorhandenen Dienstplänen
	private ArrayList<String> createDienstplanNameList() {
		this.db.initDBConnection();
		ArrayList<String> nameList = new ArrayList<String>();
		try {
			ResultSet rest1 = stmt.executeQuery("SELECT Name FROM Dienstplan;");
			while (rest1.next()) {
				nameList.add(rest1.getString("Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nameList;
	}

	// Erstellt eine Liste mit allen IDs zu den vorhandenen Dienstplänen
	private ArrayList<Integer> createDienstplanIDs() {

		this.db.initDBConnection();
		ArrayList<Integer> idList = new ArrayList<Integer>();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT ID FROM Dienstplan");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				idList.add(rest1.getInt("ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idList;

	}
	
	// Erstellt eine Liste mit allen IDs zu den vorhandenen Dienstplänen
	private ArrayList<Integer> createSzenarioIDs() {

		this.db.initDBConnection();
		ArrayList<Integer> idList = new ArrayList<Integer>();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT ID FROM Szenario");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				idList.add(rest1.getInt("ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idList;

	}

	// Erstellt eine Liste von Uploaddaten der vorhandenen Dienstpläne
	private void createDienstplanDate() {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT Datum FROM Dienstplan");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				String datum = rest1.getString("Datum").toString();
				dienstplanDateList.add(datum);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	// Erstellt eine Liste von Uploaddaten der vorhandenen Szenarien
	private void createSzenarioDate() {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT Datum FROM Szenario");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				String datum = rest1.getString("Datum").toString();
				szenarioDateList.add(datum);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Prüft zu welchem Fahrplan der übergebene Umlaufplan gehört
	public int getFahrplanzugehoerigkeitUmlaufplan(int umlaufplanID) {
		int fahrplanID = 0;

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt
					.executeQuery("SELECT * FROM Umlaufplan WHERE ID="
							+ umlaufplanID);
			fahrplanID = rest1.getInt("FahrplanID");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fahrplanID;
	}

	// ***********************************************************************************************
	// ****** In this method both array lists will be integrated in an
	// fahrplan object. *******
	// ****** This method returns an completely object of a fahrplan, which
	// can be modified. ******* *******
	// ***********************************************************************************************
	public ArrayList<Fahrplan> createFahrplanObject() {
		createFahrplanID();
		createStoppoint();
		createLine();
		createLinebundle();
		createVehType();
		createVehTypeGroup();
		createVehTypeToVehTypeGroup();
		createVehCapToStoppoint();
		createServiceJourney();
		createDeadruntime();
		createReliefpoint();
		createDays();
		createTransfertime();
		createFahrplanBezeichnugn();
		createFahrplanDate();
		this.fahrplanNameList = createFahrplanNameList();
		// Anzahl der Fahrplaene wird ausgelesen
		// Strukturvariablen
		int anzahlPlan = 1;
		for (int i = 0; i < serviceJourney.size(); i++) {
			if (i >= 1
					&& serviceJourney.get(i).getFahrplanID() > serviceJourney
							.get(i - 1).getFahrplanID()) {
				anzahlPlan++;
			}
		}
		// Fahrplanliste wird erzeugt
		for (int i = 1; i <= anzahlPlan; i++) {
			ArrayList<ServiceJourney> serviceJourneyList = new ArrayList<ServiceJourney>();
			ArrayList<Stoppoint> stoppointList = new ArrayList<Stoppoint>();
			ArrayList<Line> lineList = new ArrayList<Line>();
			ArrayList<Linebundle> linebundleList = new ArrayList<Linebundle>();
			ArrayList<VehicleType> vehTypeList = new ArrayList<VehicleType>();
			ArrayList<VehicleTypeGroup> vehTypeGroupList = new ArrayList<VehicleTypeGroup>();
			ArrayList<VehicleTypeToVehicleTypeGroup> vehTypeToVehTypeGroupList = new ArrayList<VehicleTypeToVehicleTypeGroup>();
			ArrayList<VehicleCapToStoppoint> vehCapToStoppointList = new ArrayList<VehicleCapToStoppoint>();
			ArrayList<Deadruntime> deadruntimeList = new ArrayList<Deadruntime>();
			ArrayList<Reliefpoint> reliefpointList = new ArrayList<Reliefpoint>();
			ArrayList<Transfertime> transfertimeList = new ArrayList<Transfertime>();
			ArrayList<Days> daysList = new ArrayList<Days>();

			for (int j = 0; j < serviceJourney.size(); j++) {
				if (this.serviceJourney.get(j).getFahrplanID() == i) {
					serviceJourneyList.add(serviceJourney.get(j));
				}
			}
			for (int j = 0; j < stoppoint.size(); j++) {
				if (this.stoppoint.get(j).getFahrplanID() == i) {
					stoppointList.add(stoppoint.get(j));
				}
			}
			for (int j = 0; j < line.size(); j++) {
				if (this.line.get(j).getFahrplanID() == i) {
					lineList.add(line.get(j));
				}
			}

			for (int j = 0; j < linebundle.size(); j++) {
				if (this.linebundle.get(j).getFahrplanID() == i) {
					linebundleList.add(linebundle.get(j));
				}
			}
			for (int j = 0; j < vehType.size(); j++) {
				if (this.vehType.get(j).getFahrplanID() == i) {
					vehTypeList.add(vehType.get(j));
				}
			}
			for (int j = 0; j < vehTypeGroup.size(); j++) {
				if (this.vehTypeGroup.get(j).getFahrplanID() == i) {
					vehTypeGroupList.add(vehTypeGroup.get(j));
				}
			}
			for (int j = 0; j < vehTypeToVehTypeGroup.size(); j++) {
				if (this.vehTypeToVehTypeGroup.get(j).getFahrplanID() == i) {
					vehTypeToVehTypeGroupList.add(vehTypeToVehTypeGroup.get(j));
				}
			}
			for (int j = 0; j < vehCapToStoppoint.size(); j++) {
				if (vehCapToStoppoint.get(j).getFahrplanID() == i) {
					vehCapToStoppointList.add(vehCapToStoppoint.get(j));
				}
			}
			for (int j = 0; j < deadruntime.size(); j++) {
				if (deadruntime.get(j).getFahrplanID() == i) {
					deadruntimeList.add(deadruntime.get(j));
				}
			}
			for (int j = 0; j < reliefpoint.size(); j++) {
				if (reliefpoint.get(j).getFahrplanID() == i) {
					reliefpointList.add(reliefpoint.get(j));
				}
			}
			for (int j = 0; j < transfertime.size(); j++) {
				if (transfertime.get(j).getFahrplanID() == i) {
					transfertimeList.add(transfertime.get(j));
				}
			}

			for (int j = 0; j < days.size(); j++) {
				if (days.get(j).getFahrplanID() == i) {
					daysList.add(days.get(j));
				}
			}

			// Ein Fahrplanobjekt wird erzeugt
			Fahrplan fahrplanAdd = new Fahrplan(fahrplanIDList.get(i - 1),
					stoppointList, lineList, linebundleList, vehTypeList,
					vehTypeGroupList, vehTypeToVehTypeGroupList,
					vehCapToStoppointList, serviceJourneyList, deadruntimeList,
					reliefpointList, transfertimeList, daysList,
					fahrplanBezeichnungList.get(i - 1),
					this.fahrplanNameList.get(i - 1),
					changeDateFormat(fahrplanDateList.get(i - 1)));
			// Erzeugtes Fahrplanobjekt wird zu einer Liste aller
			// Fahrplanobjekte hinzugefügt
			fahrplanliste.add(fahrplanAdd);
		}
		this.serviceJourney.clear();
		return fahrplanliste;
	}

	// Erstellt eine ArrayList mit allen in der Datenbank vorhandenen
	// Linebundles
	private void createLinebundle() {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT  * FROM Linebundle");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int id = Integer.parseInt(rest1.getString("ID"));
				int linebundleID = Integer.parseInt(rest1
						.getString("LinebundleID"));
				int lineID = Integer.parseInt(rest1.getString("LineID"));
				int fahrplanID = Integer
						.parseInt(rest1.getString("FahrplanID"));
				linebundle.add(new Linebundle(id, linebundleID, lineID,
						fahrplanID));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private ArrayList<String> createFahrplanNameList() {
		this.db.initDBConnection();
		ArrayList<String> namelist = new ArrayList<String>();
		try {
			ResultSet rest1 = stmt.executeQuery("SELECT Name FROM Fahrplan;");
			while (rest1.next()) {
				namelist.add(rest1.getString("Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return namelist;

	}

	private void createFahrplanDate() {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT Datum FROM Fahrplan");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				String datum = rest1.getString("Datum").toString();
				fahrplanDateList.add(datum);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void createFahrplanBezeichnugn() {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt
					.executeQuery("SELECT Bezeichnung FROM Fahrplan");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				String bezeichnung = rest1.getString("Bezeichnung");
				fahrplanBezeichnungList.add(bezeichnung);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void createFahrplanID() {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT ID FROM Fahrplan");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int id = Integer.parseInt(rest1.getString("ID"));
				fahrplanIDList.add(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void createTransfertime() {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt
					.executeQuery("SELECT  DISTINCT * FROM Transfertime");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int fromStopID = Integer
						.parseInt(rest1.getString("FromStopID"));
				int toStopID = Integer.parseInt(rest1.getString("ToStopID"));
				String fromTime = rest1.getString("FromTime");
				String toTime = rest1.getString("ToTime");
				int runtime = Integer.parseInt(rest1.getString("Runtime"));
				int fahrplanID = Integer
						.parseInt(rest1.getString("FahrplanID"));
				transfertime.add(new Transfertime(fromStopID, toStopID,
						fromTime, toTime, runtime, fahrplanID));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void createDays() {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT  DISTINCT * FROM Days");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int tripID = Integer.parseInt(rest1.getString("TripID"));
				int d1 = Integer.parseInt(rest1.getString("d1"));
				int d2 = Integer.parseInt(rest1.getString("d2"));
				int d3 = Integer.parseInt(rest1.getString("d3"));
				int d4 = Integer.parseInt(rest1.getString("d4"));
				int d5 = Integer.parseInt(rest1.getString("d5"));
				int d6 = 0;
				if (rest1.getString("d6") != null) {
					d6 = Integer.parseInt(rest1.getString("d6"));
				}
				int d7 = 0;
				if (rest1.getString("d7") != null) {
					d7 = Integer.parseInt(rest1.getString("d7"));
				}
				int fahrplanID = Integer
						.parseInt(rest1.getString("FahrplanID"));
				;
				days.add(new Days(tripID, d1, d2, d3, d4, d5, d6, d7,
						fahrplanID));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void createReliefpoint() {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt
					.executeQuery("SELECT  rp.ReliefpointID, sj.ServiceJourneyID, st.StoppointID, rp.StopTime, rp.FahrplanID FROM Reliefpoint AS rp, ServiceJourney AS sj, Stoppoint AS st WHERE rp.ServiceJourneyID=sj.ID AND rp.StoppointID=st.ID");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int reliefpointID = Integer.parseInt(rest1
						.getString("ReliefpointID"));
				int serviceJourneyID = Integer.parseInt(rest1
						.getString("ServiceJourneyID"));
				int stoppointID = Integer.parseInt(rest1
						.getString("StoppointID"));
				String stopTime = rest1.getString("StopTime");
				int fahrplanID = Integer
						.parseInt(rest1.getString("FahrplanID"));
				reliefpoint.add(new Reliefpoint(reliefpointID,
						serviceJourneyID, stoppointID, stopTime, fahrplanID));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void createDeadruntime() {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt
					.executeQuery("SELECT  DISTINCT * FROM Deadruntime");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int fromStopID = Integer
						.parseInt(rest1.getString("FromStopID"));
				int toStopID = Integer.parseInt(rest1.getString("ToStopID"));
				String fromTime = rest1.getString("FromTime");
				String toTime = rest1.getString("ToTime");
				int distance = Integer.parseInt(rest1.getString("Distance"));
				int runtime = Integer.parseInt(rest1.getString("Runtime"));
				int fahrplanID = Integer
						.parseInt(rest1.getString("FahrplanID"));
				deadruntime.add(new Deadruntime(fromStopID, toStopID, fromTime,
						toTime, distance, runtime, fahrplanID));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void createServiceJourney() {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt
			// .executeQuery("SELECT sj.ID,sj.ServiceJourneyID,l.LineID,sj.FromStopID,sj.ToStopID, sj.DepTime, sj.ArrTime, sj.MinAheadTime, sj.MinLayoverTime, vtg.VehicleTypeGroupID, sj.MaxShiftBackwardSeconds, sj.MaxShiftForwardSeconds, sj.FromStopBreakFacility, sj.ToStopBreakFacility, sj.Code FROM ServiceJourney AS sj, VehicleTypeGroup AS vtg, Line AS l, Stoppoint AS st WHERE sj.LineID=l.ID AND sj.FromStopID=st.ID AND sj.ToStopID=st.ID AND sj.VehTypeGroupID=vtg.ID");
					.executeQuery("SELECT * FROM ServiceJourney;");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int id = Integer.parseInt(rest1.getString("ID"));
				int serviceJourneyID = Integer.parseInt(rest1
						.getString("ServiceJourneyID"));
				int lineID = Integer.parseInt(rest1.getString("LineID"));
				int fromStopID = Integer
						.parseInt(rest1.getString("FromStopID"));
				int toStopID = Integer.parseInt(rest1.getString("ToStopID"));
				String depTime = rest1.getString("DepTime");
				String arrTime = rest1.getString("ArrTime");
				int minAheadTime = Integer.parseInt(rest1
						.getString("MinAheadTime"));
				int minLayoverTime = Integer.parseInt(rest1
						.getString("MinLayoverTime"));
				int vehTypeGroupID = Integer.parseInt(rest1
						.getString("VehTypeGroupID"));
				int maxShiftBackwardSeconds = Integer.parseInt(rest1
						.getString("MaxShiftBackwardSeconds"));
				int maxShiftForwardSeconds = Integer.parseInt(rest1
						.getString("MaxShiftForwardSeconds"));
				int fromStopBreakFacility = Integer.parseInt(rest1
						.getString("FromStopBreakFacility"));
				int toStopBreakFacility = Integer.parseInt(rest1
						.getString("ToStopBreakFacility"));
				String code = rest1.getString("Code");
				int fahrplanID = Integer
						.parseInt(rest1.getString("FahrplanID"));
				serviceJourney.add(new ServiceJourney(id, serviceJourneyID,
						lineID, fromStopID, toStopID, depTime, arrTime,
						minAheadTime, minLayoverTime, vehTypeGroupID,
						maxShiftBackwardSeconds, maxShiftForwardSeconds,
						fromStopBreakFacility, toStopBreakFacility, code,
						fahrplanID));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// Füllt eine Liste mit allen Fahrzeugkapazitäten, die den Haltestellen
	// zugeordnet sind
	private void createVehCapToStoppoint() {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt
					.executeQuery("SELECT  vt.VehicleTypeID, st.StoppointID, vcts.Min, vcts.Max,vcts.FahrplanID FROM VehicleCapToStoppoint AS vcts, VehicleType AS vt, Stoppoint AS st WHERE vcts.VehTypeID=vt.ID AND vcts.StoppointID=st.ID");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int vehTypeID = Integer.parseInt(rest1
						.getString("VehicleTypeID"));
				int stoppointID = Integer.parseInt(rest1
						.getString("StoppointID"));
				int min = Integer.parseInt(rest1.getString("Min"));
				int max = Integer.parseInt(rest1.getString("Max"));
				int fahrplanID = Integer
						.parseInt(rest1.getString("FahrplanID"));
				vehCapToStoppoint.add(new VehicleCapToStoppoint(vehTypeID,
						stoppointID, min, max, fahrplanID));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// Füllt eine Liste mit allen Fahrzeugtypen, die zu einer Fahrzeugtypgruppe
	// gehören
	private void createVehTypeToVehTypeGroup() {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt
					.executeQuery("SELECT  vtg.VehicleTypeGroupID, vt.VehicleTypeID, v.FahrplanID FROM VehicleTypeToVehicleTypeGroup AS v, VehicleTypeGroup AS vtg, VehicleType AS vt WHERE v.VehTypeID=vt.ID AND v.VehTypeGroupID=vtg.ID");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int vehTypeGroupID = Integer.parseInt(rest1
						.getString("VehicleTypeGroupID"));
				int vehTypeID = Integer.parseInt(rest1
						.getString("VehicleTypeID"));
				int fahrplanID = Integer
						.parseInt(rest1.getString("FahrplanID"));
				vehTypeToVehTypeGroup.add(new VehicleTypeToVehicleTypeGroup(
						vehTypeID, vehTypeGroupID, fahrplanID));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// Füllt eine Liste mit allen Fahrzuegtypgruppen
	private void createVehTypeGroup() {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt
					.executeQuery("SELECT  DISTINCT * FROM VehicleTypeGroup");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int vehTypeGroupID = Integer.parseInt(rest1
						.getString("VehicleTypeGroupID"));
				int code = Integer.parseInt(rest1.getString("Code"));
				String name = rest1.getString("Name");
				int fahrplanID = Integer
						.parseInt(rest1.getString("FahrplanID"));
				vehTypeGroup.add(new VehicleTypeGroup(vehTypeGroupID, code,
						name, fahrplanID));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// Füllt eine Liste mit allen Fahrzeugtypen
	private void createVehType() {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt
					.executeQuery("SELECT  DISTINCT * FROM VehicleType");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int vehTypeID = Integer.parseInt(rest1
						.getString("VehicleTypeID"));
				int code = Integer.parseInt(rest1.getString("Code"));
				int name = Integer.parseInt(rest1.getString("Name"));
				int vehCost = Integer.parseInt(rest1.getString("VehCost"));
				int kmCost = Integer.parseInt(rest1.getString("KmCost"));
				int hourCost = Integer.parseInt(rest1.getString("HourCost"));
				int capacity = Integer.parseInt(rest1.getString("Capacity"));
				int fahrplanID = Integer
						.parseInt(rest1.getString("FahrplanID"));
				vehType.add(new VehicleType(vehTypeID, code, name, vehCost,
						kmCost, hourCost, capacity, fahrplanID));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// Füllt eine Liste mit allen Linien
	private void createLine() {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT  DISTINCT * FROM Line");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int lineID = Integer.parseInt(rest1.getString("LineID"));
				int code = Integer.parseInt(rest1.getString("Code"));
				String name = rest1.getString("Name");
				int fahrplanID = Integer
						.parseInt(rest1.getString("FahrplanID"));
				line.add(new Line(lineID, code, name, fahrplanID));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// Füllt eine Liste mit allen Stoppoints
	private void createStoppoint() {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt
					.executeQuery("SELECT  DISTINCT * FROM Stoppoint");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int stoppointID = Integer.parseInt(rest1
						.getString("StoppointID"));
				int code = Integer.parseInt(rest1.getString("Code"));
				String name = rest1.getString("Name");
				int fahrplanID = Integer
						.parseInt(rest1.getString("FahrplanID"));
				stoppoint
						.add(new Stoppoint(stoppointID, code, name, fahrplanID));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// Prüft zu welchem Fahrplan der Dienstplan gehört
	public int getFahrplanzugehoerigkeitDienstPlan(int dienstplanID) {
		int fahrplanID = 0;

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt
					.executeQuery("SELECT * FROM Dienstplan WHERE ID="
							+ dienstplanID);
			fahrplanID = rest1.getInt("FahrplanID");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return fahrplanID;
	}

	// Prüft, ob Datenbank leer ist
	public boolean databaseIsEmpty() {

		boolean isEmpty = false;

		this.db.initDBConnection();

		ArrayList<Integer> id = new ArrayList<Integer>();

		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT * FROM Fahrplan;");
			while (rest1.next()) {
				id.add(Integer.parseInt(rest1.getString("ID")));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (id.isEmpty()) {
			isEmpty = true;
		}
		return isEmpty;
	}

	// Prüft, ob kein Umlaufplan vorhanden ist
	public boolean umlaufplanIsEmpty() {
		boolean isEmpty = false;

		this.db.initDBConnection();

		ArrayList<Integer> id = new ArrayList<Integer>();

		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT * FROM Umlaufplan;");
			while (rest1.next()) {
				id.add(Integer.parseInt(rest1.getString("ID")));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (id.isEmpty()) {
			isEmpty = true;
		}
		return isEmpty;
	}

	// Prüft, ob kein Dienstplan vorhanden ist
	public boolean dienstplanIsEmpty() {
		boolean isEmpty = false;

		this.db.initDBConnection();

		ArrayList<Integer> dienstplan = new ArrayList<Integer>();

		// Creating a sql query
		try {

			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT * FROM Dienstplan;");
			while (rest1.next()) {
				dienstplan.add(Integer.parseInt(rest1.getString("ID")));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (dienstplan.isEmpty()) {
			isEmpty = true;
		}
		return isEmpty;
	}

	// Prüft, ob kein Fahrplan vorhanden ist
	public boolean fahrplanIsEmpty() {
		boolean isEmpty = false;

		this.db.initDBConnection();

		ArrayList<Integer> fahrplan = new ArrayList<Integer>();

		// Creating a sql query
		try {

			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT * FROM Fahrplan;");
			while (rest1.next()) {
				fahrplan.add(Integer.parseInt(rest1.getString("ID")));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		if (fahrplan.isEmpty()) {
			isEmpty = true;
		}
		return isEmpty;
	}

	// Wandelt einen String in das richte Datumsformat um
	public String changeDateFormat(String date) {

		String newDate = "";
		String[] dateSplit = date.split("-");
		newDate = dateSplit[2] + "." + dateSplit[1] + "." + dateSplit[0];

		return newDate;
	}

	// Löscht einen Fahrplan
	public void deleteFahrplan(Fahrplan fahrplan) {

		this.db.initDBConnection();

		// Creating a sql query
		try {

			stmt = this.db.getConnection().createStatement();
			stmt.executeUpdate("DELETE FROM Fahrplan WHERE ID='"
					+ fahrplan.getId() + "';");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// Löscht einen Dienstplan
	public void deleteDienstplan(Dienstplan dienstplan) {

		this.db.initDBConnection();

		// Creating a sql query
		try {

			stmt = this.db.getConnection().createStatement();
			stmt.executeUpdate("DELETE FROM Dienstplan WHERE ID='"
					+ dienstplan.getId() + "';");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// Löscht einen Umlaufplan
	public void deleteUmlaufplan(Umlaufplan umlaufplan) {

		this.db.initDBConnection();

		// Creating a sql query
		try {

			stmt = this.db.getConnection().createStatement();
			stmt.executeUpdate("DELETE FROM Umlaufplan WHERE ID='"
					+ umlaufplan.getId() + "';");

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public ArrayList<Szenario> createSzenarioObject() {

		ArrayList<Szenario> szenarioList = new ArrayList<Szenario>();
		ArrayList<PrimeDelay> primeDelayList = new ArrayList<PrimeDelay>();
		ArrayList<Integer> idList= createSzenarioIDs();
		createSzenario();
		this.szenarioBezeichnungList=createSzenarioBezeichnungList();
		createSzenarioDate();
		int anzahlSzenarien = 1;

		// Anzahl der Szenarien wird ermittelt
		for (int i = 0; i < primeDelay.size(); i++) {
			if (i >= 1&&primeDelay.get(i).getSzenarioID() > primeDelay.get(i - 1).getSzenarioID()) {
				anzahlSzenarien++;
			}
		}
	

		for (int i = 1; i <= anzahlSzenarien; i++) {
			for (int j = 0; j < primeDelay.size(); j++) {
				if (primeDelay.get(j).getSzenarioID() == i) {
					primeDelayList.add(primeDelay.get(j));
				}
			}
			
     
			Szenario szenario = new Szenario(i, primeDelayList,
					getFahrplanzugehoerigkeitSzenario(idList.get(i-1)),szenarioBezeichnungList.get(i-1),szenarioDateList.get(i-1));
			// Objekt wird einer Liste mit allen Szeanrien hinzugefügt
			szenarioList.add(szenario);
		}
		return szenarioList;

	}

	// Prüft zu welchem Fahrplan das Szenario gehört
	private int getFahrplanzugehoerigkeitSzenario(int i) {

		int fahrplanID = 0;

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt
					.executeQuery("SELECT * FROM Szenario WHERE ID=" + i);
			fahrplanID = rest1.getInt("FahrplanID");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return fahrplanID;
	}

	// Erstellt ein Szenarioobjekt
	private void createSzenario() {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			ResultSet rest1 = stmt
					.executeQuery("SELECT * FROM PrimeDelaySzenario");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				String dutyID = rest1.getString("DutyID");
				String vehicleID = rest1.getString("VehicleID");
				String serviceJourneyID = rest1.getString("ServiceJourneyID");
				String depTime = rest1.getString("DepTime");
				int delay = Integer.parseInt(rest1.getString("Delay"));
				int szenarioID = Integer
						.parseInt(rest1.getString("SzenarioID"));
				// Objekt wird erzeugt
				primeDelay.add(new PrimeDelay(dutyID, vehicleID,
						serviceJourneyID, depTime, delay, szenarioID));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// Benennt den Namen des Fahrplans um
	public void benenneFahrplanUm(int fahrplanID, String bezeichnung) {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			stmt.executeUpdate("UPDATE Fahrplan SET Name='" + bezeichnung
					+ "' WHERE ID='" + fahrplanID + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// Benennt den Namen des Umlaufplans um
	public void benenneUmlaufplanUm(int umlaufplanID, String bezeichnung) {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			stmt.executeUpdate("UPDATE Umlaufplan SET Name='" + bezeichnung
					+ "' WHERE ID='" + umlaufplanID + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// Benennt den Namen des Dienstplans um
	public void benenneDienstplanUm(int dienstplanID, String bezeichnung) {

		this.db.initDBConnection();
		// Creating a sql query
		try {
			stmt = this.db.getConnection().createStatement();
			stmt.executeUpdate("UPDATE Dienstplan SET Name='" + bezeichnung
					+ "' WHERE ID='" + dienstplanID + "'");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// *****************************
	// ****** Getter methods *******
	// *****************************
	public Umlaufplan getUmlaufplan() {
		return umlaufplan;
	}

	public Dienstplan getDienstplan() {
		return dienstplan;
	}

	public Fahrplan getFahrplan() {
		return fahrplan;
	}

	public Szenario getSzenario() {
		return szenario;
	}

}