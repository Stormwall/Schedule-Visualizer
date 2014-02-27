package sv.creation.adress.database;

import java.sql.SQLException;
import java.sql.Statement;

import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Umlaufplan;

public class DBSave {

	Statement stmt;

	//Speichert den Umlauflplan in die Datenbank
	public void saveUmlaufplan(Umlaufplan umlaufplan, String name) {

		DBConnection db = new DBConnection();
		db.initDBConnection();
		int pos = 0;

		// Creating a sql query
		try {

			stmt = db.getConnection().createStatement();
			stmt.executeUpdate("INSERT INTO Umlaufplan (Bezeichnung, Name, FahrplanID, DayID, Datum) VALUES ('"
					+ umlaufplan.getBezeichnung()
					+ "','"
					+ name
					+ "', '"
					+ umlaufplan.getFahrplanID()
					+ "', '"
					+ umlaufplan.getDayID() + "', CURRENT_DATE);");

			for (int i = 0; i < umlaufplan.getUmlauf().size(); i++) {
				stmt.executeUpdate("INSERT INTO Block (BlockID, VehTypeID, DepotID, UmlaufplanID)  VALUES('"
						+ umlaufplan.getUmlauf().get(i).getId()
						+ "', "
						+ "'"
						+ umlaufplan.getUmlauf().get(i).getvehTypeID()
						+ "', "
						+ "'"
						+ umlaufplan.getUmlauf().get(i).getdepotID()
						+ "', "
						+ "(SELECT up.ID FROM Umlaufplan AS up WHERE Bezeichnung = '"
						+ umlaufplan.getBezeichnung()
						+ "' AND Name = '"
						+ name
						+ "'));");
			}

			for (int i = 0; i < umlaufplan.getFahrtZuUmlauf().size(); i++) {
				pos++;
				if (umlaufplan.getFahrtZuUmlauf().get(i).getElementType() == 1) {
					stmt.executeUpdate("INSERT INTO Blockelement (ElementType, BlockID, ServiceJourneyID, UmlaufplanID, MatchingPos) VALUES('"
							+ umlaufplan.getFahrtZuUmlauf().get(i)
									.getElementType()
							+ "', "
							+ "(SELECT b.ID FROM Block AS b WHERE b.BlockID='"
							+ umlaufplan.getFahrtZuUmlauf().get(i).getBlockID()
							+ "' AND b.UmlaufplanID= (SELECT up.ID FROM Umlaufplan AS up WHERE Bezeichnung = '"
							+ umlaufplan.getBezeichnung()
							+ "' AND Name = '"
							+ name
							+ "')), "
							+ "(SELECT sj.ID FROM ServiceJourney AS sj WHERE sj.ID= '"
							+ umlaufplan.getFahrtZuUmlauf().get(i)
									.getServiceJourneyID()
							+ "'), "
							+ "(SELECT ID FROM Umlaufplan WHERE Bezeichnung = '"
							+ umlaufplan.getBezeichnung()
							+ "' AND Name = '"
							+ name + "'),'" + pos + "');");

				} else {

					stmt.executeUpdate("INSERT INTO ExceptionalBlockelement ( DepTime, ArrTime, ServiceJourneyID, ElementType, BlockID, FromStopID, ToStopID, UmlaufplanID, MatchingPos) VALUES('"
							+ umlaufplan.getFahrtZuUmlauf().get(i).getDepTime()
							+ "','"
							+ umlaufplan.getFahrtZuUmlauf().get(i).getArrTime()
							+ "','"
							+ umlaufplan.getFahrtZuUmlauf().get(i)
									.getServiceJourneyID()
							+ "','"
							+ umlaufplan.getFahrtZuUmlauf().get(i)
									.getElementType()
							+ "', "
							+ "(SELECT b.ID FROM Block AS b WHERE b.BlockID='"
							+ umlaufplan.getFahrtZuUmlauf().get(i).getBlockID()
							+ "' AND b.UmlaufplanID= (SELECT up.ID FROM Umlaufplan AS up WHERE Bezeichnung = '"
							+ umlaufplan.getBezeichnung()
							+ "' AND Name = '"
							+ name
							+ "')), "
							+ "(SELECT sp.ID FROM Stoppoint AS sp WHERE sp.ID='"
							+ umlaufplan.getFahrtZuUmlauf().get(i)
									.getFromStopID()
							+ "' AND sp.FahrplanID='"
							+ umlaufplan.getFahrplanID()
							+ "'), "
							+ "(SELECT sp.ID FROM Stoppoint AS sp WHERE sp.ID='"
							+ umlaufplan.getFahrtZuUmlauf().get(i)
									.getToStopID()
							+ "' AND sp.FahrplanID='"
							+ umlaufplan.getFahrplanID()
							+ "'), "
							+ "(SELECT up.ID FROM Umlaufplan AS up WHERE Bezeichnung = '"
							+ umlaufplan.getBezeichnung()
							+ "' AND Name = '"
							+ name + "'),'" + pos + "');");

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Speichert den Dienstplan in die Datenbank
	public void saveDienstplan(Dienstplan dienstplan, String name) {

		DBConnection db = new DBConnection();
		db.initDBConnection();
		int pos = 0;

		// Creating a sql query
		try {

			stmt = db.getConnection().createStatement();
			stmt.executeUpdate("INSERT INTO Dienstplan (Bezeichnung, Name, FahrplanID,UmlaufplanID, DayID, Datum) VALUES ('"
					+ dienstplan.getBezeichnung()
					+ "','"
					+ name
					+ "', '"
					+ dienstplan.getFahrplanID()
					+ "','"
					+ dienstplan.getUmlaufplanID()
					+ "','"
					+ dienstplan.getDayID() + "', CURRENT_DATE);");

			for (int i = 0; i < dienstplan.getDuty().size(); i++) {
				stmt.executeUpdate("INSERT INTO Duty (DutyID, DutyTypeID, DienstplanID)  VALUES('"
						+ dienstplan.getDuty().get(i).getId()
						+ "', "
						+ "(SELECT dt.ID FROM Dutytype AS dt WHERE dt.ID='"
						+ dienstplan.getDuty().get(i).getType()
						+ "'), "
						+ "(SELECT up.ID FROM Dienstplan AS up WHERE Bezeichnung = '"
						+ dienstplan.getBezeichnung()
						+ "' AND Name = '"
						+ name
						+ "'));");
			}

			for (int i = 0; i < dienstplan.getDutyelement().size(); i++) {
				pos++;
				if (dienstplan.getDutyelement().get(i).getElementType() == 1) {
					stmt.executeUpdate("INSERT INTO Dutyelement (ElementType, BlockID, DutyID, ServiceJourneyID, DienstplanID, MatchingPos) VALUES('"
							+ dienstplan.getDutyelement().get(i)
									.getElementType()
							+ "', "
							+ "(SELECT b.ID FROM Block AS b WHERE b.BlockID='"
							+ dienstplan.getDutyelement().get(i).getBlockID()
							+ "' AND b.UmlaufplanID=(SELECT UmlaufplanID FROM Dienstplan WHERE Bezeichnung = '"
							+ dienstplan.getBezeichnung()
							+ "' AND Name = '"
							+ name
							+ "')), "
							+ "(SELECT d.ID FROM Duty AS d WHERE d.DutyID='"
							+ dienstplan.getDutyelement().get(i).getDutyID()
							+ "' AND d.DienstplanID= (SELECT dp.ID FROM Dienstplan AS dp WHERE Bezeichnung = '"
							+ dienstplan.getBezeichnung()
							+ "' AND Name = '"
							+ name
							+ "')), "
							+ "(SELECT sj.ID FROM ServiceJourney AS sj WHERE sj.ID= '"
							+ dienstplan.getDutyelement().get(i)
									.getServiceJourneyID()
							+ "'), "
							+ "(SELECT ID FROM Dienstplan WHERE Bezeichnung = '"
							+ dienstplan.getBezeichnung()
							+ "' AND Name = '"
							+ name + "'),'" + pos + "');");

				} else {
					stmt.executeUpdate("INSERT INTO ExceptionalDutyelement ( DepTime, ArrTime, ServiceJourneyID, ElementType, DutyID,  BlockID, FromStopID, ToStopID, DienstplanID, MatchingPos) VALUES('"
							+ dienstplan.getDutyelement().get(i).getDepTime()
							+ "','"
							+ dienstplan.getDutyelement().get(i).getArrTime()
							+ "','"
							+ dienstplan.getDutyelement().get(i)
									.getServiceJourneyID()
							+ "','"
							+ dienstplan.getDutyelement().get(i)
									.getElementType()
							+ "', "
							+ "(SELECT d.ID FROM Duty AS d WHERE d.DutyID='"
							+ dienstplan.getDutyelement().get(i).getDutyID()
							+ "' AND d.DienstplanID= (SELECT dp.ID FROM Dienstplan AS dp WHERE Bezeichnung = '"
							+ dienstplan.getBezeichnung()
							+ "' AND Name = '"
							+ name
							+ "')), '"
							+ dienstplan.getDutyelement().get(i).getBlockID()
							+ "',"
							+ "'"
							+ dienstplan.getDutyelement().get(i)
									.getFromStopID()
							+ "', "
							+ " '"
							+ dienstplan.getDutyelement().get(i).getToStopID()
							+ "', "
							+ "(SELECT up.ID FROM Dienstplan AS up WHERE Bezeichnung = '"
							+ dienstplan.getBezeichnung()
							+ "' AND Name = '"
							+ name + "'),'" + pos + "');");

				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
