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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
	private AnchorPane xUp;
	@FXML
	private AnchorPane yUp;
	@FXML
	private AnchorPane xLow;
	@FXML
	private AnchorPane yLow;
	@FXML
	private ScrollPane upperGraphicPane;
	@FXML
	private ScrollPane lowerGraphicPane;
	@FXML
	private GridPane graphicPane;
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
	private Canvas upperXChart;
	private Canvas upperYChart;
	private Canvas lowerXChart;
	private Canvas lowerYChart;
	private GraphicsContext uppergc;
	private GraphicsContext lowergc;
	private GraphicsContext upperXgc;
	private GraphicsContext upperYgc;
	private GraphicsContext lowerXgc;
	private GraphicsContext lowerYgc;
	
	// Attribute der statistischen Darstellung
	
	private int startzeitVar = 0;
	private int endzeitVar = 24;
	private double upperheight = 600;
	private double lowerheight = 800;
	
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
		dbc.fillUmlaufplanIntoTables();
		dbc.fillDienstplanIntoTable();
		dbc.fillDiensttypenIntoTables();
		dbc.fillFahrplanIntoTables();
		//dbc.closeConnection();
		
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
		this.xLow.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	refreshBothGraphics();		    	
		    }
		});
		
		// Listen for selection changes (StartSlider)
		
		this.startzeitSlider.valueProperty().addListener(new ChangeListener<Number>(){
				public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
						// Handhabung wenn die Checkbox angew�hlt wird
						startzeitVar = new_val.intValue();	
						if(startzeitVar > endzeitVar){
							endzeitVar = endzeitVar+24;
							}
						}			
					}				
				);
		
		// Listen for selection changes (EndSlider)
		
		this.endzeitSlider.valueProperty().addListener(new ChangeListener<Number>(){
				public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
					// Handhabung wenn die Checkbox angew�hlt wird
					endzeitVar = new_val.intValue();
					if(startzeitVar > endzeitVar){
						endzeitVar = new_val.intValue()+24;
						}
					if(startzeitVar < new_val.intValue()){
						endzeitVar = new_val.intValue();
						}
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
			this.lowerXgc.clearRect(0, 0, this.lowerXChart.getWidth(), this.lowerXChart.getHeight());
			this.upperXgc.clearRect(0, 0, this.upperXChart.getWidth(), this.upperXChart.getHeight());
	
			createUpperGraphic();
			createLowerGraphic();
			createLowerXScale();
			createUpperXScale();
		
			if(this.hilfslinienAktiv == true){
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
		createLowerXScale();
		createUpperXScale();
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
		this.upperChart = new Canvas(this.upperGraphicPane.getWidth()-4,this.upperheight);
				
		//Erstellen des HintergrundgrafikKontextes
		this.uppergc = this.upperChart.getGraphicsContext2D();
		this.uppergc.clearRect(0, 0, this.upperChart.getWidth(), this.upperChart.getHeight());
		
		//Erstellen des Hintergrundes
		this.uppergc.setFill(Color.BEIGE);				
		this.uppergc.fillRect(0, 0,this.upperChart.getWidth(),this.upperChart.getHeight());
		
		this.uppergc.setLineWidth(3);
		this.uppergc.setStroke(Color.BLACK);
		this.uppergc.strokeLine(1, 0, 1, this.upperChart.getHeight());		
		
		double abstandNetz = (this.upperChart.getWidth()-30)/(this.endzeitVar-this.startzeitVar);				
		this.uppergc.setLineWidth(1);
		this.uppergc.setFont(Font.getDefault());
		this.uppergc.setFill(Color.BLACK);
		this.uppergc.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for(int i=0; i<=(endzeitVar-startzeitVar) ;i++) {
			
			double pixel=((i)*abstandNetz);
			this.uppergc.strokeLine(pixel, 0, pixel, this.upperChart.getHeight());
	    }
		
		this.upperGraphicPane.setContent(this.upperChart);
		// Best�tigung das ein Feld erzeugt wurde
		this.grafikErstellt = true;
	}
	/**
	 * Creates The Upper X - Scale.
	 */
	@FXML
	private void createUpperXScale() {
		
		// Hier wird das Skala Canvas erzeugt
				this.upperXChart = new Canvas(this.xUp.getWidth(),this.xUp.getHeight());
				// Hier der Graphic Context dazu erzeugt
				this.upperXgc = this.upperXChart.getGraphicsContext2D();
				this.upperXgc.clearRect(0, 0, this.upperXChart.getWidth(), this.upperXChart.getHeight());
				
				double abstandNetz = (this.upperXChart.getWidth()-30)/(this.endzeitVar-this.startzeitVar);				
				this.upperXgc.setLineWidth(1);
				this.upperXgc.setFont(Font.getDefault());
				this.upperXgc.setFill(Color.BLACK);
				this.upperXgc.setStroke(Color.BLACK);
				
				// Variable zum Darstellen verschiedener Zeitpunkte
						int chartCounter = startzeitVar ;
						for(int i=0; i<=(endzeitVar-startzeitVar) ;i++) {
							
							if(i == 0){
								double pixel=((i)*abstandNetz);
								this.upperXgc.fillText(String.valueOf(chartCounter), pixel+1, 15);
								chartCounter = chartCounter + 1;	
							}
							if(i!=0){
							double pixel=((i)*abstandNetz);
							this.upperXgc.fillText(String.valueOf(chartCounter), pixel-4, 15);
							if(chartCounter<23){
							chartCounter = chartCounter + 1;
							}else{
								chartCounter = 0;
								}
							}
					    }
						
				this.xUp.getChildren().add(upperXChart);
	}
	
	/**
	 * Creates The Lower Y - Scale.
	 */
	@FXML
	private void createUpperYScale() {
		
		
	}
	/**
	 * Creates The Lower Graphic.
	 */
	@FXML
	private void createLowerGraphic() {
		
		//Initialize the Chart
		this.lowerChart = new Canvas(this.lowerGraphicPane.getWidth()-4,this.lowerheight);
			
		//Erstellen des HintergrundgrafikKontextes
		this.lowergc = this.lowerChart.getGraphicsContext2D();
		this.lowergc.clearRect(0, 0, this.lowerChart.getWidth(), this.lowerChart.getHeight());
		
		//Erstellen des Hintergrundes
		this.lowergc.setFill(Color.BISQUE);				
		this.lowergc.fillRect(0, 0,this.lowerChart.getWidth(),this.lowerChart.getHeight());
		
		this.lowergc.setLineWidth(3);
		this.lowergc.setStroke(Color.BLACK);
		this.lowergc.strokeLine(1, 0, 1, this.lowerChart.getHeight());
		this.lowergc.strokeLine(0, this.lowerChart.getHeight()-1, this.lowerChart.getWidth(),this.lowerChart.getHeight()-1);
		
		double abstandNetz = (this.lowerChart.getWidth()-30)/(this.endzeitVar-this.startzeitVar);				
		this.lowergc.setLineWidth(1);
		this.lowergc.setFont(Font.getDefault());
		this.lowergc.setFill(Color.BLACK);
		this.lowergc.setStroke(Color.BLACK);	
		// Variable zum Darstellen verschiedener Zeitpunkte
		for(int i=0; i<=(this.endzeitVar-this.startzeitVar) ;i++) {
						
			double pixel=((i)*abstandNetz);
			this.lowergc.strokeLine(pixel, 0, pixel, this.lowerChart.getHeight()-1);
		}
		
		this.lowerGraphicPane.setContent(this.lowerChart);
		}
	/**
	 * Creates The Lower X - Scale.
	 */
	@FXML
	private void createLowerXScale() {
		
		// Hier wird das Skala Canvas erzeugt
		this.lowerXChart = new Canvas(this.xLow.getWidth(),this.xLow.getHeight());
		// Hier der Graphic Context dazu erzeugt
		this.lowerXgc = this.lowerXChart.getGraphicsContext2D();
		this.lowerXgc.clearRect(0, 0, this.lowerXChart.getWidth(), this.lowerXChart.getHeight());
		
		double abstandNetz = (this.lowerXChart.getWidth()-30)/(this.endzeitVar-this.startzeitVar);				
		this.lowerXgc.setLineWidth(1);
		this.lowerXgc.setFont(Font.getDefault());
		this.lowerXgc.setFill(Color.BLACK);
		this.lowerXgc.setStroke(Color.BLACK);
		
		// Variable zum Darstellen verschiedener Zeitpunkte
				int chartCounter = startzeitVar ;
				for(int i=0; i<=(endzeitVar-startzeitVar) ;i++) {
					
					if(i == 0){
						double pixel=((i)*abstandNetz);
						this.lowerXgc.fillText(String.valueOf(chartCounter), pixel+1, 15);
						chartCounter = chartCounter + 1;	
					}
					if(i!=0){
						
					double pixel=((i)*abstandNetz);
					this.lowerXgc.fillText(String.valueOf(chartCounter), pixel-4, 15);
					if(chartCounter<23){
					chartCounter = chartCounter + 1;
						}else{
							chartCounter = 0;
						}
					}
			    }
				
		this.xLow.getChildren().add(lowerXChart);
		
	}
	
	/**
	 * Creates The Lower Y - Scale.
	 */
	@FXML
	private void createLowerYScale() {
		
		
	}

	/**
	 * Creates The Helplines in the graphic.
	 */
	@FXML
	private void createHelpLines() {
		
		double abstandNetz = (this.lowerChart.getWidth()-30)/(this.endzeitVar-this.startzeitVar);				
		this.lowergc.setLineWidth(1);
		this.lowergc.setStroke(Color.LIGHTGREY);
		this.uppergc.setLineWidth(1);
		this.uppergc.setStroke(Color.LIGHTGREY);
		
		// Methoden zu Erstellung der dynamischen Hilfslinien
			int chartCounter = this.startzeitVar;
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
								
			double pixel=((i)*abstandNetz)+abstandNetz/4;
			this.lowergc.strokeLine(pixel, 0, pixel, this.lowerChart.getHeight()-3);
			chartCounter = chartCounter + 1;
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
				
			double pixel=((i)*abstandNetz)+abstandNetz/2;
			this.lowergc.strokeLine(pixel, 0, pixel, this.lowerChart.getHeight()-3);
			chartCounter = chartCounter + 1;
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
				
			double pixel=((i)*abstandNetz)+abstandNetz/4*3;
			this.lowergc.strokeLine(pixel, 0, pixel, this.lowerChart.getHeight()-3);
			chartCounter = chartCounter + 1;
			}
			
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
				
			double pixel=((i)*abstandNetz)+abstandNetz/4;
			this.uppergc.strokeLine(pixel, 0, pixel, this.lowerChart.getHeight());
			chartCounter = chartCounter + 1;
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/2;
			this.uppergc.strokeLine(pixel, 0, pixel, this.lowerChart.getHeight());
			chartCounter = chartCounter + 1;
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/4*3;
			this.uppergc.strokeLine(pixel, 0, pixel, this.lowerChart.getHeight());
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
