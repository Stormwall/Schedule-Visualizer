package sv.creation.adress.util;

import java.util.ArrayList;

import sv.creation.adress.model.Blockelement;
import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Duty;
import sv.creation.adress.model.Dutyelement;
import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.model.ServiceJourney;
import sv.creation.adress.model.Umlaufplan;


public class Kennzahlenberechnung {
	/*********************************************************************************************************************************
	 ****berechnet durchschnittl. Wiederholrate für Dienstplanliste mit allen Fahrten*************************************************
	 *********************************************************************************************************************************/
	public double berechneDurschnittlicheWiederholrateDienstplanAll(ArrayList<Dienstplan> dienstplanliste,  Fahrplan fahrplan){
		
		double anzahlDutyGesamt=0.0;
		double anzahlGleicheDuty=0.0;
		double avgrepeat;
		boolean alleGleich=false;
		
		
		
		ArrayList<ArrayList<ArrayList<String>>> ListPlaeneGesamt = erstelleDutyelementListAll(dienstplanliste);
		
		
		//Anzahl der Dienste von allen Dienstpl������nen
		for (int i = 0; i < dienstplanliste.size(); i++) {
			if(dienstplanliste.get(i).getFahrplanID()==fahrplan.getId()){
				for (int j = 0; j < dienstplanliste.get(i).getDuty().size(); j++) {
						anzahlDutyGesamt++;
				}
			}
		}
		//Alle Dienstplaene
		for (int plan = 0; plan < ListPlaeneGesamt.size()-1; plan++) {
			alleGleich=false;
			//Alle B������ndel in einem Plan
			for (int buendel = 0; buendel < ListPlaeneGesamt.get(plan).size(); buendel++) {
			//Alle ServiceJounreys im ersten B������ndel im ersten Plan
			for (int buendel2 = 0; buendel2 < ListPlaeneGesamt.get(plan+1).size(); buendel2++) {
				
				if(ListPlaeneGesamt.get(plan).get(buendel).size()==ListPlaeneGesamt.get(plan+1).get(buendel2).size()){
					//Alle ServiceJourneys im B������ndel
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
	/*********************************************************************************************************************************
	 ****berechnet durchschnittl. Wiederholrate für Umlaufplanliste mit allen Fahrten*************************************************
	 *********************************************************************************************************************************/
	public double berechneDurschnittlicheWiederholrateUmlaufplanAll(ArrayList<Umlaufplan> umlaufplanliste, Fahrplan fahrplan){
		
		int anzahlBlockGesamt=0;
		int anzahlGleicheBlock=0;
		double avgrepeat=0.0;
		
		//Anzahl der Uml������ufe von allen Umlaufpl������nen, die zu dem Fahrplan geh������ren
		for (int i = 0; i < umlaufplanliste.size(); i++) {
			if(umlaufplanliste.get(i).getFahrplanID()==fahrplan.getId()){
			anzahlBlockGesamt+=umlaufplanliste.get(i).getUmlauf().size();
			}
		}
		
		//Anzahl gleicher Uml������ufe zwischen zwei Umlaufpl������nen
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
			System.out.println("Es m������ssen mindestens zwei Umlaufpl������ne mit dem gleichen Fahrplan ausgew������hlt werden!");
		}
		//Wenn Nenner 0, dann wird Durchschnitt auf 0 gesetzt
		if(anzahlGleicheBlock==0){
			avgrepeat=0.0;
		}else{
		//Formel f������r durchschn. Wiederholrate
		avgrepeat=anzahlBlockGesamt/(anzahlBlockGesamt-anzahlGleicheBlock);
		}
		return avgrepeat;
	}
	
	/*********************************************************************************************************************************
	 ****erzeugt Liste mit regelmaeßigen Dutyelements*********************************************************************************
	 *********************************************************************************************************************************/
	public ArrayList<Dutyelement> regelmaessigeDutyelement(Dienstplan dienstplan, Fahrplan fahrplan){
		
		ArrayList<Dutyelement> dutyelementList = new ArrayList<Dutyelement>();
		
				for (int j2 = 0; j2 < dienstplan.getDutyelement().size(); j2++) {
					if(dienstplan.getDutyelement().get(j2).getElementType()==1){
						for (int k = 0; k < fahrplan.getDays().size(); k++) {
							if(Integer.parseInt(dienstplan.getDutyelement().get(j2).getServiceJourneyID())==(fahrplan.getDays().get(k).getTripID())&&fahrplan.getDays().get(k).getD1()==1&&fahrplan.getDays().get(k).getD2()==1&&fahrplan.getDays().get(k).getD3()==1&&fahrplan.getDays().get(k).getD4()==1&&fahrplan.getDays().get(k).getD5()==1){
								dutyelementList.add(dienstplan.getDutyelement().get(j2));
								break;
							}
						}
					}
			}	
		return dutyelementList;
	}
	/*********************************************************************************************************************************
	 ****berechnet durchschnittl. Wiederholrate der regelmaessigen Fahrten in Dienstplanliste*****************************************
	 *********************************************************************************************************************************/
	public double berechneDurschnittlicheWiederholrateDienstplanRegular(ArrayList<Dienstplan> dienstplanliste, Fahrplan fahrplan){
		
		double anzahlDutyGesamt=0.0;
		double anzahlGleicheDuty=0.0;
		double avgrepeat;
		boolean alleGleich=false;
		
		ArrayList<ArrayList<ArrayList<String>>> regularList = erstelleDutyelementListRegular(dienstplanliste, fahrplan);
		
		//Anzahl der Dienste von allen Dienstpl������nen
		for (int i = 0; i < dienstplanliste.size(); i++) {
			if(dienstplanliste.get(i).getFahrplanID()==fahrplan.getId()){
				for (int j = 0; j < dienstplanliste.get(i).getDuty().size(); j++) {
						anzahlDutyGesamt++;
					
				}
			}
		}
		//Alle Dienstpl������ne
		for (int plan = 0; plan < regularList.size()-1; plan++) {
			alleGleich=false;
			//Alle B������ndel in einem Plan
			for (int buendel = 0; buendel < regularList.get(plan).size(); buendel++) {
			//Alle ServiceJounreys im ersten B������ndel im ersten Plan
			for (int buendel2 = 0; buendel2 < regularList.get(plan+1).size(); buendel2++) {
				
				if(regularList.get(plan).get(buendel).size()==regularList.get(plan+1).get(buendel2).size()){
					//Alle ServiceJourneys im B������ndel
					for (int serviceJourney = 0; serviceJourney < regularList.get(plan).get(buendel).size(); serviceJourney++) {
						
						if(!(regularList.get(plan).get(buendel).get(serviceJourney).equals(regularList.get(plan+1).get(buendel2).get(serviceJourney)))){
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
	/*********************************************************************************************************************************
	 ****Erstellt eine ArrayList mit allen Buendeln an ServiceJourneys zu den einzelnen Dienstplaenen*********************************
	 *********************************************************************************************************************************/
	//Erstellt eine ArrayList mit allen Buendeln an ServiceJourneys zu den einzelnen Dienstplaenen
	public ArrayList<ArrayList<ArrayList<String>>> erstelleDutyelementListAll(ArrayList<Dienstplan> dienstplanliste){
		
		ArrayList<ArrayList<ArrayList<String>>> ListPlaeneGesamt = new ArrayList<ArrayList<ArrayList<String>>>();
		int zaehler=0;
		
		for (int i = 0; i < dienstplanliste.size(); i++) {
			ArrayList<ArrayList<String>> ListPlan = new ArrayList<ArrayList<String>>();
			zaehler=0;
			for (int j = 0; j < dienstplanliste.get(i).getDuty().size(); j++) {
				ArrayList<String> serviceJourneyList = new ArrayList<String>();
				for (int j2 = zaehler; j2 < dienstplanliste.get(i).getDutyelement().size(); j2++) {
				String dutyID=dienstplanliste.get(i).getDuty().get(j).getId();
					if(dienstplanliste.get(i).getDutyelement().get(j2).getElementType()==1){
						if(dienstplanliste.get(i).getDutyelement().get(j2).getDutyID().equals(dutyID)){	
						serviceJourneyList.add(dienstplanliste.get(i).getDutyelement().get(j2).getServiceJourneyID());
						}else{
							ListPlan.add(serviceJourneyList);
							break;
						}
					}
					zaehler++;
					if(zaehler==dienstplanliste.get(i).getDutyelement().size()){
						ListPlan.add(serviceJourneyList);
					}
				}
			}
			ListPlaeneGesamt.add(ListPlan);
		}
		return ListPlaeneGesamt;
	}
	/*********************************************************************************************************************************
	 ****berechnet durchschnittl. Wiederholrate der regelmaessigen Fahrten in Dienstplanliste*****************************************
	 *********************************************************************************************************************************/
	public double berechneDurschnittlicheWiederholrateUmlaufplanRegular(ArrayList<Umlaufplan> umlaufplanliste, Fahrplan fahrplan){
		
		double anzahlBlockGesamt=0.0;
		double anzahlGleicherBlock=0.0;
		double avgrepeat;
		boolean alleGleich=false;
		
		ArrayList<ArrayList<ArrayList<String>>> regularList = erstelleBlockelementListRegular(umlaufplanliste, fahrplan);
		
		//Anzahl der Dienste von allen Umlaufplaenen
		for (int i = 0; i < umlaufplanliste.size(); i++) {
			if(umlaufplanliste.get(i).getFahrplanID()==fahrplan.getId()){
				for (int j = 0; j < umlaufplanliste.get(i).getUmlauf().size(); j++) {
						anzahlBlockGesamt++;
					
				}
			}
		}
		//Alle Umlaufplaene
		for (int plan = 0; plan < regularList.size()-1; plan++) {
			alleGleich=false;
			//Alle Buendel in einem Plan
			for (int buendel = 0; buendel < regularList.get(plan).size(); buendel++) {
			//Alle ServiceJounreys im ersten Buendel im ersten Plan
			for (int buendel2 = 0; buendel2 < regularList.get(plan+1).size(); buendel2++) {
				
				if(regularList.get(plan).get(buendel).size()==regularList.get(plan+1).get(buendel2).size()){
					//Alle ServiceJourneys im Buendel
					for (int serviceJourney = 0; serviceJourney < regularList.get(plan).get(buendel).size(); serviceJourney++) {
						
						if(!(regularList.get(plan).get(buendel).get(serviceJourney).equals(regularList.get(plan+1).get(buendel2).get(serviceJourney)))){
							alleGleich=false;
							break;
						}
						alleGleich=true;
					}
					if(alleGleich){
						anzahlGleicherBlock++;
						break;
					}
				}else{
					alleGleich=false;
				}
				
				}
			}
		}
		
		avgrepeat=anzahlBlockGesamt/(anzahlBlockGesamt-anzahlGleicherBlock);
		
		return avgrepeat;
		
	}
	/*********************************************************************************************************************************
	 ****berechnet Distanzmaß für Dienstplanliste**************************************************************************************
	 *********************************************************************************************************************************/
	public int berechneDistanzCrewSchedule(ArrayList<Dienstplan> dienstplanliste, Fahrplan fahrplan){
		
		ArrayList<ArrayList<ArrayList<String>>> regularList = erstelleDutyelementListRegular(dienstplanliste, fahrplan);
		ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> planListe= new ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>>();
		
		//for each service journey will be set the previous and next service journey
		//Plaene
		for (int i = 0; i < regularList.size(); i++) {
			ArrayList<ArrayList<ArrayList<Integer>>> plaene = new ArrayList<ArrayList<ArrayList<Integer>>>();
			//Buendel
			for (int j = 0; j < regularList.get(i).size(); j++) {
				ArrayList<ArrayList<Integer>> serviceJourneyReihenfolge = new ArrayList<ArrayList<Integer>>();
				//regular service journeys
				for (int j2 = 0; j2 < regularList.get(i).get(j).size(); j2++) {
					ArrayList<Integer> reihenfolge = new ArrayList<Integer>();
					if(j2==0){
						//Vorgaenger
						reihenfolge.add(null);
						//pointer
						reihenfolge.add(Integer.parseInt(regularList.get(i).get(j).get(j2)));
						//nachfolger
						reihenfolge.add(Integer.parseInt(regularList.get(i).get(j).get(j2+1)));
						
					}else if(j2==regularList.get(i).get(j).size()-1){
						//Vorgaenger
						reihenfolge.add(Integer.parseInt(regularList.get(i).get(j).get(j2-1)));
						//pointer
						reihenfolge.add(Integer.parseInt(regularList.get(i).get(j).get(j2)));
						//nachfolger
						reihenfolge.add(null);
						serviceJourneyReihenfolge.add(reihenfolge);
						break;
					}else{
						//Vorgaenger
						reihenfolge.add(Integer.parseInt(regularList.get(i).get(j).get(j2-1)));
						//pointer
						reihenfolge.add(Integer.parseInt(regularList.get(i).get(j).get(j2)));
						//nachfolger
						reihenfolge.add(Integer.parseInt(regularList.get(i).get(j).get(j2+1)));
					}
					serviceJourneyReihenfolge.add(reihenfolge);
			}
				plaene.add(serviceJourneyReihenfolge);
		}
			planListe.add(plaene);
		}
		
		int unregelmaessigeFahrt=0;
		
		ArrayList<Dutyelement> dutyelementRegular = regelmaessigeDutyelement(dienstplanliste.get(0), fahrplan);
		ArrayList<ArrayList<Integer>> listDP1  = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> listDP2  = new ArrayList<ArrayList<Integer>>();
		
		//two lists with regular service journeys of each dienstplan will be filled
		for (int i = 0; i < planListe.size(); i++) {
			for (int j = 0; j < planListe.get(i).size(); j++) {
				for (int j2 = 0; j2 < planListe.get(i).get(j).size(); j2++) {
					if(i==0){
						listDP1.add(planListe.get(i).get(j).get(j2));
					}else if(i==1){
						listDP2.add(planListe.get(i).get(j).get(j2));
					}
				}
			}
		}
		
		int[] reihenfolgeDP1=new int[3];
		int[] reihenfolgeDP2=new int[3];
		
		for (int i = 0; i < dutyelementRegular.size(); i++) {
			
			for (int j = 0; j < listDP1.size(); j++) {
				if(listDP1.get(j).get(1)==Integer.parseInt(dutyelementRegular.get(i).getServiceJourneyID())){
					//if the dutyelement before/after the pointer null the value will be set -1
					if(listDP1.get(j).get(0)==null){
						reihenfolgeDP1[0]=-1;
					}else{
					reihenfolgeDP1[0]=listDP1.get(j).get(0);
					}
					if(listDP1.get(j).get(2)==null){
						reihenfolgeDP1[2]=-1;
					}else{
						reihenfolgeDP1[2]=listDP1.get(j).get(2);
					}
					reihenfolgeDP1[1]=listDP1.get(j).get(1);
					break;
				}
			}
			
			for (int j = 0; j < listDP2.size(); j++) {
				if(listDP2.get(j).get(1)==Integer.parseInt(dutyelementRegular.get(i).getServiceJourneyID())){
					//if the dutyelement before/after the pointer null the value will be set -1
					if(listDP2.get(j).get(0)==null){
						reihenfolgeDP2[0]=-1;
					}else{
					reihenfolgeDP2[0]=listDP2.get(j).get(0);
					}
					if(listDP2.get(j).get(2)==null){
						reihenfolgeDP2[2]=-1;
					}else{
						reihenfolgeDP2[2]=listDP2.get(j).get(2);
					}
					reihenfolgeDP2[1]=listDP2.get(j).get(1);
					break;
				}
			}
			
			//if the previous and next service journey of a regular service journey similar to the regular service journey in the second diesntplan the counter of regular service journeys will be increased
			if(reihenfolgeDP1[0]!=reihenfolgeDP2[0]&&reihenfolgeDP1[1]==reihenfolgeDP2[1]&&reihenfolgeDP1[2]==reihenfolgeDP2[2]){
				unregelmaessigeFahrt++;
			}else if(reihenfolgeDP1[0]==reihenfolgeDP2[0]&&reihenfolgeDP1[1]==reihenfolgeDP2[1]&&reihenfolgeDP1[2]!=reihenfolgeDP2[2]){
				unregelmaessigeFahrt++;
			}else if(reihenfolgeDP1[0]!=reihenfolgeDP2[0]&&reihenfolgeDP1[1]==reihenfolgeDP2[1]&&reihenfolgeDP1[2]!=reihenfolgeDP2[2]){
				unregelmaessigeFahrt++;
				unregelmaessigeFahrt++;
			}
		}
		return unregelmaessigeFahrt;
	}
	/*********************************************************************************************************************************
	 ****erstellt Liste mit allen Buendeln an regelmaeßigen ServiceJourneys  für Dienstplanliste***************************************
	 *********************************************************************************************************************************/
	public ArrayList<ArrayList<ArrayList<String>>> erstelleDutyelementListRegular(ArrayList<Dienstplan> dienstplanliste, Fahrplan fahrplan){
		
		ArrayList<ArrayList<ArrayList<String>>> ListPlaeneGesamt = new ArrayList<ArrayList<ArrayList<String>>>();
		int zaehler=0;
		
		ArrayList<Dutyelement> dutyelementlist = new ArrayList<Dutyelement>();
		
		for (int i = 0; i < dienstplanliste.size(); i++) {
			ArrayList<ArrayList<String>> ListPlan = new ArrayList<ArrayList<String>>();
			dutyelementlist=regelmaessigeDutyelement(dienstplanliste.get(i), fahrplan);
			zaehler=0;
			for (int j = 0; j < dienstplanliste.get(i).getDuty().size(); j++) {
				ArrayList<String> serviceJourneyList = new ArrayList<String>();
				for (int j2 = zaehler; j2 < dutyelementlist.size(); j2++) {
					String dutyID=dienstplanliste.get(i).getDuty().get(j).getId();
					if(dutyelementlist.get(j2).getDutyID().equals(dutyID)){	
						serviceJourneyList.add(dutyelementlist.get(j2).getServiceJourneyID());
						zaehler++;
						if(zaehler==dutyelementlist.size()){
							ListPlan.add(serviceJourneyList);
						}
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

	
	/*********************************************************************************************************************************
	 ****berechnet Distanzmaß für Umlaufplanliste**************************************************************************************
	 *********************************************************************************************************************************/
public int berechneDistanzVehSchedule(ArrayList<Umlaufplan> umlaufplanliste, Fahrplan fahrplan){
		
		ArrayList<ArrayList<ArrayList<String>>> regularList = erstelleBlockelementListRegular(umlaufplanliste, fahrplan);
		ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> planListe= new ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>>();
		
		//for each service journey will be set the previous and next service journey
		//Plaene
		for (int i = 0; i < regularList.size(); i++) {
			ArrayList<ArrayList<ArrayList<Integer>>> plaene = new ArrayList<ArrayList<ArrayList<Integer>>>();
			//Buendel
			for (int j = 0; j < regularList.get(i).size(); j++) {
				ArrayList<ArrayList<Integer>> serviceJourneyReihenfolge = new ArrayList<ArrayList<Integer>>();
				//regular service journeys
				for (int j2 = 0; j2 < regularList.get(i).get(j).size(); j2++) {
					ArrayList<Integer> reihenfolge = new ArrayList<Integer>();
					if(j2==0){
						//Vorgaenger
						reihenfolge.add(null);
						//pointer
						reihenfolge.add(Integer.parseInt(regularList.get(i).get(j).get(j2)));
						//nachfolger
						reihenfolge.add(Integer.parseInt(regularList.get(i).get(j).get(j2+1)));
						
					}else if(j2==regularList.get(i).get(j).size()-1){
						//Vorgaenger
						reihenfolge.add(Integer.parseInt(regularList.get(i).get(j).get(j2-1)));
						//pointer
						reihenfolge.add(Integer.parseInt(regularList.get(i).get(j).get(j2)));
						//nachfolger
						reihenfolge.add(null);
						serviceJourneyReihenfolge.add(reihenfolge);
						break;
					}else{
						//Vorgaenger
						reihenfolge.add(Integer.parseInt(regularList.get(i).get(j).get(j2-1)));
						//pointer
						reihenfolge.add(Integer.parseInt(regularList.get(i).get(j).get(j2)));
						//nachfolger
						reihenfolge.add(Integer.parseInt(regularList.get(i).get(j).get(j2+1)));
					}
					serviceJourneyReihenfolge.add(reihenfolge);
			}
				plaene.add(serviceJourneyReihenfolge);
		}
			planListe.add(plaene);
		}
		
		int unregelmaessigeFahrt=0;
		
		ArrayList<Blockelement> blockelementRegular = regelmaessigeBlockelement(umlaufplanliste.get(0), fahrplan);
		ArrayList<ArrayList<Integer>> listUP1  = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> listUP2  = new ArrayList<ArrayList<Integer>>();
		
		//two lists with regular service journeys of each umlaufplan will be filled
		for (int i = 0; i < planListe.size(); i++) {
			for (int j = 0; j < planListe.get(i).size(); j++) {
				for (int j2 = 0; j2 < planListe.get(i).get(j).size(); j2++) {
					if(i==0){
						listUP1.add(planListe.get(i).get(j).get(j2));
					}else if(i==1){
						listUP2.add(planListe.get(i).get(j).get(j2));
					}
				}
			}
		}
		
		int[] reihenfolgeUP1=new int[3];
		int[] reihenfolgeUP2=new int[3];
		
		for (int i = 0; i < blockelementRegular.size(); i++) {
			
			for (int j = 0; j < listUP1.size(); j++) {
				if(listUP1.get(j).get(1)==Integer.parseInt(blockelementRegular.get(i).getServiceJourneyID())){
					//if the blockelement before/after the pointer null the value will be set -1
					if(listUP1.get(j).get(0)==null){
						reihenfolgeUP1[0]=-1;
					}else{
					reihenfolgeUP1[0]=listUP1.get(j).get(0);
					}
					if(listUP1.get(j).get(2)==null){
						reihenfolgeUP1[2]=-1;
					}else{
						reihenfolgeUP1[2]=listUP1.get(j).get(2);
					}
					reihenfolgeUP1[1]=listUP1.get(j).get(1);
					break;
				}
			}
			
			for (int j = 0; j < listUP2.size(); j++) {
				if(listUP2.get(j).get(1)==Integer.parseInt(blockelementRegular.get(i).getServiceJourneyID())){
					//if the blockelement before/after the pointer null the value will be set -1
					if(listUP2.get(j).get(0)==null){
						reihenfolgeUP2[0]=-1;
					}else{
					reihenfolgeUP2[0]=listUP2.get(j).get(0);
					}
					if(listUP2.get(j).get(2)==null){
						reihenfolgeUP2[2]=-1;
					}else{
						reihenfolgeUP2[2]=listUP2.get(j).get(2);
					}
					reihenfolgeUP2[1]=listUP2.get(j).get(1);
					break;
				}
			}
			
			//if the previous and next service journey of a regular service journey similar to the regular service journey in the second diesntplan the counter of regular service journeys will be increased
			if(reihenfolgeUP1[0]!=reihenfolgeUP2[0]&&reihenfolgeUP1[1]==reihenfolgeUP2[1]&&reihenfolgeUP1[2]==reihenfolgeUP2[2]){
				unregelmaessigeFahrt++;
			}else if(reihenfolgeUP1[0]==reihenfolgeUP2[0]&&reihenfolgeUP1[1]==reihenfolgeUP2[1]&&reihenfolgeUP1[2]!=reihenfolgeUP2[2]){
				unregelmaessigeFahrt++;
			}else if(reihenfolgeUP1[0]!=reihenfolgeUP2[0]&&reihenfolgeUP1[1]==reihenfolgeUP2[1]&&reihenfolgeUP1[2]!=reihenfolgeUP2[2]){
				unregelmaessigeFahrt++;
				unregelmaessigeFahrt++;
			}
		}
		return unregelmaessigeFahrt;
	}
/*********************************************************************************************************************************
 ****erstellt Liste mit regelmaeßigen Blockelements für Umlaufplanliste************************************************************
 *********************************************************************************************************************************/
public ArrayList<ArrayList<ArrayList<String>>> erstelleBlockelementListRegular(ArrayList<Umlaufplan> umlaufplanliste, Fahrplan fahrplan){
	
	ArrayList<ArrayList<ArrayList<String>>> ListPlaeneGesamt = new ArrayList<ArrayList<ArrayList<String>>>();
	int zaehler=0;
	
	ArrayList<Blockelement> blockelementlist = new ArrayList<Blockelement>();
	
	for (int i = 0; i < umlaufplanliste.size(); i++) {
		ArrayList<ArrayList<String>> ListPlan = new ArrayList<ArrayList<String>>();
		blockelementlist=regelmaessigeBlockelement(umlaufplanliste.get(i), fahrplan);
		zaehler=0;
		for (int j = 0; j < umlaufplanliste.get(i).getUmlauf().size(); j++) {
			ArrayList<String> serviceJourneyList = new ArrayList<String>();
			for (int j2 = zaehler; j2 < blockelementlist.size(); j2++) {
				Integer blockID=umlaufplanliste.get(i).getUmlauf().get(j).getId();
				if(blockelementlist.get(j2).getBlockID()==blockID){	
					serviceJourneyList.add(blockelementlist.get(j2).getServiceJourneyID());
					zaehler++;
					if(zaehler==blockelementlist.size()){
						ListPlan.add(serviceJourneyList);
					}
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
/*********************************************************************************************************************************
 ****erzeugt Liste mit regelmaeßigen Blockelements*********************************************************************************
 *********************************************************************************************************************************/
public ArrayList<Blockelement> regelmaessigeBlockelement(Umlaufplan umlaufplan, Fahrplan fahrplan){
	
	ArrayList<Blockelement> blockelementList = new ArrayList<Blockelement>();
	
			for (int j2 = 0; j2 < umlaufplan.getFahrtZuUmlauf().size(); j2++) {
				if(umlaufplan.getFahrtZuUmlauf().get(j2).getElementType()==1){
					for (int k = 0; k < fahrplan.getDays().size(); k++) {
						if(Integer.parseInt(umlaufplan.getFahrtZuUmlauf().get(j2).getServiceJourneyID())==(fahrplan.getDays().get(k).getTripID())&&fahrplan.getDays().get(k).getD1()==1&&fahrplan.getDays().get(k).getD2()==1&&fahrplan.getDays().get(k).getD3()==1&&fahrplan.getDays().get(k).getD4()==1&&fahrplan.getDays().get(k).getD5()==1){
							blockelementList.add(umlaufplan.getFahrtZuUmlauf().get(j2));
							break;
						}
					}
				}
		}	
	return blockelementList;
}
}