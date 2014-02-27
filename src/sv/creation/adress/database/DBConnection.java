package sv.creation.adress.database;

/**
 * DESCRIPTION:
 * This class creates the database, manages the database connection and fills the DB-tables in the process of importing new plans 
 * from txt-files into the DB.
 * 
 * CONTENTS:
 * 1. initializing & closing DB connection
 * 2. creating tables in DB
 * 3. filling tables with data from txt files using stringsplitter-class
 * 
 * TODO:
 * generell mï¿½ssten import,  initale Erstellung der Relationen und  DB-Verbindung noch getrennt werden oder?
 * Zeile 36, 470, 516, 580, 630, 656
 */

import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import sv.creation.adress.util.StringSplitter;

public class DBConnection {
	

	public static DBConnection instance = null;
	private static Connection connection;
	// Path where a empty database file is created
	public static final String DB_PATH = System.getProperty("user.home") + "/"
			+ "PlanB_DB.db";
	File file=new File(DB_PATH);
	private boolean fahrplanVorhanden=false;
	private boolean diensttypenVorhanden=false;
	private boolean umlaufplanVorhanden=false;
	//loading database drivers
	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.err.println("Treiber konnte nicht geladen werden.");
			e.printStackTrace();
		}
	}
 
	
	// Singleton
	public static DBConnection getInstance() {
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}
	
	public DBConnection(){
	}
	//Create connection to database
	
	public void initDBConnection() {

		try {
			if (connection != null)
				return;
			System.out.println("Creating DB Connection...");
			connection = DriverManager.getConnection("jdbc:sqlite:" + file);
			Statement stmt=connection.createStatement();
			stmt.executeUpdate("PRAGMA foreign_keys = ON;");
			if (!connection.isClosed())
				System.out.println("...Connection established!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	//close connection to database
	
	public void closeConnection() {

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					if (!connection.isClosed() && connection != null) {
						connection.close();
						if (connection.isClosed())
							System.out
									.println("Connection to Database closed!");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}

//*****************************************************************************************************************************************
//Create initial tables for schedules in database******************************************************************************************
//*****************************************************************************************************************************************
	
	public void createTables() {
		
		if(!file.exists()){
		try {
			initDBConnection();
			//create db connection
			Statement stmnt = connection.createStatement();
			//******************************
			//Checks if table exists or not
			//******************************
			//*********************************************************************************************************************************************
			//Tables for schedules (stoppoints, lines, vehicles etc.)**************************************************************************************
			//*********************************************************************************************************************************************
			
			//table for schedule
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Fahrplan (ID INTEGER PRIMARY KEY AUTOINCREMENT, Bezeichnung VARCHAR(255), Name VARCHAR(100), VersNr VARCHAR(30), FileType VARCHAR(30), Datum DATE);");
			
			//table for stoppoints
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Stoppoint (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																	+ "StoppointID INTEGER NOT NULL, "
																	+ "Code INTEGER NOT NULL, "
																	+ "Name INTEGER NOT NULL, "
																	+ "FahrplanID INTEGER NOT NULL, "
																	+ "FOREIGN KEY (FahrplanID) REFERENCES Fahrplan(ID) ON UPDATE CASCADE ON DELETE CASCADE);");
			
			//table for lines
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Line (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
															   + "LineID INTEGER NOT NULL, "
															   + "Code INTEGER NOT NULL, "
															   + "Name INTEGER NOT NULL, "
															   + "FahrplanID INTEGER NOT NULL, "
															   + "FOREIGN KEY (FahrplanID) REFERENCES Fahrplan(ID) ON UPDATE CASCADE ON DELETE CASCADE);");
			
			//table for line bundles
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Linebundle (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																	+ "LinebundleID INTEGER NOT NULL, "   													
																	+ "LineID INTEGER NOT NULL, "
//																	+ "LineID_txt INTEGER NOT NULL, "
																	+ "FahrplanID INTEGER NOT NULL, "
																	+ "FOREIGN KEY (FahrplanID) REFERENCES Fahrplan(ID) ON UPDATE CASCADE ON DELETE CASCADE);");
			
			//table for different vehicle types
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS VehicleType (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																	  + "VehicleTypeID INTEGER NOT NULL, "
																	  + "Code INTEGER NOT NULL, "
																	  + "Name INTEGER NOT NULL, "
																	  + "VehCost INTEGER NOT NULL, "
																	  + "KmCost INTEGER NOT NULL, "
																	  + "HourCost INTEGER NOT NULL, "
																	  + "Capacity INTEGER NOT NULL, "
																	  + "FahrplanID INTEGER NOT NULL, "
																	  + "FOREIGN KEY (FahrplanID) REFERENCES Fahrplan(ID) ON UPDATE CASCADE ON DELETE CASCADE);");
			
			//table for vehicle type groups
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS VehicleTypeGroup (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																		   + "VehicleTypeGroupID INTEGER NOT NULL, "
																		   + "Code INTEGER NOT NULL, "
																		   + "Name INTEGER NOT NULL, "
																		   + "FahrplanID INTEGER NOT NULL, "
																		   + "FOREIGN KEY (FahrplanID) REFERENCES Fahrplan(ID) ON UPDATE CASCADE ON DELETE CASCADE);");
			
			//matching of vehicle types to vehicle type groups
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS VehicleTypeToVehicleTypeGroup (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																						+ "VehTypeID INTEGER NOT NULL, "
//																						+ "VehTypeID_txt INTEGER NOT NULL, "
																						+ "VehTypeGroupID INTEGER NOT NULL, "
//																						+ "VehTypeGroupID_txt INTEGER NOT NULL, "
																						+ "FahrplanID INTEGER NOT NULL, "
																						+ "FOREIGN KEY (VehTypeID) REFERENCES VehicleType(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
																						+ "FOREIGN KEY (VehTypeGroupID) REFERENCES VehicleTypeGroup(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
																						+ "FOREIGN KEY (FahrplanID) REFERENCES Fahrplan(ID) ON UPDATE CASCADE ON DELETE CASCADE);");
			
			//tables for capacities of depots according to vehicle type
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS VehicleCapToStoppoint (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																				+ "VehTypeID INTEGER NOT NULL, "
//																				+ "VehTypeID_txt INTEGER NOT NULL, "
																				+ "StoppointID INTEGER NOT NULL, "
//																				+ "StoppointID_txt INTEGER NOT NULL, "
																				+ "Min INTEGER NOT NULL, "
																				+ "Max INTEGER NOT NULL, "
																				+ "FahrplanID INTEGER NOT NULL, "
																				+ "FOREIGN KEY (VehTypeID) REFERENCES VehicleType(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
																				+ "FOREIGN KEY (FahrplanID) REFERENCES Fahrplan(ID) ON UPDATE CASCADE ON DELETE CASCADE);");
			
			//table for service journeys
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS ServiceJourney (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																		 + "ServiceJourneyID INTEGER NOT NULL, "
																		 + "LineID INTEGER NOT NULL, "
//																		 + "LineID_txt INTEGER NOT NULL, "
																		 + "FromStopID INTEGER NOT NULL, "
//																		 + "FromStopID_txt INTEGER NOT NULL, "
																		 + "ToStopID INTEGER NOT NULL, "
//																		 + "ToStopID_txt INTEGER NOT NULL, "
																		 + "DepTime VARCHAR(10) NOT NULL, "
																		 + "ArrTime VARCHAR(10) NOT NULL, "
																		 + "MinAheadTime INTEGER NOT NULL, "
																		 + "MinLayoverTime INTEGER NOT NULL, "
																		 + "VehTypeGroupID INTEGER NOT NULL, "
//																		 + "VehTypeGroupID_txt INTEGER NOT NULL, "
																		 + "MaxShiftBackwardSeconds INTEGER NOT NULL, "
																		 + "MaxShiftForwardSeconds INTEGER NOT NULL, "
																		 + "FromStopBreakFacility INTEGER NOT NULL, "
																		 + "ToStopBreakFacility INTEGER NOT NULL, "
																		 + "Code INTEGER,"
																		 + "FahrplanID INTEGER NOT NULL, "
																		 + "FOREIGN KEY (LineID) REFERENCES Line(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
																		 + "FOREIGN KEY (FromStopID) REFERENCES Stoppoint(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
																		 + "FOREIGN KEY (ToStopID) REFERENCES Stoppoint(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
																		 + "FOREIGN KEY (VehTypeGroupID) REFERENCES VehicleTypeGroup(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
																		 + "FOREIGN KEY (FahrplanID) REFERENCES Fahrplan(ID) ON UPDATE CASCADE ON DELETE CASCADE);");
			
			//table for deadheading journeys depending on the day time
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Deadruntime (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																	  + "FromStopID INTEGER NOT NULL, "
//																	  + "FromStopID_txt INTEGER NOT NULL, "
																	  + "ToStopID INTEGER NOT NULL, "
//																	  + "ToStopID_txt INTEGER NOT NULL, "
																	  + "FromTime VARCHAR(10) NOT NULL, "
																	  + "ToTime VARCHAR(10) NOT NULL, "
																	  + "Distance INTEGER NOT NULL, "
																	  + "RunTime INTEGER NOT NULL, "
																	  + "FahrplanID INTEGER NOT NULL, "
																	  + "FOREIGN KEY (FromStopID) REFERENCES Stoppoint(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
																	  + "FOREIGN KEY (ToStopID) REFERENCES Stoppoint(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
																	  + "FOREIGN KEY (FahrplanID) REFERENCES Fahrplan(ID) ON UPDATE CASCADE ON DELETE CASCADE);");

			//table for stoppoints and respective times which allow for a change of ressources
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Reliefpoint (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																	  + "ReliefpointID INTEGER NOT NULL, "
																	  + "ServiceJourneyID INTEGER NOT NULL, "
//																	  + "ServiceJourneyID_txt INTEGER NOT NULL, "
																	  + "StoppointID INTEGER NOT NULL, "
//																	  + "StoppointID_txt INTEGER NOT NULL, "
																	  + "StopTime VARCHAR(30), "
																	  + "FahrplanID INTEGER NOT NULL, "
																 	  + "FOREIGN KEY(ServiceJourneyID) REFERENCES ServiceJourney(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
																	  + "FOREIGN KEY (StoppointID) REFERENCES Stoppoint(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
																	  + "FOREIGN KEY (FahrplanID) REFERENCES Fahrplan(ID) ON UPDATE CASCADE ON DELETE CASCADE);");			
			
			//table for days in which the respective service journey is valid
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Days (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
															   + "TRIPID INTEGER NOT NULL, "
//															   + "TRIPID_txt INTEGER NOT NULL, "
															   + "d1 INTEGER NOT NULL, "
															   + "d2 INTEGER NOT NULL, "
															   + "d3 INTEGER NOT NULL, "
															   + "d4 INTEGER NOT NULL, "
															   + "d5 INTEGER NOT NULL, "
															   + "d6 INTEGER, "
															   + "d7 INTEGER, "
															   + "FahrplanID INTEGER NOT NULL, "
															   + "FOREIGN KEY(TRIPID) REFERENCES ServiceJourney(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
															   + "FOREIGN KEY (FahrplanID) REFERENCES Fahrplan(ID) ON UPDATE CASCADE ON DELETE CASCADE);");
			//weekdays
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Day (dayID INTEGER NOT NULL PRIMARY KEY, "
														      + "Name VARCHAR(10));");
			//Transfertime
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Transfertime (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																			+ "FromStopID INTEGER, "
//																			+ "FromStopID_txt INTEGER, "
																			+ "ToStopID INTEGER, "
//																			+ "ToStopID_txt INTEGER, "
																			+ "FromTime VARCHAR(30) NOT NULL, "
																			+ "ToTime VARCHAR(30) NOT NULL, "
																			+ "Runtime INTEGER NOT NULL, "
																			+ "FahrplanID INTEGER NOT NULL, "
																			+ "FOREIGN KEY (FromStopID) REFERENCES Stoppoint(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
																			+ "FOREIGN KEY (ToStopID) REFERENCES Stoppoint(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
																			+ "FOREIGN KEY (FahrplanID) REFERENCES Fahrplan(ID) ON UPDATE CASCADE ON DELETE CASCADE);");
			
			//****************************************************************************************************************************
			//*****Tables for vehicle schedules*******************************************************************************************
			//****************************************************************************************************************************	
			
			//table for tour roster
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Umlaufplan (ID INTEGER PRIMARY KEY AUTOINCREMENT, Bezeichnung VARCHAR(255), Name VARCHAR(100), FahrplanID INTEGER, DayID INTEGER, Datum DATE, "
																		+ "FOREIGN KEY (FahrplanID) REFERENCES Fahrplan(ID) ON UPDATE CASCADE ON DELETE CASCADE);");
			
			//table for tours
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Block (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																+ "BlockID INTEGER NOT NULL, "
																+ "VehTypeID INTEGER NOT NULL, "
//																+ "VehTypeID_txt INTEGER NOT NULL, "
																+ "DepotID INTEGER NOT NULL, "
//																+ "DepotID_txt INTEGER NOT NULL, "
																+ "UmlaufplanID INTEGER NOT NULL, "
																+ "FOREIGN KEY (VehTypeID) REFERENCES VehicleType(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
																+ "FOREIGN KEY (DepotID) REFERENCES Stoppoint(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
																+ "FOREIGN KEY (UmlaufplanID) REFERENCES Umlaufplan(ID) ON DELETE CASCADE);");
			
			//table for tour elements (journeys)
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Blockelement (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																	   + "BlockID INTEGER NOT NULL, "
//																	   + "BlockID_txt INTEGER NOT NULL, "
																	   + "ServiceJourneyID VARCHAR(30) NOT NULL, "
//																	   + "ServiceJourneyID_txt VARCHAR(30) NOT NULL, "
																	   + "ElementType INTEGER NOT NULL, "
																	   + "UmlaufplanID INTEGER NOT NULL, "
																	   + "MatchingPos INTEGER NOT NULL, "
																	   + "FOREIGN KEY (BlockID) REFERENCES Block(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
																	   + "FOREIGN KEY (ServiceJourneyID) REFERENCES ServiceJourney(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
																	   + "FOREIGN KEY (UmlaufplanID) REFERENCES Umlaufplan(ID) ON DELETE CASCADE);");
					
			//table for specific tour elements (left over from initial solution for the tour planning problem)
			//e.g. journeys from or to depots, waiting times etc.
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS ExceptionalBlockelement (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																					+ "BlockID INTEGER NOT NULL, "
//																					+ "BlockID_txt VARCHAR(30) NOT NULL, "
																					+ "ServiceJourneyID VARCHAR(30) NOT NULL, "
//																					+ "FromStopID_txt INTEGER NOT NULL,"
																					+ "FromStopID INTEGER NOT NULL,"
//																					+ "ToStopID_txt INTEGER NOT NULL,"
																					+ "ToStopID INTEGER NOT NULL,"
																					+ "DepTime VARCHAR(30) NOT NULL,"
																					+ "ArrTime VARCHAR(30) NOT NULL,"
																					+ "ElementType INTEGER NOT NULL, "
																					+ "UmlaufplanID INTEGER NOT NULL, "
																					+ "MatchingPos INTEGER NOT NULL, "
			   																		+ "FOREIGN KEY (BlockID) REFERENCES Block(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
			   																		+ "FOREIGN KEY (FromStopID) REFERENCES Stoppoint(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
			   																		+ "FOREIGN KEY (ToStopID) REFERENCES Stoppoint(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
			   																		+ "FOREIGN KEY (UmlaufplanID) REFERENCES Umlaufplan(ID) ON DELETE CASCADE ON UPDATE CASCADE);");
			
			
		
			
			//*********************************************************************************************************************************************
			//Tables for Dutytypes*************************************************************************************************************************
			//*********************************************************************************************************************************************
			//duty types (work time rules)
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Dutytype (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																   + "Name VARCHAR(30) NOT NULL, "
																   + "StartTimeMin VARCHAR(30) NOT NULL, "
																   + "StartTimeMax VARCHAR(30) NOT NULL, "
																   + "EndTimeMin VARCHAR(30) NOT NULL, "
																   + "EndTimeMax VARCHAR(30) NOT NULL, "
																   + "SignOnTime VARCHAR(30) NOT NULL, "
																   + "SignOffTime VARCHAR(30) NOT NULL, "
																   + "DurationMin VARCHAR(30) NOT NULL, "
																   + "DurationMax VARCHAR(30) NOT NULL, "
																   + "WorkingTimeTotalMin VARCHAR(30) NOT NULL, "
																   + "WorkingTimeTotalMax VARCHAR(30) NOT NULL, "
																   + "WorkingTimeBeforeBreakMin VARCHAR(30) NOT NULL, "
																   + "WorkingTimeWithoutBreakMin VARCHAR(30) NOT NULL, "
																   + "WorkingTimeAfterLastBreakMin VARCHAR(30) NOT NULL, "
																   + "DrivingTimeTotalMin VARCHAR(30) NOT NULL, "
																   + "DrivingTimeTotalMax VARCHAR(30) NOT NULL, "
																   + "DrivingTimeWithoutBreakMin VARCHAR(30) NOT NULL, "
																   + "DrivingTimeWithoutBreakMax VARCHAR(30) NOT NULL, "
																   + "DrivingTimeWithoutBreakMinInterruptionTime VARCHAR(30) NOT NULL, "
																   + "DrivingTimeBeforeFirstBreakMin VARCHAR(30) NOT NULL, "
																   + "BreakType VARCHAR(30) NOT NULL, "
																   + "BreakTimeTotalMin VARCHAR(30) NOT NULL, "
																   + "BreakTimeTotalMax VARCHAR(30) NOT NULL, "
																   + "BreakTimeMin VARCHAR(30) NOT NULL, "
																   + "BreakTimeMax VARCHAR(30) NOT NULL, "
																   + "PieceCountMin INTEGER NOT NULL, "
																   + "PieceCountMax INTEGER NOT NULL, "
																   + "AllowedCumulatedWorkingTimeMax VARCHAR(30) NOT NULL, "
																   + "DutyFixCost FLOAT(6,2) NOT NULL, "
																   + "IsWorkRateConsidered INTEGER NOT NULL, "
																   + "IsBreakRateConsidered INTEGER NOT NULL, "
																   + "DutyCostPerMinute FLOAT(6,2) NOT NULL, "
																   + "IsVehicleChangeAllowedDuringBreak INTEGER NOT NULL, "
																   + "BreakTimeAllowedStarts VARCHAR(30) NOT NULL, "
																   + "BreakTimeAllowedEnds VARCHAR(30) NOT NULL, "
																   + "FahrplanID INTEGER NOT NULL, "
																   + "WorkingTimeWithoutBreakMax VARCHAR(30) NOT NULL, "
																   + "FOREIGN KEY (FahrplanID) REFERENCES Fahrplan(ID) ON UPDATE CASCADE ON DELETE CASCADE);");

			//*********************************************************************************************************************************************
			//Tables for crew schedules********************************************************************************************************************
			//*********************************************************************************************************************************************
			
			//table for duty roster
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Dienstplan (ID INTEGER PRIMARY KEY AUTOINCREMENT, Bezeichnung VARCHAR(255), Name VARCHAR(100), FahrplanID INTEGER NOT NULL, UmlaufplanID INTEGER NOT NULL, DayID INTEGER, Datum DATE, "
																		+ "FOREIGN KEY (FahrplanID) REFERENCES Fahrplan(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
																		+ "FOREIGN KEY (UmlaufplanID) REFERENCES Umlaufplan(ID) ON UPDATE CASCADE ON DELETE CASCADE);");
			
			//table for duty
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Duty (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
															   + "DutyID VARCHAR(30) NOT NULL, "
															   + "DutyTypeID VARCHAR(50) NOT NULL, "
//															   + "DutyTypeID_txt VARCHAR(50) NOT NULL, "
															   + "DienstplanID INTERGER NOT NULL, "
															   + "FOREIGN KEY (DutyTypeID) REFERENCES Dutytype(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
															   + "FOREIGN KEY (DienstplanID) REFERENCES Dienstplan(ID) ON UPDATE CASCADE ON DELETE CASCADE);");
			
			//table for specific tour elements which are NOT SERVICE JOURNEYS! so time and stop point infos can not come from the servicejourney relation
			//e.g. journeys from or to depots, waiting times etc.
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS ExceptionalDutyelement (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																					+ "DutyID VARCHAR(30) , "
//																					+ "DutyID_txt INTEGER NOT NULL, "
																					+ "BlockID INTEGER , "
																					+ "ServiceJourneyID VARCHAR(30) , "
																					+ "FromStopID INTEGER ,"
																					+ "ToStopID INTEGER ,"
																					+ "DepTime VARCHAR(30) ,"
																					+ "ArrTime VARCHAR(30) ,"
																					+ "ElementType INTEGER, "
																					+ "DienstplanID INTEGER NOT NULL,"
																					+ "MatchingPos INTEGER NOT NULL, "
																					+ "FOREIGN KEY (DutyID) REFERENCES Duty(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
					   																+ "FOREIGN KEY (DienstplanID) REFERENCES Dienstplan(ID) ON UPDATE CASCADE ON DELETE CASCADE);");
			
			//table for duty elements
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Dutyelement (ID INTEGER PRIMARY KEY AUTOINCREMENT,"
																	  + "DutyID INTEGER NOT NULL, "
//																	  + "DutyID_txt INTEGER NOT NULL, "
																	  + "BlockID INTEGER NOT NULL, "
//																	  + "BlockID_txt INTEGER NOT NULL, "
																	  + "ServiceJourneyID VARCHAR(30) NOT NULL, "
//																	  + "ServiceJourneyID_txt VARCHAR(30) NOT NULL, "
																	  + "ElementType INTEGER NOT NULL, "
																	  + "DienstplanID INTEGER NOT NULL, "
																	  + "MatchingPos INTEGER NOT NULL, "
																	  + "FOREIGN KEY (DutyID) REFERENCES Duty(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
//																	  + "FOREIGN KEY (BlockID) REFERENCES Block(ID, UmlaufplanID) ON UPDATE CASCADE ON DELETE CASCADE, "
																	  + "FOREIGN KEY (ServiceJourneyID) REFERENCES ServiceJourney(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
																	  + "FOREIGN KEY (DienstplanID) REFERENCES Dienstplan(ID) ON UPDATE CASCADE ON DELETE CASCADE);");
			
			//*********************************************************************************************************************************
			//Tables for delay scenarios (primary delays)**************************************************************************************
			//*********************************************************************************************************************************			

			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS PrimeDelaySzenario (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																				+ "DutyID VARCHAR(30), "
//																				+ "DutyID_txt VARCHAR(30), "
																				+ "VehicleID VARCHAR(30), "
																				+ "ServiceJourneyID VARCHAR(30) NOT NULL, "
//																				+ "ServiceJourneyID_txt VARCHAR(30) NOT NULL, "
																				+ "DepTime VARCHAR(30) NOT NULL, "
																				+ "Delay INTEGER NOT NULL, "
																				+ "SzenarioID INTEGER NOT NULL, "
																				+ "FOREIGN KEY (ServiceJourneyID) REFERENCES ServiceJourney(ID) ON UPDATE CASCADE ON DELETE CASCADE, "
																				+ "FOREIGN KEY (SzenarioID) REFERENCES Szenario(ID) ON UPDATE CASCADE ON DELETE CASCADE);");
			
			//table szenario
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Szenario (ID INTEGER PRIMARY KEY AUTOINCREMENT, Bezeichnung VARCHAR(30) NOT NULL, FahrplanID INTEGER NOT NULL, Datum DATE NOT NULL, FOREIGN KEY (FahrplanID) REFERENCES Fahrplan(ID) ON UPDATE CASCADE ON DELETE CASCADE);"); 
						
			//table colors
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Colors (ID INTEGER PRIMARY KEY AUTOINCREMENT, Farbe VARCHAR(30) NOT NULL);"); 
			
			

			//***************************
			//tables get filled with data		
			//***************************		
						
			//weekdays 
			ResultSet rest1 = stmnt
					.executeQuery("SELECT  COUNT(*) AS AnzahlEintraege FROM Day;");
			int anzahlEintraege=1;
			while(rest1.next()){
				anzahlEintraege=Integer.parseInt(rest1.getString("AnzahlEintraege"));
			}
			if(anzahlEintraege==0){
			 stmnt.executeUpdate("INSERT INTO Day VALUES('0','Mo');"); 
			  stmnt.executeUpdate("INSERT INTO Day VALUES('1','Di');"); 
			  stmnt.executeUpdate("INSERT INTO Day VALUES('2','Mi');"); 
			  stmnt.executeUpdate("INSERT INTO Day VALUES('3','Do');"); 
			  stmnt.executeUpdate("INSERT INTO Day VALUES('4','Fr');"); 
			  stmnt.executeUpdate("INSERT INTO Day VALUES('5','Sa');"); 
			  stmnt.executeUpdate("INSERT INTO Day VALUES('6','So');"); 
			}
			
			 stmnt.executeUpdate("INSERT INTO Colors(Farbe) VALUES('0x669966ff');");
			 stmnt.executeUpdate("INSERT INTO Colors(Farbe) VALUES('0xff9966ff');");
			 stmnt.executeUpdate("INSERT INTO Colors(Farbe) VALUES('0x334db3ff');");
			 stmnt.executeUpdate("INSERT INTO Colors(Farbe) VALUES('0xffffffff');");
			 stmnt.executeUpdate("INSERT INTO Colors(Farbe) VALUES('0xb3b34dff');");
			 stmnt.executeUpdate("INSERT INTO Colors(Farbe) VALUES('0xe6b34dff');");
			 stmnt.executeUpdate("INSERT INTO Colors(Farbe) VALUES('0x1a1a1aff');");
			 stmnt.executeUpdate("INSERT INTO Colors(Farbe) VALUES('0xffff4dff');");
			 stmnt.executeUpdate("INSERT INTO Colors(Farbe) VALUES('0x80334dff');");
			 stmnt.executeUpdate("INSERT INTO Colors(Farbe) VALUES('0x336666ff');");
			 

		//catch for the whole table creation
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}
	
	//******************************************************************************************************************************************
	//filling the schedule data into the respective tables**************************************************************************************
	//******************************************************************************************************************************************
	
	public void fillFahrplanIntoTables(String filename){
		
		//temporary stringsplitter object that contains the the data from text files in array lists
		StringSplitter ss = StringSplitter.getInstance();
		//invoke stringsplitter method for reading the data in  txt-files
//		ss.readTxtFahrplan();
		
		String fileNameVergleich=filename;
		
		initDBConnection();
		
		  try{ Statement stmnt=connection.createStatement(); 
		  //Fill values in specific tables
		  
		  //schedule name
		  stmnt.executeUpdate("INSERT INTO Fahrplan (Bezeichnung,Name, Datum) VALUES ('"+fileNameVergleich+"','"+fileNameVergleich+"', CURRENT_DATE);");
		  /**
		   * TODO: VersNr. + File Type
		   **/
		  //iterators for getting values from stringsplitter object
		  //stoppoint
		  Iterator<Integer> it = ss.getStopID().iterator(); 
		  Iterator<String> it2 =ss.getStopCode().iterator(); 
		  Iterator<String> it3 =ss.getStopName().iterator();
		  //line
		  Iterator<Integer> it4 =ss.getLineID().iterator(); 
		  Iterator<String> it5 =ss.getLineCode().iterator(); 
		  Iterator<String> it6 = ss.getLineName().iterator();
		  
		  //linebundle
		  Iterator<Integer> it62 = ss.getLinebundleID().iterator();
//		  Iterator<Integer> it63 = ss.getLinebundleline().iterator();
		  
		  Iterator<Integer> it64 = ss.getLinebundleline().iterator();
		  
		  //Vehicletype
		  Iterator<Integer> it7 = ss.getVehicleTypeID().iterator(); 
		  Iterator<String> it8 = ss.getVehicleTypeCode().iterator(); 
		  Iterator<String> it9 = ss.getVehicleTypeName().iterator(); 
		  Iterator<Float> it10 = ss.getVehicleTypeVehCost().iterator();
		  Iterator<Float> it11 = ss.getVehicleTypeKmCost().iterator(); 
		  Iterator<Float> it12 =ss.getVehicleTypeHourCost().iterator(); 
		  Iterator<Integer> it13 =ss.getVehicleTypeCapacity().iterator();
		  //Vehicletypegroup
		  Iterator<Integer> it14 =ss.getVehicleTypeGroupID().iterator(); 
		  Iterator<String> it15 =ss.getVehicleTypeGroupCode().iterator(); 
		  Iterator<String> it16 = ss.getVehicleTypeGroupName().iterator();
		  //Vehicletype to Vehicletypegroup
		  Iterator<Integer> it17 = ss.getVehicleToVehicleTypeGroupVehTypeID().iterator(); 
		  Iterator<Integer> it18 = ss.getVehicleToVehicleTypeGroupVehTypeGroupID().iterator();

		  //Vehiclecap to Stoppoint
		  Iterator<Integer> it19 = ss.getVehicleCapToStopVehTypeID().iterator(); 
		  Iterator<Integer> it20 = ss.getVehicleCapToStopStoppointID().iterator();
		  Iterator<Integer> it21= ss.getVehicleCapToStopMin().iterator(); 
		  Iterator<Integer> it22 =ss.getVehicleCapToStopMax().iterator();

		  //Servicejourney
		  Iterator<Integer> it23 =ss.getServiceJourneyID().iterator(); 
		  Iterator<Integer> it24 =ss.getServiceJourneyLineID().iterator(); 
		  Iterator<Integer> it25 =ss.getServiceJourneyFromStopID().iterator(); 
		  Iterator<Integer> it26 = ss.getServiceJourneyToStopID().iterator(); 
		  Iterator<String> it27 = ss.getServiceJourneyDepTime().iterator(); 
		  Iterator<String> it28 = ss.getServiceJourneyArrTime().iterator(); 
		  Iterator<Integer> it29 = ss.getServiceJourneyMinAheadTime().iterator(); 
		  Iterator<Integer> it30 = ss.getServiceJourneyMinLayoverTime().iterator(); 
		  Iterator<Integer> it31 = ss.getServiceJourneyVehTypeGroupID().iterator();
		  Iterator<Integer> it32 = ss.getServiceJourneyMaxShiftBackwardSeconds().iterator(); 
		  Iterator<Integer> it33 =ss.getServiceJourneyMaxShiftForwardSeconds().iterator(); 
		  Iterator<Integer> it34 =ss.getServiceJourneyFromStopBreakFacility().iterator(); 
		  Iterator<Integer> it35 =ss.getServiceJourneyToStopBreakFacility().iterator(); 
		  Iterator<String> it36 =ss.getServiceJourneyCode().iterator();

		  //Deadruntime
		  Iterator<Integer> it37 = ss.getDeadruntimeFromStopID().iterator();
		  Iterator<Integer> it38 = ss.getDeadruntimeToStopID().iterator(); 
		  Iterator<String> it39 = ss.getDeadruntimeFromTime().iterator(); 
		  Iterator<String> it40 = ss.getDeadruntimeToTime().iterator(); 
		  Iterator<Integer> it41 = ss.getDeadruntimeDistance().iterator(); 
		  Iterator<Integer> it42 = ss.getDeadruntimeRuntime().iterator();
		  

		  //Reliefpoints
		  Iterator<Integer> it43 = ss.getReliefpointID().iterator(); 
		  Iterator<String> it44 =ss.getReliefpointServiceJourneyID().iterator(); 
		  Iterator<Integer> it45 =ss.getReliefpointStoppointID().iterator(); 
		  Iterator<String> it46 =ss.getReliefpointStoptime().iterator(); 
		  

		  //Transfertimes
		  Iterator<Integer> it57 = ss.getWalkruntimeFromStopID().iterator(); 
		  Iterator<Integer> it58 =ss.getWalkruntimeToStopID().iterator(); 
		  Iterator<String> it59 =ss.getWalkruntimeFromTime().iterator(); 
		  Iterator<String> it60 =ss.getWalkruntimeToTime().iterator(); 
		  Iterator<Integer> it61 = ss.getWalkruntimeRuntime().iterator();  
		  
 
		  //days
		  Iterator<Integer> it49 = ss.getTripID().iterator(); 
		  Iterator<Integer> it50 = ss.getDayOne().iterator();
		  Iterator<Integer> it51 = ss.getDayTwo().iterator(); 
		  Iterator<Integer> it52 =ss.getDayThree().iterator(); 
		  Iterator<Integer> it53 =ss.getDayFour().iterator(); 
		  Iterator<Integer> it54 =ss.getDayFive().iterator(); 
		  Iterator<Integer> it55 =ss.getDaySix().iterator(); 
		  Iterator<Integer> it56 =ss.getDaySeven().iterator();
		  

		  //fill Stoppoint
		  while((it.hasNext()&&it2.hasNext()&&it3.hasNext())){
			stmnt.executeUpdate("INSERT INTO Stoppoint (StoppointID, Code, Name, FahrplanID) VALUES('"+it.next()+"','"+it2.next()+"','"+it3.next()+"',(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%'));");
			  }
		  //fill line
		  while((it4.hasNext()&&it5.hasNext()&&it6.hasNext())){
			  stmnt.executeUpdate("INSERT INTO Line (LineID, Code, Name, FahrplanID) VALUES('"+it4.next()+"','"+it5.next()+"','"+it6.next()+"',(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%'));"); 
		  }
		  //fill linebundle
		  while((it62.hasNext()&&it64.hasNext())){
			  stmnt.executeUpdate("INSERT INTO Linebundle (LinebundleID, line, Fahrplan) VALUES ('"+it62.next()+"',"
					  + "(SELECT ID FROM Line WHERE LineID ='"+it64.next()+"'), "
					  + "(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+ss.getFilename()+"%'), "
					  + ");");
		  }
		  //fill Vehicletype
		  while((it7.hasNext()&&it8.hasNext()&&it9.hasNext()&&it10.hasNext()&&it11.hasNext()&&it12.hasNext()&&it13.hasNext())){
			  
			  stmnt.executeUpdate("INSERT INTO VehicleType (VehicleTypeID, Code, Name, VehCost, KmCost, HourCost, Capacity, FahrplanID) VALUES('"+it7.next()+"','"+it8.next()+"','"+it9.next()+"','"+it10.next()+"','"+it11.next()+"','"+it12.next()+"','"+it13.next()+"',(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%'));"); 
		  }
		  //fill vehicletypegroup
		  while((it14.hasNext()&&it15.hasNext()&&it16.hasNext())){
			  stmnt.executeUpdate("INSERT INTO VehicleTypeGroup (VehicleTypeGroupID, Code, Name, FahrplanID) VALUES('"+it14.next()+"','"+it15.next()+"','"+it16.next()+"',(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%'));"); 
		  }
		  //fill vehicletype to vehicletypegroup
		  while((it17.hasNext()&&it18.hasNext())){
			  stmnt.executeUpdate("INSERT INTO VehicleTypeToVehicleTypeGroup (VehTypeID,VehTypeGroupID, FahrplanID) VALUES((SELECT vt.ID FROM VehicleType AS vt WHERE vt.VehicleTypeID='"+it17.next()+"' AND vt.FahrplanID= (SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%')),(SELECT vtg.ID FROM VehicleTypeGroup AS vtg WHERE vtg.VehicleTypeGroupID='"+it18.next()+"'AND vtg.FahrplanID=(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+ss.getFilename()+"%')),(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+ss.getFilename()+"%'))");
					  																																				
		  }
		  //vehiclecap to stoppoint
		  while((it19.hasNext()&&it20.hasNext()&&it21.hasNext()&&it22.hasNext())){
			  stmnt.executeUpdate("INSERT INTO VehicleCapToStoppoint (Min, Max, FahrplanID, VehTypeID, StoppointID) VALUES('"+it21.next()+"','"+it22.next()+"',(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%'), "
					  																																																			+ " (SELECT vt.ID FROM VehicleType AS vt WHERE vt.VehicleTypeID='"+it19.next()+"' AND vt.FahrplanID= (SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%')), "
					  																																																			+ "(SELECT sp.ID FROM Stoppoint AS sp WHERE sp.StoppointID='"+it20.next()+"' AND sp.FahrplanID=(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%')));"); 
		  }
		  //fill Servicejourney
		  while((it23.hasNext()&&it24.hasNext()&&it25.hasNext()&&it26.hasNext()&&it27.hasNext()&&it28.hasNext()&&it29.hasNext()&&it30.hasNext()&&it31.hasNext()&&it32.hasNext()&&it33.hasNext()&&it34.hasNext()&&it35.hasNext()&&it36.hasNext())){
			  
			  stmnt.executeUpdate("INSERT INTO ServiceJourney (ServiceJourneyID, DepTime, ArrTime, MinAheadTime, MinLayoverTime, MaxShiftBackwardSeconds, MaxShiftForwardSeconds, FromStopBreakFacility, ToStopBreakFacility, Code, FahrplanID, LineID, FromStopID, ToStopID, VehTypeGroupID) VALUES('"+it23.next()+"','"+it27.next()+"','"+it28.next()+"','"+it29.next()+"','"+it30.next()+"','"+it32.next()+"','"+it33.next()+"','"+it34.next()+"','"+it35.next()+"','"+it36.next()+"', "
					  																																																													+ "(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+ss.getFilename()+"%'), "
			  																																																															+ "(SELECT l.ID FROM Line AS l WHERE l.LineID='"+it24.next()+"' AND l.FahrplanID=(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%')), "
			  																																																															+ "(SELECT sp.ID FROM Stoppoint AS sp WHERE sp.StoppointID='"+it25.next()+"' AND sp.FahrplanID=(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%')), "
			  																																																															+ "(SELECT sp.ID FROM Stoppoint AS sp WHERE sp.StoppointID='"+it26.next()+"' AND sp.FahrplanID=(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%')), "
			  																																																															+ "(SELECT vt.ID FROM VehicleType AS vt WHERE vt.VehicleTypeID='"+it31.next()+"' AND vt.FahrplanID=(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%')));"); 
		  }
		  //fill deadruntime
		  while((it37.hasNext()&&it38.hasNext()&&it39.hasNext()&&it40.hasNext()&&it41.hasNext()&&it42.hasNext())){
			  
			  stmnt.executeUpdate("INSERT INTO Deadruntime (FromTime, ToTime, Distance, Runtime, FahrplanID, FromStopID, ToStopID) VALUES('"+it39.next()+"','"+it40.next()+"','"+it41.next()+"','"+it42.next()+"', "
					  																																					+ "(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%'), "
					  																																					+ "(SELECT sp.ID FROM Stoppoint AS sp WHERE sp.StoppointID='"+it37.next()+"' AND sp.FahrplanID=(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%')), "
			  																																							+ "(SELECT sp.ID FROM Stoppoint AS sp WHERE sp.StoppointID='"+it38.next()+"' AND sp.FahrplanID=(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%')));"); 
		  }
		  //fill Reliefpoint
			while ((it43.hasNext() && it44.hasNext() && it45.hasNext() && it46
					.hasNext())) {


				stmnt.executeUpdate("INSERT INTO Reliefpoint (ReliefpointID, StopTime, FahrplanID, ServiceJourneyID, StoppointID) VALUES('"
						+ it43.next()
						+ "','"
						+ it46.next()
						+ "', "
						+ "(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '%"
						+ fileNameVergleich
						+ "%'), "
						+ "(SELECT sj.ID FROM ServiceJourney AS sj WHERE sj.ServiceJourneyID='"
						+ it44.next()
						+ "' AND sj.FahrplanID=(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '%"
						+ ss.getFilename()
						+ "%')), "
						+ "(SELECT sp.ID FROM Stoppoint AS sp WHERE sp.StoppointID='"
						+ it45.next()
						+ "' AND sp.FahrplanID=(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '%"
						+ fileNameVergleich+ "%')));");
			}
		  //fill Transfertime
		  while((it57.hasNext()&&it58.hasNext()&&it59.hasNext()&&it60.hasNext()&&it61.hasNext())){
			  stmnt.executeUpdate("INSERT INTO Transfertime (FromTime, ToTime, Runtime, FahrplanID, FromStopID, ToStopID) VALUES('"+it59.next()+"','"+it60.next()+"','"+it61.next()+"', "
					  																																	+ "(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%'), "
																																						+ "(SELECT sp.ID FROM Stoppoint AS sp WHERE sp.StoppointID='"+it57.next()+"' AND sp.FahrplanID=(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%')), "
					  																																	+ "(SELECT sp.ID FROM Stoppoint AS sp WHERE sp.StoppointID='"+it58.next()+"' AND sp.FahrplanID=(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%')));"); 
		  }
		  
		  //fill days according to how much days are considered in the plan (min: 5, max: 7)
		  //5 days
		  while((it49.hasNext()&&it50.hasNext()&&it51.hasNext()&&it52.hasNext()&&it53.hasNext()&&it54.hasNext())){
			  int id=it49.next();
			  
			  stmnt.executeUpdate("INSERT INTO Days (d1, d2, d3, d4, d5, FahrplanID, TRIPID) VALUES('"+it50.next()+"','"+it51.next()+"','"+it52.next()+"','"+it53.next()+"','"+it54.next()+"', "
					  																						+ "(SELECT ID FROM Fahrplan WHERE Bezeichnung = '"+fileNameVergleich+"'), "
					  																						+ "(SELECT sj.ID FROM ServiceJourney AS sj WHERE sj.ServiceJourneyID='"+id+"' AND sj.FahrplanID=(SELECT ID FROM Fahrplan WHERE Bezeichnung = '"+fileNameVergleich+"')));");
		  }
		  //6 days
		  while((it49.hasNext()&&it50.hasNext()&&it51.hasNext()&&it52.hasNext()&&it53.hasNext()&&it54.hasNext()&&it55.hasNext())){
			  int id=it49.next();
			  
			  stmnt.executeUpdate("INSERT INTO Days (d1, d2, d3, d4, d5, d6, FahrplanID, TRIPID) VALUES('"+it50.next()+"','"+it51.next()+"','"+it52.next()+"','"+it53.next()+"','"+it54.next()+"','"+it55.next()+"',"
					  																							+ "(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%'), "
					  																							+ "(SELECT sj.ID FROM ServiceJourney AS sj WHERE sj.ServiceJourneyID='"+id+"' AND sj.FahrplanID=(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%')));");

		  }
		  //7 days
		  while((it49.hasNext()&&it50.hasNext()&&it51.hasNext()&&it52.hasNext()&&it53.hasNext()&&it54.hasNext()&&it55.hasNext()&&it56.hasNext())){
			  int id=it49.next();
			  
			  stmnt.executeUpdate("INSERT INTO Days (d1, d2, d3, d4, d5, d6, d7, FahrplanID, TRIPID) VALUES('"+it50.next()+"','"+it51.next()+"','"+it52.next()+"','"+it53.next()+"','"+it54.next()+"','"+it55.next()+"','"+it56.next()+"', "
					  																								+ "(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%'), "
					  																								+ "(SELECT sj.ID FROM ServiceJourney AS sj WHERE sj.ServiceJourneyID='"+id+"' AND sj.FahrplanID=(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"%')));");
		  }
		  
		  //All Fahrplan array lists will be cleared
		  ss.clearFahrplanArraylists();
		  
		  closeConnection();
		  
	//catch for filling schedule data into tables
	}catch(SQLException e){
		  System.out.println("Could not execute SQL-Query!");
		  e.printStackTrace(); }
	}
	
	//*********************************************************************************************************************************************
	//filling the vehicle schedule data into the respective tables*********************************************************************************
	//*********************************************************************************************************************************************
	
	public void fillUmlaufplanIntoTables(String filename) {
		
		//temporary stringsplitter object that contains the the data from text files in array lists
		 StringSplitter ss = StringSplitter.getInstance();
		//invoke stringsplitter method for reading the data in  txt-files
		//get naming of tour plan
		String fileNameVergleich=filename;
		String finalString= getVehicleScheduleName(fileNameVergleich);
		
		
		int pos=0;
		 
		  //get name of vehicle schedule file
		  //String umlaufplanBezeichnung=fileNameVergleich;
		  
		  
		  int dayID;
		  
		  try{ Statement stmnt=connection.createStatement();
		  
		  if(!ss.getBlockelementDayID().isEmpty()){
			  dayID = ss.getBlockelementDayID().get(0);
		  
		  stmnt.executeUpdate("INSERT INTO Umlaufplan (Bezeichnung, Name, FahrplanID, DayID, Datum) VALUES ('"+fileNameVergleich+"','"+fileNameVergleich+"',(SELECT f.ID FROM Fahrplan AS f WHERE f.Bezeichnung LIKE('%"+finalString+"%')), '"+dayID+"', CURRENT_DATE);");
		  }
		  else{
			  stmnt.executeUpdate("INSERT INTO Umlaufplan (Bezeichnung, Name, FahrplanID, Datum) VALUES ('"+fileNameVergleich+"','"+fileNameVergleich+"',(SELECT f.ID FROM Fahrplan AS f WHERE f.Bezeichnung LIKE('%"+finalString+"%')), CURRENT_DATE);");
		  }    
		  
		  
		  //iterators for getting values from stringsplitter object
		  //block (tour)
		  Iterator<Integer> it = ss.getId().iterator(); 
		  Iterator<Integer> it2 =ss.getVehTypeID().iterator(); 
		  Iterator<Integer> it3 =ss.getDepotID().iterator();
		  
		  //blockelement
		  Iterator<Integer> it4 =ss.getBlockelementBlockID().iterator(); 
		  Iterator<String> it5 =ss.getBlockelementServiceJourneyID().iterator();
		  Iterator<Integer> it10 =ss.getBlockelementElementType().iterator();
		  
		  //exceptional journeys
		  Iterator<String> it15 =ss.getExceptionalblockelementServiceJourneyID().iterator();
		  Iterator<Integer>it16 = ss.getExceptionalblockelementFromStopID().iterator(); 
		  Iterator<Integer>it17 = ss.getExceptionalblockelementToStopID().iterator(); 
		  Iterator<String> it18 =ss.getExceptionalblockelementDepTime().iterator(); 
		  Iterator<String> it19 =ss.getExceptionalblockelementArrTime().iterator(); 

		  
		  
		  //fill block
		  while((it.hasNext()&&it2.hasNext()&&it3.hasNext())){
			  int BlockID = it.next();
			  int vehType = it2.next();
			  int DepotID = it3.next();
			  
			  stmnt.executeUpdate("INSERT INTO Block (BlockID, VehTypeID, DepotID, UmlaufplanID)  VALUES('"+BlockID+"', "
			  + "(SELECT vt.ID FROM VehicleType AS vt WHERE vt.VehicleTypeID='"+vehType+"' AND vt.FahrplanID= (SELECT f.ID FROM Fahrplan AS f WHERE f.Bezeichnung LIKE('%"+finalString+"%'))), "
			  + "(SELECT sp.ID FROM Stoppoint AS sp WHERE sp.StoppointID='"+DepotID+"' AND sp.FahrplanID=(SELECT f.ID FROM Fahrplan AS f WHERE f.Bezeichnung LIKE('%"+finalString+"%'))), "
			  + "(SELECT up.ID FROM Umlaufplan AS up WHERE Bezeichnung LIKE '"+fileNameVergleich+"'));");
				}
		  
		//fill special journey
		while(it4.hasNext()&&it5.hasNext()&&it10.hasNext()&&it15.hasNext()&&it16.hasNext()&&it17.hasNext()&&it18.hasNext()&&it19.hasNext()){ 
			int blockID = it4.next();
			String sjID = it5.next();
			int EleType = it10.next();
			pos++;
			
		
			if (EleType == 1){
				
				stmnt.executeUpdate("INSERT INTO Blockelement (ElementType, BlockID, ServiceJourneyID, UmlaufplanID, MatchingPos) VALUES('"+EleType+"', "
						  + "(SELECT b.ID FROM Block AS b WHERE b.BlockID='"+blockID+"' AND b.UmlaufplanID=(SELECT up.ID FROM Umlaufplan AS up WHERE up.Bezeichnung LIKE ('"+fileNameVergleich+"'))), "
						  + "(SELECT sj.ID FROM ServiceJourney AS sj WHERE sj.ServiceJourneyID='"+sjID+"' AND sj.FahrplanID=(SELECT fp.ID FROM Fahrplan AS fp WHERE fp.Bezeichnung LIKE ('%"+finalString+"%'))), "
						  + "(SELECT ID FROM Umlaufplan WHERE Bezeichnung LIKE '"+fileNameVergleich+"'),'"+pos+"');");
				
			}else{
				int fromstop = it16.next();
				int tostop = it17.next();
				String deptime = it18.next();
				String arrtime = it19.next();
				
				stmnt.executeUpdate("INSERT INTO ExceptionalBlockelement ( DepTime, ArrTime, ServiceJourneyID, ElementType, BlockID, FromStopID, ToStopID, UmlaufplanID, MatchingPos) VALUES('"+deptime+"','"+arrtime+"','"+it15.next()+"','"+EleType+"', "
						  + "(SELECT b.ID FROM Block AS b WHERE b.BlockID='"+blockID+"' AND b.UmlaufplanID= (SELECT up.ID FROM Umlaufplan AS up WHERE Bezeichnung LIKE '"+fileNameVergleich+"')), "
						  + "(SELECT sp.ID FROM Stoppoint AS sp WHERE sp.StoppointID='"+fromstop+"' AND sp.FahrplanID=(SELECT f.ID FROM Fahrplan AS f WHERE f.Bezeichnung LIKE('%"+finalString+"%'))), "
						  + "(SELECT sp.ID FROM Stoppoint AS sp WHERE sp.StoppointID='"+tostop+"' AND sp.FahrplanID=(SELECT f.ID FROM Fahrplan AS f WHERE f.Bezeichnung LIKE('%"+finalString+"%'))), "
						  + "(SELECT up.ID FROM Umlaufplan AS up WHERE Bezeichnung LIKE '"+fileNameVergleich+"'),'"+pos+"');");
			}	 
		 }
		 
		//All umlaufplan array lists will be cleared
		  closeConnection();
		 
		 
		  
		  //catch for filling tour plan tables
		  }catch(SQLException e){
		  System.out.println("Could not execute SQL-Query!");
		  e.printStackTrace(); }
	}
	
	//*******************************************************************************************************************************************
	//filling the duty type data into the respective tables**************************************************************************************
	//*******************************************************************************************************************************************
	
	//duty types
	public void fillDiensttypenIntoTables(String filename){
		
		//temporary stringsplitter object that contains the the data from text files in array lists
		StringSplitter ss = StringSplitter.getInstance();
		//invoke stringsplitter method for reading the data in  txt-files
		

		 try{ Statement stmnt=connection.createStatement(); 
		 //Fill values in specific tables
		 
		  
		  //iterators for getting values from stringsplitter object
		  Iterator<String> it = ss.getName().iterator(); 
		  Iterator<String> it2 =ss.getStartTimeMin().iterator(); 
		  Iterator<String> it3 =ss.getStartTimeMax().iterator(); 
		  Iterator<String> it4 =ss.getEndTimeMin().iterator(); 
		  Iterator<String> it5 =ss.getEndTimeMax().iterator(); 
		  Iterator<String> it6 = ss.getSignOnTime().iterator(); 
		  Iterator<String> it7 = ss.getSignOffTime().iterator(); 
		  Iterator<String> it8 = ss.getDurationMin().iterator(); 
		  Iterator<String> it9 = ss.getDurationMax().iterator(); 
		  Iterator<String> it10 = ss.getWorkingTimeTotalMin().iterator();
		  Iterator<String> it11 = ss.getWorkingTimeTotalMax().iterator(); 
		  Iterator<String> it12 =ss.getWorkingTimeBeforeBreakMin().iterator(); 
		  Iterator<String> it13 =ss.getWorkingTimeWithoutBreakMin().iterator(); 
		  Iterator<String> it14 =ss.getWorkingTimeAfterLastBreakMin().iterator(); 
		  Iterator<String> it15 =ss.getDrivingTimeTotalMin().iterator(); 
		  Iterator<String> it16 = ss.getDrivingTimeTotalMax().iterator(); 
		  Iterator<String> it17 = ss.getDrivingTimeWithoutBreakMin().iterator(); 
		  Iterator<String> it18 = ss.getDrivingTimeWithoutBreakMax().iterator(); 
		  Iterator<String> it19 = ss.getDrivingTimeWithoutBreakMinInterruptionTime().iterator(); 
		  Iterator<String> it20 = ss.getDrivingTimeBeforeFirstBreakMin().iterator();
		  Iterator<String> it21= ss.getBreakType().iterator(); 
		  Iterator<String> it22 =ss.getBreakTimeTotalMin().iterator(); 
		  Iterator<String> it23 =ss.getBreakTimeTotalMax().iterator(); 
		  Iterator<String> it24 =ss.getBreakTimeMin().iterator(); 
		  Iterator<String> it25 =ss.getBreakTimeMax().iterator(); 
		  Iterator<Integer> it26 = ss.getPieceCountMin().iterator(); 
		  Iterator<Integer> it27 = ss.getPieceCountMax().iterator(); 
		  Iterator<String> it28 = ss.getAllowedCumulatedWorkingTimeMax().iterator(); 
		  Iterator<Float> it29 = ss.getDutyFixCost().iterator(); 
		  Iterator<Integer> it30 = ss.getIsWorkRateConsidered().iterator();
		  Iterator<Integer> it31 = ss.getIsBreakRateConsidered().iterator(); 
		  Iterator<Float> it32 =ss.getDutyCostPerMinute().iterator(); 
		  Iterator<Integer> it33 =ss.getIsVehicleChangeAllowedDuringBreak().iterator(); 
		  Iterator<String> it34 =ss.getBreakTimeAllowsStarts().iterator(); 
		  Iterator<String> it35 =ss.getBreakTimeAllowsEnds().iterator();
		  
		  //WorkingtimeWithoutBreakMax not included in current plans, but left because future plans may contain values 

		  Iterator<String> it36 = ss.getWorkingtimeWithoutBreakMax().iterator();
	
		  
		  //Get schedule name
		  String fileNameVergleich=filename;
			String finalString= getVehicleScheduleName(fileNameVergleich);

		  while((it.hasNext()&&it2.hasNext()&&it3.hasNext()&&it4.hasNext()&&it5.hasNext()&&it6.hasNext()&&it7.hasNext()&&it8.hasNext()&&it9.hasNext()&&it10.hasNext()&&it11.hasNext()&&it12.hasNext()&&it13.hasNext()&&it14.hasNext()&&it15.hasNext()&&it16.hasNext()&&it17.hasNext()&&it18.hasNext()&&it19.hasNext()&&it20.hasNext()&&it21.hasNext()&&it22.hasNext()&&it23.hasNext()&&it24.hasNext()&&it25.hasNext()&&it26.hasNext()&&it27.hasNext()&&it28.hasNext()&&it29.hasNext()&&it30.hasNext()&&it31.hasNext()&&it32.hasNext()&&it33.hasNext()&&it34.hasNext()&&it35.hasNext()&&it36.hasNext())){

			  stmnt.executeUpdate("INSERT INTO Dutytype (Name, StartTimeMin, StartTimeMax, EndTimeMin, EndTimeMax, SignOnTime, SignOffTime, DurationMin, DurationMax, WorkingTimeTotalMin, WorkingTimeTotalMax, WorkingTimeBeforeBreakMin, WorkingTimeWithoutBreakMin, WorkingTimeAfterLastBreakMin, DrivingTimeTotalMin, DrivingTimeTotalMax, DrivingTimeWithoutBreakMin, DrivingTimeWithoutBreakMax, DrivingTimeWithoutBreakMinInterruptionTime, DrivingTimeBeforeFirstBreakMin, BreakType, BreakTimeTotalMin, BreakTimeTotalMax, BreakTimeMin, BreakTimeMax, PieceCountMin, PieceCountMax, AllowedCumulatedWorkingTimeMax, DutyFixCost, IsWorkRateConsidered, IsBreakRateConsidered, DutyCostPerMinute, IsVehicleChangeAllowedDuringBreak, BreakTimeAllowedStarts, BreakTimeAllowedEnds, WorkingTimeWithoutBreakMax, FahrplanID) "
					+ " VALUES ('"+it.next()+"','"+it2.next()+"','"+it3.next()+"','"+it4.next()+"','"+it5.next()+"','"+it6.next()+"','"+it7.next()+"','"+it8.next()+"','"+it9.next()+"','"+it10.next()+"','"+it11.next()+"','"+it12.next()+"','"+it13.next()+"','"+it14.next()+"','"+it15.next()+"','"+it16.next()+"','"+it17.next()+"','"+it18.next()+"','"+it19.next()+"','"+it20.next()+"','"+it21.next()+"','"+it22.next()+"','"+it23.next()+"','"+it24.next()+"','"+it25.next()+"','"+it26.next()+"','"+it27.next()+"','"+it28.next()+"','"+it29.next()+"','"+it30.next()+"','"+it31.next()+"','"+it32.next()+"','"+it33.next()+"','"+it34.next()+"','"+it35.next()+"','"+it36.next()+"', "
			  		+ "(SELECT f.ID FROM Fahrplan AS f WHERE f.Bezeichnung LIKE('%"+finalString+"%')));");
		  }
		  
		  closeConnection();
		  
		  //catch for filling duty type
		  }catch(SQLException e){
		  System.out.println("Could not execute SQL-Query!");
		  e.printStackTrace(); }
		 		
	}
	
	//*********************************************************************************************************************************************
	//filling the crew schedule data into the respective tables************************************************************************************
	//*********************************************************************************************************************************************
	
	public void fillDienstplanIntoTable(String filename){
		
		//temporary stringsplitter object that contains the the data from text files in array lists
		StringSplitter ss = StringSplitter.getInstance();
		String[] cutFilename=filename.split("_");
		String fileNameVergleich=filename;
		String dienstplanNameCut="";
		for (int i = 1; i < cutFilename.length; i++) {
			if (i<cutFilename.length-1) {
				dienstplanNameCut+=cutFilename[i]+"_";
			}else{
			dienstplanNameCut+=cutFilename[i];
			}
		}
		  String finalString=getVehicleScheduleName(fileNameVergleich);
		//invoke stringsplitter method for reading the data in  txt-files
		int pos=0;

		int dayID;
		  
		  try{ Statement stmnt=connection.createStatement();
		  
		  if(!ss.getDutyelementDayID().isEmpty()){
			  //Get Day on which the crew schedule is valid
			  dayID = ss.getDutyelementDayID().get(0);
			  //filling Dienstplan Table

		  stmnt.executeUpdate("INSERT INTO Dienstplan (Bezeichnung, Name, FahrplanID, UmlaufplanID, DayID, Datum) VALUES ('"
				  			+fileNameVergleich+"','"+fileNameVergleich+"',(SELECT f.ID FROM Fahrplan AS f WHERE f.Bezeichnung LIKE('%"+finalString+"%')),(SELECT u.ID FROM Umlaufplan AS u WHERE u.FahrplanID=(SELECT f.ID FROM Fahrplan AS f WHERE f.Bezeichnung LIKE('%"+finalString+"%') AND u.Bezeichnung LIKE ('%"
				  			+dienstplanNameCut+"%') AND u.DayID='"+dayID+"')),'"+dayID+"', CURRENT_DATE);");
		  }else{
			    
			  //filling Dienstplan Table
		  stmnt.executeUpdate("INSERT INTO Dienstplan (Bezeichnung, Name, FahrplanID, UmlaufplanID, Datum) VALUES ('"
				  			+fileNameVergleich+"','"+fileNameVergleich+"',(SELECT f.ID FROM Fahrplan AS f WHERE f.Bezeichnung LIKE('%"+finalString+"%')),(SELECT u.ID FROM Umlaufplan AS u WHERE u.FahrplanID=(SELECT f.ID FROM Fahrplan AS f WHERE f.Bezeichnung LIKE('%"+finalString+"%')AND u.Bezeichnung LIKE ('%"
				  			+dienstplanNameCut+"%'))), CURRENT_DATE);");
		  }
		  //iterators for getting values from stringsplitter object
		  Iterator<Integer> it11 = ss.getDutyelementElementType().iterator();
		  
		  //duty
		  Iterator<String> it1 = ss.getDutyDutyID().iterator(); 
		  Iterator<String> it2 =ss.getDutyDutyType().iterator();
		  
		  //dutyelements
		  Iterator<String> it3 =ss.getDutyelementDutyID().iterator();
		  Iterator<Integer> it4 =ss.getDutyelementBlockID().iterator();
		  Iterator<String> it5 =ss.getDutyelementServiceJourneyID().iterator();


		  /**
		   * TODO:
		   * Stationen und Zeiten fï¿½r die Dienste die Sonderfahrten bedienen mï¿½ssen hier drin gespeichert werden
		   * evtl. so trennen wie blockelemente?
		   * ServiceJourneycode(Z512) muss wieder rein, wirft dann aber Fehler wegen static
		  */
		  //exceptional journeys

		  Iterator<String> it15 =ss.getExceptionaldutyelementServiceJourneyID().iterator();
		  Iterator<Integer>it16 = ss.getExceptionaldutyelementFromStopID().iterator(); 
		  Iterator<Integer>it17 = ss.getExceptionaldutyelementToStopID().iterator(); 
		  Iterator<String> it18 =ss.getExceptionaldutyelementDepTime().iterator(); 
		  Iterator<String> it19 =ss.getExceptionaldutyelementArrTime().iterator(); 
		  
		  /*ATTENTION!!!******************************************************************************************************************************
		  Feb.28th2014:
		  BlockID, ServiceJourneyID, From- and ToStopID currently can not be imported into the exceptional Dutyelement Table as Foreign keys 
		  referenceing the respective primary keys in their parent relation. This is due to artificial values (negative IDs with other values) which
		  are generated for no-service-journeys in the crew schedules at the moment.
		  DutyelementServiceJourneyCode not included in current plans, but left because future plans may contain values 
		  Iterator<String> it11 = StringSplitter.getDutyelementServiceJourneyCode().iterator();*/
		  //******************************************************************************************************************************************
		
		  		  
		  //fill duty

		  while((it1.hasNext()&&it2.hasNext())){
			  String dutyID = it1.next();
			  String dutytype = it2.next();	
			  
		  stmnt.executeUpdate("INSERT INTO Duty (DutyID, DutyTypeID, DienstplanID) VALUES('"+dutyID+"', "
				  + "(SELECT ID FROM Dutytype WHERE Name='"+dutytype+"' AND Dutytype.FahrplanID=(SELECT ID FROM Fahrplan WHERE Bezeichnung LIKE '%"+finalString+"%')), "
				  + "(SELECT ID FROM Dienstplan WHERE Bezeichnung = '"+fileNameVergleich+"'));"); }
		  
		//fill duty element
			  while(it3.hasNext()&&it4.hasNext()&&it5.hasNext()&&it11.hasNext()&&it15.hasNext()&&it16.hasNext()&&it17.hasNext()&&it18.hasNext()&&it19.hasNext()){
				  int de_elementtype = it11.next();
				  String dutyelementdutyID = it3.next();
				  int dutyelementblockID = it4.next();
				  String dutyelementservicejourneyID = it5.next();
				  pos++;
				  //exceptional dutyelements
				 
				   
				  if (de_elementtype==1){
					 stmnt.executeUpdate("INSERT INTO Dutyelement (ElementType, DutyID, BlockID, ServiceJourneyID, DienstplanID, MatchingPos) VALUES('"+de_elementtype+"', "
							  + "(SELECT d.ID from Duty AS d WHERE d.DutyID = '"+dutyelementdutyID+"' AND d.DienstplanID=(SELECT dp.ID FROM Dienstplan AS dp WHERE Bezeichnung LIKE ('%"+fileNameVergleich+"%'))), "
							  + "(SELECT b.ID FROM Block AS b, Dienstplan AS dp WHERE b.BlockID='"+dutyelementblockID+"' AND b.UmlaufplanID=(SELECT d.UmlaufplanID FROM Dienstplan AS d WHERE d.Bezeichnung='"+fileNameVergleich+"')), "
							  + "(SELECT sj.ID FROM ServiceJourney AS sj WHERE sj.ServiceJourneyID='"+dutyelementservicejourneyID+"' AND sj.FahrplanID=(SELECT f.ID FROM Fahrplan AS f WHERE f.Bezeichnung LIKE('%"+finalString+"%'))), "
							  + "(SELECT ID FROM Dienstplan WHERE Bezeichnung='"+fileNameVergleich+"'),'"+pos+"');");

				  }else{
					  
					  Integer exdutyelementfromstopID = it16.next(); 
					  Integer exdutyelementtostopID = it17.next();
					  String exdutyelemmentdeptime = it18.next();
					  String exdutyelementarrtime = it19.next();

					    stmnt.executeUpdate("INSERT INTO ExceptionalDutyelement (DepTime, ArrTime,FromStopID, ToStopID, ServiceJourneyID, Elementtype, DutyID, BlockID, DienstplanID, MatchingPos ) VALUES('"+exdutyelemmentdeptime+"','"+exdutyelementarrtime+"','"+exdutyelementfromstopID+"','"+exdutyelementtostopID+"','"+dutyelementservicejourneyID+"','"+de_elementtype+"', "
								  + "(SELECT ID from Duty WHERE DutyID ='"+dutyelementdutyID+"' AND DienstplanID =(SELECT ID FROM Dienstplan WHERE Bezeichnung='"+fileNameVergleich+"')),'"+dutyelementblockID+"',"
								  + "(SELECT ID FROM Dienstplan WHERE Bezeichnung='"+fileNameVergleich+"'),'"+pos+"');");
				  }			  
		  }
		  
		  //All Dienstplan array lists will be cleared
		  ss.clearDienstplanArraylists();
		  closeConnection();
		  //catch for filling duty roster tables
		  }catch(SQLException e){
		  System.out.println("Could not execute SQL-Query!");
		  e.printStackTrace(); }
		  		
	}
	

	
	//******************************************************************************************************************************************
	//filling the scenario data into the respective tables**************************************************************************************
	//******************************************************************************************************************************************
	public void fillSzenarioIntoTables(String filename){
		
		Statement stmnt;
		StringSplitter ss = StringSplitter.getInstance();
		
		String fileNameVergleich=filename;
		  String finalString=getVehicleScheduleNameSzenario(fileNameVergleich);
		
		try {
			stmnt = connection.createStatement();
		
		  //Szenario
		  Iterator<String> it1 = ss.getSzenarioDutyID().iterator();
		  Iterator<String> it2 = ss.getSzenarioVehicleID().iterator(); 
		  Iterator<String> it3 = ss.getSzenarioServiceJourneyID().iterator(); 
		  Iterator<String> it4 = ss.getSzenarioDepTime().iterator();
		  Iterator<Integer> it5 = ss.getSzenarioDelay().iterator();
		  
		  stmnt.executeUpdate("INSERT INTO Szenario (Bezeichnung, FahrplanID, Datum) VALUES('"+fileNameVergleich+"',(SELECT f.ID FROM Fahrplan AS f WHERE f.Bezeichnung LIKE('%"+finalString+"%')), CURRENT_DATE);");
		  
		  while((it1.hasNext()&&it2.hasNext()&&it3.hasNext()&&it4.hasNext()&&it5.hasNext())){

			  stmnt.executeUpdate("INSERT INTO PrimeDelaySzenario (DutyID, VehicleID, ServiceJourneyID, DepTime, Delay, SzenarioID) VALUES('"+it1.next()+"','"+it2.next()+"',(SELECT ID FROM ServiceJourney WHERE ServiceJourneyID='"+it3.next()+"'),'"+it4.next()+"','"+it5.next()+"',(SELECT s.ID FROM Szenario AS s WHERE s.Bezeichnung LIKE('"+fileNameVergleich+"')));");
		  }
		  
		  closeConnection();
		  } 
		catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
	
	//Prüft, ob ein Fahrplan in der Datenbank existiert
	public boolean checkFahrplan(String filename){
		
		ArrayList <String> fahrplaene=new ArrayList<String>();
		try{
			Statement stmnt=getConnection().createStatement();
			ResultSet rest1=stmnt.executeQuery("SELECT Bezeichnung FROM Fahrplan;");
			while(rest1.next()){
			fahrplaene.add(rest1.getString("Bezeichnung"));
			}
			for (int i = 0; i < fahrplaene.size(); i++) {
				if(fahrplaene.get(i).contains(filename)){
					fahrplanVorhanden=true;
					break;
				}
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return fahrplanVorhanden;
	}
	
	public boolean checkDiensttypen(String filename){
		
		ArrayList <Integer> diensttypenListe=new ArrayList<Integer>();
		try{
			Statement stmnt=getConnection().createStatement();
			ResultSet rest1=stmnt.executeQuery("SELECT dt.FahrplanID, f.Bezeichnung FROM Dutytype AS dt, Fahrplan AS f WHERE dt.FahrplanID=f.ID;");
			while(rest1.next()){
			diensttypenListe.add(Integer.parseInt(rest1.getString("FahrplanID")));
			}
				if(!diensttypenListe.isEmpty()){
					diensttypenVorhanden=true;
				}
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		return diensttypenVorhanden;
		
	}
	
	//method for getting schedule naming to compare for getting the correct foreign keys

	public String getVehicleScheduleName(String filename){
		String vehicleschedulename =filename;
		String[] string=filename.split("_real");
		vehicleschedulename=string[1];
		String[] resultString=vehicleschedulename.split("_");
		String scheduleName="";
		for (int i = 0; i < resultString.length-1; i++) {
			if(i==resultString.length-2){
				scheduleName+=resultString[i];
			}else{
			scheduleName+=resultString[i]+"_";
			}
		}
		return scheduleName;
	}
	
	public String getVehicleScheduleNameSzenario(String filename)
	{
		String vehicleschedulename =filename;
		String scheduleName="";
		String[] string=filename.split("_real");
		vehicleschedulename=string[1];
		String[] resultString=vehicleschedulename.split("_");
		for (int i = 1; i < resultString.length; i++) {
			if(resultString[i].matches("^[0-9].*")&&resultString[i].endsWith("txt")){
				break;
			}else{
				scheduleName+="_"+resultString[i];
			}
		}
		return scheduleName;
	}
	public boolean checkUmlaufplan(String test) {
		
		try{
			Statement stmnt=getConnection().createStatement();
			ResultSet rest1=stmnt.executeQuery("SELECT b.BlockID FROM Block AS b, Umlaufplan AS u, Fahrplan AS f WHERE b.UmlaufplanID=u.ID AND u.FahrplanID=f.ID AND f.Bezeichnung LIKE'%"+test+"%';");
			if(!rest1.next()){
			umlaufplanVorhanden=false;
			}else{
				umlaufplanVorhanden=true;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return umlaufplanVorhanden;
		
	}
	
	//getter for DB connection
	public Connection getConnection() {
		return connection;
	}

	public boolean isFahrplanVorhanden() {
		return fahrplanVorhanden;
	}

	public boolean isDiensttypenVorhanden() {
		return diensttypenVorhanden;
	}

	public boolean isUmlaufplanVorhanden() {
		return umlaufplanVorhanden;
	}


	
	
	
	
}
