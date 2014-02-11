package sv.creation.adress.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sv.creation.adress.database.DBConnection;

public class Vergleich {

	public int[] vergleicheFahrplan(int id, int day) {

		int[] anzahlServiceFahrten = new int[1440];
		String dayString = "d" + day;
		DBConnection db = new DBConnection();
		db.initDBConnection();
		ArrayList<Integer> trips = new ArrayList<Integer>();
		Statement stmt;

		// Creating a sql query
		try {

			for (int i = 0; i <= 1439; i++) {
				String time = timeFormat(i);
				stmt = db.getConnection().createStatement();
				ResultSet rest1 = stmt
						.executeQuery("SELECT COUNT(sj.ServiceJourneyID) AS AnzahlFahrten FROM ServiceJourney AS sj, Days AS d WHERE sj.ID=d.TRIPID AND d."
								+ dayString
								+ "='1' AND d.FahrplanID= '"
								+ id
								+ "'AND d.FahrplanID=sj.FahrplanID AND '"
								+ time + "' BETWEEN sj.DepTime AND sj.ArrTime;");
				while (rest1.next()) {
					int trip = rest1.getInt("AnzahlFahrten");
					anzahlServiceFahrten[i] = trip;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// the sum of all servicejourneys will be count if some servicejourneys
		// exists
		if (!trips.isEmpty()) {

			for (int i = 0; i < trips.size(); i++) {

				// Creating a sql query
				try {
					stmt = db.getConnection().createStatement();
					ResultSet rest1 = stmt
							.executeQuery("SELECT TRIPID FROM Days WHERE "
									+ dayString + "='1' AND FahrplanID= '" + id
									+ "';");
					while (rest1.next()) {
						int trip = rest1.getInt("TRIPID");
						trips.add(trip);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		return anzahlServiceFahrten;

	}

	public String timeFormat(int i) {

		String time = "";
		int min = 0;
		int hour = 0;
		if (i >= 60) {
			hour = i / 60;
			min = i - (hour * 60);
		}
		if (hour < 10 && min < 10) {
			time = "0" + hour + ":" + min + "0";
		} else {
			time = hour + ":" + min;
		}
		return time;
	}

}
