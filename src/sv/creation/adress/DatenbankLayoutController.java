package sv.creation.adress;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class DatenbankLayoutController {
	
	private Stage dialogStage;
	
	
	/**
	  * Initializes the controller class. This method is automatically called
	  * after the fxml file has been loaded.
	  */
	  
	@FXML
	private void initialize() {
	      
	}
	
	// Methode zum Beenden des PopUp
	
		public void endStage(){
			
			dialogStage.close();
		}	
	
	
	
	

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}
