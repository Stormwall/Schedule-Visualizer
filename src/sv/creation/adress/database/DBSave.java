package sv.creation.adress.database;


import java.sql.SQLException;
import java.sql.Statement;

import sv.creation.adress.model.Umlaufplan;

public class DBSave {
	
	Statement stmt;

	public void saveUmlaufplan(Umlaufplan umlaufplan, String bezeichnung){
		
		DBConnection db = new DBConnection();
		db.initDBConnection();
		int pos=0;

		// Creating a sql query
		try {

			stmt = db.getConnection().createStatement();
			stmt.executeUpdate("INSERT INTO Umlaufplan (Bezeichnung, FahrplanID, DayID, Datum) VALUES ('"+bezeichnung+"', '"+umlaufplan.getFahrplanID()+"', '"+umlaufplan.getDayID()+"', CURRENT_DATE);");
			
			for (int i = 0; i < umlaufplan.getUmlauf().size(); i++) {
				stmt.executeUpdate("INSERT INTO Block (BlockID, VehTypeID, DepotID, UmlaufplanID)  VALUES('"+umlaufplan.getUmlauf().get(i).getId()+"', "
						  + "'"+umlaufplan.getUmlauf().get(i).getvehTypeID()+"', "
						  + "'"+umlaufplan.getUmlauf().get(i).getdepotID()+"', "
						  + "(SELECT up.ID FROM Umlaufplan AS up WHERE Bezeichnung = '"+bezeichnung+"'));");				
			}
			
			for (int i = 0; i < umlaufplan.getFahrtZuUmlauf().size(); i++) {
				pos++;
				if(umlaufplan.getFahrtZuUmlauf().get(i).getElementType()==1){
					stmt.executeUpdate("INSERT INTO Blockelement (ElementType, BlockID, ServiceJourneyID, UmlaufplanID, MatchingPos) VALUES('"+umlaufplan.getFahrtZuUmlauf().get(i).getElementType()+"', "
							  + " '"+umlaufplan.getFahrtZuUmlauf().get(i).getBlockID()+"', "
							  + " '"+umlaufplan.getFahrtZuUmlauf().get(i).getServiceJourneyID()+"', "
							  + "(SELECT ID FROM Umlaufplan WHERE Bezeichnung = '"+bezeichnung+"'),'"+pos+"');");
					
				}else{
					stmt.executeUpdate("INSERT INTO ExceptionalBlockelement ( DepTime, ArrTime, ServiceJourneyID, ElementType, BlockID, FromStopID, ToStopID, UmlaufplanID, MatchingPos) VALUES('"+umlaufplan.getFahrtZuUmlauf().get(i).getDepTime()+"','"+umlaufplan.getFahrtZuUmlauf().get(i).getArrTime()+"','"+umlaufplan.getFahrtZuUmlauf().get(i).getServiceJourneyID()+"','"+umlaufplan.getFahrtZuUmlauf().get(i).getElementType()+"', "
							  + "(SELECT b.ID FROM Block AS b WHERE b.ID='"+umlaufplan.getFahrtZuUmlauf().get(i).getBlockID()+"' AND b.UmlaufplanID= (SELECT up.ID FROM Umlaufplan AS up WHERE Bezeichnung = '"+bezeichnung+"')), "
							  + "(SELECT sp.ID FROM Stoppoint AS sp WHERE sp.ID='"+umlaufplan.getFahrtZuUmlauf().get(i).getFromStopID()+"' AND sp.FahrplanID='"+umlaufplan.getFahrplanID()+"'), "
							  + "(SELECT sp.ID FROM Stoppoint AS sp WHERE sp.ID='"+umlaufplan.getFahrtZuUmlauf().get(i).getToStopID()+"' AND sp.FahrplanID='"+umlaufplan.getFahrplanID()+"'), "
							  + "(SELECT up.ID FROM Umlaufplan AS up WHERE Bezeichnung = '"+bezeichnung+"'),'"+pos+"');");
				
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
