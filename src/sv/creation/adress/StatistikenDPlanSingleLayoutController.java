package sv.creation.adress;

import java.util.ArrayList;

import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.util.DutyStatistics;
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

public class StatistikenDPlanSingleLayoutController {

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
	private Label aTransfer;
	@FXML
	private Label aPausen;
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
	private Label dTransfer;
	@FXML
	private Label dPausen;
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
	private Label duTransfer;
	@FXML
	private Label duPausen;

	@FXML
	private PieChart graphic;

	// Arbeitsvariabelen

	private ArrayList<Dienstplan> dienstplanliste = new ArrayList<Dienstplan>();
	private Fahrplan fahrplan;
	private TableView<DutyStatistics> detailsdutyTable = new TableView<DutyStatistics>();

	private Stage dialogStage;

	private ArrayList<ScheduleStatistics> scheduleStatistics;
	private ArrayList<DutyStatistics> dutyStatistics;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {

		detailsdutyTable
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
	private void createTableViewDutyStat() {

		this.detailsdutyTable.setEditable(true);

		TableColumn<DutyStatistics, Integer> dutyEleCol = new TableColumn<DutyStatistics, Integer>(
				"Duty-Ele.");
		TableColumn<DutyStatistics, Double> dutytotalNumberOfTrips = new TableColumn<DutyStatistics, Double>(
				"# Fahrten Gesamt");
		TableColumn<DutyStatistics, Double> dutytotalRunTime = new TableColumn<DutyStatistics, Double>(
				"Gesamtdauer");
		TableColumn<DutyStatistics, Double> dutynumberOfServiceTrips = new TableColumn<DutyStatistics, Double>(
				"# Servicefahrten");
		TableColumn<DutyStatistics, Double> dutyaverageLengthOfServiceTrips = new TableColumn<DutyStatistics, Double>(
				"Ø Dauer Serv.F.");
		TableColumn<DutyStatistics, Double> dutynumberOfDeadheads = new TableColumn<DutyStatistics, Double>(
				"# Verbindungsfahrten");
		TableColumn<DutyStatistics, Double> dutyaverageLengthOfDeadheads = new TableColumn<DutyStatistics, Double>(
				"Ø Dauer Verb.F.");
		TableColumn<DutyStatistics, Double> dutynumberOfWaitings = new TableColumn<DutyStatistics, Double>(
				"# Warten");
		TableColumn<DutyStatistics, Double> dutyaverageLengthOfWaitings = new TableColumn<DutyStatistics, Double>(
				"Ø Dauer Warten");
		TableColumn<DutyStatistics, Double> dutynumberOfPullIns = new TableColumn<DutyStatistics, Double>(
				"# Pull in");
		TableColumn<DutyStatistics, Double> dutyaverageLengthOfPullIns = new TableColumn<DutyStatistics, Double>(
				"Ø Pull in");
		TableColumn<DutyStatistics, Double> dutynumberOfPullOuts = new TableColumn<DutyStatistics, Double>(
				"# Pull outs");
		TableColumn<DutyStatistics, Double> dutyaverageLengthOfPullOuts = new TableColumn<DutyStatistics, Double>(
				"Ø Pull outs");
		TableColumn<DutyStatistics, Double> dutyserviceTimetotaldutyTimeRatio = new TableColumn<DutyStatistics, Double>(
				"Servicefahrten / Gesamt");

		dutyEleCol
				.setCellValueFactory(new PropertyValueFactory<DutyStatistics, Integer>(
						"dutyHilfsID"));
		dutytotalNumberOfTrips
				.setCellValueFactory(new PropertyValueFactory<DutyStatistics, Double>(
						"dutytotalNumberOfTrips"));
		dutytotalRunTime
				.setCellValueFactory(new PropertyValueFactory<DutyStatistics, Double>(
						"dutytotalRunTime"));
		dutynumberOfServiceTrips
				.setCellValueFactory(new PropertyValueFactory<DutyStatistics, Double>(
						"dutynumberOfServiceTrips"));
		dutyaverageLengthOfServiceTrips
				.setCellValueFactory(new PropertyValueFactory<DutyStatistics, Double>(
						"dutyaverageLengthOfServiceTrips"));
		dutynumberOfDeadheads
				.setCellValueFactory(new PropertyValueFactory<DutyStatistics, Double>(
						"dutynumberOfDeadheads"));
		dutyaverageLengthOfDeadheads
				.setCellValueFactory(new PropertyValueFactory<DutyStatistics, Double>(
						"dutyaverageLengthOfDeadheads"));
		dutynumberOfWaitings
				.setCellValueFactory(new PropertyValueFactory<DutyStatistics, Double>(
						"dutynumberOfWaitings"));
		dutyaverageLengthOfWaitings
				.setCellValueFactory(new PropertyValueFactory<DutyStatistics, Double>(
						"dutyaverageLengthOfWaitings"));
		dutynumberOfPullIns
				.setCellValueFactory(new PropertyValueFactory<DutyStatistics, Double>(
						"dutynumberOfPullIns"));
		dutyaverageLengthOfPullIns
				.setCellValueFactory(new PropertyValueFactory<DutyStatistics, Double>(
						"dutyaverageLengthOfPullIns"));
		dutynumberOfPullOuts
				.setCellValueFactory(new PropertyValueFactory<DutyStatistics, Double>(
						"dutynumberOfPullOuts"));
		dutyaverageLengthOfPullOuts
				.setCellValueFactory(new PropertyValueFactory<DutyStatistics, Double>(
						"dutyaverageLengthOfPullOuts"));
		dutyserviceTimetotaldutyTimeRatio
				.setCellValueFactory(new PropertyValueFactory<DutyStatistics, Double>(
						"dutyserviceTimetotaldutyTimeRatio"));

		dutyEleCol.prefWidthProperty().bind(dutyEleCol.widthProperty());
		dutytotalNumberOfTrips.prefWidthProperty().bind(
				dutytotalNumberOfTrips.widthProperty());
		dutytotalRunTime.prefWidthProperty().bind(
				dutytotalRunTime.widthProperty());
		dutynumberOfServiceTrips.prefWidthProperty().bind(
				dutynumberOfServiceTrips.widthProperty());
		dutyaverageLengthOfServiceTrips.prefWidthProperty().bind(
				dutyaverageLengthOfServiceTrips.widthProperty());
		dutynumberOfDeadheads.prefWidthProperty().bind(
				dutynumberOfDeadheads.widthProperty());
		dutyaverageLengthOfDeadheads.prefWidthProperty().bind(
				dutyaverageLengthOfDeadheads.widthProperty());
		dutynumberOfWaitings.prefWidthProperty().bind(
				dutynumberOfWaitings.widthProperty());
		dutyaverageLengthOfWaitings.prefWidthProperty().bind(
				dutyaverageLengthOfWaitings.widthProperty());
		dutynumberOfPullIns.prefWidthProperty().bind(
				dutynumberOfPullIns.widthProperty());
		dutyaverageLengthOfPullIns.prefWidthProperty().bind(
				dutyaverageLengthOfPullIns.widthProperty());
		dutynumberOfPullOuts.prefWidthProperty().bind(
				dutynumberOfPullOuts.widthProperty());
		dutyaverageLengthOfPullOuts.prefWidthProperty().bind(
				dutyaverageLengthOfPullOuts.widthProperty());
		dutyserviceTimetotaldutyTimeRatio.prefWidthProperty().bind(
				dutyserviceTimetotaldutyTimeRatio.widthProperty());

		ObservableList<DutyStatistics> data = FXCollections
				.observableArrayList();

		for (int i = 0; i < this.dutyStatistics.size(); i++) {
			data.add(this.dutyStatistics.get(i));
		}

		this.detailsdutyTable.setItems(data);
		this.detailsdutyTable.getColumns().addAll(dutyEleCol,
				dutytotalNumberOfTrips, dutytotalRunTime,
				dutynumberOfServiceTrips, dutyaverageLengthOfServiceTrips,
				dutynumberOfDeadheads, dutyaverageLengthOfDeadheads,
				dutynumberOfWaitings, dutyaverageLengthOfWaitings,
				dutynumberOfPullIns, dutyaverageLengthOfPullIns,
				dutynumberOfPullOuts, dutyaverageLengthOfPullOuts,
				dutyserviceTimetotaldutyTimeRatio);
		this.tablePane.setContent(this.detailsdutyTable);

	}

	// Methode zum Beenden des PopUp

	public void fillLabels() {

		this.aGesamt.setText(String.valueOf(Math.round(this.scheduleStatistics
				.get(0).getTotalNumberOfTrips() * 100) / 100));
		this.aServicefahrten
				.setText(String.valueOf(Math.round(this.scheduleStatistics.get(
						0).getNumberOfServiceTrips() * 100) / 100));
		this.aVerbindungsfahrt
				.setText(String.valueOf(Math.round(this.scheduleStatistics.get(
						0).getNumberOfDeadheads() * 100) / 100));
		this.aWarten.setText(String.valueOf(Math.round(this.scheduleStatistics
				.get(0).getNumberOfWaitings() * 100) / 100));
		this.aEinrueckfahrt
				.setText(String.valueOf(Math.round(this.scheduleStatistics.get(
						0).getNumberOfPullIns() * 100) / 100));
		this.aAusrueckfahrt
				.setText(String.valueOf(Math.round(this.scheduleStatistics.get(
						0).getNumberOfPullOuts() * 100) / 100));
		this.aAnzahllinien.setText("Folgt");
		this.aTransfer
		.setText(String.valueOf(Math.round(this.scheduleStatistics.get(
				0).getNumberOfTransfers() * 100) / 100));
		this.aPausen
		.setText(String.valueOf(Math.round(this.scheduleStatistics.get(
				0).getNumberOfBreaks() * 100) / 100));

		this.dGesamt.setText(String.valueOf(Math.round(this.scheduleStatistics
				.get(0).getTotalRunTime() * 100) / 100) + " sek");
		this.dServicefahrten.setText(String.valueOf(Math
				.round(this.scheduleStatistics.get(0)
						.getOveralldurationServiceTrips() * 100) / 100)
				+ " sek");
		this.dVerbindungsfahrt.setText(String.valueOf(Math
				.round(this.scheduleStatistics.get(0)
						.getOveralldurationDeadHeads() * 100) / 100)
				+ " sek");
		this.dWarten.setText(String.valueOf(Math.round(this.scheduleStatistics
				.get(0).getOveralldurationWaitings() * 100) / 100) + " sek");
		this.dEinrueckfahrt.setText(String.valueOf(Math
				.round(this.scheduleStatistics.get(0)
						.getOveralldurationPullIns() * 100) / 100)
				+ " sek");
		this.dAusrueckfahrt.setText(String.valueOf(Math
				.round(this.scheduleStatistics.get(0)
						.getOveralldurationPullOuts() * 100) / 100)
				+ " sek");
		this.dAnzahllinien.setText("Folgt");
		this.dTransfer
		.setText(String.valueOf(Math.round(this.scheduleStatistics.get(
				0).getOveralldurationTransfers() * 100) / 100));
		this.dPausen
		.setText(String.valueOf(Math.round(this.scheduleStatistics.get(
				0).getOveralldurationBreaks()* 100) / 100));

		this.duGesamt.setText(String.valueOf(Math.round(this.scheduleStatistics.get(
				0).getAverageLengthOfTotalTrips() * 100) / 100)
				+ " sek");
		this.duServicefahrten.setText(String.valueOf(Math
				.round(this.scheduleStatistics.get(0)
						.getAverageLengthOfServiceTrips() * 100) / 100)
				+ " sek");
		this.duVerbindungsfahrt.setText(String.valueOf(Math
				.round(this.scheduleStatistics.get(0)
						.getAverageLengthOfDeadheads() * 100) / 100)
				+ " sek");
		this.duWarten.setText(String.valueOf(Math.round(this.scheduleStatistics.get(
				0).getAverageLengthOfWaitings() * 100) / 100)
				+ " sek");
		this.duEinrueckfahrt.setText(String.valueOf(Math
				.round(this.scheduleStatistics.get(0)
						.getAverageLengthOfPullIns() * 100) / 100)
				+ " sek");
		this.duAusrueckfahrt.setText(String.valueOf(Math
				.round(this.scheduleStatistics.get(0)
						.getAverageLengthOfPullOuts() * 100) / 100)
				+ " sek");
		this.duAnzahllinien.setText("Folgt");
		this.duTransfer
		.setText(String.valueOf(Math.round(this.scheduleStatistics.get(
				0).getAverageLengthOfTranfers() * 100) / 100));
		this.duPausen
		.setText(String.valueOf(Math.round(this.scheduleStatistics.get(
				0).getAverageLengthOfBreaks() * 100) / 100));

	}

	// Fuellt das PieChart

	public void fillPieChart() {

		ObservableList<PieChart.Data> pieChartData = FXCollections
				.observableArrayList(
						new PieChart.Data(
								"Servicefahrten",
								(int) (this.scheduleStatistics.get(0)
										.getOveralldurationServiceTrips() * 100) / 100),
						new PieChart.Data(
								"Verbindungsfahrt",
								(int) (this.scheduleStatistics.get(0)
										.getOveralldurationDeadHeads() * 100) / 100),
						new PieChart.Data(
								"Warten",
								(int) (this.scheduleStatistics.get(0)
										.getOveralldurationWaitings() * 100) / 100),
						new PieChart.Data(
								"Einruecken",
								(int) (this.scheduleStatistics.get(0)
										.getOveralldurationPullIns() * 100) / 100),
						new PieChart.Data(
								"Ausruecken",
								(int) (this.scheduleStatistics.get(0)
										.getOveralldurationPullOuts() * 100) / 100),
						new PieChart.Data(
								"Vorbereitung",
								(int) (this.scheduleStatistics.get(0)
										.getOveralldurationPreparations() * 100) / 100),
						new PieChart.Data(
								"WrapUps",
								(int) (this.scheduleStatistics.get(0)
										.getOveralldurationWrapUps() * 100) / 100),
						new PieChart.Data(
								"Layovers",
								(int) (this.scheduleStatistics.get(0)
										.getOveralldurationLayovers() * 100) / 100),
						new PieChart.Data(
								"Transfers",
								(int) (this.scheduleStatistics.get(0)
										.getOveralldurationTransfers() * 100) / 100),
						new PieChart.Data(
								"Pausen",
								(int) (this.scheduleStatistics.get(0)
										.getOveralldurationBreaks() * 100) / 100));

		this.graphic.setData(pieChartData);
		this.graphic.setTitle("Zeitverteilung");
	}

	// Zuordnungsmethoden

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public ArrayList<Dienstplan> getDienstplanliste() {
		return dienstplanliste;
	}

	public Fahrplan getFahrplan() {
		return fahrplan;
	}

	public void setFahrplan(Fahrplan fahrplan) {
		this.fahrplan = fahrplan;
	}

	public void setDienstplanliste(ArrayList<Dienstplan> dienstplanliste) {
		this.dienstplanliste = dienstplanliste;

		this.plan.setText(this.dienstplanliste.get(0).getName());

		ScheduleStatistics stat = new ScheduleStatistics();
		DutyStatistics dstat = new DutyStatistics();
		this.scheduleStatistics = stat
				.calculateCrewScheduleStatistics(dienstplanliste, this.fahrplan);
		this.dutyStatistics = dstat
				.calculateDutystatistics(this.dienstplanliste.get(0), this.fahrplan);

		// Aufruf der Arbeitsmethoden
		fillLabels();
		createTableViewDutyStat();
		fillPieChart();
	}

}
