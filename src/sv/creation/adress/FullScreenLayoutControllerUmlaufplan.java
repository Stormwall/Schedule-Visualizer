package sv.creation.adress;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sv.creation.adress.model.Umlaufplan;
import sv.creation.adress.util.StringSplitter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FullScreenLayoutControllerUmlaufplan {

	private Stage dialogStage;
	private Umlaufplan umlaufplan;

	// Strukturvariablen
	@FXML
	private AnchorPane GraphicPane;
	@FXML
	private AnchorPane xPane;
	@FXML
	private AnchorPane yPane;

	// Zeichenvariablen
	@FXML
	private Canvas canvas;
	@FXML
	private Canvas xCanvas;
	@FXML
	private Canvas yCanvas;
	@FXML
	private GraphicsContext gc;
	@FXML
	private GraphicsContext xgc;
	@FXML
	private GraphicsContext ygc;
	@FXML
	private int startzeitVar = 0;
	@FXML
	private int endzeitVar = 24;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {

		// An dieser Stelle wird der Bildschirm des Nutzers ausgelesen und die
		// Werte belegt

		this.GraphicPane.widthProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {

					}
				});

		this.GraphicPane.heightProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						drawGraphic();
					}
				});
		this.xPane.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(
					ObservableValue<? extends Number> observableValue,
					Number oldSceneWidth, Number newSceneWidth) {
				createUpperXScalePane();
			}
		});

		this.xPane.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(
					ObservableValue<? extends Number> observableValue,
					Number oldSceneWidth, Number newSceneWidth) {
			}
		});
	}

	public void drawGraphic() {

		// Initialize the Chart
		this.canvas = new Canvas(this.GraphicPane.getWidth(),
				this.GraphicPane.getHeight());

		// Erstellen des HintergrundgrafikKontextes
		this.gc = this.canvas.getGraphicsContext2D();
		this.gc.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());

		// Erstellen des Hintergrundes
		this.gc.setFill(Color.BEIGE);
		this.gc.fillRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());

		this.gc.setLineWidth(3);
		this.gc.setStroke(Color.BLACK);
		this.gc.strokeLine(1, 0, 1, this.canvas.getHeight());

		double abstandNetz = (this.canvas.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);
		this.gc.setLineWidth(1);
		this.gc.setFont(Font.getDefault());
		this.gc.setFill(Color.BLACK);
		this.gc.setStroke(Color.BLACK);

		// Variable zum Darstellen verschiedener Zeitpunkte
		for (int i = 0; i <= (this.endzeitVar - this.startzeitVar); i++) {

			double pixel = ((i) * abstandNetz);
			this.gc.strokeLine(pixel, 0, pixel, this.canvas.getHeight());
		}

		this.GraphicPane.getChildren().add(this.canvas);
		createUmlaufElementGraphic();
	}

	/**
	 * Creates The X - Scale.
	 */
	private void createUpperXScalePane() {

		// Hier wird das Skala Canvas erzeugt
		this.xCanvas = new Canvas(this.xPane.getWidth(), this.xPane.getHeight());
		// Hier der Graphic Context dazu erzeugt
		this.xgc = this.xCanvas.getGraphicsContext2D();
		this.xgc.clearRect(0, 0, this.xCanvas.getWidth(),
				this.xCanvas.getHeight());

		double abstandNetz = (this.xCanvas.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);
		this.xgc.setLineWidth(1);
		this.xgc.setFont(new Font("Arial", 12));
		this.xgc.setFill(Color.WHITE);
		this.xgc.setStroke(Color.BLACK);

		// Variable zum Darstellen verschiedener Zeitpunkte
		int chartCounter = startzeitVar;
		for (int i = 0; i <= (endzeitVar - startzeitVar); i++) {

			if (i == 0) {
				double pixel = ((i) * abstandNetz);
				this.xgc.fillText(String.valueOf(chartCounter), pixel + 1, 15);
				chartCounter = chartCounter + 1;
			}
			if (i != 0) {
				double pixel = ((i) * abstandNetz);
				this.xgc.fillText(String.valueOf(chartCounter), pixel - 4, 15);
				if (chartCounter < 23) {
					chartCounter = chartCounter + 1;
				} else {
					chartCounter = 0;
				}
			}
		}

		xPane.getChildren().add(this.xCanvas);
	}

	/**
	 * Creates Umlauf Elements in the Graphic.
	 */
	private void createUmlaufElementGraphic() {

		double abstandNetz = (this.canvas.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);

		// Auslesen der Blockanzahl
		for (int j = 0; j < this.umlaufplan.getUmlauf().size(); j++) {

			// Auslesen Blockelementanzahl
			for (int i = 0; i < this.umlaufplan.getFahrtZuUmlauf().size(); i++) {

				// Abgleich mit den Werten
				if (this.umlaufplan.getFahrtZuUmlauf().get(i).getBlockID() == this.umlaufplan
						.getUmlauf().get(j).getId()) {

					// Auslesen der Zeit als Integer
					StringSplitter ss = new StringSplitter();
					int[] zeit = new int[2];
					zeit = ss.intParse(this.umlaufplan.getFahrtZuUmlauf().get(i)
							.getDepTime());
					int startHour = zeit[0];
					int startMin = zeit[1];
					zeit = ss.intParse(this.umlaufplan.getFahrtZuUmlauf().get(i)
							.getArrTime());

					// Belegung der Pixelwerte
					if (this.startzeitVar <= startHour) {
						int stundenDifferenz = startHour - this.startzeitVar;
						int startPixelX = (int) ((stundenDifferenz * abstandNetz) + ((abstandNetz / 60) * startMin));
						double b = this.GraphicPane.getHeight()/this.umlaufplan.getUmlauf().size();
						Integer breite = (int) b;
						int startPixelY = 10 + (j * breite);
						int fahrtDauer = 0;

						// Berrechnen der Dauer zwischen der Abfahrt und der
						// Ankunft

						String depTime = this.umlaufplan.getFahrtZuUmlauf().get(i)
								.getDepTime();
						String arrtime = this.umlaufplan.getFahrtZuUmlauf().get(i)
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

						switch (this.umlaufplan.getFahrtZuUmlauf().get(i)
								.getElementType()) {

						case 1:
							// Servicefahrt
							this.gc.setFill(Color.SEAGREEN);
							break;
						case 2:
							// Leerfahrt Haltestellen
							this.gc.setFill(Color.LIGHTCORAL);
							break;
						case 3:
							// Fahrt ins Depot
							this.gc.setFill(Color.ANTIQUEWHITE);
							break;
						case 4:
							// Fahrt aus dem Depot
							this.gc.setFill(Color.WHITESMOKE);
							break;
						case 5:
							// Vorbereitung
							this.gc.setFill(Color.GREEN);
							break;
						case 6:
							// Nachbereitung
							this.gc.setFill(Color.GREEN);
							break;
						case 7:
							// Transfer
							this.gc.setFill(Color.GREEN);
							break;
						case 8:
							// Pause
							this.gc.setFill(Color.ORANGE);
							break;
						case 9:
							// Warten
							this.gc.setFill(Color.LIGHTSKYBLUE);
							break;
						case 10:
							// LayoverTime
							this.gc.setFill(Color.GREEN);
							break;
						}

						// Malen der Elemente

						this.gc.fillRoundRect(startPixelX, startPixelY, fahrtDauer,
								breite/2, 20, 10);
						this.gc.strokeRoundRect(startPixelX, startPixelY,
								fahrtDauer, breite/2, 20, 10);

//						// Beschriftet die Elemente
//
//						if (fahrtDauer > 30) {
//							this.gc.setFill(Color.BLACK);
//							this.gc.setFont(new Font("Arial", 12));
//							this.gc.fillText(
//									String.valueOf(umlaufplan
//											.getFahrtZuUmlauf().get(i).getId()),
//									startPixelX - 3 + (fahrtDauer / 5),
//									startPixelY + 14);
//						}
					}
				}
			}
		}
	}

	// Methode zum Beenden des PopUp

	public void endStage() {

		dialogStage.close();
	}

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

		// Auslesen der Startzeit

		ArrayList<String> startzeiten = new ArrayList<String>();
		ArrayList<String> endzeiten = new ArrayList<String>();

		for (int i = 0; i < this.umlaufplan.getUmlauf().size(); i++) {
			boolean anfang = true;

			for (int j = 0; j < this.umlaufplan.getFahrtZuUmlauf().size(); j++) {

				if (this.umlaufplan.getUmlauf().get(i).getId() == this.umlaufplan
						.getFahrtZuUmlauf().get(j).getBlockID()
						&& anfang == true) {
					startzeiten.add(this.umlaufplan.getFahrtZuUmlauf().get(j)
							.getDepTime());
					anfang = false;
					if (1 < startzeiten.size()) {
						endzeiten.add(this.umlaufplan.getFahrtZuUmlauf()
								.get(j - 1).getArrTime());
					}
				}

			}
			anfang = true;
		}

		endzeiten.add(this.umlaufplan.getFahrtZuUmlauf()
				.get(this.umlaufplan.getFahrtZuUmlauf().size() - 1)
				.getArrTime());

		for (int j = 0; j < startzeiten.size(); j++) {

			// Auslesen der Zeit als Integer
			StringSplitter ss = new StringSplitter();
			int[] zeit = new int[2];
			zeit = ss.intParse(startzeiten.get(j));
			int startHour = zeit[0];
			if (this.startzeitVar > startHour || j == 0) {
				this.startzeitVar = startHour;
			}
		}
		for (int j = 0; j < startzeiten.size(); j++) {

			// Auslesen der Zeit als Integer
			StringSplitter ss = new StringSplitter();
			int[] zeit = new int[2];
			zeit = ss.intParse(endzeiten.get(j));
			int endHour = zeit[0];
			if (endHour < 12) {
				endHour = endHour + 24;
			}
			if (this.endzeitVar < endHour || j == 0) {
				this.endzeitVar = endHour;
			}
		}
		this.endzeitVar = this.endzeitVar + 1;

	}

}
