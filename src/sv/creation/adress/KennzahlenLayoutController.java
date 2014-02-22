package sv.creation.adress;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import sv.creation.adress.database.DBMatching;
import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.model.Umlaufplan;
import sv.creation.adress.util.Kennzahlenberechnung;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

public class KennzahlenLayoutController {

	private Stage dialogStage;

	// Referenz zur MainApp

	private MainApplication mainApp;

	// Arbeitsvariablen der Stage

	@FXML
	private ScrollPane umlaufplanPane;
	@FXML
	private ScrollPane dienstplanPane;
	@FXML
	private ScrollPane fahrplanPane;
	@FXML
	private Label auswahlUmlaufplan;
	@FXML
	private Label auswahlDienstplan;
	@FXML
	private Label auswahlFahrplan;
	@FXML
	private Label auswahlSzenario;
	@FXML
	private Button dWU;
	@FXML
	private Button dWD;
	@FXML
	private Button dMU;
	@FXML
	private Button dMD;
	@FXML
	private Button kostenU;
	@FXML
	private Button kostenD;
	@FXML
	private Button statisitkU;
	@FXML
	private Button statistikD;

	// Bau der Zugriffslisten

	private ArrayList<Umlaufplan> umlaufplanliste = new ArrayList<Umlaufplan>();
	private ArrayList<Dienstplan> dienstplanliste = new ArrayList<Dienstplan>();
	private ArrayList<Fahrplan> fahrplanliste = new ArrayList<Fahrplan>();

	private ArrayList<Umlaufplan> umlaufplanChoiceliste = new ArrayList<Umlaufplan>();
	private ArrayList<Dienstplan> dienstplanChoiceliste = new ArrayList<Dienstplan>();
	private ArrayList<Fahrplan> fahrplanChoiceliste = new ArrayList<Fahrplan>();

	// Erstellung der Detailtableviews

	private TableView<Umlaufplan> detailsUmlaufTable = new TableView<Umlaufplan>();
	private TableView<Dienstplan> detailsDienstTable = new TableView<Dienstplan>();
	private TableView<Fahrplan> detailsFahrplanTable = new TableView<Fahrplan>();

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */

	@FXML
	private void initialize() {

		// Befuellung der Planlisten zur Auswahl der Plaene

		fillUmlaufplanliste();
		fillDienstplanliste();
		fillFahrplanliste();

		// Belegen der Tableviews

		createTableViewUmlauf();
		createTableViewDienst();
		createTableViewFahrplan();

		// Sets the Standardelement condition of the Interface

		detailsUmlaufTable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		detailsDienstTable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		detailsFahrplanTable
				.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

	}

	/**
	 * Opens the Statistikview Umlaufplan.
	 */
	@FXML
	private void handleStatistikenUplan() {

		if (this.umlaufplanChoiceliste.size() == 1) {
			this.mainApp.showStatistikUPlanSingle(this.umlaufplanChoiceliste);
		} else {
			String fehlerA = "Es wurde noch Element ausgew�hlt";
			String fehlerB = "Welche Statistik soll angezeigt werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}
	}

	/**
	 * Opens the Statistikview Dienstplan.
	 */
	@FXML
	private void handleStatistikenDplan() {

		if (this.dienstplanChoiceliste.size() == 1) {
			this.mainApp.showStatistikDPlanSingle(this.dienstplanChoiceliste);

		} else {
			String fehlerA = "Es wurde noch Element ausgew�hlt";
			String fehlerB = "Welche Statistik soll angezeigt werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}
	}

	/**
	 * Opens the durchschnittl. U w.
	 */
	@FXML
	private void handleDurchschnittWU() {

		// Arbeitsvariablen der Methode
		Kennzahlenberechnung calc = new Kennzahlenberechnung();
		Fahrplan fahrplan = null;
		boolean matchFplan = false;
		int i = 0;
		// Zuordnung des PLans

		while (matchFplan == false) {
			if (this.umlaufplanChoiceliste.get(0).getFahrplanID() == this.fahrplanliste
					.get(i).getId()) {
				fahrplan = this.fahrplanliste.get(i);
				matchFplan = true;
			}
			++i;
		}

		// Berrechnung der Plaene
		double result = calc.berechneDurschnittlicheWiederholrateUmlaufplanAll(
				this.umlaufplanChoiceliste, fahrplan);

		String fehlerA = "Die durschnittliche Wiederholrate der eingebenen Umlaufplaene lautet : "
				+ result;
		String fehlerB = "Ihr Ergebnis";
		String fehlerC = "Ausgabe";
		this.mainApp.informationMeldung(fehlerA, fehlerB, fehlerC);

	}

	/**
	 * Opens the durchschnittl. D w.
	 */
	@FXML
	private void handleDurchschnittWD() {

		// Arbeitsvariablen der Methode
		Kennzahlenberechnung calc = new Kennzahlenberechnung();
		Fahrplan fahrplan = null;
		boolean matchFplan = false;
		int i = 0;
		// Zuordnung des PLans

		while (matchFplan == false) {
			if (this.dienstplanChoiceliste.get(0).getFahrplanID() == this.fahrplanliste
					.get(i).getId()) {
				fahrplan = this.fahrplanliste.get(i);
				matchFplan = true;
			}
			++i;
		}

		// Berrechnung der Plaene
		double result = calc.berechneDurschnittlicheWiederholrateDienstplanAll(
				this.dienstplanChoiceliste, fahrplan);

		String fehlerA = "Die durschnittliche Wiederholrate der eingebenen Dienstplaene lautet : "
				+ result;
		String fehlerB = "Ihr Ergebnis";
		String fehlerC = "Ausgabe";
		this.mainApp.informationMeldung(fehlerA, fehlerB, fehlerC);

	}

	/**
	 * Opens the durchschnittl. U w.
	 */
	@FXML
	private void handleDistanzMU() {

		// Arbeitsvariablen der Methode
		Kennzahlenberechnung calc = new Kennzahlenberechnung();
		Fahrplan fahrplan = null;
		boolean matchFplan = false;
		int i = 0;
		// Zuordnung des PLans

		while (matchFplan == false) {
			if (this.umlaufplanChoiceliste.get(0).getFahrplanID() == this.fahrplanliste
					.get(i).getId()) {
				fahrplan = this.fahrplanliste.get(i);
				matchFplan = true;
			}
			++i;
		}

		// Berrechnung der Plaene
		double result = calc.berechneDistanzVehSchedule(this.umlaufplanChoiceliste, fahrplan);

		String fehlerA = "Das Distanzma� der eingebenen Umlaufplaene lautet : "
				+ result;
		String fehlerB = "Ihr Ergebnis";
		String fehlerC = "Ausgabe";
		this.mainApp.informationMeldung(fehlerA, fehlerB, fehlerC);

	}

	/**
	 * Opens the durchschnittl. D w.
	 */
	@FXML
	private void handleDistanzMD() {

		// Arbeitsvariablen der Methode
		Kennzahlenberechnung calc = new Kennzahlenberechnung();
		Fahrplan fahrplan = null;
		boolean matchFplan = false;
		int i = 0;
		// Zuordnung des PLans

		while (matchFplan == false) {
			if (this.dienstplanChoiceliste.get(0).getFahrplanID() == this.fahrplanliste
					.get(i).getId()) {
				fahrplan = this.fahrplanliste.get(i);
				matchFplan = true;
			}
			++i;
		}

		// Berrechnung der Plaene
		double result = calc.berechneDistanzCrewSchedule(this.dienstplanChoiceliste, fahrplan);

		String fehlerA = "Das Distanzma� der eingebenen Dienstplaene lautet : "
				+ result;
		String fehlerB = "Ihr Ergebnis";
		String fehlerC = "Ausgabe";
		this.mainApp.informationMeldung(fehlerA, fehlerB, fehlerC);

	}

	/**
	 * Opens the Kostenview.
	 */
	@FXML
	private void handleKostenU() {
		this.mainApp.showKostenU();
	}

	/**
	 * Opens the Kostenview.
	 */
	@FXML
	private void handleKostenD() {
		this.mainApp.showKostenD();
	}

	/**
	 * UmlaufChoiceAdd.
	 */
	@FXML
	private void chooseUmlaufplan() {

		if (this.detailsUmlaufTable.getSelectionModel().getSelectedItem() != null) {

			boolean checkList = true;

			for (int i = 0; i < this.umlaufplanChoiceliste.size(); i++) {
				if (this.detailsUmlaufTable.getSelectionModel()
						.getSelectedItem().getName() == this.umlaufplanChoiceliste
						.get(i).getName()) {
					checkList = false;
				}
			}
			if (checkList) {

				this.umlaufplanChoiceliste.add(this.detailsUmlaufTable
						.getSelectionModel().getSelectedItem());

				Set<String> setInput = new TreeSet<String>();
				for (int i = 0; i < this.umlaufplanChoiceliste.size(); i++) {
					setInput.add(this.umlaufplanChoiceliste.get(i).getName());
				}

				StringBuilder sb = new StringBuilder();
				for (String tempString : setInput) {
					sb.append("").append(tempString).append(",");
				}

				this.auswahlUmlaufplan.setText(sb.toString());

				if (this.umlaufplanChoiceliste.size() == 1
						&& this.statisitkU.getOpacity() != 1) {
					FadeTransition fa = new FadeTransition(
							Duration.millis(1000), this.statisitkU);
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					this.statisitkU.setDisable(false);
				}
				if (this.umlaufplanChoiceliste.size() > 1
						&& this.statisitkU.getOpacity() == 1) {
					FadeTransition faa = new FadeTransition(
							Duration.millis(1000), this.statisitkU);
					faa.setFromValue(1.0);
					faa.setToValue(0.0);
					faa.setAutoReverse(true);
					faa.play();
					this.statisitkU.setDisable(true);
				}
				if (this.umlaufplanChoiceliste.size() >= 1
						&& this.kostenU.getOpacity() != 1) {
					FadeTransition fa = new FadeTransition(
							Duration.millis(1000), this.kostenU);
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					this.kostenU.setDisable(false);
				}
				if (this.umlaufplanChoiceliste.size() == 2
						&& this.dMU.getOpacity() != 1) {
					FadeTransition fa = new FadeTransition(
							Duration.millis(1000), this.dMU);
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					this.dMU.setDisable(false);
				}
				if (this.umlaufplanChoiceliste.size() > 2
						&& this.dMU.getOpacity() == 1) {
					FadeTransition faa = new FadeTransition(
							Duration.millis(1000), this.dMU);
					faa.setFromValue(1.0);
					faa.setToValue(0.0);
					faa.setAutoReverse(true);
					faa.play();
					this.dMU.setDisable(true);
				}
				if (this.umlaufplanChoiceliste.size() >= 2
						&& this.dWU.getOpacity() != 1) {
					FadeTransition fa = new FadeTransition(
							Duration.millis(1000), this.dWU);
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					this.dWU.setDisable(false);
				}
			} else {
				String fehlerA = "Das Element wurde bereits ausgewaehlt.";
				String fehlerB = "Was soll ausgewaehlt werden ?";
				String fehlerC = "Fehler";
				this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
			}
		} else {
			String fehlerA = "Es wurde noch Element ausgew�hlt";
			String fehlerB = "Was soll ausgewaehlt werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}
	}

	/**
	 * UmlaufChoiceDelete.
	 */
	@FXML
	private void deleteUmlaufplan() {

		if (this.umlaufplanChoiceliste.isEmpty()) {
			String fehlerA = "Es wurde noch Element ausgew�hlt";
			String fehlerB = "Was soll geloescht werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		} else {

			this.umlaufplanChoiceliste.remove(((this.umlaufplanChoiceliste
					.size() - 1)));

			Set<String> setInput = new TreeSet<String>();
			for (int i = 0; i < this.umlaufplanChoiceliste.size(); i++) {
				setInput.add(this.umlaufplanChoiceliste.get(i).getName());
			}

			StringBuilder sb = new StringBuilder();
			for (String tempString : setInput) {
				sb.append("").append(tempString).append(",");
			}

			this.auswahlUmlaufplan.setText(sb.toString());
			if (this.umlaufplanChoiceliste.size() == 0
					&& this.statisitkU.getOpacity() == 1) {
				FadeTransition faa = new FadeTransition(Duration.millis(1000),
						this.statisitkU);
				faa.setFromValue(1.0);
				faa.setToValue(0.0);
				faa.setAutoReverse(true);
				faa.play();
				this.statisitkU.setDisable(true);

				FadeTransition faaa = new FadeTransition(Duration.millis(1000),
						this.kostenU);
				faaa.setFromValue(1.0);
				faaa.setToValue(0.0);
				faaa.setAutoReverse(true);
				faaa.play();
				this.kostenU.setDisable(true);
			}

			if (this.umlaufplanChoiceliste.size() == 1
					&& this.statisitkU.getOpacity() != 1) {
				FadeTransition fa = new FadeTransition(Duration.millis(1000),
						this.statisitkU);
				fa.setFromValue(0.0);
				fa.setToValue(1.0);
				fa.setAutoReverse(true);
				fa.play();
				this.statisitkU.setDisable(false);
			}
			if (this.umlaufplanChoiceliste.size() > 1
					&& this.statisitkU.getOpacity() == 1) {
				FadeTransition faa = new FadeTransition(Duration.millis(1000),
						this.statisitkU);
				faa.setFromValue(1.0);
				faa.setToValue(0.0);
				faa.setAutoReverse(true);
				faa.play();
				this.statisitkU.setDisable(true);
			}
			if (this.umlaufplanChoiceliste.size() >= 1
					&& this.kostenU.getOpacity() != 1) {
				FadeTransition fa = new FadeTransition(Duration.millis(1000),
						this.kostenU);
				fa.setFromValue(0.0);
				fa.setToValue(1.0);
				fa.setAutoReverse(true);
				fa.play();
				this.kostenU.setDisable(false);
			}
			if (this.umlaufplanChoiceliste.size() == 2
					&& this.dMU.getOpacity() != 1) {
				FadeTransition fa = new FadeTransition(Duration.millis(1000),
						this.dMU);
				fa.setFromValue(0.0);
				fa.setToValue(1.0);
				fa.setAutoReverse(true);
				fa.play();
				this.dMU.setDisable(false);
			}
			if (this.umlaufplanChoiceliste.size() != 2
					&& this.dMU.getOpacity() == 1) {
				FadeTransition faa = new FadeTransition(Duration.millis(1000),
						this.dMU);
				faa.setFromValue(1.0);
				faa.setToValue(0.0);
				faa.setAutoReverse(true);
				faa.play();
				this.dMU.setDisable(true);
			}
			if (this.umlaufplanChoiceliste.size() >= 2
					&& this.dWU.getOpacity() != 1) {
				FadeTransition fa = new FadeTransition(Duration.millis(1000),
						this.dWU);
				fa.setFromValue(0.0);
				fa.setToValue(1.0);
				fa.setAutoReverse(true);
				fa.play();
				this.dWU.setDisable(false);
			}
			if (this.umlaufplanChoiceliste.size() < 2
					&& this.dWU.getOpacity() == 1) {
				FadeTransition faa = new FadeTransition(Duration.millis(1000),
						this.dWU);
				faa.setFromValue(1.0);
				faa.setToValue(0.0);
				faa.setAutoReverse(true);
				faa.play();
				this.dWU.setDisable(true);
			}
		}
	}

	/**
	 * DutyChoiceAdd.
	 */
	@FXML
	private void chooseDienstplan() {

		if (this.detailsDienstTable.getSelectionModel().getSelectedItem() != null) {

			boolean checkList = true;

			for (int i = 0; i < this.dienstplanChoiceliste.size(); i++) {
				if (this.detailsDienstTable.getSelectionModel()
						.getSelectedItem().getName() == this.dienstplanChoiceliste
						.get(i).getName()) {
					checkList = false;
				}
			}
			if (checkList) {

				this.dienstplanChoiceliste.add(this.detailsDienstTable
						.getSelectionModel().getSelectedItem());

				Set<String> setInput = new TreeSet<String>();
				for (int i = 0; i < this.dienstplanChoiceliste.size(); i++) {
					setInput.add(this.dienstplanChoiceliste.get(i).getName());
				}

				StringBuilder sb = new StringBuilder();
				for (String tempString : setInput) {
					sb.append("").append(tempString).append(",");
				}

				this.auswahlDienstplan.setText(sb.toString());

				if (this.dienstplanChoiceliste.size() == 1
						&& this.statistikD.getOpacity() != 1) {
					FadeTransition fa = new FadeTransition(
							Duration.millis(1000), this.statistikD);
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					this.statistikD.setDisable(false);
				}
				if (this.dienstplanChoiceliste.size() > 1
						&& this.statistikD.getOpacity() == 1) {
					FadeTransition faa = new FadeTransition(
							Duration.millis(1000), this.statistikD);
					faa.setFromValue(1.0);
					faa.setToValue(0.0);
					faa.setAutoReverse(true);
					faa.play();
					this.statistikD.setDisable(true);
				}
				if (this.dienstplanChoiceliste.size() >= 1
						&& this.kostenD.getOpacity() != 1) {
					FadeTransition fa = new FadeTransition(
							Duration.millis(1000), this.kostenD);
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					this.kostenD.setDisable(false);
				}
				if (this.dienstplanChoiceliste.size() == 2
						&& this.dMD.getOpacity() != 1) {
					FadeTransition fa = new FadeTransition(
							Duration.millis(1000), this.dMD);
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					this.dMD.setDisable(false);
				}
				if (this.dienstplanChoiceliste.size() != 2
						&& this.dMD.getOpacity() == 1) {
					FadeTransition faa = new FadeTransition(
							Duration.millis(1000), this.dMD);
					faa.setFromValue(1.0);
					faa.setToValue(0.0);
					faa.setAutoReverse(true);
					faa.play();
					this.dMD.setDisable(true);
				}
				if (this.dienstplanChoiceliste.size() >= 2
						&& this.dWD.getOpacity() != 1) {
					FadeTransition fa = new FadeTransition(
							Duration.millis(1000), this.dWD);
					fa.setFromValue(0.0);
					fa.setToValue(1.0);
					fa.setAutoReverse(true);
					fa.play();
					this.dWD.setDisable(false);
				}
			} else {
				String fehlerA = "Das Element wurde bereits ausgewaehlt.";
				String fehlerB = "Was soll ausgewaehlt werden ?";
				String fehlerC = "Fehler";
				this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
			}
		} else {
			String fehlerA = "Es wurde noch Element ausgew�hlt";
			String fehlerB = "Was soll ausgewaehlt werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}

	}

	/**
	 * DutyChoiceDelete.
	 */
	@FXML
	private void deleteDienstplan() {

		if (this.dienstplanChoiceliste.isEmpty()) {
			String fehlerA = "Es wurde noch Element ausgew�hlt";
			String fehlerB = "Was soll geloescht werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		} else {
			this.dienstplanChoiceliste.remove(((this.dienstplanChoiceliste
					.size() - 1)));

			Set<String> setInput = new TreeSet<String>();
			for (int i = 0; i < this.dienstplanChoiceliste.size(); i++) {
				setInput.add(this.dienstplanChoiceliste.get(i).getName());
			}

			StringBuilder sb = new StringBuilder();
			for (String tempString : setInput) {
				sb.append("").append(tempString).append(",");
			}

			this.auswahlDienstplan.setText(sb.toString());

			if (this.dienstplanChoiceliste.size() == 0
					&& this.statistikD.getOpacity() == 1) {
				FadeTransition faa = new FadeTransition(Duration.millis(1000),
						this.statistikD);
				faa.setFromValue(1.0);
				faa.setToValue(0.0);
				faa.setAutoReverse(true);
				faa.play();
				this.statistikD.setDisable(true);

				FadeTransition faaa = new FadeTransition(Duration.millis(1000),
						this.kostenD);
				faaa.setFromValue(1.0);
				faaa.setToValue(0.0);
				faaa.setAutoReverse(true);
				faaa.play();
				this.kostenD.setDisable(true);
			}
			if (this.dienstplanChoiceliste.size() == 1
					&& this.statistikD.getOpacity() != 1) {
				FadeTransition fa = new FadeTransition(Duration.millis(1000),
						this.statistikD);
				fa.setFromValue(0.0);
				fa.setToValue(1.0);
				fa.setAutoReverse(true);
				fa.play();
				this.statistikD.setDisable(false);
			}
			if (this.dienstplanChoiceliste.size() > 1
					&& this.statistikD.getOpacity() == 1) {
				FadeTransition faa = new FadeTransition(Duration.millis(1000),
						this.statistikD);
				faa.setFromValue(1.0);
				faa.setToValue(0.0);
				faa.setAutoReverse(true);
				faa.play();
				this.statistikD.setDisable(true);
			}
			if (this.dienstplanChoiceliste.size() >= 1
					&& this.kostenD.getOpacity() != 1) {
				FadeTransition fa = new FadeTransition(Duration.millis(1000),
						this.kostenD);
				fa.setFromValue(0.0);
				fa.setToValue(1.0);
				fa.setAutoReverse(true);
				fa.play();
				this.kostenD.setDisable(false);
			}
			if (this.dienstplanChoiceliste.size() == 2
					&& this.dMD.getOpacity() != 1) {
				FadeTransition fa = new FadeTransition(Duration.millis(1000),
						this.dMD);
				fa.setFromValue(0.0);
				fa.setToValue(1.0);
				fa.setAutoReverse(true);
				fa.play();
				this.dMD.setDisable(false);
			}
			if (this.dienstplanChoiceliste.size() != 2
					&& this.dMD.getOpacity() == 1) {
				FadeTransition faa = new FadeTransition(Duration.millis(1000),
						this.dMD);
				faa.setFromValue(1.0);
				faa.setToValue(0.0);
				faa.setAutoReverse(true);
				faa.play();
				this.dMD.setDisable(true);
			}
			if (this.dienstplanChoiceliste.size() >= 2
					&& this.dWD.getOpacity() != 1) {
				FadeTransition fa = new FadeTransition(Duration.millis(1000),
						this.dWD);
				fa.setFromValue(0.0);
				fa.setToValue(1.0);
				fa.setAutoReverse(true);
				fa.play();
				this.dWD.setDisable(false);
			}
			if (this.dienstplanChoiceliste.size() < 2
					&& this.dWD.getOpacity() == 1) {
				FadeTransition faa = new FadeTransition(Duration.millis(1000),
						this.dWD);
				faa.setFromValue(1.0);
				faa.setToValue(0.0);
				faa.setAutoReverse(true);
				faa.play();
				this.dWD.setDisable(true);
			}
		}

	}

	/**
	 * FahrplanChoiceAdd.
	 */
	@FXML
	private void chooseFahrplan() {

		this.fahrplanChoiceliste.clear();

		if (this.detailsFahrplanTable.getSelectionModel().getSelectedItem() != null) {
			this.fahrplanChoiceliste.add(this.detailsFahrplanTable
					.getSelectionModel().getSelectedItem());
			this.auswahlFahrplan.setText(fahrplanChoiceliste.get(0)
					.getBezeichnung());
		} else {
			String fehlerA = "Es wurde noch Element ausgew�hlt";
			String fehlerB = "Was soll ausgewaehlt werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		}

	}

	/**
	 * FahrplanChoiceDelete.
	 */
	@FXML
	private void deleteFahrplan() {

		if (this.fahrplanChoiceliste.isEmpty()) {

			String fehlerA = "Es wurde noch Element ausgew�hlt";
			String fehlerB = "Was soll geloescht werden ?";
			String fehlerC = "Fehler";
			this.mainApp.fehlerMeldung(fehlerA, fehlerB, fehlerC);
		} else {
			this.fahrplanChoiceliste.clear();
			this.auswahlFahrplan.setText("");
		}
	}

	// Methode zum Beenden des PopUp

	public void endStage() {

		dialogStage.close();
	}

	/**
	 * Builds Umlaufplantableview.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void createTableViewUmlauf() {

		this.umlaufplanPane.setContent(null);

		this.detailsUmlaufTable.setEditable(true);

		TableColumn<Umlaufplan, String> uBezeichnung = new TableColumn<Umlaufplan, String>(
				"Bezeichnung");
		TableColumn<Umlaufplan, Integer> uID = new TableColumn<Umlaufplan, Integer>(
				"ID");
		TableColumn<Umlaufplan, Integer> ufahrplanID = new TableColumn<Umlaufplan, Integer>(
				"FahrplanID");
		TableColumn<Umlaufplan, String> uUploadDate = new TableColumn<Umlaufplan, String>(
				"Upload");

		uBezeichnung
				.setCellValueFactory(new PropertyValueFactory<Umlaufplan, String>(
						"name"));
		uID.setCellValueFactory(new PropertyValueFactory<Umlaufplan, Integer>(
				"id"));
		ufahrplanID
				.setCellValueFactory(new PropertyValueFactory<Umlaufplan, Integer>(
						"fahrplanID"));
		uUploadDate
				.setCellValueFactory(new PropertyValueFactory<Umlaufplan, String>(
						"date"));

		uBezeichnung.prefWidthProperty().bind(uBezeichnung.widthProperty());
		uID.prefWidthProperty().bind(uID.widthProperty());
		ufahrplanID.prefWidthProperty().bind(uUploadDate.widthProperty());
		uUploadDate.prefWidthProperty().bind(uUploadDate.widthProperty());

		ObservableList<Umlaufplan> data = FXCollections.observableArrayList();

		for (int i = 0; i < this.umlaufplanliste.size(); i++) {
			data.add(this.umlaufplanliste.get(i));
		}

		this.detailsUmlaufTable.setItems(data);
		this.detailsUmlaufTable.getColumns().addAll(uBezeichnung, uID,
				ufahrplanID, uUploadDate);
		this.umlaufplanPane.setContent(this.detailsUmlaufTable);

	}

	/**
	 * Builds Dienstplantableview.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void createTableViewDienst() {

		this.dienstplanPane.setContent(null);

		this.detailsDienstTable.setEditable(true);

		TableColumn<Dienstplan, String> dBezeichnung = new TableColumn<Dienstplan, String>(
				"Bezeichnung");
		TableColumn<Dienstplan, Integer> dID = new TableColumn<Dienstplan, Integer>(
				"ID");
		TableColumn<Dienstplan, Integer> dfahrplanID = new TableColumn<Dienstplan, Integer>(
				"FahrplanID");
		TableColumn<Dienstplan, String> dUploadDate = new TableColumn<Dienstplan, String>(
				"Upload");

		dBezeichnung
				.setCellValueFactory(new PropertyValueFactory<Dienstplan, String>(
						"name"));
		dID.setCellValueFactory(new PropertyValueFactory<Dienstplan, Integer>(
				"id"));
		dfahrplanID
				.setCellValueFactory(new PropertyValueFactory<Dienstplan, Integer>(
						"fahrplanID"));
		dUploadDate
				.setCellValueFactory(new PropertyValueFactory<Dienstplan, String>(
						"date"));

		dBezeichnung.prefWidthProperty().bind(dBezeichnung.widthProperty());
		dID.prefWidthProperty().bind(dID.widthProperty());
		dfahrplanID.prefWidthProperty().bind(dfahrplanID.widthProperty());
		dUploadDate.prefWidthProperty().bind(dUploadDate.widthProperty());

		ObservableList<Dienstplan> data = FXCollections.observableArrayList();

		for (int i = 0; i < this.dienstplanliste.size(); i++) {
			data.add(this.dienstplanliste.get(i));
		}

		this.detailsDienstTable.setItems(data);
		this.detailsDienstTable.getColumns().addAll(dBezeichnung, dID,
				dfahrplanID, dUploadDate);
		this.dienstplanPane.setContent(this.detailsDienstTable);

	}

	/**
	 * Builds Fahrplantableview.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void createTableViewFahrplan() {

		this.fahrplanPane.setContent(null);

		this.detailsFahrplanTable.setEditable(true);

		TableColumn<Fahrplan, String> fBezeichnung = new TableColumn<Fahrplan, String>(
				"Bezeichnung");
		TableColumn<Fahrplan, Integer> fID = new TableColumn<Fahrplan, Integer>(
				"ID");
		TableColumn<Fahrplan, String> fUploadDate = new TableColumn<Fahrplan, String>(
				"Upload");

		fBezeichnung
				.setCellValueFactory(new PropertyValueFactory<Fahrplan, String>(
						"bezeichnung"));
		fID.setCellValueFactory(new PropertyValueFactory<Fahrplan, Integer>(
				"id"));
		fUploadDate
				.setCellValueFactory(new PropertyValueFactory<Fahrplan, String>(
						"date"));

		fBezeichnung.prefWidthProperty().bind(fBezeichnung.widthProperty());
		fID.prefWidthProperty().bind(fID.widthProperty());
		fUploadDate.prefWidthProperty().bind(fUploadDate.widthProperty());

		ObservableList<Fahrplan> data = FXCollections.observableArrayList();

		for (int i = 0; i < this.fahrplanliste.size(); i++) {
			data.add(this.fahrplanliste.get(i));
		}

		this.detailsFahrplanTable.setItems(data);
		this.detailsFahrplanTable.getColumns().addAll(fBezeichnung, fID,
				fUploadDate);
		this.fahrplanPane.setContent(this.detailsFahrplanTable);

	}

	// Methoden zur Befuellung der Fahrplanliste

	public void fillFahrplanliste() {

		// Fahrplaene -- Choicebox wird gefaellt

		DBMatching dbm = new DBMatching();

		this.fahrplanliste.clear();

		if (dbm.databaseIsEmpty() || dbm.fahrplanIsEmpty()) {

		} else {

			this.fahrplanliste.clear();
			this.fahrplanliste = dbm.createFahrplanObject();
			for (int i = 0; i < this.fahrplanliste.size(); i++) {
				if (this.fahrplanliste.get(i).getBezeichnung() == null) {
					this.fahrplanliste.get(i).setBezeichnung(
							" Fahrplan " + (i + 1));
				}
			}
		}
	}

	// Methoden zur Befüllung der Dienstplanliste

	public void fillDienstplanliste() {

		// Dienstpläne -- Choicebox wird gefüllt

		DBMatching dbm = new DBMatching();

		this.dienstplanliste.clear();

		if (dbm.databaseIsEmpty() || dbm.dienstplanIsEmpty()) {

		} else {
			this.dienstplanliste.clear();

			this.dienstplanliste = dbm.createDienstplanObject();
			for (int i = 0; i < this.dienstplanliste.size(); i++) {
				if (this.dienstplanliste.get(i).getName() == null) {
					this.dienstplanliste.get(i).setName(
							" Dienstplan " + (i + 1));
				}
			}
		}
	}

	// Methoden zur Befuellung der Umlaufplanliste

	public void fillUmlaufplanliste() {

		// Umlaufpläne -- Choicebox wird gefüllt

		DBMatching dbm = new DBMatching();

		this.umlaufplanliste.clear();

		if (dbm.databaseIsEmpty() || dbm.umlaufplanIsEmpty()) {
		} else {
			this.umlaufplanliste = dbm.createUmlaufplanObject();
			for (int i = 0; i < this.umlaufplanliste.size(); i++) {
				if (this.umlaufplanliste.get(i).getName() == null) {
					this.umlaufplanliste.get(i).setName(
							" Umlaufplan " + (i + 1));
				}
			}
		}

	}

	// Zuordnungsmethoden

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
