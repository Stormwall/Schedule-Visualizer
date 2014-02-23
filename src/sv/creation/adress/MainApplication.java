package sv.creation.adress;

import java.io.IOException;
import java.util.ArrayList;

import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.model.Umlaufplan;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Dialogs;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainApplication extends Application {

	private Stage primaryStage;

	private BorderPane rootLayout;
	MainLayoutController controller = null;

	// Stageübergabeobjekte

	private String startzeit;
	private String endzeit;

	// Load Rootlayout and handle scenes

	@Override
	public void start(Stage primaryStage) {

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle(" Schedule-Visualizer - "
				+ System.getProperty("user.name"));
		this.primaryStage.getIcons().add(
				new Image("file:resources/images/IconFinal.png"));

		try {
			// Load the root layout from the fxml file
			FXMLLoader loader = new FXMLLoader(
					MainApplication.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			// Give the controller access to the main app
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);

			Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
			primaryStage.setX(bounds.getMinX());
			primaryStage.setY(bounds.getMinY());
			primaryStage.setWidth(bounds.getWidth());
			primaryStage.setHeight(bounds.getHeight());

			primaryStage.show();
		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}
		showMainScene();
	}

	// Initiate MainLayout fxml

	public void showMainScene() {

		try {
			// Load the fxml file and set into the center of the main layout
			FXMLLoader loader = new FXMLLoader(
					MainApplication.class.getResource("view/MainLayout.fxml"));
			AnchorPane overviewPage = (AnchorPane) loader.load();
			rootLayout.setCenter(overviewPage);

			// Give the controller access to the main app
			MainLayoutController controller = loader.getController();
			this.controller = controller;
			controller.setMainApp(this);

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}
	}

	// Initiate Kennzahlen fxml

	public void showKennzahlen() {

		try {

			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					MainApplication.class
							.getResource("view/KennzahlenLayout.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Kennzahlen");
			dialogStage.getIcons().add(
					new Image("file:resources/images/IconFinal.png"));
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the controller
			KennzahlenLayoutController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(this);
			

			dialogStage.show();

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}
	}
	
	public void showStatistikUPlanSingle(ArrayList<Umlaufplan> umlaufplanliste, Fahrplan fahrplan) {

		try {

			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					MainApplication.class
							.getResource("view/StatistikenUPlanSingleLayout.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Kennzahlen");
			dialogStage.getIcons().add(
					new Image("file:resources/images/IconFinal.png"));
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setFullScreen(true);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the controller
			StatistikenUPlanSingleLayoutController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setFahrplan(fahrplan);
			controller.setUmlaufplanliste(umlaufplanliste);

			dialogStage.show();

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}
	}
	
	public void showStatistikDPlanSingle(ArrayList<Dienstplan> dienstplanliste, Fahrplan fahrplan) {

		try {

			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					MainApplication.class
							.getResource("view/StatistikenDPlanSingleLayout.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Kennzahlen");
			dialogStage.getIcons().add(
					new Image("file:resources/images/IconFinal.png"));
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setFullScreen(true);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the controller
			StatistikenDPlanSingleLayoutController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setFahrplan(fahrplan);
			controller.setDienstplanliste(dienstplanliste);

			dialogStage.show();

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}
	}

	// Initiate DatenbankLayout fxml

	public void showDatenbank() {

		try {

			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					MainApplication.class
							.getResource("view/DatenbankLayout.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Datenbank");
			dialogStage.getIcons().add(
					new Image("file:resources/images/IconFinal.png"));
			// dialogStage.setResizable(false);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the controller
			DatenbankLayoutController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(this);

			dialogStage.showAndWait();
			
			this.controller.fillUmlaufplanliste();
			this.controller.fillDienstplanliste();
			this.controller.fillFahrplanliste();

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}

	}

	// Initiate Fullscreen fxml

	public void showFullScreenGraphicUmlaufplan(Umlaufplan umlaufplan) {

		try {

			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					MainApplication.class
							.getResource("view/FullScreenLayoutUmlaufplan.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(
					new Image("file:resources/images/IconFinal.png"));
			dialogStage.setFullScreen(true);
			dialogStage.initModality(Modality.NONE);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the controller
			FullScreenLayoutControllerUmlaufplan controller = loader
					.getController();
			controller.setDialogStage(dialogStage);
			controller.setUmlaufplan(umlaufplan);
			controller.setMainApp(this);

			dialogStage.show();

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loade
			e.printStackTrace();
		}
	}

	// Initiate Fullscreen fxml

	public void showFullScreenGraphicDienstplan(Dienstplan dienstplan) {

		try {

			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					MainApplication.class
							.getResource("view/FullScreenLayoutDienstplan.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(
					new Image("file:resources/images/IconFinal.png"));
			dialogStage.setFullScreen(true);
			dialogStage.initModality(Modality.NONE);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the controller
			FullScreenLayoutControllerDienstplan controller = loader
					.getController();
			controller.setDialogStage(dialogStage);
			controller.setDienstplan(dienstplan);
			controller.setMainApp(this);

			dialogStage.show();

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loade
			e.printStackTrace();
		}
	}

	// Initiate Fullscreen fxml

	public void showFullScreenFahrplan(Fahrplan fahrplan, int auswahl) {

		try {

			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					MainApplication.class
							.getResource("view/FahrplanGraphicLayout.fxml"));
			GridPane page = (GridPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(
					new Image("file:resources/images/IconFinal.png"));
			dialogStage.setFullScreen(true);
			dialogStage.initModality(Modality.NONE);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the controller
			FahrplanGraphicLayoutController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setFahrplan(fahrplan);
			controller.setErstauswahl(auswahl);
			controller.setMainApp(this);

			dialogStage.show();

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loade
			e.printStackTrace();
		}
	}
	
	// Initiate Color Choice fxml

	public void showColorChoice() {

		try {

			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					MainApplication.class
							.getResource("view/ColorChoiceLayout.fxml"));
			GridPane page = (GridPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.getIcons().add(
					new Image("file:resources/images/IconFinal.png"));
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.setResizable(false);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the controller
			ColorChoiceLayoutController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(this);

			dialogStage.show();

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loade
			e.printStackTrace();
		}
	}

	// Initiate KostenLayout fxml

	public void showKostenU(ArrayList<Umlaufplan> umlaufplanliste, ArrayList<Fahrplan> fahrplanliste) {

		try {

			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					MainApplication.class.getResource("view/KostenLayoutU.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Kostenkalkulation");
			dialogStage.getIcons().add(
					new Image("file:resources/images/IconFinal.png"));
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setResizable(false);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the controller
			KostenLayoutControllerU controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setUmlaufplanliste(umlaufplanliste);
			controller.setFahrplanliste(fahrplanliste);

			dialogStage.show();
		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}
	}
	
	// Initiate KostenLayout fxml

	public void showKostenD(ArrayList<Dienstplan> dienstplanChoiceliste, ArrayList<Fahrplan> fahrplanliste) {

		try {

			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					MainApplication.class.getResource("view/KostenLayoutD.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Kostenkalkulation");
			dialogStage.getIcons().add(
					new Image("file:resources/images/IconFinal.png"));
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.setResizable(false);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the controller
			KostenLayoutControllerD controller = loader.getController();
			controller.setDialogStage(dialogStage);

			dialogStage.show();
		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}
	}
	
	// Initiate P-Vergleich Layout fxml

	public void showP_Vergleich(Fahrplan fahrplan) {

		try {

			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					MainApplication.class.getResource("view/P_VergleichLayout.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Kostenkalkulation");
			dialogStage.getIcons().add(
					new Image("file:resources/images/IconFinal.png"));
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.setResizable(false);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the controller
			P_VergleichLayoutController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setFahrplan(fahrplan);

			dialogStage.show();
		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
		}
	}

	// Initiate Edit U-Plan fxml

	public boolean showEditUPlan(Umlaufplan umlaufplan) {

		try {

			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					MainApplication.class.getResource("view/EditU_Plan.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Umlaufplanbearbeitung");
			dialogStage.getIcons().add(
					new Image("file:resources/images/IconFinal.png"));
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the controller with all data
			EditU_PlanController controller = loader.getController();
			controller.setUmlaufplan(umlaufplan);
			controller.setMainApp(this);
			controller.setDialogStage(dialogStage);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
			return false;
		}

	}

	// Initiate Edit U-Plan fxml

	public boolean showEditDPlan(Dienstplan dienstplan) {

		try {

			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					MainApplication.class.getResource("view/EditD_Plan.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Dienstplanbearbeitung");
			dialogStage.getIcons().add(
					new Image("file:resources/images/IconFinal.png"));
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the controller with all data
			EditD_PlanController controller = loader.getController();
			controller.setDienstplan(dienstplan);
			controller.setMainApp(this);
			controller.setDialogStage(dialogStage);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
			return false;
		}

	}

	// Initiate Edit Time Details fxml

	public boolean showEditTimeDetails(String startzeit, String endzeit) {

		try {

			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					MainApplication.class
							.getResource("view/EditTimeDetails.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Editor");
			dialogStage.getIcons().add(
					new Image("file:resources/images/IconFinal.png"));
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setResizable(false);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the controller with all data
			EditTimeDetailsLayoutController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setStartzeit(startzeit);
			controller.setEndzeit(endzeit);
			controller.setMainApp(this);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
			return false;
		}

	}

	public int[] showEditMultipleTimeDetails(int before, int after,
			int elementID) {

		try {

			// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(
					MainApplication.class
							.getResource("view/EditMultipleTimeDetails.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Editor");
			dialogStage.getIcons().add(
					new Image("file:resources/images/IconFinal.png"));
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setResizable(false);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the controller with all data
			EditMultipleTimeDetailsLayoutController controller = loader
					.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(this);
			controller.setBefore(before);
			controller.setAfter(after);
			controller.setElementID(elementID);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.getResult();

		} catch (IOException e) {
			// Exception gets thrown if the fxml file could not be loaded
			e.printStackTrace();
			return null;
		}
	}

	// Fehlermeldung bei nicht erstellter Grafik

	public void fehlerMeldung(String fehlermeldungA, String fehlermeldungB,
			String fehlermeldungC) {

		Dialogs.showErrorDialog(primaryStage, fehlermeldungA, fehlermeldungB,
				fehlermeldungC);
	}
	public void warnungsMeldung(String fehlermeldungA, String fehlermeldungB,
			String fehlermeldungC) {

		Dialogs.showWarningDialog(primaryStage, fehlermeldungA, fehlermeldungB,
				fehlermeldungC);
	}
	public void informationMeldung(String fehlermeldungA, String fehlermeldungB,
			String fehlermeldungC) {

		Dialogs.showInformationDialog(primaryStage, fehlermeldungA, fehlermeldungB,
				fehlermeldungC);
	}
	public String inputMeldung(String fehlermeldungA, String fehlermeldungB,
			String fehlermeldungC) {

		return Dialogs.showInputDialog(primaryStage, fehlermeldungA, fehlermeldungB,
				fehlermeldungC);
	}

	// Übergabemethoden der Stageobjekte

	public String getStartzeit() {
		return startzeit;
	}

	public void setStartzeit(String startzeit) {
		this.startzeit = startzeit;
	}

	public String getEndzeit() {
		return endzeit;
	}

	public void setEndzeit(String endzeit) {
		this.endzeit = endzeit;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	// Main Method

	public static void main(String[] args) {
		launch(args);
	}
}
