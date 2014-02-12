package sv.creation.adress.util;

import java.io.File;

import sv.creation.adress.database.DBConnection;

public class Import {

	public void importFile(File file) {

		DBConnection dbc = new DBConnection();
		StringSplitter ss = StringSplitter.getInstance();

		if (file.getName().startsWith("vs")) {
			ss.readTxtUmlaufplan(file.getPath());
		} else if (file.getName().startsWith("cs")) {
			ss.readTxtDienstplan(file.getPath());
		} else if (file.getName().startsWith("sc")) {
			ss.readTxtFromSzenario(file.getPath());
			dbc.fillSzenarioIntoTables();
		} else if (file.getName().startsWith("dt")) {
			ss.readTxtDiensttypen(file.getPath());
		} else {
			ss.readTxtFahrplan(file.getPath());
			dbc.fillFahrplanIntoTables(ss);
		}

	}

}
