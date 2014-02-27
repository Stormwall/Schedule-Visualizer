package sv.creation.adress;

import java.io.File;
import java.util.ArrayList;

import sv.creation.adress.database.DBMatching;
import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.model.Szenario;
import sv.creation.adress.model.Umlaufplan;
import sv.creation.adress.util.Import;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DatenbankLayoutController {

	// Strukturelemente dieser Stage

	@FXML
	private ScrollPane umlaufplanPane;
	@FXML
	private ScrollPane dienstplanPane;
	@FXML
	private ScrollPane fahrplanPane;
	@FXML
	private ScrollPane szenarienPane;
	@FXML
	private ProgressIndicator pIndik;

	// Erstellung der Detailtableviews

	private TableView<Umlaufplan> detailsUmlaufTable = new TableView<Umlaufplan>();
	private TableView<Dienstplan> detailsDienstTable = new TableView<Dienstplan>();
	private TableView<Fahrplan> detailsFahrplanTable = new TableView<Fahrplan>();
	private TableView<Szenario> szenarienTable = new TableView<Szenario>();

	// Bau der Zugriffslisten

	private ArrayList<Umlaufplan> umlaufplanliste = new ArrayList<Umlaufplan>();
	private ArrayList<Dienstplan> dienstplanliste = new ArrayList<Dienstplan>();
	private ArrayList<Fahrplan> fahrplanliste = new ArrayList<Fahrplan>();
	private ArrayList<Szenario> szenarienListe = new ArrayList<Szenario>();

	private Stage dialogStage;
	private MainApplication mainApp;
	private MainLayoutController mainLayoutcontroller;

	private DBMatching dbm = new DBMatching();

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {

		// Anordnung der Tabelle
		this.detailsUmlaufTable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		this.detailsDienstTable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		this.detailsFahrplanTable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		this.szenarienTable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	}

	/**
	 * Deletes U-Plan
	 */

	@FXML
	private void deleteUplan() {

		this.mainApp.confirmMeldung("Soll wirklich gelöscht werden?");

		if (this.detailsUmlaufTable.getSelectionModel().getSelectedItem() != null) {
			Umlaufplan umlaufplan = this.detailsUmlaufTable.getSelectionModel()
					.getSelectedItem();
			this.dbm.deleteUmlaufplan(umlaufplan);
			for (int i = 0; i < this.umlaufplanliste.size(); i++) {
				if (this.umlaufplanliste.get(i).equals(umlaufplan)) {
					for (int j = 0; j < this.dienstplanliste.size(); j++) {
						if (this.umlaufplanliste.get(i).getId() == this.dienstplanliste.get(j)
								.getUmlaufplanID()) {
							this.dienstplanliste.remove(j);
							--j;
						}
					}					
					this.umlaufplanliste.remove(i);
				}
			}

			// Aktualisieren der Listen und Anzeigen
			refreshUmlaufplan();
			refreshDienstplan();
		} else {
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
		;

		if (this.detailsDienstTable.getSelectionModel().getSelectedItem() != null) {
			Dienstplan dienstplan = this.detailsDienstTable.getSelectionModel()
					.getSelectedItem();
			this.dbm.deleteDienstplan(dienstplan);

			for (int i = 0; i < this.dienstplanliste.size(); i++) {
				if (this.dienstplanliste.get(i).equals(dienstplan)) {
					this.dienstplanliste.remove(i);
				}
			}

			// Aktualisieren der Listen und Anzeigen
			refreshDienstplan();
		} else {
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

		if (this.detailsFahrplanTable.getSelectionModel().getSelectedItem() != null) {
			Fahrplan fahrplan = this.detailsFahrplanTable.getSelectionModel()
					.getSelectedItem();
			this.dbm.deleteFahrplan(fahrplan);
			for (int i = 0; i < this.fahrplanliste.size(); i++) {
				if (this.fahrplanliste.get(i).equals(fahrplan)) {
					this.fahrplanliste.remove(i);
					for (int j = 0; j < this.umlaufplanliste.size(); j++) {
						if (fahrplan.getId() == umlaufplanliste.get(j)
								.getFahrplanID()) {
							this.umlaufplanliste.remove(j);
							--j;
						}
					}

					for (int j = 0; j < this.dienstplanliste.size(); j++) {
						if (fahrplan.getId() == this.dienstplanliste.get(j)
								.getFahrplanID()) {
							this.dienstplanliste.remove(j);
							--j;
						}
					}

					for (int j = 0; j < this.szenarienListe.size(); j++) {
						if (fahrplan.getId() == this.szenarienListe.get(j)
								.getFahrplanID()) {
							this.szenarienListe.remove(j);
							--j;
						}
					}
				}
			}

			// Aktualisieren der Listen und Anzeigen
			refreshUmlaufplan();
			refreshDienstplan();
			refreshFahrplan();
			refreshSzenario();
		} else {
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

		if (this.detailsUmlaufTable.getSelectionModel().getSelectedItem() != null) {
			String name = this.mainApp.inputMeldung(
					"Geben Sie bitte eine Bezeichnung für den Umlaufplan ein.",
					"Bitte eingeben", "Bezeichnung Dienstplan");
			if (name != null) {
				this.dbm.benenneUmlaufplanUm(this.detailsUmlaufTable
						.getSelectionModel().getSelectedItem().getId(), name);
				Umlaufplan umlaufplan = this.detailsUmlaufTable
						.getSelectionModel().getSelectedItem();
				for (int i = 0; i < this.umlaufplanliste.size(); i++) {
					if (this.umlaufplanliste.get(i).equals(umlaufplan)) {
						this.umlaufplanliste.get(i).setName(name);
					}
				}
				refreshUmlaufplan();
			} else {
				String fehlerA = "Es wurde noch kein Name angegeben";
				String fehlerB = "Wie soll der Plan bennant werden ?";
				String fehlerC = "Fehler";
				this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
			}
		} else {
			String fehlerA = "Es wurde noch Element ausgewaehlt";
			String fehlerB = "Was soll bearbeitet werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}
	}

	/**
	 * Renames D-Plan
	 */

	@FXML
	private void renamesDplan() {

		if (this.detailsDienstTable.getSelectionModel().getSelectedItem() != null) {
			String name = this.mainApp.inputMeldung(
					"Geben Sie bitte eine Bezeichnung für den Dienstplan ein.",
					"Bitte eingeben", "Bezeichnung Dienstplan");
			if (name != null) {
				this.dbm.benenneDienstplanUm(this.detailsDienstTable
						.getSelectionModel().getSelectedItem().getId(), name);
				Dienstplan dienstplan = this.detailsDienstTable
						.getSelectionModel().getSelectedItem();
				for (int i = 0; i < this.dienstplanliste.size(); i++) {
					if (this.dienstplanliste.get(i).equals(dienstplan)) {
						this.dienstplanliste.get(i).setName(name);
					}
				}
				refreshDienstplan();
			} else {
				String fehlerA = "Es wurde noch kein Name angegeben";
				String fehlerB = "Wie soll der Plan bennant werden ?";
				String fehlerC = "Fehler";
				this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
			}
		} else {
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

		if (this.detailsFahrplanTable.getSelectionModel().getSelectedItem() != null) {
			String name = this.mainApp.inputMeldung(
					"Geben Sie bitte eine Bezeichnung für den Fahrplan ein.",
					"Bitte eingeben", "Bezeichnung Fahrplan");
			if (name != null) {
				this.dbm.benenneFahrplanUm(this.detailsFahrplanTable
						.getSelectionModel().getSelectedItem().getId(), name);
				Fahrplan fahplan = this.detailsFahrplanTable
						.getSelectionModel().getSelectedItem();
				for (int i = 0; i < this.fahrplanliste.size(); i++) {
					if (this.fahrplanliste.get(i).equals(fahplan)) {
						this.fahrplanliste.get(i).setName(name);
					}
				}
				refreshFahrplan();
			} else {
				String fehlerA = "Es wurde noch kein Name angegeben";
				String fehlerB = "Wie soll der Plan bennant werden ?";
				String fehlerC = "Fehler";
				this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
			}
		} else {
			String fehlerA = "Es wurde noch Element ausgewaehlt";
			String fehlerB = "Was soll bearbeitet werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}
		refreshFahrplan();
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

		// // Show open file dialog
		File file = null;
		try {
					
			FileChooser fileChooser = new FileChooser();
			
			// Set extension filter
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
					"TXT files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);
			FadeTransition fa = new FadeTransition(Duration.millis(1500),
					this.pIndik);			
			fa.setFromValue(0.0);
			fa.setToValue(1.0);
			fa.setAutoReverse(true);
			fa.play();
			file = fileChooser.showOpenDialog(dialogStage);

			Import im = new Import();
			
			
			
			if (file.getName().startsWith("vs")) {
				im.importFile(file);
				if (im.isUmlaufplanImportFailed() == true) {
					this.mainApp.fehlerMeldung(
							"Bitte überprüfen Sie die Datenbank.",
							"Kein passender Fahrplan vorhanden!",
							"Importfehler");
				} else
					this.mainApp.informationMeldung("",
							"Der Umlaufplan wurde erfolgreich importiert.",
							"Import erfolgreich!");
			} else if (file.getName().startsWith("cs")) {
				im.importFile(file);
				if (im.isDienstplanImportDiensttypenFailed() == true) {
					this.mainApp.fehlerMeldung(
							"Bitte überprüfen Sie die Datenbank.",
							"Keine passenden Diensttypen vorhanden!",
							"Importfehler");
				} else if (im.isDienstplanImportUmlaufplanFailed() == true) {
					this.mainApp.fehlerMeldung(
							"Bitte überprüfen Sie die Datenbank.",
							"Kein passender Umlaufplan vorhanden!",
							"Importfehler");
				} else if (im.isDienstplanImportFahrplanFailed() == true) {
					this.mainApp.fehlerMeldung(
							"Bitte überprüfen Sie die Datenbank.",
							"Kein passender Fahrplan vorhanden!",
							"Importfehler");
				} else
					this.mainApp.informationMeldung("",
							"Der Dienstplan wurde erfolgreich importiert.",
							"Import erfolgreich!");
			} else if (file.getName().startsWith("dt")) {
				im.importFile(file);
				if (im.isDiensttypenImportFailed() == true) {
					this.mainApp.fehlerMeldung(
							"Bitte überprüfen Sie die Datenbank.",
							"Kein passender Fahrplan vorhanden!",
							"Importfehler");
				} else
					this.mainApp.informationMeldung("",
							"Die Diensttypen wurden erfolgreich importiert.",
							"Import erfolgreich!");
			} else if (file.getName().startsWith("real")) {
				im.importFile(file);
				if (im.isFahrplanImportFailed()) {
					this.mainApp.fehlerMeldung("Bitte neustarten.",
							"Es ist ein Fehler aufgetreten!", "Importfehler");
				} else
					this.mainApp.informationMeldung("",
							"Der Fahrplan wurden erfolgreich importiert.",
							"Import erfolgreich!");
			} else if (file.getName().startsWith("sc")) {
				im.importFile(file);
				if (im.isSzenarienImportFailed() == true) {
					this.mainApp.fehlerMeldung(
							"Bitte überprüfen Sie die Datenbank.",
							"Kein passender Fahrplan vorhanden!",
							"Importfehler");
				} else
					this.mainApp.informationMeldung("",
							"Die Szenarien wurden erfolgreich importiert.",
							"Import erfolgreich!");
			} else {
				this.mainApp.informationMeldung("Importfehler",
						"Falsche Dateibezeichnung",
						"Bitte überprüfen Sie die genaue Dateibezeichnung!");
			}

			fillUmlaufplanliste();
			fillDienstplanliste();
			fillFahrplanliste();
			fillSzenarienliste();

			this.mainApp.setUmlaufplanliste(this.umlaufplanliste);
			this.mainApp.setDienstplanliste(this.dienstplanliste);
			this.mainApp.setFahrplanliste(this.fahrplanliste);
			this.mainApp.setSzenarienListe(this.szenarienListe);
			
			this.mainLayoutcontroller.setUmlaufplanliste(this.umlaufplanliste);
			this.mainLayoutcontroller.setDienstplanliste(this.dienstplanliste);
			this.mainLayoutcontroller.setFahrplanliste(this.fahrplanliste);
			this.mainLayoutcontroller.setSzenarienListe(this.szenarienListe);

			refreshUmlaufplan();
			refreshDienstplan();
			refreshFahrplan();
			refreshSzenario();
			
			fa.setFromValue(1.0);
			fa.setToValue(0.0);
			fa.setAutoReverse(true);
			fa.play();

		} catch (Exception e) {
			this.pIndik.setOpacity(0);
		}
	}

	/**
	 * Builds Umlaufplantableview.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void refreshUmlaufplan() {

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
		ufahrplanID.prefWidthProperty().bind(ufahrplanID.widthProperty());
		uUploadDate.prefWidthProperty().bind(uUploadDate.widthProperty());

		ObservableList<Umlaufplan> data = FXCollections.observableArrayList();

		for (int i = 0; i < this.umlaufplanliste.size(); i++) {
			data.add(this.umlaufplanliste.get(i));
		}

		this.detailsUmlaufTable.setItems(data);
		this.detailsUmlaufTable.getColumns().clear();
		this.detailsUmlaufTable.getColumns().addAll(uBezeichnung, uID,
				ufahrplanID, uUploadDate);
		this.umlaufplanPane.setContent(this.detailsUmlaufTable);

	}

	/**
	 * Builds Dienstplantableview.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void refreshDienstplan() {

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
		this.detailsDienstTable.getColumns().clear();
		this.detailsDienstTable.getColumns().addAll(dBezeichnung, dID,
				dfahrplanID, dUploadDate);
		this.dienstplanPane.setContent(this.detailsDienstTable);

	}

	/**
	 * Builds Fahrplantableview.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void refreshFahrplan() {

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
						"name"));
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
		this.detailsFahrplanTable.getColumns().clear();
		this.detailsFahrplanTable.getColumns().addAll(fBezeichnung, fID,
				fUploadDate);
		this.fahrplanPane.setContent(this.detailsFahrplanTable);

	}

	/**
	 * Builds Szenarientableview.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void refreshSzenario() {

		this.szenarienPane.setContent(null);

		this.szenarienTable.setEditable(true);

		TableColumn<Szenario, String> szName = new TableColumn<Szenario, String>(
				"Bezeichnung");
		TableColumn<Szenario, Integer> szID = new TableColumn<Szenario, Integer>(
				"Szenario ID");
		TableColumn<Szenario, Integer> spID = new TableColumn<Szenario, Integer>(
				"Fahrplan ID");
		TableColumn<Szenario, String> sdate = new TableColumn<Szenario, String>(
				"Upload Datum");

		szName.setCellValueFactory(new PropertyValueFactory<Szenario, String>(
				"bezeichnung"));
		szID.setCellValueFactory(new PropertyValueFactory<Szenario, Integer>(
				"id"));
		spID.setCellValueFactory(new PropertyValueFactory<Szenario, Integer>(
				"fahrplanID"));
		sdate.setCellValueFactory(new PropertyValueFactory<Szenario, String>(
				"date"));

		szName.prefWidthProperty().bind(szName.widthProperty());
		szID.prefWidthProperty().bind(szID.widthProperty());
		spID.prefWidthProperty().bind(spID.widthProperty());
		sdate.prefWidthProperty().bind(sdate.widthProperty());

		ObservableList<Szenario> data = FXCollections.observableArrayList();

		for (int i = 0; i < this.szenarienListe.size(); i++) {
			data.add(this.szenarienListe.get(i));
		}

		this.szenarienTable.setItems(data);
		this.szenarienTable.getColumns().clear();
		this.szenarienTable.getColumns().addAll(szName, szID, spID, sdate);
		this.szenarienPane.setContent(this.szenarienTable);

	}

	// Methoden zur Befuellung der Szenarienliste

	public void fillSzenarienliste() {

		this.szenarienListe.clear();

		if (dbm.databaseIsEmpty() || dbm.szenarioIsEmpty()) {

		} else {
			this.szenarienListe = dbm.createSzenarioObject();
		}
	}

	// Methoden zur Befuellung der Fahrplanliste

	public void fillFahrplanliste() {

		this.fahrplanliste.clear();

		if (dbm.databaseIsEmpty() || dbm.fahrplanIsEmpty()) {

		} else {

			this.fahrplanliste = dbm.createFahrplanObject();

		}
	}

	// Methoden zur BefÃ¼llung der Dienstplanliste

	public void fillDienstplanliste() {

		this.dienstplanliste.clear();

		if (dbm.databaseIsEmpty() || dbm.dienstplanIsEmpty()) {

		} else {

			this.dienstplanliste = dbm.createDienstplanObject();

		}

	}

	// Methoden zur Befuellung der Umlaufplanliste

	public void fillUmlaufplanliste() {

		this.umlaufplanliste.clear();

		if (dbm.databaseIsEmpty() || dbm.umlaufplanIsEmpty()) {

		} else {

			this.umlaufplanliste = dbm.createUmlaufplanObject();

		}
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

	public ArrayList<Umlaufplan> getUmlaufplanliste() {
		return umlaufplanliste;
	}

	public void setUmlaufplanliste(ArrayList<Umlaufplan> umlaufplanliste) {
		this.umlaufplanliste = umlaufplanliste;
		refreshUmlaufplan();
	}

	public ArrayList<Dienstplan> getDienstplanliste() {
		return dienstplanliste;
	}

	public void setDienstplanliste(ArrayList<Dienstplan> dienstplanliste) {
		this.dienstplanliste = dienstplanliste;
		refreshDienstplan();
	}

	public ArrayList<Fahrplan> getFahrplanliste() {
		return fahrplanliste;
	}

	public void setFahrplanliste(ArrayList<Fahrplan> fahrplanliste) {
		this.fahrplanliste = fahrplanliste;
		refreshFahrplan();
	}

	public ArrayList<Szenario> getSzenarienListe() {
		return szenarienListe;
	}

	public void setSzenarienListe(ArrayList<Szenario> szenarienListe) {
		this.szenarienListe = szenarienListe;
		refreshSzenario();
	}

	public MainLayoutController getMainLayoutcontroller() {
		return mainLayoutcontroller;
	}

	public void setMainLayoutcontroller(MainLayoutController mainLayoutcontroller) {
		this.mainLayoutcontroller = mainLayoutcontroller;
	}

}
