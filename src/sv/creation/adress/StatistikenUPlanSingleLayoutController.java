package sv.creation.adress;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class StatistikenUPlanSingleLayoutController {
	
	// Arbeitselemente der Stage
	
	@FXML
	private ScrollPane tablePane;
	@FXML
	private Label aGesamt;
	@FXML
	private Label aServicefahrten;
	@FXML
	private Label aVerbindungsfahrt;
	@FXML
	private Label aWarten;
	@FXML
	private Label aEinrueckfahrt;
	@FXML
	private Label aAusrueckfahrt;
	@FXML
	private Label aAnzahllinien;
	@FXML
	private Label dGesamt;
	@FXML
	private Label dServicefahrten;
	@FXML
	private Label dVerbindungsfahrt;
	@FXML
	private Label dWarten;
	@FXML
	private Label dEinrueckfahrt;
	@FXML
	private Label dAusrueckfahrt;
	@FXML
	private Label dAnzahllinien;
	@FXML
	private Label duGesamt;
	@FXML
	private Label duServicefahrten;
	@FXML
	private Label duVerbindungsfahrt;
	@FXML
	private Label duWarten;
	@FXML
	private Label duEinrueckfahrt;
	@FXML
	private Label duAusrueckfahrt;
	@FXML
	private Label duAnzahllinien;
	
	@FXML
	private PieChart graphic;
	

	private Stage dialogStage;
	

	  /**
	  * Initializes the controller class. This method is automatically called
	  * after the fxml file has been loaded.
	  */
	  
	@FXML
	  private void initialize() {
	      
	  }	

	
	//Zuordnungsmethoden

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	
}

