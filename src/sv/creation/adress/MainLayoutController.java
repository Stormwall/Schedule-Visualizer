package sv.creation.adress;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sv.creation.adress.database.DBMatching;
import sv.creation.adress.model.Blockelement;
import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Dutyelement;
import sv.creation.adress.model.Umlaufplan;
import sv.creation.adress.util.StringSplitter;
import javafx.animation.FadeTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
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
	private Button loeschenUmlaufplan;
	@FXML
	private Button loeschenDienstplan;
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
	private TableView<Dutyelement> detailsDienstTable = new TableView<Dutyelement>();

	// Zugriff auf die Labels des DetailPane

	@FXML
	private Label anzahlFahrten;
	@FXML
	private Label anzahlServiceFahrten;
	@FXML
	private Label anzahlLeerFahrten;
	@FXML
	private Label gehoertzuFahrplan;

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

	private ArrayList<Umlaufplan> umlaufplanliste = new ArrayList<Umlaufplan>();
	private ArrayList<Dienstplan> dienstplanliste = new ArrayList<Dienstplan>();

	// Zuordnung der UmlaufplÃ¤ne

	private Umlaufplan umlaufplanEins;
	private Umlaufplan umlaufplanZwei;
	private Umlaufplan umlaufplanDrei;
	private Umlaufplan umlaufplanVier;
	private Umlaufplan umlaufplanFuenf;
	private Umlaufplan umlaufplanSechs;
	private Umlaufplan umlaufplanSieben;

	// Zuordnung der DienstplÃ¤ne

	private Dienstplan dienstplanEins;
	private Dienstplan dienstplanZwei;
	private Dienstplan dienstplanDrei;
	private Dienstplan dienstplanVier;
	private Dienstplan dienstplanFuenf;
	private Dienstplan dienstplanSechs;
	private Dienstplan dienstplanSieben;

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
	private Canvas upperYChart2;
	private Canvas lowerYChart2;
	private Canvas upperYChart3;
	private Canvas lowerYChart3;
	private Canvas upperYChart4;
	private Canvas lowerYChart4;
	private Canvas upperYChart5;
	private Canvas lowerYChart5;
	private Canvas upperYChart6;
	private Canvas lowerYChart6;
	private Canvas upperYChart7;
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
	private GraphicsContext upperYgc2;
	private GraphicsContext lowerYgc2;
	private GraphicsContext upperYgc3;
	private GraphicsContext lowerYgc3;
	private GraphicsContext upperYgc4;
	private GraphicsContext lowerYgc4;
	private GraphicsContext upperYgc5;
	private GraphicsContext lowerYgc5;
	private GraphicsContext upperYgc6;
	private GraphicsContext lowerYgc6;
	private GraphicsContext upperYgc7;
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
	private double lowerheightEins = 600;
	private double lowerheightZwei = 800;
	private double lowerheightDrei = 800;
	private double lowerheightVier = 800;
	private double lowerheightFuenf = 800;
	private double lowerheightSechs = 800;
	private double lowerheightSieben = 800;

	// Pruefvariable Upper Screen

	private boolean firstUppergrafikErstellt = false;
	private boolean secondUppergrafikErstellt = false;
	private boolean thirdUppergrafikErstellt = false;
	private boolean fourthUppergrafikErstellt = false;
	private boolean fifthUppergrafikErstellt = false;
	private boolean sixthUppergrafikErstellt = false;
	private boolean seventhUppergrafikErstellt = false;

	private int umlaufChoice = 0;
	private int umlaufTabCounter = 0;

	private boolean uDetailsTableErstellt = false;

	// Pruefvariable Lower Screen

	private boolean firstLowergrafikErstellt = false;
	private boolean secondLowergrafikErstellt = false;
	private boolean thirdLowergrafikErstellt = false;
	private boolean fourthLowergrafikErstellt = false;
	private boolean fifthLowergrafikErstellt = false;
	private boolean sixthLowergrafikErstellt = false;
	private boolean seventhLowergrafikErstellt = false;

	private int dienstChoice = 0;
	private int dienstTabCounter = 0;

	private boolean dDetailsTableErstellt = false;

	// weitere PrÃ¼fvariablen

	private boolean secondGrafikErstellt = false;
	private boolean thirdGrafikErstellt = false;
	private boolean fourthGrafikErstellt = false;
	private boolean fifthGrafikErstellt = false;
	private boolean sixthGrafikErstellt = false;
	private boolean seventhGrafikErstellt = false;

	private boolean umlaufIsCurrent = false;
	private boolean dienstIsCurrent = false;

	private boolean hilfslinienAktiv = false;
	private boolean addButtonPressed = true;

	// Referenz zur MainApp

	private MainApplication mainApp;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {

		// Fades in Filter Panel
		FadeTransition fa = new FadeTransition(Duration.millis(3000),
				this.leftinnerPane);
		fa.setFromValue(0.0);
		fa.setToValue(1.0);
		fa.setAutoReverse(true);
		fa.play();

		FadeTransition ft = new FadeTransition(Duration.millis(5000),
				this.rightinnerPane);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.setAutoReverse(true);
		ft.play();

		// BefÃ¼llung der Dropdownmenues zur Auswahl der PlÃ¤ne

		fillUmlaufplanliste();
		fillDienstplanliste();

		// Sets the Standardelement condition of the Interface

		detailsUmlaufTable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		detailsDienstTable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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
			public void changed(ObservableValue<?> arg0, Object arg1,
					Object arg2) {
				yUp1.setVvalue((double) arg2);

			}
		});

		DoubleProperty aPosition = new SimpleDoubleProperty();
		aPosition.bind(this.upperGraphicPane2.vvalueProperty());
		aPosition.addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> arg0, Object arg1,
					Object arg2) {
				yUp2.setVvalue((double) arg2);

			}
		});
		DoubleProperty bPosition = new SimpleDoubleProperty();
		bPosition.bind(this.upperGraphicPane3.vvalueProperty());
		bPosition.addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> arg0, Object arg1,
					Object arg2) {
				yUp3.setVvalue((double) arg2);

			}
		});
		DoubleProperty cPosition = new SimpleDoubleProperty();
		cPosition.bind(this.upperGraphicPane4.vvalueProperty());
		cPosition.addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> arg0, Object arg1,
					Object arg2) {
				yUp4.setVvalue((double) arg2);

			}
		});
		DoubleProperty dPosition = new SimpleDoubleProperty();
		dPosition.bind(this.upperGraphicPane5.vvalueProperty());
		dPosition.addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> arg0, Object arg1,
					Object arg2) {
				yUp5.setVvalue((double) arg2);

			}
		});
		DoubleProperty ePosition = new SimpleDoubleProperty();
		ePosition.bind(this.upperGraphicPane6.vvalueProperty());
		ePosition.addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> arg0, Object arg1,
					Object arg2) {
				yUp6.setVvalue((double) arg2);

			}
		});
		DoubleProperty fPosition = new SimpleDoubleProperty();
		fPosition.bind(this.upperGraphicPane7.vvalueProperty());
		fPosition.addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> arg0, Object arg1,
					Object arg2) {
				yUp7.setVvalue((double) arg2);

			}
		});

		DoubleProperty gPosition = new SimpleDoubleProperty();
		gPosition.bind(this.lowerGraphicPane1.vvalueProperty());
		gPosition.addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> arg0, Object arg1,
					Object arg2) {
				yLow1.setVvalue((double) arg2);

			}
		});

		DoubleProperty hPosition = new SimpleDoubleProperty();
		hPosition.bind(this.lowerGraphicPane2.vvalueProperty());
		hPosition.addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> arg0, Object arg1,
					Object arg2) {
				yLow2.setVvalue((double) arg2);

			}
		});
		DoubleProperty iPosition = new SimpleDoubleProperty();
		iPosition.bind(this.upperGraphicPane3.vvalueProperty());
		iPosition.addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> arg0, Object arg1,
					Object arg2) {
				yLow3.setVvalue((double) arg2);

			}
		});
		DoubleProperty jPosition = new SimpleDoubleProperty();
		jPosition.bind(this.lowerGraphicPane4.vvalueProperty());
		jPosition.addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> arg0, Object arg1,
					Object arg2) {
				yLow4.setVvalue((double) arg2);

			}
		});
		DoubleProperty kPosition = new SimpleDoubleProperty();
		kPosition.bind(this.lowerGraphicPane5.vvalueProperty());
		kPosition.addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> arg0, Object arg1,
					Object arg2) {
				yLow5.setVvalue((double) arg2);

			}
		});
		DoubleProperty lPosition = new SimpleDoubleProperty();
		lPosition.bind(this.lowerGraphicPane6.vvalueProperty());
		lPosition.addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> arg0, Object arg1,
					Object arg2) {
				yLow6.setVvalue((double) arg2);

			}
		});

		DoubleProperty mPosition = new SimpleDoubleProperty();
		mPosition.bind(this.lowerGraphicPane7.vvalueProperty());
		mPosition.addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> arg0, Object arg1,
					Object arg2) {
				yLow7.setVvalue((double) arg2);

			}
		});

		// Listen for Resizechanges (Graphic)
		this.upperGraphicPane1.widthProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.upperGraphicPane1.heightProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.upperGraphicPane2.widthProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.upperGraphicPane2.heightProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.upperGraphicPane3.widthProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.upperGraphicPane3.heightProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.upperGraphicPane4.widthProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.upperGraphicPane4.heightProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.upperGraphicPane5.widthProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.upperGraphicPane5.heightProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.upperGraphicPane6.widthProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.upperGraphicPane6.heightProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.upperGraphicPane7.widthProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.upperGraphicPane7.heightProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.lowerGraphicPane1.widthProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.lowerGraphicPane1.heightProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.lowerGraphicPane2.widthProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.lowerGraphicPane2.heightProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.lowerGraphicPane3.widthProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.lowerGraphicPane3.heightProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.lowerGraphicPane4.widthProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.lowerGraphicPane4.heightProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.lowerGraphicPane5.widthProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.lowerGraphicPane5.heightProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.lowerGraphicPane6.widthProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.lowerGraphicPane6.heightProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.lowerGraphicPane7.widthProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});
		this.lowerGraphicPane7.heightProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {
						refreshBothGraphics();
					}
				});

		// Listen for selection changes (StartSlider)

		this.startzeitSlider.valueProperty().addListener(
				new ChangeListener<Number>() {
					public void changed(ObservableValue<? extends Number> ov,
							Number old_val, Number new_val) {
						// Handhabung wenn die Checkbox angewï¿½hlt wird
						startzeitVar = new_val.intValue();
						if (startzeitVar > endzeitVar) {
							endzeitVar = endzeitVar + 24;
						}
					}
				});

		// Listen for selection changes (EndSlider)

		this.endzeitSlider.valueProperty().addListener(
				new ChangeListener<Number>() {
					public void changed(ObservableValue<? extends Number> ov,
							Number old_val, Number new_val) {
						// Handhabung wenn die Checkbox angewï¿½hlt wird
						endzeitVar = new_val.intValue();
						if (startzeitVar > endzeitVar) {
							endzeitVar = new_val.intValue() + 24;
						}
						if (startzeitVar < new_val.intValue()) {
							endzeitVar = new_val.intValue();
						}
					}
				});

		// Listen for selection changes (Checkbox... Hilfslinien)

		this.hilfslinien.selectedProperty().addListener(
				new ChangeListener<Boolean>() {
					public void changed(ObservableValue<? extends Boolean> ov,
							Boolean old_val, Boolean new_val) {
						// Handhabung wenn die Checkbox angewï¿½hlt wird
						if (new_val == true) {
							if (firstUppergrafikErstellt == true
									|| firstLowergrafikErstellt == true) {
								createHelpLines();
								hilfslinienAktiv = true;
							} else {
								String fehlerA = "Es wurde noch keine Grafik erzeugt";
								String fehlerB = "Noch nicht";
								String fehlerC = "Fehler";
								mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
								hilfslinien.setSelected(false);
							}
						}
						// Handhabung wenn die Checkbox ausgeschaltet wird
						if (new_val == false) {
							hilfslinienAktiv = false;
							refreshBothGraphics();

						}
					}
				});
	}

	/**
	 * Refreshes Both Graphics.
	 */
	@FXML
	private void refreshBothGraphics() {

		// Hier wird das Feld gecleared und geprï¿½ft ob es schon existiert

		if (firstUppergrafikErstellt == true) {

			this.xUp1.getChildren().clear();
			if (secondUppergrafikErstellt == true) {
				this.xUp2.getChildren().clear();
			}
			if (thirdUppergrafikErstellt == true) {
				this.xUp3.getChildren().clear();
			}
			if (fourthUppergrafikErstellt == true) {
				this.xUp4.getChildren().clear();
			}
			if (fifthUppergrafikErstellt == true) {
				this.xUp5.getChildren().clear();
			}
			if (sixthUppergrafikErstellt == true) {
				this.xUp6.getChildren().clear();
			}
			if (seventhUppergrafikErstellt == true) {
				this.xUp7.getChildren().clear();
			}
			this.addButtonPressed = false;
			createUpperGraphic();
		}

		if (firstLowergrafikErstellt == true) {

			this.xLow1.getChildren().clear();
			if (secondLowergrafikErstellt == true) {
				this.xLow2.getChildren().clear();
			}
			if (thirdLowergrafikErstellt == true) {
				this.xLow3.getChildren().clear();
			}
			if (fourthLowergrafikErstellt == true) {
				this.xLow4.getChildren().clear();
			}
			if (fifthLowergrafikErstellt == true) {
				this.xLow5.getChildren().clear();
			}
			if (sixthLowergrafikErstellt == true) {
				this.xLow6.getChildren().clear();
			}
			if (seventhLowergrafikErstellt == true) {
				this.xLow7.getChildren().clear();
			}
			this.addButtonPressed = false;
			createLowerGraphic();
		}

		if (this.hilfslinienAktiv == true) {
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

		final SingleSelectionModel<Tab> selectionModel = Planpane
				.getSelectionModel();

		if (UPlan.getSelectionModel().getSelectedItem() != null) {

			//
			// Erstellung der Umlaufplangrafik auf dem ersten Tab
			//
			// LÃ¶schen der bisherigen Elemente

			if (this.umlaufTabCounter >= 0) {

				if (this.UPlan1.isVisible() == false) {
					this.umlaufplanEins = this.umlaufplanliste.get(this.UPlan
							.getSelectionModel().getSelectedIndex());
					this.upperheightEins = this.umlaufplanEins.getUmlauf()
							.size() * 40 + 10;
					createUpperYScale(yUp1, upperYChart1, upperYgc1,
							this.upperheightEins, this.umlaufplanEins);
					this.UPlanValue1.setText(UPlan.getSelectionModel()
							.getSelectedItem().toString());
					this.umlaufChoice = 1;

					// Zuordnung der Auswahl

					this.umlaufIsCurrent = true;
					this.dienstIsCurrent = false;

					graphicTransition();
				}
				// Labelbeschriftungen fÃ¼r UmlaufplÃ¤ne
				this.UPlan1.setVisible(true);
				this.loeschenUmlaufplan.setVisible(true);

				this.UPlanValue1.setVisible(true);
				this.UPlanValue1
						.setOnMousePressed(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								umlaufChoice = 1;
								changeUplanDetails();
								selectionModel.select(Plan1);

								// Zuordnung der Auswahl

								umlaufIsCurrent = true;
								dienstIsCurrent = false;
							}
						});

				// Zur Kontrolle ob es sich um einen Buttonklick handelt oder
				// nicht

				if (this.addButtonPressed == true) {
					selectionModel.select(this.Plan1);
					this.umlaufTabCounter = this.umlaufTabCounter + 1;
				}

				// Zur Verhinderung vom doppelten Erschaffen der Grafiken

				if (this.firstUppergrafikErstellt == false) {
					this.umlaufTabCounter = 0;
				}
				this.firstUppergrafikErstellt = true;
				// Hintergrunderstellung
				createUpperBackgroundGraphicFirstPane();
				createUpperXScalePane(this.xUp1, this.upperGraphicPane1);
				createUmlaufElementGraphic(this.umlaufplanEins,
						this.upperGraphicPane1, this.upperChart1, this.uppergc1);
				// FÃ¼gt den EventhÃ¤ndler hinzu
				this.upperChart1.addEventHandler(MouseEvent.MOUSE_CLICKED,
						new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								boolean okClicked = mainApp
										.showEditUPlan(umlaufplanEins);
								if (okClicked) {
									refreshBothGraphics();
									fillUmlaufplanliste();
								}
							}
						});

			}

			//
			// Erstellung der Umlaufplangrafik auf dem zweiten Tab
			//

			if (this.umlaufTabCounter >= 1) {

				if (this.secondUppergrafikErstellt == true) {
					this.uppergc2.clearRect(0, 0, this.upperChart2.getWidth(),
							this.upperChart2.getHeight());
				}
				// Initiale Belegung der Grafik
				if (this.UPlan2.isVisible() == false) {
					this.umlaufplanZwei = this.umlaufplanliste.get(this.UPlan
							.getSelectionModel().getSelectedIndex());
					this.upperheightZwei = this.umlaufplanZwei.getUmlauf()
							.size() * 40 + 10;
					createUpperYScale(yUp2, upperYChart2, upperYgc2,
							this.upperheightZwei, this.umlaufplanZwei);
					this.UPlanValue2.setText(UPlan.getSelectionModel()
							.getSelectedItem().toString());
					this.umlaufChoice = 2;

					// Zuordnung der Auswahl

					umlaufIsCurrent = true;
					dienstIsCurrent = false;
				}
				// Labelbeschriftungen fÃ¼r UmlaufplÃ¤ne und Enabling des Tabs

				this.Plan2.setDisable(false);
				this.UPlan2.setVisible(true);
				this.UPlanValue2.setVisible(true);
				this.UPlanValue2
						.setOnMousePressed(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								umlaufChoice = 2;
								changeUplanDetails();
								selectionModel.select(Plan2);

								// Zuordnung der Auswahl

								umlaufIsCurrent = true;
								dienstIsCurrent = false;
							}
						});

				// Zur Kontrolle ob es sich um einen Buttonklick handelt oder
				// nicht

				if (this.addButtonPressed == true) {
					selectionModel.select(this.Plan2);
					this.umlaufTabCounter = 2;
				}

				// Zur Verhinderung vom doppelten Erschaffen der Grafiken

				if (this.secondUppergrafikErstellt == false) {
					this.umlaufTabCounter = 1;
				}

				this.secondUppergrafikErstellt = true;
				this.secondGrafikErstellt = true;

				// Hintergrunderstellung
				createUpperBackgroundGraphicSecondPane();
				createUpperXScalePane(this.xUp2, this.upperGraphicPane2);

				createUmlaufElementGraphic(this.umlaufplanZwei,
						this.upperGraphicPane2, this.upperChart2, this.uppergc2);
				// FÃ¼gt den EventhÃ¤ndler hinzu
				this.upperChart2.addEventHandler(MouseEvent.MOUSE_CLICKED,
						new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								boolean okClicked = mainApp
										.showEditUPlan(umlaufplanZwei);
								if (okClicked) {
									refreshBothGraphics();
									fillUmlaufplanliste();
								}
							}
						});
			}

			//
			// Erstellung der Umlaufplangrafik auf dem dritten Tab
			//

			if (this.umlaufTabCounter >= 2) {

				if (this.thirdUppergrafikErstellt == true) {
					this.uppergc3.clearRect(0, 0, this.upperChart3.getWidth(),
							this.upperChart3.getHeight());
				}
				// Initiale Belegung der Grafik
				if (this.UPlan3.isVisible() == false) {
					this.umlaufplanDrei = this.umlaufplanliste.get(this.UPlan
							.getSelectionModel().getSelectedIndex());
					this.upperheightDrei = this.umlaufplanDrei.getUmlauf()
							.size() * 40 + 10;
					createUpperYScale(yUp3, upperYChart3, upperYgc3,
							this.upperheightDrei, this.umlaufplanDrei);
					this.UPlanValue3.setText(UPlan.getSelectionModel()
							.getSelectedItem().toString());
					this.umlaufChoice = 3;

					// Zuordnung der Auswahl

					umlaufIsCurrent = true;
					dienstIsCurrent = false;
				}
				// Labelbeschriftungen fÃ¼r UmlaufplÃ¤ne und Enabling des Tabs

				this.Plan3.setDisable(false);
				this.UPlan3.setVisible(true);
				this.UPlanValue3.setVisible(true);
				this.UPlanValue3
						.setOnMousePressed(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								umlaufChoice = 3;
								changeUplanDetails();
								selectionModel.select(Plan3);

								// Zuordnung der Auswahl

								umlaufIsCurrent = true;
								dienstIsCurrent = false;
							}
						});
				// Zur Kontrolle ob es sich um einen Buttonklick handelt oder
				// nicht

				if (this.addButtonPressed == true) {
					selectionModel.select(this.Plan3);
					this.umlaufTabCounter = 3;
				}

				// Zur Verhinderung vom doppelten Erschaffen der Grafiken

				if (this.thirdUppergrafikErstellt == false) {
					this.umlaufTabCounter = 2;
				}

				this.thirdUppergrafikErstellt = true;
				this.thirdGrafikErstellt = true;

				// Hintergrunderstellung
				createUpperBackgroundGraphicThirdPane();
				createUpperXScalePane(this.xUp3, this.upperGraphicPane3);

				createUmlaufElementGraphic(this.umlaufplanDrei,
						this.upperGraphicPane3, this.upperChart3, this.uppergc3);
				// FÃ¼gt den EventhÃ¤ndler hinzu
				this.upperChart3.addEventHandler(MouseEvent.MOUSE_CLICKED,
						new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								boolean okClicked = mainApp
										.showEditUPlan(umlaufplanDrei);
								if (okClicked) {
									refreshBothGraphics();
									fillUmlaufplanliste();
								}
							}
						});
			}

			//
			// Erstellung der Umlaufplangrafik auf dem vierten Tab
			//

			if (this.umlaufTabCounter >= 3) {

				if (this.fourthUppergrafikErstellt == true) {
					this.uppergc4.clearRect(0, 0, this.upperChart4.getWidth(),
							this.upperChart4.getHeight());
				}
				// Initiale Belegung der Grafik
				if (this.UPlan4.isVisible() == false) {
					this.umlaufplanVier = this.umlaufplanliste.get(this.UPlan
							.getSelectionModel().getSelectedIndex());
					this.upperheightVier = this.umlaufplanVier.getUmlauf()
							.size() * 40 + 10;
					createUpperYScale(yUp4, upperYChart4, upperYgc4,
							this.upperheightVier, this.umlaufplanVier);
					this.UPlanValue4.setText(UPlan.getSelectionModel()
							.getSelectedItem().toString());
					this.umlaufChoice = 4;

					// Zuordnung der Auswahl

					umlaufIsCurrent = true;
					dienstIsCurrent = false;
				}
				// Labelbeschriftungen fÃ¼r UmlaufplÃ¤ne und Enabling des Tabs

				this.Plan4.setDisable(false);
				this.UPlan4.setVisible(true);
				this.UPlanValue4.setVisible(true);
				this.UPlanValue4
						.setOnMousePressed(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								umlaufChoice = 4;
								changeUplanDetails();
								selectionModel.select(Plan4);

								// Zuordnung der Auswahl

								umlaufIsCurrent = true;
								dienstIsCurrent = false;
							}
						});
				// Zur Kontrolle ob es sich um einen Buttonklick handelt oder
				// nicht

				if (this.addButtonPressed == true) {
					selectionModel.select(this.Plan4);
					this.umlaufTabCounter = 4;
				}

				// Zur Verhinderung vom doppelten Erschaffen der Grafiken

				if (this.fourthUppergrafikErstellt == false) {
					this.umlaufTabCounter = 3;
				}

				this.fourthUppergrafikErstellt = true;
				this.fourthGrafikErstellt = true;

				// Hintergrunderstellung
				createUpperBackgroundGraphicFourthPane();
				createUpperXScalePane(this.xUp4, this.upperGraphicPane4);

				createUmlaufElementGraphic(this.umlaufplanVier,
						this.upperGraphicPane4, this.upperChart4, this.uppergc4);
				// FÃ¼gt den EventhÃ¤ndler hinzu
				this.upperChart4.addEventHandler(MouseEvent.MOUSE_CLICKED,
						new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								boolean okClicked = mainApp
										.showEditUPlan(umlaufplanVier);
								if (okClicked) {
									refreshBothGraphics();
									fillUmlaufplanliste();
								}
							}
						});
			}

			//
			// Erstellung der Umlaufplangrafik auf dem fÃ¼nften Tab
			//

			if (this.umlaufTabCounter >= 4) {

				if (this.fifthUppergrafikErstellt == true) {
					this.uppergc5.clearRect(0, 0, this.upperChart5.getWidth(),
							this.upperChart5.getHeight());
				}
				// Initiale Belegung der Grafik
				if (this.UPlan5.isVisible() == false) {
					this.umlaufplanFuenf = this.umlaufplanliste.get(this.UPlan
							.getSelectionModel().getSelectedIndex());
					this.upperheightFuenf = this.umlaufplanFuenf.getUmlauf()
							.size() * 40 + 10;
					createUpperYScale(yUp5, upperYChart5, upperYgc5,
							this.upperheightFuenf, this.umlaufplanFuenf);
					this.UPlanValue5.setText(UPlan.getSelectionModel()
							.getSelectedItem().toString());
					this.umlaufChoice = 5;

					// Zuordnung der Auswahl

					umlaufIsCurrent = true;
					dienstIsCurrent = false;
				}
				// Labelbeschriftungen fÃ¼r UmlaufplÃ¤ne und Enabling des Tabs

				this.Plan5.setDisable(false);
				this.UPlan5.setVisible(true);
				this.UPlanValue5.setVisible(true);
				this.UPlanValue5
						.setOnMousePressed(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								umlaufChoice = 5;
								changeUplanDetails();
								selectionModel.select(Plan5);

								// Zuordnung der Auswahl

								umlaufIsCurrent = true;
								dienstIsCurrent = false;
							}
						});
				// Zur Kontrolle ob es sich um einen Buttonklick handelt oder
				// nicht

				if (this.addButtonPressed == true) {
					selectionModel.select(this.Plan5);
					this.umlaufTabCounter = 5;
				}

				// Zur Verhinderung vom doppelten Erschaffen der Grafiken

				if (this.fifthUppergrafikErstellt == false) {
					this.umlaufTabCounter = 4;
				}

				this.fifthUppergrafikErstellt = true;
				this.fifthGrafikErstellt = true;

				// Hintergrunderstellung
				createUpperBackgroundGraphicFifthPane();
				createUpperXScalePane(this.xUp5, this.upperGraphicPane5);

				createUmlaufElementGraphic(this.umlaufplanFuenf,
						this.upperGraphicPane5, this.upperChart5, this.uppergc5);
				// FÃ¼gt den EventhÃ¤ndler hinzu
				this.upperChart5.addEventHandler(MouseEvent.MOUSE_CLICKED,
						new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								boolean okClicked = mainApp
										.showEditUPlan(umlaufplanFuenf);
								if (okClicked) {
									refreshBothGraphics();
									fillUmlaufplanliste();
								}
							}
						});
			}

			//
			// Erstellung der Umlaufplangrafik auf dem sechsten Tab
			//

			if (this.umlaufTabCounter >= 5) {

				if (this.sixthUppergrafikErstellt == true) {
					this.uppergc6.clearRect(0, 0, this.upperChart6.getWidth(),
							this.upperChart6.getHeight());
				}
				// Initiale Belegung der Grafik
				if (this.UPlan6.isVisible() == false) {
					this.umlaufplanSechs = this.umlaufplanliste.get(this.UPlan
							.getSelectionModel().getSelectedIndex());
					this.upperheightSechs = this.umlaufplanSechs.getUmlauf()
							.size() * 40 + 10;
					createUpperYScale(yUp6, upperYChart6, upperYgc6,
							this.upperheightSechs, this.umlaufplanSechs);
					this.UPlanValue6.setText(UPlan.getSelectionModel()
							.getSelectedItem().toString());
					this.umlaufChoice = 6;

					// Zuordnung der Auswahl

					umlaufIsCurrent = true;
					dienstIsCurrent = false;
				}
				// Labelbeschriftungen fÃ¼r UmlaufplÃ¤ne und Enabling des Tabs

				this.Plan6.setDisable(false);
				this.UPlan6.setVisible(true);
				this.UPlanValue6.setVisible(true);
				this.UPlanValue6
						.setOnMousePressed(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								umlaufChoice = 6;
								changeUplanDetails();
								selectionModel.select(Plan6);

								// Zuordnung der Auswahl

								umlaufIsCurrent = true;
								dienstIsCurrent = false;
							}
						});
				// Zur Kontrolle ob es sich um einen Buttonklick handelt oder
				// nicht

				if (this.addButtonPressed == true) {
					selectionModel.select(this.Plan6);
					this.umlaufTabCounter = 6;
				}

				// Zur Verhinderung vom doppelten Erschaffen der Grafiken

				if (this.sixthUppergrafikErstellt == false) {
					this.umlaufTabCounter = 5;
				}

				this.sixthUppergrafikErstellt = true;
				this.sixthGrafikErstellt = true;

				// Hintergrunderstellung
				createUpperBackgroundGraphicSixthPane();
				createUpperXScalePane(this.xUp6, this.upperGraphicPane6);
				createUmlaufElementGraphic(this.umlaufplanSechs,
						this.upperGraphicPane6, this.upperChart6, this.uppergc6);
				// FÃ¼gt den EventhÃ¤ndler hinzu
				this.upperChart6.addEventHandler(MouseEvent.MOUSE_CLICKED,
						new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								boolean okClicked = mainApp
										.showEditUPlan(umlaufplanSechs);
								if (okClicked) {
									refreshBothGraphics();
									fillUmlaufplanliste();
								}
							}
						});
			}

			//
			// Erstellung der Umlaufplangrafik auf dem siebten Tab
			//

			if (this.umlaufTabCounter >= 6) {

				if (this.seventhUppergrafikErstellt == true) {
					this.uppergc7.clearRect(0, 0, this.upperChart7.getWidth(),
							this.upperChart7.getHeight());
				}
				// Initiale Belegung der Grafik
				if (this.UPlan7.isVisible() == false) {
					this.umlaufplanSieben = this.umlaufplanliste.get(this.UPlan
							.getSelectionModel().getSelectedIndex());
					this.upperheightSieben = this.umlaufplanSieben.getUmlauf()
							.size() * 40 + 10;
					createUpperYScale(yUp7, upperYChart7, upperYgc7,
							this.upperheightSieben, this.umlaufplanSieben);
					this.UPlanValue7.setText(UPlan.getSelectionModel()
							.getSelectedItem().toString());
					this.umlaufChoice = 7;

					// Zuordnung der Auswahl

					umlaufIsCurrent = true;
					dienstIsCurrent = false;
				}
				// Labelbeschriftungen fÃ¼r UmlaufplÃ¤ne und Enabling des Tabs

				this.Plan7.setDisable(false);
				this.UPlan7.setVisible(true);
				this.UPlanValue7.setVisible(true);
				this.UPlanValue7
						.setOnMousePressed(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								umlaufChoice = 7;
								changeUplanDetails();
								selectionModel.select(Plan7);

								// Zuordnung der Auswahl

								umlaufIsCurrent = true;
								dienstIsCurrent = false;
							}
						});
				// Zur Kontrolle ob es sich um einen Buttonklick handelt oder
				// nicht

				if (this.addButtonPressed == true) {
					selectionModel.select(this.Plan7);
					this.umlaufTabCounter = 7;
				}

				// Zur Verhinderung vom doppelten Erschaffen der Grafiken

				if (this.seventhUppergrafikErstellt == false) {
					this.umlaufTabCounter = 6;
				}

				this.seventhUppergrafikErstellt = true;
				this.seventhGrafikErstellt = true;

				// Hintergrunderstellung
				createUpperBackgroundGraphicSeventhPane();
				createUpperXScalePane(this.xUp7, this.upperGraphicPane7);

				createUmlaufElementGraphic(this.umlaufplanSieben,
						this.upperGraphicPane7, this.upperChart7, this.uppergc7);
				// FÃ¼gt den EventhÃ¤ndler hinzu
				this.upperChart7.addEventHandler(MouseEvent.MOUSE_CLICKED,
						new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								boolean okClicked = mainApp
										.showEditUPlan(umlaufplanSieben);
								if (okClicked) {
									refreshBothGraphics();
									fillUmlaufplanliste();
								}
							}
						});
			}

			this.addButtonPressed = true;
			if (this.umlaufIsCurrent == true) {
				changeUplanDetails();
			}
		}
	}

	/**
	 * Creates The Lower Graphic.
	 */
	@FXML
	private void createLowerGraphic() {

		// Clearen der X-Scales

		if (firstLowergrafikErstellt == true) {

			this.xLow1.getChildren().clear();
			if (secondLowergrafikErstellt == true) {
				this.xLow2.getChildren().clear();
			}
			if (thirdLowergrafikErstellt == true) {
				this.xLow3.getChildren().clear();
			}
			if (fourthLowergrafikErstellt == true) {
				this.xLow4.getChildren().clear();
			}
			if (fifthLowergrafikErstellt == true) {
				this.xLow5.getChildren().clear();
			}
			if (sixthLowergrafikErstellt == true) {
				this.xLow6.getChildren().clear();
			}
			if (seventhLowergrafikErstellt == true) {
				this.xLow7.getChildren().clear();
			}
		}

		this.lowerDetailsPane.setMaxHeight(lowerDetailsPane.getHeight());
		this.lowerDetailsPane.setMinHeight(lowerDetailsPane.getHeight());

		final SingleSelectionModel<Tab> selectionModel = Planpane
				.getSelectionModel();

		if (DPlan.getSelectionModel().getSelectedItem() != null) {

			// Erstellung der Dienstplangrafik auf dem ersten Tab

			if (this.dienstTabCounter >= 0) {

				if (this.DPlan1.isVisible() == false) {
					this.dienstplanEins = this.dienstplanliste.get(this.DPlan
							.getSelectionModel().getSelectedIndex());
					this.lowerheightEins = this.dienstplanEins.getDuty().size() * 40 + 10;
					createLowerYScale(yLow1, lowerYChart1, lowerYgc1,
							this.lowerheightEins, this.dienstplanEins);
					this.DPlanValue1.setText(DPlan.getSelectionModel()
							.getSelectedItem().toString());
					this.dienstChoice = 1;

					// Zuordnung der Auswahl

					this.umlaufIsCurrent = false;
					this.dienstIsCurrent = true;

					graphicTransition();
				}
				// Labelbeschriftungen fÃ¼r UmlaufplÃ¤ne
				this.DPlan1.setVisible(true);
				this.loeschenDienstplan.setVisible(true);

				this.DPlanValue1.setVisible(true);
				this.DPlanValue1
						.setOnMousePressed(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								dienstChoice = 1;
								changeDplanDetails();
								selectionModel.select(Plan1);

								// Zuordnung der Auswahl

								umlaufIsCurrent = false;
								dienstIsCurrent = true;
							}
						});

				// Zur Kontrolle ob es sich um einen Buttonklick handelt oder
				// nicht

				if (this.addButtonPressed == true) {
					selectionModel.select(this.Plan1);
					this.dienstTabCounter = this.dienstTabCounter + 1;
				}

				// Zur Verhinderung vom doppelten Erschaffen der Grafiken

				if (this.firstLowergrafikErstellt == false) {
					this.dienstTabCounter = 0;
				}
				this.firstLowergrafikErstellt = true;

				// Hintergrunderstellung
				createLowerBackgroundGraphicFirstPane();
				createLowerXScalePane(this.xLow1, this.lowerGraphicPane1);
				createDienstElementGraphic(this.dienstplanEins,
						this.lowerGraphicPane1, this.lowerChart1, this.lowergc1);

				// Fügt den EventhÃ¤ndler hinzu
				this.lowerChart1.addEventHandler(MouseEvent.MOUSE_CLICKED,
						new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								boolean okClicked = mainApp
										.showEditDPlan(dienstplanEins);
								if (okClicked) {
									refreshBothGraphics();
									fillDienstplanliste();
								}
							}
						});

			}

			//
			// Erstellung der Dienstplangrafik auf dem zweiten Tab
			//

			if (this.dienstTabCounter >= 1) {

				if (this.secondLowergrafikErstellt == true) {
					this.lowergc2.clearRect(0, 0, this.lowerChart2.getWidth(),
							this.lowerChart2.getHeight());
				}
				// Initiale Belegung der Grafik
				if (this.DPlan2.isVisible() == false) {
					this.dienstplanZwei = this.dienstplanliste.get(this.DPlan
							.getSelectionModel().getSelectedIndex());
					this.lowerheightZwei = this.dienstplanZwei.getDuty().size() * 40 + 10;
					createLowerYScale(yLow2, lowerYChart2, lowerYgc2,
							this.lowerheightZwei, this.dienstplanZwei);
					this.DPlanValue2.setText(DPlan.getSelectionModel()
							.getSelectedItem().toString());
					this.dienstChoice = 2;

					this.umlaufIsCurrent = false;
					this.dienstIsCurrent = true;
				}
				// Labelbeschriftungen fÃ¼r UmlaufplÃ¤ne und Enabling des Tabs

				this.Plan2.setDisable(false);
				this.DPlan2.setVisible(true);
				this.DPlanValue2.setVisible(true);
				this.DPlanValue2
						.setOnMousePressed(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								dienstChoice = 2;
								changeDplanDetails();
								selectionModel.select(Plan2);

								// Zuordnung der Auswahl

								umlaufIsCurrent = false;
								dienstIsCurrent = true;
							}
						});

				// Zur Kontrolle ob es sich um einen Buttonklick handelt oder
				// nicht

				if (this.addButtonPressed == true) {
					selectionModel.select(this.Plan2);
					this.dienstTabCounter = 2;
				}

				// Zur Verhinderung vom doppelten Erschaffen der Grafiken

				if (this.secondLowergrafikErstellt == false) {
					this.dienstTabCounter = 1;
				}

				this.secondLowergrafikErstellt = true;
				this.secondGrafikErstellt = true;

				// Hintergrunderstellung
				createLowerBackgroundGraphicSecondPane();
				createLowerXScalePane(this.xLow2, this.lowerGraphicPane2);

				createDienstElementGraphic(this.dienstplanZwei,
						this.lowerGraphicPane2, this.lowerChart2, this.lowergc2);
				// // FÃ¼gt den EventhÃ¤ndler hinzu
				// this.upperChart2.addEventHandler(MouseEvent.MOUSE_CLICKED,
				// new EventHandler<MouseEvent>() {
				// @Override
				// public void handle(MouseEvent e) {
				// boolean okClicked = mainApp
				// .showEditUPlan(umlaufplanZwei);
				// if (okClicked) {
				// refreshBothGraphics();
				// fillUmlaufplanliste();
				// }
				// }
				// });
			}

			// Erstellung der Umlaufplangrafik auf dem dritten Tab

			if (this.dienstTabCounter >= 2) {

				if (this.thirdLowergrafikErstellt == true) {
					this.lowergc3.clearRect(0, 0, this.lowerChart3.getWidth(),
							this.lowerChart3.getHeight());
				}
				// Initiale Belegung der Grafik
				if (this.DPlan3.isVisible() == false) {
					this.dienstplanDrei = this.dienstplanliste.get(this.DPlan
							.getSelectionModel().getSelectedIndex());
					this.lowerheightDrei = this.dienstplanDrei.getDuty().size() * 40 + 10;
					createLowerYScale(yLow3, lowerYChart3, lowerYgc3,
							this.lowerheightDrei, this.dienstplanDrei);
					this.DPlanValue3.setText(DPlan.getSelectionModel()
							.getSelectedItem().toString());
					this.dienstChoice = 3;

					this.umlaufIsCurrent = false;
					this.dienstIsCurrent = true;
				}
				// Labelbeschriftungen fÃ¼r UmlaufplÃ¤ne und Enabling des Tabs

				this.Plan3.setDisable(false);
				this.DPlan3.setVisible(true);
				this.DPlanValue3.setVisible(true);
				this.DPlanValue3
						.setOnMousePressed(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								dienstChoice = 3;
								changeDplanDetails();
								selectionModel.select(Plan3);

								// Zuordnung der Auswahl

								umlaufIsCurrent = false;
								dienstIsCurrent = true;
							}
						});
				// Zur Kontrolle ob es sich um einen Buttonklick handelt oder
				// nicht

				if (this.addButtonPressed == true) {
					selectionModel.select(this.Plan3);
					this.dienstTabCounter = 3;
				}

				// Zur Verhinderung vom doppelten Erschaffen der Grafiken

				if (this.thirdLowergrafikErstellt == false) {
					this.dienstTabCounter = 2;
				}

				this.thirdLowergrafikErstellt = true;
				this.thirdGrafikErstellt = true;

				// Hintergrunderstellung
				createLowerBackgroundGraphicThirdPane();
				createLowerXScalePane(this.xLow3, this.lowerGraphicPane3);

				createDienstElementGraphic(this.dienstplanDrei,
						this.lowerGraphicPane3, this.lowerChart3, this.lowergc3);
				// // FÃ¼gt den EventhÃ¤ndler hinzu
				// this.upperChart3.addEventHandler(MouseEvent.MOUSE_CLICKED,
				// new EventHandler<MouseEvent>() {
				// @Override
				// public void handle(MouseEvent e) {
				// boolean okClicked = mainApp
				// .showEditUPlan(umlaufplanDrei);
				// if (okClicked) {
				// refreshBothGraphics();
				// fillUmlaufplanliste();
				// }
				// }
				// });
			}

			// // Erstellung der Umlaufplangrafik auf dem vierten Tab

			if (this.dienstTabCounter >= 3) {

				if (this.fourthLowergrafikErstellt == true) {
					this.lowergc4.clearRect(0, 0, this.lowerChart4.getWidth(),
							this.lowerChart4.getHeight());
				}
				// Initiale Belegung der Grafik
				if (this.DPlan4.isVisible() == false) {
					this.dienstplanVier = this.dienstplanliste.get(this.DPlan
							.getSelectionModel().getSelectedIndex());
					this.lowerheightVier = this.dienstplanVier.getDuty().size() * 40 + 10;
					createLowerYScale(yLow4, lowerYChart4, lowerYgc4,
							this.lowerheightVier, this.dienstplanVier);
					this.DPlanValue4.setText(DPlan.getSelectionModel()
							.getSelectedItem().toString());
					this.dienstChoice = 4;

					this.umlaufIsCurrent = false;
					this.dienstIsCurrent = true;
				}
				// Labelbeschriftungen fÃ¼r UmlaufplÃ¤ne und Enabling des Tabs

				this.Plan4.setDisable(false);
				this.DPlan4.setVisible(true);
				this.DPlanValue4.setVisible(true);
				this.DPlanValue4
						.setOnMousePressed(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								dienstChoice = 4;
								changeDplanDetails();
								selectionModel.select(Plan4);

								// Zuordnung der Auswahl

								umlaufIsCurrent = false;
								dienstIsCurrent = true;
							}
						});
				// Zur Kontrolle ob es sich um einen Buttonklick handelt oder
				// nicht

				if (this.addButtonPressed == true) {
					selectionModel.select(this.Plan4);
					this.dienstTabCounter = 4;
				}

				// Zur Verhinderung vom doppelten Erschaffen der Grafiken

				if (this.fourthLowergrafikErstellt == false) {
					this.dienstTabCounter = 3;
				}

				this.fourthLowergrafikErstellt = true;
				this.fourthGrafikErstellt = true;

				// Hintergrunderstellung
				createLowerBackgroundGraphicFourthPane();
				createLowerXScalePane(this.xLow4, this.lowerGraphicPane4);

				createDienstElementGraphic(this.dienstplanVier,
						this.lowerGraphicPane4, this.lowerChart4, this.lowergc4);
				// // FÃ¼gt den EventhÃ¤ndler hinzu
				// this.upperChart4.addEventHandler(MouseEvent.MOUSE_CLICKED,
				// new EventHandler<MouseEvent>() {
				// @Override
				// public void handle(MouseEvent e) {
				// boolean okClicked = mainApp
				// .showEditUPlan(umlaufplanVier);
				// if (okClicked) {
				// refreshBothGraphics();
				// fillUmlaufplanliste();
				// }
				// }
				// });
			}

			//
			// Erstellung der Umlaufplangrafik auf dem fÃ¼nften Tab
			//

			if (this.dienstTabCounter >= 4) {

				if (this.fifthLowergrafikErstellt == true) {
					this.lowergc5.clearRect(0, 0, this.lowerChart5.getWidth(),
							this.lowerChart5.getHeight());
				}
				// Initiale Belegung der Grafik
				if (this.DPlan5.isVisible() == false) {
					this.dienstplanFuenf = this.dienstplanliste.get(this.DPlan
							.getSelectionModel().getSelectedIndex());
					this.lowerheightFuenf = this.dienstplanFuenf.getDuty()
							.size() * 40 + 10;
					createLowerYScale(yLow5, lowerYChart5, lowerYgc5,
							this.lowerheightFuenf, this.dienstplanFuenf);
					this.DPlanValue5.setText(DPlan.getSelectionModel()
							.getSelectedItem().toString());
					this.dienstChoice = 5;

					this.umlaufIsCurrent = false;
					this.dienstIsCurrent = true;
				}
				// Labelbeschriftungen fÃ¼r UmlaufplÃ¤ne und Enabling des Tabs

				this.Plan5.setDisable(false);
				this.DPlan5.setVisible(true);
				this.DPlanValue5.setVisible(true);
				this.DPlanValue5
						.setOnMousePressed(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								dienstChoice = 5;
								changeDplanDetails();
								selectionModel.select(Plan5);

								// Zuordnung der Auswahl

								umlaufIsCurrent = false;
								dienstIsCurrent = true;
							}
						});
				// Zur Kontrolle ob es sich um einen Buttonklick handelt oder
				// nicht

				if (this.addButtonPressed == true) {
					selectionModel.select(this.Plan5);
					this.dienstTabCounter = 5;
				}

				// Zur Verhinderung vom doppelten Erschaffen der Grafiken

				if (this.fifthLowergrafikErstellt == false) {
					this.dienstTabCounter = 4;
				}

				this.fifthLowergrafikErstellt = true;
				this.fifthGrafikErstellt = true;

				// Hintergrunderstellung
				createLowerBackgroundGraphicFifthPane();
				createLowerXScalePane(this.xLow5, this.lowerGraphicPane5);

				createDienstElementGraphic(this.dienstplanFuenf,
						this.lowerGraphicPane5, this.lowerChart5, this.lowergc5);
				// // FÃ¼gt den EventhÃ¤ndler hinzu
				// this.upperChart5.addEventHandler(MouseEvent.MOUSE_CLICKED,
				// new EventHandler<MouseEvent>() {
				// @Override
				// public void handle(MouseEvent e) {
				// boolean okClicked = mainApp
				// .showEditUPlan(umlaufplanFuenf);
				// if (okClicked) {
				// refreshBothGraphics();
				// fillUmlaufplanliste();
				// }
				// }
				// });
			}

			//
			// Erstellung der Umlaufplangrafik auf dem sechsten Tab
			//

			if (this.dienstTabCounter >= 5) {

				if (this.sixthLowergrafikErstellt == true) {
					this.lowergc6.clearRect(0, 0, this.lowerChart6.getWidth(),
							this.lowerChart6.getHeight());
				}
				// Initiale Belegung der Grafik
				if (this.DPlan6.isVisible() == false) {
					this.dienstplanSechs = this.dienstplanliste.get(this.DPlan
							.getSelectionModel().getSelectedIndex());
					this.lowerheightSechs = this.dienstplanSechs.getDuty()
							.size() * 40 + 10;
					createLowerYScale(yLow6, lowerYChart6, lowerYgc6,
							this.lowerheightSechs, this.dienstplanSechs);
					this.DPlanValue6.setText(DPlan.getSelectionModel()
							.getSelectedItem().toString());
					this.dienstChoice = 6;

					this.umlaufIsCurrent = false;
					this.dienstIsCurrent = true;
				}
				// Labelbeschriftungen fÃ¼r UmlaufplÃ¤ne und Enabling des Tabs

				this.Plan6.setDisable(false);
				this.DPlan6.setVisible(true);
				this.DPlanValue6.setVisible(true);
				this.DPlanValue6
						.setOnMousePressed(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								dienstChoice = 6;
								changeDplanDetails();
								selectionModel.select(Plan6);

								// Zuordnung der Auswahl

								umlaufIsCurrent = false;
								dienstIsCurrent = true;
							}
						});
				// Zur Kontrolle ob es sich um einen Buttonklick handelt oder
				// nicht

				if (this.addButtonPressed == true) {
					selectionModel.select(this.Plan6);
					this.dienstTabCounter = 6;
				}

				// Zur Verhinderung vom doppelten Erschaffen der Grafiken

				if (this.sixthLowergrafikErstellt == false) {
					this.dienstTabCounter = 5;
				}

				this.sixthLowergrafikErstellt = true;
				this.sixthGrafikErstellt = true;

				// Hintergrunderstellung
				createLowerBackgroundGraphicSixthPane();
				createLowerXScalePane(this.xLow6, this.lowerGraphicPane6);
				createDienstElementGraphic(this.dienstplanSechs,
						this.lowerGraphicPane6, this.lowerChart6, this.lowergc6);
				// // FÃ¼gt den EventhÃ¤ndler hinzu
				// this.upperChart6.addEventHandler(MouseEvent.MOUSE_CLICKED,
				// new EventHandler<MouseEvent>() {
				// @Override
				// public void handle(MouseEvent e) {
				// boolean okClicked = mainApp
				// .showEditUPlan(umlaufplanSechs);
				// if (okClicked) {
				// refreshBothGraphics();
				// fillUmlaufplanliste();
				// }
				// }
				// });
			}

			//
			// Erstellung der Umlaufplangrafik auf dem siebten Tab
			//

			if (this.dienstTabCounter >= 6) {

				if (this.seventhLowergrafikErstellt == true) {
					this.lowergc7.clearRect(0, 0, this.lowerChart7.getWidth(),
							this.lowerChart7.getHeight());
				}
				// Initiale Belegung der Grafik
				if (this.DPlan7.isVisible() == false) {
					this.dienstplanSieben = this.dienstplanliste.get(this.DPlan
							.getSelectionModel().getSelectedIndex());
					this.lowerheightSieben = this.dienstplanSieben.getDuty()
							.size() * 40 + 10;
					createLowerYScale(yLow7, lowerYChart7, lowerYgc7,
							this.lowerheightSieben, this.dienstplanSieben);
					this.DPlanValue7.setText(DPlan.getSelectionModel()
							.getSelectedItem().toString());
					this.dienstChoice = 7;

					this.umlaufIsCurrent = false;
					this.dienstIsCurrent = true;
				}
				// Labelbeschriftungen fÃ¼r UmlaufplÃ¤ne und Enabling des Tabs

				this.Plan7.setDisable(false);
				this.DPlan7.setVisible(true);
				this.DPlanValue7.setVisible(true);
				this.DPlanValue7
						.setOnMousePressed(new EventHandler<MouseEvent>() {
							@Override
							public void handle(MouseEvent e) {
								dienstChoice = 7;
								changeDplanDetails();
								selectionModel.select(Plan7);

								// Zuordnung der Auswahl

								umlaufIsCurrent = false;
								dienstIsCurrent = true;
							}
						});
				// Zur Kontrolle ob es sich um einen Buttonklick handelt oder
				// nicht

				if (this.addButtonPressed == true) {
					selectionModel.select(this.Plan7);
					this.dienstTabCounter = 7;
				}

				// Zur Verhinderung vom doppelten Erschaffen der Grafiken

				if (this.seventhLowergrafikErstellt == false) {
					this.dienstTabCounter = 6;
				}

				this.seventhLowergrafikErstellt = true;
				this.seventhGrafikErstellt = true;

				// Hintergrunderstellung
				createLowerBackgroundGraphicSeventhPane();
				createLowerXScalePane(this.xLow7, this.lowerGraphicPane7);

				createDienstElementGraphic(this.dienstplanSieben,
						this.lowerGraphicPane7, this.lowerChart7, this.lowergc7);
				// // FÃ¼gt den EventhÃ¤ndler hinzu
				// this.upperChart7.addEventHandler(MouseEvent.MOUSE_CLICKED,
				// new EventHandler<MouseEvent>() {
				// @Override
				// public void handle(MouseEvent e) {
				// boolean okClicked = mainApp
				// .showEditUPlan(umlaufplanSieben);
				// if (okClicked) {
				// refreshBothGraphics();
				// fillUmlaufplanliste();
				// }
				// }
				// });
			}

			this.addButtonPressed = true;
			if (this.dienstIsCurrent == true) {
				changeDplanDetails();
			}

		}
	}

	/**
	 * Creates The First Upper Background Graphic.
	 */
	@FXML
	private void createUpperBackgroundGraphicFirstPane() {

		// Initialize the Chart
		this.upperChart1 = new Canvas(this.upperGraphicPane1.getWidth() - 4,
				this.upperheightEins);

		// Erstellen des HintergrundgrafikKontextes
		this.uppergc1 = this.upperChart1.getGraphicsContext2D();
		this.uppergc1.clearRect(0, 0, this.upperChart1.getWidth(),
				this.upperChart1.getHeight());

		// Erstellen des Hintergrundes
		this.uppergc1.setFill(Color.BEIGE);
		this.uppergc1.fillRect(0, 0, this.upperChart1.getWidth(),
				this.upperChart1.getHeight());

		this.uppergc1.setLineWidth(3);
		this.uppergc1.setStroke(Color.BLACK);
		this.uppergc1.strokeLine(1, 0, 1, this.upperChart1.getHeight());

		double abstandNetz = (this.upperChart1.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);
		this.uppergc1.setLineWidth(1);
		this.uppergc1.setFont(Font.getDefault());
		this.uppergc1.setFill(Color.BLACK);
		this.uppergc1.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for (int i = 0; i <= (endzeitVar - startzeitVar); i++) {

			double pixel = ((i) * abstandNetz);
			this.uppergc1.strokeLine(pixel, 0, pixel,
					this.upperChart1.getHeight());
		}

		this.upperGraphicPane1.setContent(this.upperChart1);
	}

	/**
	 * Creates the second Upper Background Graphic.
	 */
	@FXML
	private void createUpperBackgroundGraphicSecondPane() {

		// Initialize the Chart
		this.upperChart2 = new Canvas(this.upperGraphicPane2.getWidth() - 4,
				this.upperheightZwei);

		// Erstellen des HintergrundgrafikKontextes
		this.uppergc2 = this.upperChart2.getGraphicsContext2D();
		this.uppergc2.clearRect(0, 0, this.upperChart2.getWidth(),
				this.upperChart2.getHeight());

		// Erstellen des Hintergrundes
		this.uppergc2.setFill(Color.BEIGE);
		this.uppergc2.fillRect(0, 0, this.upperChart2.getWidth(),
				this.upperChart2.getHeight());

		this.uppergc2.setLineWidth(3);
		this.uppergc2.setStroke(Color.BLACK);
		this.uppergc2.strokeLine(1, 0, 1, this.upperChart2.getHeight());

		double abstandNetz = (this.upperChart2.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);
		this.uppergc2.setLineWidth(1);
		this.uppergc2.setFont(Font.getDefault());
		this.uppergc2.setFill(Color.BLACK);
		this.uppergc2.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for (int i = 0; i <= (endzeitVar - startzeitVar); i++) {

			double pixel = ((i) * abstandNetz);
			this.uppergc2.strokeLine(pixel, 0, pixel,
					this.upperChart2.getHeight());
		}

		this.upperGraphicPane2.setContent(this.upperChart2);
	}

	/**
	 * Creates the second Upper Background Graphic.
	 */
	@FXML
	private void createUpperBackgroundGraphicThirdPane() {

		// Initialize the Chart
		this.upperChart3 = new Canvas(this.upperGraphicPane3.getWidth() - 4,
				this.upperheightDrei);

		// Erstellen des HintergrundgrafikKontextes
		this.uppergc3 = this.upperChart3.getGraphicsContext2D();
		this.uppergc3.clearRect(0, 0, this.upperChart3.getWidth(),
				this.upperChart3.getHeight());

		// Erstellen des Hintergrundes
		this.uppergc3.setFill(Color.BEIGE);
		this.uppergc3.fillRect(0, 0, this.upperChart3.getWidth(),
				this.upperChart3.getHeight());

		this.uppergc3.setLineWidth(3);
		this.uppergc3.setStroke(Color.BLACK);
		this.uppergc3.strokeLine(1, 0, 1, this.upperChart3.getHeight());

		double abstandNetz = (this.upperChart3.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);
		this.uppergc3.setLineWidth(1);
		this.uppergc3.setFont(Font.getDefault());
		this.uppergc3.setFill(Color.BLACK);
		this.uppergc3.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for (int i = 0; i <= (endzeitVar - startzeitVar); i++) {

			double pixel = ((i) * abstandNetz);
			this.uppergc3.strokeLine(pixel, 0, pixel,
					this.upperChart3.getHeight());
		}

		this.upperGraphicPane3.setContent(this.upperChart3);
	}

	/**
	 * Creates the second Upper Background Graphic.
	 */
	@FXML
	private void createUpperBackgroundGraphicFourthPane() {

		// Initialize the Chart
		this.upperChart4 = new Canvas(this.upperGraphicPane4.getWidth() - 4,
				this.upperheightVier);

		// Erstellen des HintergrundgrafikKontextes
		this.uppergc4 = this.upperChart4.getGraphicsContext2D();
		this.uppergc4.clearRect(0, 0, this.upperChart4.getWidth(),
				this.upperChart4.getHeight());

		// Erstellen des Hintergrundes
		this.uppergc4.setFill(Color.BEIGE);
		this.uppergc4.fillRect(0, 0, this.upperChart4.getWidth(),
				this.upperChart4.getHeight());

		this.uppergc4.setLineWidth(3);
		this.uppergc4.setStroke(Color.BLACK);
		this.uppergc4.strokeLine(1, 0, 1, this.upperChart4.getHeight());

		double abstandNetz = (this.upperChart4.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);
		this.uppergc4.setLineWidth(1);
		this.uppergc4.setFont(Font.getDefault());
		this.uppergc4.setFill(Color.BLACK);
		this.uppergc4.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for (int i = 0; i <= (endzeitVar - startzeitVar); i++) {

			double pixel = ((i) * abstandNetz);
			this.uppergc4.strokeLine(pixel, 0, pixel,
					this.upperChart4.getHeight());
		}

		this.upperGraphicPane4.setContent(this.upperChart4);
	}

	/**
	 * Creates the second Upper Background Graphic.
	 */
	@FXML
	private void createUpperBackgroundGraphicFifthPane() {

		// Initialize the Chart
		this.upperChart5 = new Canvas(this.upperGraphicPane5.getWidth() - 4,
				this.upperheightFuenf);

		// Erstellen des HintergrundgrafikKontextes
		this.uppergc5 = this.upperChart5.getGraphicsContext2D();
		this.uppergc5.clearRect(0, 0, this.upperChart5.getWidth(),
				this.upperChart5.getHeight());

		// Erstellen des Hintergrundes
		this.uppergc5.setFill(Color.BEIGE);
		this.uppergc5.fillRect(0, 0, this.upperChart5.getWidth(),
				this.upperChart5.getHeight());

		this.uppergc5.setLineWidth(3);
		this.uppergc5.setStroke(Color.BLACK);
		this.uppergc5.strokeLine(1, 0, 1, this.upperChart5.getHeight());

		double abstandNetz = (this.upperChart5.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);
		this.uppergc5.setLineWidth(1);
		this.uppergc5.setFont(Font.getDefault());
		this.uppergc5.setFill(Color.BLACK);
		this.uppergc5.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for (int i = 0; i <= (endzeitVar - startzeitVar); i++) {

			double pixel = ((i) * abstandNetz);
			this.uppergc5.strokeLine(pixel, 0, pixel,
					this.upperChart5.getHeight());
		}

		this.upperGraphicPane5.setContent(this.upperChart5);
	}

	/**
	 * Creates the second Upper Background Graphic.
	 */
	@FXML
	private void createUpperBackgroundGraphicSixthPane() {

		// Initialize the Chart
		this.upperChart6 = new Canvas(this.upperGraphicPane6.getWidth() - 4,
				this.upperheightSechs);

		// Erstellen des HintergrundgrafikKontextes
		this.uppergc6 = this.upperChart6.getGraphicsContext2D();
		this.uppergc6.clearRect(0, 0, this.upperChart6.getWidth(),
				this.upperChart6.getHeight());

		// Erstellen des Hintergrundes
		this.uppergc6.setFill(Color.BEIGE);
		this.uppergc6.fillRect(0, 0, this.upperChart6.getWidth(),
				this.upperChart6.getHeight());

		this.uppergc6.setLineWidth(3);
		this.uppergc6.setStroke(Color.BLACK);
		this.uppergc6.strokeLine(1, 0, 1, this.upperChart6.getHeight());

		double abstandNetz = (this.upperChart6.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);
		this.uppergc6.setLineWidth(1);
		this.uppergc6.setFont(Font.getDefault());
		this.uppergc6.setFill(Color.BLACK);
		this.uppergc6.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for (int i = 0; i <= (endzeitVar - startzeitVar); i++) {

			double pixel = ((i) * abstandNetz);
			this.uppergc6.strokeLine(pixel, 0, pixel,
					this.upperChart6.getHeight());
		}

		this.upperGraphicPane6.setContent(this.upperChart6);
	}

	/**
	 * Creates the seventh Upper Background Graphic.
	 */
	@FXML
	private void createUpperBackgroundGraphicSeventhPane() {

		// Initialize the Chart
		this.upperChart7 = new Canvas(this.upperGraphicPane7.getWidth() - 4,
				this.upperheightSieben);

		// Erstellen des HintergrundgrafikKontextes
		this.uppergc7 = this.upperChart7.getGraphicsContext2D();
		this.uppergc7.clearRect(0, 0, this.upperChart7.getWidth(),
				this.upperChart7.getHeight());

		// Erstellen des Hintergrundes
		this.uppergc7.setFill(Color.BEIGE);
		this.uppergc7.fillRect(0, 0, this.upperChart7.getWidth(),
				this.upperChart7.getHeight());

		this.uppergc7.setLineWidth(3);
		this.uppergc7.setStroke(Color.BLACK);
		this.uppergc7.strokeLine(1, 0, 1, this.upperChart7.getHeight());

		double abstandNetz = (this.upperChart7.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);
		this.uppergc7.setLineWidth(1);
		this.uppergc7.setFont(Font.getDefault());
		this.uppergc7.setFill(Color.BLACK);
		this.uppergc7.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for (int i = 0; i <= (endzeitVar - startzeitVar); i++) {

			double pixel = ((i) * abstandNetz);
			this.uppergc7.strokeLine(pixel, 0, pixel,
					this.upperChart7.getHeight());
		}

		this.upperGraphicPane7.setContent(this.upperChart7);
	}

	/**
	 * Creates The First Lower Background Graphic.
	 */
	@FXML
	private void createLowerBackgroundGraphicFirstPane() {

		// Initialize the Chart
		this.lowerChart1 = new Canvas(this.lowerGraphicPane1.getWidth() - 4,
				this.lowerheightEins);

		// Erstellen des HintergrundgrafikKontextes
		this.lowergc1 = this.lowerChart1.getGraphicsContext2D();
		this.lowergc1.clearRect(0, 0, this.lowerChart1.getWidth(),
				this.lowerChart1.getHeight());

		// Erstellen des Hintergrundes
		this.lowergc1.setFill(Color.ALICEBLUE);
		this.lowergc1.fillRect(0, 0, this.lowerChart1.getWidth(),
				this.lowerChart1.getHeight());

		this.lowergc1.setLineWidth(3);
		this.lowergc1.setStroke(Color.BLACK);
		this.lowergc1.strokeLine(1, 0, 1, this.lowerChart1.getHeight());

		double abstandNetz = (this.lowerChart1.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);
		this.lowergc1.setLineWidth(1);
		this.lowergc1.setFont(Font.getDefault());
		this.lowergc1.setFill(Color.BLACK);
		this.lowergc1.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for (int i = 0; i <= (endzeitVar - startzeitVar); i++) {

			double pixel = ((i) * abstandNetz);
			this.lowergc1.strokeLine(pixel, 0, pixel,
					this.lowerChart1.getHeight());
		}

		this.lowerGraphicPane1.setContent(this.lowerChart1);
	}

	/**
	 * Creates the second Lower Background Graphic.
	 */
	@FXML
	private void createLowerBackgroundGraphicSecondPane() {

		// Initialize the Chart
		this.lowerChart2 = new Canvas(this.lowerGraphicPane2.getWidth() - 4,
				this.lowerheightZwei);

		// Erstellen des HintergrundgrafikKontextes
		this.lowergc2 = this.lowerChart2.getGraphicsContext2D();
		this.lowergc2.clearRect(0, 0, this.lowerChart2.getWidth(),
				this.lowerChart2.getHeight());

		// Erstellen des Hintergrundes
		this.lowergc2.setFill(Color.ALICEBLUE);
		this.lowergc2.fillRect(0, 0, this.lowerChart2.getWidth(),
				this.lowerChart2.getHeight());

		this.lowergc2.setLineWidth(3);
		this.lowergc2.setStroke(Color.BLACK);
		this.lowergc2.strokeLine(1, 0, 1, this.lowerChart2.getHeight());

		double abstandNetz = (this.lowerChart2.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);
		this.lowergc2.setLineWidth(1);
		this.lowergc2.setFont(Font.getDefault());
		this.lowergc2.setFill(Color.BLACK);
		this.lowergc2.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for (int i = 0; i <= (endzeitVar - startzeitVar); i++) {

			double pixel = ((i) * abstandNetz);
			this.lowergc2.strokeLine(pixel, 0, pixel,
					this.lowerChart2.getHeight());
		}

		this.lowerGraphicPane2.setContent(this.lowerChart2);
	}

	/**
	 * Creates the second Lower Background Graphic.
	 */
	@FXML
	private void createLowerBackgroundGraphicThirdPane() {

		// Initialize the Chart
		this.lowerChart3 = new Canvas(this.lowerGraphicPane3.getWidth() - 4,
				this.lowerheightDrei);

		// Erstellen des HintergrundgrafikKontextes
		this.lowergc3 = this.lowerChart3.getGraphicsContext2D();
		this.lowergc3.clearRect(0, 0, this.lowerChart3.getWidth(),
				this.lowerChart3.getHeight());

		// Erstellen des Hintergrundes
		this.lowergc3.setFill(Color.ALICEBLUE);
		this.lowergc3.fillRect(0, 0, this.lowerChart3.getWidth(),
				this.lowerChart3.getHeight());

		this.lowergc3.setLineWidth(3);
		this.lowergc3.setStroke(Color.BLACK);
		this.lowergc3.strokeLine(1, 0, 1, this.lowerChart3.getHeight());

		double abstandNetz = (this.lowerChart3.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);
		this.lowergc3.setLineWidth(1);
		this.lowergc3.setFont(Font.getDefault());
		this.lowergc3.setFill(Color.BLACK);
		this.lowergc3.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for (int i = 0; i <= (endzeitVar - startzeitVar); i++) {

			double pixel = ((i) * abstandNetz);
			this.lowergc3.strokeLine(pixel, 0, pixel,
					this.lowerChart3.getHeight());
		}

		this.lowerGraphicPane3.setContent(this.lowerChart3);
	}

	/**
	 * Creates the second Lower Background Graphic.
	 */
	@FXML
	private void createLowerBackgroundGraphicFourthPane() {

		// Initialize the Chart
		this.lowerChart4 = new Canvas(this.lowerGraphicPane4.getWidth() - 4,
				this.lowerheightVier);

		// Erstellen des HintergrundgrafikKontextes
		this.lowergc4 = this.lowerChart4.getGraphicsContext2D();
		this.lowergc4.clearRect(0, 0, this.lowerChart4.getWidth(),
				this.lowerChart4.getHeight());

		// Erstellen des Hintergrundes
		this.lowergc4.setFill(Color.ALICEBLUE);
		this.lowergc4.fillRect(0, 0, this.lowerChart4.getWidth(),
				this.lowerChart4.getHeight());

		this.lowergc4.setLineWidth(3);
		this.lowergc4.setStroke(Color.BLACK);
		this.lowergc4.strokeLine(1, 0, 1, this.lowerChart4.getHeight());

		double abstandNetz = (this.lowerChart4.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);
		this.lowergc4.setLineWidth(1);
		this.lowergc4.setFont(Font.getDefault());
		this.lowergc4.setFill(Color.BLACK);
		this.lowergc4.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for (int i = 0; i <= (endzeitVar - startzeitVar); i++) {

			double pixel = ((i) * abstandNetz);
			this.lowergc4.strokeLine(pixel, 0, pixel,
					this.lowerChart4.getHeight());
		}

		this.lowerGraphicPane4.setContent(this.lowerChart4);
	}

	/**
	 * Creates the second Lower Background Graphic.
	 */
	@FXML
	private void createLowerBackgroundGraphicFifthPane() {

		// Initialize the Chart
		this.lowerChart5 = new Canvas(this.lowerGraphicPane5.getWidth() - 4,
				this.lowerheightFuenf);

		// Erstellen des HintergrundgrafikKontextes
		this.lowergc5 = this.lowerChart5.getGraphicsContext2D();
		this.lowergc5.clearRect(0, 0, this.lowerChart5.getWidth(),
				this.lowerChart5.getHeight());

		// Erstellen des Hintergrundes
		this.lowergc5.setFill(Color.ALICEBLUE);
		this.lowergc5.fillRect(0, 0, this.lowerChart5.getWidth(),
				this.lowerChart5.getHeight());

		this.lowergc5.setLineWidth(3);
		this.lowergc5.setStroke(Color.BLACK);
		this.lowergc5.strokeLine(1, 0, 1, this.lowerChart5.getHeight());

		double abstandNetz = (this.lowerChart5.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);
		this.lowergc5.setLineWidth(1);
		this.lowergc5.setFont(Font.getDefault());
		this.lowergc5.setFill(Color.BLACK);
		this.lowergc5.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for (int i = 0; i <= (endzeitVar - startzeitVar); i++) {

			double pixel = ((i) * abstandNetz);
			this.lowergc5.strokeLine(pixel, 0, pixel,
					this.lowerChart5.getHeight());
		}

		this.lowerGraphicPane5.setContent(this.lowerChart5);
	}

	/**
	 * Creates the second Lower Background Graphic.
	 */
	@FXML
	private void createLowerBackgroundGraphicSixthPane() {

		// Initialize the Chart
		this.lowerChart6 = new Canvas(this.lowerGraphicPane6.getWidth() - 4,
				this.lowerheightSechs);

		// Erstellen des HintergrundgrafikKontextes
		this.lowergc6 = this.lowerChart6.getGraphicsContext2D();
		this.lowergc6.clearRect(0, 0, this.lowerChart6.getWidth(),
				this.lowerChart6.getHeight());

		// Erstellen des Hintergrundes
		this.lowergc6.setFill(Color.ALICEBLUE);
		this.lowergc6.fillRect(0, 0, this.lowerChart6.getWidth(),
				this.lowerChart6.getHeight());

		this.lowergc6.setLineWidth(3);
		this.lowergc6.setStroke(Color.BLACK);
		this.lowergc6.strokeLine(1, 0, 1, this.lowerChart6.getHeight());

		double abstandNetz = (this.lowerChart6.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);
		this.lowergc6.setLineWidth(1);
		this.lowergc6.setFont(Font.getDefault());
		this.lowergc6.setFill(Color.BLACK);
		this.lowergc6.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for (int i = 0; i <= (endzeitVar - startzeitVar); i++) {

			double pixel = ((i) * abstandNetz);
			this.lowergc6.strokeLine(pixel, 0, pixel,
					this.lowerChart6.getHeight());
		}

		this.lowerGraphicPane6.setContent(this.lowerChart6);
	}

	/**
	 * Creates the seventh Lower Background Graphic.
	 */
	@FXML
	private void createLowerBackgroundGraphicSeventhPane() {

		// Initialize the Chart
		this.lowerChart7 = new Canvas(this.lowerGraphicPane7.getWidth() - 4,
				this.lowerheightSieben);

		// Erstellen des HintergrundgrafikKontextes
		this.lowergc7 = this.lowerChart7.getGraphicsContext2D();
		this.lowergc7.clearRect(0, 0, this.lowerChart7.getWidth(),
				this.lowerChart7.getHeight());

		// Erstellen des Hintergrundes
		this.lowergc7.setFill(Color.ALICEBLUE);
		this.lowergc7.fillRect(0, 0, this.lowerChart7.getWidth(),
				this.lowerChart7.getHeight());

		this.lowergc7.setLineWidth(3);
		this.lowergc7.setStroke(Color.BLACK);
		this.lowergc7.strokeLine(1, 0, 1, this.lowerChart7.getHeight());

		double abstandNetz = (this.lowerChart7.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);
		this.lowergc7.setLineWidth(1);
		this.lowergc7.setFont(Font.getDefault());
		this.lowergc7.setFill(Color.BLACK);
		this.lowergc7.setStroke(Color.BLACK);
		// Variable zum Darstellen verschiedener Zeitpunkte
		for (int i = 0; i <= (endzeitVar - startzeitVar); i++) {

			double pixel = ((i) * abstandNetz);
			this.lowergc7.strokeLine(pixel, 0, pixel,
					this.lowerChart7.getHeight());
		}

		this.lowerGraphicPane7.setContent(this.lowerChart7);
	}

	/**
	 * Creates The Upper X - Scale for Tabs.
	 */
	private void createUpperXScalePane(AnchorPane xScale, ScrollPane xScrollPane) {

		// Hier wird das Skala Canvas erzeugt
		this.upperXChart1 = new Canvas(xScrollPane.getWidth(),
				this.xUp1.getHeight());
		// Hier der Graphic Context dazu erzeugt
		this.upperXgc1 = this.upperXChart1.getGraphicsContext2D();
		this.upperXgc1.clearRect(0, 0, this.upperXChart1.getWidth(),
				this.upperXChart1.getHeight());

		double abstandNetz = (this.upperXChart1.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);
		this.upperXgc1.setLineWidth(1);
		this.upperXgc1.setFont(Font.getDefault());
		this.upperXgc1.setFill(Color.BLACK);
		this.upperXgc1.setStroke(Color.BLACK);

		// Variable zum Darstellen verschiedener Zeitpunkte
		int chartCounter = startzeitVar;
		for (int i = 0; i <= (endzeitVar - startzeitVar); i++) {

			if (i == 0) {
				double pixel = ((i) * abstandNetz);
				this.upperXgc1.fillText(String.valueOf(chartCounter),
						pixel + 1, 15);
				chartCounter = chartCounter + 1;
			}
			if (i != 0) {
				double pixel = ((i) * abstandNetz);
				this.upperXgc1.fillText(String.valueOf(chartCounter),
						pixel - 4, 15);
				if (chartCounter < 23) {
					chartCounter = chartCounter + 1;
				} else {
					chartCounter = 0;
				}
			}
		}

		xScale.getChildren().add(this.upperXChart1);
	}

	/**
	 * Creates The Upper Y - Scale.
	 */
	private void createUpperYScale(ScrollPane scrollPane, Canvas canvas,
			GraphicsContext gc, double height, Umlaufplan umlaufplan) {

		// Initialize the Chart
		canvas = new Canvas(30, height);

		// Erstellen des HintergrundgrafikKontextes
		gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		// Eigenschaften der Beschriftung
		gc.setLineWidth(1);
		gc.setFont(Font.getDefault());
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.BLACK);
		// Auslesen der Werte
		for (int j = 0; j < umlaufplan.getUmlauf().size(); j++) {

			gc.fillText("B " + umlaufplan.getUmlauf().get(j).getId(), 4,
					22 + (j * 40));

		}

		scrollPane.setContent(canvas);

	}

	/**
	 * Changes the table and Detailpane to the Selection (Umlaufplan)
	 */
	private void changeUplanDetails() {

		// Belegung der DetailsPanes
		switch (this.umlaufChoice) {
		case 1:
			// Erstellung/ BefÃ¼llen des Details Table-- Clearen des Alten
			if (uDetailsTableErstellt == true) {
				this.detailsUmlaufTable.getColumns().clear();
				this.uDetailsTableErstellt = false;
			}
			changeFocusUplan(this.umlaufplanEins);
			this.UPlanValue1
					.setStyle("-fx-background-color:#a7aacc; -fx-font-weight: bold;");
			this.UPlanValue2.setStyle("-fx-background-color:white;");
			this.UPlanValue3.setStyle("-fx-background-color:white;");
			this.UPlanValue4.setStyle("-fx-background-color:white;");
			this.UPlanValue5.setStyle("-fx-background-color:white;");
			this.UPlanValue6.setStyle("-fx-background-color:white;");
			this.UPlanValue7.setStyle("-fx-background-color:white;");
			this.DPlanValue1.setStyle("-fx-background-color:white;");
			this.DPlanValue2.setStyle("-fx-background-color:white;");
			this.DPlanValue3.setStyle("-fx-background-color:white;");
			this.DPlanValue4.setStyle("-fx-background-color:white;");
			this.DPlanValue5.setStyle("-fx-background-color:white;");
			this.DPlanValue6.setStyle("-fx-background-color:white;");
			this.DPlanValue7.setStyle("-fx-background-color:white;");
			break;
		case 2:
			// Erstellung/ BefÃ¼llen des Details Table-- Clearen des Alten
			if (uDetailsTableErstellt == true) {
				this.detailsUmlaufTable.getColumns().clear();
				this.uDetailsTableErstellt = false;
			}
			changeFocusUplan(this.umlaufplanZwei);
			this.UPlanValue1.setStyle("-fx-background-color:white;");
			this.UPlanValue2
					.setStyle("-fx-background-color:#a7aacc; -fx-font-weight: bold;");
			this.UPlanValue3.setStyle("-fx-background-color:white;");
			this.UPlanValue4.setStyle("-fx-background-color:white;");
			this.UPlanValue5.setStyle("-fx-background-color:white;");
			this.UPlanValue6.setStyle("-fx-background-color:white;");
			this.UPlanValue7.setStyle("-fx-background-color:white;");
			this.DPlanValue1.setStyle("-fx-background-color:white;");
			this.DPlanValue2.setStyle("-fx-background-color:white;");
			this.DPlanValue3.setStyle("-fx-background-color:white;");
			this.DPlanValue4.setStyle("-fx-background-color:white;");
			this.DPlanValue5.setStyle("-fx-background-color:white;");
			this.DPlanValue6.setStyle("-fx-background-color:white;");
			this.DPlanValue7.setStyle("-fx-background-color:white;");
			break;
		case 3:
			// Erstellung/ BefÃ¼llen des Details Table-- Clearen des Alten
			if (uDetailsTableErstellt == true) {
				this.detailsUmlaufTable.getColumns().clear();
				this.uDetailsTableErstellt = false;
			}
			changeFocusUplan(this.umlaufplanDrei);
			this.UPlanValue1.setStyle("-fx-background-color:white;");
			this.UPlanValue2.setStyle("-fx-background-color:white;");
			this.UPlanValue3
					.setStyle("-fx-background-color:#a7aacc; -fx-font-weight: bold;");
			this.UPlanValue4.setStyle("-fx-background-color:white;");
			this.UPlanValue5.setStyle("-fx-background-color:white;");
			this.UPlanValue6.setStyle("-fx-background-color:white;");
			this.UPlanValue7.setStyle("-fx-background-color:white;");
			this.DPlanValue1.setStyle("-fx-background-color:white;");
			this.DPlanValue2.setStyle("-fx-background-color:white;");
			this.DPlanValue3.setStyle("-fx-background-color:white;");
			this.DPlanValue4.setStyle("-fx-background-color:white;");
			this.DPlanValue5.setStyle("-fx-background-color:white;");
			this.DPlanValue6.setStyle("-fx-background-color:white;");
			this.DPlanValue7.setStyle("-fx-background-color:white;");
			break;
		case 4:
			// Erstellung/ BefÃ¼llen des Details Table-- Clearen des Alten
			if (uDetailsTableErstellt == true) {
				this.detailsUmlaufTable.getColumns().clear();
				this.uDetailsTableErstellt = false;
			}
			changeFocusUplan(this.umlaufplanVier);
			this.UPlanValue1.setStyle("-fx-background-color:white;");
			this.UPlanValue2.setStyle("-fx-background-color:white;");
			this.UPlanValue3.setStyle("-fx-background-color:white;");
			this.UPlanValue4
					.setStyle("-fx-background-color:#a7aacc; -fx-font-weight: bold;");
			this.UPlanValue5.setStyle("-fx-background-color:white;");
			this.UPlanValue6.setStyle("-fx-background-color:white;");
			this.UPlanValue7.setStyle("-fx-background-color:white;");
			this.DPlanValue1.setStyle("-fx-background-color:white;");
			this.DPlanValue2.setStyle("-fx-background-color:white;");
			this.DPlanValue3.setStyle("-fx-background-color:white;");
			this.DPlanValue4.setStyle("-fx-background-color:white;");
			this.DPlanValue5.setStyle("-fx-background-color:white;");
			this.DPlanValue6.setStyle("-fx-background-color:white;");
			this.DPlanValue7.setStyle("-fx-background-color:white;");
			break;
		case 5:
			// Erstellung/ BefÃ¼llen des Details Table-- Clearen des Alten
			if (uDetailsTableErstellt == true) {
				this.detailsUmlaufTable.getColumns().clear();
				this.uDetailsTableErstellt = false;
			}
			changeFocusUplan(this.umlaufplanFuenf);
			this.UPlanValue1.setStyle("-fx-background-color:white;");
			this.UPlanValue2.setStyle("-fx-background-color:white;");
			this.UPlanValue3.setStyle("-fx-background-color:white;");
			this.UPlanValue4.setStyle("-fx-background-color:white;");
			this.UPlanValue5
					.setStyle("-fx-background-color:#a7aacc; -fx-font-weight: bold;");
			this.UPlanValue6.setStyle("-fx-background-color:white;");
			this.UPlanValue7.setStyle("-fx-background-color:white;");
			this.DPlanValue1.setStyle("-fx-background-color:white;");
			this.DPlanValue2.setStyle("-fx-background-color:white;");
			this.DPlanValue3.setStyle("-fx-background-color:white;");
			this.DPlanValue4.setStyle("-fx-background-color:white;");
			this.DPlanValue5.setStyle("-fx-background-color:white;");
			this.DPlanValue6.setStyle("-fx-background-color:white;");
			this.DPlanValue7.setStyle("-fx-background-color:white;");
			break;
		case 6:
			// Erstellung/ BefÃ¼llen des Details Table-- Clearen des Alten
			if (uDetailsTableErstellt == true) {
				this.detailsUmlaufTable.getColumns().clear();
				this.uDetailsTableErstellt = false;
			}
			changeFocusUplan(this.umlaufplanSechs);
			this.UPlanValue1.setStyle("-fx-background-color:white;");
			this.UPlanValue2.setStyle("-fx-background-color:white;");
			this.UPlanValue3.setStyle("-fx-background-color:white;");
			this.UPlanValue4.setStyle("-fx-background-color:white;");
			this.UPlanValue5.setStyle("-fx-background-color:white;");
			this.UPlanValue6
					.setStyle("-fx-background-color:#a7aacc; -fx-font-weight: bold;");
			this.UPlanValue7.setStyle("-fx-background-color:white;");
			this.DPlanValue1.setStyle("-fx-background-color:white;");
			this.DPlanValue2.setStyle("-fx-background-color:white;");
			this.DPlanValue3.setStyle("-fx-background-color:white;");
			this.DPlanValue4.setStyle("-fx-background-color:white;");
			this.DPlanValue5.setStyle("-fx-background-color:white;");
			this.DPlanValue6.setStyle("-fx-background-color:white;");
			this.DPlanValue7.setStyle("-fx-background-color:white;");
			break;

		case 7:
			// Erstellung/ BefÃ¼llen des Details Table-- Clearen des Alten
			if (uDetailsTableErstellt == true) {
				this.detailsUmlaufTable.getColumns().clear();
				this.uDetailsTableErstellt = false;
			}
			changeFocusUplan(this.umlaufplanSieben);

			this.UPlanValue1.setStyle("-fx-background-color:white;");
			this.UPlanValue2.setStyle("-fx-background-color:white;");
			this.UPlanValue3.setStyle("-fx-background-color:white;");
			this.UPlanValue4.setStyle("-fx-background-color:white;");
			this.UPlanValue5.setStyle("-fx-background-color:white;");
			this.UPlanValue6.setStyle("-fx-background-color:white;");
			this.UPlanValue7
					.setStyle("-fx-background-color:#a7aacc; -fx-font-weight: bold;");
			this.DPlanValue1.setStyle("-fx-background-color:white;");
			this.DPlanValue2.setStyle("-fx-background-color:white;");
			this.DPlanValue3.setStyle("-fx-background-color:white;");
			this.DPlanValue4.setStyle("-fx-background-color:white;");
			this.DPlanValue5.setStyle("-fx-background-color:white;");
			this.DPlanValue6.setStyle("-fx-background-color:white;");
			this.DPlanValue7.setStyle("-fx-background-color:white;");
			break;
		case 666:

			break;

		default:
			break;
		}

	}

	/**
	 * Changes the table and Detailpane to the Selection (Dienstplan)
	 */
	private void changeDplanDetails() {

		// Belegung der DetailsPanes
		switch (this.dienstChoice) {
		case 1:
			// Erstellung/ BefÃ¼llen des Details Table-- Clearen des Alten
			if (dDetailsTableErstellt == true) {
				this.detailsDienstTable.getColumns().clear();
				this.dDetailsTableErstellt = false;
			}
			changeFocusDplan(this.dienstplanEins);
			this.DPlanValue1
					.setStyle("-fx-background-color:#a7aacc; -fx-font-weight: bold;");
			this.DPlanValue2.setStyle("-fx-background-color:white;");
			this.DPlanValue3.setStyle("-fx-background-color:white;");
			this.DPlanValue4.setStyle("-fx-background-color:white;");
			this.DPlanValue5.setStyle("-fx-background-color:white;");
			this.DPlanValue6.setStyle("-fx-background-color:white;");
			this.DPlanValue7.setStyle("-fx-background-color:white;");
			this.UPlanValue1.setStyle("-fx-background-color:white;");
			this.UPlanValue2.setStyle("-fx-background-color:white;");
			this.UPlanValue3.setStyle("-fx-background-color:white;");
			this.UPlanValue4.setStyle("-fx-background-color:white;");
			this.UPlanValue5.setStyle("-fx-background-color:white;");
			this.UPlanValue6.setStyle("-fx-background-color:white;");
			this.UPlanValue7.setStyle("-fx-background-color:white;");
			break;
		case 2:
			// Erstellung/ BefÃ¼llen des Details Table-- Clearen des Alten
			if (dDetailsTableErstellt == true) {
				this.detailsDienstTable.getColumns().clear();
				this.dDetailsTableErstellt = false;
			}
			changeFocusDplan(this.dienstplanZwei);
			this.DPlanValue1.setStyle("-fx-background-color:white;");
			this.DPlanValue2
					.setStyle("-fx-background-color:#a7aacc; -fx-font-weight: bold;");
			this.DPlanValue3.setStyle("-fx-background-color:white;");
			this.DPlanValue4.setStyle("-fx-background-color:white;");
			this.DPlanValue5.setStyle("-fx-background-color:white;");
			this.DPlanValue6.setStyle("-fx-background-color:white;");
			this.DPlanValue7.setStyle("-fx-background-color:white;");
			this.UPlanValue1.setStyle("-fx-background-color:white;");
			this.UPlanValue2.setStyle("-fx-background-color:white;");
			this.UPlanValue3.setStyle("-fx-background-color:white;");
			this.UPlanValue4.setStyle("-fx-background-color:white;");
			this.UPlanValue5.setStyle("-fx-background-color:white;");
			this.UPlanValue6.setStyle("-fx-background-color:white;");
			this.UPlanValue7.setStyle("-fx-background-color:white;");
			break;
		case 3:
			// Erstellung/ BefÃ¼llen des Details Table-- Clearen des Alten
			if (dDetailsTableErstellt == true) {
				this.detailsDienstTable.getColumns().clear();
				this.dDetailsTableErstellt = false;
			}
			changeFocusDplan(this.dienstplanDrei);
			this.DPlanValue1.setStyle("-fx-background-color:white;");
			this.DPlanValue2.setStyle("-fx-background-color:white;");
			this.DPlanValue3
					.setStyle("-fx-background-color:#a7aacc; -fx-font-weight: bold;");
			this.DPlanValue4.setStyle("-fx-background-color:white;");
			this.DPlanValue5.setStyle("-fx-background-color:white;");
			this.DPlanValue6.setStyle("-fx-background-color:white;");
			this.DPlanValue7.setStyle("-fx-background-color:white;");
			this.UPlanValue1.setStyle("-fx-background-color:white;");
			this.UPlanValue2.setStyle("-fx-background-color:white;");
			this.UPlanValue3.setStyle("-fx-background-color:white;");
			this.UPlanValue4.setStyle("-fx-background-color:white;");
			this.UPlanValue5.setStyle("-fx-background-color:white;");
			this.UPlanValue6.setStyle("-fx-background-color:white;");
			this.UPlanValue7.setStyle("-fx-background-color:white;");
			break;
		case 4:
			// Erstellung/ BefÃ¼llen des Details Table-- Clearen des Alten
			if (dDetailsTableErstellt == true) {
				this.detailsDienstTable.getColumns().clear();
				this.dDetailsTableErstellt = false;
			}
			changeFocusDplan(this.dienstplanVier);
			this.DPlanValue1.setStyle("-fx-background-color:white;");
			this.DPlanValue2.setStyle("-fx-background-color:white;");
			this.DPlanValue3.setStyle("-fx-background-color:white;");
			this.DPlanValue4
					.setStyle("-fx-background-color:#a7aacc; -fx-font-weight: bold;");
			this.UPlanValue5.setStyle("-fx-background-color:white;");
			this.UPlanValue6.setStyle("-fx-background-color:white;");
			this.UPlanValue7.setStyle("-fx-background-color:white;");
			this.UPlanValue1.setStyle("-fx-background-color:white;");
			this.UPlanValue2.setStyle("-fx-background-color:white;");
			this.UPlanValue3.setStyle("-fx-background-color:white;");
			this.UPlanValue4.setStyle("-fx-background-color:white;");
			this.UPlanValue5.setStyle("-fx-background-color:white;");
			this.UPlanValue6.setStyle("-fx-background-color:white;");
			this.UPlanValue7.setStyle("-fx-background-color:white;");
			break;
		case 5:
			// Erstellung/ BefÃ¼llen des Details Table-- Clearen des Alten
			if (dDetailsTableErstellt == true) {
				this.detailsDienstTable.getColumns().clear();
				this.dDetailsTableErstellt = false;
			}
			changeFocusDplan(this.dienstplanFuenf);
			this.DPlanValue1.setStyle("-fx-background-color:white;");
			this.DPlanValue2.setStyle("-fx-background-color:white;");
			this.DPlanValue3.setStyle("-fx-background-color:white;");
			this.DPlanValue4.setStyle("-fx-background-color:white;");
			this.DPlanValue5
					.setStyle("-fx-background-color:#a7aacc; -fx-font-weight: bold;");
			this.DPlanValue6.setStyle("-fx-background-color:white;");
			this.DPlanValue7.setStyle("-fx-background-color:white;");
			this.UPlanValue1.setStyle("-fx-background-color:white;");
			this.UPlanValue2.setStyle("-fx-background-color:white;");
			this.UPlanValue3.setStyle("-fx-background-color:white;");
			this.UPlanValue4.setStyle("-fx-background-color:white;");
			this.UPlanValue5.setStyle("-fx-background-color:white;");
			this.UPlanValue6.setStyle("-fx-background-color:white;");
			this.UPlanValue7.setStyle("-fx-background-color:white;");
			break;
		case 6:
			// Erstellung/ BefÃ¼llen des Details Table-- Clearen des Alten
			if (dDetailsTableErstellt == true) {
				this.detailsDienstTable.getColumns().clear();
				this.dDetailsTableErstellt = false;
			}
			changeFocusDplan(this.dienstplanSechs);
			this.DPlanValue1.setStyle("-fx-background-color:white;");
			this.DPlanValue2.setStyle("-fx-background-color:white;");
			this.DPlanValue3.setStyle("-fx-background-color:white;");
			this.DPlanValue4.setStyle("-fx-background-color:white;");
			this.DPlanValue5.setStyle("-fx-background-color:white;");
			this.DPlanValue6
					.setStyle("-fx-background-color:#a7aacc; -fx-font-weight: bold;");
			this.DPlanValue7.setStyle("-fx-background-color:white;");
			this.UPlanValue1.setStyle("-fx-background-color:white;");
			this.UPlanValue2.setStyle("-fx-background-color:white;");
			this.UPlanValue3.setStyle("-fx-background-color:white;");
			this.UPlanValue4.setStyle("-fx-background-color:white;");
			this.UPlanValue5.setStyle("-fx-background-color:white;");
			this.UPlanValue6.setStyle("-fx-background-color:white;");
			this.UPlanValue7.setStyle("-fx-background-color:white;");
			break;

		case 7:
			// Erstellung/ BefÃ¼llen des Details Table-- Clearen des Alten
			if (dDetailsTableErstellt == true) {
				this.detailsDienstTable.getColumns().clear();
				this.dDetailsTableErstellt = false;
			}
			changeFocusDplan(this.dienstplanSieben);

			this.DPlanValue1.setStyle("-fx-background-color:white;");
			this.DPlanValue2.setStyle("-fx-background-color:white;");
			this.DPlanValue3.setStyle("-fx-background-color:white;");
			this.DPlanValue4.setStyle("-fx-background-color:white;");
			this.DPlanValue5.setStyle("-fx-background-color:white;");
			this.DPlanValue6.setStyle("-fx-background-color:white;");
			this.DPlanValue7
					.setStyle("-fx-background-color:#a7aacc; -fx-font-weight: bold;");
			this.UPlanValue1.setStyle("-fx-background-color:white;");
			this.UPlanValue2.setStyle("-fx-background-color:white;");
			this.UPlanValue3.setStyle("-fx-background-color:white;");
			this.UPlanValue4.setStyle("-fx-background-color:white;");
			this.UPlanValue5.setStyle("-fx-background-color:white;");
			this.UPlanValue6.setStyle("-fx-background-color:white;");
			this.UPlanValue7.setStyle("-fx-background-color:white;");
			break;
		case 666:

			break;

		default:
			break;
		}

	}

	// Calls both refresh methods for U-Plan
	private void changeFocusUplan(Umlaufplan umlaufplan) {

		createUDetailsTable(umlaufplan);
		fillDetailPaneUmlauf(umlaufplan);

	}

	// Calls both refresh methods for D-Plan
	private void changeFocusDplan(Dienstplan dienstplan) {

		createDDetailsTable(dienstplan);
		fillDetailPaneDienst(dienstplan);

	}

	/**
	 * Fills the DetailPane fÃ¼r die UmlaufplÃ¤ne
	 */
	private void fillDetailPaneUmlauf(Umlaufplan umlaufplan) {
		// Anzahl Blockelemente ermitteln und ausgeben
		this.anzahlFahrten.setVisible(true);
		this.anzahlFahrten.setText(String.valueOf(umlaufplan.getFahrtZuUmlauf()
				.size()));
		// Anzahl Service- und Leerfahrten ermitteln und ausgeben
		int sj = 0;
		int lf = 0;
		for (int i = 0; i < umlaufplan.getFahrtZuUmlauf().size(); i++) {
			if (umlaufplan.getFahrtZuUmlauf().get(i).getElementType() == 1) {
				sj++;
			}
			if (umlaufplan.getFahrtZuUmlauf().get(i).getElementType() == 2) {
				lf++;
			}
		}
		this.anzahlServiceFahrten.setVisible(true);
		this.anzahlServiceFahrten.setText(String.valueOf(sj));
		this.anzahlLeerFahrten.setVisible(true);
		this.anzahlLeerFahrten.setText(String.valueOf(lf));
		// ZugehÃ¶rigkeit zu Fahrplan
		int fpID;
		fpID = umlaufplan.getFahrplanID();
		this.gehoertzuFahrplan.setVisible(true);
		this.gehoertzuFahrplan.setText(String.valueOf(fpID));
	}

	/**
	 * Creates DetailsTable for U-Plan.
	 */
	@SuppressWarnings("unchecked")
	private void createUDetailsTable(Umlaufplan umlaufplan) {

		this.detailsUmlaufTable.setEditable(true);

		TableColumn<Blockelement, Integer> blockEleCol = new TableColumn<Blockelement, Integer>(
				"Block-Ele.");
		TableColumn<Blockelement, String> startzeitCol = new TableColumn<Blockelement, String>(
				"Startzeit");
		TableColumn<Blockelement, String> endzeitCol = new TableColumn<Blockelement, String>(
				"Endzeit");
		TableColumn<Blockelement, Integer> eleTypeCol = new TableColumn<Blockelement, Integer>(
				"Ele.-Type");
		TableColumn<Blockelement, Integer> blockCol = new TableColumn<Blockelement, Integer>(
				"Block");
		TableColumn<Blockelement, String> dauerCol = new TableColumn<Blockelement, String>(
				"Dauer");

		blockEleCol
				.setCellValueFactory(new PropertyValueFactory<Blockelement, Integer>(
						"id"));
		startzeitCol
				.setCellValueFactory(new PropertyValueFactory<Blockelement, String>(
						"depTime"));
		endzeitCol
				.setCellValueFactory(new PropertyValueFactory<Blockelement, String>(
						"arrTime"));
		eleTypeCol
				.setCellValueFactory(new PropertyValueFactory<Blockelement, Integer>(
						"elementTypeName"));
		blockCol.setCellValueFactory(new PropertyValueFactory<Blockelement, Integer>(
				"blockID"));
		dauerCol.setCellValueFactory(new PropertyValueFactory<Blockelement, String>(
				"driveTime"));

		blockEleCol.prefWidthProperty().bind(blockEleCol.widthProperty());
		startzeitCol.prefWidthProperty().bind(startzeitCol.widthProperty());
		endzeitCol.prefWidthProperty().bind(endzeitCol.widthProperty());
		eleTypeCol.prefWidthProperty().bind(eleTypeCol.widthProperty());
		blockCol.prefWidthProperty().bind(blockCol.widthProperty());
		dauerCol.prefWidthProperty().bind(dauerCol.widthProperty());

		// Hereinladen der Daten

		ObservableList<Blockelement> data = FXCollections.observableArrayList();

		for (int i = 0; i < umlaufplan.getFahrtZuUmlauf().size(); i++) {

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
			differenz = (differenz / 1000) / 60;
			umlaufplan.getFahrtZuUmlauf().get(i)
					.setDriveTime(differenz + " min");

			// HinzufÃ¼gen der Daten

			data.add(umlaufplan.getFahrtZuUmlauf().get(i));
		}
		;

		this.detailsUmlaufTable.setItems(data);
		this.detailsUmlaufTable.getColumns().addAll(blockEleCol, startzeitCol,
				endzeitCol, eleTypeCol, blockCol, dauerCol);
		this.tablePane.setContent(detailsUmlaufTable);

		uDetailsTableErstellt = true;
	}

	/**
	 * Fills the DetailPane fÃ¼r die DienstplÃ¤ne
	 */
	private void fillDetailPaneDienst(Dienstplan dienstplan) {
		// Anzahl Blockelemente ermitteln und ausgeben
		this.anzahlFahrten.setVisible(true);
		this.anzahlFahrten.setText(String.valueOf(dienstplan.getDutyelement()
				.size()));
		// Anzahl Service- und Leerfahrten ermitteln und ausgeben
		int sj = 0;
		int lf = 0;
		for (int i = 0; i < dienstplan.getDutyelement().size(); i++) {
			if (dienstplan.getDutyelement().get(i).getElementType() == 1) {
				sj++;
			}
			if (dienstplan.getDutyelement().get(i).getElementType() == 2) {
				lf++;
			}
		}
		this.anzahlServiceFahrten.setVisible(true);
		this.anzahlServiceFahrten.setText(String.valueOf(sj));
		this.anzahlLeerFahrten.setVisible(true);
		this.anzahlLeerFahrten.setText(String.valueOf(lf));

		// Zugehörigkeit zu Fahrplan
		int fpID;
		fpID = dienstplan.getFahrplanID();
		this.gehoertzuFahrplan.setVisible(true);
		this.gehoertzuFahrplan.setText(String.valueOf(fpID));

	}

	/**
	 * Creates DetailsTable for U-Plan.
	 */
	@SuppressWarnings("unchecked")
	private void createDDetailsTable(Dienstplan dienstplan) {

		this.detailsDienstTable.setEditable(true);

		TableColumn<Dutyelement, Integer> dutyEleCol = new TableColumn<Dutyelement, Integer>(
				"Duty-Ele.");
		TableColumn<Dutyelement, String> startzeitCol = new TableColumn<Dutyelement, String>(
				"Startzeit");
		TableColumn<Dutyelement, String> endzeitCol = new TableColumn<Dutyelement, String>(
				"Endzeit");
		TableColumn<Dutyelement, Integer> eleTypeCol = new TableColumn<Dutyelement, Integer>(
				"Ele.-Type");
		TableColumn<Dutyelement, Integer> dutyCol = new TableColumn<Dutyelement, Integer>(
				"Dienst");
		TableColumn<Dutyelement, String> dauerCol = new TableColumn<Dutyelement, String>(
				"Dauer");

		dutyEleCol
				.setCellValueFactory(new PropertyValueFactory<Dutyelement, Integer>(
						"id"));
		startzeitCol
				.setCellValueFactory(new PropertyValueFactory<Dutyelement, String>(
						"depTime"));
		endzeitCol
				.setCellValueFactory(new PropertyValueFactory<Dutyelement, String>(
						"arrTime"));
		eleTypeCol
				.setCellValueFactory(new PropertyValueFactory<Dutyelement, Integer>(
						"elementType"));
		dutyCol.setCellValueFactory(new PropertyValueFactory<Dutyelement, Integer>(
				"dutyHilfsID"));
		dauerCol.setCellValueFactory(new PropertyValueFactory<Dutyelement, String>(
				"driveTime"));

		dutyEleCol.prefWidthProperty().bind(dutyEleCol.widthProperty());
		startzeitCol.prefWidthProperty().bind(startzeitCol.widthProperty());
		endzeitCol.prefWidthProperty().bind(endzeitCol.widthProperty());
		eleTypeCol.prefWidthProperty().bind(eleTypeCol.widthProperty());
		dutyCol.prefWidthProperty().bind(dutyCol.widthProperty());
		dauerCol.prefWidthProperty().bind(dauerCol.widthProperty());

		// Hereinladen der Daten

		ObservableList<Dutyelement> data = FXCollections.observableArrayList();

		for (int i = 0; i < dienstplan.getDutyelement().size(); i++) {

			// Berrechnen der Dauer zwischen der Abfahrt und der Ankunft

			String depTime = dienstplan.getDutyelement().get(i).getDepTime();
			String arrtime = dienstplan.getDutyelement().get(i).getArrTime();

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
			dienstplan.getDutyelement().get(i).setDriveTime(differenz + " min");

			// HinzufÃ¼gen der Daten

			data.add(dienstplan.getDutyelement().get(i));
		}
		;

		this.detailsDienstTable.setItems(data);
		this.detailsDienstTable.getColumns().addAll(dutyEleCol, startzeitCol,
				endzeitCol, eleTypeCol, dutyCol, dauerCol);
		this.tablePane.setContent(detailsDienstTable);

		dDetailsTableErstellt = true;
	}

	/**
	 * Creates Umlauf Elements in the Graphic.
	 */
	private void createUmlaufElementGraphic(Umlaufplan umlaufplan,
			ScrollPane scrollPane, Canvas canvas, GraphicsContext gc) {

		double abstandNetz = (canvas.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);

		// Auslesen der Blockanzahl
		for (int j = 0; j < umlaufplan.getUmlauf().size(); j++) {

			// Variablen zur Prüfung der ersten Stunde
			int changeHour = 100;
			int changeMin = 100;
			Boolean anfangUmlauf = true;
			// Auslesen Blockelementanzahl
			for (int i = 0; i < umlaufplan.getFahrtZuUmlauf().size(); i++) {

				// Abgleich mit den Werten
				if (umlaufplan.getFahrtZuUmlauf().get(i).getBlockID() == umlaufplan
						.getUmlauf().get(j).getId()) {

					// Auslesen der Zeit als Integer
					StringSplitter ss = new StringSplitter();
					int[] zeit = new int[2];
					zeit = ss.intParse(umlaufplan.getFahrtZuUmlauf().get(i)
							.getDepTime());
					int startHour = zeit[0];
					int startMin = zeit[1];
					zeit = ss.intParse(umlaufplan.getFahrtZuUmlauf().get(i)
							.getArrTime());

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
						int startPixelY = 10 + (j * 40);
						int fahrtDauer = 0;

						// Berrechnen der Dauer zwischen der Abfahrt und der
						// Ankunft

						String depTime = umlaufplan.getFahrtZuUmlauf().get(i)
								.getDepTime();
						String arrtime = umlaufplan.getFahrtZuUmlauf().get(i)
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
						// Über 0 Uhr
						if (date2.getTime() < date1.getTime()) {
							differenz = date2.getTime() - date1.getTime()
									+ 86400000;
						}
						differenz = (differenz / 1000) / 60;

						fahrtDauer = (int) (differenz * (abstandNetz / 60));

						// FÃ¤rben der Elemente

						switch (umlaufplan.getFahrtZuUmlauf().get(i)
								.getElementType()) {

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

						gc.fillRoundRect(startPixelX, startPixelY, fahrtDauer,
								20, 20, 10);
						gc.strokeRoundRect(startPixelX, startPixelY,
								fahrtDauer, 20, 20, 10);

						// Beschriftet die Elemente

						if (fahrtDauer > 30) {
							gc.setFill(Color.BLACK);
							gc.setFont(new Font("Arial", 12));
							gc.fillText(
									String.valueOf(umlaufplan
											.getFahrtZuUmlauf().get(i).getId()),
									startPixelX - 3 + (fahrtDauer / 5),
									startPixelY + 14);
						}
					}
				}
			}
		}
	}

	/**
	 * Creates Duty Elements in the Graphic.
	 */
	private void createDienstElementGraphic(Dienstplan dienstplan,
			ScrollPane scrollPane, Canvas canvas, GraphicsContext gc) {

		double abstandNetz = (canvas.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);

		// Auslesen der Blockanzahl
		for (int j = 0; j < dienstplan.getDuty().size(); j++) {

			// Auslesen Blockelementanzahl
			for (int i = 0; i < dienstplan.getDutyelement().size(); i++) {

				// Abgleich mit den Werten
				if (dienstplan.getDutyelement().get(i).getDutyID()
						.equals(dienstplan.getDuty().get(j).getId())) {

					// Auslesen der Zeit als Integer
					StringSplitter ss = new StringSplitter();
					int[] zeit = new int[2];
					zeit = ss.intParse(dienstplan.getDutyelement().get(i)
							.getDepTime());
					int startHour = zeit[0];
					int startMin = zeit[1];
					zeit = ss.intParse(dienstplan.getDutyelement().get(i)
							.getArrTime());

					// Belegung der Pixelwerte
					if (this.startzeitVar <= startHour) {
						int stundenDifferenz = startHour - this.startzeitVar;
						int startPixelX = (int) ((stundenDifferenz * abstandNetz) + ((abstandNetz / 60) * startMin));
						int startPixelY = 10 + (j * 40);
						int fahrtDauer = 0;
						// Berrechnen der Dauer zwischen der Abfahrt und der
						// Ankunft

						String depTime = dienstplan.getDutyelement().get(i)
								.getDepTime();
						String arrtime = dienstplan.getDutyelement().get(i)
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

						// FÃ¤rben der Elemente

						switch (dienstplan.getDutyelement().get(i)
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

						gc.fillRoundRect(startPixelX, startPixelY, fahrtDauer,
								20, 20, 10);
						gc.strokeRoundRect(startPixelX, startPixelY,
								fahrtDauer, 20, 20, 10);

						// Beschriftet die Elemente

						if (fahrtDauer > 30) {
							gc.setFill(Color.BLACK);
							gc.setFont(new Font("Arial", 12));
							gc.fillText(
									String.valueOf(dienstplan.getDutyelement()
											.get(i).getId()), startPixelX - 3
											+ (fahrtDauer / 5),
									startPixelY + 14);
						}
					}
				}
			}
		}
	}

	/**
	 * Creates The Lower X - Scale for Tabs.
	 */
	private void createLowerXScalePane(AnchorPane xScale, ScrollPane xScrollPane) {

		// Hier wird das Skala Canvas erzeugt
		this.lowerXChart1 = new Canvas(xScrollPane.getWidth(),
				this.xLow1.getHeight());
		// Hier der Graphic Context dazu erzeugt
		this.lowerXgc1 = this.lowerXChart1.getGraphicsContext2D();
		this.lowerXgc1.clearRect(0, 0, this.lowerXChart1.getWidth(),
				this.lowerXChart1.getHeight());

		double abstandNetz = (this.lowerXChart1.getWidth() - 30)
				/ (this.endzeitVar - this.startzeitVar);
		this.lowerXgc1.setLineWidth(1);
		this.lowerXgc1.setFont(Font.getDefault());
		this.lowerXgc1.setFill(Color.BLACK);

		// Variable zum Darstellen verschiedener Zeitpunkte
		int chartCounter = startzeitVar;
		for (int i = 0; i <= (endzeitVar - startzeitVar); i++) {

			if (i == 0) {
				double pixel = ((i) * abstandNetz);
				this.lowerXgc1.fillText(String.valueOf(chartCounter),
						pixel + 1, 15);
				chartCounter = chartCounter + 1;
			}
			if (i != 0) {
				double pixel = ((i) * abstandNetz);
				this.lowerXgc1.fillText(String.valueOf(chartCounter),
						pixel - 4, 15);
				if (chartCounter < 23) {
					chartCounter = chartCounter + 1;
				} else {
					chartCounter = 0;
				}
			}
		}
		xScale.getChildren().add(lowerXChart1);
	}

	/**
	 * Creates The Lower Y - Scale.
	 */
	private void createLowerYScale(ScrollPane scrollPane, Canvas canvas,
			GraphicsContext gc, double height, Dienstplan dienstplan) {

		// Initialize the Chart
		canvas = new Canvas(30, height);

		// Erstellen des HintergrundgrafikKontextes
		gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		// Eigenschaften der Beschriftung
		gc.setLineWidth(1);
		gc.setFont(Font.getDefault());
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.BLACK);
		// Auslesen der Werte
		for (int j = 0; j < dienstplan.getDuty().size(); j++) {

			gc.fillText("D " + dienstplan.getDuty().get(j).getHilfsID(), 4,
					22 + (j * 40));

		}

		scrollPane.setContent(canvas);

	}

	/**
	 * Creates The Helplines in the graphic.
	 */
	private void createHelpLines() {

		createHelpLinesFirstTab();
		if (this.secondGrafikErstellt == true) {
			createHelpLinesSecondTab();
		}
		if (this.thirdGrafikErstellt == true) {
			createHelpLinesThirdTab();
		}
		if (this.fourthGrafikErstellt == true) {
			createHelpLinesFourthTab();
		}
		if (this.fifthGrafikErstellt == true) {
			createHelpLinesFifthTab();
		}
		if (this.sixthGrafikErstellt == true) {
			createHelpLinesSixthTab();
		}
		if (this.seventhGrafikErstellt == true) {
			createHelpLinesSeventhTab();
		}
	}

	/**
	 * Creates The Helplines in the graphic.
	 */
	private void createHelpLinesFirstTab() {

		// Methoden zu Erstellung der dynamischen Hilfslinien

		if (this.firstLowergrafikErstellt == true) {

			double abstandNetz = (this.lowerChart1.getWidth() - 30)
					/ (this.endzeitVar - this.startzeitVar);

			this.lowergc1.setLineWidth(1);
			this.lowergc1.setStroke(Color.LIGHTGREY);

			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4;
				this.lowergc1.strokeLine(pixel, 0, pixel,
						this.lowerChart1.getHeight() - 3);

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 2;
				this.lowergc1.strokeLine(pixel, 0, pixel,
						this.lowerChart1.getHeight() - 3);

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4 * 3;
				this.lowergc1.strokeLine(pixel, 0, pixel,
						this.lowerChart1.getHeight() - 3);

			}
		}
		if (this.firstUppergrafikErstellt == true) {

			double abstandNetz = (this.upperChart1.getWidth() - 30)
					/ (this.endzeitVar - this.startzeitVar);

			this.uppergc1.setLineWidth(1);
			this.uppergc1.setStroke(Color.LIGHTGREY);

			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4;
				this.uppergc1.strokeLine(pixel, 0, pixel,
						this.upperChart1.getHeight());

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 2;
				this.uppergc1.strokeLine(pixel, 0, pixel,
						this.upperChart1.getHeight());

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4 * 3;
				this.uppergc1.strokeLine(pixel, 0, pixel,
						this.upperChart1.getHeight());

			}
		}

	}

	/**
	 * Creates The Helplines in the graphic.
	 */
	private void createHelpLinesSecondTab() {

		// Methoden zu Erstellung der dynamischen Hilfslinien

		if (this.secondLowergrafikErstellt == true) {

			double abstandNetz = (this.lowerChart2.getWidth() - 30)
					/ (this.endzeitVar - this.startzeitVar);

			this.lowergc2.setLineWidth(1);
			this.lowergc2.setStroke(Color.LIGHTGREY);

			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4;
				this.lowergc2.strokeLine(pixel, 0, pixel,
						this.lowerChart2.getHeight() - 3);

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 2;
				this.lowergc2.strokeLine(pixel, 0, pixel,
						this.lowerChart2.getHeight() - 3);

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4 * 3;
				this.lowergc2.strokeLine(pixel, 0, pixel,
						this.lowerChart2.getHeight() - 3);

			}
		}

		if (this.secondUppergrafikErstellt == true) {

			double abstandNetz = (this.upperChart2.getWidth() - 30)
					/ (this.endzeitVar - this.startzeitVar);

			this.uppergc2.setLineWidth(1);
			this.uppergc2.setStroke(Color.LIGHTGREY);

			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4;
				this.uppergc2.strokeLine(pixel, 0, pixel,
						this.upperChart1.getHeight());

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 2;
				this.uppergc2.strokeLine(pixel, 0, pixel,
						this.upperChart1.getHeight());

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4 * 3;
				this.uppergc2.strokeLine(pixel, 0, pixel,
						this.upperChart1.getHeight());

			}
		}

	}

	/**
	 * Creates The Helplines in the graphic.
	 */
	private void createHelpLinesThirdTab() {

		// Methoden zu Erstellung der dynamischen Hilfslinien

		if (this.thirdLowergrafikErstellt == true) {

			double abstandNetz = (this.lowerChart3.getWidth() - 30)
					/ (this.endzeitVar - this.startzeitVar);

			this.lowergc3.setLineWidth(1);
			this.lowergc3.setStroke(Color.LIGHTGREY);

			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4;
				this.lowergc3.strokeLine(pixel, 0, pixel,
						this.lowerChart3.getHeight() - 3);

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 2;
				this.lowergc3.strokeLine(pixel, 0, pixel,
						this.lowerChart3.getHeight() - 3);

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4 * 3;
				this.lowergc3.strokeLine(pixel, 0, pixel,
						this.lowerChart3.getHeight() - 3);

			}
		}
		if (this.thirdUppergrafikErstellt == true) {

			double abstandNetz = (this.upperChart3.getWidth() - 30)
					/ (this.endzeitVar - this.startzeitVar);

			this.uppergc3.setLineWidth(1);
			this.uppergc3.setStroke(Color.LIGHTGREY);

			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4;
				this.uppergc3.strokeLine(pixel, 0, pixel,
						this.upperChart3.getHeight());

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 2;
				this.uppergc3.strokeLine(pixel, 0, pixel,
						this.upperChart3.getHeight());

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4 * 3;
				this.uppergc3.strokeLine(pixel, 0, pixel,
						this.upperChart3.getHeight());

			}
		}

	}

	/**
	 * Creates The Helplines in the graphic.
	 */
	private void createHelpLinesFourthTab() {

		// Methoden zu Erstellung der dynamischen Hilfslinien

		if (this.fourthLowergrafikErstellt == true) {

			double abstandNetz = (this.lowerChart4.getWidth() - 30)
					/ (this.endzeitVar - this.startzeitVar);

			this.lowergc4.setLineWidth(1);
			this.lowergc4.setStroke(Color.LIGHTGREY);

			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4;
				this.lowergc4.strokeLine(pixel, 0, pixel,
						this.lowerChart4.getHeight() - 3);

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 2;
				this.lowergc4.strokeLine(pixel, 0, pixel,
						this.lowerChart4.getHeight() - 3);

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4 * 3;
				this.lowergc4.strokeLine(pixel, 0, pixel,
						this.lowerChart4.getHeight() - 3);

			}
		}
		if (this.fourthUppergrafikErstellt == true) {

			double abstandNetz = (this.upperChart4.getWidth() - 30)
					/ (this.endzeitVar - this.startzeitVar);

			this.uppergc4.setLineWidth(1);
			this.uppergc4.setStroke(Color.LIGHTGREY);

			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4;
				this.uppergc4.strokeLine(pixel, 0, pixel,
						this.upperChart4.getHeight());

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 2;
				this.uppergc4.strokeLine(pixel, 0, pixel,
						this.upperChart4.getHeight());

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4 * 3;
				this.uppergc4.strokeLine(pixel, 0, pixel,
						this.upperChart4.getHeight());

			}
		}

	}

	/**
	 * Creates The Helplines in the graphic.
	 */
	private void createHelpLinesFifthTab() {

		// Methoden zu Erstellung der dynamischen Hilfslinien

		if (this.fifthLowergrafikErstellt == true) {

			double abstandNetz = (this.lowerChart5.getWidth() - 30)
					/ (this.endzeitVar - this.startzeitVar);

			this.lowergc5.setLineWidth(1);
			this.lowergc5.setStroke(Color.LIGHTGREY);

			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4;
				this.lowergc5.strokeLine(pixel, 0, pixel,
						this.lowerChart5.getHeight() - 3);

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 2;
				this.lowergc5.strokeLine(pixel, 0, pixel,
						this.lowerChart5.getHeight() - 3);

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4 * 3;
				this.lowergc5.strokeLine(pixel, 0, pixel,
						this.lowerChart5.getHeight() - 3);

			}
		}
		if (this.fifthUppergrafikErstellt == true) {

			double abstandNetz = (this.upperChart5.getWidth() - 30)
					/ (this.endzeitVar - this.startzeitVar);

			this.uppergc5.setLineWidth(1);
			this.uppergc5.setStroke(Color.LIGHTGREY);

			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4;
				this.uppergc5.strokeLine(pixel, 0, pixel,
						this.upperChart5.getHeight());

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 2;
				this.uppergc5.strokeLine(pixel, 0, pixel,
						this.upperChart5.getHeight());

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4 * 3;
				this.uppergc5.strokeLine(pixel, 0, pixel,
						this.upperChart5.getHeight());

			}
		}

	}

	/**
	 * Creates The Helplines in the graphic.
	 */
	private void createHelpLinesSixthTab() {

		// Methoden zu Erstellung der dynamischen Hilfslinien

		if (this.sixthLowergrafikErstellt == true) {

			double abstandNetz = (this.lowerChart6.getWidth() - 30)
					/ (this.endzeitVar - this.startzeitVar);

			this.lowergc6.setLineWidth(1);
			this.lowergc6.setStroke(Color.LIGHTGREY);

			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4;
				this.lowergc6.strokeLine(pixel, 0, pixel,
						this.lowerChart6.getHeight() - 3);

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 2;
				this.lowergc6.strokeLine(pixel, 0, pixel,
						this.lowerChart6.getHeight() - 3);

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4 * 3;
				this.lowergc6.strokeLine(pixel, 0, pixel,
						this.lowerChart6.getHeight() - 3);

			}
		}
		if (this.sixthUppergrafikErstellt == true) {

			double abstandNetz = (this.upperChart6.getWidth() - 30)
					/ (this.endzeitVar - this.startzeitVar);

			this.uppergc6.setLineWidth(1);
			this.uppergc6.setStroke(Color.LIGHTGREY);

			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4;
				this.uppergc6.strokeLine(pixel, 0, pixel,
						this.upperChart6.getHeight());

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 2;
				this.uppergc6.strokeLine(pixel, 0, pixel,
						this.upperChart6.getHeight());

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4 * 3;
				this.uppergc6.strokeLine(pixel, 0, pixel,
						this.upperChart6.getHeight());

			}
		}

	}

	/**
	 * Creates The Helplines in the graphic.
	 */
	private void createHelpLinesSeventhTab() {

		// Methoden zu Erstellung der dynamischen Hilfslinien

		if (this.seventhLowergrafikErstellt == true) {

			double abstandNetz = (this.lowerChart7.getWidth() - 30)
					/ (this.endzeitVar - this.startzeitVar);

			this.lowergc7.setLineWidth(1);
			this.lowergc7.setStroke(Color.LIGHTGREY);

			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4;
				this.lowergc7.strokeLine(pixel, 0, pixel,
						this.lowerChart7.getHeight() - 3);

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 2;
				this.lowergc7.strokeLine(pixel, 0, pixel,
						this.lowerChart7.getHeight() - 3);

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4 * 3;
				this.lowergc7.strokeLine(pixel, 0, pixel,
						this.lowerChart7.getHeight() - 3);

			}
		}
		if (this.seventhUppergrafikErstellt == true) {

			double abstandNetz = (this.upperChart7.getWidth() - 30)
					/ (this.endzeitVar - this.startzeitVar);

			this.uppergc7.setLineWidth(1);
			this.uppergc7.setStroke(Color.LIGHTGREY);

			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4;
				this.uppergc7.strokeLine(pixel, 0, pixel,
						this.upperChart7.getHeight());

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 2;
				this.uppergc7.strokeLine(pixel, 0, pixel,
						this.upperChart7.getHeight());

			}
			for (int i = 0; i < (this.endzeitVar - this.startzeitVar); i++) {

				double pixel = ((i) * abstandNetz) + abstandNetz / 4 * 3;
				this.uppergc7.strokeLine(pixel, 0, pixel,
						this.upperChart7.getHeight());

			}
		}

	}

	/**
	 * Deletes the latest U-Plan
	 */
	@FXML
	public void deleteLastUPlan() {

		int pruefcounter = 0;

		// An dieser Stelle wird das richtige Objekt herausgefunden

		if (this.UPlan1.isVisible() == true) {
			if (this.UPlan2.isVisible() == true) {
				pruefcounter = 1;
				if (this.UPlan3.isVisible() == true) {
					pruefcounter = 2;
					if (this.UPlan4.isVisible() == true) {
						pruefcounter = 3;
						if (this.UPlan5.isVisible() == true) {
							pruefcounter = 4;
							if (this.UPlan6.isVisible() == true) {
								pruefcounter = 5;
								if (this.UPlan7.isVisible() == true) {
									pruefcounter = 6;
								}
							}
						}
					}
				}
			}
		}

		// Hier werden die Schritte fÃ¼r den LÃ¶schvorgang bestimmt

		switch (pruefcounter) {
		case 0:
			this.UPlan1.setVisible(false);
			this.UPlanValue1.setVisible(false);
			this.upperGraphicPane1.setContent(null);
			this.xUp1.getChildren().clear();
			this.yUp1.setContent(null);
			this.loeschenUmlaufplan.setVisible(false);
			this.firstUppergrafikErstellt = false;
			this.umlaufplanEins = null;
			break;
		case 1:
			this.UPlan2.setVisible(false);
			this.UPlanValue2.setVisible(false);
			this.upperGraphicPane2.setContent(null);
			this.xUp2.getChildren().clear();
			this.yUp2.setContent(null);
			this.Plan2.setDisable(true);
			this.secondUppergrafikErstellt = false;
			this.umlaufplanZwei = null;
			this.UPlanValue1
					.setStyle("-fx-background-color:#a7aacc; -fx-font-weight: bold;");
			break;
		case 2:
			this.UPlan3.setVisible(false);
			this.UPlanValue3.setVisible(false);
			this.upperGraphicPane3.setContent(null);
			this.xUp3.getChildren().clear();
			this.yUp3.setContent(null);
			this.Plan3.setDisable(true);
			this.thirdUppergrafikErstellt = false;
			this.umlaufplanDrei = null;
			;
			this.UPlanValue2
					.setStyle("-fx-background-color:#a7aacc; -fx-font-weight: bold;");
			break;
		case 3:
			this.UPlan4.setVisible(false);
			this.UPlanValue4.setVisible(false);
			this.upperGraphicPane4.setContent(null);
			this.xUp4.getChildren().clear();
			this.yUp4.setContent(null);
			this.Plan4.setDisable(true);
			this.fourthUppergrafikErstellt = false;
			this.umlaufplanVier = null;
			;
			this.UPlanValue3
					.setStyle("-fx-background-color:#a7aacc; -fx-font-weight: bold;");
			break;
		case 4:
			this.UPlan5.setVisible(false);
			this.UPlanValue5.setVisible(false);
			this.upperGraphicPane5.setContent(null);
			this.xUp5.getChildren().clear();
			this.yUp5.setContent(null);
			this.Plan5.setDisable(true);
			this.fifthUppergrafikErstellt = false;
			this.umlaufplanFuenf = null;
			;
			this.UPlanValue4
					.setStyle("-fx-background-color:#a7aacc; -fx-font-weight: bold;");
			break;
		case 5:
			this.UPlan6.setVisible(false);
			this.UPlanValue6.setVisible(false);
			this.upperGraphicPane6.setContent(null);
			this.xUp6.getChildren().clear();
			this.yUp6.setContent(null);
			this.Plan6.setDisable(true);
			this.sixthUppergrafikErstellt = false;
			this.umlaufplanSechs = null;
			;
			this.UPlanValue5
					.setStyle("-fx-background-color:#a7aacc; -fx-font-weight: bold;");
			break;
		case 6:
			this.UPlan7.setVisible(false);
			this.UPlanValue7.setVisible(false);
			this.upperGraphicPane7.setContent(null);
			this.xUp7.getChildren().clear();
			this.yUp7.setContent(null);
			this.Plan7.setDisable(true);
			this.seventhUppergrafikErstellt = false;
			this.umlaufplanSieben = null;
			;
			this.UPlanValue6
					.setStyle("-fx-background-color:#a7aacc; -fx-font-weight: bold;");
			break;
		default:
			break;
		}
		if (this.umlaufTabCounter > 0) {
			this.umlaufTabCounter = this.umlaufTabCounter - 1;
		}

	}

	/**
	 * Deletes the latest D-Plan
	 */
	@FXML
	public void deleteLastDPlan() {

	}

	/**
	 * Creates Filter Transitions.
	 */

	public void graphicTransition() {

		// Fades in Filter Panel
		FadeTransition fa = new FadeTransition(Duration.millis(500),
				this.filterPanel);
		fa.setFromValue(0.0);
		fa.setToValue(1.0);
		fa.setAutoReverse(true);
		fa.play();

		FadeTransition faa = new FadeTransition(Duration.millis(500),
				this.showFullscreen);
		faa.setFromValue(0.0);
		faa.setToValue(1.0);
		faa.setAutoReverse(true);
		faa.play();

	}

	// Methoden zur Weitergabe der Werte an andere Klassen

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

	// Methoden zur BefÃ¼llung der Dienstplanliste

	public void fillDienstplanliste() {

		// DienstplÃ¤ne -- Choicebox wird gefÃ¼llt

		DBMatching dbm = new DBMatching();

		this.dienstplanliste.clear();

		this.dienstplanliste = dbm.createDienstplanObject();
		for (int i = 0; i < this.dienstplanliste.size(); i++) {
			this.dienstplanliste.get(i).setName(" Dienstplan " + (i + 1));
		}

		if (this.firstLowergrafikErstellt == false) {
			this.DPlan.setItems(FXCollections
					.observableArrayList(dienstplanliste.get(0).getName()));
			for (int i = 1; i < dienstplanliste.size(); i++) {
				this.DPlan.getItems().add(dienstplanliste.get(i).getName());
			}
		}

	}

	// Methoden zur BefÃ¼llung der Umlaufplanliste

	public void fillUmlaufplanliste() {

		// UmlaufplÃ¤ne -- Choicebox wird gefÃ¼llt

		DBMatching dbm = new DBMatching();

		this.umlaufplanliste.clear();

		this.umlaufplanliste = dbm.createUmlaufplanObject();
		for (int i = 0; i < this.umlaufplanliste.size(); i++) {
			this.umlaufplanliste.get(i).setName(" Umlaufplan " + (i + 1));
		}

		if (this.firstUppergrafikErstellt == false) {
			this.UPlan.setItems(FXCollections
					.observableArrayList(umlaufplanliste.get(0).getName()));
			for (int i = 1; i < umlaufplanliste.size(); i++) {
				this.UPlan.getItems().add(umlaufplanliste.get(i).getName());
			}
		}

	}

	// Methoden zur Festsetzung der Main

	public MainApplication getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApplication mainApp) {
		this.mainApp = mainApp;
	}

}
