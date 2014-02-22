package sv.creation.adress;

import java.util.ArrayList;

import sv.creation.adress.model.Umlaufplan;
import sv.creation.adress.util.ScheduleCosts;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class KostenLayoutControllerU {

	@FXML
	private Button berrechnen;
	@FXML
	private TextField vk;
	@FXML
	private TextField fpf;
	@FXML
	private Label fixkostenGesamt;
	@FXML
	private Label variableKostenGesamt;
	@FXML
	private Label gesamtKosten;

	// Referenz zur MainApp
	private Stage dialogStage;
	private MainApplication mainApp;
	
	private ArrayList<Umlaufplan> umlaufplanliste = new ArrayList<Umlaufplan>();

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {

	}

	// Methode zum Berrechnen der Kosten

	public void berrechnen() {

		ScheduleCosts sc = new ScheduleCosts();
		
		double variablen = Double.parseDouble(this.vk.getText());
		double fixen = Double.parseDouble(this.fpf.getText());
		
		ArrayList<ScheduleCosts> result = sc.calculateVehicleScheduleCosts(this.umlaufplanliste, variablen, fixen);
		this.gesamtKosten.setText(String.valueOf(result.get(0).getSchedulecosts()));
		this.variableKostenGesamt.setText(String.valueOf(result.get(0).getVariableschedulecosts()));
		this.fixkostenGesamt.setText(String.valueOf(result.get(0).getFixedschedulecosts()));
		
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

	public ArrayList<Umlaufplan> getUmlaufplanliste() {
		return umlaufplanliste;
	}

	public void setUmlaufplanliste(ArrayList<Umlaufplan> umlaufplanliste) {
		this.umlaufplanliste = umlaufplanliste;
	}
	
	

}
