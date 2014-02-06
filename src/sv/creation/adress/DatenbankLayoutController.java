package sv.creation.adress;

import java.util.ArrayList;

import sv.creation.adress.database.DBMatching;
import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.model.Umlaufplan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
				this.fahrplanliste.get(i).setBezeichnung(" Fahrplan " + (i + 1));
			}

		}
	}
	
	// Methoden zur Befuellung der Dienstplanliste

	public void fillDienstplanliste() {

		// Dienstpläne -- Choicebox wird gefüllt

		DBMatching dbm = new DBMatching();

		this.dienstplanliste.clear();

		if (dbm.databaseIsEmpty() || dbm.dienstplanIsEmpty()) {

		} else {

			this.dienstplanliste.clear();
			this.dienstplanliste = dbm.createDienstplanObject();
			for (int i = 0; i < this.umlaufplanliste.size(); i++) {
				this.dienstplanliste.get(i).setName(" Dienstplan " + (i + 1));
			}

		}
	}

	// Methoden zur Befüllung der Umlaufplanliste

	public void fillUmlaufplanliste() {

		// Umlaufpläne -- Choicebox wird gefüllt

		DBMatching dbm = new DBMatching();

		this.umlaufplanliste.clear();

		if (dbm.databaseIsEmpty() || dbm.umlaufplanIsEmpty()) {

		} else {
			this.umlaufplanliste.clear();
			this.umlaufplanliste = dbm.createUmlaufplanObject();
			for (int i = 0; i < this.umlaufplanliste.size(); i++) {
				this.umlaufplanliste.get(i).setName(" Umlaufplan " + (i + 1));
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

		// // Set extension filter
		// FileChooser.ExtensionFilter extFilter = new
		// FileChooser.ExtensionFilter(
		// "TXT files (*.txt)", "*.txt");
		// fileChooser.getExtensionFilters().add(extFilter);

		// Show open file dialog
		// File file = fileChooser.showOpenDialog(dialogStage);
		//
		// Import im = new Import();
		// im.importFile(file);

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
