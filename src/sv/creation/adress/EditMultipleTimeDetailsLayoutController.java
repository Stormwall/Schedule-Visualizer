package sv.creation.adress;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sv.creation.adress.util.StringSplitter;

public class EditMultipleTimeDetailsLayoutController {
	
	private Stage dialogStage;

	// Strukturobjekte der Stage

	@FXML
	private TextField stunde;
	@FXML
	private TextField minute;
	@FXML
	private ChoiceBox<Integer> elementChoicebox;
	@FXML
	private ChoiceBox<String> addOrMinus;
	@FXML
	private CheckBox beforeCheckbox;
	@FXML
	private CheckBox afterCheckbox;
	@FXML
	private Label elementIDLabel;

	// Arbeitsobjekte der Stage

	private String verschiebung;
	private int[] result = new int[6];
	private int before;
	private int after;
	private int elementID;

	// Referenz zur MainApp

	private MainApplication mainApp;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {
		
		// Ausgangsbelegung
		
		this.beforeCheckbox.setSelected(true);
		this.addOrMinus.setItems(FXCollections.observableArrayList(
			    "+", "-"));
		
		this.beforeCheckbox.selectedProperty().addListener(
				new ChangeListener<Boolean>() {
					public void changed(ObservableValue<? extends Boolean> ov,
							Boolean old_val, Boolean new_val) {
						// Handhabung wenn die Checkbox angewaehlt wird
						if (new_val == true) {
							afterCheckbox.setSelected(false);
							elementChoicebox.getItems().clear();
							if (before != 0) {
								for (int i = 0; i < before; i++) {
									elementChoicebox.getItems().add(i+1);
								}
							}
							
						}						
					}
				});
		this.afterCheckbox.selectedProperty().addListener(
				new ChangeListener<Boolean>() {
					public void changed(ObservableValue<? extends Boolean> ov,
							Boolean old_val, Boolean new_val) {
						// Handhabung wenn die Checkbox angewaehlt wird
						if (new_val == true) {
							beforeCheckbox.setSelected(false);
							elementChoicebox.getItems().clear();
							if (after != 0) {
								for (int i = 0; i < after; i++) {
									elementChoicebox.getItems().add(i+1);
								}
							}
						}						
					}
				});

	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		
		boolean fehler = false;
		
		// Methoden zur Fehlerbehebung und Formatierung

				int startStunde = 0;
				String hour = "";
				try {
					startStunde = Integer.parseInt(this.stunde.getText());
					if (startStunde < 24) {
						if (startStunde < 10) {
							hour = ("0" + String.valueOf(startStunde));
						} else {
							hour = String.valueOf(startStunde);
						}
					} else {
						String fehlerA = "Das ist keine Stunde";
						String fehlerB = "Falsche Eingabe ?";
						String fehlerC = "Fehler";
						this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
						hour = "0";
						fehler = true;
					}
				} catch (NumberFormatException e) {
					String fehlerA = "Das ist keine Stunde";
					String fehlerB = "Falsche Eingabe ?";
					String fehlerC = "Fehler";
					this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
					fehler = true;
				}
				int startMinute = 0;
				String minute = "";
				try {
					startMinute = Integer.parseInt(this.minute.getText());
					if (startMinute < 60) {
						if (startMinute < 10) {
							minute = ("0" + String.valueOf(startMinute));
						} else {
							minute = String.valueOf(startMinute);
						}
					} else {
						String fehlerA = "Das ist keine Minute";
						String fehlerB = "Falsche Eingabe ?";
						String fehlerC = "Fehler";
						this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
						minute = "0";
						fehler = true;
					}
				} catch (NumberFormatException e) {
					String fehlerA = "Das ist keine Minute";
					String fehlerB = "Falsche Eingabe ?";
					String fehlerC = "Fehler";
					this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
					fehler = true;
				}
				// Übergabe der Ergebnisse
				
				if (fehler == false && this.elementChoicebox.getSelectionModel().getSelectedItem()!=null && this.addOrMinus.getSelectionModel().getSelectedItem()!= null) {
					this.result[0]=1;
					if (this.beforeCheckbox.isSelected()) {
						this.result[1]=0;
					} else {
						this.result[1]=1;
					}
					this.result[2]=this.elementChoicebox.getSelectionModel().getSelectedItem();
					this.result[3] = Integer.parseInt(hour);
					this.result[4] = Integer.parseInt(minute);
					if (this.addOrMinus.getSelectionModel().getSelectedIndex() == 0) {
						this.result[5]=0;
					} else {
						this.result[5]=1;
					}
					dialogStage.close();
				}
		
		
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



	public Stage getDialogStage() {
		return dialogStage;
	}

	public String getVerschiebung() {
		return verschiebung;
	}

	public void setVerschiebung(String verschiebung) {
		this.verschiebung = verschiebung;
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

	public int getBefore() {
		return before;
	}

	public void setBefore(int before) {
		this.before = before;
		
		this.elementChoicebox.getItems().clear();
		if (before != 0) {
			for (int i = 0; i < before; i++) {
				this.elementChoicebox.getItems().add(i+1);
			}
		}
	}

	public int getAfter() {
		return after;
	}

	public void setAfter(int after) {
		this.after = after;
	}

	public int getElementID() {
		return elementID;
	}

	public void setElementID(int elementID) {
		this.elementID = elementID;
		
		this.elementIDLabel.setText(String.valueOf(elementID));
	}

	public int[] getResult() {
		return result;
	}
	

}
