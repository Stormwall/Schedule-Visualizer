package sv.creation.adress;

import java.io.File;
import java.util.ArrayList;

import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.model.Umlaufplan;
import sv.creation.adress.util.BlockStatistics;
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
	private Label aServTotRatio;
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
	private PieChart graphic;

	// Arbeitsvariabelen

	private ArrayList<Umlaufplan> umlaufplanliste = new ArrayList<Umlaufplan>();
	private Fahrplan fahrplan;
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
		TableColumn<BlockStatistics, Integer> blocktotalNumberOfTrips = new TableColumn<BlockStatistics, Integer>(
				"# Fahrten Gesamt");
		TableColumn<BlockStatistics, Long> blocktotalRunTime = new TableColumn<BlockStatistics, Long>(
				"Gesamtdauer");
		TableColumn<BlockStatistics, Integer> blocknumberOfServiceTrips = new TableColumn<BlockStatistics, Integer>(
				"# Servicefahrten");
		TableColumn<BlockStatistics, Double> blockaverageLengthOfServiceTrips = new TableColumn<BlockStatistics, Double>(
				"� Dauer Serv.F.");
		TableColumn<BlockStatistics, Integer> blocknumberOfDeadheads = new TableColumn<BlockStatistics, Integer>(
				"# Verbindungsfahrten");
		TableColumn<BlockStatistics, Double> blockaverageLengthOfDeadheads = new TableColumn<BlockStatistics, Double>(
				"� Dauer Verb.F.");
		TableColumn<BlockStatistics, Integer> blocknumberOfWaitings = new TableColumn<BlockStatistics, Integer>(
				"# Warten");
		TableColumn<BlockStatistics, Double> blockaverageLengthOfWaitings = new TableColumn<BlockStatistics, Double>(
				"� Dauer Warten");
		TableColumn<BlockStatistics, Integer> blocknumberOfPullIns = new TableColumn<BlockStatistics, Integer>(
				"# Pull in");
		TableColumn<BlockStatistics, Double> blockaverageLengthOfPullIns = new TableColumn<BlockStatistics, Double>(
				"� Pull in");
		TableColumn<BlockStatistics, Integer> blocknumberOfPullOuts = new TableColumn<BlockStatistics, Integer>(
				"# Pull outs");
		TableColumn<BlockStatistics, Double> blockaverageLengthOfPullOuts = new TableColumn<BlockStatistics, Double>(
				"� Pull outs");
		TableColumn<BlockStatistics, Double> blockserviceTimetotalBlockTimeRatio = new TableColumn<BlockStatistics, Double>(
				"Servicefahrten / Gesamt");
		TableColumn<BlockStatistics, Integer> blocknumberOfLines = new TableColumn<BlockStatistics, Integer>(
				"# Linien");

		blockEleCol
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Integer>(
						"blockID"));
		blocktotalNumberOfTrips
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Integer>(
						"blocktotalNumberOfTrips"));
		blocktotalRunTime
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Long>(
						"blocktotalRunTime"));
		blocknumberOfServiceTrips
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Integer>(
						"blocknumberOfServiceTrips"));
		blockaverageLengthOfServiceTrips
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Double>(
						"blockaverageLengthOfServiceTrips"));
		blocknumberOfDeadheads
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Integer>(
						"blocknumberOfDeadheads"));
		blockaverageLengthOfDeadheads
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Double>(
						"blockaverageLengthOfDeadheads"));
		blocknumberOfWaitings
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Integer>(
						"blocknumberOfWaitings"));
		blockaverageLengthOfWaitings
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Double>(
						"blockaverageLengthOfWaitings"));
		blocknumberOfPullIns
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Integer>(
						"blocknumberOfPullIns"));
		blockaverageLengthOfPullIns
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Double>(
						"blockaverageLengthOfPullIns"));
		blocknumberOfPullOuts
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Integer>(
						"blocknumberOfPullOuts"));
		blockaverageLengthOfPullOuts
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Double>(
						"blockaverageLengthOfPullOuts"));
		blockserviceTimetotalBlockTimeRatio
				.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Double>(
						"blockserviceTimetotalBlockTimeRatio"));
		blocknumberOfLines.setCellValueFactory(new PropertyValueFactory<BlockStatistics, Integer>("blocknumberOfLines"));

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
		blocknumberOfLines.prefWidthProperty().bind(
				blocknumberOfLines.widthProperty());

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
				blockserviceTimetotalBlockTimeRatio,blocknumberOfLines);
		this.tablePane.setContent(this.detailsBlockTable);

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
		

		this.dGesamt.setText(changeTimeFormat((long)this.scheduleStatistics
				.get(0).getTotalRunTime()));
		this.dServicefahrten.setText(changeTimeFormat((long)this.scheduleStatistics.get(0)
						.getOveralldurationServiceTrips()));
		this.dVerbindungsfahrt.setText(changeTimeFormat((long)this.scheduleStatistics.get(0)
						.getOveralldurationDeadHeads()));
		this.dWarten.setText(changeTimeFormat((long)this.scheduleStatistics
				.get(0).getOveralldurationWaitings()));
		this.duEinrueckfahrt.setText(changeTimeFormat((long)this.scheduleStatistics.get(0)
						.getOveralldurationPullIns()));
		this.dAusrueckfahrt.setText(changeTimeFormat((long)this.scheduleStatistics.get(0)
						.getOveralldurationPullOuts()));
		this.aServTotRatio.setText(String.valueOf(this.scheduleStatistics.get(0).getServiceTimetotalTimeRatio()));

		this.duGesamt.setText(changeTimeFormat((long)this.scheduleStatistics.get(
				0).getAverageLengthOfTotalTrips()));
		this.duServicefahrten.setText(changeTimeFormat((long)this.scheduleStatistics.get(0)
						.getAverageLengthOfServiceTrips()));
		this.duVerbindungsfahrt.setText(changeTimeFormat((long)this.scheduleStatistics.get(0)
						.getAverageLengthOfDeadheads()));
		this.duWarten.setText(changeTimeFormat((long)this.scheduleStatistics.get(
				0).getAverageLengthOfWaitings()));
		this.dEinrueckfahrt.setText(changeTimeFormat((long)this.scheduleStatistics.get(0)
						.getAverageLengthOfPullIns()));
		this.duAusrueckfahrt.setText(changeTimeFormat((long)this.scheduleStatistics.get(0)
						.getAverageLengthOfPullOuts()));
		

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
										.getOveralldurationLayovers() * 100) / 100));
		
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
	
	public Fahrplan getFahrplan() {
		return fahrplan;
	}

	public void setFahrplan(Fahrplan fahrplan) {
		this.fahrplan = fahrplan;
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
				.calculateVehicleScheduleStatistics(umlaufplanliste, this.fahrplan);
		this.blockStatistics = bstat
				.calculateBlockStatistics(this.umlaufplanliste.get(0), this.fahrplan);

		// Aufruf der Arbeitsmethoden
		fillLabels();
		createTableViewBlockStat();
		fillPieChart();
	}
	
	public void handleExportCSV(){
		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"CSV files (*.csv)", "*.csv");
		fileChooser.getExtensionFilters().add(extFilter);

//		// Show save file dialog
		File file = fileChooser.showSaveDialog(this.getDialogStage());

		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(".csv")) {
				file = new File(file.getPath() + ".csv");
			}

			Export export = new Export();
			export.exportUmlaufplanStatistik(blockStatistics, file);
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
