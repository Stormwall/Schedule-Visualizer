package sv.creation.adress;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Dialogs;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApplication extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	private String username;
	
	// Load Rootlayout and handle scenes

	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
	    this.primaryStage.setTitle(" Schedule-Visualizer");
	    
	      try {
	          // Load the root layout from the fxml file
	          FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("view/RootLayout.fxml"));
	          rootLayout = (BorderPane) loader.load();
	          Scene scene = new Scene(rootLayout);
	          primaryStage.setScene(scene);
	          
	          	// Give the controller access to the main app
				RootLayoutController controller = loader.getController();
				controller.setMainApp(this);
				
	          primaryStage.show();
	      } catch (IOException e) {
	          // Exception gets thrown if the fxml file could not be loaded
	          e.printStackTrace();
	      }
	      
	      username = Dialogs.showInputDialog(primaryStage, "Tragen Sie bitte Ihren Namen ein:", "Benutzer", "Identifizierung");	      	      
	      showMainScene();	      
	}
		
	// Initiate mainview fxml
	
	public void showMainScene(){
		
		try {
	          // Load the fxml file and set into the center of the main layout
	          FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("view/MainLayout.fxml"));
	          AnchorPane overviewPage = (AnchorPane) loader.load();
	          rootLayout.setCenter(overviewPage);	          
	       	          
	      } catch (IOException e) {
	          // Exception gets thrown if the fxml file could not be loaded
	          e.printStackTrace();
	      }
	}
	
	// Initiate Handbuch fxml

	public void showHandbuch(){
		
		try {
			
			// Load the fxml file and create a new stage for the popup
		FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("view/HandbuchLayout.fxml"));		
		AnchorPane page = (AnchorPane) loader.load();		
	    Stage dialogStage = new Stage();	    
	    dialogStage.setTitle("Handbuch");	    	    
	    dialogStage.initModality(Modality.WINDOW_MODAL);	    
	    dialogStage.initOwner(primaryStage);	    
	    Scene scene = new Scene(page);	    
	    dialogStage.setScene(scene);	 
	    
	    // Set the controller
	    HandbuchLayoutController controller = loader.getController();
	    controller.setDialogStage(dialogStage);
	
	    dialogStage.show();	    

	  } catch (IOException e) {
	    // Exception gets thrown if the fxml file could not be loaded
	    e.printStackTrace();
	  }


	}
	
	
	
	
	
	// Main Method

	public static void main(String[] args) {
		launch(args);
	}
}
