package sv.creation.adress;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class KennzahlenLayoutController {

	private Stage dialogStage;
	
	// Referenz zur MainApp

	private MainApplication mainApp;
	

	  /**
	  * Initializes the controller class. This method is automatically called
	  * after the fxml file has been loaded.
	  */
	  
	@FXML
	  private void initialize() {
	      
	  }
	
	/**
	 * Opens the Kostenview.
	 */
	@FXML
	private void handleStatistikenUplanSingle() {
		
		this.mainApp.showStatistikUPlanSingle();
	}
	
	/**
	 * Opens the Kostenview.
	 */
	@FXML
	private void handleKosten() {
		
	}
	// Methode zum Beenden des PopUp
	
	public void endStage(){
		
		dialogStage.close();
	}
	
	//Zuordnungsmethoden

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

