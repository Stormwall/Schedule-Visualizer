package sv.creation.adress;

import javafx.fxml.FXML;

public class RootLayoutController {
	


	// Reference to the main application
	private MainApplication mainApp;

	public void setMainApp(MainApplication mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Opens the ColorChange.
	 */
	@FXML
	private void handleColorChange() {
		mainApp.showColorChoice();
	}
	/**
	 * Opens the Kostenview.
	 */
	@FXML
	private void handleKennzahlen() {
		mainApp.showKennzahlen();
	}

	/**
	 * Opens the Databasesview.
	 */
	@FXML
	private void handleDatenbank() {
		mainApp.showDatenbank();
	}
	
	/**
	 * Opens the Contactview.
	 */
	@FXML
	private void handleContact() {
		String fehlerA = "Grafische Visualisierung:\nErik Haus\nerikhaus.home@googlemail.com\n\nDatenbankverwaltung:\nDennis Than\ndennis.than703@gmail.com\n\nKennzahlen und Organisation:\nFabian Riebold\nf.riebold@hotmail.de\n\nDimitrios Anapliotis\ndimitrios_anapliotis@yahoo.de ";
		String fehlerB = "Kontaktinformation";
		String fehlerC = "Kontakt";
		this.mainApp.informationMeldung(fehlerA, fehlerB, fehlerC);
	}

	/**
	 * Closes the application.
	 */
	@FXML
	private void handleExit() {
		System.exit(0);
	}


}
