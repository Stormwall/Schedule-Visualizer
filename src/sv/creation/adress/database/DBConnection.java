package sv.creation.adress.database;

import java.sql.Statement; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

	
	private static final DBConnection dbcontroller=new DBConnection();
	private static Connection connection;
	//Pfad, wo die Datenbank bzw. die Datei gespeichert wird
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
		return dbcontroller;
	}
	
	public void initDBConnection(){
		
		try{
			if(connection!=null)
				return;
			System.out.println("Creating DB Connection...");
			connection=DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
			if(!connection.isClosed())
				System.out.println("...Connection established!");}
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
	
	public void handleDB(){
		
		try{
			Statement stmnt=connection.createStatement();
			stmnt.executeUpdate("CREATE TABLE books (author, title, publication, pages, price);");
		}catch(SQLException e){
			System.out.println("Could not execute SQL-Query!");
			e.printStackTrace();
		}
	}
	
}

