package sv.creation.adress.util;

import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Umlaufplan;

public class Export {

	public void exportDienstplan(Dienstplan dienstplan) {

		String textOutput = new String();
		textOutput = "*\n* Diensttypen\n*\n$DUTY:DutyID;DutyType";
		for (int i = 0; i < dienstplan.getDuty().size(); i++) {
			textOutput += "\n" + dienstplan.getDuty().get(i).getId() + ";"
					+ dienstplan.getDuty().get(i).getType();
		}
		textOutput += "*\n* Dienste\n* (mit ElementType: 1 = Servicefahrt, 2 = Leerfahrt Haltestellen, 3 = Fahrt ins Depot, 4 = Fahrt aus dem Depot, 5 = Vorbereitung, 6 = Nachbereitung, 7 = Transfer, 8 = Pause, 9 = Warten, 10 = LayoverTime)\n$DUTYELEMENT:DutyID;BlockID;ServiceJourneyID;FromStopID;ToStopID;DepTime;ArrTime;ElementType;ServiceJourneyCode";
		for (int i = 0; i < dienstplan.getDutyelement().size(); i++) {
			textOutput += "\n" + dienstplan.getDutyelement().get(i).getDutyID()
					+ ";" + dienstplan.getDutyelement().get(i).getBlockID()
					+ ";"
					+ dienstplan.getDutyelement().get(i).getServiceJourneyID()
					+ ";" + dienstplan.getDutyelement().get(i).getFromStopID()
					+ ";" + dienstplan.getDutyelement().get(i).getToStopID()
					+ ";0:" + dienstplan.getDutyelement().get(i).getDepTime()
					+ ":00;0:"
					+ dienstplan.getDutyelement().get(i).getArrTime() + ":00;"
					+ dienstplan.getDutyelement().get(i).getElementType() + ";";
		}
		System.out.println(textOutput);
	}
	
	public void exportUmlaufplan(Umlaufplan umlaufplan) {

		String textOutput = new String();
		textOutput = "*\n* Umlaeufe\n*\n$BLOCK:ID;VehTypeID;DepotID";
		for (int i = 0; i < umlaufplan.getUmlauf().size(); i++) {
			textOutput += "\n" + umlaufplan.getUmlauf().get(i).getId()+";"
					+ umlaufplan.getUmlauf().get(i).getvehTypeID()+";"
					+ umlaufplan.getUmlauf().get(i).getdepotID();
		}
		textOutput += "*\n* FahrtZuUmlauf\n*\n$BLOCKELEMENT:BlockID;ServiceJourneyID;FromStopID;ToStopID;DepTime;ArrTime;ElementType;ServiceJourneyCode";
		for (int i = 0; i < umlaufplan.getFahrtZuUmlauf().size(); i++) {
			textOutput += "\n"+ umlaufplan.getFahrtZuUmlauf().get(i).getBlockID()
					+ ";"
					+ umlaufplan.getFahrtZuUmlauf().get(i).getServiceJourneyID()
					+ ";" + umlaufplan.getFahrtZuUmlauf().get(i).getFromStopID()
					+ ";" + umlaufplan.getFahrtZuUmlauf().get(i).getToStopID()
					+ ";0:" + umlaufplan.getFahrtZuUmlauf().get(i).getDepTime()
					+ ":00;0:"
					+ umlaufplan.getFahrtZuUmlauf().get(i).getArrTime() + ":00;"
					+ umlaufplan.getFahrtZuUmlauf().get(i).getElementType() + ";";
		}
		System.out.println(textOutput);
	}

}
