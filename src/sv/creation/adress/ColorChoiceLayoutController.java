package sv.creation.adress;


import javafx.fxml.FXML;
import javafx.stage.Stage;


public class ColorChoiceLayoutController {
	

	private Stage dialogStage;
	private MainApplication mainApp;


	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {



	}


	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public MainApplication getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApplication mainApp) {
		this.mainApp = mainApp;
	}

}



