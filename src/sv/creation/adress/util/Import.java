package sv.creation.adress.util;

import java.io.File;

public class Import {

	private boolean umlaufplanImportFailed = false;
	private boolean dienstplanImportDiensttypenFailed = false;
	private boolean dienstplanImportUmlaufplanFailed = false;
	private boolean dienstplanImportFahrplanFailed = false;
	private boolean fahrplanImportFailed = false;
	private boolean diensttypenImportFailed = false;
	private boolean szenarienImportFailed = false;

	public void importFile(File file) {

		StringSplitter ss = StringSplitter.getInstance();

		if (file.getName().startsWith("vs")) {
			ss.readTxtUmlaufplan(file.getPath());
			if (ss.isUmlaufplanImportiert() == false) {
				umlaufplanImportFailed = true;
			}
		} else if (file.getName().startsWith("cs")) {
			ss.readTxtDienstplan(file.getPath());
			if (ss.isDienstplanImportiert() == false
					&& ss.isDienstplanFahrplanFail() == true) {
				dienstplanImportFahrplanFailed = true;
			} else if (ss.isDienstplanImportiert() == false
					&& ss.isDienstplanUmlaufplanFail() == true) {
				dienstplanImportUmlaufplanFailed = true;
			} else if (ss.isDienstplanImportiert() == false
					&& ss.isDienstplanDiensttypenFail() == true) {
				dienstplanImportDiensttypenFailed = true;
			}
		} else if (file.getName().startsWith("sc")) {
			ss.readTxtFromSzenario(file.getPath());
			if (ss.isSzenarienImportiert() == false) {
				szenarienImportFailed = true;
			}
		} else if (file.getName().startsWith("dt")) {
			ss.readTxtDiensttypen(file.getPath());
			if (ss.isDiensttypenImportiert() == false) {
				diensttypenImportFailed = true;
			}
		} else {
			ss.readTxtFahrplan(file.getPath());
			if (ss.isFahrplanImportiert() == false) {
				fahrplanImportFailed = true;
			}
		}

	}

	public boolean isUmlaufplanImportFailed() {
		return umlaufplanImportFailed;
	}


	public boolean isDienstplanImportDiensttypenFailed() {
		return dienstplanImportDiensttypenFailed;
	}

	public boolean isDienstplanImportUmlaufplanFailed() {
		return dienstplanImportUmlaufplanFailed;
	}

	public boolean isDienstplanImportFahrplanFailed() {
		return dienstplanImportFahrplanFailed;
	}

	public boolean isFahrplanImportFailed() {
		return fahrplanImportFailed;
	}

	public boolean isDiensttypenImportFailed() {
		return diensttypenImportFailed;
	}

	public boolean isSzenarienImportFailed() {
		return szenarienImportFailed;
	}

}
