package sv.creation.adress.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Umlaufplan;

public class Export {

	// Creates the txt-file of a selected dienstplan
	public void exportDienstplan(Dienstplan dienstplan) {

		File exportFile;
		FileWriter writer;

		String textOutput = new String();

		try {
			exportFile = new File(System.getProperty("user.home") + "/"
					+ "Export.txt");
			writer = new FileWriter(exportFile);

			textOutput = "*\n* Diensttypen\n*\n$DUTY:DutyID;DutyType";
			writer.write(textOutput + "\n");
			for (int i = 0; i < dienstplan.getDuty().size(); i++) {
				textOutput = dienstplan.getDuty().get(i).getId() + ";"
						+ dienstplan.getDuty().get(i).getType();
				writer.write(textOutput + "\n");
			}
			textOutput = "*\n* Dienste\n* (mit ElementType: 1 = Servicefahrt, 2 = Leerfahrt Haltestellen, 3 = Fahrt ins Depot, 4 = Fahrt aus dem Depot, 5 = Vorbereitung, 6 = Nachbereitung, 7 = Transfer, 8 = Pause, 9 = Warten, 10 = LayoverTime)\n$DUTYELEMENT:DutyID;BlockID;ServiceJourneyID;FromStopID;ToStopID;DepTime;ArrTime;ElementType;ServiceJourneyCode";
			writer.write(textOutput + "\n");
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
				writer.write(textOutput + "\n");
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
	public void exportUmlaufplan(Umlaufplan umlaufplan) {

		File exportFile;
		FileWriter writer;

		String textOutput = new String();

		try {
			exportFile = new File(System.getProperty("user.home") + "/"
					+ "Export2.txt");
			writer = new FileWriter(exportFile);

			textOutput = "*\n* Umlaeufe\n*\n$BLOCK:ID;VehTypeID;DepotID";
			writer.write(textOutput + "\n");
			for (int i = 0; i < umlaufplan.getUmlauf().size(); i++) {
				textOutput = umlaufplan.getUmlauf().get(i).getId() + ";"
						+ umlaufplan.getUmlauf().get(i).getvehTypeID() + ";"
						+ umlaufplan.getUmlauf().get(i).getdepotID();
				writer.write(textOutput + "\n");
			}
			textOutput = "*\n* FahrtZuUmlauf\n*\n$BLOCKELEMENT:BlockID;ServiceJourneyID;FromStopID;ToStopID;DepTime;ArrTime;ElementType;ServiceJourneyCode";
			writer.write(textOutput + "\n");
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
				writer.write(textOutput + "\n");
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
