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
			stmnt.executeUpdate("CREATE TABLE IF NOT EXISTS books (author, title, publication, pages, price);");
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
