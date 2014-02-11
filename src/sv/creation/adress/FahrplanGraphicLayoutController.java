package sv.creation.adress;

import java.util.ArrayList;

import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.util.Vergleich;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FahrplanGraphicLayoutController {

	// Zugriff auf Strukturelemente

	@FXML
	private ChoiceBox<String> startTag;
	@FXML
	private ChoiceBox<String> endTag;
	@FXML
	private AreaChart<Number, Number> fahrplanvergleich;
	@FXML
	private NumberAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	@FXML
	private Label monday;
	@FXML
	private Label tuesday;
	@FXML
	private Label wednsday;
	@FXML
	private Label thursday;
	@FXML
	private Label friday;
	@FXML
	private Label saturday;
	@FXML
	private Label sunday;
	@FXML
	private Pane mondayP;
	@FXML
	private Pane tuesdayP;
	@FXML
	private Pane wednsdayP;
	@FXML
	private Pane thursdayP;
	@FXML
	private Pane fridayP;
	@FXML
	private Pane saturdayP;
	@FXML
	private Pane sundayP;

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

		this.xAxis.setLowerBound(0);
		this.xAxis.setUpperBound(1440 / 60);
		this.xAxis.setTickUnit(1);

		// Belegung der Y-Achse

		// Belegung der Grafik

		this.fahrplanvergleich.setTitle(this.fahrplan.getBezeichnung());

		// Liste aus allen Grafiken
		ArrayList<XYChart.Series<Number, Number>> mainChart = new ArrayList<XYChart.Series<Number, Number>>();

		// Aufrufen des Vergleichs
		Vergleich vergleich = new Vergleich();
		int[] result;

		// Zuordnung der entsprechenden Fahrplaene

		for (int i = this.tagStart; i <= this.tagEnde; i++) {

			switch (i) {
			case 0:

				XYChart.Series<Number, Number> monday = new XYChart.Series<Number, Number>();
				monday.setName("Montag");

				result = vergleich.vergleicheFahrplan(this.fahrplan.getId(), 1);

				for (int j = 0; j < result.length; j++) {
					float xValue = (float) j / 60;
					monday.getData().add(
							new Data<Number, Number>(xValue, result[j]));
				}

				mainChart.add(monday);
				break;
			case 1:
				XYChart.Series<Number, Number> tuesday = new XYChart.Series<Number, Number>();
				tuesday.setName("Dienstag");

				result = vergleich.vergleicheFahrplan(this.fahrplan.getId(), 2);

				for (int j = 0; j < result.length; j++) {
					float xValue = (float) j / 60;
					tuesday.getData().add(
							new Data<Number, Number>(xValue, result[j]));
				}

				mainChart.add(tuesday);
				break;
			case 2:
				XYChart.Series<Number, Number> wednsday = new XYChart.Series<Number, Number>();
				wednsday.setName("Mittwoch");

				result = vergleich.vergleicheFahrplan(this.fahrplan.getId(), 3);

				for (int j = 0; j < result.length; j++) {
					float xValue = (float) j / 60;
					wednsday.getData().add(
							new Data<Number, Number>(xValue, result[j]));
				}

				mainChart.add(wednsday);
				break;
			case 3:
				XYChart.Series<Number, Number> thursday = new XYChart.Series<Number, Number>();
				thursday.setName("Donnerstag");

				result = vergleich.vergleicheFahrplan(this.fahrplan.getId(), 4);

				for (int j = 0; j < result.length; j++) {
					float xValue = (float) j / 60;
					thursday.getData().add(
							new Data<Number, Number>(xValue, result[j]));
				}

				mainChart.add(thursday);
				break;
			case 4:
				XYChart.Series<Number, Number> friday = new XYChart.Series<Number, Number>();
				friday.setName("Freitag");

				result = vergleich.vergleicheFahrplan(this.fahrplan.getId(), 5);

				for (int j = 0; j < result.length; j++) {
					float xValue = (float) j / 60;
					friday.getData().add(
							new Data<Number, Number>(xValue, result[j]));
				}

				mainChart.add(friday);
				break;
			case 5:
				XYChart.Series<Number, Number> saturday = new XYChart.Series<Number, Number>();
				saturday.setName("Samstag");

				result = vergleich.vergleicheFahrplan(this.fahrplan.getId(), 6);

				for (int j = 0; j < result.length; j++) {
					float xValue = (float) j / 60;
					saturday.getData().add(
							new Data<Number, Number>(xValue, result[j]));
				}

				mainChart.add(saturday);
				break;
			case 6:
				XYChart.Series<Number, Number> sunday = new XYChart.Series<Number, Number>();
				sunday.setName("Sonntag");

				result = vergleich.vergleicheFahrplan(this.fahrplan.getId(), 7);

				for (int j = 0; j < result.length; j++) {
					float xValue = (float) j / 60;
					sunday.getData().add(
							new Data<Number, Number>(xValue, result[j]));
				}

				mainChart.add(sunday);
				break;
			default:
				break;
			}

		}
		this.fahrplanvergleich.getData().addAll(mainChart);
		changeInfoColors();

	}
	
	/**
	 * Changes parameters of the graphic
	 */
	@FXML
	public void changeTimewindow() {
		
		if (this.tagStart <= this.tagEnde) {		
		
		// Clearen des Planes
		
		this.fahrplanvergleich.getData().clear();
		
		// Clearen der Legende
		
		ArrayList<Label> labelListe = new ArrayList<Label>();
		labelListe.add(this.monday);
		labelListe.add(this.tuesday);
		labelListe.add(this.wednsday);
		labelListe.add(this.thursday);
		labelListe.add(this.friday);
		labelListe.add(this.saturday);
		labelListe.add(this.sunday);
		
		ArrayList<Pane> paneListe = new ArrayList<Pane>();
		paneListe.add(this.mondayP);
		paneListe.add(this.tuesdayP);
		paneListe.add(this.wednsdayP);
		paneListe.add(this.thursdayP);
		paneListe.add(this.fridayP);
		paneListe.add(this.saturdayP);
		paneListe.add(this.sundayP);
		
		for (int i = 0; i < labelListe.size(); i++) {
			labelListe.get(i).setOpacity(0);
			paneListe.get(i).setOpacity(0);
		}
		
		// Auslesen der Dropdownmenues
		
		this.tagStart = this.startTag.getSelectionModel().getSelectedIndex();
		this.tagEnde = this.endTag.getSelectionModel().getSelectedIndex();
		
		drawFahrplanvergleich();
		}
	}

	// Methode zur Bestimmung der Farbe

	public void changeInfoColors() {
		
		ArrayList<Label> labelListe = new ArrayList<Label>();
		labelListe.add(this.monday);
		labelListe.add(this.tuesday);
		labelListe.add(this.wednsday);
		labelListe.add(this.thursday);
		labelListe.add(this.friday);
		labelListe.add(this.saturday);
		labelListe.add(this.sunday);
		
		ArrayList<Pane> paneListe = new ArrayList<Pane>();
		paneListe.add(this.mondayP);
		paneListe.add(this.tuesdayP);
		paneListe.add(this.wednsdayP);
		paneListe.add(this.thursdayP);
		paneListe.add(this.fridayP);
		paneListe.add(this.saturdayP);
		paneListe.add(this.sundayP);
		
		String[] colors = new String[7];
		colors[0]="-fx-background-color: #ffd65e;";
		colors[1]="-fx-background-color: #d2ff52;";
		colors[2]="-fx-background-color: #b3dced";
		colors[3]="-fx-background-color: #00b7ea;";
		colors[4]="-fx-background-color: #1e5799;";
		colors[5]="-fx-background-color: #fceabb;";
		colors[6]="-fx-background-color: #fceabb;";

		switch (this.tagStart) {
		case 0:
			switch (this.tagEnde) {
			case 0:
				for (int i = 0; i < labelListe.size()-6; i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i]);
				}				
				break;
			case 1:
				for (int i = 0; i < labelListe.size()-5; i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i]);
				}				
				break;
			case 2:
				for (int i = 0; i < labelListe.size()-4; i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i]);
				}				
				break;
			case 3:
				for (int i = 0; i < labelListe.size()-3; i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i]);
				}				
				break;
			case 4:
				for (int i = 0; i < labelListe.size()-2; i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i]);
				}				
				break;
			case 5:
				for (int i = 0; i < labelListe.size()-1; i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i]);
				}				
				break;
			case 6:
				for (int i = 0; i < labelListe.size(); i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i]);
				}				
				break;

			default:
				break;
			}

			break;
		case 1:
			switch (this.tagEnde) {
			case 1:
				for (int i = 1; i < labelListe.size()-5; i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i-1]);
				}				
				break;
			case 2:
				for (int i = 1; i < labelListe.size()-4; i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i-1]);
				}				
				break;
			case 3:
				for (int i = 1; i < labelListe.size()-3; i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i-1]);
				}				
				break;
			case 4:
				for (int i = 1; i < labelListe.size()-2; i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i-1]);
				}				
				break;
			case 5:
				for (int i = 1; i < labelListe.size()-1; i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i-1]);
				}				
				break;
			case 6:
				for (int i = 1; i < labelListe.size(); i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i-1]);
				}				
				break;

			default:
				break;
			}

			break;
		case 2:
			switch (this.tagEnde) {
			case 2:
				for (int i = 2; i < labelListe.size()-4; i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i-2]);
				}				
				break;
			case 3:
				for (int i = 2; i < labelListe.size()-3; i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i-2]);
				}				
				break;
			case 4:
				for (int i = 2; i < labelListe.size()-2; i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i-2]);
				}				
				break;
			case 5:
				for (int i = 2; i < labelListe.size()-1; i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i-2]);
				}				
				break;
			case 6:
				for (int i = 2; i < labelListe.size(); i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i-2]);
				}				
				break;

			default:
				break;
			}

			break;
		case 3:
			switch (this.tagEnde) {
			case 3:
				for (int i = 3; i < labelListe.size()-3; i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i-3]);
				}				
				break;
			case 4:
				for (int i = 3; i < labelListe.size()-2; i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i-3]);
				}				
				break;
			case 5:
				for (int i = 3; i < labelListe.size()-1; i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i-3]);
				}				
				break;
			case 6:
				for (int i = 3; i < labelListe.size(); i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i-3]);
				}				
				break;
			}

			break;
		case 4:
			switch (this.tagEnde) {
			case 4:
				for (int i = 4; i < labelListe.size()-2; i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i-4]);
				}				
				break;
			case 5:
				for (int i = 4; i < labelListe.size()-1; i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i-4]);
				}				
				break;
			case 6:
				for (int i = 4; i < labelListe.size(); i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i-4]);
				}				
				break;
			default:
				break;
			}

			break;
		case 5:
			switch (this.tagEnde) {
			case 5:
				for (int i = 5; i < labelListe.size()-1; i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i-5]);
				}				
				break;
			case 6:
				for (int i = 5; i < labelListe.size(); i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i-5]);
				}				
				break;
			default:
				break;
			}

			break;
		case 6:
			switch (this.tagEnde) {
			
			case 6:
				for (int i = 6; i < labelListe.size(); i++) {					
					// Fades in Filter Panel
					FadeTransition fa = new FadeTransition(Duration.millis(1500),
							labelListe.get(i));
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					
					FadeTransition faa = new FadeTransition(Duration.millis(1500),
							paneListe.get(i));
					faa.setFromValue(0.0);
					faa.setToValue(1.0);
					faa.setAutoReverse(true);
					faa.play();
					
					//Change Color
					paneListe.get(i).setStyle(colors[i-6]);
				}				
				break;

			default:
				break;
			}

			break;

		default:
			break;
		}
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
