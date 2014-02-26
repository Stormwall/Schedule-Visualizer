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

//	private ArrayList<DutyStatistics> dutyStatistics;
	
	
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
	public void exportDienstplanStatistik(ArrayList<DutyStatistics> dutyStatistics, File file){
		
		String textOutput = new String();
		try {
			
			writer = new FileWriter(file);

			textOutput = "dutyID;totalNumberOfTrips;totalDutyTime;numberOfServiceTrips;averageLengthOfServiceTrips;numberOfDeadheads;averageLengthOfDeadheads;numberOfWaitings;averageLengthOfWaitings;numberOfPullOuts;averageLengthOfPullOuts;numberOfPullIns;averageLengthOfPullIns;serviceTime/totalDutyTime;numberOfLinesPerDuty;numberOfVehiclesPerDuty";
			writer.write(textOutput + lineSeparator);
			for (int i = 0; i < dutyStatistics.size(); i++) {
				textOutput = dutyStatistics.get(i).dutyHilfsID+";"+dutyStatistics.get(i).dutytotalNumberOfTrips+";"+dutyStatistics.get(i).dutytotalRunTime+";"+dutyStatistics.get(i).dutynumberOfServiceTrips+";"+dutyStatistics.get(i).dutyaverageLengthOfServiceTrips+";"+dutyStatistics.get(i).dutynumberOfDeadheads+";"+dutyStatistics.get(i).dutyaverageLengthOfDeadheads+";"+dutyStatistics.get(i).dutynumberOfWaitings+";"+dutyStatistics.get(i).dutyaverageLengthOfWaitings+";"+dutyStatistics.get(i).dutynumberOfPullOuts+";"+dutyStatistics.get(i).dutyaverageLengthOfPullOuts+";"+dutyStatistics.get(i).dutynumberOfPullIns+";"+dutyStatistics.get(i).dutyaverageLengthOfPullIns+";"+dutyStatistics.get(i).dutyserviceTimetotalDutyTimeRatio+";"+dutyStatistics.get(i).dutynumberOfVehicles;
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
	
	public void exportUmlaufplanStatistik(ArrayList<BlockStatistics> blockstatistics, File file){
		
		String textOutput = new String();
		try {
			
			writer = new FileWriter(file);

			textOutput = "blockID;totalNumberOfTrips;totalRunTime;numberOfServiceTrips;averageLengthOfServiceTrips;numberOfDeadheads;averageLengthOfDeadheads;numberOfWaitings;averageLengthOfWaitings;numberOfPullOuts;averageLengthOfPullOuts;numberOfPullIns;averageLengthOfPullIns;serviceTime/totalRunTime;numberOfLinesPerBlock";
			writer.write(textOutput + lineSeparator);
			for (int i = 0; i < blockstatistics.size(); i++) {
				//BlockID fehlt noch!!
				textOutput=blockstatistics.get(i).blockID+";"+blockstatistics.get(i).blocktotalNumberOfTrips+";"+blockstatistics.get(i).blocktotalRunTime+";"+blockstatistics.get(i).blocknumberOfServiceTrips+";"+blockstatistics.get(i).blockaverageLengthOfServiceTrips+";"+blockstatistics.get(i).blocknumberOfDeadheads+";"+blockstatistics.get(i).blockaverageLengthOfDeadheads+";"+blockstatistics.get(i).blocknumberOfWaitings+";"+blockstatistics.get(i).blockaverageLengthOfWaitings+";"+blockstatistics.get(i).blocknumberOfPullOuts+";"+blockstatistics.get(i).blockaverageLengthOfPullOuts+";"+blockstatistics.get(i).blocknumberOfPullIns+";"+blockstatistics.get(i).blockaverageLengthOfPullIns+";"+blockstatistics.get(i).blockserviceTimetotalBlockTimeRatio+";"+blockstatistics.get(i).blocknumberOfLines;
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

}
