package sv.creation.adress.database;

import java.sql.Statement; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

	
	private static DBConnection instance=null;
	private static Connection connection;
	//Path where a empty database file was created
	private static final String DB_PATH =System.getProperty("user.home")+"/"+"testdb.db";
	
	
	static{
		try{
			Class.forName("org.sqlite.JDBC");
		}catch(ClassNotFoundException e){
			System.err.println("Treiber konnte nicht geladen werden.");
			e.printStackTrace();
		}
	}
	
	private DBConnection(){
		
	}
	
	//Singleton
	public static DBConnection getInstance(){
		if(instance==null){
			instance=new DBConnection();	
		}return instance;
	}
	
	public static void initDBConnection(){
		
		try{
			if(connection!=null)
				return;
			System.out.println("Creating DB Connection...");
			connection=DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
			if(!connection.isClosed())
				System.out.println("...Connection established!");		
				createTables();
				fillTables();}
		catch(SQLException e){
			throw new RuntimeException(e);
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			public void run(){
				try{
					if(!connection.isClosed()&&connection!=null){
						connection.close();
						if(connection.isClosed())
							System.out.println("Connection to Database closed!");
						}
						}catch(SQLException e){
							e.printStackTrace();					
				}
			}
		});
	}
	
	public static void createTables(){
		
		try{
			Statement stmnt=connection.createStatement();
			//Checks if table exists or not
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Diensttyp (DutyID INTEGER NOT NULL PRIMARY KEY, DutyType VARCHAR(50) NOT NULL);");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Dienst (BlockID INTEGER NOT NULL PRIMARY KEY, ServiceJourneyID INTEGER NOT NULL, FromStopID INTEGER NOT NULL, ToStopID INTEGER NOT NULL, DepTime date NOT NULL, ArrTime date NOT NULL, ElementType INTEGER NOT NULL, ServiceJourneyCode INTEGER);");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Umlauf (BlockID INTEGER NOT NULL PRIMARY KEY, DutyType VARCHAR(50));");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS FahrtZuUmlauf (ID INTEGER PRIMARY KEY AUTOINCREMENT, BlockID INTEGER NOT NULL, ServiceJourneyID INTEGER NOT NULL, FromStopID INTEGER NOT NULL, ToStopID INTEGER NOT NULL, DepTime VARCHAR(10) NOT NULL, ArrTime VARCHAR(10) NOT NULL, ElementType INTEGER NOT NULL, ServiceJourneyCode INTEGER);");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Haltestellen (ID INTEGER NOT NULL PRIMARY KEY, Code INTEGER NOT NULL, Name INTEGER NOT NULL);");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Linien (ID INTEGER NOT NULL PRIMARY KEY, Code INTEGER NOT NULL, Name INTEGER NOT NULL);");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Fahrzeugtypen (ID INTEGER NOT NULL PRIMARY KEY, Code INTEGER NOT NULL, Name INTEGER NOT NULL, VehCost INTEGER NOT NULL, KmCost INTEGER NOT NULL, HourCost INTEGER NOT NULL, Capacity INTEGER NOT NULL);");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Fahrzeugtypgruppen (ID INTEGER NOT NULL PRIMARY KEY, Code INTEGER NOT NULL, Name INTEGER NOT NULL);");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS FahrzeugtypZuFahrzeugtypgruppe (ID INTEGER PRIMARY KEY AUTOINCREMENT, VehTypeID INTEGER NOT NULL, VehTypeGroupID INTEGER NOT NULL);");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS FahrzeugKapazitaetenZuHaltestelle (ID INTEGER PRIMARY KEY AUTOINCREMENT, VehTypeID INTEGER NOT NULL, StoppointID INTEGER NOT NULL, Min INTEGER NOT NULL, Max INTEGER NOT NULL);");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Linienfahrten (ID INTEGER NOT NULL PRIMARY KEY, LineID INTEGER NOT NULL, FromStopID INTEGER NOT NULL, ToStopID INTEGER NOT NULL, DepTime VARCHAR(10) NOT NULL, ArrTime VARCHAR(10) NOT NULL, MinAheadTime INTEGER NOT NULL, MinLayoverTime INTEGER NOT NULL, VehTypeGroupID INTEGER NOT NULL, MaxShiftBackwardSeconds INTEGER NOT NULL, MaxShiftForwardSeconds INTEGER NOT NULL, FromStopBreakFacility INTEGER NOT NULL, ToStopBreakFacility INTEGER NOT NULL, Code INTEGER);");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Verbindungen (ID INTEGER PRIMARY KEY AUTOINCREMENT, FromStopID INTEGER NOT NULL, ToStopID INTEGER NOT NULL, FromTime VARCHAR(10) NOT NULL, ToTime VARCHAR(10) NOT NULL, Distance INTEGER NOT NULL, RunTime INTEGER NOT NULL);");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Abloesepunkte (ID INTEGER NOT NULL PRIMARY KEY, ServiceJourneyID INTEGER NOT NULL, StoppointID INTEGER NOT NULL, StopTime VARCHAR(10) NOT NULL);");
			
		}catch(SQLException e){
			System.out.println("Could not execute SQL-Query!");
			e.printStackTrace();
		}
	}
	
	public static void fillTables(){
		
		try{
			Statement stmnt=connection.createStatement();
			//Fill values in specific tables
			stmnt.executeUpdate("INSERT INTO books VALUES('Mustermann','Mustermanns Buch','01.01.2012','60','29.99');");
		}catch(SQLException e){
			System.out.println("Could not execute SQL-Query!");
			e.printStackTrace();
		}
		
	}
}
