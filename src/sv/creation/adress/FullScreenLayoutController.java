package sv.creation.adress;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FullScreenLayoutController {
	
	private Stage dialogStage;
	private MainLayoutController Controller;	
	

	//Strukturvariablen
	@FXML
	private AnchorPane upperGraphicPane;
	@FXML
	private AnchorPane lowerGraphicPane;
	
	// Konstruktion der Canvas Elemente
	
		private Canvas upperChart;
		private Canvas lowerChart;
		private GraphicsContext uppergc;
		private GraphicsContext lowergc;
	
	// Attribute der statistischen Darstellung
	
		private int startzeitVar = 0;
		private int endzeitVar = 24;	
		
		//Pruefvariable
		
		private boolean hilfslinienAktiv = false;
		
		
	
	/**
	  * Initializes the controller class. This method is automatically called
	  * after the fxml file has been loaded.
	  */
		  
	@FXML
	private void initialize() {
		
		// An dieser Stelle wird der Bildschirm des Nutzers ausgelesen und die Werte belegt
		
		upperGraphicPane.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		     	
		    }
		});
		
		upperGraphicPane.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	
		    }
		});	
		lowerGraphicPane.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		     	
		    }
		});
		
		lowerGraphicPane.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	
		    	//Vergleich zum Stand der Filter im MainLayout
				hilfslinienAktiv = Controller.isHilfslinienAktiv();
				startzeitVar = Controller.getStartzeitVar();
				endzeitVar = Controller.getEndzeitVar();
				// Creates the Graphic
				createUpperGraphic();
				createLowerGraphic();
				if(hilfslinienAktiv == true){
					createHelpLines();
				}
		    	
		    }
		});
		
	}
	
	/**
	 * Creates The Upper Graphic.
	 */
	@FXML
	private void createUpperGraphic() {
		
		//Initialize the Chart
		this.upperChart = new Canvas(this.upperGraphicPane.getWidth(),this.upperGraphicPane.getHeight());
				
		//Erstellen des HintergrundgrafikKontextes
		this.uppergc = this.upperChart.getGraphicsContext2D();
		this.uppergc.clearRect(0, 0, this.upperChart.getWidth(), this.upperChart.getHeight());
		
		//Erstellen des Hintergrundes
		this.uppergc.setFill(Color.BEIGE);				
		this.uppergc.fillRect(0, 0,this.upperChart.getWidth(),this.upperChart.getHeight());
		
		this.uppergc.setLineWidth(3);
		this.uppergc.setStroke(Color.BLACK);
		this.uppergc.strokeLine(40, 30, 40, this.upperChart.getHeight());		
		
		double abstandNetz = (this.upperChart.getWidth()-40-30)/(this.endzeitVar-this.startzeitVar);				
		this.uppergc.setLineWidth(1);
		this.uppergc.setFont(Font.getDefault());
		this.uppergc.setFill(Color.BLACK);
		this.uppergc.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		int chartCounter = startzeitVar ;
		for(int i=0; i<=(endzeitVar-startzeitVar) ;i++) {
			
			double pixel=40+((i)*abstandNetz);
			this.uppergc.strokeLine(pixel, 35, pixel, this.upperChart.getHeight());
			this.uppergc.fillText(String.valueOf(chartCounter), pixel-4, 20);
			chartCounter = chartCounter + 1;
	    }
		
		this.upperGraphicPane.getChildren().add(this.upperChart);
	}
	
	/**
	 * Creates The Lower Graphic.
	 */
	@FXML
	private void createLowerGraphic() {
		
		//Initialize the Chart
		this.lowerChart = new Canvas(this.lowerGraphicPane.getWidth(),this.lowerGraphicPane.getHeight());
			
		//Erstellen des HintergrundgrafikKontextes
		this.lowergc = this.lowerChart.getGraphicsContext2D();
		this.lowergc.clearRect(0, 0, this.lowerChart.getWidth(), this.lowerChart.getHeight());
		
		//Erstellen des Hintergrundes
		this.lowergc.setFill(Color.BISQUE);				
		this.lowergc.fillRect(0, 0,this.lowerChart.getWidth(),this.lowerChart.getHeight());
		
		this.lowergc.setLineWidth(3);
		this.lowergc.setStroke(Color.BLACK);
		this.lowergc.strokeLine(40, 0, 40, this.lowerChart.getHeight()-30);
		this.lowergc.strokeLine(40, this.lowerChart.getHeight()-30, this.lowerChart.getWidth()-30,this.lowerChart.getHeight()-30);
		
		double abstandNetz = (this.lowerChart.getWidth()-40-30)/(this.endzeitVar-this.startzeitVar);				
		this.lowergc.setLineWidth(1);
		this.lowergc.setFont(Font.getDefault());
		this.lowergc.setFill(Color.BLACK);
		this.lowergc.setStroke(Color.BLACK);	
		// Variable zum Darstellen verschiedener Zeitpunkte
		int chartCounter = this.startzeitVar;
		for(int i=0; i<=(this.endzeitVar-this.startzeitVar) ;i++) {
						
			double pixel=40+((i)*abstandNetz);
			this.lowergc.strokeLine(pixel, 0, pixel, this.lowerChart.getHeight()-30);
			this.lowergc.fillText(String.valueOf(chartCounter), pixel-4,this.lowerChart.getHeight()-10);
			chartCounter = chartCounter + 1;
		}
		
		this.lowerGraphicPane.getChildren().add(this.lowerChart);
		}
	
	/**
	 * Creates The Helplines in the graphic.
	 */
	@FXML
	private void createHelpLines() {
		
		double abstandNetz = (this.lowerChart.getWidth()-40-30)/(this.endzeitVar-this.startzeitVar);				
		this.lowergc.setLineWidth(1);
		this.lowergc.setStroke(Color.LIGHTGREY);
		this.uppergc.setLineWidth(1);
		this.uppergc.setStroke(Color.LIGHTGREY);
		
		// Methoden zu Erstellung der dynamischen Hilfslinien
			int chartCounter = this.startzeitVar;
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
								
			double pixel=40+((i)*abstandNetz)+abstandNetz/4;
			this.lowergc.strokeLine(pixel, 0, pixel, this.lowerChart.getHeight()-33);
			chartCounter = chartCounter + 1;
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
				
			double pixel=40+((i)*abstandNetz)+abstandNetz/2;
			this.lowergc.strokeLine(pixel, 0, pixel, this.lowerChart.getHeight()-33);
			chartCounter = chartCounter + 1;
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
				
			double pixel=40+((i)*abstandNetz)+abstandNetz/4*3;
			this.lowergc.strokeLine(pixel, 0, pixel, this.lowerChart.getHeight()-33);
			chartCounter = chartCounter + 1;
			}
			for(int i=0; i<=(endzeitVar-startzeitVar) ;i++) {
				
			double pixel=40+((i)*abstandNetz)+abstandNetz/4;
			this.uppergc.strokeLine(pixel, 35, pixel, this.upperChart.getHeight());			
			chartCounter = chartCounter + 1;
			 }
				
			for(int i=0; i<=(endzeitVar-startzeitVar) ;i++) {
				
			double pixel=40+((i)*abstandNetz)+abstandNetz/2;
			this.uppergc.strokeLine(pixel, 35, pixel, this.upperChart.getHeight());			
			chartCounter = chartCounter + 1;
		    }
			for(int i=0; i<=(endzeitVar-startzeitVar) ;i++) {
				
			double pixel=40+((i)*abstandNetz)+abstandNetz/4*3;
			this.uppergc.strokeLine(pixel, 35, pixel, this.upperChart.getHeight());			
			chartCounter = chartCounter + 1;
		    }		
	}
	
	/**
	 * Creates The Upper Graphic.
	 */
	
	
	// Methode zum Beenden des PopUp
	
	public void endStage(){		
		dialogStage.close();
	}
	
	//Zuordnungsmethoden
	
	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public MainLayoutController getController() {
		return Controller;
	}

	public void setController(MainLayoutController controller) {
		Controller = controller;
	}
	
	

}
