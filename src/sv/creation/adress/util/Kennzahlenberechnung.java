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
		
		//Anzahl der Dienste von allen Dienstpl��nen
		for (int i = 0; i < dienstplanliste.size(); i++) {
			
			if(dienstplanliste.get(i).getFahrplanID()==fahrplan.getId()){
			anzahlDutyGesamt+=dienstplanliste.get(i).getDuty().size();
			}
		}
		
		//Anzahl gleicher Dienste zwischen zwei Dienstpl��nen
		for (int i = 0; i < dienstplanliste.size(); i++) {
			if(i==dienstplanliste.size()-1){
				break;
			}
			for (int j = 0; j < dienstplanliste.get(i).getDuty().size(); j++) {
				for (int j2 = 0; j2 < dienstplanliste.get(i+1).getDuty().size(); j2++) {
					
				if(j==dienstplanliste.get(i).getDuty().size()-1){
				break;
				}
				if(dienstplanliste.get(i).getDuty().get(j)==dienstplanliste.get(i+1).getDuty().get(j2)){
					anzahlGleicheDuty++;
				}
				}
			}
		}
		//Man darf nicht durch 0 teilen
		if(anzahlGleicheDuty==0){
			anzahlGleicheDuty=anzahlDutyGesamt;
		}
		
		if(anzahlDutyGesamt==0){
			System.out.println("Es m��ssen mindestens zwei Dienstpl��ne mit dem gleichen Fahrplan ausgew��hlt werden!");
		}
		
		//Wenn Nenner 0, dann wird Durchschnitt auf 0 gesetzt
		if(anzahlGleicheDuty==0){
			avgrepeat=0.0;
		}else{
		//Formel f��r durchschn. Wiederholrate
		avgrepeat=anzahlDutyGesamt/(anzahlDutyGesamt-anzahlGleicheDuty);
		}
		return avgrepeat;
	}
	
	public double berechneDurschnittlicheWiederholrateUmlaufplanAll(ArrayList<Umlaufplan> umlaufplanliste, Fahrplan fahrplan){
		
		int anzahlBlockGesamt=0;
		int anzahlGleicheBlock=0;
		double avgrepeat=0.0;
		
		//Anzahl der Uml��ufe von allen Umlaufpl��nen, die zu dem Fahrplan geh��ren
		for (int i = 0; i < umlaufplanliste.size(); i++) {
			if(umlaufplanliste.get(i).getFahrplanID()==fahrplan.getId()){
			anzahlBlockGesamt+=umlaufplanliste.get(i).getUmlauf().size();
			}
		}
		
		//Anzahl gleicher Uml��ufe zwischen zwei Umlaufpl��nen
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
			System.out.println("Es m��ssen mindestens zwei Umlaufpl��ne mit dem gleichen Fahrplan ausgew��hlt werden!");
		}
		//Wenn Nenner 0, dann wird Durchschnitt auf 0 gesetzt
		if(anzahlGleicheBlock==0){
			avgrepeat=0.0;
		}else{
		//Formel f��r durchschn. Wiederholrate
		avgrepeat=anzahlBlockGesamt/(anzahlBlockGesamt-anzahlGleicheBlock);
		}
		return avgrepeat;
	}
	
	//Methode bekommt dienstplanliste mit Dienstpl��nen und dem dazugeh��rigen Fahrplan
	public ArrayList<Dutyelement> regelmaessigeDutyelement(ArrayList <Dienstplan> dienstplanliste, Fahrplan fahrplan){
		
		ArrayList<Dutyelement> dutyelementList = new ArrayList<Dutyelement>();
		
		for (int i = 0; i < dienstplanliste.size(); i++) {
			for (int j = 0; j < dienstplanliste.get(i).getDuty().size(); j++) {
				for (int j2 = 0; j2 < dienstplanliste.get(i).getDutyelement().size(); j2++) {
					if(dienstplanliste.get(i).getDutyelement().get(j2).getServiceJourneyID().equals(fahrplan.getDays().get(j2).getTripID())&&fahrplan.getDays().get(j2).getD1()==1&&fahrplan.getDays().get(j2).getD2()==1&&fahrplan.getDays().get(j2).getD3()==1&&fahrplan.getDays().get(j2).getD4()==1&&fahrplan.getDays().get(j2).getD5()==1){
						dutyelementList.add(dienstplanliste.get(i).getDutyelement().get(j2));
					}
				}
			}	
		}
		return dutyelementList;
	}
	
	public double berechneDurschnittlicheWiederholrateDienstplanRegular(ArrayList<Dienstplan> dienstplanliste, ArrayList<ArrayList<ArrayList<String>>> ListPlaeneGesamt, Fahrplan fahrplan){
		
		double anzahlDutyGesamt=0.0;
		double anzahlGleicheDuty=0.0;
		double avgrepeat;
		boolean alleGleich=false;
		
		ArrayList<Dutyelement> dutyelementlist = new ArrayList<Dutyelement>();
//		dutyelementlist=regelmaessigeDutyelement(dienstplanliste, fahrplan);
		
		//Anzahl der Dienste von allen Dienstpl��nen
		for (int i = 0; i < dienstplanliste.size(); i++) {
			if(dienstplanliste.get(i).getFahrplanID()==fahrplan.getId()){
				anzahlDutyGesamt+=dienstplanliste.get(i).getDuty().size();
			}
		}
		//Alle Dienstpl��ne
		for (int plan = 0; plan < ListPlaeneGesamt.size()-1; plan++) {
			alleGleich=false;
			//Alle B��ndel in einem Plan
			for (int buendel = 0; buendel < ListPlaeneGesamt.get(plan).size(); buendel++) {
			//Alle ServiceJounreys im ersten B��ndel im ersten Plan
			for (int buendel2 = 0; buendel2 < ListPlaeneGesamt.get(plan+1).size(); buendel2++) {
				
				if(ListPlaeneGesamt.get(plan).get(buendel).size()==ListPlaeneGesamt.get(plan+1).get(buendel2).size()){
					//Alle ServiceJourneys im B��ndel
					for (int serviceJourney = 0; serviceJourney < ListPlaeneGesamt.get(plan).get(buendel).size(); serviceJourney++) {
						
						if(!(ListPlaeneGesamt.get(plan).get(buendel).get(serviceJourney).equals(ListPlaeneGesamt.get(plan+1).get(buendel2).get(serviceJourney)))){
							alleGleich=false;
							break;
						}
						alleGleich=true;
					}
					if(alleGleich){
						anzahlGleicheDuty++;
						break;
					}
				}else{
					alleGleich=false;
				}
				
				}
			}
		}
		
		avgrepeat=anzahlDutyGesamt/(anzahlDutyGesamt-anzahlGleicheDuty);
		
		return avgrepeat;
		
	}
	
	//Erstellt eine ArrayList mit allen B��ndeln an ServiceJourneys zu den einzelnen Dienstpl��nen
	public ArrayList<ArrayList<ArrayList<String>>> erstelleDutyelementList(ArrayList<Dienstplan> dienstplanliste){
		
		ArrayList<ArrayList<ArrayList<String>>> ListPlaeneGesamt = new ArrayList<ArrayList<ArrayList<String>>>();
		int zaehler=0;
		
		for (int i = 0; i < dienstplanliste.size(); i++) {
			ArrayList<ArrayList<String>> ListPlan = new ArrayList<ArrayList<String>>();
			zaehler=0;
			for (int j = 0; j < dienstplanliste.get(i).getDuty().size(); j++) {
				ArrayList<String> serviceJourneyList = new ArrayList<String>();
				for (int j2 = zaehler; j2 < dienstplanliste.get(i).getDutyelement().size(); j2++) {
				String dutyID=dienstplanliste.get(i).getDuty().get(j).getId();
					if(dienstplanliste.get(i).getDutyelement().get(j2).getDutyID().equals(dutyID)){	
						serviceJourneyList.add(dienstplanliste.get(i).getDutyelement().get(j2).getServiceJourneyID());
						zaehler++;
					}else{
						ListPlan.add(serviceJourneyList);
						break;
					}
				}
			}
			ListPlaeneGesamt.add(ListPlan);
		}
		return ListPlaeneGesamt;
		
		
	}
	
	
	
	

}