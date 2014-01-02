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

}
