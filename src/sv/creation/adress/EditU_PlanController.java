package sv.creation.adress;

import sv.creation.adress.model.Blockelement;
import sv.creation.adress.model.Umlaufplan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EditU_PlanController {

	// Strukturelemente dieser Stage
	@FXML
	private ChoiceBox<String> blockChoice ;
	@FXML
	private TableView<Blockelement> elementsTable = new TableView<Blockelement>();
	@FXML
	private TableColumn<Blockelement, Integer> blockEleCol; 
	@FXML
	private TableColumn<Blockelement, String> startzeitCol; 
	@FXML
	private TableColumn<Blockelement, String> endzeitCol; 
	@FXML
	private TableColumn<Blockelement, Integer> eleTypeCol; 
	@FXML
	private TableColumn<Blockelement, Integer> blockCol;
	@FXML
	private TableColumn<Blockelement, String> dauerCol;
	
	
	// Verbindung zum MainLayout
	private Stage dialogStage;
	
	// Arbeitsobjekte der Stage
	
	private Umlaufplan umlaufplan;
	

	  /**
	  * Initializes the controller class. This method is automatically called
	  * after the fxml file has been loaded.
	  */
	  
	@FXML
	  private void initialize() {
		
		// Anordnung der Tabelle
		elementsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		
	}
		
	// Methode zum Befüllen der Tabelle
		@FXML
		public void fillTable(){
			
			blockEleCol.setCellValueFactory(new PropertyValueFactory<Blockelement, Integer>("id"));
			startzeitCol.setCellValueFactory(new PropertyValueFactory<Blockelement, String>("depTime"));
			endzeitCol.setCellValueFactory(new PropertyValueFactory<Blockelement, String>("arrTime"));
			eleTypeCol.setCellValueFactory(new PropertyValueFactory<Blockelement, Integer>("elementType"));
			blockCol.setCellValueFactory(new PropertyValueFactory<Blockelement, Integer>("blockID"));
			dauerCol.setCellValueFactory(new PropertyValueFactory<Blockelement, String>("driveTime"));
						
			
			// Hereinladen der Daten
			
			ObservableList<Blockelement> data = FXCollections.observableArrayList();
			
			for (int i = 0; i < umlaufplan.getFahrtZuUmlauf().size(); i++) {
				data.add(umlaufplan.getFahrtZuUmlauf().get(i));
			}
			
			elementsTable.setItems(data);
			
		}
	
	// Methode zum Beenden des PopUp
	@FXML
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

	public Umlaufplan getUmlaufplan() {
		return umlaufplan;
	}

	public void setUmlaufplan(Umlaufplan umlaufplan) {
		this.umlaufplan = umlaufplan;
	}
	
	
	
}

	

