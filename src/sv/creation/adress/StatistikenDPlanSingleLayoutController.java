package sv.creation.adress;

import java.io.File;
import java.util.ArrayList;

import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.util.DutyStatistics;
import sv.creation.adress.util.Export;
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
import javafx.stage.FileChooser;
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
	private Label aServTotRatio;
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
				"ø Dauer Serv.F.");
		TableColumn<DutyStatistics, Double> dutynumberOfDeadheads = new TableColumn<DutyStatistics, Double>(
				"# Verbindungsfahrten");
		TableColumn<DutyStatistics, Double> dutyaverageLengthOfDeadheads = new TableColumn<DutyStatistics, Double>(
				"ø Dauer Verb.F.");
		TableColumn<DutyStatistics, Double> dutynumberOfWaitings = new TableColumn<DutyStatistics, Double>(
				"# Warten");
		TableColumn<DutyStatistics, Double> dutyaverageLengthOfWaitings = new TableColumn<DutyStatistics, Double>(
				"ø Dauer Warten");
		TableColumn<DutyStatistics, Double> dutynumberOfPullIns = new TableColumn<DutyStatistics, Double>(
				"# Pullins");
		TableColumn<DutyStatistics, Double> dutyaverageLengthOfPullIns = new TableColumn<DutyStatistics, Double>(
				"ø Pull ins");
		TableColumn<DutyStatistics, Double> dutynumberOfPullOuts = new TableColumn<DutyStatistics, Double>(
				"# Pull outs");
		TableColumn<DutyStatistics, Double> dutyaverageLengthOfPullOuts = new TableColumn<DutyStatistics, Double>(
				"ø Pull outs");
		TableColumn<DutyStatistics, Double> dutyserviceTimetotalDutyTimeRatio = new TableColumn<DutyStatistics, Double>(
				"Servicefahrten / Gesamt");
		TableColumn<DutyStatistics, Integer> dutynumberOfLines = new TableColumn<DutyStatistics, Integer>(
				"# Linien");
		TableColumn<DutyStatistics, Integer> dutynumberOfVehicles = new TableColumn<DutyStatistics, Integer>(
				"# Fzg.");

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
		dutyserviceTimetotalDutyTimeRatio
				.setCellValueFactory(new PropertyValueFactory<DutyStatistics, Double>(
						"dutyserviceTimetotalDutyTimeRatio"));
		dutynumberOfLines.setCellValueFactory(new PropertyValueFactory<DutyStatistics, Integer>("dutynumberOfLines"));
		dutynumberOfVehicles.setCellValueFactory(new PropertyValueFactory<DutyStatistics, Integer>("dutynumberOfVehicles"));

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
		dutyserviceTimetotalDutyTimeRatio.prefWidthProperty().bind(
				dutyserviceTimetotalDutyTimeRatio.widthProperty());
		dutynumberOfLines.prefWidthProperty().bind(
				dutynumberOfLines.widthProperty());
		dutynumberOfVehicles.prefWidthProperty().bind(
				dutynumberOfVehicles.widthProperty());

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
				dutyserviceTimetotalDutyTimeRatio,dutynumberOfLines,dutynumberOfVehicles);
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
		
		this.aTransfer
		.setText(String.valueOf(Math.round(this.scheduleStatistics.get(
				0).getNumberOfTransfers() * 100) / 100));
		this.aPausen
		.setText(String.valueOf(Math.round(this.scheduleStatistics.get(
				0).getNumberOfBreaks() * 100) / 100));
		this.aServTotRatio.setText(String.valueOf(this.scheduleStatistics.get(0).getServiceTimetotalTimeRatio()));
		this.dGesamt.setText(changeTimeFormat((long)this.scheduleStatistics
				.get(0).getTotalRunTime()));
		this.dServicefahrten.setText(changeTimeFormat((long)this.scheduleStatistics.get(0)
						.getOveralldurationServiceTrips()));
		this.dVerbindungsfahrt.setText(changeTimeFormat((long)this.scheduleStatistics.get(0)
						.getOveralldurationDeadHeads()));
		this.dWarten.setText(changeTimeFormat((long)this.scheduleStatistics
				.get(0).getOveralldurationWaitings()));
		this.dEinrueckfahrt.setText(changeTimeFormat((long)this.scheduleStatistics.get(0)
						.getOveralldurationPullIns()));
		this.dAusrueckfahrt.setText(changeTimeFormat((long)this.scheduleStatistics.get(0)
						.getOveralldurationPullOuts()));
		this.dTransfer
		.setText(changeTimeFormat((long)this.scheduleStatistics.get(
				0).getOveralldurationTransfers() ));
		this.dPausen
		.setText(changeTimeFormat((long)this.scheduleStatistics.get(
				0).getOveralldurationBreaks()));

		this.duGesamt.setText(changeTimeFormat((long)this.scheduleStatistics.get(
				0).getAverageLengthOfTotalTrips()));
		this.duServicefahrten.setText(changeTimeFormat((long)this.scheduleStatistics.get(0)
						.getAverageLengthOfServiceTrips()));
		this.duVerbindungsfahrt.setText(changeTimeFormat((long)this.scheduleStatistics.get(0)
						.getAverageLengthOfDeadheads()));
		this.duWarten.setText(changeTimeFormat((long)this.scheduleStatistics.get(
				0).getAverageLengthOfWaitings()));
		this.duEinrueckfahrt.setText(changeTimeFormat((long)this.scheduleStatistics.get(0)
						.getAverageLengthOfPullIns()));
		this.duAusrueckfahrt.setText(changeTimeFormat((long)this.scheduleStatistics.get(0)
						.getAverageLengthOfPullOuts()));
		this.duTransfer
		.setText(changeTimeFormat((long)this.scheduleStatistics.get(
				0).getAverageLengthOfTranfers()));
		this.duPausen
		.setText(changeTimeFormat((long)this.scheduleStatistics.get(
				0).getAverageLengthOfBreaks()));

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
								"Nachbereitung",
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
		this.graphic.setTitle("Relative Anteile");
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
	
	public void handleExportCSV(){
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"CSV files (*.csv)", "*.csv");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(this.getDialogStage());

		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(".csv")) {
				file = new File(file.getPath() + ".csv");
			}

			Export export = new Export();
			export.exportDienstplanStatistik(dutyStatistics, file);
		}
	}
	
	public String changeTimeFormat(long seconds){
		String time;
		long hr = (long)(seconds/3600);
		long rem = (long)(seconds%3600);
		long mn = rem/60;
		long sec = rem%60;
	      String hrStr = (hr<10 ? "0" : "")+hr;
	      String mnStr = (mn<10 ? "0" : "")+mn;
	      String secStr = (sec<10 ? "0" : "")+sec;
	    time = hrStr+" : " +mnStr+ " : "+secStr;
	    return time;
	}
}
