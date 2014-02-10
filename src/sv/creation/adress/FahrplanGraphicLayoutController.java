package sv.creation.adress;

import java.util.ArrayList;

import sv.creation.adress.model.Fahrplan;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class FahrplanGraphicLayoutController {

	// Zugriff auf Strukturelemente

	@FXML
	private ChoiceBox<String> startTag;
	@FXML
	private ChoiceBox<String> endTag;
	@FXML
	private StackedAreaChart<Number, Number> fahrplanvergleich;
	@FXML
	private NumberAxis xAxis;
	@FXML
	private NumberAxis yAxis;

	// Arbeitsobjekte der Stage

	private Fahrplan fahrplan;
	private int erstauswahl;
	private Stage dialogStage;
	private int tagStart = 0;
	private int tagEnde = 0;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {

		// Belegung der Choiceboxes

		this.startTag.setItems(FXCollections.observableArrayList("Montag",
				"Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag",
				"Sonntag"));
		this.startTag.getSelectionModel().select(0);

		this.endTag.setItems(FXCollections.observableArrayList("Montag",
				"Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag",
				"Sonntag"));

	}

	/**
	 * Draws the Graphic
	 */
	@FXML
	public void drawFahrplanvergleich() {

		// Belegung der X-Achse

		this.xAxis.setLabel("Zeitraum");
		this.xAxis.setLowerBound(0);
		this.xAxis.setUpperBound(1440/60);
		this.xAxis.setTickUnit(1);

		// Belegung der Y-Achse

		this.yAxis.setLabel("Nachfragen");

		// Belegung der Grafik

		this.fahrplanvergleich.setTitle(this.fahrplan.getBezeichnung());

		// Liste aus allen Grafiken
		ArrayList<XYChart.Series<Number, Number>> mainChart = new ArrayList<XYChart.Series<Number, Number>>();

		// Vergleich vergleich = null;
		// int[] anzahlServiceFahrten = new int[1440];
		// anzahlServiceFahrten = vergleich.vergleicheFahrplan(1, 1);
		
		// Zuordnung der entsprechenden Fahrplaene

		for (int i = this.tagStart; i <= this.tagEnde; i++) {

			switch (i) {
			case 0:

				XYChart.Series<Number, Number> monday = new XYChart.Series<Number, Number>();
				monday.setName("Montag");

				for (int j = 0; j < 1440; j++) {
					monday.getData().add(new Data<Number, Number>(j/60, j + 1));
				}
				mainChart.add(monday);
				break;
			case 1:
				XYChart.Series<Number, Number> tuesday = new XYChart.Series<Number, Number>();
				tuesday.setName("Dienstag");

				for (int j = 0; j < 1440; j++) {
					tuesday.getData().add(new Data<Number, Number>(j/60, j + 2));
				}
				mainChart.add(tuesday);
				break;
			case 2:
				XYChart.Series<Number, Number> wednsday = new XYChart.Series<Number, Number>();
				wednsday.setName("Mittwoch");

				for (int j = 0; j < 1440; j++) {
					wednsday.getData().add(new Data<Number, Number>(j/60, j + 3));
				}
				mainChart.add(wednsday);
				break;
			case 3:
				XYChart.Series<Number, Number> thursday = new XYChart.Series<Number, Number>();
				thursday.setName("Donnerstag");

				for (int j = 0; j < 1440; j++) {
					thursday.getData().add(new Data<Number, Number>(j/60, j + 4));
				}
				mainChart.add(thursday);
				break;
			case 4:
				XYChart.Series<Number, Number> friday = new XYChart.Series<Number, Number>();
				friday.setName("Freitag");

				for (int j = 0; j < 1440; j++) {
					friday.getData().add(new Data<Number, Number>(j/60, j + 5));
				}
				mainChart.add(friday);
				break;
			case 5:
				XYChart.Series<Number, Number> saturday = new XYChart.Series<Number, Number>();
				saturday.setName("Samstag");

				for (int j = 0; j < 1440; j++) {
					saturday.getData().add(new Data<Number, Number>(j/60, j + 6));
				}
				mainChart.add(saturday);
				break;
			case 6:
				XYChart.Series<Number, Number> sunday = new XYChart.Series<Number, Number>();
				sunday.setName("Sonntag");

				for (int j = 0; j < 1440; j++) {
					sunday.getData().add(new Data<Number, Number>(j/60, j + 7));
				}
				mainChart.add(sunday);
				break;
			default:
				break;
			}

		}
		this.fahrplanvergleich.getData().addAll(mainChart);

	}

	// Methode zum Beenden des PopUp

	public void endStage() {

		dialogStage.close();
	}

	// Zuordnungsmethoden

	public Stage getDialogStage() {
		return dialogStage;
	}

	public Fahrplan getFahrplan() {
		return fahrplan;
	}

	public void setFahrplan(Fahrplan fahrplan) {
		this.fahrplan = fahrplan;
	}

	public int getErstauswahl() {
		return erstauswahl;
	}

	public void setErstauswahl(int erstauswahl) {
		this.erstauswahl = erstauswahl;
		this.tagEnde = erstauswahl;

		// Belegt das Endtagdropdownmenu
		this.endTag.getSelectionModel().select(this.erstauswahl);

		// Zeichnet die Grafik

		drawFahrplanvergleich();

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}
