package sv.creation.adress;

import java.io.File;
import java.util.ArrayList;

import sv.creation.adress.database.DBMatching;
import sv.creation.adress.database.DBSave;
import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.model.Umlaufplan;
import sv.creation.adress.util.Import;
import sv.creation.adress.util.Kennzahlenberechnung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DatenbankLayoutController {

	// Strukturelemente dieser Stage

	// Umlaufplan

	@FXML
	private TableView<Umlaufplan> umlauftable = new TableView<Umlaufplan>();
	@FXML
	private TableColumn<Umlaufplan, String> uBezeichnung;
	@FXML
	private TableColumn<Umlaufplan, Integer> uID;
	@FXML
	private TableColumn<Umlaufplan, Integer> ufahrplanID;
	@FXML
	private TableColumn<Umlaufplan, String> uUploadDate;

	// Dienstplan

	@FXML
	private TableView<Dienstplan> diensttable = new TableView<Dienstplan>();
	@FXML
	private TableColumn<Dienstplan, String> dBezeichnung;
	@FXML
	private TableColumn<Dienstplan, Integer> dID;
	@FXML
	private TableColumn<Dienstplan, Integer> dfahrplanID;
	@FXML
	private TableColumn<Dienstplan, String> dUploadDate;

	// Fahrplan

	@FXML
	private TableView<Fahrplan> fahrtable = new TableView<Fahrplan>();
	@FXML
	private TableColumn<Fahrplan, String> fBezeichnung;
	@FXML
	private TableColumn<Fahrplan, Integer> fID;
	@FXML
	private TableColumn<Fahrplan, String> fUploadDate;

	// Bau der Zugriffslisten

	private ArrayList<Umlaufplan> umlaufplanliste = new ArrayList<Umlaufplan>();
	private ArrayList<Dienstplan> dienstplanliste = new ArrayList<Dienstplan>();
	private ArrayList<Fahrplan> fahrplanliste = new ArrayList<Fahrplan>();

	private Stage dialogStage;
	private MainApplication mainApp;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {

		// Anordnung der Tabelle
		this.umlauftable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		this.diensttable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		this.fahrtable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		// Belegung der �bersicht mit den Daten 
		
		refreshUmlaufplan();
		refreshDienstplan();
		refreshFahrplan();

	}

	public void refreshUmlaufplan() {

		fillUmlaufplanliste();

		// Belegung der Tabelle mit Daten

		this.uBezeichnung
				.setCellValueFactory(new PropertyValueFactory<Umlaufplan, String>(
						"name"));
		this.uID.setCellValueFactory(new PropertyValueFactory<Umlaufplan, Integer>(
				"id"));
		this.ufahrplanID
				.setCellValueFactory(new PropertyValueFactory<Umlaufplan, Integer>(
						"fahrplanID"));
		this.uUploadDate
				.setCellValueFactory(new PropertyValueFactory<Umlaufplan, String>(
						"date"));

		// Hereinladen der Daten

		ObservableList<Umlaufplan> data = FXCollections.observableArrayList();

		for (int i = 0; i < this.umlaufplanliste.size(); i++) {
			data.add(this.umlaufplanliste.get(i));
		}

		this.umlauftable.setItems(data);

	}

	public void refreshDienstplan() {

		fillDienstplanliste();

		// Belegung der Tabelle mit Daten

		this.dBezeichnung
				.setCellValueFactory(new PropertyValueFactory<Dienstplan, String>(
						"name"));
		this.dID.setCellValueFactory(new PropertyValueFactory<Dienstplan, Integer>(
				"id"));
		this.dfahrplanID
				.setCellValueFactory(new PropertyValueFactory<Dienstplan, Integer>(
						"fahrplanID"));
		this.dUploadDate
				.setCellValueFactory(new PropertyValueFactory<Dienstplan, String>(
						"date"));

		// Hereinladen der Daten

		ObservableList<Dienstplan> data = FXCollections.observableArrayList();

		for (int i = 0; i < this.dienstplanliste.size(); i++) {
			data.add(this.dienstplanliste.get(i));
		}

		this.diensttable.setItems(data);

	}
	
	public void refreshFahrplan() {

		fillFahrplanliste();

		// Belegung der Tabelle mit Daten

		this.fBezeichnung
				.setCellValueFactory(new PropertyValueFactory<Fahrplan, String>(
						"bezeichnung"));
		this.fID.setCellValueFactory(new PropertyValueFactory<Fahrplan, Integer>(
				"id"));
		this.fUploadDate
				.setCellValueFactory(new PropertyValueFactory<Fahrplan, String>(
						"date"));

		// Hereinladen der Daten

		ObservableList<Fahrplan> data = FXCollections.observableArrayList();

		for (int i = 0; i < this.fahrplanliste.size(); i++) {
			data.add(this.fahrplanliste.get(i));
		}

		this.fahrtable.setItems(data);

	}
	
	/**
	 * Deletes U-Plan
	 */

	@FXML
	private void deleteUplan() {
		
		DBMatching dbm = new DBMatching();
		
		if (this.umlauftable.getSelectionModel().getSelectedItem()!=null) {
			Umlaufplan umlaufplan = this.umlauftable.getSelectionModel().getSelectedItem();
			dbm.deleteUmlaufplan(umlaufplan);
			
			// Aktualisieren der Listen und Anzeigen
			fillUmlaufplanliste();
			refreshUmlaufplan();
		}else{
			String fehlerA = "Es wurde noch Element ausgewählt";
			String fehlerB = "Was soll geloescht werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}
				
	}
	
	/**
	 * Deletes D-Plan
	 */

	@FXML
	private void deleteDplan() {
		
		DBMatching dbm = new DBMatching();
		
		if (this.diensttable.getSelectionModel().getSelectedItem()!=null) {
			Dienstplan dienstplan = this.diensttable.getSelectionModel().getSelectedItem();
			dbm.deleteDienstplan(dienstplan);
			
			// Aktualisieren der Listen und Anzeigen
			fillDienstplanliste();
			refreshDienstplan();
		}else{
			String fehlerA = "Es wurde noch Element ausgewählt";
			String fehlerB = "Was soll geloescht werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}
	}
	
	/**
	 * Deletes F-Plan
	 */

	@FXML
	private void deleteFplan() {
		
		DBMatching dbm = new DBMatching();
		
		if (this.fahrtable.getSelectionModel().getSelectedItem()!=null) {
			Fahrplan fahrplan = this.fahrtable.getSelectionModel().getSelectedItem();
			dbm.deleteFahrplan(fahrplan);
			
			// Aktualisieren der Listen und Anzeigen
			fillFahrplanliste();
			refreshFahrplan();
		}else{
			String fehlerA = "Es wurde noch Element ausgewählt";
			String fehlerB = "Was soll geloescht werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}	
	}
	
	/**
	 * Renames U-Plan
	 */

	@FXML
	private void renamesUplan() {
		
		if (this.umlauftable.getSelectionModel().getSelectedItem() != null) {
			DBMatching dbm = new DBMatching();
			String name = this.mainApp.inputMeldung("Geben Sie bitte eine Bezeichnung für den Umlaufplan ein.", "Bitte eingeben", "Bezeichnung Dienstplan");
			if(name != null){
				dbm.benenneUmlaufplanUm(this.umlauftable.getSelectionModel().getSelectedItem().getId(),name);
			}else{
				String fehlerA = "Es wurde noch kein Name angegeben";
				String fehlerB = "Wie soll der Plan bennant werden ?";
				String fehlerC = "Fehler";
				this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
			}
		}else{
			String fehlerA = "Es wurde noch Element ausgewaehlt";
			String fehlerB = "Was soll bearbeitet werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}
	refreshUmlaufplan();			
	}
	
	/**
	 * Renames D-Plan
	 */

	@FXML
	private void renamesDplan() {
		
		if (this.diensttable.getSelectionModel().getSelectedItem() != null) {
			DBMatching dbm = new DBMatching();
			String name = this.mainApp.inputMeldung("Geben Sie bitte eine Bezeichnung für den Dienstplan ein.", "Bitte eingeben", "Bezeichnung Dienstplan");
			if(name != null){
				dbm.benenneDienstplanUm(this.diensttable.getSelectionModel().getSelectedItem().getId(),name);
			}else{
				String fehlerA = "Es wurde noch kein Name angegeben";
				String fehlerB = "Wie soll der Plan bennant werden ?";
				String fehlerC = "Fehler";
				this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
			}
		}else{
			String fehlerA = "Es wurde noch Element ausgewaehlt";
			String fehlerB = "Was soll bearbeitet werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}
		refreshDienstplan();
	}
	
	/**
	 * Renames F-Plan
	 */

	@FXML
	private void renamesFplan() {
		
		if (this.fahrtable.getSelectionModel().getSelectedItem() != null) {
			DBMatching dbm = new DBMatching();
			String name = this.mainApp.inputMeldung("Geben Sie bitte eine Bezeichnung für den Fahrplan ein.", "Bitte eingeben", "Bezeichnung Fahrplan");
			if(name != null){
				dbm.benenneFahrplanUm(this.fahrtable.getSelectionModel().getSelectedItem().getId(),name);
			}else{
				String fehlerA = "Es wurde noch kein Name angegeben";
				String fehlerB = "Wie soll der Plan bennant werden ?";
				String fehlerC = "Fehler";
				this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
			}
		}else{
			String fehlerA = "Es wurde noch Element ausgewaehlt";
			String fehlerB = "Was soll bearbeitet werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}
		refreshFahrplan();
	}
	
	// Methoden zur Befuellung der Fahrplanliste
	
	public void fillFahrplanliste() {

		// Fahrplaene -- Choicebox wird gefaellt

		DBMatching dbm = new DBMatching();

		this.fahrplanliste.clear();

		if (dbm.databaseIsEmpty() || dbm.dienstplanIsEmpty()) {

		} else {

			this.fahrplanliste.clear();
			this.fahrplanliste = dbm.createFahrplanObject();
			for (int i = 0; i < this.fahrplanliste.size(); i++) {	
				if (this.fahrplanliste.get(i).getBezeichnung() == null) {
					this.fahrplanliste.get(i).setBezeichnung(" Fahrplan " + (i + 1));
				}	
			}
		}
	}
	
	// Methoden zur Befuellung der Dienstplanliste

	public void fillDienstplanliste() {

		// Dienstplaene -- Choicebox wird geuellt

		DBMatching dbm = new DBMatching();

		this.dienstplanliste.clear();

		if (dbm.databaseIsEmpty() || dbm.dienstplanIsEmpty()) {

		} else {

			this.dienstplanliste.clear();
			this.dienstplanliste = dbm.createDienstplanObject();
			for (int i = 0; i < this.dienstplanliste.size(); i++) {
				if (this.dienstplanliste.get(i).getName() == null) {
					this.dienstplanliste.get(i).setName(" Dienstplan " + (i + 1));
				}	
			}
		}
	}

	// Methoden zur Befuellung der Umlaufplanliste

	public void fillUmlaufplanliste() {

		// Umlaufplaene -- Choicebox wird gef��llt

		DBMatching dbm = new DBMatching();

		this.umlaufplanliste.clear();

		if (dbm.databaseIsEmpty() || dbm.umlaufplanIsEmpty()) {

		} else {
			this.umlaufplanliste.clear();
			this.umlaufplanliste = dbm.createUmlaufplanObject();
			for (int i = 0; i < this.umlaufplanliste.size(); i++) {
				if (this.umlaufplanliste.get(i).getName() == null) {
					this.umlaufplanliste.get(i).setName(" Umlaufplan " + (i + 1));
				}				
			}
		}

	}

	// Methode zum Beenden des PopUp

	public void endStage() {

		dialogStage.close();
	}

	/**
	 * Imports files
	 * 
	 * @return
	 */
	public void handleImport() {

//		FileChooser fileChooser = new FileChooser();
//
//		// // Set extension filter
//		FileChooser.ExtensionFilter extFilter = new
//		 FileChooser.ExtensionFilter(
//		"TXT files (*.txt)", "*.txt");
//		 fileChooser.getExtensionFilters().add(extFilter);
//
//		// Show open file dialog
//		 File file = fileChooser.showOpenDialog(dialogStage);
//		//
//		 Import im = new Import();
//		 im.importFile(file);
		
		DBMatching dbm = new DBMatching();
		dbm.benenneFahrplanUm(fahrplanliste.get(0).getId(), "Test");
		
//		DBSave dbs = new DBSave();
//		dbs.saveUmlaufplan(umlaufplanliste.get(0), "Testversion");
		
//		Kennzahlenberechnung kzb = new Kennzahlenberechnung();
////		kzb.regelmaessigeDutyelement(dienstplanliste.get(0), fahrplanliste.get(0));
//		kzb.erstelleDutyelementListRegular(dienstplanliste, fahrplanliste.get(0));
//		System.out.println(kzb.berechneDurschnittlicheWiederholrateDienstplanRegular(dienstplanliste, fahrplanliste.get(0)));
//		System.out.println(kzb.berechneDurschnittlicheWiederholrateDienstplanAll(dienstplanliste,kzb.erstelleDutyelementListAll(dienstplanliste), fahrplanliste.get(0)));
//		
//		kzb.berechneDistanz(dienstplanliste, fahrplanliste.get(0));
		
		// testausgabe Dienstplanstatistik
/*		ScheduleStatistics stat = new ScheduleStatistics();
		ArrayList<ScheduleStatistics> sstats = new ArrayList<ScheduleStatistics>();
		sstats = stat.calculateCrewScheduleStatistics(dienstplanliste);
		for (int i = 0; i < 1; i++) {
			System.out.println("Dienstplan global:");
			System.out.println(sstats.get(i).getTotalNumberOfTrips());
			System.out.println(sstats.get(i).getTotalRunTime());
			;
			System.out.println(sstats.get(i).getNumberOfServiceTrips());
			System.out.println(sstats.get(i).getAverageLengthOfServiceTrips());
			System.out.println(sstats.get(i).getNumberOfDeadheads());
			System.out.println(sstats.get(i).getAverageLengthOfDeadheads());
			System.out.println(sstats.get(i).getNumberOfWaitings());
			System.out.println(sstats.get(i).getAverageLengthOfWaitings());
			System.out.println(sstats.get(i).getNumberOfPullOuts());
			System.out.println(sstats.get(i).getAverageLengthOfPullOuts());
			System.out.println(sstats.get(i).getNumberOfPullIns());
			System.out.println(sstats.get(i).getAverageLengthOfPullIns());
			System.out
					.println(sstats.get(i).getServiceTimetotalDutyTimeRatio());
		}
		// testausgabe Umlaufplanstatistik
		ScheduleStatistics stat = new ScheduleStatistics();
		ArrayList<ScheduleStatistics> sstats = new ArrayList<ScheduleStatistics>();
		sstats = stat.calculateVehicleScheduleStatistics(umlaufplanliste);
		for (int i = 0; i < 1; i++) {
			System.out.println("Umlaufplan global:");
			System.out.println(sstats.get(i).getTotalNumberOfTrips());
			System.out.println(sstats.get(i).getTotalRunTime());
			;
			System.out.println(sstats.get(i).getNumberOfServiceTrips());
			System.out.println(sstats.get(i).getAverageLengthOfServiceTrips());
			System.out.println(sstats.get(i).getNumberOfDeadheads());
			System.out.println(sstats.get(i).getAverageLengthOfDeadheads());
			System.out.println(sstats.get(i).getNumberOfWaitings());
			System.out.println(sstats.get(i).getAverageLengthOfWaitings());
			System.out.println(sstats.get(i).getNumberOfPullOuts());
			System.out.println(sstats.get(i).getAverageLengthOfPullOuts());
			System.out.println(sstats.get(i).getNumberOfPullIns());
			System.out.println(sstats.get(i).getAverageLengthOfPullIns());
			System.out.println(sstats.get(i)
					.getServiceTimetotalBlockTimeRatio());
		}*/
	}

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
