package sv.creation.adress;

import java.io.File;

import sv.creation.adress.util.Import;
import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DatenbankLayoutController {

	private Stage dialogStage;

	private MainApplication mainApp;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {

	}

	// Methode zum Beenden des PopUp

	public void endStage() {

		dialogStage.close();
	}

	/**
	 * Imports files
	 * 
	 * @return
	 */
	public void handleImport() {

		FileChooser fileChooser = new FileChooser();

		// // Set extension filter
		// FileChooser.ExtensionFilter extFilter = new
		// FileChooser.ExtensionFilter(
		// "TXT files (*.txt)", "*.txt");
		// fileChooser.getExtensionFilters().add(extFilter);

		// Show open file dialog
		File file = fileChooser.showOpenDialog(dialogStage);

		Dialogs.showInformationDialog(dialogStage,
				"I have a great message for you!", "Information Dialog",
				"title");

		Import im = new Import();
		im.importFile(file);

	}

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}
