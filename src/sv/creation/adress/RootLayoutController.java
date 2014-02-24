package sv.creation.adress;

import java.util.ArrayList;

import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.model.Umlaufplan;
import javafx.fxml.FXML;

public class RootLayoutController {
	
	// Bau der Zugriffslisten

	private ArrayList<Umlaufplan> umlaufplanliste = new ArrayList<Umlaufplan>();
	private ArrayList<Dienstplan> dienstplanliste = new ArrayList<Dienstplan>();
	private ArrayList<Fahrplan> fahrplanliste = new ArrayList<Fahrplan>();


	// Reference to the main application
	private MainApplication mainApp;

	public void setMainApp(MainApplication mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Opens the ColorChange.
	 */
	@FXML
	private void handleColorChange() {
		mainApp.showColorChoice();
	}
	/**
	 * Opens the Kostenview.
	 */
	@FXML
	private void handleKennzahlen() {
		mainApp.showKennzahlen();
	}

	/**
	 * Opens the Databasesview.
	 */
	@FXML
	private void handleDatenbank() {
		mainApp.showDatenbank(this.getUmlaufplanliste(),this.getDienstplanliste(),this.getFahrplanliste());
	}

	/**
	 * Closes the application.
	 */
	@FXML
	private void handleExit() {
		System.exit(0);
	}

	public ArrayList<Umlaufplan> getUmlaufplanliste() {
		return umlaufplanliste;
	}

	public void setUmlaufplanliste(ArrayList<Umlaufplan> umlaufplanliste) {
		this.umlaufplanliste = umlaufplanliste;
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
