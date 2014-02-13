package sv.creation.adress.util;

import java.util.ArrayList;

import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Duty;
import sv.creation.adress.model.Dutyelement;
import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.model.Umlaufplan;


public class Kennzahlenberechnung {
	
	public double berechneDurschnittlicheWiederholrateDienstplanAll(ArrayList<Dienstplan> dienstplanliste, Fahrplan fahrplan){
		
		int anzahlDutyGesamt=0;
		int anzahlGleicheDuty=0;
		double avgrepeat=0.0;
		
		//Anzahl der Dienste von allen Dienstplänen
		for (int i = 0; i < dienstplanliste.size(); i++) {
			
			if(dienstplanliste.get(i).getFahrplanID()==fahrplan.getId()){
			anzahlDutyGesamt+=dienstplanliste.get(i).getDuty().size();
			}
		}
		
		//Anzahl gleicher Dienste zwischen zwei Dienstplänen
		for (int i = 0; i < dienstplanliste.size(); i++) {
			for (int j = 0; j < dienstplanliste.get(i).getDuty().size(); j++) {
				if(dienstplanliste.get(i).getDuty().get(j)==dienstplanliste.get(i+1).getDuty().get(j+1)){
					anzahlGleicheDuty++;
				}
			}
		}
		//Man darf nicht durch 0 teilen
		if(anzahlGleicheDuty==0){
			anzahlGleicheDuty=anzahlDutyGesamt;
		}
		
		if(anzahlDutyGesamt==0){
			System.out.println("Es müssen mindestens zwei Dienstpläne mit dem gleichen Fahrplan ausgewählt werden!");
		}
		
		//Wenn Nenner 0, dann wird Durchschnitt auf 0 gesetzt
		if(anzahlGleicheDuty==0){
			avgrepeat=0.0;
		}else{
		//Formel für durchschn. Wiederholrate
		avgrepeat=anzahlDutyGesamt/(anzahlDutyGesamt-anzahlGleicheDuty);
		}
		return avgrepeat;
	}
	
	public double berechneDurschnittlicheWiederholrateUmlaufplanAll(ArrayList<Umlaufplan> umlaufplanliste, Fahrplan fahrplan){
		
		int anzahlBlockGesamt=0;
		int anzahlGleicheBlock=0;
		double avgrepeat=0.0;
		
		//Anzahl der Umläufe von allen Umlaufplänen, die zu dem Fahrplan gehören
		for (int i = 0; i < umlaufplanliste.size(); i++) {
			if(umlaufplanliste.get(i).getFahrplanID()==fahrplan.getId()){
			anzahlBlockGesamt+=umlaufplanliste.get(i).getUmlauf().size();
			}
		}
		
		//Anzahl gleicher Umläufe zwischen zwei Umlaufplänen
		for (int i = 0; i < umlaufplanliste.size(); i++) {
			for (int j = 0; j < umlaufplanliste.get(i).getUmlauf().size(); j++) {
				if(umlaufplanliste.get(i).getUmlauf().get(j)==umlaufplanliste.get(i+1).getUmlauf().get(j+1)){
					anzahlGleicheBlock++;
				}
			}
		}
		//Man darf nicht durch 0 teilen
		if(anzahlGleicheBlock==0){
			anzahlGleicheBlock=anzahlBlockGesamt;
		}
		
		if(anzahlBlockGesamt==0){
			System.out.println("Es müssen mindestens zwei Umlaufpläne mit dem gleichen Fahrplan ausgewählt werden!");
		}
		//Wenn Nenner 0, dann wird Durchschnitt auf 0 gesetzt
		if(anzahlGleicheBlock==0){
			avgrepeat=0.0;
		}else{
		//Formel für durchschn. Wiederholrate
		avgrepeat=anzahlBlockGesamt/(anzahlBlockGesamt-anzahlGleicheBlock);
		}
		return avgrepeat;
	}
	
	//Methode bekommt dienstplanliste mit Dienstplänen und dem dazugehörigen Fahrplan
	public ArrayList<Dutyelement> regelmaessigeDutyelement(ArrayList <Dienstplan> dienstplanliste, Fahrplan fahrplan){
		
		ArrayList<Dutyelement> dutyelementList = new ArrayList<Dutyelement>();
		
		for (int i = 0; i < dienstplanliste.size(); i++) {
			for (int j = 0; j < dienstplanliste.get(i).getDuty().size(); j++) {
				for (int j2 = 0; j2 < dienstplanliste.get(i).getDutyelement().size(); j2++) {
					if(Integer.parseInt(dienstplanliste.get(i).getDutyelement().get(j2).getServiceJourneyID())==fahrplan.getDays().get(j2).getTripID()&&fahrplan.getDays().get(j2).getD1()==1&&fahrplan.getDays().get(j2).getD2()==1&&fahrplan.getDays().get(j2).getD3()==1&&fahrplan.getDays().get(j2).getD4()==1&&fahrplan.getDays().get(j2).getD5()==1){
						dutyelementList.add(dienstplanliste.get(i).getDutyelement().get(j2));
					}
				}
			}	
		}
		return dutyelementList;
	}
	
	public double berechneDurschnittlicheWiederholrateDienstplanRegular(ArrayList<Dienstplan> dienstplanliste, ArrayList<ArrayList<ArrayList<String>>> ListPlaeneGesamt, Fahrplan fahrplan){
		
		int anzahlDutyGesamt=0;
		int anzahlGleicheDuty=0;
		double avgrepeat=0.0;
		boolean alleGleich=false;
		
		ArrayList<Dutyelement> dutyelementlist = new ArrayList<Dutyelement>();
		dutyelementlist=regelmaessigeDutyelement(dienstplanliste, fahrplan);
		
		//Anzahl der Dienste von allen Dienstplänen
		for (int i = 0; i < dienstplanliste.size(); i++) {
			if(dienstplanliste.get(i).getFahrplanID()==fahrplan.getId()){
				anzahlDutyGesamt+=dienstplanliste.get(i).getDuty().size();
			}
		}
		//Alle Dienstpläne
		for (int plan = 0; plan < ListPlaeneGesamt.size(); plan++) {
			//Alle Bündel in einem Plan
			for (int buendel = 0; buendel < ListPlaeneGesamt.get(plan).size(); buendel++) {
			//Alle ServiceJounreys im ersten Bündel im ersten Plan
			for (int buendel2 = 0; buendel2 < ListPlaeneGesamt.get(plan+1).size(); buendel2++) {
					//Alle ServiceJourneys im Bündel
					for (int serviceJourney = 0; serviceJourney < ListPlaeneGesamt.get(plan).get(buendel).size(); serviceJourney++) {
						
						if(ListPlaeneGesamt.get(plan).get(buendel).get(serviceJourney)!=ListPlaeneGesamt.get(plan+1).get(buendel2).get(serviceJourney)){
							alleGleich=false;
							break;
						}
						alleGleich=true;
					}
					if(alleGleich){
						anzahlGleicheDuty++;
					}
				
				}
			}
		}
		
		avgrepeat=anzahlDutyGesamt/(anzahlDutyGesamt-anzahlGleicheDuty);
		return avgrepeat;
		
	}
	
	//Erstellt eine ArrayList mit allen Bündeln an ServiceJourneys zu den einzelnen Dienstplänen
	public void erstelleDutyelementList(ArrayList<Dienstplan> dienstplanliste){
		
		ArrayList<ArrayList<ArrayList<String>>> ListPlaeneGesamt = new ArrayList<ArrayList<ArrayList<String>>>();
		ArrayList<ArrayList<String>> ListPlan = new ArrayList<ArrayList<String>>();
		ArrayList<String> serviceJourneyList = new ArrayList<String>();
		
		for (int i = 0; i < dienstplanliste.size(); i++) {
			for (int j = 0; j < dienstplanliste.get(i).getDutyelement().size(); j++) {
				String dutyID=dienstplanliste.get(i).getDutyelement().get(i).getDutyID();
					while(dienstplanliste.get(i).getDutyelement().get(j).getDutyID().equals(dutyID)){	
						serviceJourneyList.add(dienstplanliste.get(i).getDutyelement().get(j).getServiceJourneyID());
					}	
			}
			ListPlan.add(serviceJourneyList);
			ListPlaeneGesamt.add(ListPlan);
			serviceJourneyList.clear();
			ListPlan.clear();
		}
		
	}
	
	
	
	

}