package sv.creation.adress.database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import sv.creation.adress.model.Block;
import sv.creation.adress.model.Blockelement;
import sv.creation.adress.model.Days;
import sv.creation.adress.model.Deadruntime;
import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Duty;
import sv.creation.adress.model.Dutyelement;
import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.model.Line;
import sv.creation.adress.model.Reliefpoint;
import sv.creation.adress.model.ServiceJourney;
import sv.creation.adress.model.Stoppoint;
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
	ArrayList<VehicleType> vehType = new ArrayList<VehicleType>();
	ArrayList<VehicleTypeGroup> vehTypeGroup = new ArrayList<VehicleTypeGroup>();
	ArrayList<VehicleTypeToVehicleTypeGroup> vehTypeToVehTypeGroup = new ArrayList<VehicleTypeToVehicleTypeGroup>();
	ArrayList<VehicleCapToStoppoint> vehCapToStoppoint = new ArrayList<VehicleCapToStoppoint>();
	ArrayList<ServiceJourney> serviceJourney = new ArrayList<ServiceJourney>();
	ArrayList<Deadruntime> deadruntime = new ArrayList<Deadruntime>();
	ArrayList<Reliefpoint> reliefpoint = new ArrayList<Reliefpoint>();
	ArrayList<Days> days = new ArrayList<Days>();
	ArrayList<Transfertime> transfertime = new ArrayList<Transfertime>();
	// ********************************
	// ****** Plan objects *******
	// ********************************
	Umlaufplan umlaufplan;
	Dienstplan dienstplan;
	Fahrplan fahrplan;
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
	
	// ***********************************************************************************************
	// ****** In this method both array lists will be integrated in an
	// fahrplan object. *******
	// ****** This method returns an completely object of a fahrplan, which
	// can be modified. ******* *******
	// ***********************************************************************************************
	public ArrayList<Fahrplan> createFahrplanObject() {
		createStoppoint();
		createLine();
		createVehType();
		createVehTypeGroup();
		createVehTypeToVehTypeGroup();
		createVehCapToStoppoint();
		createServiceJourney();
		createDeadruntime();
		createReliefpoint();
		createDays();
		createTransfertime();
		// Anzahl der Fahrplaene wird ausgelesen
		// Strukturvariablen
		int anzahlPlan = 1;
		int zaehlerFahrt = 0;
		for (int i = 0; i < serviceJourney.size(); i++) {
			if (i >= 1
					&& serviceJourney.get(i).getFahrplanID() > serviceJourney.get(
							i - 1).getFahrplanID()) {
				anzahlPlan++;
			}
		}
		// Fahrplanliste wird erzeugt
		for (int i = 1; i <= anzahlPlan; i++) {
			ArrayList<ServiceJourney> serviceJourneyList = new ArrayList<ServiceJourney>();
			ArrayList<Stoppoint> stoppointList = new ArrayList<Stoppoint>();
			ArrayList<Line> lineList = new ArrayList<Line>();
			ArrayList<VehicleType> vehTypeList = new ArrayList<VehicleType>();
			ArrayList<VehicleTypeGroup> vehTypeGroupList = new ArrayList<VehicleTypeGroup>();
			ArrayList<VehicleTypeToVehicleTypeGroup> vehTypeToVehTypeGroupList = new ArrayList<VehicleTypeToVehicleTypeGroup>();
			ArrayList<VehicleCapToStoppoint> vehCapToStoppointList = new ArrayList<VehicleCapToStoppoint>();
			ArrayList<Deadruntime> deadruntimeList = new ArrayList<Deadruntime>();
			ArrayList<Reliefpoint> reliefpointList = new ArrayList<Reliefpoint>();
			ArrayList<Transfertime> transfertimeList = new ArrayList<Transfertime>();
			
			for (int j = 0; j < serviceJourney.size(); j++) {
				if (this.serviceJourney.get(j).getFahrplanID() == i) {
					serviceJourneyList.add(serviceJourney.get(j));
				}
			}
			for (int j = 0; j < stoppoint.size(); j++) {
				if(this.stoppoint.get(j).getFahrplanID() == i){
					stoppointList.add(stoppoint.get(j));
				}
			}
			for (int j = 0; j < line.size(); j++) {
				if(this.line.get(j).getFahrplanID() == i){
					lineList.add(line.get(j));
				}		
			}
			for (int j = 0; j < vehType.size(); j++) {
				if(this.vehType.get(j).getFahrplanID() == i){
					vehTypeList.add(vehType.get(j));
				}
			} 
			for (int j = 0; j < vehTypeGroup.size(); j++) {
				if(this.vehTypeGroup.get(j).getFahrplanID() == i){
					vehTypeGroupList.add(vehTypeGroup.get(j));
				}
			}
			for (int j = 0; j < vehTypeToVehTypeGroup.size(); j++) {
				if(this.vehTypeToVehTypeGroup.get(j).getFahrplanID() == i){
					vehTypeToVehTypeGroupList.add(vehTypeToVehTypeGroup.get(j));
				}
			}
			for (int j = 0; j < vehCapToStoppoint.size(); j++) {
				if(vehCapToStoppoint.get(j).getFahrplanID() == i){
					vehCapToStoppointList.add(vehCapToStoppoint.get(j));
				}
			}
			for (int j = 0; j < deadruntime.size(); j++) {
				if(deadruntime.get(j).getFahrplanID() == i){
					deadruntimeList.add(deadruntime.get(j));
				}
			}
			for (int j = 0; j < reliefpoint.size(); j++) {
				if(reliefpoint.get(j).getFahrplanID() == i){
					reliefpointList.add(reliefpoint.get(j));
				}
			}
			for (int j = 0; j < transfertime.size(); j++) {
				if(transfertime.get(j).getFahrplanID() == i){
					transfertimeList.add(transfertime.get(j));
				}
			}
			Fahrplan fahrplanAdd = new Fahrplan(stoppointList ,lineList,vehTypeList,vehTypeGroupList,vehTypeToVehTypeGroupList,vehCapToStoppointList,serviceJourneyList,deadruntimeList,reliefpointList,transfertimeList);
			fahrplanliste.add(fahrplanAdd);
		}
		
		/**
		 * WICHTIG!!!! Es muss noch die FahrplanID ausgelesen werden. DB
		 * VerknÃƒÂ¼pfung!!!!
		 */
		return fahrplanliste;
	}
	 
	private void createTransfertime() {
		
		DBConnection db = new DBConnection();
		db.initDBConnection();
		// Creating a sql query
		try {
			stmt = db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT  DISTINCT * FROM Transfertime");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int fromStopID = Integer.parseInt(rest1.getString("FromStopID"));
				int toStopID = Integer.parseInt(rest1.getString("ToStopID"));
				String fromTime = rest1.getString("FromTime");
				String toTime = rest1.getString("ToTime");
				int runtime = Integer.parseInt(rest1.getString("Runtime"));
				int fahrplanID = Integer.parseInt(rest1.getString("FahrplanID"));
				transfertime.add(new Transfertime(fromStopID,toStopID,fromTime,toTime,runtime, fahrplanID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void createDays() {

		DBConnection db = new DBConnection();
		db.initDBConnection();
		// Creating a sql query
		try {
			stmt = db.getConnection().createStatement();
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
				if(rest1.getString("d6")!=null){
				d6 = Integer.parseInt(rest1.getString("d6"));
				}
				int d7=0;
				if(rest1.getString("d7")!=null){
					d7 = Integer.parseInt(rest1.getString("d7"));
					};
				days.add(new Days(tripID,d1,d2,d3,d4,d5,d6,d7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void createReliefpoint() {
		
		DBConnection db = new DBConnection();
		db.initDBConnection();
		// Creating a sql query
		try {
			stmt = db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT  DISTINCT * FROM Reliefpoint");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int reliefpointID = Integer.parseInt(rest1.getString("ReliefpointID"));
				int serviceJourneyID = Integer.parseInt(rest1.getString("ServiceJourneyID"));
				int stoppointID = Integer.parseInt(rest1.getString("StoppointID"));
				String stopTime = rest1.getString("StopTime");
				int fahrplanID = Integer.parseInt(rest1.getString("FahrplanID"));
				reliefpoint.add(new Reliefpoint(reliefpointID,serviceJourneyID,stoppointID,stopTime, fahrplanID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void createDeadruntime() {
		
		DBConnection db = new DBConnection();
		db.initDBConnection();
		// Creating a sql query
		try {
			stmt = db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT  DISTINCT * FROM Deadruntime");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int fromStopID = Integer.parseInt(rest1.getString("FromStopID"));
				int toStopID = Integer.parseInt(rest1.getString("ToStopID"));
				String fromTime = rest1.getString("FromTime");
				String toTime = rest1.getString("ToTime");
				int distance = Integer.parseInt(rest1.getString("Distance"));
				int runtime = Integer.parseInt(rest1.getString("Runtime"));
				int fahrplanID = Integer.parseInt(rest1.getString("FahrplanID"));
				deadruntime.add(new Deadruntime(fromStopID,toStopID,fromTime,toTime,distance,runtime,fahrplanID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void createServiceJourney() {
		
		DBConnection db = new DBConnection();
		db.initDBConnection();
		// Creating a sql query
		try {
			stmt = db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT  DISTINCT * FROM ServiceJourney");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int serviceJourneyID = Integer.parseInt(rest1.getString("ServiceJourneyID"));
				int lineID = Integer.parseInt(rest1.getString("LineID"));
				int fromStopID = Integer.parseInt(rest1.getString("FromStopID"));
				int toStopID = Integer.parseInt(rest1.getString("ToStopID"));
				String depTime = rest1.getString("DepTime");
				String arrTime = rest1.getString("ArrTime");
				int minAheadTime = Integer.parseInt(rest1.getString("MinAheadTime"));
				int minLayoverTime = Integer.parseInt(rest1.getString("MinLayoverTime"));
				int vehTypeGroupID = Integer.parseInt(rest1.getString("VehTypeGroupID"));
				int maxShiftBackwardSeconds = Integer.parseInt(rest1.getString("MaxShiftBackwardSeconds"));
				int maxShiftForwardSeconds = Integer.parseInt(rest1.getString("MaxShiftForwardSeconds"));
				int fromStopBreakFacility = Integer.parseInt(rest1.getString("FromStopBreakFacility"));
				int toStopBreakFacility = Integer.parseInt(rest1.getString("ToStopBreakFacility"));
				String code = rest1.getString("Code");
				int fahrplanID=Integer.parseInt(rest1.getString("FahrplanID"));
				serviceJourney.add(new ServiceJourney(serviceJourneyID,lineID,fromStopID,toStopID,depTime,arrTime,minAheadTime,minLayoverTime,vehTypeGroupID,maxShiftBackwardSeconds,maxShiftForwardSeconds,fromStopBreakFacility,toStopBreakFacility,code, fahrplanID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void createVehCapToStoppoint() {
		
		DBConnection db = new DBConnection();
		db.initDBConnection();
		// Creating a sql query
		try {
			stmt = db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT  DISTINCT * FROM VehicleCapToStoppoint");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int vehTypeID = Integer.parseInt(rest1.getString("VehTypeID"));
				int stoppointID = Integer.parseInt(rest1.getString("StoppointID"));
				int min = Integer.parseInt(rest1.getString("Min"));
				int max = Integer.parseInt(rest1.getString("Max"));
				int fahrplanID = Integer.parseInt(rest1.getString("FahrplanID"));
				vehCapToStoppoint.add(new VehicleCapToStoppoint(vehTypeID,stoppointID,min,max, fahrplanID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void createVehTypeToVehTypeGroup() {
	
		DBConnection db = new DBConnection();
		db.initDBConnection();
		// Creating a sql query
		try {
			stmt = db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT  DISTINCT * FROM VehicleTypeToVehicleTypeGroup");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int vehTypeGroupID = Integer.parseInt(rest1.getString("VehTypeGroupID"));
				int vehTypeID = Integer.parseInt(rest1.getString("VehTypeID"));
				int fahrplanID = Integer.parseInt(rest1.getString("FahrplanID"));
				vehTypeToVehTypeGroup.add(new VehicleTypeToVehicleTypeGroup(vehTypeID,vehTypeGroupID, fahrplanID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void createVehTypeGroup() {
		
		DBConnection db = new DBConnection();
		db.initDBConnection();
		// Creating a sql query
		try {
			stmt = db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT  DISTINCT * FROM VehicleTypeGroup");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int vehTypeGroupID = Integer.parseInt(rest1.getString("VehicleTypeGroupID"));
				int code = Integer.parseInt(rest1.getString("Code"));
				String name = rest1.getString("Name");
				int fahrplanID = Integer.parseInt(rest1.getString("FahrplanID"));
				vehTypeGroup.add(new VehicleTypeGroup(vehTypeGroupID,code, name, fahrplanID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		

	private void createVehType() {
	
		DBConnection db = new DBConnection();
		db.initDBConnection();
		// Creating a sql query
		try {
			stmt = db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT  DISTINCT * FROM VehicleType");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int vehTypeID = Integer.parseInt(rest1.getString("VehicleTypeID"));
				int code = Integer.parseInt(rest1.getString("Code"));
				int name = Integer.parseInt(rest1.getString("Name"));
				int vehCost = Integer.parseInt(rest1.getString("VehCost"));
				int kmCost = Integer.parseInt(rest1.getString("KmCost"));
				int hourCost = Integer.parseInt(rest1.getString("HourCost"));
				int capacity = Integer.parseInt(rest1.getString("Capacity"));
				int fahrplanID = Integer.parseInt(rest1.getString("FahrplanID"));
				vehType.add(new VehicleType(vehTypeID,code, name,vehCost,kmCost,hourCost,capacity, fahrplanID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void createLine() {
		
		DBConnection db = new DBConnection();
		db.initDBConnection();
		// Creating a sql query
		try {
			stmt = db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT  DISTINCT * FROM Line");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int lineID = Integer.parseInt(rest1.getString("LineID"));
				int code = Integer.parseInt(rest1.getString("Code"));
				String name = rest1.getString("Name");
				int fahrplanID = Integer.parseInt(rest1.getString("FahrplanID"));
				line.add(new Line(lineID,code, name, fahrplanID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void createStoppoint() {
		
		DBConnection db = new DBConnection();
		db.initDBConnection();
		// Creating a sql query
		try {
			stmt = db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT  DISTINCT * FROM Stoppoint");
			// All resulted datasets of the sql query will be added to the block
			// array list
			while (rest1.next()) {
				int stoppointID = Integer.parseInt(rest1.getString("StoppointID"));
				int code = Integer.parseInt(rest1.getString("Code"));
				String name = rest1.getString("Name");
				int fahrplanID = Integer.parseInt(rest1.getString("FahrplanID"));
				stoppoint.add(new Stoppoint(stoppointID,code, name, fahrplanID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	

	public boolean databaseIsEmpty(){
		
		boolean isEmpty=false;
		
		DBConnection db = new DBConnection();
		db.initDBConnection();
		
		ArrayList <Integer> id=new ArrayList<Integer>();
		
		// Creating a sql query
		try {
			stmt = db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT * FROM Fahrplan;");
			while(rest1.next()){
				id.add(Integer.parseInt(rest1.getString("ID")));	
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(id.isEmpty()){
			isEmpty=true;
		}
		return isEmpty;
	}
	
	public boolean umlaufplanIsEmpty() {
		boolean isEmpty=false;
		
		DBConnection db = new DBConnection();
		db.initDBConnection();
		
		ArrayList <Integer> id=new ArrayList<Integer>();
		
		// Creating a sql query
		try {
			stmt = db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT * FROM Umlaufplan;");
			while(rest1.next()){
				id.add(Integer.parseInt(rest1.getString("ID")));	
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(id.isEmpty()){
			isEmpty=true;
		}
		return isEmpty;
	}
	
	public boolean dienstplanIsEmpty() {
		boolean isEmpty=false;
		
		DBConnection db = new DBConnection();
		db.initDBConnection();
		
		ArrayList <Integer> dienstplan=new ArrayList<Integer>();
		
		// Creating a sql query
		try {
			
			stmt = db.getConnection().createStatement();
			ResultSet rest1 = stmt.executeQuery("SELECT * FROM Dienstplan;");
			while(rest1.next()){
				dienstplan.add(Integer.parseInt(rest1.getString("ID")));	
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(dienstplan.isEmpty()){
			isEmpty=true;
		}
		return isEmpty;
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
	
	
}