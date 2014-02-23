package sv.creation.adress;

import java.util.ArrayList;

import sv.creation.adress.util.Color;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ColorChoiceLayoutController {

	// Strukturvariablen

	@FXML
	private AnchorPane a1;
	@FXML
	private AnchorPane a2;
	@FXML
	private AnchorPane a3;
	@FXML
	private AnchorPane a4;
	@FXML
	private AnchorPane a5;
	@FXML
	private AnchorPane a6;
	@FXML
	private AnchorPane a7;
	@FXML
	private AnchorPane a8;
	@FXML
	private AnchorPane a9;
	@FXML
	private AnchorPane a10;
	@FXML
	private ColorPicker colorPicker1;
	@FXML
	private ColorPicker colorPicker2;
	@FXML
	private ColorPicker colorPicker3;
	@FXML
	private ColorPicker colorPicker4;
	@FXML
	private ColorPicker colorPicker5;
	@FXML
	private ColorPicker colorPicker6;
	@FXML
	private ColorPicker colorPicker7;
	@FXML
	private ColorPicker colorPicker8;
	@FXML
	private ColorPicker colorPicker9;
	@FXML
	private ColorPicker colorPicker10;

	// Variablen der Stage

	private Stage dialogStage;
	private MainApplication mainApp;

	private ArrayList<String> colors = new ArrayList<String>();

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML
	private void initialize() {

		// Fill Color Array

		 fillColorArray();

		for (int i = 0; i < this.colors.size(); i++) {
			
			System.out.println(this.colors.get(i));
//			javafx.scene.paint.Color c = javafx.scene.paint.Color.valueOf(this.colors.get(i));
			
		}
		

		// Action Listener

		this.colorPicker1.setOnAction(new EventHandler() {
			public void handle(Event t) {
				colors.set(0, colorPicker1.getValue().toString());
				fillColors(0);
			}
		});
		this.colorPicker2.setOnAction(new EventHandler() {
			public void handle(Event t) {
				colors.set(1, colorPicker2.getValue().toString());
				fillColors(1);
			}
		});
		this.colorPicker3.setOnAction(new EventHandler() {
			public void handle(Event t) {
				colors.set(2, colorPicker3.getValue().toString());
				fillColors(2);
			}
		});
		this.colorPicker4.setOnAction(new EventHandler() {
			public void handle(Event t) {
				colors.set(3, colorPicker4.getValue().toString());
				fillColors(3);
			}
		});
		this.colorPicker5.setOnAction(new EventHandler() {
			public void handle(Event t) {
				colors.set(4, colorPicker5.getValue().toString());
				fillColors(4);
			}
		});
		this.colorPicker6.setOnAction(new EventHandler() {
			public void handle(Event t) {
				colors.set(5, colorPicker6.getValue().toString());
				fillColors(5);
			}
		});
		this.colorPicker7.setOnAction(new EventHandler() {
			public void handle(Event t) {
				colors.set(6, colorPicker7.getValue().toString());
				fillColors(6);
			}
		});
		this.colorPicker8.setOnAction(new EventHandler() {
			public void handle(Event t) {
				colors.set(7, colorPicker8.getValue().toString());
				fillColors(7);
			}
		});
		this.colorPicker9.setOnAction(new EventHandler() {
			public void handle(Event t) {
				colors.set(8, colorPicker9.getValue().toString());
				fillColors(8);
			}
		});
		this.colorPicker10.setOnAction(new EventHandler() {
			public void handle(Event t) {
				colors.set(9, colorPicker10.getValue().toString());
				fillColors(9);
			}
		});

	}

	// Change a single Color

	public void fillColors(int i) {

		
		Canvas canvas;
		GraphicsContext gc;

		switch (i) {
		case 0:
			this.a1.getChildren().clear();
			canvas = new Canvas(this.a1.getWidth(), this.a1.getHeight());
			gc = canvas.getGraphicsContext2D();
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

			gc.setFill(javafx.scene.paint.Color.valueOf(this.colors.get(i)));
			gc.fillRoundRect(0, 0, canvas.getWidth(), canvas.getHeight(), 20,
					10);

			this.a1.getChildren().add(canvas);

			break;
		case 1:
			this.a2.getChildren().clear();
			canvas = new Canvas(this.a2.getWidth(), this.a2.getHeight());
			gc = canvas.getGraphicsContext2D();
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

			gc.setFill(javafx.scene.paint.Color.valueOf(this.colors.get(i)));
			gc.fillRoundRect(0, 0, canvas.getWidth(), canvas.getHeight(), 20,
					10);

			this.a2.getChildren().add(canvas);

			break;
		case 2:
			this.a3.getChildren().clear();
			canvas = new Canvas(this.a3.getWidth(), this.a3.getHeight());
			gc = canvas.getGraphicsContext2D();
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

			gc.setFill(javafx.scene.paint.Color.valueOf(this.colors.get(i)));
			gc.fillRoundRect(0, 0, canvas.getWidth(), canvas.getHeight(), 20,
					10);

			this.a3.getChildren().add(canvas);

			break;
		case 3:
			this.a4.getChildren().clear();
			canvas = new Canvas(this.a4.getWidth(), this.a4.getHeight());
			gc = canvas.getGraphicsContext2D();
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

			gc.setFill(javafx.scene.paint.Color.valueOf(this.colors.get(i)));
			gc.fillRoundRect(0, 0, canvas.getWidth(), canvas.getHeight(), 20,
					10);

			this.a4.getChildren().add(canvas);

			break;
		case 4:
			this.a5.getChildren().clear();
			canvas = new Canvas(this.a5.getWidth(), this.a5.getHeight());
			gc = canvas.getGraphicsContext2D();
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

			gc.setFill(javafx.scene.paint.Color.valueOf(this.colors.get(i)));
			gc.fillRoundRect(0, 0, canvas.getWidth(), canvas.getHeight(), 20,
					10);

			this.a5.getChildren().add(canvas);

			break;
		case 5:
			this.a6.getChildren().clear();
			canvas = new Canvas(this.a6.getWidth(), this.a6.getHeight());
			gc = canvas.getGraphicsContext2D();
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

			gc.setFill(javafx.scene.paint.Color.valueOf(this.colors.get(i)));
			gc.fillRoundRect(0, 0, canvas.getWidth(), canvas.getHeight(), 20,
					10);

			this.a6.getChildren().add(canvas);

			break;
		case 6:
			this.a7.getChildren().clear();
			canvas = new Canvas(this.a7.getWidth(), this.a7.getHeight());
			gc = canvas.getGraphicsContext2D();
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

			gc.setFill(javafx.scene.paint.Color.valueOf(this.colors.get(i)));
			gc.fillRoundRect(0, 0, canvas.getWidth(), canvas.getHeight(), 20,
					10);

			this.a7.getChildren().add(canvas);

			break;
		case 7:
			this.a8.getChildren().clear();
			canvas = new Canvas(this.a8.getWidth(), this.a8.getHeight());
			gc = canvas.getGraphicsContext2D();
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

			gc.setFill(javafx.scene.paint.Color.valueOf(this.colors.get(i)));
			gc.fillRoundRect(0, 0, canvas.getWidth(), canvas.getHeight(), 20,
					10);

			this.a8.getChildren().add(canvas);

			break;
		case 8:
			this.a9.getChildren().clear();
			canvas = new Canvas(this.a9.getWidth(), this.a9.getHeight());
			gc = canvas.getGraphicsContext2D();
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

			gc.setFill(javafx.scene.paint.Color.valueOf(this.colors.get(i)));
			gc.fillRoundRect(0, 0, canvas.getWidth(), canvas.getHeight(), 20,
					10);

			this.a9.getChildren().add(canvas);

			break;
		case 9:
			this.a10.getChildren().clear();
			canvas = new Canvas(this.a10.getWidth(), this.a10.getHeight());
			gc = canvas.getGraphicsContext2D();
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

			gc.setFill(javafx.scene.paint.Color.valueOf(this.colors.get(i)));
			gc.fillRoundRect(0, 0, canvas.getWidth(), canvas.getHeight(), 20,
					10);

			this.a10.getChildren().add(canvas);

			break;

		default:
			break;
		}

	}

	// Methode zum Beenden des PopUp

	public void endStage() {

		dialogStage.close();
	}

	// Methode zum Befuellen der Farben

	public void fillColorArray() {

		Color color = new Color();

//		color.changeColor(1, "SEAGREEN");
//		color.changeColor(2, "LIGHTCORAL");
//		color.changeColor(3, "ANTIQUEWHITE");
//		color.changeColor(4, "WHITESMOKE");
//		color.changeColor(5, "GREEN");
//		color.changeColor(6, "GREEN");
//		color.changeColor(7, "GREEN");
//		color.changeColor(8, "ORANGE");
//		color.changeColor(9, "LIGHTSKYBLUE");
//		color.changeColor(10, "GREEN");
		
		this.colors = color.readColorTable();
		
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

}
