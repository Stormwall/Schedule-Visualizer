package sv.creation.adress;

import sv.creation.adress.database.DBConnection;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class MainLayoutController {
	
	// Zugriff auf Strukturelemente
	@FXML
	private AnchorPane MainPane;
	@FXML
	private AnchorPane leftinnerPane;
	@FXML
	private AnchorPane rightinnerPane;
	@FXML
	private AnchorPane backgroundgraphicPane;
	@FXML
	private AnchorPane lowerDetailsPane;
	@FXML
	private AnchorPane upperGraphicPane;
	@FXML
	private AnchorPane lowerGraphicPane;
	@FXML
	private SplitPane graphicPane;
	@FXML
	private Button createGraphic;
	@FXML
	private Button refreshGraphic;
	@FXML
	private Button showFullscreen;
	@FXML
	private Button applyFilter;
	@FXML
	private Slider startzeitSlider;
	@FXML
	private Slider endzeitSlider;
	@FXML
	private CheckBox hilfslinien;
	@FXML
	private HBox filterPanel;
	
	// Konstruktion der Canvas Elemente
	
	private Canvas upperChart;
	private Canvas lowerChart;
	private GraphicsContext uppergc;
	private GraphicsContext lowergc;
	
	// Attribute der statistischen Darstellung
	
	private int startzeitVar = 0;
	private int endzeitVar = 24;	
	
	//Pruefvariable
	
	private boolean grafikErstellt = false;
	private boolean hilfslinienAktiv = false;
	
	//Referenz zur MainApp
	
	private MainApplication mainApp;
	
	
	/**
	  * Initializes the controller class. This method is automatically called
	  * after the fxml file has been loaded.
	  */
	  
	@FXML
	  private void initialize() {
		
		//Erstellung der Datenbank
		DBConnection dbc =new DBConnection();
		dbc.initDBConnection();
		dbc.createTables();
		//dbc.fillUmlaufplanIntoTables();
		dbc.closeConnection();
		
		//Fades in Filter Panel
		FadeTransition fa = new FadeTransition(Duration.millis(3000), this.leftinnerPane);
		fa.setFromValue(0.0);
		fa.setToValue(1.0);
		fa.setAutoReverse(true);
		fa.play();
		
		FadeTransition ft = new FadeTransition(Duration.millis(5000), this.rightinnerPane);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.setAutoReverse(true);
		ft.play();
		
		// Listen for Resizechanges (Graphic)
		
		this.upperGraphicPane.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	refreshBothGraphics();		    	
		    }
		});
		this.upperGraphicPane.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	refreshBothGraphics();		    	
		    }
		});	
		this.lowerGraphicPane.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	refreshBothGraphics();		    	
		    }
		});
		this.lowerGraphicPane.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	refreshBothGraphics();
		    }
		});
		
		// Listen for selection changes (StartSlider)
		
		this.startzeitSlider.valueProperty().addListener(new ChangeListener<Number>(){
				public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
						// Handhabung wenn die Checkbox angew�hlt wird
						startzeitVar = new_val.intValue();										
						}			
					}				
				);
		
		// Listen for selection changes (EndSlider)
		
		this.endzeitSlider.valueProperty().addListener(new ChangeListener<Number>(){
				public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
						// Handhabung wenn die Checkbox angew�hlt wird
					endzeitVar = new_val.intValue();										
					}			
				}				
		);
				
		// Listen for selection changes (Checkbox... Hilfslinien)
				
		this.hilfslinien.selectedProperty().addListener(new ChangeListener<Boolean>(){
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
					// Handhabung wenn die Checkbox angew�hlt wird
					if(new_val == true){
						if(grafikErstellt == true){
							refreshBothGraphics();
							createHelpLines();
							hilfslinienAktiv = true;
						}else{							
							mainApp.fehlerMeldungGrafikFehlt();	
							hilfslinien.setSelected(false);
						}
					}
					// Handhabung wenn die Checkbox ausgeschaltet wird
					if(new_val == false){
						hilfslinienAktiv = false;
						refreshBothGraphics();
						
					}					
				}			
			}				
		);	
	  }
	
	
	/**
	 * Refreshes Both Graphics.
	 */
	@FXML
	private void refreshBothGraphics() {
		
		//Hier wird das Feld gecleared und gepr�ft ob es schon existiert
		if(this.grafikErstellt== true){
			this.lowergc.clearRect(0, 0, this.lowerChart.getWidth(), this.lowerChart.getHeight());
			this.uppergc.clearRect(0, 0, this.upperChart.getWidth(), this.upperChart.getHeight());
	
			createUpperGraphic();
			createLowerGraphic();
			if(hilfslinienAktiv == true){
				createHelpLines();
			}
		}
	}
	/**
	 * Creates Both Graphics.
	 */
	@FXML
	private void createBothGraphics() {	
		
		this.lowerDetailsPane.setMaxHeight(lowerDetailsPane.getHeight());
		this.lowerDetailsPane.setMinHeight(lowerDetailsPane.getHeight());
		// Creates the Graphic
		createUpperGraphic();
		createLowerGraphic();
		//Fades out Create Graphic Button
		FadeTransition ft = new FadeTransition(Duration.millis(500), this.createGraphic);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.setAutoReverse(true);
		ft.play();
		
		//Fades in Filter Panel
		FadeTransition fa = new FadeTransition(Duration.millis(500), this.filterPanel);
		fa.setFromValue(0.0);
		fa.setToValue(1.0);
		fa.setAutoReverse(true);
		fa.play();
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
		// Best�tigung das ein Feld erzeugt wurde
		this.grafikErstellt = true;
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
	//Methoden zur Weitergabe der Werte an andere Klassen
	
	public boolean isHilfslinienAktiv() {
		return hilfslinienAktiv;
	}


	public void setHilfslinienAktiv(boolean hilfslinienAktiv) {
		this.hilfslinienAktiv = hilfslinienAktiv;
	}
	
	public int getStartzeitVar() {
		return startzeitVar;
	}


	public void setStartzeitVar(int startzeitVar) {
		this.startzeitVar = startzeitVar;
	}


	public int getEndzeitVar() {
		return endzeitVar;
	}


	public void setEndzeitVar(int endzeitVar) {
		this.endzeitVar = endzeitVar;
	}


	public boolean isGrafikErstellt() {
		return grafikErstellt;
	}


	public void setGrafikErstellt(boolean grafikErstellt) {
		this.grafikErstellt = grafikErstellt;
	}

	
	//Methoden zur Festsetzung der Main


	public void openFullscreen() {
		if(isGrafikErstellt()== true){
		mainApp.showFullScreenGraphic(this);
		}else{
			mainApp.fehlerMeldungGrafikFehlt();
		}
	}
	
	//Methoden zur Festsetzung der Main	
	

	public MainApplication getMainApp() {
		return mainApp;
	}


	public void setMainApp(MainApplication mainApp) {
		this.mainApp = mainApp;
	}
	
	

}
