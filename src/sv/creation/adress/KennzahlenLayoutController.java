package sv.creation.adress;

import java.util.ArrayList;

import sv.creation.adress.database.DBMatching;
import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.model.Umlaufplan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class KennzahlenLayoutController {

	private Stage dialogStage;

	// Referenz zur MainApp

	private MainApplication mainApp;

	// Arbeitsvariablen der Stage

	@FXML
	private ScrollPane umlaufplanPane;
	@FXML
	private ScrollPane dienstplanPane;
	@FXML
	private ScrollPane fahrplanPane;
	@FXML
	private Label auswahlUmlaufplan;
	@FXML
	private Label auswahlDienstplan;
	@FXML
	private Label auswahlFahrplan;
	@FXML
	private Label auswahlSzenario;

	// Bau der Zugriffslisten

	private ArrayList<Umlaufplan> umlaufplanliste = new ArrayList<Umlaufplan>();
	private ArrayList<Dienstplan> dienstplanliste = new ArrayList<Dienstplan>();
	private ArrayList<Fahrplan> fahrplanliste = new ArrayList<Fahrplan>();

	private ArrayList<Umlaufplan> umlaufplanChoiceliste = new ArrayList<Umlaufplan>();
	private ArrayList<Dienstplan> dienstplanChoiceliste = new ArrayList<Dienstplan>();
	private ArrayList<Fahrplan> fahrplanChoiceliste = new ArrayList<Fahrplan>();

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

		// Belegen der Tableviews

		createTableViewUmlauf();
		createTableViewDienst();
		createTableViewFahrplan();

		// Sets the Standardelement condition of the Interface

		detailsUmlaufTable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		detailsDienstTable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		detailsFahrplanTable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	}

	/**
	 * Opens the Statistikview Umlaufplan.
	 */
	@FXML
	private void handleStatistikenUplan() {

		if (this.umlaufplanChoiceliste.size() == 1) {
			this.mainApp.showStatistikUPlanSingle(this.umlaufplanChoiceliste);
		} else {
			String fehlerA = "Es wurde noch Element ausgewählt";
			String fehlerB = "Welche Statistik soll angezeigt werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}
	}
	
	/**
	 * Opens the Statistikview Dienstplan.
	 */
	@FXML
	private void handleStatistikenDplan() {

		if (this.dienstplanChoiceliste.size() == 1) {
			this.mainApp.showStatistikDPlanSingle(this.dienstplanChoiceliste);
		} else {
			String fehlerA = "Es wurde noch Element ausgewählt";
			String fehlerB = "Welche Statistik soll angezeigt werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}
	}

	/**
	 * Opens the Kostenview.
	 */
	@FXML
	private void handleKosten() {

	}

	/**
	 * UmlaufChoiceAdd.
	 */
	@FXML
	private void chooseUmlaufplan() {

		this.umlaufplanChoiceliste.clear();

		if (this.detailsUmlaufTable.getSelectionModel().getSelectedItem() != null) {
			this.umlaufplanChoiceliste.add(this.detailsUmlaufTable
					.getSelectionModel().getSelectedItem());
			this.auswahlUmlaufplan.setText(umlaufplanChoiceliste.get(0)
					.getName());
		} else {
			String fehlerA = "Es wurde noch Element ausgewählt";
			String fehlerB = "Was soll ausgewaehlt werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}

	}

	/**
	 * UmlaufChoiceDelete.
	 */
	@FXML
	private void deleteUmlaufplan() {

		if (this.umlaufplanChoiceliste.isEmpty()) {
			String fehlerA = "Es wurde noch Element ausgewählt";
			String fehlerB = "Was soll geloescht werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		} else {
			this.umlaufplanChoiceliste.clear();

			this.auswahlUmlaufplan.setText("");
		}
	}

	/**
	 * DutyChoiceAdd.
	 */
	@FXML
	private void chooseDienstplan() {

		this.dienstplanChoiceliste.clear();

		if (this.detailsDienstTable.getSelectionModel().getSelectedItem() != null) {
			this.dienstplanChoiceliste.add(this.detailsDienstTable
					.getSelectionModel().getSelectedItem());
			this.auswahlDienstplan.setText(dienstplanChoiceliste.get(0)
					.getName());
		} else {
			String fehlerA = "Es wurde noch Element ausgewählt";
			String fehlerB =  "Was soll ausgewaehlt werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}

	}

	/**
	 * DutyChoiceDelete.
	 */
	@FXML
	private void deleteDienstplan() {

		if (this.dienstplanChoiceliste.isEmpty()) {
			String fehlerA = "Es wurde noch Element ausgewählt";
			String fehlerB = "Was soll geloescht werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		} else {
			this.dienstplanChoiceliste.clear();

			this.auswahlDienstplan.setText("");
		}


	}

	/**
	 * FahrplanChoiceAdd.
	 */
	@FXML
	private void chooseFahrplan() {

		this.fahrplanChoiceliste.clear();

		if (this.detailsFahrplanTable.getSelectionModel().getSelectedItem() != null) {
			this.fahrplanChoiceliste.add(this.detailsFahrplanTable
					.getSelectionModel().getSelectedItem());
			this.auswahlFahrplan.setText(fahrplanChoiceliste.get(0)
					.getBezeichnung());
		} else {
			String fehlerA = "Es wurde noch Element ausgewählt";
			String fehlerB =  "Was soll ausgewaehlt werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}

	}

	/**
	 * FahrplanChoiceDelete.
	 */
	@FXML
	private void deleteFahrplan() {

		if (this.fahrplanChoiceliste.isEmpty()) {

			String fehlerA = "Es wurde noch Element ausgewählt";
			String fehlerB = "Was soll geloescht werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		} else {
			this.fahrplanChoiceliste.clear();
			this.auswahlFahrplan.setText("");
		}
	}

	// Methode zum Beenden des PopUp

	public void endStage() {

		dialogStage.close();
	}

	/**
	 * Builds Umlaufplantableview.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void createTableViewUmlauf() {

		this.umlaufplanPane.setContent(null);

		this.detailsUmlaufTable.setEditable(true);

		TableColumn<Umlaufplan, String> uBezeichnung = new TableColumn<Umlaufplan, String>(
				"Bezeichnung");
		TableColumn<Umlaufplan, Integer> uID = new TableColumn<Umlaufplan, Integer>(
				"ID");
		TableColumn<Umlaufplan, Integer> ufahrplanID = new TableColumn<Umlaufplan, Integer>(
				"FahrplanID");
		TableColumn<Umlaufplan, String> uUploadDate = new TableColumn<Umlaufplan, String>(
				"Upload");

		uBezeichnung
				.setCellValueFactory(new PropertyValueFactory<Umlaufplan, String>(
						"name"));
		uID.setCellValueFactory(new PropertyValueFactory<Umlaufplan, Integer>(
				"id"));
		ufahrplanID
				.setCellValueFactory(new PropertyValueFactory<Umlaufplan, Integer>(
						"fahrplanID"));
		uUploadDate
				.setCellValueFactory(new PropertyValueFactory<Umlaufplan, String>(
						"date"));

		uBezeichnung.prefWidthProperty().bind(uBezeichnung.widthProperty());
		uID.prefWidthProperty().bind(uID.widthProperty());
		ufahrplanID.prefWidthProperty().bind(uUploadDate.widthProperty());
		uUploadDate.prefWidthProperty().bind(uUploadDate.widthProperty());

		ObservableList<Umlaufplan> data = FXCollections.observableArrayList();

		for (int i = 0; i < this.umlaufplanliste.size(); i++) {
			data.add(this.umlaufplanliste.get(i));
		}

		this.detailsUmlaufTable.setItems(data);
		this.detailsUmlaufTable.getColumns().addAll(uBezeichnung, uID,
				ufahrplanID, uUploadDate);
		this.umlaufplanPane.setContent(this.detailsUmlaufTable);

	}

	/**
	 * Builds Dienstplantableview.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void createTableViewDienst() {

		this.dienstplanPane.setContent(null);

		this.detailsDienstTable.setEditable(true);

		TableColumn<Dienstplan, String> dBezeichnung = new TableColumn<Dienstplan, String>(
				"Bezeichnung");
		TableColumn<Dienstplan, Integer> dID = new TableColumn<Dienstplan, Integer>(
				"ID");
		TableColumn<Dienstplan, Integer> dfahrplanID = new TableColumn<Dienstplan, Integer>(
				"FahrplanID");
		TableColumn<Dienstplan, String> dUploadDate = new TableColumn<Dienstplan, String>(
				"Upload");

		dBezeichnung
				.setCellValueFactory(new PropertyValueFactory<Dienstplan, String>(
						"name"));
		dID.setCellValueFactory(new PropertyValueFactory<Dienstplan, Integer>(
				"id"));
		dfahrplanID
				.setCellValueFactory(new PropertyValueFactory<Dienstplan, Integer>(
						"fahrplanID"));
		dUploadDate
				.setCellValueFactory(new PropertyValueFactory<Dienstplan, String>(
						"date"));

		dBezeichnung.prefWidthProperty().bind(dBezeichnung.widthProperty());
		dID.prefWidthProperty().bind(dID.widthProperty());
		dfahrplanID.prefWidthProperty().bind(dfahrplanID.widthProperty());
		dUploadDate.prefWidthProperty().bind(dUploadDate.widthProperty());

		ObservableList<Dienstplan> data = FXCollections.observableArrayList();

		for (int i = 0; i < this.dienstplanliste.size(); i++) {
			data.add(this.dienstplanliste.get(i));
		}

		this.detailsDienstTable.setItems(data);
		this.detailsDienstTable.getColumns().addAll(dBezeichnung, dID,
				dfahrplanID, dUploadDate);
		this.dienstplanPane.setContent(this.detailsDienstTable);

	}

	/**
	 * Builds Fahrplantableview.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void createTableViewFahrplan() {

		this.fahrplanPane.setContent(null);

		this.detailsFahrplanTable.setEditable(true);

		TableColumn<Fahrplan, String> fBezeichnung = new TableColumn<Fahrplan, String>(
				"Bezeichnung");
		TableColumn<Fahrplan, Integer> fID = new TableColumn<Fahrplan, Integer>(
				"ID");
		TableColumn<Fahrplan, String> fUploadDate = new TableColumn<Fahrplan, String>(
				"Upload");

		fBezeichnung
				.setCellValueFactory(new PropertyValueFactory<Fahrplan, String>(
						"bezeichnung"));
		fID.setCellValueFactory(new PropertyValueFactory<Fahrplan, Integer>(
				"id"));
		fUploadDate
				.setCellValueFactory(new PropertyValueFactory<Fahrplan, String>(
						"date"));

		fBezeichnung.prefWidthProperty().bind(fBezeichnung.widthProperty());
		fID.prefWidthProperty().bind(fID.widthProperty());
		fUploadDate.prefWidthProperty().bind(fUploadDate.widthProperty());

		ObservableList<Fahrplan> data = FXCollections.observableArrayList();

		for (int i = 0; i < this.fahrplanliste.size(); i++) {
			data.add(this.fahrplanliste.get(i));
		}

		this.detailsFahrplanTable.setItems(data);
		this.detailsFahrplanTable.getColumns().addAll(fBezeichnung, fID,
				fUploadDate);
		this.fahrplanPane.setContent(this.detailsFahrplanTable);

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

	// Methoden zur BefÃ¼llung der Dienstplanliste

	public void fillDienstplanliste() {

		// DienstplÃ¤ne -- Choicebox wird gefÃ¼llt

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

		// UmlaufplÃ¤ne -- Choicebox wird gefÃ¼llt

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

	// Zuordnungsmethoden

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
