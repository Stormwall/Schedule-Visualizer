package sv.creation.adress;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import sv.creation.adress.model.Szenario;
import sv.creation.adress.model.Umlaufplan;
import sv.creation.adress.util.StringSplitter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
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
	@FXML
	private CheckBox beschriftung;

	// Zeichenvariablen

	private Canvas canvas;
	private Canvas xCanvas;
	private Canvas yCanvas;
	private GraphicsContext gc;
	private GraphicsContext xgc;
	private GraphicsContext ygc;
	private int startzeitVar = 0;
	private int endzeitVar = 24;
	private int breite = 0;
	private boolean beschriftungCheck = false;
	private ArrayList<String> colors = new ArrayList<String>();
	private Szenario szenario;
	private boolean szenarienAktiv = false;
	private int fahrplanID;
	
	// Referenz zur MainApp

	private MainApplication mainApp;

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
						GraphicPane.getChildren().clear();
						drawGraphic();
					}
				});

		this.GraphicPane.heightProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						GraphicPane.getChildren().clear();
						drawGraphic();
					}
				});
		this.xPane.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(
					ObservableValue<? extends Number> observableValue,
					Number oldSceneWidth, Number newSceneWidth) {
				xPane.getChildren().clear();
				createXScale();
			}
		});

		this.xPane.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(
					ObservableValue<? extends Number> observableValue,
					Number oldSceneWidth, Number newSceneWidth) {
				xPane.getChildren().clear();
				createXScale();
			}
		});

		this.yPane.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(
					ObservableValue<? extends Number> observableValue,
					Number oldSceneWidth, Number newSceneWidth) {
				yPane.getChildren().clear();
				createYScale();
			}
		});

		this.yPane.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(
					ObservableValue<? extends Number> observableValue,
					Number oldSceneWidth, Number newSceneWidth) {
				yPane.getChildren().clear();
				createYScale();
			}
		});

		// Listen for selection changes (Checkbox... Beschriftung)

		this.beschriftung.selectedProperty().addListener(
				new ChangeListener<Boolean>() {
					public void changed(ObservableValue<? extends Boolean> ov,
							Boolean old_val, Boolean new_val) {
						// Handhabung wenn die Checkbox angewï¿½hlt wird
						if (new_val == true) {
							GraphicPane.getChildren().clear();
							beschriftungCheck = true;
							drawGraphic();

						}
						// Handhabung wenn die Checkbox ausgeschaltet wird
						if (new_val == false) {
							GraphicPane.getChildren().clear();
							beschriftungCheck = false;
							drawGraphic();
						}
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

		// Berrechnung der Breite der Elemente
		double b = this.GraphicPane.getHeight()
				/ this.umlaufplan.getUmlauf().size();
		this.breite = (int) b;

		this.GraphicPane.getChildren().add(this.canvas);
		createUmlaufElementGraphic();
	}

	/**
	 * Creates The X - Scale.
	 */
	private void createXScale() {

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
	 * Creates The Y - Scale.
	 */
	private void createYScale() {

		// Hier wird das Skala Canvas erzeugt
		this.yCanvas = new Canvas(this.yPane.getWidth(), this.yPane.getHeight());

		// Hier der Graphic Context dazu erzeugt
		this.ygc = this.yCanvas.getGraphicsContext2D();
		this.ygc.clearRect(0, 0, this.yCanvas.getWidth(),
				this.yCanvas.getHeight());

		// Eigenschaften der Beschriftung
		this.ygc.setLineWidth(1);
		this.ygc.setFont(new Font("Arial", 12));
		this.ygc.setFill(Color.WHITE);
		this.ygc.setStroke(Color.BLACK);
		// Auslesen der Werte
		for (int j = 0; j < umlaufplan.getUmlauf().size(); j++) {

			ygc.fillText("B" + umlaufplan.getUmlauf().get(j).getId(), 4,
					22 + (j * this.breite));

		}

		yPane.getChildren().add(this.yCanvas);
	}

	/**
	 * Creates Umlauf Elements in the Graphic.
	 */
	private void createUmlaufElementGraphic() {

		double abstandNetz = (this.canvas.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);

		// Auslesen der Blockanzahl
		for (int j = 0; j < this.umlaufplan.getUmlauf().size(); j++) {

			// Variablen zur Prüfung der ersten Stunde
			int changeHour = 100;
			int changeMin = 100;
			Boolean anfangUmlauf = true;

			// Auslesen Blockelementanzahl
			for (int i = 0; i < this.umlaufplan.getFahrtZuUmlauf().size(); i++) {

				// Abgleich mit den Werten
				if (this.umlaufplan.getFahrtZuUmlauf().get(i).getBlockID() == this.umlaufplan
						.getUmlauf().get(j).getId()) {

					// Auslesen der Zeit als Integer
					StringSplitter ss = new StringSplitter();
					int[] zeit = new int[2];
					zeit = ss.intParse(this.umlaufplan.getFahrtZuUmlauf()
							.get(i).getDepTime());
					int startHour = zeit[0];
					int startMin = zeit[1];
					zeit = ss.intParse(this.umlaufplan.getFahrtZuUmlauf()
							.get(i).getArrTime());

					// Prüfung der ersten Stunde
					if (anfangUmlauf == true && startHour < changeHour) {
						changeHour = startHour;
						changeMin = startMin;
						anfangUmlauf = false;
					}
					if (startHour < changeHour) {
						startHour = startHour + 24;
					}
					if (startHour == changeHour && startMin < changeMin) {
						startHour = startHour + 24;
					}

					// Belegung der Pixelwerte
					if (this.startzeitVar <= startHour) {
						int stundenDifferenz = startHour - this.startzeitVar;
						int startPixelX = (int) ((stundenDifferenz * abstandNetz) + ((abstandNetz / 60) * startMin));

						int startPixelY = 10 + (j * this.breite);
						int fahrtDauer = 0;

						// Berrechnen der Dauer zwischen der Abfahrt und der
						// Ankunft

						String depTime = this.umlaufplan.getFahrtZuUmlauf()
								.get(i).getDepTime();
						String arrtime = this.umlaufplan.getFahrtZuUmlauf()
								.get(i).getArrTime();

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

						// Über 0 Uhr
						if (date2.getTime() < date1.getTime()) {
							differenz = date2.getTime() - date1.getTime()
									+ 86400000;
						}

						differenz = (differenz / 1000) / 60;

						fahrtDauer = (int) (differenz * (abstandNetz / 60));

						// FÃ¤rben der Elemente

						switch (this.umlaufplan.getFahrtZuUmlauf().get(i)
								.getElementType()) {

						case 1:
							// Servicefahrt
							gc.setFill(javafx.scene.paint.Color
									.valueOf(this.colors.get(0)));
							break;
						case 2:
							// Leerfahrt Haltestellen
							gc.setFill(javafx.scene.paint.Color
									.valueOf(this.colors.get(1)));
							break;
						case 3:
							// Fahrt ins Depot
							gc.setFill(javafx.scene.paint.Color
									.valueOf(this.colors.get(2)));
							break;
						case 4:
							// Fahrt aus dem Depot
							gc.setFill(javafx.scene.paint.Color
									.valueOf(this.colors.get(3)));
							break;
						case 5:
							// Vorbereitung
							gc.setFill(javafx.scene.paint.Color
									.valueOf(this.colors.get(4)));
							break;
						case 6:
							// Nachbereitung
							gc.setFill(javafx.scene.paint.Color
									.valueOf(this.colors.get(5)));
							break;
						case 7:
							// Transfer
							gc.setFill(javafx.scene.paint.Color
									.valueOf(this.colors.get(6)));
							break;
						case 8:
							// Pause
							gc.setFill(javafx.scene.paint.Color
									.valueOf(this.colors.get(7)));
							break;
						case 9:
							// Warten
							gc.setFill(javafx.scene.paint.Color
									.valueOf(this.colors.get(8)));
							break;
						case 10:
							// LayoverTime
							gc.setFill(javafx.scene.paint.Color
									.valueOf(this.colors.get(9)));
							break;
						}

						// Malen der Elemente

						this.gc.fillRoundRect(startPixelX, startPixelY,
								fahrtDauer, breite / 2, 20, 10);
						this.gc.strokeRoundRect(startPixelX, startPixelY,
								fahrtDauer, breite / 2, 20, 10);
						
						// Szenarien
						if (this.szenarienAktiv && umlaufplan.getFahrplanID() == this.fahrplanID) {
							double delay;
							for (int k = 0; k < this.szenario.getPrimeDelay().size(); k++) {
								if (this.szenario.getPrimeDelay().get(k).getServiceJourneyID().equals(umlaufplan.getFahrtZuUmlauf().get(i).getServiceJourneyID())) {
									delay = (abstandNetz / 60) * (this.szenario.getPrimeDelay().get(k).getDelay()/60);
									
									gc.setStroke(Color.RED);
									gc.setFill(Color.RED);
									gc.setLineWidth(2);
									
									gc.strokeRoundRect(startPixelX, startPixelY- breite / 10, fahrtDauer + delay, breite / 1.5, 20, 10);
									
								}
							}	
							
							gc.setStroke(Color.BLACK);
							gc.setLineWidth(1);
						}


						// Beschriftet die Elemente
						if (beschriftungCheck) {
							if (fahrtDauer > 30) {
								this.gc.setFill(Color.BLACK);
								this.gc.setFont(new Font("Arial", 12));
								this.gc.fillText(
										String.valueOf(umlaufplan
												.getFahrtZuUmlauf().get(i)
												.getId()), startPixelX - 3
												+ (fahrtDauer / 5), startPixelY
												+ 1 + breite / 3);
							}
						}

					}
				}
			}
		}
	}

	@FXML
	public void saveAsPng() {

		WritableImage image = GraphicPane.snapshot(new SnapshotParameters(),
				null);

		FileChooser fileChooser = new FileChooser();

		// Set extension filter
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter(
				"PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().add(extFilterPNG);

		// TODO: probably use a file chooser here
		File fileF = fileChooser.showSaveDialog(this.mainApp.getPrimaryStage());
		File file = new File(fileF.getAbsolutePath() + ".png");

		try {
			ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
		} catch (IOException e) {
			// TODO: handle exception here
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

	public MainApplication getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApplication mainApp) {
		this.mainApp = mainApp;
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
			if (endHour < 7) {
				endHour = endHour + 24;
			}
			if (this.endzeitVar < endHour || j == 0) {
				this.endzeitVar = endHour;
			}
		}
		this.endzeitVar = this.endzeitVar + 1;

	}

	public ArrayList<String> getColors() {
		return colors;
	}

	public void setColors(ArrayList<String> colors) {
		this.colors = colors;
	}

	public void setSzenario(Szenario szenario) {
		this.szenario = szenario;
	}

	public void setSzenarienAktiv(boolean szenarienAktiv) {
		this.szenarienAktiv = szenarienAktiv;
	}

	public void setFahrplanID(int fahrplanID) {
		this.fahrplanID = fahrplanID;
	}

}
