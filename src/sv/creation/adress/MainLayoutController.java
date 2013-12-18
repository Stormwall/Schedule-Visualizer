package sv.creation.adress;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sv.creation.adress.database.DBConnection;
import sv.creation.adress.database.DBMatching;
import sv.creation.adress.model.Blockelement;
import sv.creation.adress.model.Umlaufplan;
import sv.creation.adress.util.StringSplitter;
import javafx.animation.FadeTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
import javafx.scene.control.SingleSelectionModel;
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
	private ScrollPane yUp1;
	@FXML
	private AnchorPane xLow1;
	@FXML
	private ScrollPane yLow1;
	@FXML
	private AnchorPane xUp2;
	@FXML
	private ScrollPane yUp2;
	@FXML
	private AnchorPane xLow2;
	@FXML
	private ScrollPane yLow2;
	@FXML
	private AnchorPane xUp3;
	@FXML
	private ScrollPane yUp3;
	@FXML
	private AnchorPane xLow3;
	@FXML
	private ScrollPane yLow3;
	@FXML
	private AnchorPane xUp4;
	@FXML
	private ScrollPane yUp4;
	@FXML
	private AnchorPane xLow4;
	@FXML
	private ScrollPane yLow4;
	@FXML
	private AnchorPane xUp5;
	@FXML
	private ScrollPane yUp5;
	@FXML
	private AnchorPane xLow5;
	@FXML
	private ScrollPane yLow5;
	@FXML
	private AnchorPane xUp6;
	@FXML
	private ScrollPane yUp6;
	@FXML
	private AnchorPane xLow6;
	@FXML
	private ScrollPane yLow6;
	@FXML
	private AnchorPane xUp7;
	@FXML
	private ScrollPane yUp7;
	@FXML
	private AnchorPane xLow7;
	@FXML
	private ScrollPane yLow7;
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
	
	// Zugriff auf die Labels des DetailPane
	
	@FXML
	private Label anzahlFahrten;
	@FXML
	private Label anzahlServiceFahrten;
	@FXML
	private Label anzahlLeerFahrten;
	@FXML
	private Label unknown;
	
	
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

	// Zuordnung der Umlaufpläne
	
	private Umlaufplan umlaufplanEins;
	private Umlaufplan umlaufplanZwei;
	private Umlaufplan umlaufplanDrei;
	private Umlaufplan umlaufplanVier;
	private Umlaufplan umlaufplanFuenf;
	private Umlaufplan umlaufplanSechs;
	private Umlaufplan umlaufplanSieben;
	
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
	private Canvas upperXChart1;
	private Canvas upperYChart1;
	private Canvas lowerXChart1;
	private Canvas lowerYChart1;
	private Canvas upperXChart2;
	private Canvas upperYChart2;
	private Canvas lowerXChart2;
	private Canvas lowerYChart2;
	private Canvas upperXChart3;
	private Canvas upperYChart3;
	private Canvas lowerXChart3;
	private Canvas lowerYChart3;
	private Canvas upperXChart4;
	private Canvas upperYChart4;
	private Canvas lowerXChart4;
	private Canvas lowerYChart4;
	private Canvas upperXChart5;
	private Canvas upperYChart5;
	private Canvas lowerXChart5;
	private Canvas lowerYChart5;
	private Canvas upperXChart6;
	private Canvas upperYChart6;
	private Canvas lowerXChart6;
	private Canvas lowerYChart6;
	private Canvas upperXChart7;
	private Canvas upperYChart7;
	private Canvas lowerXChart7;
	private Canvas lowerYChart7;
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
	private double upperheightEins = 600;
	private double upperheightZwei = 1600;
	private double upperheightDrei = 1600;
	private double upperheightVier = 1600;
	private double upperheightFuenf = 1600;
	private double upperheightSechs = 1600;
	private double upperheightSieben = 1600;
	private double lowerheight = 800;
	
	//Pruefvariable
	
	private boolean firstUppergrafikErstellt = false;
	private boolean secondUppergrafikErstellt = false;
	private boolean thirdUppergrafikErstellt = false;
	private boolean fourthUppergrafikErstellt = false;
	private boolean fifthUppergrafikErstellt = false;
	private boolean sixthUppergrafikErstellt = false;
	private boolean seventhUppergrafikErstellt = false;
	private boolean secondGrafikErstellt = false;
	private boolean thirdGrafikErstellt = false;
	private boolean fourthGrafikErstellt = false;
	private boolean fifthGrafikErstellt = false;
	private boolean sixthGrafikErstellt = false;
	private boolean seventhGrafikErstellt = false;
	private boolean uDetailsTableErstellt = false;
	private boolean lowergrafikErstellt = false;
	private boolean hilfslinienAktiv = false;
	private boolean addButtonPressed = true;
	private int umlaufChoice = 0;
	private int umlaufTabCounter = 0;
	
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
		
		this.yUp1.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.yUp1.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.yUp2.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.yUp2.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.yUp3.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.yUp3.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.yUp4.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.yUp4.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.yUp5.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.yUp5.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.yUp6.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.yUp6.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.yUp7.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.yUp7.setVbarPolicy(ScrollBarPolicy.NEVER);
		
		this.yLow1.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.yLow1.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.yLow2.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.yLow2.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.yLow3.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.yLow3.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.yLow4.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.yLow4.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.yLow5.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.yLow5.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.yLow6.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.yLow6.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.yLow7.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.yLow7.setVbarPolicy(ScrollBarPolicy.NEVER);
		
		// Bindet die ScrollPanes aneinander 
		
		DoubleProperty vPosition = new SimpleDoubleProperty();
	    vPosition.bind(this.upperGraphicPane1.vvalueProperty());
	    vPosition.addListener(new ChangeListener<Object>() {
	        @Override
	        public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
	        	yUp1.setVvalue((double) arg2);

	        }
	    });
	    
	    DoubleProperty aPosition = new SimpleDoubleProperty();
	    aPosition.bind(this.upperGraphicPane2.vvalueProperty());
	    aPosition.addListener(new ChangeListener<Object>() {
	        @Override
	        public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
	        	yUp2.setVvalue((double) arg2);

	        }
	    });
	    DoubleProperty bPosition = new SimpleDoubleProperty();
	    bPosition.bind(this.upperGraphicPane3.vvalueProperty());
	    bPosition.addListener(new ChangeListener<Object>() {
	        @Override
	        public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
	        	yUp3.setVvalue((double) arg2);

	        }
	    });
	    DoubleProperty cPosition = new SimpleDoubleProperty();
	    cPosition.bind(this.upperGraphicPane4.vvalueProperty());
	    cPosition.addListener(new ChangeListener<Object>() {
	        @Override
	        public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
	        	yUp4.setVvalue((double) arg2);

	        }
	    });
	    DoubleProperty dPosition = new SimpleDoubleProperty();
	    dPosition.bind(this.upperGraphicPane5.vvalueProperty());
	    dPosition.addListener(new ChangeListener<Object>() {
	        @Override
	        public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
	        	yUp5.setVvalue((double) arg2);

	        }
	    });
	    DoubleProperty ePosition = new SimpleDoubleProperty();
	    ePosition.bind(this.upperGraphicPane6.vvalueProperty());
	    ePosition.addListener(new ChangeListener<Object>() {
	        @Override
	        public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
	        	yUp6.setVvalue((double) arg2);

	        }
	    });
	    DoubleProperty fPosition = new SimpleDoubleProperty();
	    fPosition.bind(this.upperGraphicPane7.vvalueProperty());
	    fPosition.addListener(new ChangeListener<Object>() {
	        @Override
	        public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
	        	yUp7.setVvalue((double) arg2);

	        }
	    });
		
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
		this.upperGraphicPane2.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		     	refreshBothGraphics();
		    }
		});
		this.upperGraphicPane2.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	refreshBothGraphics();
		    }
		});
		this.upperGraphicPane3.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		     	refreshBothGraphics();
		    }
		});
		this.upperGraphicPane3.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	refreshBothGraphics();
		    }
		});
		this.upperGraphicPane4.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		     	refreshBothGraphics();
		    }
		});
		this.upperGraphicPane4.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	refreshBothGraphics();
		    }
		});
		this.upperGraphicPane5.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		     	refreshBothGraphics();
		    }
		});
		this.upperGraphicPane5.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	refreshBothGraphics();
		    }
		});
		this.upperGraphicPane6.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		     	refreshBothGraphics();
		    }
		});
		this.upperGraphicPane6.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		    	refreshBothGraphics();
		    }
		});
		this.upperGraphicPane7.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		     	refreshBothGraphics();
		    }
		});
		this.upperGraphicPane7.heightProperty().addListener(new ChangeListener<Number>() {
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
						if(firstUppergrafikErstellt == true){
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
			
			if(firstUppergrafikErstellt == true){
					this.xUp1.getChildren().clear();
				if(secondUppergrafikErstellt == true){
					this.xUp2.getChildren().clear();
				}
				if(thirdUppergrafikErstellt == true){
					this.xUp3.getChildren().clear();
				}
				if(fourthUppergrafikErstellt == true){
					this.xUp4.getChildren().clear();
				}
				if(fifthUppergrafikErstellt == true){
					this.xUp5.getChildren().clear();
				}
				if(sixthUppergrafikErstellt == true){
					this.xUp6.getChildren().clear();
				}
				if(seventhUppergrafikErstellt == true){
					this.xUp7.getChildren().clear();
				}
				this.addButtonPressed = false;
				createUpperGraphic();
		       }
			if(lowergrafikErstellt == true){
				this.lowergc1.clearRect(0, 0, this.lowerChart1.getWidth(), this.lowerChart1.getHeight());
				this.lowerXgc1.clearRect(0, 0, this.lowerXChart1.getWidth(), this.lowerXChart1.getHeight());
				createLowerGraphic();
				createLowerXScale();
			}
		
			if(this.hilfslinienAktiv == true){
				createHelpLines();			
			}
	}
	
	/**
	 * Creates The Upper Graphic.
	 */
	@FXML
	private void createUpperGraphic() {
		
		this.lowerDetailsPane.setMaxHeight(lowerDetailsPane.getHeight());
		this.lowerDetailsPane.setMinHeight(lowerDetailsPane.getHeight());
		
	SingleSelectionModel<Tab> selectionModel = Planpane.getSelectionModel();	
				
	 if(UPlan.getSelectionModel().getSelectedItem()!= null){
		 
		 //
		 // Erstellung der Umlaufplangrafik auf dem ersten Tab
		 //
		 // Löschen der bisherigen Elemente
		 
		if(this.umlaufTabCounter >= 0){
			
			if(this.firstUppergrafikErstellt == true){
			this.uppergc1.clearRect(0, 0, this.upperChart1.getWidth(), this.upperChart1.getHeight());
			this.upperXgc1.clearRect(0, 0, this.upperXChart1.getWidth(), this.upperXChart1.getHeight());
				if(this.secondUppergrafikErstellt == true){
				this.uppergc2.clearRect(0, 0, this.upperChart1.getWidth(), this.upperChart1.getHeight());
				}
				if(this.thirdUppergrafikErstellt == true){
					this.uppergc3.clearRect(0, 0, this.upperChart1.getWidth(), this.upperChart1.getHeight());
				}
				if(this.fourthUppergrafikErstellt == true){
					this.uppergc4.clearRect(0, 0, this.upperChart1.getWidth(), this.upperChart1.getHeight());
				}
				if(this.fifthUppergrafikErstellt == true){
					this.uppergc5.clearRect(0, 0, this.upperChart1.getWidth(), this.upperChart1.getHeight());
				}
				if(this.sixthUppergrafikErstellt == true){
					this.uppergc6.clearRect(0, 0, this.upperChart1.getWidth(), this.upperChart1.getHeight());
				}
				if(this.seventhUppergrafikErstellt == true){
					this.uppergc7.clearRect(0, 0, this.upperChart1.getWidth(), this.upperChart1.getHeight());
				}
		}
			
		if(this.UPlan1.isVisible() == false){
			this.umlaufplanEins = this.umlaufplanliste.get(this.UPlan.getSelectionModel().getSelectedIndex());
			this.upperheightEins = this.umlaufplanEins.getUmlauf().size()*40 + 10;
			createUpperYScale(yUp1, upperYChart1, upperYgc1, this.upperheightEins, this.umlaufplanEins);
		}
		// Labelbeschriftungen für Umlaufpläne
		this.UPlan1.setVisible(true);
		
		this.UPlanValue1.setText(UPlan.getSelectionModel().getSelectedItem().toString());
		this.UPlanValue1.setVisible(true);
		this.UPlanValue1.setStyle("-fx-background-color:#F0E68C; -fx-font-weight: bold;");
				
		// Zur Kontrolle ob es sich um einen Buttonklick handelt oder nicht
		
		if(this.addButtonPressed == true){
		selectionModel.select(this.Plan1);
		this.umlaufTabCounter = this.umlaufTabCounter + 1;
		}
		
		// Zur Verhinderung vom doppelten Erschaffen der Grafiken 
		
		if(this.firstUppergrafikErstellt == false){
			this.umlaufTabCounter = 0;
		}
		this.firstUppergrafikErstellt = true;
		// Hintergrunderstellung
		createUpperBackgroundGraphicFirstPane();
		createUpperXScalePane(this.xUp1, this.upperGraphicPane1);
		graphicTransition();		
		
			// Erstellung/ Befüllen des Details Table-- Clearen des Alten
			if(uDetailsTableErstellt == true){
			this.detailsUmlaufTable.getColumns().clear();
			this.uDetailsTableErstellt = false;
			}
		createUDetailsTable(this.umlaufplanEins);
		fillDetailPaneUmlauf(this.umlaufplanEins);
		createUmlaufElementGraphic(this.umlaufplanEins, this.upperGraphicPane1, this.upperChart1, this.uppergc1);
			
		}
		
		
		 //
		 // Erstellung der Umlaufplangrafik auf dem zweiten Tab
		 //
		
		
		if(this.umlaufTabCounter >= 1){
			
			if(this.secondUppergrafikErstellt == true){
			this.uppergc2.clearRect(0, 0, this.upperChart2.getWidth(), this.upperChart2.getHeight());
		}				
		// Labelbeschriftungen für Umlaufpläne und Enabling des Tabs
		
		this.Plan2.setDisable(false);		
		this.UPlan2.setVisible(true);
		this.UPlanValue2.setText(UPlan.getSelectionModel().getSelectedItem().toString());
		this.UPlanValue2.setVisible(true);
		this.UPlanValue1.setStyle("-fx-background-color:white;");
		this.UPlanValue2.setStyle("-fx-background-color:#F0E68C; -fx-font-weight: bold;");
		
		// Zur Kontrolle ob es sich um einen Buttonklick handelt oder nicht
		
		if(this.addButtonPressed == true){
		selectionModel.select(this.Plan2);
		this.umlaufTabCounter = 2;
		}
		
		// Zur Verhinderung vom doppelten Erschaffen der Grafiken 
		
		if(this.secondUppergrafikErstellt == false){
			this.umlaufTabCounter = 1;
		}
		
		this.secondUppergrafikErstellt = true;
		this.secondGrafikErstellt = true;
		
		// Hintergrunderstellung
		createUpperBackgroundGraphicSecondPane();
		createUpperXScalePane(this.xUp2, this.upperGraphicPane2);
		
						
		}
		
		 //
		 // Erstellung der Umlaufplangrafik auf dem dritten Tab
		 //
		
		
		if(this.umlaufTabCounter >= 2){
			
			if(this.thirdUppergrafikErstellt == true){
			this.uppergc3.clearRect(0, 0, this.upperChart3.getWidth(), this.upperChart3.getHeight());
		}				
		// Labelbeschriftungen für Umlaufpläne und Enabling des Tabs
		
		this.Plan3.setDisable(false);		
		this.UPlan3.setVisible(true);
		this.UPlanValue3.setText(UPlan.getSelectionModel().getSelectedItem().toString());
		this.UPlanValue3.setVisible(true);
		this.UPlanValue1.setStyle("-fx-background-color:white;");
		this.UPlanValue2.setStyle("-fx-background-color:white;");
		this.UPlanValue3.setStyle("-fx-background-color:#F0E68C; -fx-font-weight: bold;");
		
		// Zur Kontrolle ob es sich um einen Buttonklick handelt oder nicht
		
		if(this.addButtonPressed == true){
		selectionModel.select(this.Plan3);
		this.umlaufTabCounter = 3;
		}
		
		// Zur Verhinderung vom doppelten Erschaffen der Grafiken 
		
		if(this.thirdUppergrafikErstellt == false){
			this.umlaufTabCounter = 2;
		}
		
		this.thirdUppergrafikErstellt = true;
		this.thirdGrafikErstellt = true;
		
		// Hintergrunderstellung
		createUpperBackgroundGraphicThirdPane();
		createUpperXScalePane(this.xUp3, this.upperGraphicPane3);		
						
		}
		
		 //
		 // Erstellung der Umlaufplangrafik auf dem vierten Tab
		 //
		
		
		if(this.umlaufTabCounter >= 3){
			
			if(this.fourthUppergrafikErstellt == true){
			this.uppergc4.clearRect(0, 0, this.upperChart4.getWidth(), this.upperChart4.getHeight());
		}				
		// Labelbeschriftungen für Umlaufpläne und Enabling des Tabs
		
		this.Plan4.setDisable(false);		
		this.UPlan4.setVisible(true);
		this.UPlanValue4.setText(UPlan.getSelectionModel().getSelectedItem().toString());
		this.UPlanValue4.setVisible(true);
		this.UPlanValue1.setStyle("-fx-background-color:white;");
		this.UPlanValue2.setStyle("-fx-background-color:white;");
		this.UPlanValue3.setStyle("-fx-background-color:white;");
		this.UPlanValue4.setStyle("-fx-background-color:#F0E68C; -fx-font-weight: bold;");
		
		// Zur Kontrolle ob es sich um einen Buttonklick handelt oder nicht
		
		if(this.addButtonPressed == true){
		selectionModel.select(this.Plan4);
		this.umlaufTabCounter = 4;
		}
		
		// Zur Verhinderung vom doppelten Erschaffen der Grafiken 
		
		if(this.fourthUppergrafikErstellt == false){
			this.umlaufTabCounter = 3;
		}
		
		this.fourthUppergrafikErstellt = true;
		this.fourthGrafikErstellt = true;
		
		// Hintergrunderstellung
		createUpperBackgroundGraphicFourthPane();
		createUpperXScalePane(this.xUp4, this.upperGraphicPane4);		
						
		}
		
		 //
		 // Erstellung der Umlaufplangrafik auf dem fünften Tab
		 //
		
		
		if(this.umlaufTabCounter >= 4){
			
			if(this.fifthUppergrafikErstellt == true){
			this.uppergc5.clearRect(0, 0, this.upperChart5.getWidth(), this.upperChart5.getHeight());
		}				
		// Labelbeschriftungen für Umlaufpläne und Enabling des Tabs
		
		this.Plan5.setDisable(false);		
		this.UPlan5.setVisible(true);
		this.UPlanValue5.setText(UPlan.getSelectionModel().getSelectedItem().toString());
		this.UPlanValue5.setVisible(true);
		this.UPlanValue1.setStyle("-fx-background-color:white;");
		this.UPlanValue2.setStyle("-fx-background-color:white;");
		this.UPlanValue3.setStyle("-fx-background-color:white;");
		this.UPlanValue4.setStyle("-fx-background-color:white;");
		this.UPlanValue5.setStyle("-fx-background-color:#F0E68C; -fx-font-weight: bold;");
		
		// Zur Kontrolle ob es sich um einen Buttonklick handelt oder nicht
		
		if(this.addButtonPressed == true){
		selectionModel.select(this.Plan5);
		this.umlaufTabCounter = 5;
		}
		
		// Zur Verhinderung vom doppelten Erschaffen der Grafiken 
		
		if(this.fifthUppergrafikErstellt == false){
			this.umlaufTabCounter = 4;
		}
		
		this.fifthUppergrafikErstellt = true;
		this.fifthGrafikErstellt = true;
		
		// Hintergrunderstellung
		createUpperBackgroundGraphicFifthPane();
		createUpperXScalePane(this.xUp5, this.upperGraphicPane5);
		
						
		}
		
		 //
		 // Erstellung der Umlaufplangrafik auf dem sechsten Tab
		 //
		
		
		if(this.umlaufTabCounter >= 5){
			
			if(this.sixthUppergrafikErstellt == true){
			this.uppergc6.clearRect(0, 0, this.upperChart6.getWidth(), this.upperChart6.getHeight());
		}				
		// Labelbeschriftungen für Umlaufpläne und Enabling des Tabs
		
		this.Plan6.setDisable(false);		
		this.UPlan6.setVisible(true);
		this.UPlanValue6.setText(UPlan.getSelectionModel().getSelectedItem().toString());
		this.UPlanValue6.setVisible(true);
		this.UPlanValue1.setStyle("-fx-background-color:white;");
		this.UPlanValue2.setStyle("-fx-background-color:white;");
		this.UPlanValue3.setStyle("-fx-background-color:white;");
		this.UPlanValue4.setStyle("-fx-background-color:white;");
		this.UPlanValue5.setStyle("-fx-background-color:white;");
		this.UPlanValue6.setStyle("-fx-background-color:#F0E68C; -fx-font-weight: bold;");
		
		// Zur Kontrolle ob es sich um einen Buttonklick handelt oder nicht
		
		if(this.addButtonPressed == true){
		selectionModel.select(this.Plan6);
		this.umlaufTabCounter = 6;
		}
		
		// Zur Verhinderung vom doppelten Erschaffen der Grafiken 
		
		if(this.sixthUppergrafikErstellt == false){
			this.umlaufTabCounter = 5;
		}
		
		this.sixthUppergrafikErstellt = true;
		this.sixthGrafikErstellt = true;
		
		// Hintergrunderstellung
		createUpperBackgroundGraphicSixthPane();
		createUpperXScalePane(this.xUp6, this.upperGraphicPane6);
		
						
		}
		
		 //
		 // Erstellung der Umlaufplangrafik auf dem siebten Tab
		 //
		
		
		if(this.umlaufTabCounter >= 6){
			
			if(this.seventhUppergrafikErstellt == true){
			this.uppergc7.clearRect(0, 0, this.upperChart7.getWidth(), this.upperChart7.getHeight());
		}				
		// Labelbeschriftungen für Umlaufpläne und Enabling des Tabs
		
		this.Plan7.setDisable(false);		
		this.UPlan7.setVisible(true);
		this.UPlanValue7.setText(UPlan.getSelectionModel().getSelectedItem().toString());
		this.UPlanValue7.setVisible(true);
		this.UPlanValue1.setStyle("-fx-background-color:white;");
		this.UPlanValue2.setStyle("-fx-background-color:white;");
		this.UPlanValue3.setStyle("-fx-background-color:white;");
		this.UPlanValue4.setStyle("-fx-background-color:white;");
		this.UPlanValue5.setStyle("-fx-background-color:white;");
		this.UPlanValue6.setStyle("-fx-background-color:white;");
		this.UPlanValue7.setStyle("-fx-background-color:#F0E68C; -fx-font-weight: bold;");
		
		// Zur Kontrolle ob es sich um einen Buttonklick handelt oder nicht
		
		if(this.addButtonPressed == true){
		selectionModel.select(this.Plan7);
		this.umlaufTabCounter = 7;
		}
		
		// Zur Verhinderung vom doppelten Erschaffen der Grafiken 
		
		if(this.seventhUppergrafikErstellt == false){
			this.umlaufTabCounter = 6;
		}
		
		this.seventhUppergrafikErstellt = true;
		this.seventhGrafikErstellt = true;
		
		// Hintergrunderstellung
		createUpperBackgroundGraphicSeventhPane();
		createUpperXScalePane(this.xUp7, this.upperGraphicPane7);
		
						
		}
		
		this.addButtonPressed = true;
		
	  }
	}
			
	
	/**
	 * Creates The First Upper Background Graphic.
	 */
	@FXML
	private void createUpperBackgroundGraphicFirstPane() {
		
		//Initialize the Chart
		this.upperChart1 = new Canvas(this.upperGraphicPane1.getWidth()-4,this.upperheightEins);
				
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
	}	
	
	/**
	 * Creates the second Upper Background Graphic.
	 */
	@FXML
	private void createUpperBackgroundGraphicSecondPane() {
		
		//Initialize the Chart
		this.upperChart2 = new Canvas(this.upperGraphicPane2.getWidth()-4,this.upperheightZwei);
				
		//Erstellen des HintergrundgrafikKontextes
		this.uppergc2 = this.upperChart2.getGraphicsContext2D();
		this.uppergc2.clearRect(0, 0, this.upperChart2.getWidth(), this.upperChart2.getHeight());
		
		//Erstellen des Hintergrundes
		this.uppergc2.setFill(Color.BEIGE);				
		this.uppergc2.fillRect(0, 0,this.upperChart2.getWidth(),this.upperChart2.getHeight());
		
		this.uppergc2.setLineWidth(3);
		this.uppergc2.setStroke(Color.BLACK);
		this.uppergc2.strokeLine(1, 0, 1, this.upperChart2.getHeight());		
		
		double abstandNetz = (this.upperChart2.getWidth()-30)/(this.endzeitVar-this.startzeitVar);				
		this.uppergc2.setLineWidth(1);
		this.uppergc2.setFont(Font.getDefault());
		this.uppergc2.setFill(Color.BLACK);
		this.uppergc2.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for(int i=0; i<=(endzeitVar-startzeitVar) ;i++) {
			
			double pixel=((i)*abstandNetz);
			this.uppergc2.strokeLine(pixel, 0, pixel, this.upperChart2.getHeight());
	    }
		
		this.upperGraphicPane2.setContent(this.upperChart2);
	}
	
	/**
	 * Creates the second Upper Background Graphic.
	 */
	@FXML
	private void createUpperBackgroundGraphicThirdPane() {
		
		//Initialize the Chart
		this.upperChart3 = new Canvas(this.upperGraphicPane3.getWidth()-4,this.upperheightDrei);
				
		//Erstellen des HintergrundgrafikKontextes
		this.uppergc3 = this.upperChart3.getGraphicsContext2D();
		this.uppergc3.clearRect(0, 0, this.upperChart3.getWidth(), this.upperChart3.getHeight());
		
		//Erstellen des Hintergrundes
		this.uppergc3.setFill(Color.BEIGE);				
		this.uppergc3.fillRect(0, 0,this.upperChart3.getWidth(),this.upperChart3.getHeight());
		
		this.uppergc3.setLineWidth(3);
		this.uppergc3.setStroke(Color.BLACK);
		this.uppergc3.strokeLine(1, 0, 1, this.upperChart3.getHeight());		
		
		double abstandNetz = (this.upperChart3.getWidth()-30)/(this.endzeitVar-this.startzeitVar);				
		this.uppergc3.setLineWidth(1);
		this.uppergc3.setFont(Font.getDefault());
		this.uppergc3.setFill(Color.BLACK);
		this.uppergc3.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for(int i=0; i<=(endzeitVar-startzeitVar) ;i++) {
			
			double pixel=((i)*abstandNetz);
			this.uppergc3.strokeLine(pixel, 0, pixel, this.upperChart3.getHeight());
	    }
		
		this.upperGraphicPane3.setContent(this.upperChart3);
	}	
	
	/**
	 * Creates the second Upper Background Graphic.
	 */
	@FXML
	private void createUpperBackgroundGraphicFourthPane() {
		
		//Initialize the Chart
		this.upperChart4 = new Canvas(this.upperGraphicPane4.getWidth()-4,this.upperheightVier);
				
		//Erstellen des HintergrundgrafikKontextes
		this.uppergc4 = this.upperChart4.getGraphicsContext2D();
		this.uppergc4.clearRect(0, 0, this.upperChart4.getWidth(), this.upperChart4.getHeight());
		
		//Erstellen des Hintergrundes
		this.uppergc4.setFill(Color.BEIGE);				
		this.uppergc4.fillRect(0, 0,this.upperChart4.getWidth(),this.upperChart4.getHeight());
		
		this.uppergc4.setLineWidth(3);
		this.uppergc4.setStroke(Color.BLACK);
		this.uppergc4.strokeLine(1, 0, 1, this.upperChart4.getHeight());		
		
		double abstandNetz = (this.upperChart4.getWidth()-30)/(this.endzeitVar-this.startzeitVar);				
		this.uppergc4.setLineWidth(1);
		this.uppergc4.setFont(Font.getDefault());
		this.uppergc4.setFill(Color.BLACK);
		this.uppergc4.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for(int i=0; i<=(endzeitVar-startzeitVar) ;i++) {
			
			double pixel=((i)*abstandNetz);
			this.uppergc4.strokeLine(pixel, 0, pixel, this.upperChart4.getHeight());
	    }
		
		this.upperGraphicPane4.setContent(this.upperChart4);
	}
	
	/**
	 * Creates the second Upper Background Graphic.
	 */
	@FXML
	private void createUpperBackgroundGraphicFifthPane() {
		
		//Initialize the Chart
		this.upperChart5 = new Canvas(this.upperGraphicPane5.getWidth()-4,this.upperheightFuenf);
				
		//Erstellen des HintergrundgrafikKontextes
		this.uppergc5 = this.upperChart5.getGraphicsContext2D();
		this.uppergc5.clearRect(0, 0, this.upperChart5.getWidth(), this.upperChart5.getHeight());
		
		//Erstellen des Hintergrundes
		this.uppergc5.setFill(Color.BEIGE);				
		this.uppergc5.fillRect(0, 0,this.upperChart5.getWidth(),this.upperChart5.getHeight());
		
		this.uppergc5.setLineWidth(3);
		this.uppergc5.setStroke(Color.BLACK);
		this.uppergc5.strokeLine(1, 0, 1, this.upperChart5.getHeight());		
		
		double abstandNetz = (this.upperChart5.getWidth()-30)/(this.endzeitVar-this.startzeitVar);				
		this.uppergc5.setLineWidth(1);
		this.uppergc5.setFont(Font.getDefault());
		this.uppergc5.setFill(Color.BLACK);
		this.uppergc5.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for(int i=0; i<=(endzeitVar-startzeitVar) ;i++) {
			
			double pixel=((i)*abstandNetz);
			this.uppergc5.strokeLine(pixel, 0, pixel, this.upperChart5.getHeight());
	    }
		
		this.upperGraphicPane5.setContent(this.upperChart5);
	}
	
	/**
	 * Creates the second Upper Background Graphic.
	 */
	@FXML
	private void createUpperBackgroundGraphicSixthPane() {
		
		//Initialize the Chart
		this.upperChart6 = new Canvas(this.upperGraphicPane6.getWidth()-4,this.upperheightSechs);
				
		//Erstellen des HintergrundgrafikKontextes
		this.uppergc6 = this.upperChart6.getGraphicsContext2D();
		this.uppergc6.clearRect(0, 0, this.upperChart6.getWidth(), this.upperChart6.getHeight());
		
		//Erstellen des Hintergrundes
		this.uppergc6.setFill(Color.BEIGE);				
		this.uppergc6.fillRect(0, 0,this.upperChart6.getWidth(),this.upperChart6.getHeight());
		
		this.uppergc3.setLineWidth(3);
		this.uppergc6.setStroke(Color.BLACK);
		this.uppergc6.strokeLine(1, 0, 1, this.upperChart6.getHeight());		
		
		double abstandNetz = (this.upperChart6.getWidth()-30)/(this.endzeitVar-this.startzeitVar);				
		this.uppergc6.setLineWidth(1);
		this.uppergc6.setFont(Font.getDefault());
		this.uppergc6.setFill(Color.BLACK);
		this.uppergc6.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for(int i=0; i<=(endzeitVar-startzeitVar) ;i++) {
			
			double pixel=((i)*abstandNetz);
			this.uppergc6.strokeLine(pixel, 0, pixel, this.upperChart6.getHeight());
	    }
		
		this.upperGraphicPane6.setContent(this.upperChart6);
	}
	
	/**
	 * Creates the seventh Upper Background Graphic.
	 */
	@FXML
	private void createUpperBackgroundGraphicSeventhPane() {
		
		//Initialize the Chart
		this.upperChart7 = new Canvas(this.upperGraphicPane7.getWidth()-4,this.upperheightSieben);
				
		//Erstellen des HintergrundgrafikKontextes
		this.uppergc7 = this.upperChart7.getGraphicsContext2D();
		this.uppergc7.clearRect(0, 0, this.upperChart7.getWidth(), this.upperChart7.getHeight());
		
		//Erstellen des Hintergrundes
		this.uppergc7.setFill(Color.BEIGE);				
		this.uppergc7.fillRect(0, 0,this.upperChart7.getWidth(),this.upperChart7.getHeight());
		
		this.uppergc7.setLineWidth(3);
		this.uppergc7.setStroke(Color.BLACK);
		this.uppergc7.strokeLine(1, 0, 1, this.upperChart7.getHeight());		
		
		double abstandNetz = (this.upperChart7.getWidth()-30)/(this.endzeitVar-this.startzeitVar);				
		this.uppergc7.setLineWidth(1);
		this.uppergc7.setFont(Font.getDefault());
		this.uppergc7.setFill(Color.BLACK);
		this.uppergc7.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for(int i=0; i<=(endzeitVar-startzeitVar) ;i++) {
			
			double pixel=((i)*abstandNetz);
			this.uppergc7.strokeLine(pixel, 0, pixel, this.upperChart7.getHeight());
	    }
		
		this.upperGraphicPane7.setContent(this.upperChart7);
	}
	/**
	 * Creates The Upper X - Scale for Tabs.
	 */
	private void createUpperXScalePane(AnchorPane xScale, ScrollPane xScrollPane) {
		
		// Hier wird das Skala Canvas erzeugt
				this.upperXChart1 = new Canvas(xScrollPane.getWidth(),this.xUp1.getHeight());
				// Hier der Graphic Context dazu erzeugt
				this.upperXgc1 = this.upperXChart1.getGraphicsContext2D();
				this.upperXgc1.clearRect(0, 0, this.upperXChart1.getWidth(), this.upperXChart1.getHeight());
				
				double abstandNetz = (this.upperXChart1.getWidth()-30)/(this.endzeitVar-this.startzeitVar);				
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
						
				xScale.getChildren().add(upperXChart1);
	}
	
	/**
	 * Creates The Lower Y - Scale.
	 */
	private void createUpperYScale(ScrollPane scrollPane, Canvas canvas, GraphicsContext gc, double height, Umlaufplan umlaufplan) {
		
		//Initialize the Chart
		canvas = new Canvas(30,height);
		
		//Erstellen des HintergrundgrafikKontextes		
		gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		//Eigenschaften der Beschriftung
		gc.setLineWidth(1);
		gc.setFont(Font.getDefault());
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.BLACK);
		// Auslesen der Werte		
		for (int j = 0; j < umlaufplan.getUmlauf().size(); j++) {
			
			gc.fillText("B "+umlaufplan.getUmlauf().get(j).getId(), 4, 22 + (j*40));
			
		}
		
		scrollPane.setContent(canvas);
		
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
	 * Fills the DetailPane für die Umlaufpläne
	 */
	private void fillDetailPaneUmlauf(Umlaufplan umlaufplan) {
		
		this.anzahlFahrten.setVisible(true);
		this.anzahlFahrten.setText(String.valueOf(umlaufplan.getFahrtZuUmlauf().size()));
		
	}
	/**
	 * Creates DetailsTable.
	 */
	@SuppressWarnings("unchecked")
	private void createUDetailsTable(Umlaufplan umlaufplan) {
		
				this.detailsUmlaufTable.setEditable(true);
				
				TableColumn<Blockelement, Integer> blockEleCol = new TableColumn<Blockelement, Integer>("Block-Ele.");
				TableColumn<Blockelement, String> startzeitCol = new TableColumn<Blockelement, String>("Startzeit");
				TableColumn<Blockelement, String> endzeitCol = new TableColumn<Blockelement, String>("Endzeit");
				TableColumn<Blockelement, Integer> eleTypeCol = new TableColumn<Blockelement, Integer>("Ele.-Type");
				TableColumn<Blockelement, Integer> blockCol = new TableColumn<Blockelement, Integer>("Block");
				TableColumn<Blockelement, String> dauerCol = new TableColumn<Blockelement, String>("Dauer");		
				
				blockEleCol.setCellValueFactory(new PropertyValueFactory<Blockelement, Integer>("id"));
				startzeitCol.setCellValueFactory(new PropertyValueFactory<Blockelement, String>("depTime"));
				endzeitCol.setCellValueFactory(new PropertyValueFactory<Blockelement, String>("arrTime"));
				eleTypeCol.setCellValueFactory(new PropertyValueFactory<Blockelement, Integer>("elementType"));
				blockCol.setCellValueFactory(new PropertyValueFactory<Blockelement, Integer>("blockID"));
				dauerCol.setCellValueFactory(new PropertyValueFactory<Blockelement, String>("driveTime"));
				
				blockEleCol.prefWidthProperty().bind(blockEleCol.widthProperty());
				startzeitCol.prefWidthProperty().bind(startzeitCol.widthProperty());
				endzeitCol.prefWidthProperty().bind(endzeitCol.widthProperty());
				eleTypeCol.prefWidthProperty().bind(eleTypeCol.widthProperty());
				blockCol.prefWidthProperty().bind(blockCol.widthProperty());
				dauerCol.prefWidthProperty().bind(dauerCol.widthProperty());
				
				// Hereinladen der Daten
				
				ObservableList<Blockelement> data = FXCollections.observableArrayList();
				
				for ( int i = 0; i < umlaufplan.getFahrtZuUmlauf().size(); i++ ){
					
					// Berrechnen der Dauer zwischen der Abfahrt und der Ankunft
					
					String depTime = umlaufplan.getFahrtZuUmlauf().get(i).getDepTime();
					String arrtime = umlaufplan.getFahrtZuUmlauf().get(i).getArrTime();
					
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
					differenz = (differenz/1000)/60;
					umlaufplan.getFahrtZuUmlauf().get(i).setDriveTime(differenz+" min");
					
					
					// Hinzufügen der Daten
					
					data.add(umlaufplan.getFahrtZuUmlauf().get(i));
				};
				
				this.detailsUmlaufTable.setItems(data);
				this.detailsUmlaufTable.getColumns().addAll(blockEleCol, startzeitCol, endzeitCol, eleTypeCol, blockCol, dauerCol);
				this.tablePane.setContent(detailsUmlaufTable);
				
				uDetailsTableErstellt = true;
	}
	
	/**
	 * Creates Elements in the Graphic.
	 */
	private void createUmlaufElementGraphic(Umlaufplan umlaufplan, ScrollPane scrollPane, Canvas canvas, GraphicsContext gc ) {
		
		double abstandNetz = (canvas.getWidth()-30)/(this.endzeitVar-this.startzeitVar);
				
		 // Auslesen der Blockanzahl		 
		 for (int j = 0; j < umlaufplan.getUmlauf().size(); j++) {
			 
			 // Auslesen Blockelementanzahl
			 for (int i = 0; i < umlaufplan.getFahrtZuUmlauf().size(); i++) {		
			
				// Abgleich mit den Werten
				if(umlaufplan.getFahrtZuUmlauf().get(i).getBlockID() == umlaufplan.getUmlauf().get(j).getId()){
					
					// Auslesen der Zeit als Integer
					StringSplitter ss = new StringSplitter();
					int [] zeit= new int[2];
					zeit = ss.intParse(umlaufplan.getFahrtZuUmlauf().get(i).getDepTime());					
					int startHour = zeit[0] ;
					int startMin = zeit[1] ;					
					zeit = ss.intParse(umlaufplan.getFahrtZuUmlauf().get(i).getArrTime());
					int endHour = zeit[0];
					int endMin = zeit[1];
					
					
					// Belegung der Pixelwerte
					if(this.startzeitVar <= startHour && this.endzeitVar > endHour){
					int stundenDifferenz =	startHour - this.startzeitVar;
					int startPixelX = (int) ((stundenDifferenz*abstandNetz) + ((abstandNetz/60)*startMin));
					int startPixelY = 10 + (j*40);
					int fahrtDauer = 0;
					// Berrechnen der Dauer zwischen der Abfahrt und der Ankunft
					
					String depTime = umlaufplan.getFahrtZuUmlauf().get(i).getDepTime();
					String arrtime = umlaufplan.getFahrtZuUmlauf().get(i).getArrTime();
					
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
					differenz = (differenz/1000)/60;										

					fahrtDauer = (int) (differenz *(abstandNetz/60));
					
					//Färben der Elemente
					
					switch(umlaufplan.getFahrtZuUmlauf().get(i).getElementType()){
					
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
					
					 gc.fillRoundRect(startPixelX, startPixelY, fahrtDauer, 20, 20, 10);
					 gc.strokeRoundRect(startPixelX, startPixelY, fahrtDauer, 20, 20, 10);
					}					
				}
		 	}			
		}		
	}
	
	/**
	 * Creates The Lower X - Scale.
	 */
	@FXML
	private void createLowerXScale() {
		
		// Hier wird das Skala Canvas erzeugt
		this.lowerXChart1 = new Canvas(this.lowerGraphicPane1.getWidth(),this.xLow1.getHeight());
		// Hier der Graphic Context dazu erzeugt
		this.lowerXgc1 = this.lowerXChart1.getGraphicsContext2D();
		this.lowerXgc1.clearRect(0, 0, this.lowerXChart1.getWidth(), this.lowerXChart1.getHeight());
		
		double abstandNetz = (this.lowerXChart1.getWidth()-30)/(this.endzeitVar-this.startzeitVar);				
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
				
		this.xLow1.getChildren().add(lowerXChart1);
		
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
	private void createHelpLines() {
		
		createHelpLinesFirstTab();
		if(this.secondGrafikErstellt == true){
		createHelpLinesSecondTab();
		}
		if(this.thirdGrafikErstellt == true){
			createHelpLinesThirdTab();
		}
		if(this.fourthGrafikErstellt == true){
			createHelpLinesFourthTab();
		}
		if(this.fifthGrafikErstellt == true){
			createHelpLinesFifthTab();
		}
		if(this.sixthGrafikErstellt == true){
			createHelpLinesSixthTab();
		}
		if(this.seventhGrafikErstellt == true){
			createHelpLinesSeventhTab();
		}		
	}

	/**
	 * Creates The Helplines in the graphic.
	 */
	private void createHelpLinesFirstTab() {
		
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
		if(this.firstUppergrafikErstellt == true){	
			
			
			
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
	 * Creates The Helplines in the graphic.
	 */
	private void createHelpLinesSecondTab() {
		
		
		double abstandNetz = (this.upperChart2.getWidth()-30)/(this.endzeitVar-this.startzeitVar);	
		// Methoden zu Erstellung der dynamischen Hilfslinien
			
		if(lowergrafikErstellt == true){
			
			this.lowergc2.setLineWidth(1);
			this.lowergc2.setStroke(Color.LIGHTGREY);
			
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {			
				
			double pixel=((i)*abstandNetz)+abstandNetz/4;
			this.lowergc2.strokeLine(pixel, 0, pixel, this.lowerChart2.getHeight()-3);
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
				
			double pixel=((i)*abstandNetz)+abstandNetz/2;
			this.lowergc2.strokeLine(pixel, 0, pixel, this.lowerChart2.getHeight()-3);
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
				
			double pixel=((i)*abstandNetz)+abstandNetz/4*3;
			this.lowergc2.strokeLine(pixel, 0, pixel, this.lowerChart2.getHeight()-3);
			
			}
		}
		
		if(this.secondUppergrafikErstellt == true){	
					
			this.uppergc2.setLineWidth(1);
			this.uppergc2.setStroke(Color.LIGHTGREY);
			
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/4;
			this.uppergc2.strokeLine(pixel, 0, pixel, this.upperChart1.getHeight());			
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/2;
			this.uppergc2.strokeLine(pixel, 0, pixel, this.upperChart1.getHeight());
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/4*3;
			this.uppergc2.strokeLine(pixel, 0, pixel, this.upperChart1.getHeight());
			
			}
		}
					
	}
	/**
	 * Creates The Helplines in the graphic.
	 */
	private void createHelpLinesThirdTab() {
		
		double abstandNetz = (this.upperChart3.getWidth()-30)/(this.endzeitVar-this.startzeitVar);	
		// Methoden zu Erstellung der dynamischen Hilfslinien
			
		if(lowergrafikErstellt == true){
			
			this.lowergc3.setLineWidth(1);
			this.lowergc3.setStroke(Color.LIGHTGREY);
			
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {			
				
			double pixel=((i)*abstandNetz)+abstandNetz/4;
			this.lowergc3.strokeLine(pixel, 0, pixel, this.lowerChart3.getHeight()-3);
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
				
			double pixel=((i)*abstandNetz)+abstandNetz/2;
			this.lowergc3.strokeLine(pixel, 0, pixel, this.lowerChart3.getHeight()-3);
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
				
			double pixel=((i)*abstandNetz)+abstandNetz/4*3;
			this.lowergc3.strokeLine(pixel, 0, pixel, this.lowerChart3.getHeight()-3);
			
			}
		}
		if(this.thirdUppergrafikErstellt == true){	
			
			
			
			this.uppergc3.setLineWidth(1);
			this.uppergc3.setStroke(Color.LIGHTGREY);
			
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/4;
			this.uppergc3.strokeLine(pixel, 0, pixel, this.upperChart3.getHeight());			
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/2;
			this.uppergc3.strokeLine(pixel, 0, pixel, this.upperChart3.getHeight());
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/4*3;
			this.uppergc3.strokeLine(pixel, 0, pixel, this.upperChart3.getHeight());
			
			}
		}
					
	}
	/**
	 * Creates The Helplines in the graphic.
	 */
	private void createHelpLinesFourthTab() {
		
		double abstandNetz = (this.upperChart4.getWidth()-30)/(this.endzeitVar-this.startzeitVar);	
		// Methoden zu Erstellung der dynamischen Hilfslinien
			
		if(lowergrafikErstellt == true){
			
			this.lowergc4.setLineWidth(1);
			this.lowergc4.setStroke(Color.LIGHTGREY);
			
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {			
				
			double pixel=((i)*abstandNetz)+abstandNetz/4;
			this.lowergc4.strokeLine(pixel, 0, pixel, this.lowerChart4.getHeight()-3);
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
				
			double pixel=((i)*abstandNetz)+abstandNetz/2;
			this.lowergc4.strokeLine(pixel, 0, pixel, this.lowerChart4.getHeight()-3);
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
				
			double pixel=((i)*abstandNetz)+abstandNetz/4*3;
			this.lowergc4.strokeLine(pixel, 0, pixel, this.lowerChart4.getHeight()-3);
			
			}
		}
		if(this.fourthUppergrafikErstellt == true){	
			
			
			
			this.uppergc4.setLineWidth(1);
			this.uppergc4.setStroke(Color.LIGHTGREY);
			
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/4;
			this.uppergc4.strokeLine(pixel, 0, pixel, this.upperChart4.getHeight());			
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/2;
			this.uppergc4.strokeLine(pixel, 0, pixel, this.upperChart4.getHeight());
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/4*3;
			this.uppergc4.strokeLine(pixel, 0, pixel, this.upperChart4.getHeight());
			
			}
		}
					
	}
	/**
	 * Creates The Helplines in the graphic.
	 */
	private void createHelpLinesFifthTab() {
		
		double abstandNetz = (this.upperChart5.getWidth()-30)/(this.endzeitVar-this.startzeitVar);	
		// Methoden zu Erstellung der dynamischen Hilfslinien
			
		if(lowergrafikErstellt == true){
			
			this.lowergc5.setLineWidth(1);
			this.lowergc5.setStroke(Color.LIGHTGREY);
			
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {			
				
			double pixel=((i)*abstandNetz)+abstandNetz/4;
			this.lowergc5.strokeLine(pixel, 0, pixel, this.lowerChart5.getHeight()-3);
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
				
			double pixel=((i)*abstandNetz)+abstandNetz/2;
			this.lowergc5.strokeLine(pixel, 0, pixel, this.lowerChart5.getHeight()-3);
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
				
			double pixel=((i)*abstandNetz)+abstandNetz/4*3;
			this.lowergc5.strokeLine(pixel, 0, pixel, this.lowerChart5.getHeight()-3);
			
			}
		}
		if(this.fifthUppergrafikErstellt == true){				
			
			
			this.uppergc5.setLineWidth(1);
			this.uppergc5.setStroke(Color.LIGHTGREY);
			
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/4;
			this.uppergc5.strokeLine(pixel, 0, pixel, this.upperChart5.getHeight());			
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/2;
			this.uppergc5.strokeLine(pixel, 0, pixel, this.upperChart5.getHeight());
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/4*3;
			this.uppergc5.strokeLine(pixel, 0, pixel, this.upperChart5.getHeight());
			
			}
		}
					
	}
	/**
	 * Creates The Helplines in the graphic.
	 */
	private void createHelpLinesSixthTab() {
		
		double abstandNetz = (this.upperChart6.getWidth()-30)/(this.endzeitVar-this.startzeitVar);	
		// Methoden zu Erstellung der dynamischen Hilfslinien
			
		if(lowergrafikErstellt == true){
			
			this.lowergc6.setLineWidth(1);
			this.lowergc6.setStroke(Color.LIGHTGREY);
			
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {			
				
			double pixel=((i)*abstandNetz)+abstandNetz/4;
			this.lowergc6.strokeLine(pixel, 0, pixel, this.lowerChart6.getHeight()-3);
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
				
			double pixel=((i)*abstandNetz)+abstandNetz/2;
			this.lowergc6.strokeLine(pixel, 0, pixel, this.lowerChart6.getHeight()-3);
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
				
			double pixel=((i)*abstandNetz)+abstandNetz/4*3;
			this.lowergc6.strokeLine(pixel, 0, pixel, this.lowerChart6.getHeight()-3);
			
			}
		}
		if(this.sixthUppergrafikErstellt == true){	
			
			
			
			this.uppergc6.setLineWidth(1);
			this.uppergc6.setStroke(Color.LIGHTGREY);
			
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/4;
			this.uppergc6.strokeLine(pixel, 0, pixel, this.upperChart6.getHeight());			
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/2;
			this.uppergc6.strokeLine(pixel, 0, pixel, this.upperChart6.getHeight());
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/4*3;
			this.uppergc6.strokeLine(pixel, 0, pixel, this.upperChart6.getHeight());
			
			}
		}
					
	}
	/**
	 * Creates The Helplines in the graphic.
	 */
	private void createHelpLinesSeventhTab() {
		
		double abstandNetz = (this.upperChart7.getWidth()-30)/(this.endzeitVar-this.startzeitVar);	
		// Methoden zu Erstellung der dynamischen Hilfslinien
			
		if(lowergrafikErstellt == true){
			
			this.lowergc7.setLineWidth(1);
			this.lowergc7.setStroke(Color.LIGHTGREY);
			
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {			
				
			double pixel=((i)*abstandNetz)+abstandNetz/4;
			this.lowergc7.strokeLine(pixel, 0, pixel, this.lowerChart7.getHeight()-3);
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
				
			double pixel=((i)*abstandNetz)+abstandNetz/2;
			this.lowergc7.strokeLine(pixel, 0, pixel, this.lowerChart7.getHeight()-3);
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
				
			double pixel=((i)*abstandNetz)+abstandNetz/4*3;
			this.lowergc7.strokeLine(pixel, 0, pixel, this.lowerChart7.getHeight()-3);
			
			}
		}
		if(this.seventhUppergrafikErstellt == true){	
			
			
			
			this.uppergc7.setLineWidth(1);
			this.uppergc7.setStroke(Color.LIGHTGREY);
			
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/4;
			this.uppergc7.strokeLine(pixel, 0, pixel, this.upperChart7.getHeight());			
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/2;
			this.uppergc7.strokeLine(pixel, 0, pixel, this.upperChart7.getHeight());
			
			}
			for(int i=0; i<(this.endzeitVar-this.startzeitVar) ;i++) {
					
			double pixel=((i)*abstandNetz)+abstandNetz/4*3;
			this.uppergc7.strokeLine(pixel, 0, pixel, this.upperChart7.getHeight());
			
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
	
	//Methoden zur Festsetzung der Main


	public void openFullscreen() {
		if(this.firstUppergrafikErstellt== true){
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
