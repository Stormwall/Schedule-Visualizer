package sv.creation.adress;

import javafx.fxml.FXML;

public class RootLayoutController {

	// Reference to the main application
	private MainApplication mainApp;

	public void setMainApp(MainApplication mainApp) {
		this.mainApp = mainApp;
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
	 * Closes the application.
	 */
	@FXML
	private void handleExit() {
		System.exit(0);
	}

}
