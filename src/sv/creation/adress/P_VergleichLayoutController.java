package sv.creation.adress;

import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.util.Kennzahlenberechnung;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class P_VergleichLayoutController {

	//  Arbeitsvariablen
	@FXML
	private Label ms;
	@FXML
	private Label m1;
	@FXML
	private Label m2;
	@FXML
	private Label m3;
	@FXML
	private Label m4;
	@FXML
	private Label m5;
	@FXML
	private Label ds;
	@FXML
	private Label d1;
	@FXML
	private Label d2;
	@FXML
	private Label d3;
	@FXML
	private Label d4;
	@FXML
	private Label d5;
	@FXML
	private Label mis;
	@FXML
	private Label mi1;
	@FXML
	private Label mi2;
	@FXML
	private Label mi3;
	@FXML
	private Label mi4;
	@FXML
	private Label mi5;
	@FXML
	private Label dos;
	@FXML
	private Label do1;
	@FXML
	private Label do2;
	@FXML
	private Label do3;
	@FXML
	private Label do4;
	@FXML
	private Label do5;
	@FXML
	private Label fs;
	@FXML
	private Label f1;
	@FXML
	private Label f2;
	@FXML
	private Label f3;
	@FXML
	private Label f4;
	@FXML
	private Label f5;
	@FXML
	private Label result;

	// Referenz zur MainApp
	private Stage dialogStage;
	private MainApplication mainApp;
	private Fahrplan fahrplan;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {

	}

	// Methode zum Berrechnen der Kosten

	public void berrechnen() {

		Kennzahlenberechnung kb = new Kennzahlenberechnung();

		double[][]matrix  = kb.matrixFahrplan(this.fahrplan);
		int[][] pMatrix=kb.erstelleMatrixpVergleich(this.fahrplan);
		double result = kb.berechneMatrixPVergleich(pMatrix);
		
		
		this.ms.setText(""+matrix[1][0]);
		this.ds.setText(""+matrix[2][0]);
		this.mis.setText(""+matrix[3][0]);
		this.dos.setText(""+matrix[4][0]);
		this.fs.setText(""+matrix[5][0]);
		this.m1.setText(""+matrix[1][1]);
		this.m2.setText(""+matrix[1][2]);
		this.m3.setText(""+matrix[1][3]);
		this.m4.setText(""+matrix[1][4]);
		this.m5.setText(""+matrix[2][5]);
		this.d1.setText(""+matrix[2][1]);
		this.d2.setText(""+matrix[2][2]);
		this.d3.setText(""+matrix[2][3]);
		this.d4.setText(""+matrix[2][4]);
		this.d5.setText(""+matrix[2][5]);
		this.mi1.setText(""+matrix[3][1]);
		this.mi2.setText(""+matrix[3][2]);
		this.mi3.setText(""+matrix[3][3]);
		this.mi4.setText(""+matrix[3][4]);
		this.mi5.setText(""+matrix[3][5]);
		this.do1.setText(""+matrix[4][1]);
		this.do2.setText(""+matrix[4][2]);
		this.do3.setText(""+matrix[4][3]);
		this.do4.setText(""+matrix[4][4]);
		this.do5.setText(""+matrix[4][5]);
		this.f1.setText(""+matrix[5][1]);
		this.f2.setText(""+matrix[5][2]);
		this.f3.setText(""+matrix[5][3]);
		this.f4.setText(""+matrix[5][4]);
		this.f5.setText(""+matrix[5][5]);
		
		//Hier matrix einfuegen
		this.result.setText(String.valueOf(result));
	}

	// Methode zum Beenden des PopUp

	public void endStage() {

		dialogStage.close();
	}

	public MainApplication getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApplication mainApp) {
		this.mainApp = mainApp;
	}

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public Fahrplan getFahrplan() {
		return fahrplan;
	}

	public void setFahrplan(Fahrplan fahrplan) {
		this.fahrplan = fahrplan;
		
		berrechnen();
	}

}
