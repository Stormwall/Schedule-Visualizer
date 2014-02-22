package sv.creation.adress;

import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.util.Kennzahlenberechnung;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class P_VergleichLayoutController {

	//  Arbeitsvariablen
	@FXML
	private Label ms;
	@FXML
	private Label m1;
	@FXML
	private Label m2;
	@FXML
	private Label m3;
	@FXML
	private Label m4;
	@FXML
	private Label m5;
	@FXML
	private Label ds;
	@FXML
	private Label d1;
	@FXML
	private Label d2;
	@FXML
	private Label d3;
	@FXML
	private Label d4;
	@FXML
	private Label d5;
	@FXML
	private Label mis;
	@FXML
	private Label mi1;
	@FXML
	private Label mi2;
	@FXML
	private Label mi3;
	@FXML
	private Label mi4;
	@FXML
	private Label mi5;
	@FXML
	private Label dos;
	@FXML
	private Label do1;
	@FXML
	private Label do2;
	@FXML
	private Label do3;
	@FXML
	private Label do4;
	@FXML
	private Label do5;
	@FXML
	private Label fs;
	@FXML
	private Label f1;
	@FXML
	private Label f2;
	@FXML
	private Label f3;
	@FXML
	private Label f4;
	@FXML
	private Label f5;
	@FXML
	private Label result;

	// Referenz zur MainApp
	private Stage dialogStage;
	private MainApplication mainApp;
	private Fahrplan fahrplan;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {

	}

	// Methode zum Berrechnen der Kosten

	public void berrechnen() {

		Kennzahlenberechnung kb = new Kennzahlenberechnung();

		double result = kb.pVergleich(this.fahrplan);
		this.result.setText(String.valueOf(result));
	}

	// Methode zum Beenden des PopUp

	public void endStage() {

		dialogStage.close();
	}

	public MainApplication getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApplication mainApp) {
		this.mainApp = mainApp;
	}

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public Fahrplan getFahrplan() {
		return fahrplan;
	}

	public void setFahrplan(Fahrplan fahrplan) {
		this.fahrplan = fahrplan;
		
		berrechnen();
	}

}
