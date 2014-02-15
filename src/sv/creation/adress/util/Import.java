package sv.creation.adress.util;

import java.io.File;


public class Import {

	public void importFile(File file) {

		StringSplitter ss = StringSplitter.getInstance();

		if (file.getName().startsWith("vs")) {
			ss.readTxtUmlaufplan(file.getPath());
		} else if (file.getName().startsWith("cs")) {
			ss.readTxtDienstplan(file.getPath());
		} else if (file.getName().startsWith("sc")) {
			ss.readTxtFromSzenario(file.getPath());
		} else if (file.getName().startsWith("dt")) {
			ss.readTxtDiensttypen(file.getPath());
		} else {
			ss.readTxtFahrplan(file.getPath());
		}

	}

}
