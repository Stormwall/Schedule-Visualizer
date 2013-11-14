package sv.creation.adress;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class MainLayoutController {
	
	// Zugriff auf Strukturelemente
	@FXML
	private AnchorPane upperPane;
	@FXML
	private AnchorPane lowerPane;
	
	// Konstruktion der Canvas Elemente
	
	private Canvas upperChart;
	private Canvas lowerChart;
	private GraphicsContext uppergc;
	private GraphicsContext lowergc;
	
	// Attribute der statistischen Darstellung
	
	private int startzeit;
	private int endzeit;	
	
	//Referenz zur MainApp
	
	private MainApplication mainApp;
	
	/**
	  * Initializes the controller class. This method is automatically called
	  * after the fxml file has been loaded.
	  */
	  
	@FXML
	  private void initialize() {
		
		
	  }
	
	/**
	 * Creates Both Graphics.
	 */
	@FXML
	private void createBothGraphics() {
		
		createUpperGraphic();
		createLowerGraphic();
		
	}
	
	/**
	 * Creates The Upper Graphic.
	 */
	@FXML
	private void createUpperGraphic() {
		
		//Initialize the Chart
		upperChart = new Canvas(upperPane.getWidth(),upperPane.getHeight());
		
		//Erstellen des HintergrundgrafikKontextes
		uppergc = upperChart.getGraphicsContext2D();
		uppergc.clearRect(0, 0, upperChart.getWidth(), upperChart.getHeight());
		
		//Erstellen des Hintergrundes
		uppergc.setFill(Color.BEIGE);				
		uppergc.fillRect(0, 0,upperChart.getWidth(),upperChart.getHeight());
		
		upperPane.getChildren().add(upperChart);
	}
	
	/**
	 * Creates The Lower Graphic.
	 */
	@FXML
	private void createLowerGraphic() {
		
		//Initialize the Chart
		lowerChart = new Canvas(lowerPane.getWidth(),lowerPane.getHeight());
		
		//Erstellen des HintergrundgrafikKontextes
		lowergc = lowerChart.getGraphicsContext2D();
		lowergc.clearRect(0, 0, lowerChart.getWidth(), lowerChart.getHeight());
		
		//Erstellen des Hintergrundes
		lowergc.setFill(Color.LIGHTGREEN);				
		lowergc.fillRect(0, 0,lowerChart.getWidth(),lowerChart.getHeight());
		
		lowerPane.getChildren().add(lowerChart);
	}

}
