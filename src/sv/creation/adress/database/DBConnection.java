package sv.creation.adress.database;

import java.sql.Statement; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;

import sv.creation.adress.util.StringSplitter;


public class DBConnection {

	
	public static DBConnection instance=null;
	private static Connection connection;
	//Path where a empty database file was created
	public static final String DB_PATH =System.getProperty("user.home")+"/"+"testdb.db";
	
	
	static{
		try{
			Class.forName("org.sqlite.JDBC");
		}catch(ClassNotFoundException e){
			System.err.println("Treiber konnte nicht geladen werden.");
			e.printStackTrace();
		}
	}
	
	public DBConnection(){
		
	}
	
	//Singleton
	public static DBConnection getInstance(){
		if(instance==null){
			instance=new DBConnection();	
		}return instance;
	}
	
	public void initDBConnection(){
		
		try{
			if(connection!=null)
				return;
			System.out.println("Creating DB Connection...");
			connection=DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
			if(!connection.isClosed())
				System.out.println("...Connection established!");		
				}
		catch(SQLException e){
			throw new RuntimeException(e);
		}
		
	}
	
	public void closeConnection(){
		
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
	
	public void createTables(){
		
		try{
			Statement stmnt=connection.createStatement();
			//Checks if table exists or not
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Diensttyp (DutyID INTEGER NOT NULL PRIMARY KEY, DutyType VARCHAR(50) NOT NULL);");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Dienst (BlockID INTEGER NOT NULL PRIMARY KEY, ServiceJourneyID INTEGER NOT NULL, FromStopID INTEGER NOT NULL, ToStopID INTEGER NOT NULL, DepTime date NOT NULL, ArrTime date NOT NULL, ElementType INTEGER NOT NULL, ServiceJourneyCode INTEGER);");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS Umlauf (BlockID INTEGER NOT NULL, Code INTEGER NOT NULL, Name INTEGER NOT NULL);");
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS FahrtZuUmlauf (ID INTEGER PRIMARY KEY AUTOINCREMENT, BlockID INTEGER NOT NULL, ServiceJourneyID INTEGER NOT NULL, FromStopID INTEGER NOT NULL, ToStopID INTEGER NOT NULL, DepTime VARCHAR(10) NOT NULL, ArrTime VARCHAR(10) NOT NULL, ElementType INTEGER NOT NULL);");
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
	
	public void fillUmlaufplanIntoTables(){
		
		StringSplitter ss =new StringSplitter();
		ss.readTxtUmlaufplan();
		
		
		try{
			Statement stmnt=connection.createStatement();
			//Fill values in specific tables
			
			Iterator<Integer> it = ss.getId().iterator();
			Iterator<Integer> it2 = ss.getVehTypeID().iterator();
			Iterator<Integer> it3 = ss.getDepotID().iterator();
			Iterator<Integer> it4 = ss.getBlockelementBlockID().iterator();
			Iterator<String> it5 = ss.getBlockelementServiceJourneyID().iterator();
			Iterator<Integer> it6 = ss.getBlockelementFromStopID().iterator();
			Iterator<Integer> it7 = ss.getBlockelementToStopID().iterator();
			Iterator<String> it8 = ss.getBlockelementDepTime().iterator();
			Iterator<String> it9 = ss.getBlockelementArrTime().iterator();
			Iterator<Integer> it10 = ss.getBlockelementElementType().iterator();

			
			while((it.hasNext()&&it2.hasNext()&&it3.hasNext())){
			stmnt.executeUpdate("INSERT INTO Umlauf VALUES('"+it.next()+"','"+it2.next()+"','"+it3.next()+"');");
			}
			
			while(it4.hasNext()&&it5.hasNext()&&it6.hasNext()&&it7.hasNext()&&it8.hasNext()&&it9.hasNext()&&it10.hasNext())
			stmnt.executeUpdate("INSERT INTO FahrtZuUmlauf (BlockID, ServiceJourneyID, FromStopID, ToStopID, DepTime, ArrTime, ElementType) VALUES('"+it4.next()+"','"+it5.next()+"','"+it6.next()+"','"+it7.next()+"','"+it8.next()+"','"+it9.next()+"','"+it10.next()+"');");
			}catch(SQLException e){
			System.out.println("Could not execute SQL-Query!");
			e.printStackTrace();
		}
		
	}
}
