package sv.creation.adress;

import java.util.ArrayList;

import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.util.ScheduleCosts;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class KostenLayoutControllerD {

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

	private ArrayList<Dienstplan> dienstplanliste = new ArrayList<Dienstplan>();
	private ArrayList<Fahrplan> fahrplanliste = new ArrayList<Fahrplan>();

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
		
		Fahrplan fahrplan = null;
		for (int i = 0; i < this.fahrplanliste.size(); i++) {
			if (this.dienstplanliste.get(0).getFahrplanID()==this.fahrplanliste.get(i).getId()) {
				fahrplan = this.fahrplanliste.get(i);
			}
		}

		ArrayList<ScheduleCosts> result = sc.calculateCrewScheduleCosts(
				this.dienstplanliste,fahrplan, fixen, variablen);
		this.gesamtKosten.setText(String.valueOf(result.get(0)
				.getSchedulecosts()));
		this.variableKostenGesamt.setText(String.valueOf(result.get(0)
				.getVariableschedulecosts()));
		this.fixkostenGesamt.setText(String.valueOf(result.get(0)
				.getFixedschedulecosts()));

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

	public ArrayList<Dienstplan> getDienstplanliste() {
		return dienstplanliste;
	}

	public void setDienstplanliste(ArrayList<Dienstplan> dienstplanliste) {
		this.dienstplanliste = dienstplanliste;
	}

	public ArrayList<Fahrplan> getFahrplanliste() {
		return fahrplanliste;
	}

	public void setFahrplanliste(ArrayList<Fahrplan> fahrplanliste) {
		this.fahrplanliste = fahrplanliste;
	}


}
