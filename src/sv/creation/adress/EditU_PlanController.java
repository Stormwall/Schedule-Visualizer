package sv.creation.adress;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sv.creation.adress.model.Blockelement;
import sv.creation.adress.model.Umlaufplan;
import sv.creation.adress.util.Export;
import sv.creation.adress.util.StringSplitter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EditU_PlanController {

	// Strukturelemente dieser Stage
	@FXML
	private AnchorPane canvas;
	@FXML
	private ChoiceBox<String> blockChoice;
	@FXML
	private TableView<Blockelement> elementsTable = new TableView<Blockelement>();
	@FXML
	private TableColumn<Blockelement, Integer> blockEleCol;
	@FXML
	private TableColumn<Blockelement, String> startzeitCol;
	@FXML
	private TableColumn<Blockelement, String> endzeitCol;
	@FXML
	private TableColumn<Blockelement, String> eleTypeCol;
	@FXML
	private TableColumn<Blockelement, Integer> blockCol;
	@FXML
	private TableColumn<Blockelement, String> dauerCol;

	// Verbindung zum MainLayout
	private Stage dialogStage;

	// Arbeitsobjekte der Stage

	private Umlaufplan umlaufplan;
	private boolean okClicked = false;

	// Referenz zur MainApp

	private MainApplication mainApp;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {

		// Anordnung der Tabelle
		this.elementsTable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		// Listen for Resizechanges (Graphic)
		this.canvas.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(
					ObservableValue<? extends Number> observableValue,
					Number oldSceneWidth, Number newSceneWidth) {
				handleauswaehlen();
			}
		});

	}

	/**
	 * Called when the user clicks auswählen.
	 */
	@FXML
	private void handleauswaehlen() {

		int blockAuswahl = 0;

		if (this.blockChoice.getSelectionModel().getSelectedItem() != null) {

			this.elementsTable.getItems().clear();

			blockAuswahl = this.umlaufplan
					.getUmlauf()
					.get(this.blockChoice.getSelectionModel()
							.getSelectedIndex()).getId();

			// Hereinladen der Daten

			ObservableList<Blockelement> data = FXCollections
					.observableArrayList();

			for (int i = 0; i < this.umlaufplan.getFahrtZuUmlauf().size(); i++) {
				if (this.umlaufplan.getFahrtZuUmlauf().get(i).getBlockID() == blockAuswahl) {
					data.add(this.umlaufplan.getFahrtZuUmlauf().get(i));
				}

			}
			// Übergabe der Daten an Tabelle und Canvas
			this.elementsTable.setItems(data);
			drawCanvas(blockAuswahl);
		}
	}

	/**
	 * Called when the user clicks übernehmen.
	 */
	@FXML
	private void handleuebernehemen() {

		okClicked = true;
		dialogStage.close();
	}

	/**
	 * Called when the user clicks bearbeiten.
	 */
	@FXML
	private void handlebearbeiten() {

		// Fehlerbehebung bei keiner Auswahl

		if (this.elementsTable.getSelectionModel().getSelectedItem() != null) {

			// Ausgewähltes Element auslesen

			Blockelement blockelement = this.elementsTable.getSelectionModel()
					.getSelectedItem();

			// Variablen werden belegt
			String startzeit = blockelement.getDepTime();
			String endzeit = blockelement.getArrTime();
			int id = blockelement.getId();

			boolean okClicked = mainApp.showEditTimeDetails(startzeit, endzeit);
			if (okClicked) {
				for (int i = 0; i < this.umlaufplan.getFahrtZuUmlauf().size(); i++) {
					if (this.umlaufplan.getFahrtZuUmlauf().get(i).getId() == id) {
						this.umlaufplan.getFahrtZuUmlauf().get(i)
								.setDepTime(this.mainApp.getStartzeit());
						this.umlaufplan.getFahrtZuUmlauf().get(i)
								.setArrTime(this.mainApp.getEndzeit());
					}
				}
				handleauswaehlen();
				this.elementsTable.getColumns().get(0).setVisible(false);
				this.elementsTable.getColumns().get(0).setVisible(true);
				this.elementsTable.getColumns().get(1).setVisible(false);
				this.elementsTable.getColumns().get(1).setVisible(true);
				this.elementsTable.getColumns().get(2).setVisible(false);
				this.elementsTable.getColumns().get(2).setVisible(true);
				this.elementsTable.getColumns().get(3).setVisible(false);
				this.elementsTable.getColumns().get(3).setVisible(true);
				this.elementsTable.getColumns().get(4).setVisible(false);
				this.elementsTable.getColumns().get(4).setVisible(true);
				this.elementsTable.getColumns().get(5).setVisible(false);
				this.elementsTable.getColumns().get(5).setVisible(true);
			}

		} else {
			String fehlerA = "Es wurde noch Element ausgewählt";
			String fehlerB = "Was soll bearbeitet werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}

	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Exports the UPlan
	 * 
	 * @return
	 */
	public void handleExport() {

		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"TXT files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(this.mainApp.getPrimaryStage());

		if (file != null) {
			// Make sure it has the correct extension
			if (!file.getPath().endsWith(".txt")) {
				file = new File(file.getPath() + ".txt");
			}
				
			Export export = new Export();
			export.exportUmlaufplan(umlaufplan, file);
		}
	}

	// Draws the Canvas

	public void drawCanvas(int auswahl) {

		this.canvas.getChildren().clear();
		
		// Start und Endzeit
		
		int startzeitVar = 0;
		int endzeitVar = 20;
		boolean start = true;
		
		// Auslesen Blockelementanzahl
		for (int i = 0; i < umlaufplan.getFahrtZuUmlauf().size(); i++) {

			// Abgleich mit den Werten
			if (umlaufplan.getFahrtZuUmlauf().get(i).getBlockID() == auswahl) {
			
				// Auslesen der Zeit als Integer
				StringSplitter ss = new StringSplitter();
				int[] zeit = new int[2];
				zeit = ss.intParse(umlaufplan.getFahrtZuUmlauf().get(i)
						.getDepTime());
				int startHour = zeit[0];
				zeit = ss.intParse(umlaufplan.getFahrtZuUmlauf().get(i)
						.getArrTime());
				int endHour = zeit[0];
				
				if (start == true) {
					startzeitVar = startHour;
					start = false;
				}
				if (umlaufplan.getFahrtZuUmlauf().get(i+1).getBlockID() != auswahl) {
					endzeitVar = endHour+1;
					if (endzeitVar<startzeitVar) {
						endzeitVar = endzeitVar +24;
					}
				}
			}
		}
		
		

		// Initialize the Chart
		Canvas Chart = new Canvas(this.canvas.getWidth() - 4,
				this.canvas.getHeight());

		// An dieser Stelle wird die Breite an den Tableview gebunden
		Chart.widthProperty().bind(this.elementsTable.widthProperty());

		// Erstellen des HintergrundgrafikKontextes
		GraphicsContext gc = Chart.getGraphicsContext2D();
		gc.clearRect(0, 0, Chart.getWidth(), Chart.getHeight());

		// Erstellen des Hintergrundes

		double abstandNetz = (Chart.getWidth() - 30)
				/ (endzeitVar - startzeitVar);
		gc.setLineWidth(1);
		gc.setFont(Font.getDefault());
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.BLACK);
		
		// Variable zum Darstellen verschiedener Zeitpunkte
		for (int i = 0; i <= (endzeitVar - startzeitVar); i++) {

			double pixel = ((i) * abstandNetz)+17;
			gc.strokeLine(pixel, 0, pixel, Chart.getHeight());
		}

		// Zeichnen der Elemente

		// Auslesen Blockelementanzahl
		for (int i = 0; i < umlaufplan.getFahrtZuUmlauf().size(); i++) {

			// Abgleich mit den Werten
			if (umlaufplan.getFahrtZuUmlauf().get(i).getBlockID() == auswahl) {

				// Auslesen der Zeit als Integer
				StringSplitter ss = new StringSplitter();
				int[] zeit = new int[2];
				zeit = ss.intParse(umlaufplan.getFahrtZuUmlauf().get(i)
						.getDepTime());
				int startHour = zeit[0];
				int startMin = zeit[1];
				zeit = ss.intParse(umlaufplan.getFahrtZuUmlauf().get(i)
						.getArrTime());
				int endHour = zeit[0];

				// Belegung der Pixelwerte
				if (0 <= startHour && 24 > endHour) {
					int stundenDifferenz = startHour - startzeitVar;
					int startPixelX = (int) ((stundenDifferenz * abstandNetz) + ((abstandNetz / 60) * startMin))+17;
					int startPixelY = 10;
					int fahrtDauer = 0;
					// Berrechnen der Dauer zwischen der Abfahrt und der
					// Ankunft

					String depTime = umlaufplan.getFahrtZuUmlauf().get(i)
							.getDepTime();
					String arrtime = umlaufplan.getFahrtZuUmlauf().get(i)
							.getArrTime();

					SimpleDateFormat format = new SimpleDateFormat("HH:mm");
					Date date1 = null;
					Date date2 = null;
					try {
						date1 = format.parse(depTime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						date2 = format.parse(arrtime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					long differenz = date2.getTime() - date1.getTime();
					differenz = (differenz / 1000) / 60;

					fahrtDauer = (int) (differenz * (abstandNetz / 60));

					// Färben der Elemente

					switch (umlaufplan.getFahrtZuUmlauf().get(i)
							.getElementType()) {

					case 1:
						// Servicefahrt
						gc.setFill(Color.SEAGREEN);
						break;
					case 2:
						// Leerfahrt Haltestellen
						gc.setFill(Color.LIGHTCORAL);
						break;
					case 3:
						// Fahrt ins Depot
						gc.setFill(Color.ANTIQUEWHITE);
						break;
					case 4:
						// Fahrt aus dem Depot
						gc.setFill(Color.WHITESMOKE);
						break;
					case 5:
						// Vorbereitung
						gc.setFill(Color.GREEN);
						break;
					case 6:
						// Nachbereitung
						gc.setFill(Color.GREEN);
						break;
					case 7:
						// Transfer
						gc.setFill(Color.GREEN);
						break;
					case 8:
						// Pause
						gc.setFill(Color.ORANGE);
						break;
					case 9:
						// Warten
						gc.setFill(Color.LIGHTSKYBLUE);
						break;
					case 10:
						// LayoverTime
						gc.setFill(Color.GREEN);
						break;
					}

					// Malen der Elemente

					gc.fillRoundRect(startPixelX, startPixelY, fahrtDauer, 20,
							20, 10);
					gc.strokeRoundRect(startPixelX, startPixelY, fahrtDauer,
							20, 20, 10);
					
					// Beschriftet die Elemente
						if (fahrtDauer > 30) {
							gc.setFill(Color.BLACK);
							gc.setFont(new Font("Arial", 12));
							gc.fillText(
									String.valueOf(umlaufplan
											.getFahrtZuUmlauf().get(i)
											.getId()), startPixelX - 3
											+ (fahrtDauer / 5), startPixelY
											+ 14);
						
					}
				}

			}
		}

		this.canvas.getChildren().add(Chart);
	}
	
	/**
	 * Called when the user clicks Vollbild.
	 */
	@FXML
	private void handleFullsreen() {
		mainApp.showFullScreenGraphicUmlaufplan(this.umlaufplan);
	}

	// Methode zum Beenden des PopUp
	@FXML
	public void endStage() {
		dialogStage.close();
	}

	// Zuordnungsmethoden

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public Umlaufplan getUmlaufplan() {
		return umlaufplan;
	}

	public void setUmlaufplan(Umlaufplan umlaufplan) {
		this.umlaufplan = umlaufplan;

		// Belegung der Tabelle mit Daten

		blockEleCol
				.setCellValueFactory(new PropertyValueFactory<Blockelement, Integer>(
						"id"));
		startzeitCol
				.setCellValueFactory(new PropertyValueFactory<Blockelement, String>(
						"depTime"));
		endzeitCol
				.setCellValueFactory(new PropertyValueFactory<Blockelement, String>(
						"arrTime"));
		eleTypeCol
				.setCellValueFactory(new PropertyValueFactory<Blockelement, String>(
						"elementTypeName"));
		blockCol.setCellValueFactory(new PropertyValueFactory<Blockelement, Integer>(
				"blockID"));
		dauerCol.setCellValueFactory(new PropertyValueFactory<Blockelement, String>(
				"driveTime"));

		// Hereinladen der Daten

		ObservableList<Blockelement> data = FXCollections.observableArrayList();

		for (int i = 0; i < umlaufplan.getFahrtZuUmlauf().size(); i++) {
			data.add(umlaufplan.getFahrtZuUmlauf().get(i));
		}

		elementsTable.setItems(data);

		// Belegung des Dropdownmenues

		ObservableList<String> list = FXCollections
				.<String> observableArrayList();

		for (int i = 0; i < umlaufplan.getUmlauf().size(); i++) {
			String input = ("Block " + umlaufplan.getUmlauf().get(i).getId());
			list.add(input);
		}

		this.blockChoice.setItems(list);

	}

	public void setMainApp(MainApplication mainApp) {
		this.mainApp = mainApp;
	}

}
