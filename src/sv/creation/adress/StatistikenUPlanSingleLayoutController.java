package sv.creation.adress;

import java.util.ArrayList;

import sv.creation.adress.model.Umlaufplan;
import sv.creation.adress.util.BlockStatistics;
import sv.creation.adress.util.ScheduleStatistics;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class StatistikenUPlanSingleLayoutController {

	// Arbeitselemente der Stage

	@FXML
	private ScrollPane tablePane;
	@FXML
	private Label plan;
	@FXML
	private Label aGesamt;
	@FXML
	private Label aServicefahrten;
	@FXML
	private Label aVerbindungsfahrt;
	@FXML
	private Label aWarten;
	@FXML
	private Label aEinrueckfahrt;
	@FXML
	private Label aAusrueckfahrt;
	@FXML
	private Label aAnzahllinien;
	@FXML
	private Label dGesamt;
	@FXML
	private Label dServicefahrten;
	@FXML
	private Label dVerbindungsfahrt;
	@FXML
	private Label dWarten;
	@FXML
	private Label dEinrueckfahrt;
	@FXML
	private Label dAusrueckfahrt;
	@FXML
	private Label dAnzahllinien;
	@FXML
	private Label duGesamt;
	@FXML
	private Label duServicefahrten;
	@FXML
	private Label duVerbindungsfahrt;
	@FXML
	private Label duWarten;
	@FXML
	private Label duEinrueckfahrt;
	@FXML
	private Label duAusrueckfahrt;
	@FXML
	private Label duAnzahllinien;

	@FXML
	private PieChart graphic;

	// Arbeitsvariabelen

	private ArrayList<Umlaufplan> umlaufplanliste = new ArrayList<Umlaufplan>();
	private TableView<BlockStatistics> detailsBlockTable = new TableView<BlockStatistics>();

	private Stage dialogStage;

	private ArrayList<ScheduleStatistics> scheduleStatistics;
	private ArrayList<BlockStatistics> blockStatistics;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {

		detailsBlockTable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	}

	// Methode zum Beenden des PopUp

	public void endStage() {

		dialogStage.close();
	}

	/**
	 * Builds Umlaufplantabkeview.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void createTableViewBlockStat() {

		this.detailsBlockTable.setEditable(true);

		TableColumn<BlockStatistics, Integer> blockEleCol = new TableColumn<BlockStatistics, Integer>(
				"Block-Ele.");
		TableColumn<BlockStatistics, Double> blocktotalNumberOfTrips = new TableColumn<BlockStatistics, Double>(
				"# Fahrten Gesamt");
		TableColumn<BlockStatistics, Double> blocktotalRunTime = new TableColumn<BlockStatistics, Double>(
				"Gesamtdauer");
		TableColumn<BlockStatistics, Double> blocknumberOfServiceTrips = new TableColumn<BlockStatistics, Double>(
				"# Servicefahrten");
		TableColumn<BlockStatistics, Double> blockaverageLengthOfServiceTrips = new TableColumn<BlockStatistics, Double>(
				"Durchschnittl. Dauer Serv.F.");
		TableColumn<BlockStatistics, Double> blocknumberOfDeadheads = new TableColumn<BlockStatistics, Double>(
				"# Verbindungsfahrten");
		TableColumn<BlockStatistics, Double> blockaverageLengthOfDeadheads = new TableColumn<BlockStatistics, Double>(
				"Durchschnittl. Dauer Verb.F.");
		TableColumn<BlockStatistics, Double> blocknumberOfWaitings = new TableColumn<BlockStatistics, Double>(
				"# Warten");
		TableColumn<BlockStatistics, Double> blockaverageLengthOfWaitings = new TableColumn<BlockStatistics, Double>(
				"Durchschnittl. Dauer Warten");
		TableColumn<BlockStatistics, Double> blocknumberOfPullIns = new TableColumn<BlockStatistics, Double>(
				"# Pull in");
		TableColumn<BlockStatistics, Double> blockaverageLengthOfPullIns = new TableColumn<BlockStatistics, Double>(
				"Durchschnittl. Pull in");
		TableColumn<BlockStatistics, Double> blocknumberOfPullOuts = new TableColumn<BlockStatistics, Double>(
				"# Pull outs");
		TableColumn<BlockStatistics, Double> blockaverageLengthOfPullOuts = new TableColumn<BlockStatistics, Double>(
				"Durchschnittl. Pull outs");
		TableColumn<BlockStatistics, Double> blockserviceTimetotalBlockTimeRatio = new TableColumn<BlockStatistics, Double>(
				"Servicefahrten / Gesamt");

		blockEleCol
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Integer>(
						"blockID"));
		blocktotalNumberOfTrips
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Double>(
						"blocktotalNumberOfTrips"));
		blocktotalRunTime
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Double>(
						"blocktotalRunTime"));
		blocknumberOfServiceTrips
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Double>(
						"blocknumberOfServiceTrips"));
		blockaverageLengthOfServiceTrips
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Double>(
						"blockaverageLengthOfServiceTrips"));
		blocknumberOfDeadheads
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Double>(
						"blocknumberOfDeadheads"));
		blockaverageLengthOfDeadheads
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Double>(
						"blockaverageLengthOfDeadheads"));
		blocknumberOfWaitings
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Double>(
						"blocknumberOfWaitings"));
		blockaverageLengthOfWaitings
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Double>(
						"blockaverageLengthOfWaitings"));
		blocknumberOfPullIns
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Double>(
						"blocknumberOfPullIns"));
		blockaverageLengthOfPullIns
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Double>(
						"blockaverageLengthOfPullIns"));
		blocknumberOfPullOuts
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Double>(
						"blocknumberOfPullOuts"));
		blockaverageLengthOfPullOuts
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Double>(
						"blockaverageLengthOfPullOuts"));
		blockserviceTimetotalBlockTimeRatio
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Double>(
						"blockserviceTimetotalBlockTimeRatio"));

		blockEleCol.prefWidthProperty().bind(blockEleCol.widthProperty());
		blocktotalNumberOfTrips.prefWidthProperty().bind(
				blocktotalNumberOfTrips.widthProperty());
		blocktotalRunTime.prefWidthProperty().bind(
				blocktotalRunTime.widthProperty());
		blocknumberOfServiceTrips.prefWidthProperty().bind(
				blocknumberOfServiceTrips.widthProperty());
		blockaverageLengthOfServiceTrips.prefWidthProperty().bind(
				blockaverageLengthOfServiceTrips.widthProperty());
		blocknumberOfDeadheads.prefWidthProperty().bind(
				blocknumberOfDeadheads.widthProperty());
		blockaverageLengthOfDeadheads.prefWidthProperty().bind(
				blockaverageLengthOfDeadheads.widthProperty());
		blocknumberOfWaitings.prefWidthProperty().bind(
				blocknumberOfWaitings.widthProperty());
		blockaverageLengthOfWaitings.prefWidthProperty().bind(
				blockaverageLengthOfWaitings.widthProperty());
		blocknumberOfPullIns.prefWidthProperty().bind(
				blocknumberOfPullIns.widthProperty());
		blockaverageLengthOfPullIns.prefWidthProperty().bind(
				blockaverageLengthOfPullIns.widthProperty());
		blocknumberOfPullOuts.prefWidthProperty().bind(
				blocknumberOfPullOuts.widthProperty());
		blockaverageLengthOfPullOuts.prefWidthProperty().bind(
				blockaverageLengthOfPullOuts.widthProperty());
		blockserviceTimetotalBlockTimeRatio.prefWidthProperty().bind(
				blockserviceTimetotalBlockTimeRatio.widthProperty());

		ObservableList<BlockStatistics> data = FXCollections
				.observableArrayList();

		for (int i = 0; i < this.blockStatistics.size(); i++) {
			data.add(this.blockStatistics.get(i));
		}

		this.detailsBlockTable.setItems(data);
		this.detailsBlockTable.getColumns().addAll(blockEleCol,
				blocktotalNumberOfTrips, blocktotalRunTime,
				blocknumberOfServiceTrips, blockaverageLengthOfServiceTrips,
				blocknumberOfDeadheads, blockaverageLengthOfDeadheads,
				blocknumberOfWaitings, blockaverageLengthOfWaitings,
				blocknumberOfPullIns, blockaverageLengthOfPullIns,
				blocknumberOfPullOuts, blockaverageLengthOfPullOuts,
				blockserviceTimetotalBlockTimeRatio);
		this.tablePane.setContent(this.detailsBlockTable);

	}

	// Methode zum Beenden des PopUp

	public void fillLabels() {

		aGesamt.setText(String.valueOf(Math.round(this.scheduleStatistics
				.get(0).getTotalNumberOfTrips() * 100) / 100));
		aServicefahrten
				.setText(String.valueOf(Math.round(this.scheduleStatistics.get(
						0).getNumberOfServiceTrips() * 100) / 100));
		aVerbindungsfahrt
				.setText(String.valueOf(Math.round(this.scheduleStatistics.get(
						0).getNumberOfDeadheads() * 100) / 100));
		aWarten.setText(String.valueOf(Math.round(this.scheduleStatistics
				.get(0).getNumberOfWaitings() * 100) / 100));
		aEinrueckfahrt
				.setText(String.valueOf(Math.round(this.scheduleStatistics.get(
						0).getNumberOfPullIns() * 100) / 100));
		aAusrueckfahrt
				.setText(String.valueOf(Math.round(this.scheduleStatistics.get(
						0).getNumberOfPullOuts() * 100) / 100));
		aAnzahllinien.setText("Folgt");

		dGesamt.setText(String.valueOf(Math.round(this.scheduleStatistics
				.get(0).getTotalRunTime() * 100) / 100) + " sek");
		dServicefahrten.setText(String.valueOf(Math
				.round(this.scheduleStatistics.get(0)
						.getOveralldurationServiceTrips() * 100) / 100)
				+ " sek");
		dVerbindungsfahrt.setText(String.valueOf(Math
				.round(this.scheduleStatistics.get(0)
						.getOveralldurationDeadHeads() * 100) / 100)
				+ " sek");
		dWarten.setText(String.valueOf(Math.round(this.scheduleStatistics
				.get(0).getOveralldurationWaitings() * 100) / 100) + " sek");
		dEinrueckfahrt.setText(String.valueOf(Math
				.round(this.scheduleStatistics.get(0)
						.getOveralldurationPullIns() * 100) / 100)
				+ " sek");
		dAusrueckfahrt.setText(String.valueOf(Math
				.round(this.scheduleStatistics.get(0)
						.getOveralldurationPullOuts() * 100) / 100)
				+ " sek");
		dAnzahllinien.setText("Folgt");

		duGesamt.setText(String.valueOf(Math.round(this.scheduleStatistics.get(
				0).getAverageLengthOfTotalTrips() * 100) / 100)
				+ " sek");
		duServicefahrten.setText(String.valueOf(Math
				.round(this.scheduleStatistics.get(0)
						.getAverageLengthOfServiceTrips() * 100) / 100)
				+ " sek");
		duVerbindungsfahrt.setText(String.valueOf(Math
				.round(this.scheduleStatistics.get(0)
						.getAverageLengthOfDeadheads() * 100) / 100)
				+ " sek");
		duWarten.setText(String.valueOf(Math.round(this.scheduleStatistics.get(
				0).getAverageLengthOfWaitings() * 100) / 100)
				+ " sek");
		duEinrueckfahrt.setText(String.valueOf(Math
				.round(this.scheduleStatistics.get(0)
						.getAverageLengthOfPullIns() * 100) / 100)
				+ " sek");
		duAusrueckfahrt.setText(String.valueOf(Math
				.round(this.scheduleStatistics.get(0)
						.getAverageLengthOfPullOuts() * 100) / 100)
				+ " sek");
		duAnzahllinien.setText("Folgt");

	}

	// Zuordnungsmethoden

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

		this.plan.setText(this.umlaufplanliste.get(0).getName());

		ScheduleStatistics stat = new ScheduleStatistics();
		BlockStatistics bstat = new BlockStatistics();
		this.scheduleStatistics = stat
				.calculateVehicleScheduleStatistics(umlaufplanliste);
		this.blockStatistics = bstat
				.calculateBlockStatistics(this.umlaufplanliste.get(0));

		// Aufruf der Arbeitsmethoden
		fillLabels();
		createTableViewBlockStat();
	}

}
