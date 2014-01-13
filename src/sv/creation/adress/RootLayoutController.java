package sv.creation.adress;

import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

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
	private void handleKosten() {
		mainApp.showKosten();
	}

	/**
	 * Opens the Databasesview.
	 */
	@FXML
	private void handleDatenbank() {
		mainApp.showDatenbank();
	}

	/**
	 * Opens the Helpview.
	 */
	@FXML
	private void handleHandbuch() {
		mainApp.showHandbuch();
	}

	/**
	 * Opens a FileChooser to let the user select an address book to load.
	 */
	@FXML
	private void handleOpen() {
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"TXT files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

		if (file != null) {
			System.out.println("kein File");
		}
	}

	/**
	 * Closes the application.
	 */
	@FXML
	private void handleExit() {
		System.exit(0);
	}

}
