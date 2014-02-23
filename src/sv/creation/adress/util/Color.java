package sv.creation.adress.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sv.creation.adress.database.DBConnection;

public class Color {

	
	private DBConnection dbc =new DBConnection();
	private Statement stmt;
	
	public void changeColor(int id,String farbe){
		
		this.dbc.initDBConnection();
		try {
			stmt.executeUpdate("UPDATE Color SET Farbe = '"+farbe+"' WHERE ID='"+id+"';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> readColorTable(){
		
		ArrayList<String> colors = new ArrayList<String>();
		this.dbc.initDBConnection();
		try {
			stmt = this.dbc.getConnection().createStatement();
			ResultSet rest1=stmt.executeQuery("SELECT Farbe FROM Colors;");
			while (rest1.next()) {
				String farbe=rest1.getString("Farbe");
				colors.add(farbe);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colors;
		}
}
