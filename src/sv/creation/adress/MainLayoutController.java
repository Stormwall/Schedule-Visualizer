package sv.creation.adress;

import sv.creation.adress.database.DBConnection;
import sv.creation.adress.database.DBMatching;
import sv.creation.adress.model.Blockelement;
import sv.creation.adress.model.Umlaufplan;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private ScrollPane tablePane;
	@FXML
	private AnchorPane xUp1;
	@FXML
	private AnchorPane yUp1;
	@FXML
	private AnchorPane xLow1;
	@FXML
	private AnchorPane yLow1;
	@FXML
	private AnchorPane xUp2;
	@FXML
	private AnchorPane yUp2;
	@FXML
	private AnchorPane xLow2;
	@FXML
	private AnchorPane yLow2;
	@FXML
	private AnchorPane xUp3;
	@FXML
	private AnchorPane yUp3;
	@FXML
	private AnchorPane xLow3;
	@FXML
	private AnchorPane yLow3;
	@FXML
	private AnchorPane xUp4;
	@FXML
	private AnchorPane yUp4;
	@FXML
	private AnchorPane xLow4;
	@FXML
	private AnchorPane yLow4;
	@FXML
	private AnchorPane xUp5;
	@FXML
	private AnchorPane yUp5;
	@FXML
	private AnchorPane xLow5;
	@FXML
	private AnchorPane yLow5;
	@FXML
	private AnchorPane xUp6;
	@FXML
	private AnchorPane yUp6;
	@FXML
	private AnchorPane xLow6;
	@FXML
	private AnchorPane yLow6;
	@FXML
	private AnchorPane xUp7;
	@FXML
	private AnchorPane yUp7;
	@FXML
	private AnchorPane xLow7;
	@FXML
	private AnchorPane yLow7;
	@FXML
	private ScrollPane upperGraphicPane1;
	@FXML
	private ScrollPane lowerGraphicPane1;
	@FXML
	private ScrollPane upperGraphicPane2;
	@FXML
	private ScrollPane lowerGraphicPane2;
	@FXML
	private ScrollPane upperGraphicPane3;
	@FXML
	private ScrollPane lowerGraphicPane3;
	@FXML
	private ScrollPane upperGraphicPane4;
	@FXML
	private ScrollPane lowerGraphicPane4;
	@FXML
	private ScrollPane upperGraphicPane5;
	@FXML
	private ScrollPane lowerGraphicPane5;
	@FXML
	private ScrollPane upperGraphicPane6;
	@FXML
	private ScrollPane lowerGraphicPane6;
	@FXML
	private ScrollPane upperGraphicPane7;
	@FXML
	private ScrollPane lowerGraphicPane7;	
	@FXML
	private GridPane graphicPane;
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
	
	// Erstellung der Detailtableviews
	
	private TableView<Blockelement> detailsUmlaufTable = new TableView<Blockelement>();
	
	
	// Zugriff auf die unterschiedlichen TabPanes (Gridpane)
	@FXML
	private ChoiceBox<String> UPlan;
	@FXML
	private ChoiceBox<String> DPlan;	
	@FXML
	private Label UPlan1;
	@FXML
	private Label UPlan2;
	@FXML
	private Label UPlan3;
	@FXML
	private Label UPlan4;
	@FXML
	private Label UPlan5;
	@FXML
	private Label UPlan6;
	@FXML
	private Label UPlan7;
	@FXML
	private Label UPlanValue1;
	@FXML
	private Label UPlanValue2;
	@FXML
	private Label UPlanValue3;
	@FXML
	private Label UPlanValue4;
	@FXML
	private Label UPlanValue5;
	@FXML
	private Label UPlanValue6;
	@FXML
	private Label UPlanValue7;
	@FXML
	private Label DPlan1;
	@FXML
	private Label DPlan2;
	@FXML
	private Label DPlan3;
	@FXML
	private Label DPlan4;
	@FXML
	private Label DPlan5;
	@FXML
	private Label DPlan6;
	@FXML
	private Label DPlan7;
	@FXML
	private Label DPlanValue1;
	@FXML
	private Label DPlanValue2;
	@FXML
	private Label DPlanValue3;
	@FXML
	private Label DPlanValue4;
	@FXML
	private Label DPlanValue5;
	@FXML
	private Label DPlanValue6;
	@FXML
	private Label DPlanValue7;
	
	// Zugriff auf die unterschiedlichen Tabs
	@FXML
	private TabPane Planpane;
	@FXML
	private Tab Plan1;
	@FXML
	private Tab Plan2;
	@FXML
	private Tab Plan3;
	@FXML
	private Tab Plan4;
	@FXML
	private Tab Plan5;
	@FXML
	private Tab Plan6;
	@FXML
	private Tab Plan7;
	
	// Bau der Zugriffslisten
	
	private ObservableList<Umlaufplan> umlaufplanliste = FXCollections.observableArrayList();

	
	
	// Canvas Elemente
	
	private Canvas upperChart1;
	private Canvas lowerChart1;
	private Canvas upperChart2;
	private Canvas lowerChart2;
	private Canvas upperChart3;
	private Canvas lowerChart3;
	private Canvas upperChart4;
	private Canvas lowerChart4;
	private Canvas upperChart5;
	private Canvas lowerChart5;
	private Canvas upperChart6;
	private Canvas lowerChart6;
	private Canvas upperChart7;
	private Canvas lowerChart7;
	private Canvas upperXChart;
	private Canvas upperYChart;
	private Canvas lowerXChart;
	private Canvas lowerYChart;
	private GraphicsContext uppergc1;
	private GraphicsContext lowergc1;
	private GraphicsContext uppergc2;
	private GraphicsContext lowergc2;
	private GraphicsContext uppergc3;
	private GraphicsContext lowergc3;
	private GraphicsContext uppergc4;
	private GraphicsContext lowergc4;
	private GraphicsContext uppergc5;
	private GraphicsContext lowergc5;
	private GraphicsContext uppergc6;
	private GraphicsContext lowergc6;
	private GraphicsContext uppergc7;
	private GraphicsContext lowergc7;
	private GraphicsContext upperXgc1;
	private GraphicsContext upperYgc1;
	private GraphicsContext lowerXgc1;
	private GraphicsContext lowerYgc1;
	private GraphicsContext upperXgc2;
	private GraphicsContext upperYgc2;
	private GraphicsContext lowerXgc2;
	private GraphicsContext lowerYgc2;
	private GraphicsContext upperXgc3;
	private GraphicsContext upperYgc3;
	private GraphicsContext lowerXgc3;
	private GraphicsContext lowerYgc3;
	private GraphicsContext upperXgc4;
	private GraphicsContext upperYgc4;
	private GraphicsContext lowerXgc4;
	private GraphicsContext lowerYgc4;
	private GraphicsContext upperXgc5;
	private GraphicsContext upperYgc5;
	private GraphicsContext lowerXgc5;
	private GraphicsContext lowerYgc5;
	private GraphicsContext upperXgc6;
	private GraphicsContext upperYgc6;
	private GraphicsContext lowerXgc6;
	private GraphicsContext lowerYgc6;
	private GraphicsContext upperXgc7;
	private GraphicsContext upperYgc7;
	private GraphicsContext lowerXgc7;
	private GraphicsContext lowerYgc7;
	
	// Attribute der statistischen Darstellung
	
	private int startzeitVar = 0;
	private int endzeitVar = 24;
	private double upperheight = 600;
	private double lowerheight = 800;
	
	//Pruefvariable
	
	private boolean grafikErstellt = false;
	private boolean uppergrafikErstellt = false;
	private boolean uDetailsTableErstellt = false;
	private boolean lowergrafikErstellt = false;
	private boolean hilfslinienAktiv = false;
	private int umlaufChoice = 0;
	
	//Referenz zur MainApp
	
	private MainApplication mainApp;
	
	
	/**
	  * Initializes the controller class. This method is automatically called
	  * after the fxml file has been loaded.
	  */
	  
	@SuppressWarnings("unchecked")
	@FXML
	  private void initialize() {
		
		//Erstellung der Datenbank
//		DBConnection dbc =new DBConnection();
//		dbc.initDBConnection();
//		dbc.createTables();
//		dbc.fillFahrplanIntoTables();
//		dbc.fillUmlaufplanIntoTables();
//		dbc.fillDienstplanIntoTable();
//		dbc.fillDiensttypenIntoTables();
		
		DBMatching dbm=new DBMatching();
		dbm.createUmlaufplanObject();
//		dbm.createDienstplanObject();
		
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
		
		// Sets the Standardelement condition of the Interface
		
		detailsUmlaufTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tablePane.setHbarPolicy(ScrollBarPolicy.NEVER);
			
		
		//Umlaufpläne -- Choicebox wird gefüllt
		
		this.umlaufplanliste.add(dbm.getUmlaufplan());
		this.umlaufplanliste.get(0).setName(" Umlaufplan 1 ");
		this.UPlan.setItems(FXCollections.observableArrayList(umlaufplanliste.get(0).getName()));
		for(int i = 1; i < umlaufplanliste.size(); i++){
			this.UPlan.getItems().add(umlaufplanliste.get(i).getName());
		}		
		
		//Dienstpläme
		
		this.DPlan.setItems(FXCollections.observableArrayList("Dienstplan  1a", "Dienstplan 1b", "Dienstplan 1c"));
		
		// Listen for Resizechanges (Graphic)
		this.upperGraphicPane1.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		     	refreshBothGraphics();
		    }
		});
		this.upperGraphicPane1.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	refreshBothGraphics();
		    }
		});	
		this.lowerGraphicPane1.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	if(lowergrafikErstellt == true){
		    	refreshBothGraphics();
		    	}
		    }
		});
		this.lowerGraphicPane1.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	if(lowergrafikErstellt == true){
		    	refreshBothGraphics();
		    	}
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
						if(uppergrafikErstellt == true){
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
			
			if(uppergrafikErstellt == true){
				this.uppergc1.clearRect(0, 0, this.upperChart1.getWidth(), this.upperChart1.getHeight());
				this.upperXgc1.clearRect(0, 0, this.upperXChart.getWidth(), this.upperXChart.getHeight());
				createUpperGraphic();
		       	}
			if(lowergrafikErstellt == true){
				this.lowergc1.clearRect(0, 0, this.lowerChart1.getWidth(), this.lowerChart1.getHeight());
				this.lowerXgc1.clearRect(0, 0, this.lowerXChart.getWidth(), this.lowerXChart.getHeight());
				createLowerGraphic();
				createLowerXScale();
			}
		
			if(this.hilfslinienAktiv == true){
				createHelpLines();			
			}
	}
	
	/**
	 * Creates The Upper Background Graphic.
	 */
	@FXML
	private void createUpperBackgroundGraphic() {
		
		//Initialize the Chart
		this.upperChart1 = new Canvas(this.upperGraphicPane1.getWidth()-4,this.upperheight);
				
		//Erstellen des HintergrundgrafikKontextes
		this.uppergc1 = this.upperChart1.getGraphicsContext2D();
		this.uppergc1.clearRect(0, 0, this.upperChart1.getWidth(), this.upperChart1.getHeight());
		
		//Erstellen des Hintergrundes
		this.uppergc1.setFill(Color.BEIGE);				
		this.uppergc1.fillRect(0, 0,this.upperChart1.getWidth(),this.upperChart1.getHeight());
		
		this.uppergc1.setLineWidth(3);
		this.uppergc1.setStroke(Color.BLACK);
		this.uppergc1.strokeLine(1, 0, 1, this.upperChart1.getHeight());		
		
		double abstandNetz = (this.upperChart1.getWidth()-30)/(this.endzeitVar-this.startzeitVar);				
		this.uppergc1.setLineWidth(1);
		this.uppergc1.setFont(Font.getDefault());
		this.uppergc1.setFill(Color.BLACK);
		this.uppergc1.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for(int i=0; i<=(endzeitVar-startzeitVar) ;i++) {
			
			double pixel=((i)*abstandNetz);
			this.uppergc1.strokeLine(pixel, 0, pixel, this.upperChart1.getHeight());
	    }
		
		this.upperGraphicPane1.setContent(this.upperChart1);
		// Best�tigung das ein Feld erzeugt wurde
		this.grafikErstellt = true;
	}	
	
	/**
	 * Creates The Upper Graphic.
	 */
	@FXML
	private void createUpperGraphic() {
		
		this.lowerDetailsPane.setMaxHeight(lowerDetailsPane.getHeight());
		this.lowerDetailsPane.setMinHeight(lowerDetailsPane.getHeight());
		
		if(this.uppergrafikErstellt == true){
			this.uppergc1.clearRect(0, 0, this.upperChart1.getWidth(), this.upperChart1.getHeight());
			this.upperXgc1.clearRect(0, 0, this.upperXChart.getWidth(), this.upperXChart.getHeight());
		}
				
		if(UPlan.getSelectionModel().getSelectedItem()!= null){
			
						
		// Labelbeschriftungen für Umlaufpläne
		this.UPlan1.setVisible(true);
		this.UPlanValue1.setText(UPlan.getSelectionModel().getSelectedItem().toString());
		this.UPlanValue1.setVisible(true);
		this.UPlanValue1.setStyle("-fx-background-color:#F0E68C; -fx-font-weight: bold;");
		this.uppergrafikErstellt = true;
		
		// Hintergrunderstellung
		createUpperBackgroundGraphic();
		createUpperXScale();
		graphicTransition();		
		
		// Erstellung/ Befüllen des Details Table-- Clearen des Alten
		if(uDetailsTableErstellt == true){
			this.detailsUmlaufTable.getColumns().clear();
			this.uDetailsTableErstellt = false;
		}
		createUDetailsTable();
		
		
		if(this.hilfslinienAktiv == true){
			createHelpLines();
			}
		}
	}
			
	
	/**
	 * Creates The Upper X - Scale.
	 */
	@FXML
	private void createUpperXScale() {
		
		// Hier wird das Skala Canvas erzeugt
				this.upperXChart = new Canvas(this.upperGraphicPane1.getWidth(),this.xUp1.getHeight());
				// Hier der Graphic Context dazu erzeugt
				this.upperXgc1 = this.upperXChart.getGraphicsContext2D();
				this.upperXgc1.clearRect(0, 0, this.upperXChart.getWidth(), this.upperXChart.getHeight());
				
				double abstandNetz = (this.upperXChart.getWidth()-30)/(this.endzeitVar-this.startzeitVar);				
				this.upperXgc1.setLineWidth(1);
				this.upperXgc1.setFont(Font.getDefault());
				this.upperXgc1.setFill(Color.BLACK);
				this.upperXgc1.setStroke(Color.BLACK);
				
				// Variable zum Darstellen verschiedener Zeitpunkte
						int chartCounter = startzeitVar ;
						for(int i=0; i<=(endzeitVar-startzeitVar) ;i++) {
							
							if(i == 0){
								double pixel=((i)*abstandNetz);
								this.upperXgc1.fillText(String.valueOf(chartCounter), pixel+1, 15);
								chartCounter = chartCounter + 1;	
							}
							if(i!=0){
							double pixel=((i)*abstandNetz);
							this.upperXgc1.fillText(String.valueOf(chartCounter), pixel-4, 15);
							if(chartCounter<23){
							chartCounter = chartCounter + 1;
							}else{
								chartCounter = 0;
								}
							}
					    }
						
				this.xUp1.getChildren().add(upperXChart);
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
		this.lowerChart1 = new Canvas(this.lowerGraphicPane1.getWidth()-4,this.lowerheight);
			
		//Erstellen des HintergrundgrafikKontextes
		this.lowergc1 = this.lowerChart1.getGraphicsContext2D();
		this.lowergc1.clearRect(0, 0, this.lowerChart1.getWidth(), this.lowerChart1.getHeight());
		
		//Erstellen des Hintergrundes
		this.lowergc1.setFill(Color.BISQUE);				
		this.lowergc1.fillRect(0, 0,this.lowerChart1.getWidth(),this.lowerChart1.getHeight());
		
		this.lowergc1.setLineWidth(3);
		this.lowergc1.setStroke(Color.BLACK);
		this.lowergc1.strokeLine(1, 0, 1, this.lowerChart1.getHeight());
		this.lowergc1.strokeLine(0, this.lowerChart1.getHeight()-1, this.lowerChart1.getWidth(),this.lowerChart1.getHeight()-1);
		
		double abstandNetz = (this.lowerChart1.getWidth()-30)/(this.endzeitVar-this.startzeitVar);				
		this.lowergc1.setLineWidth(1);
		this.lowergc1.setFont(Font.getDefault());
		this.lowergc1.setFill(Color.BLACK);
		this.lowergc1.setStroke(Color.BLACK);	
		// Variable zum Darstellen verschiedener Zeitpunkte
		for(int i=0; i<=(this.endzeitVar-this.startzeitVar) ;i++) {
						
			double pixel=((i)*abstandNetz);
			this.lowergc1.strokeLine(pixel, 0, pixel, this.lowerChart1.getHeight()-1);
		}
		
		this.lowerGraphicPane1.setContent(this.lowerChart1);
		}
	
	/**
	 * Creates DetailsTable.
	 */
	@FXML
	private void createUDetailsTable() {
		
				this.detailsUmlaufTable.setEditable(true);
				
				TableColumn<Blockelement, String> blockEleCol = new TableColumn<Blockelement, String>("Block-Ele.");
				TableColumn<Blockelement, String> startzeitCol = new TableColumn<Blockelement, String>("Startzeit");
				TableColumn<Blockelement, String> endzeitCol = new TableColumn<Blockelement, String>("Endzeit");
				TableColumn<Blockelement, String> eleTypeCol = new TableColumn<Blockelement, String>("Ele.-Type");
				TableColumn<Blockelement, String> blockCol = new TableColumn<Blockelement, String>("Block");
				TableColumn<Blockelement, String> dauerCol = new TableColumn<Blockelement, String>("Dauer");		
				
				blockEleCol.setCellValueFactory(new PropertyValueFactory<Blockelement, String>("id"));
				startzeitCol.setCellValueFactory(new PropertyValueFactory<Blockelement, String>("depTime"));
				endzeitCol.setCellValueFactory(new PropertyValueFactory<Blockelement, String>("arrTime"));
				eleTypeCol.setCellValueFactory(new PropertyValueFactory<Blockelement, String>("elementType"));
				blockCol.setCellValueFactory(new PropertyValueFactory<Blockelement, String>("blockID"));
				dauerCol.setCellValueFactory(new PropertyValueFactory<Blockelement, String>("firstName"));
				
				blockEleCol.prefWidthProperty().bind(blockEleCol.widthProperty());
				startzeitCol.prefWidthProperty().bind(startzeitCol.widthProperty());
				endzeitCol.prefWidthProperty().bind(endzeitCol.widthProperty());
				eleTypeCol.prefWidthProperty().bind(eleTypeCol.widthProperty());
				blockCol.prefWidthProperty().bind(blockCol.widthProperty());
				dauerCol.prefWidthProperty().bind(dauerCol.widthProperty());
				
				// Hereinladen der Daten
				
				ObservableList<Blockelement> data = FXCollections.observableArrayList();
				
				for ( int i = 0; i < this.umlaufplanliste.get(this.umlaufChoice).getFahrtZuUmlauf().size(); i++ ){					
					data.add(this.umlaufplanliste.get(this.umlaufChoice).getFahrtZuUmlauf().get(i));
				};
				
				this.detailsUmlaufTable.setItems(data);
				this.detailsUmlaufTable.getColumns().addAll(blockEleCol, startzeitCol, endzeitCol, eleTypeCol, blockCol, dauerCol);		
				this.tablePane.setContent(detailsUmlaufTable);
				
				uDetailsTableErstellt = true;
	}
	
	/**
	 * Creates The Lower X - Scale.
	 */
	@FXML
	private void createLowerXScale() {
		
		// Hier wird das Skala Canvas erzeugt
		this.lowerXChart = new Canvas(this.lowerGraphicPane1.getWidth(),this.xLow1.getHeight());
		// Hier der Graphic Context dazu erzeugt
		this.lowerXgc1 = this.lowerXChart.getGraphicsContext2D();
		this.lowerXgc1.clearRect(0, 0, this.lowerXChart.getWidth(), this.lowerXChart.getHeight());
		
		double abstandNetz = (this.lowerXChart.getWidth()-30)/(this.endzeitVar-this.startzeitVar);				
		this.lowerXgc1.setLineWidth(1);
		this.lowerXgc1.setFont(Font.getDefault());
		this.lowerXgc1.setFill(Color.BLACK);
		this.lowerXgc1.setStroke(Color.BLACK);
		
		// Variable zum Darstellen verschiedener Zeitpunkte
				int chartCounter = startzeitVar ;
				for(int i=0; i<=(endzeitVar-startzeitVar) ;i++) {
					
					if(i == 0){
						double pixel=((i)*abstandNetz);
						this.lowerXgc1.fillText(String.valueOf(chartCounter), pixel+1, 15);
						chartCounter = chartCounter + 1;	
					}
					if(i!=0){
						
					double pixel=((i)*abstandNetz);
					this.lowerXgc1.fillText(String.valueOf(chartCounter), pixel-4, 15);
					if(chartCounter<23){
					chartCounter = chartCounter + 1;
						}else{
							chartCounter = 0;
						}
					}
			    }
				
		this.xLow1.getChildren().add(lowerXChart);
		
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
		
		//double abstandNetz = (this.upperGraphicPane1.getWidth()-30)/(this.endzeitVar-this.startzeitVar);
		double abstandNetz = (this.upperChart1.getWidth()-30)/(this.endzeitVar-this.startzeitVar);	
		// Methoden zu Erstellung der dynamischen Hilfslinien
			
		if(lowergrafikErstellt == true){
			
			this.lowergc1.setLineWidth(1);
			this.lowergc1.setStroke(Color.LIGHTGREY);
			
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {			
				
			double pixel=((i)*abstandNetz)+abstandNetz/4;
			this.lowergc1.strokeLine(pixel, 0, pixel, this.lowerChart1.getHeight()-3);
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
				
			double pixel=((i)*abstandNetz)+abstandNetz/2;
			this.lowergc1.strokeLine(pixel, 0, pixel, this.lowerChart1.getHeight()-3);
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
				
			double pixel=((i)*abstandNetz)+abstandNetz/4*3;
			this.lowergc1.strokeLine(pixel, 0, pixel, this.lowerChart1.getHeight()-3);
			
			}
		}
		if(uppergrafikErstellt == true){	
			
			
			
			this.uppergc1.setLineWidth(1);
			this.uppergc1.setStroke(Color.LIGHTGREY);
			
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/4;
			this.uppergc1.strokeLine(pixel, 0, pixel, this.upperChart1.getHeight());			
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/2;
			this.uppergc1.strokeLine(pixel, 0, pixel, this.upperChart1.getHeight());
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/4*3;
			this.uppergc1.strokeLine(pixel, 0, pixel, this.upperChart1.getHeight());
			
			}
		}
					
	}
	
	/**
	 * Creates Filter Transitions.
	 */
	
	public void graphicTransition(){
		
		//Fades in Filter Panel
				FadeTransition fa = new FadeTransition(Duration.millis(500), this.filterPanel);
				fa.setFromValue(0.0);
				fa.setToValue(1.0);
				fa.setAutoReverse(true);
				fa.play();
				
				FadeTransition faa = new FadeTransition(Duration.millis(500), this.showFullscreen);
				faa.setFromValue(0.0);
				faa.setToValue(1.0);
				faa.setAutoReverse(true);
				faa.play();
		
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
