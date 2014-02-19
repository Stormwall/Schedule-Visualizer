package sv.creation.adress;

import java.util.ArrayList;

import sv.creation.adress.database.DBMatching;
import sv.creation.adress.model.Blockelement;
import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Dutyelement;
import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.model.Umlaufplan;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class KennzahlenLayoutController {

	private Stage dialogStage;
	
	// Referenz zur MainApp

	private MainApplication mainApp;
	
	// Arbeitsvariablen der Stage
	
	// Bau der Zugriffslisten

		private ArrayList<Umlaufplan> umlaufplanliste = new ArrayList<Umlaufplan>();
		private ArrayList<Dienstplan> dienstplanliste = new ArrayList<Dienstplan>();
		private ArrayList<Fahrplan> fahrplanliste = new ArrayList<Fahrplan>();

		// Erstellung der Detailtableviews

		private TableView<Umlaufplan> detailsUmlaufTable = new TableView<Umlaufplan>();
		private TableView<Dienstplan> detailsDienstTable = new TableView<Dienstplan>();
		private TableView<Fahrplan> detailsFahrplanTable = new TableView<Fahrplan>();

	  /**
	  * Initializes the controller class. This method is automatically called
	  * after the fxml file has been loaded.
	  */
	  
	@FXML
	  private void initialize() {
		
		// Befuellung der Planlisten zur Auswahl der Plaene

		fillUmlaufplanliste();
		fillDienstplanliste();
		fillFahrplanliste();

		// Sets the Standardelement condition of the Interface

		detailsUmlaufTable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		detailsDienstTable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		detailsFahrplanTable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	      
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
	
	// Methoden zur Befuellung der Fahrplanliste

	public void fillFahrplanliste() {

		// Fahrplaene -- Choicebox wird gefaellt

		DBMatching dbm = new DBMatching();

		this.fahrplanliste.clear();

		if (dbm.databaseIsEmpty() || dbm.fahrplanIsEmpty()) {

		} else {

			this.fahrplanliste.clear();
			this.fahrplanliste = dbm.createFahrplanObject();
			for (int i = 0; i < this.fahrplanliste.size(); i++) {
				if (this.fahrplanliste.get(i).getBezeichnung() == null) {
					this.fahrplanliste.get(i).setBezeichnung(
							" Fahrplan " + (i + 1));
				}
			}
		}
	}

	// Methoden zur Befüllung der Dienstplanliste

	public void fillDienstplanliste() {

		// Dienstpläne -- Choicebox wird gefüllt

		DBMatching dbm = new DBMatching();

		this.dienstplanliste.clear();

		if (dbm.databaseIsEmpty() || dbm.dienstplanIsEmpty()) {

		} else {
			this.dienstplanliste.clear();

			this.dienstplanliste = dbm.createDienstplanObject();
			for (int i = 0; i < this.dienstplanliste.size(); i++) {
				if (this.dienstplanliste.get(i).getName() == null) {
					this.dienstplanliste.get(i).setName(
							" Dienstplan " + (i + 1));
				}
			}
		}
	}

	// Methoden zur Befuellung der Umlaufplanliste

	public void fillUmlaufplanliste() {

		// Umlaufpläne -- Choicebox wird gefüllt

		DBMatching dbm = new DBMatching();

		this.umlaufplanliste.clear();

		if (dbm.databaseIsEmpty() || dbm.umlaufplanIsEmpty()) {
		} else {
			this.umlaufplanliste = dbm.createUmlaufplanObject();
			for (int i = 0; i < this.umlaufplanliste.size(); i++) {
				if (this.umlaufplanliste.get(i).getName() == null) {
					this.umlaufplanliste.get(i).setName(
							" Umlaufplan " + (i + 1));
				}
			}
		}

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

