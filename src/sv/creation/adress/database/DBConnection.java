package sv.creation.adress.database;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;

import sv.creation.adress.util.StringSplitter;

public class DBConnection {

	public static DBConnection instance = null;
	private static Connection connection;
	// Path where a empty database file was created
	public static final String DB_PATH = System.getProperty("user.home") + "/"
			+ "testdb.db";

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.err.println("Treiber konnte nicht geladen werden.");
			e.printStackTrace();
		}
	}

	public DBConnection() {

	}

	// Singleton
	public static DBConnection getInstance() {
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}

	public void initDBConnection() {

		try {
			if (connection != null)
				return;
			System.out.println("Creating DB Connection...");
			connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
			if (!connection.isClosed())
				System.out.println("...Connection established!");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

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

	public void createTables() {

		try {
			Statement stmnt = connection.createStatement();
			// Checks if table exists or not
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Duty (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
															   + "DutyID VARCHAR(30) NOT NULL, "
															   + "DutyType VARCHAR(50) NOT NULL);");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Dutyelement (ID INTEGER PRIMARY KEY AUTOINCREMENT,"
																	  + "DutyID INTEGER NOT NULL, "
																	  + "BlockID INTEGER NOT NULL, "
																	  + "ServiceJourneyID INTEGER NOT NULL, "
																	  + "ElementType INTEGER NOT NULL, "
																	  + "DayID INTEGER);");
//																	  + "FOREIGN KEY (DutyID) REFERENCES Duty(ID), "
//																	  + "FOREIGN KEY (BlockID) REFERENCES Block(BlockID), "
//																	  + "FOREIGN KEY (ServiceJourneyID) REFERENCES ServiceJourney(ID), "
//																	  + "FOREIGN KEY(DayID) REFERENCES Day(dayID));");
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
																   + "BreakTimeAllowedEnds VARCHAR(30) NOT NULL "
																   //+ "WorkingTimeWithoutBreakMax VARCHAR(30)"
																   + ");");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Block (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																+ "BlockID INTEGER NOT NULL, "
																+ "Code INTEGER NOT NULL, "
																+ "Name INTEGER NOT NULL);");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Blockelement (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																	   + "BlockID INTEGER NOT NULL, "
																	   + "ServiceJourneyID INTEGER NOT NULL, "
																	   + "ElementType INTEGER NOT NULL, "
																	   + "DayID INTEGER);");
//																	   + "FOREIGN KEY (BlockID) REFERENCES Block(BlockID), "
//																	   + "FOREIGN KEY (ServiceJourneyID) REFERENCES ServiceJourney(ID), "
//																	   + "FOREIGN KEY (FromStopID) REFERENCES ServiceJourney(FromStopID), "
//																	   + "FOREIGN KEY (ToStopID) REFERENCES ServiceJourney(ToStopID), "
//																	   + "FOREIGN KEY (ArrTime) REFERENCES ServiceJourney(ArrTime), "
//																	   + "FOREIGN KEY (DepTime) REFERENCES ServiceJourney(DepTime), "
//																	   + "FOREIGN KEY(DayID) REFERENCES Day(dayID));");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Stoppoint (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																	+ "StoppointID INTEGER NOT NULL, "
																	+ "Code INTEGER NOT NULL, "
																	+ "Name INTEGER NOT NULL);");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Line (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
															   + "LineID INTEGERNOT NULL, "
															   + "Code INTEGER NOT NULL, "
															   + "Name INTEGER NOT NULL);");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS VehicleType (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																	  + "VehicleTypeID INTEGER NOT NULL, "
																	  + "Code INTEGER NOT NULL, "
																	  + "Name INTEGER NOT NULL, "
																	  + "VehCost INTEGER NOT NULL, "
																	  + "KmCost INTEGER NOT NULL, "
																	  + "HourCost INTEGER NOT NULL, "
																	  + "Capacity INTEGER NOT NULL);");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS VehicleTypeGroup (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																		   + "VehicleTypeGroupID INTEGER NOT NULL, "
																		   + "Code INTEGER NOT NULL, "
																		   + "Name INTEGER NOT NULL);");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS VehicleTypeToVehicleTypeGroup (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																						+ "VehTypeID INTEGER NOT NULL, "
																						+ "VehTypeGroupID INTEGER NOT NULL); ");
//																						+ "FOREIGN KEY (VehTypeID) REFERENCES VehicleType(ID), "
//																						+ "FOREIGN KEY (VehTypeGroupID) REFERENCES VehicleTypeGroup(ID));");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS VehicleCapToStoppoint (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																				+ "VehTypeID INTEGER NOT NULL, "
																				+ "StoppointID INTEGER NOT NULL, "
																				+ "Min INTEGER NOT NULL, "
																				+ "Max INTEGER NOT NULL); ");
//																				+ "FOREIGN KEY (VehTypeID) REFERENCES VehicleType(ID), "
//																				+ "FOREIGN KEY (StoppointID) REFERENCES Stoppoint(ID));");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS ServiceJourney (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																		 + "ServiceJourneyID INTEGER NOT NULL, "
																		 + "LineID INTEGER NOT NULL, "
																		 + "FromStopID INTEGER NOT NULL, "
																		 + "ToStopID INTEGER NOT NULL, "
																		 + "DepTime VARCHAR(10) NOT NULL, "
																		 + "ArrTime VARCHAR(10) NOT NULL, "
																		 + "MinAheadTime INTEGER NOT NULL, "
																		 + "MinLayoverTime INTEGER NOT NULL, "
																		 + "VehTypeGroupID INTEGER NOT NULL, "
																		 + "MaxShiftBackwardSeconds INTEGER NOT NULL, "
																		 + "MaxShiftForwardSeconds INTEGER NOT NULL, "
																		 + "FromStopBreakFacility INTEGER NOT NULL, "
																		 + "ToStopBreakFacility INTEGER NOT NULL, "
																		 + "Code INTEGER);");
//																		 + "FOREIGN KEY (LineID) REFERENCES Line(ID), "
//																		 + "FOREIGN KEY (VehTypeGroupID) REFERENCES VehicleTypeGroup(ID), "
//																		 + "FOREIGN KEY(FromStopID) REFERENCES Stoppoint(ID), "
//																		 + "FOREIGN KEY(ToStopID) REFERENCES Stoppoint(ID));");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Deadruntime (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																	  + "FromStopID INTEGER NOT NULL, "
																	  + "ToStopID INTEGER NOT NULL, "
																	  + "FromTime VARCHAR(10) NOT NULL, "
																	  + "ToTime VARCHAR(10) NOT NULL, "
																	  + "Distance INTEGER NOT NULL, "
																	  + "RunTime INTEGER NOT NULL); ");
//																	  + "FOREIGN KEY (FromStopID) REFERENCES Stoppoint(ID), "
//																	  + "FOREIGN KEY (ToStopID) REFERENCES Stoppoint(ID), "
//																	  + "FOREIGN KEY(FromTime) REFERENCES ServiceJourney(DepTime), "
//																	  + "FOREIGN KEY(ToTime) REFERENCES ServiceJoruney(ArrTime));");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Reliefpoint (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
																	  + "ReliefpointID INTEGER NOT NULL, "
																	  + "ServiceJourneyID INTEGER NOT NULL, "
																	  + "StoppointID INTEGER NOT NULL, "
																	  + "StopTime VARCHAR(10) NOT NULL); ");
//																 	  + "FOREIGN KEY(ServiceJourneyID) REFERENCES ServiceJourney(ID), "
//																	  + "FOREIGN KEY (StoppointID) REFERENCES Stoppoint(ID));");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Days (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
															   + "TRIPID INTEGER NOT NULL, "
															   + "d1 INTEGER NOT NULL, "
															   + "d2 INTEGER NOT NULL, "
															   + "d3 INTEGER NOT NULL, "
															   + "d4 INTEGER NOT NULL, "
															   + "d5 INTEGER NOT NULL, "
															   + "d6 INTEGER, "
															   + "d7 INTEGER); ");
//															   + "FOREIGN KEY (TRIPID) REFERENCES ServiceJourney(ID));");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Day (dayID INTEGER NOT NULL PRIMARY KEY, "
														      + "Name VARCHAR(10));");
			
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS PrimeDelaySzenario (ID INTEGER PRIMARY KEY AUTOINCREMENT, DutyID INTEGER, VehicleID INTEGER, ServiceJourneyID VARCHAR(30) NOT NULL, DepTime VARCHAR(30) NOT NULL, Delay INTEGER NOT NULL); ");

			
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
			  
		} catch (SQLException e) {
			System.out.println("Could not execute SQL-Query!");
			e.printStackTrace();
		}
	}

	public void fillUmlaufplanIntoTables() {

		StringSplitter ss = new StringSplitter();
		ss.readTxtUmlaufplan();

		
		  try{ Statement stmnt=connection.createStatement(); //Fill values in
		  //specific tables
		  
		  Iterator<Integer> it = ss.getId().iterator(); 
		  Iterator<Integer> it2 =ss.getVehTypeID().iterator(); 
		  Iterator<Integer> it3 =ss.getDepotID().iterator(); 
		  Iterator<Integer> it4 =ss.getBlockelementBlockID().iterator(); 
		  Iterator<String> it5 =ss.getBlockelementServiceJourneyID().iterator();
		  Iterator<Integer>it6 = ss.getBlockelementFromStopID().iterator(); 
		  Iterator<Integer>it7 = ss.getBlockelementToStopID().iterator(); 
		  Iterator<String> it8 =ss.getBlockelementDepTime().iterator(); 
		  Iterator<String> it9 =ss.getBlockelementArrTime().iterator(); 
		  Iterator<Integer> it10 =ss.getBlockelementElementType().iterator();
		  String dayID=ss.getBlockelementDayID().get(0);
		  
		  
		  while((it.hasNext()&&it2.hasNext()&&it3.hasNext())){
			  //System.out.println("INSERT INTO Block VALUES('"+it.next()+"','"+it2.next()+"','"+it3.next()+"');"); 
			  
			  stmnt.executeUpdate("INSERT INTO Block (BlockID, Code, Name)  VALUES('"+it.next()+"','"+it2.next()+"','"+it3.next()+"');"); }
		  
		  
		  while(it4.hasNext()&&it5.hasNext()&&it6.hasNext()&&it10.hasNext()){ stmnt.executeUpdate(
		  "INSERT INTO Blockelement (BlockID, ServiceJourneyID, ElementType, DayID) VALUES('"
		  +it4.next()+"','"+it5.next()+"','"+it10.next()+"','"+dayID+"');");}
		  System.out.println("Umlaufplan importiert!");
		  }catch(SQLException e){
		  System.out.println("Could not execute SQL-Query!");
		  e.printStackTrace(); }
		
	}
	
	public void fillDiensttypenIntoTables(){
		
		StringSplitter ss = new StringSplitter();
		ss.readTxtDiensttypen();
		
		 try{ Statement stmnt=connection.createStatement(); //Fill values in
		  //specific tables
		  
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
		  //Iterator<Integer> it36 = ss.getWorkingtimeWithoutBreakMax().iterator();
		  
		  
		  while((it.hasNext()&&it2.hasNext()&&it3.hasNext()&&it4.hasNext()&&it5.hasNext()&&it6.hasNext()&&it7.hasNext()&&it8.hasNext()&&it9.hasNext()&&it10.hasNext()&&it11.hasNext()&&it12.hasNext()&&it13.hasNext()&&it14.hasNext()&&it15.hasNext()&&it16.hasNext()&&it17.hasNext()&&it18.hasNext()&&it19.hasNext()&&it20.hasNext()&&it21.hasNext()&&it22.hasNext()&&it23.hasNext()&&it24.hasNext()&&it25.hasNext()&&it26.hasNext()&&it27.hasNext()&&it28.hasNext()&&it29.hasNext()&&it30.hasNext()&&it31.hasNext()&&it32.hasNext()&&it33.hasNext()&&it34.hasNext()&&it35.hasNext())){
			  stmnt.executeUpdate
		  ("INSERT INTO Dutytype (Name, StartTimeMin, StartTimeMax, EndTimeMin, EndTimeMax, SignOnTime, SignOffTime, DurationMin, DurationMax, WorkingTimeTotalMin, WorkingTimeTotalMax, WorkingTimeBeforeBreakMin, WorkingTimeWithoutBreakMin, WorkingTimeAfterLastBreakMin, DrivingTimeTotalMin, DrivingTimeTotalMax, DrivingTimeWithoutBreakMin, DrivingTimeWithoutBreakMax, DrivingTimeWithoutBreakMinInterruptionTime, DrivingTimeBeforeFirstBreakMin, BreakType, BreakTimeTotalMin, BreakTimeTotalMax, BreakTimeMin, BreakTimeMax, PieceCountMin, PieceCountMax, AllowedCumulatedWorkingTimeMax, DutyFixCost, IsWorkRateConsidered, IsBreakRateConsidered, DutyCostPerMinute, IsVehicleChangeAllowedDuringBreak, BreakTimeAllowedStarts, BreakTimeAllowedEnds) VALUES('"+it.next()+"','"+it2.next()+"','"+it3.next()+"','"+it4.next()+"','"+it5.next()+"','"+it6.next()+"','"+it7.next()+"','"+it8.next()+"','"+it9.next()+"','"+it10.next()+"','"+it11.next()+"','"+it12.next()+"','"+it13.next()+"','"+it14.next()+"','"+it15.next()+"','"+it16.next()+"','"+it17.next()+"','"+it18.next()+"','"+it19.next()+"','"+it20.next()+"','"+it21.next()+"','"+it22.next()+"','"+it23.next()+"','"+it24.next()+"','"+it25.next()+"','"+it26.next()+"','"+it27.next()+"','"+it28.next()+"','"+it29.next()+"','"+it30.next()+"','"+it31.next()+"','"+it32.next()+"','"+it33.next()+"','"+it34.next()+"','"+it35.next()+"');"); }
		  System.out.println("Dienstplan importiert!");
		  }catch(SQLException e){
		  System.out.println("Could not execute SQL-Query!");
		  e.printStackTrace(); }
		
	}
	
	public void fillDienstplanIntoTable(){
		
		StringSplitter ss = new StringSplitter();
		ss.readTxtDienstplan();

		
		  try{ Statement stmnt=connection.createStatement(); //Fill values in
		  //specific tables
		  
		  Iterator<String> it = ss.getDutyDutyID().iterator(); 
		  Iterator<String> it2 =ss.getDutyDutyType().iterator(); 
		  Iterator<String> it3 =ss.getDutyelementDutyType().iterator(); 
		  Iterator<Integer> it4 =ss.getDutyelementBlockID().iterator(); 
		  Iterator<String> it5 =ss.getDutyelementServiceJourneyID().iterator(); 
//		  Iterator<Integer> it6 = StringSplitter.getDutyelementFromStopID().iterator(); 
//		  Iterator<Integer> it7 = StringSplitter.getDutyelementToStopID().iterator(); 
//		  Iterator<String> it8 = StringSplitter.getDutyelementDepTime().iterator(); 
//		  Iterator<String> it9 = StringSplitter.getDutyelementArrTime().iterator(); 
		  Iterator<Integer> it10 = ss.getDutyelementElementType().iterator();
		 // Iterator<String> it11 = StringSplitter.getDutyelementServiceJourneyCode().iterator();
		  String dayID = ss.getDutyelementDayID().get(0);
		  
		  
		  while((it.hasNext()&&it2.hasNext())){
			 
		  stmnt.executeUpdate("INSERT INTO Duty (DutyID, DutyType) VALUES('"+it.next()+"','"+it2.next()+"');"); }
		   
		  while(it3.hasNext()&&it4.hasNext()&&it5.hasNext()&&it10.hasNext()) 
		  stmnt.executeUpdate(		  
		  "INSERT INTO Dutyelement (DutyId, BlockID, ServiceJourneyID, ElementType, DayID) VALUES('"
		  +it3.next()+"','"+it4.next()+"','"+it5.next()+"','"+it10.next()+"','"+dayID+"');");
		  }catch(SQLException e){
		  System.out.println("Could not execute SQL-Query!");
		  e.printStackTrace(); }
		
	}
	
	public void fillFahrplanIntoTables(){
		
		StringSplitter ss = new StringSplitter();
		ss.readTxtFahrplan();

		
		  try{ Statement stmnt=connection.createStatement(); //Fill values in
		  //specific tables
		  
		  Iterator<Integer> it = ss.getStopID().iterator(); 
		  Iterator<String> it2 =ss.getStopCode().iterator(); 
		  Iterator<String> it3 =ss.getStopName().iterator(); 
		  Iterator<Integer> it4 =ss.getLineID().iterator(); 
		  Iterator<String> it5 =ss.getLineCode().iterator(); 
		  Iterator<String> it6 = ss.getLineName().iterator(); 
		  Iterator<Integer> it7 = ss.getVehicleTypeID().iterator(); 
		  Iterator<String> it8 = ss.getVehicleTypeCode().iterator(); 
		  Iterator<String> it9 = ss.getVehicleTypeName().iterator(); 
		  Iterator<Float> it10 = ss.getVehicleTypeVehCost().iterator();
		  Iterator<Float> it11 = ss.getVehicleTypeKmCost().iterator(); 
		  Iterator<Float> it12 =ss.getVehicleTypeHourCost().iterator(); 
		  Iterator<Integer> it13 =ss.getVehicleTypeCapacity().iterator(); 
		  Iterator<Integer> it14 =ss.getVehicleTypeGroupID().iterator(); 
		  Iterator<String> it15 =ss.getVehicleTypeGroupCode().iterator(); 
		  Iterator<String> it16 = ss.getVehicleTypeGroupName().iterator(); 
		  Iterator<Integer> it17 = ss.getVehicleToVehicleTypeGroupVehTypeID().iterator(); 
		  Iterator<Integer> it18 = ss.getVehicleToVehicleTypeGroupVehTypeGroupID().iterator(); 
		  Iterator<Integer> it19 = ss.getVehicleCapToStopVehTypeID().iterator(); 
		  Iterator<Integer> it20 = ss.getVehicleCapToStopStoppointID().iterator();
		  Iterator<Integer> it21= ss.getVehicleCapToStopMin().iterator(); 
		  Iterator<Integer> it22 =ss.getVehicleCapToStopMax().iterator(); 
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
		  Iterator<Integer> it37 = ss.getDeadruntimeFromStopID().iterator();
		  Iterator<Integer> it38 = ss.getDeadruntimeToStopID().iterator(); 
		  Iterator<String> it39 = ss.getDeadruntimeFromTime().iterator(); 
		  Iterator<String> it40 = ss.getDeadruntimeToTime().iterator(); 
		  Iterator<Integer> it41 = ss.getDeadruntimeDistance().iterator(); 
		  Iterator<Integer> it42 = ss.getDeadruntimeRuntime().iterator();
		  Iterator<Integer> it43 = ss.getReliefpointID().iterator(); 
		  Iterator<String> it44 =ss.getReliefpointServiceJourneyID().iterator(); 
		  Iterator<Integer> it45 =ss.getReliefpointStoppointID().iterator(); 
		  Iterator<String> it46 =ss.getReliefpointStoptime().iterator(); 
		  Iterator<Integer> it47 = ss.getDayID().iterator(); 
		  Iterator<String> it48 = ss.getDayName().iterator(); 
		  Iterator<Integer> it49 = ss.getTripID().iterator(); 
		  Iterator<Integer> it50 = ss.getDayOne().iterator();
		  Iterator<Integer> it51 = ss.getDayTwo().iterator(); 
		  Iterator<Integer> it52 =ss.getDayThree().iterator(); 
		  Iterator<Integer> it53 =ss.getDayFour().iterator(); 
		  Iterator<Integer> it54 =ss.getDayFive().iterator(); 
		  Iterator<Integer> it55 =ss.getDaySix().iterator(); 
		  Iterator<Integer> it56 =ss.getDaySeven().iterator();

		  
		  while((it.hasNext()&&it2.hasNext()&&it3.hasNext())){
				 
			  stmnt.executeUpdate("INSERT INTO Stoppoint (StoppointID, Code, Name) VALUES('"+it.next()+"','"+it2.next()+"','"+it3.next()+"');");
			  }
		  
		  while((it4.hasNext()&&it5.hasNext()&&it6.hasNext())){
			  stmnt.executeUpdate("INSERT INTO Line (LineID, Code,Name) VALUES('"+it4.next()+"','"+it5.next()+"','"+it6.next()+"');"); 
		  }
		  
		  while((it7.hasNext()&&it8.hasNext()&&it9.hasNext()&&it10.hasNext()&&it11.hasNext()&&it12.hasNext()&&it13.hasNext())){
			  
			  stmnt.executeUpdate("INSERT INTO VehicleType (VehicleTypeID, Code, Name, VehCost, KmCost, HourCost, Capacity) VALUES('"+it7.next()+"','"+it8.next()+"','"+it9.next()+"','"+it10.next()+"','"+it11.next()+"','"+it12.next()+"','"+it13.next()+"');"); 
		  }
		  
		  while((it14.hasNext()&&it15.hasNext()&&it16.hasNext())){
			  stmnt.executeUpdate("INSERT INTO VehicleTypeGroup (VehicleTypeGroupID, Code, Name) VALUES('"+it14.next()+"','"+it15.next()+"','"+it16.next()+"');"); 
		  }
		  
		  while((it17.hasNext()&&it18.hasNext())){
			  stmnt.executeUpdate("INSERT INTO VehicleTypeToVehicleTypeGroup (VehTypeID,VehTypeGroupID) VALUES('"+it17.next()+"','"+it18.next()+"');"); 
		  }
		  
		  while((it19.hasNext()&&it20.hasNext()&&it21.hasNext()&&it22.hasNext())){
			  stmnt.executeUpdate("INSERT INTO VehicleCapToStoppoint (VehTypeID,StoppointID, Min, Max) VALUES('"+it19.next()+"','"+it20.next()+"','"+it21.next()+"','"+it22.next()+"');"); 
		  }
		  
		  while((it23.hasNext()&&it24.hasNext()&&it25.hasNext()&&it26.hasNext()&&it27.hasNext()&&it28.hasNext()&&it29.hasNext()&&it30.hasNext()&&it31.hasNext()&&it32.hasNext()&&it33.hasNext()&&it34.hasNext()&&it35.hasNext()&&it36.hasNext())){
			  
			  stmnt.executeUpdate("INSERT INTO ServiceJourney (ServiceJourneyID, LineID, FromStopID, ToStopID, DepTime, ArrTime, MinAheadTime, MinLayoverTime, VehTypeGroupID, MaxShiftBackwardSeconds, MaxShiftForwardSeconds, FromStopBreakFacility, ToStopBreakFacility, Code) VALUES('"+it23.next()+"','"+it24.next()+"','"+it25.next()+"','"+it26.next()+"','"+it27.next()+"','"+it28.next()+"','"+it29.next()+"','"+it30.next()+"','"+it31.next()+"','"+it32.next()+"','"+it33.next()+"','"+it34.next()+"','"+it35.next()+"','"+it36.next()+"');"); 
		  }
		  
		  while((it37.hasNext()&&it38.hasNext()&&it39.hasNext()&&it40.hasNext()&&it41.hasNext()&&it42.hasNext())){
			  
			  stmnt.executeUpdate("INSERT INTO Deadruntime (FromStopID, ToStopID, FromTime, ToTime, Distance, Runtime) VALUES('"+it37.next()+"','"+it38.next()+"','"+it39.next()+"','"+it40.next()+"','"+it41.next()+"','"+it42.next()+"');"); 
		  }
		  
		  while((it43.hasNext()&&it44.hasNext()&&it45.hasNext()&&it46.hasNext())){
			  stmnt.executeUpdate("INSERT INTO Reliefpoint (ReliefpointID, ServiceJourneyID, StoppointID, StopTime) VALUES('"+it43.next()+"','"+it44.next()+"','"+it45.next()+"','"+it46.next()+"');"); 
		  }
		  
		  
		  while((it49.hasNext()&&it50.hasNext()&&it51.hasNext()&&it52.hasNext()&&it53.hasNext()&&it54.hasNext())){
			  
			  stmnt.executeUpdate("INSERT INTO Days (TRIPID, d1, d2, d3, d4, d5) VALUES('"+it49.next()+"','"+it50.next()+"','"+it51.next()+"','"+it52.next()+"','"+it53.next()+"','"+it54.next()+"');");
		  }
		  while((it49.hasNext()&&it50.hasNext()&&it51.hasNext()&&it52.hasNext()&&it53.hasNext()&&it54.hasNext()&&it55.hasNext())){
			  
			  stmnt.executeUpdate("INSERT INTO Days (TRIPID, d1, d2, d3, d4, d5, d6) VALUES('"+it49.next()+"','"+it50.next()+"','"+it51.next()+"','"+it52.next()+"','"+it53.next()+"','"+it54.next()+"','"+it55.next()+"');");
		  }
		  while((it49.hasNext()&&it50.hasNext()&&it51.hasNext()&&it52.hasNext()&&it53.hasNext()&&it54.hasNext()&&it55.hasNext()&&it56.hasNext())){
	  
			  stmnt.executeUpdate("INSERT INTO Days (TRIPID, d1, d2, d3, d4, d5, d6, d7) VALUES('"+it49.next()+"','"+it50.next()+"','"+it51.next()+"','"+it52.next()+"','"+it53.next()+"','"+it54.next()+"','"+it55.next()+"','"+it56.next()+"');");
		  }
		  
		  System.out.println("Fahrplan importiert!");
	}catch(SQLException e){
		  System.out.println("Could not execute SQL-Query!");
		  e.printStackTrace(); }
	}
	
}
