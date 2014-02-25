package sv.creation.adress;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sv.creation.adress.model.Szenario;

public class SzenarienLayoutController {
	// Strukturelemente dieser Stage

	@FXML
	private ScrollPane szenarienPane;
	@FXML
	private Label referencePlan;

	// Erstellung der Detailtableviews

	private TableView<Szenario> szenarienTable = new TableView<Szenario>();

	// Bau der Zugriffslisten

	private ArrayList<Szenario> szenarienListe = new ArrayList<Szenario>();
	private String fahrplanName;
	private boolean okClicked = false;
	private Stage dialogStage;
	private MainApplication mainApp;
	private MainLayoutController mainLayoutcontroller;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {

		// Anordnung der Tabelle
		this.szenarienTable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	// Methode zum Beenden des PopUp

	public void endStage() {

		dialogStage.close();
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {

		if (this.szenarienTable.getSelectionModel().getSelectedItem() != null) {
			Szenario sz = this.szenarienTable.getSelectionModel()
					.getSelectedItem();
			this.mainLayoutcontroller.setSzenario(sz);
			this.okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Builds Umlaufplantableview.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void refreshSzenario() {

		this.szenarienPane.setContent(null);

		this.szenarienTable.setEditable(true);

		TableColumn<Szenario, Integer> szID = new TableColumn<Szenario, Integer>(
				"Szenario ID");

		szID.setCellValueFactory(new PropertyValueFactory<Szenario, Integer>(
				"id"));

		szID.prefWidthProperty().bind(szID.widthProperty());

		ObservableList<Szenario> data = FXCollections.observableArrayList();

		for (int i = 0; i < this.szenarienListe.size(); i++) {
			data.add(this.szenarienListe.get(i));
		}

		this.szenarienTable.setItems(data);
		this.szenarienTable.getColumns().clear();
		this.szenarienTable.getColumns().addAll(szID);
		this.szenarienPane.setContent(this.szenarienTable);

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

	public ArrayList<Szenario> getSzenarienListe() {
		return szenarienListe;
	}

	public void setSzenarienListe(ArrayList<Szenario> szenarienListe) {
		this.szenarienListe = szenarienListe;
		refreshSzenario();
	}

	public String getFahrplanName() {
		return fahrplanName;
	}

	public void setFahrplanName(String fahrplanName) {
		this.fahrplanName = fahrplanName;
		this.referencePlan.setText(this.fahrplanName);
	}

	public MainLayoutController getMainLayoutcontroller() {
		return mainLayoutcontroller;
	}

	public void setMainLayoutcontroller(
			MainLayoutController mainLayoutcontroller) {
		this.mainLayoutcontroller = mainLayoutcontroller;
	}

	public boolean isOkClicked() {
		return okClicked;
	}

}
