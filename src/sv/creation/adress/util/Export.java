package sv.creation.adress.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Umlaufplan;

public class Export {
	
	FileWriter writer;
	String lineSeparator = System.getProperty("line.separator");

	private ArrayList<DutyStatistics> dutyStatistics;
	
	// Creates the txt-file of a selected dienstplan
	public void exportDienstplan(Dienstplan dienstplan, File exportFile) {

		String textOutput = new String();

		try {
			
			writer = new FileWriter(exportFile);

			textOutput = "*"+lineSeparator+"* Diensttypen"+lineSeparator+"*"+lineSeparator+"$DUTY:DutyID;DutyType";
			writer.write(textOutput + lineSeparator);
			for (int i = 0; i < dienstplan.getDuty().size(); i++) {
				textOutput = dienstplan.getDuty().get(i).getId() + ";"
						+ dienstplan.getDuty().get(i).getType();
				writer.write(textOutput + lineSeparator);
			}
			textOutput = "*"+lineSeparator+"* Dienste"+lineSeparator+"* (mit ElementType: 1 = Servicefahrt, 2 = Leerfahrt Haltestellen, 3 = Fahrt ins Depot, 4 = Fahrt aus dem Depot, 5 = Vorbereitung, 6 = Nachbereitung, 7 = Transfer, 8 = Pause, 9 = Warten, 10 = LayoverTime)\n$DUTYELEMENT:DutyID;BlockID;ServiceJourneyID;FromStopID;ToStopID;DepTime;ArrTime;ElementType;ServiceJourneyCode";
			writer.write(textOutput + lineSeparator);
			for (int i = 0; i < dienstplan.getDutyelement().size(); i++) {
				textOutput = dienstplan.getDutyelement().get(i).getDutyID()
						+ ";"
						+ dienstplan.getDutyelement().get(i).getBlockID()
						+ ";"
						+ dienstplan.getDutyelement().get(i)
								.getServiceJourneyID() + ";"
						+ dienstplan.getDutyelement().get(i).getFromStopID()
						+ ";"
						+ dienstplan.getDutyelement().get(i).getToStopID()
						+ ";0:"
						+ dienstplan.getDutyelement().get(i).getDepTime()
						+ ":00;0:"
						+ dienstplan.getDutyelement().get(i).getArrTime()
						+ ":00;"
						+ dienstplan.getDutyelement().get(i).getElementType()
						+ ";";
				writer.write(textOutput + lineSeparator);
			}

			writer.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// Creates the txt-file of a selected umlaufplan
	public void exportUmlaufplan(Umlaufplan umlaufplan, File exportFile) {

		String textOutput = new String();

		try {
			writer = new FileWriter(exportFile);
			
			

			textOutput = "*"+lineSeparator+"* Umlaeufe"+lineSeparator+"*"+lineSeparator+"$BLOCK:ID;VehTypeID;DepotID";
			writer.write(textOutput + lineSeparator);
			for (int i = 0; i < umlaufplan.getUmlauf().size(); i++) {
				textOutput = umlaufplan.getUmlauf().get(i).getId() + ";"
						+ umlaufplan.getUmlauf().get(i).getvehTypeID() + ";"
						+ umlaufplan.getUmlauf().get(i).getdepotID();
				writer.write(textOutput + lineSeparator);
			}
			textOutput = "*"+lineSeparator+"* FahrtZuUmlauf"+lineSeparator+"*"+lineSeparator+"$BLOCKELEMENT:BlockID;ServiceJourneyID;FromStopID;ToStopID;DepTime;ArrTime;ElementType;ServiceJourneyCode";
			writer.write(textOutput + lineSeparator);
			for (int i = 0; i < umlaufplan.getFahrtZuUmlauf().size(); i++) {
				textOutput = umlaufplan.getFahrtZuUmlauf().get(i).getBlockID()
						+ ";"
						+ umlaufplan.getFahrtZuUmlauf().get(i)
								.getServiceJourneyID() + ";"
						+ umlaufplan.getFahrtZuUmlauf().get(i).getFromStopID()
						+ ";"
						+ umlaufplan.getFahrtZuUmlauf().get(i).getToStopID()
						+ ";0:"
						+ umlaufplan.getFahrtZuUmlauf().get(i).getDepTime()
						+ ":00;0:"
						+ umlaufplan.getFahrtZuUmlauf().get(i).getArrTime()
						+ ":00;"
						+ umlaufplan.getFahrtZuUmlauf().get(i).getElementType()
						+ ";";
				writer.write(textOutput + lineSeparator);
			}
			writer.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	//Erstellt für die Dienstplan Statistik ein CSV-File
	public void exportDienstplanStatistik(DutyStatistics dutystatistics, File file){
		
		String textOutput = new String();
		try {
			
			writer = new FileWriter(file);
			
			ObservableList<DutyStatistics> data = FXCollections
					.observableArrayList();

			for (int i = 0; i < this.dutyStatistics.size(); i++) {
				data.add(this.dutyStatistics.get(i));
			}

			textOutput = "dutyID;totalNumberOfTrips;totalDutyTime;numberOfServiceTrips;averageLengthOfServiceTrips;numberOfDeadheads;averageLengthOfDeadheads;numberOfWaitings;averageLengthOfWaitings;numberOfPullOuts;averageLengthOfPullOuts;numberOfPullIns;averageLengthOfPullIns;serviceTime/totalDutyTime;numberOfLinesPerDuty;numberOfVehiclesPerDuty";
			writer.write(textOutput + lineSeparator);
			for (int i = 0; i < this.dutyStatistics.size(); i++) {
				textOutput = this.dutyStatistics.get(i).dutyHilfsID+";"+this.dutyStatistics.get(i).dutytotalNumberOfTrips+";"+this.dutyStatistics.get(i).dutytotalRunTime+";"+this.dutyStatistics.get(i).dutynumberOfServiceTrips+";"+this.dutyStatistics.get(i).dutyaverageLengthOfServiceTrips+";"+this.dutyStatistics.get(i).dutynumberOfDeadheads+";"+this.dutyStatistics.get(i).dutyaverageLengthOfDeadheads+";"+this.dutyStatistics.get(i).dutynumberOfWaitings+";"+this.dutyStatistics.get(i).dutyaverageLengthOfWaitings+";"+this.dutyStatistics.get(i).dutynumberOfPullOuts+";"+this.dutyStatistics.get(i).dutyaverageLengthOfPullOuts+";"+this.dutyStatistics.get(i).dutynumberOfPullIns+";"+this.dutyStatistics.get(i).dutyaverageLengthOfPullIns+";"+this.dutyStatistics.get(i).dutyserviceTimetotalDutyTimeRatio+";"+this.dutyStatistics.get(i).dutynumberOfVehicles;
				writer.write(textOutput + lineSeparator);
			}
//			for (int i = 0; i < dienstplan.getDuty().size(); i++) {
//				textOutput = dienstplan.getDuty().get(i).getId() + ";"
//						+ dienstplan.getDuty().get(i).getType();
//				writer.write(textOutput + lineSeparator);
//			}
//			textOutput = "*"+lineSeparator+"* Dienste"+lineSeparator+"* (mit ElementType: 1 = Servicefahrt, 2 = Leerfahrt Haltestellen, 3 = Fahrt ins Depot, 4 = Fahrt aus dem Depot, 5 = Vorbereitung, 6 = Nachbereitung, 7 = Transfer, 8 = Pause, 9 = Warten, 10 = LayoverTime)\n$DUTYELEMENT:DutyID;BlockID;ServiceJourneyID;FromStopID;ToStopID;DepTime;ArrTime;ElementType;ServiceJourneyCode";
//			writer.write(textOutput + lineSeparator);
//			for (int i = 0; i < dienstplan.getDutyelement().size(); i++) {
//				textOutput = dienstplan.getDutyelement().get(i).getDutyID()
//						+ ";"
//						+ dienstplan.getDutyelement().get(i).getBlockID()
//						+ ";"
//						+ dienstplan.getDutyelement().get(i)
//								.getServiceJourneyID() + ";"
//						+ dienstplan.getDutyelement().get(i).getFromStopID()
//						+ ";"
//						+ dienstplan.getDutyelement().get(i).getToStopID()
//						+ ";0:"
//						+ dienstplan.getDutyelement().get(i).getDepTime()
//						+ ":00;0:"
//						+ dienstplan.getDutyelement().get(i).getArrTime()
//						+ ":00;"
//						+ dienstplan.getDutyelement().get(i).getElementType()
//						+ ";";
//				writer.write(textOutput + lineSeparator);
//			}
			writer.write(textOutput + lineSeparator);
			writer.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
