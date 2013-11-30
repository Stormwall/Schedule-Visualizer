package sv.creation.adress.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sv.creation.adress.model.Block;
import sv.creation.adress.model.Blockelement;
import sv.creation.adress.model.Umlaufplan;

public class DBMatching {

	ArrayList<Block> umlauf = new ArrayList<Block>();
	ArrayList<Blockelement> fahrtZuUmlauf = new ArrayList<Blockelement>();

	Statement stmt;

	public void erstelleUmlaufplan() {

		DBConnection db = new DBConnection();
		db.initDBConnection();

		try {
			stmt = db.getConnection().createStatement();

			ResultSet rest1 = stmt
					.executeQuery("SELECT  DISTINCT * FROM Block");

			while (rest1.next()) {
				int id = Integer.parseInt(rest1.getString("BlockID"));
				int code = Integer.parseInt(rest1.getString("Code"));
				String name = rest1.getString("Name");
				umlauf.add(new Block(id, code, name));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void test() {

		try {

			ResultSet rest2 = stmt
					.executeQuery("SELECT DISTINCT be.ID, be.BlockID, be.ServiceJourneyID, sj.FromStopID, sj.ToStopID, sj.DepTime, sj.ArrTime, be.elementType, sj.Code FROM Blockelement AS be, Block, ServiceJourney AS sj, Sonderfahrt AS sf WHERE Block.BlockID=be.BlockID AND (be.ServiceJourneyID = sj.ServiceJourneyID OR be.ServiceJourneyID=sf.ServiceJourneyID);");

			while (rest2.next()) {
				int id = Integer.parseInt(rest2.getString("ID"));
				int blockID = Integer.parseInt(rest2.getString("BlockID"));
				String serviceJourneyID = rest2
						.getString("ServiceJourneyID");
				int fromStopID = Integer
						.parseInt(rest2.getString("FromStopID"));
				int toStopID = Integer.parseInt(rest2.getString("ToStopID"));
				String depTime = rest2.getString("DepTime");
				String arrTime = rest2.getString("ArrTime");
				int elementType = Integer.parseInt(rest2
						.getString("ElementType"));
				String serviceJourneyCode = rest2.getString("Code");

				fahrtZuUmlauf.add(new Blockelement(id, blockID,
						serviceJourneyID, fromStopID, toStopID, depTime,
						arrTime, elementType, serviceJourneyCode));
				System.out.println("Hat geklappt!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void erstelleUmlaufplanDaten() {
		// for(int i=0;i<=umlauf.size();i++){
		Umlaufplan umlaufplan = new Umlaufplan(1, umlauf, fahrtZuUmlauf);
		System.out
				.println(umlaufplan.getFahrtZuUmlauf().get(0).getFromStopID());
	}

}
