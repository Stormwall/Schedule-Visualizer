package sv.creation.adress;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApplication extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
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
	          primaryStage.show();
	      } catch (IOException e) {
	          // Exception gets thrown if the fxml file could not be loaded
	          e.printStackTrace();
	      }
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
	
	// Open Helper fxml
	
	public void showHandbuchScene(){
		
		
	}
	
	
	
	
	
	
	// Main Method

	public static void main(String[] args) {
		launch(args);
	}
}
