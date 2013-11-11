package sv.creation.adress;


import javafx.fxml.FXML;

public class RootLayoutController {
	
	// Reference to the main application
	private MainApplication mainApp;
	

	public void setMainApp(MainApplication mainApp) {
		this.mainApp = mainApp;
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
	 * Closes the application.
	 */
	@FXML
	private void handleExit() {
		System.exit(0);
	}

}
