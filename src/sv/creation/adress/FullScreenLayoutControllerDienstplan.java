package sv.creation.adress;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import sv.creation.adress.model.Dienstplan;
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

public class FullScreenLayoutControllerDienstplan {

	private Stage dialogStage;
	private Dienstplan dienstplan;

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
		this.gc.setFill(Color.ALICEBLUE);
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
				/ this.dienstplan.getDuty().size();
		this.breite = (int) b;

		this.GraphicPane.getChildren().add(this.canvas);
		createDienstElementGraphic();
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
		
		// Sortierung des Planes
				ArrayList<String> orderList = new ArrayList<String>();
				ArrayList<Integer> listInt = new ArrayList<Integer>();
				ArrayList<Integer> match = new ArrayList<Integer>();
				ArrayList<Integer> orderedDuty = new ArrayList<Integer>();
				boolean idChange = true;
				String dutyBefore = null;

				for (int i = 0; i < this.dienstplan.getDutyelement().size(); i++) {
					if (idChange) {
						orderList.add(this.dienstplan.getDutyelement().get(i).getDepTime());
						dutyBefore = this.dienstplan.getDutyelement().get(i).getDutyID();
						idChange = false;
					}
					if(!this.dienstplan.getDutyelement().get(i).getDutyID().equals(dutyBefore)){
						idChange = true;
						i = i-1;
					}
				}

				for (int i = 0; i < orderList.size(); i++) {
					StringSplitter ss = new StringSplitter();
					int[] zeit = new int[2];
					zeit = ss.intParse(orderList.get(i));
					int startHour = zeit[0];
					int startMin = zeit[1];

					listInt.add((startHour * 100) + startMin);
					match.add((startHour * 100) + startMin);
				}
				// Sortierung (Bubble Sort)
				boolean unsortiert = true;
				int temp;
				while (unsortiert) {
					unsortiert = false;
					for (int i = 0; i < listInt.size() - 1; i++)
						if (listInt.get(i) > listInt.get(i + 1)) {
							temp = listInt.get(i);
							listInt.set(i, listInt.get(i + 1));
							listInt.set(i + 1, temp);
							unsortiert = true;
						}
				}

				for (int i = 0; i < listInt.size(); i++) {
					temp = listInt.get(i);
					for (int j = 0; j < match.size(); j++) {
						if (match.get(j) == temp) {
							int index = j;
							boolean auslassen = false;
							for (int j2 = 0; j2 < orderedDuty.size(); j2++) {
								if (index == orderedDuty.get(j2)) {
									auslassen = true;
								}
							}
							if (auslassen == false) {
								orderedDuty.add(j);
							}
						}
					}
				}

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
		for (int j = 0; j < dienstplan.getDuty().size(); j++) {

			ygc.fillText("D " + dienstplan.getDuty().get(orderedDuty.get(j)).getHilfsID(), 4,
					22 + (j * this.breite));

		}

		yPane.getChildren().add(this.yCanvas);
	}

	/**
	 * Creates Duty Elements in the Graphic.
	 */
	private void createDienstElementGraphic() {

		// Sortierung des Planes
		ArrayList<String> orderList = new ArrayList<String>();
		ArrayList<Integer> listInt = new ArrayList<Integer>();
		ArrayList<Integer> match = new ArrayList<Integer>();
		ArrayList<Integer> orderedDuty = new ArrayList<Integer>();
		boolean idChange = true;
		String dutyBefore = null;

		for (int i = 0; i < this.dienstplan.getDutyelement().size(); i++) {
			if (idChange) {
				orderList.add(this.dienstplan.getDutyelement().get(i).getDepTime());
				dutyBefore = this.dienstplan.getDutyelement().get(i).getDutyID();
				idChange = false;
			}
			if(!this.dienstplan.getDutyelement().get(i).getDutyID().equals(dutyBefore)){
				idChange = true;
				i = i-1;
			}
		}

		for (int i = 0; i < orderList.size(); i++) {
			StringSplitter ss = new StringSplitter();
			int[] zeit = new int[2];
			zeit = ss.intParse(orderList.get(i));
			int startHour = zeit[0];
			int startMin = zeit[1];

			listInt.add((startHour * 100) + startMin);
			match.add((startHour * 100) + startMin);
		}
		// Sortierung (Bubble Sort)
		boolean unsortiert = true;
		int temp;
		while (unsortiert) {
			unsortiert = false;
			for (int i = 0; i < listInt.size() - 1; i++)
				if (listInt.get(i) > listInt.get(i + 1)) {
					temp = listInt.get(i);
					listInt.set(i, listInt.get(i + 1));
					listInt.set(i + 1, temp);
					unsortiert = true;
				}
		}

		for (int i = 0; i < listInt.size(); i++) {
			temp = listInt.get(i);
			for (int j = 0; j < match.size(); j++) {
				if (match.get(j) == temp) {
					int index = j;
					boolean auslassen = false;
					for (int j2 = 0; j2 < orderedDuty.size(); j2++) {
						if (index == orderedDuty.get(j2)) {
							auslassen = true;
						}
					}
					if (auslassen == false) {
						orderedDuty.add(j);
					}
				}
			}
		}

		double abstandNetz = (this.canvas.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);

		// Auslesen der Blockanzahl
		for (int j = 0; j < this.dienstplan.getDuty().size(); j++) {

			// Variablen zur Prüfung der ersten Stunde
			int changeHour = 100;
			int changeMin = 100;
			Boolean anfangDienst = true;

			// Auslesen Blockelementanzahl
			for (int i = 0; i < this.dienstplan.getDutyelement().size(); i++) {

				// Abgleich mit den Werten
				if (dienstplan.getDutyelement().get(i).getDutyID()
						.equals(dienstplan.getDuty().get(orderedDuty.get(j)).getId())) {

					// Auslesen der Zeit als Integer
					StringSplitter ss = new StringSplitter();
					int[] zeit = new int[2];
					zeit = ss.intParse(this.dienstplan.getDutyelement().get(i)
							.getDepTime());
					int startHour = zeit[0];
					int startMin = zeit[1];

					// Prüfung der ersten Stunde
					if (anfangDienst == true && startHour < changeHour) {
						changeHour = startHour;
						changeMin = startMin;
						anfangDienst = false;
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

						int startPixelY = 15 + (j * this.breite);
						int fahrtDauer = 0;

						// Berrechnen der Dauer zwischen der Abfahrt und der
						// Ankunft

						String depTime = this.dienstplan.getDutyelement()
								.get(i).getDepTime();
						String arrtime = this.dienstplan.getDutyelement()
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

						switch (this.dienstplan.getDutyelement().get(i)
								.getElementType()) {

						case 1:
							// Servicefahrt
							gc.setFill(Color.CORNFLOWERBLUE);
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

						this.gc.fillRoundRect(startPixelX, startPixelY,
								fahrtDauer, breite / 2, 20, 10);
						this.gc.strokeRoundRect(startPixelX, startPixelY,
								fahrtDauer, breite / 2, 20, 10);

						// Beschriftet die Elemente
						if (beschriftungCheck) {
							if (fahrtDauer > 30) {
								this.gc.setFill(Color.BLACK);
								this.gc.setFont(new Font("Arial", 12));
								this.gc.fillText(
										String.valueOf(dienstplan
												.getDutyelement().get(i)
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

	public MainApplication getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApplication mainApp) {
		this.mainApp = mainApp;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public Dienstplan getDienstplan() {
		return dienstplan;
	}

	public void setDienstplan(Dienstplan dienstplan) {
		this.dienstplan = dienstplan;

		// Auslesen der Startzeit

		ArrayList<String> startzeiten = new ArrayList<String>();
		ArrayList<String> endzeiten = new ArrayList<String>();

		for (int i = 0; i < this.dienstplan.getDuty().size(); i++) {
			boolean anfang = true;

			for (int j = 0; j < this.dienstplan.getDutyelement().size(); j++) {

				if (this.dienstplan
						.getDuty()
						.get(i)
						.getId()
						.equals(this.dienstplan.getDutyelement().get(j)
								.getDutyID())
						&& anfang == true) {
					startzeiten.add(this.dienstplan.getDutyelement().get(j)
							.getDepTime());
					anfang = false;
					if (1 < startzeiten.size()) {
						endzeiten.add(this.dienstplan.getDutyelement()
								.get(j - 1).getArrTime());
					}
				}

			}
			anfang = true;
		}

		endzeiten.add(this.dienstplan.getDutyelement()
				.get(this.dienstplan.getDutyelement().size() - 1).getArrTime());

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

}
