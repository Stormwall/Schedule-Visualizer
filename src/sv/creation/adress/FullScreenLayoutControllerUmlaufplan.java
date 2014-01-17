package sv.creation.adress;

import sv.creation.adress.model.Umlaufplan;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FullScreenLayoutControllerUmlaufplan {

	private Stage dialogStage;
	private Umlaufplan umlaufplan;

	// Strukturvariablen
	@FXML
	private AnchorPane GraphicPane;
	@FXML
	private AnchorPane xPane;
	@FXML
	private AnchorPane yPane;

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {

		// An dieser Stelle wird der Bildschirm des Nutzers ausgelesen und die
		// Werte belegt

		this.GraphicPane.widthProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {

					}
				});

		this.GraphicPane.heightProperty().addListener(
				new ChangeListener<Number>() {
					@Override
					public void changed(
							ObservableValue<? extends Number> observableValue,
							Number oldSceneWidth, Number newSceneWidth) {

					}
				});
	}

	// Methode zum Beenden des PopUp

	public void endStage() {

		dialogStage.close();
	}

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
