package sv.creation.adress;

import sv.creation.adress.util.StringSplitter;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditTimeDetailsLayoutController {

	private Stage dialogStage;

	// Strukturobjekte der Stage

	@FXML
	private TextField startzeitStunde;
	@FXML
	private TextField startzeitMinute;
	@FXML
	private TextField endzeitStunde;
	@FXML
	private TextField endzeitMinute;

	// Arbeitsobjekte der Stage

	private String startzeit;
	private String endzeit;
	private boolean okClicked = false;

	// Referenz zur MainApp

	private MainApplication mainApp;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {

	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		// if (isInputValid()) {

		// Methoden zur Fehlerbehebung und Formatierung

		int startStunde = 0;
		String sS = "";
		try {
			startStunde = Integer.parseInt(this.startzeitStunde.getText());

			if (startStunde < 10) {
				sS = ("0" + String.valueOf(startStunde));
			} else {
				sS = String.valueOf(startStunde);
			}
		} catch (NumberFormatException e) {
			String fehlerA = "Das ist keine Stunde";
			String fehlerB = "Falsche Eingabe ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}
		int startMinute = 0;
		String sM = "";
		try {
			startMinute = Integer.parseInt(this.startzeitMinute.getText());

			if (startMinute < 10) {
				sM = ("0" + String.valueOf(startMinute));
			} else {
				sM = String.valueOf(startMinute);
			}
		} catch (NumberFormatException e) {
			String fehlerA = "Das ist keine Minute";
			String fehlerB = "Falsche Eingabe ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}
		int endStunde = 0;
		String eS = "";
		try {
			endStunde = Integer.parseInt(this.endzeitStunde.getText());

			if (endStunde < 10) {
				eS = ("0" + String.valueOf(endStunde));
			} else {
				eS = String.valueOf(endStunde);
			}
		} catch (NumberFormatException e) {
			String fehlerA = "Das ist keine Stunde";
			String fehlerB = "Falsche Eingabe ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}
		int endMinute = 0;
		String eM = "";
		try {
			endMinute = Integer.parseInt(this.endzeitMinute.getText());

			if (endMinute < 10) {
				eM = ("0" + String.valueOf(endMinute));
			} else {
				eM = String.valueOf(endMinute);
			}
		} catch (NumberFormatException e) {
			String fehlerA = "Das ist keine Minute";
			String fehlerB = "Falsche Eingabe ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}

		this.startzeit = (sS + ":" + sM);
		this.endzeit = (eS + ":" + eM);

		// Übergabe der Werte

		this.mainApp.setStartzeit(this.startzeit);
		this.mainApp.setEndzeit(this.endzeit);

		okClicked = true;
		dialogStage.close();
		// }
	}

	// Methode zum Beenden des PopUp

	public void endStage() {

		dialogStage.close();
	}

	// Belegt die Textfelder mit den ursprünglichen Werten

	public void insertValue(String Value, TextField stunde, TextField minute) {

		// Auslesen der Zeit als Integer
		StringSplitter ss = new StringSplitter();
		int[] zeit = new int[2];
		zeit = ss.intParse(Value);
		int Hour = zeit[0];
		int Min = zeit[1];

		// Belegung der Textfelder
		stunde.setText(String.valueOf(Hour));
		minute.setText(String.valueOf(Min));

	}

	// Zuordnungsmethoden

	public String getStartzeit() {
		return startzeit;
	}

	public void setStartzeit(String startzeit) {
		this.startzeit = startzeit;
		insertValue(this.startzeit, this.startzeitStunde, this.startzeitMinute);
	}

	public String getEndzeit() {
		return endzeit;
	}

	public void setEndzeit(String endzeit) {
		this.endzeit = endzeit;
		insertValue(this.endzeit, this.endzeitStunde, this.endzeitMinute);
	}

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public MainApplication getMainApp() {
		return mainApp;
	}

	public void setMainApp(MainApplication mainApp) {
		this.mainApp = mainApp;
	}

}
