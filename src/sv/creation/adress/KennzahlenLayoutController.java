package sv.creation.adress;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class KennzahlenLayoutController {

	private Stage dialogStage;
	

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
	
	
}

