package sv.creation.adress.database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import sv.creation.adress.model.Block;
import sv.creation.adress.model.Blockelement;
import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Duty;
import sv.creation.adress.model.Dutyelement;
import sv.creation.adress.model.Umlaufplan;
/**
 * 
 * This class implements the sql queries which will sent to the database
 * 
 */
public class DBMatching {
	// **********************************************************************
	// ****** Array lists to create objects of the schedule elements *******
	// ****** *******
	// **********************************************************************
	ArrayList<Block> umlauf = new ArrayList<Block>();
	ArrayList<Blockelement> blockelement = new ArrayList<Blockelement>();
	ArrayList<Duty> duty = new ArrayList<Duty>();
	ArrayList<Dutyelement> dutyelement = new ArrayList<Dutyelement>();
	// ********************************
	// ****** Plan objects *******
	// ********************************
	Umlaufplan umlaufplan;
	Dienstplan dienstplan;
	// *********************************
	// ****** ArrayList of plans *******
	// *********************************
	ArrayList<Umlaufplan> umlaufplanliste = new ArrayList<Umlaufplan>();
	ArrayList<Dienstplan> dienstplanliste = new ArrayList<Dienstplan>();
	// Object of a database statement
	Statement stmt;
	Statement stmt2;
	Statement stmt3;
	Statement stmt4;
	// **********************************************************************
	// ****** In this method an block object will be created *******
	// ****** *******
	// **********************************************************************
	public void createBlock() {
		DBConnection db = new DBConnection();
		db.initDBConnection();
		// Creating a sql query
		try {
			stmt = db.getConnection().createStatement();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// **********************************************************************
	// ****** In this method an blockelement object will be created *******
	// ****** *******
	// **********************************************************************
	public void createBlockelement() {
		DBConnection db = new DBConnection();
		db.initDBConnection();
		try {
			// Creating separated statements for the sql queries
			stmt = db.getConnection().createStatement();
			stmt2 = db.getConnection().createStatement();
			stmt3 = db.getConnection().createStatement();
			// Two different datasets will be created
			ResultSet rest3 = stmt.executeQuery("SELECT ID, BlockID, ServiceJourneyID, ElementType, MatchingPos FROM Blockelement UNION SELECT ID, BlockID,ServiceJourneyID, ElementType, MatchingPos FROM ExceptionalBlockelement ORDER BY MatchingPos ASC;");
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
				//Bezeichnung für den Elementtyp wird entsprechend hinzugefügt
				String elementTypeName = "";
				switch(zahl){
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
			// TODO Auto-generated catch block
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
		ArrayList<Umlaufplan> umlaufplanliste = new ArrayList<Umlaufplan>();
		createBlock();
		createBlockelement();
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
				if (this.blockelement.get(j).getUmlaufplanID() == i) {
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
			zaehlerUmlauf = zaehlerUmlauf + 1;
			Umlaufplan umlaufplanAdd = new Umlaufplan(i, blockList,
					blockelementList,getFahrplanzugehoerigkeitUmlaufplan(i));
			umlaufplanliste.add(umlaufplanAdd);
		}
		return umlaufplanliste;
	}
	// **********************************************************************
	// ****** In this method an duty object will be created *******
	// ****** *******
	// **********************************************************************
	public void createDuty() {
		DBConnection db = new DBConnection();
		db.initDBConnection();
		// Creating a sql query
		try {
			stmt = db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT  DISTINCT * FROM Duty");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int hilfsID = Integer.parseInt(rest1.getString("ID"));
				String id = rest1.getString("DutyID");
				String type = rest1.getString("DutyTypeID");
				duty.add(new Duty(hilfsID,id, type));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// **********************************************************************
	// ****** In this method an dutyelement object will be created *******
	// ****** *******
	// **********************************************************************
	public void createDutyelement() {
		DBConnection db = new DBConnection();
		db.initDBConnection();
		try {
			// Creating separated statements for the sql queries
			stmt = db.getConnection().createStatement();
			stmt2 = db.getConnection().createStatement();
			stmt3 = db.getConnection().createStatement();
			stmt4 = db.getConnection().createStatement();
			// Two different datasets will be created
			ResultSet rest3 = stmt.executeQuery("SELECT ID, DutyID, BlockID, ServiceJourneyID, ElementType, MatchingPos FROM Dutyelement UNION SELECT ID, DutyID, BlockID,ServiceJourneyID, ElementType, MatchingPos FROM ExceptionalDutyelement ORDER BY MatchingPos ASC;");
			ResultSet rest2;
			ResultSet rest4;
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
//									+ "' AND b.BlockID='"
//									+ rest3.getString("BlockID")
									+ "' AND d.ID='"
									+ rest3.getString("DutyID")+ "';");
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
//				rest4=stmt4.executeQuery("SELECT ID FROM Duty WHERE DutyID ="+rest3.getString("DutyID"));
				int dutyHilfsID=Integer.parseInt(rest3.getString("DutyID"));
				
				// all variables will be sum up to an umlaufelement
				dutyelement.add(new Dutyelement(id, dutyID, blockID,
						serviceJourneyID, fromStopID, toStopID, depTime,
						arrTime, elementType, dienstplanID, dutyHilfsID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		ArrayList<Dienstplan> dienstplanliste = new ArrayList<Dienstplan>();
		createDuty();
		createDutyelement();
		// Anzahl der DienstplÃƒÂ¤ne wird ausgelesen
		// Strukturvariablen
		int anzahlPlan = 1;
		int zaehlerDienst = 0;
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
				if (this.dutyelement.get(j).getDienstplanID() == i) {
					dutyelementList.add(dutyelement.get(j));
				}
			}
			for (int j2 = zaehlerDienst; j2 < this.duty.size() - 1; j2++) {

				if(this.duty.get(j2).getId().endsWith("p")&&this.duty.get(j2+1).getId().endsWith("p")){
					
					String[] string=this.duty.get(j2).getId().split("p");
					String[] string2=this.duty.get(j2+1).getId().split("p");
					int id1=Integer.parseInt(string[0]);
					int id2=Integer.parseInt(string2[0]);
					
					if (id1 < id2) {
						dutyList.add(this.duty.get(j2));
						zaehlerDienst = zaehlerDienst + 1;
					}
					if (id1 > id2) {
						dutyList.add(this.duty.get(j2));
						j2 = this.duty.size() - 1;
					}
					
				}
				else if(this.duty.get(j2).getId().endsWith("p")&&!this.duty.get(j2+1).getId().endsWith("p")){
					
					String[] string=this.duty.get(j2).getId().split("p");
					int id1=Integer.parseInt(string[0]);
					
					if (id1 < Integer.parseInt(this.duty.get(j2 + 1).getId())) {
						dutyList.add(this.duty.get(j2));
						zaehlerDienst = zaehlerDienst + 1;
					}
					if (id1 > Integer.parseInt(this.duty.get(j2 + 1).getId())) {
						dutyList.add(this.duty.get(j2));
						j2 = this.duty.size() - 1;
					}
					
				}	else if(!this.duty.get(j2).getId().endsWith("p")&&this.duty.get(j2+1).getId().endsWith("p")){
					
					String[] string=this.duty.get(j2+1).getId().split("p");
					int id2=Integer.parseInt(string[0]);
					
					if (Integer.parseInt(this.duty.get(j2).getId()) < id2) {
						dutyList.add(this.duty.get(j2));
						zaehlerDienst = zaehlerDienst + 1;
					}
					if (Integer.parseInt(this.duty.get(j2).getId()) > id2) {
						dutyList.add(this.duty.get(j2));
						j2 = this.duty.size() - 1;
					}
					
				}else{


				if (Integer.parseInt(this.duty.get(j2).getId()) < Integer.parseInt(this.duty.get(j2 + 1).getId())) {
					dutyList.add(this.duty.get(j2));
					zaehlerDienst = zaehlerDienst + 1;
				}
				if (Integer.parseInt(this.duty.get(j2).getId()) > Integer.parseInt(this.duty.get(j2 + 1).getId())) {
					dutyList.add(this.duty.get(j2));
					j2 = this.duty.size() - 1;
				}}
				if (j2 == this.duty.size() - 2) {
					dutyList.add(this.duty.get(j2 + 1));
				}
			} 
			zaehlerDienst = zaehlerDienst + 1;
			Dienstplan dienstplanAdd = new Dienstplan(1, dutyList,
					dutyelementList, getFahrplanzugehoerigkeitDienstPlan(i));
			dienstplanliste.add(dienstplanAdd);
		}
		/**
		 * WICHTIG!!!! Es muss noch die FahrplanID ausgelesen werden. DB
		 * VerknÃƒÂ¼pfung!!!!
		 */
		return dienstplanliste;
	}
	
	public int getFahrplanzugehoerigkeitUmlaufplan(int umlaufplanID){
		int fahrplanID=0;
		
		DBConnection db = new DBConnection();
		db.initDBConnection();
		// Creating a sql query
		try {
			stmt = db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT * FROM Umlaufplan WHERE ID="+umlaufplanID);
			fahrplanID=rest1.getInt("FahrplanID");
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fahrplanID;
	}
	
	public int getFahrplanzugehoerigkeitDienstPlan(int dienstplanID){
		int fahrplanID=0;
		
		DBConnection db = new DBConnection();
		db.initDBConnection();
		// Creating a sql query
		try {
			stmt = db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT * FROM Umlaufplan WHERE ID="+dienstplanID);
			fahrplanID=rest1.getInt("FahrplanID");
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fahrplanID;
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
}