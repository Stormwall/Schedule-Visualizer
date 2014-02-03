package sv.creation.adress.util;

import java.util.ArrayList;

import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Duty;


public class Kennzahlenberechnung {
	
	public double berechneDurschnittlicheWiederholrate(ArrayList<Dienstplan> dienstplanliste){
		
		int anzahlDutyGesamt=0;
		int anzahlGleicheDuty=0;
		double avgrepeat=0.0;
		
		
		for (int i = 0; i < dienstplanliste.size(); i++) {
			anzahlDutyGesamt+=dienstplanliste.get(i).getDuty().size();
		}
		
		for (int i = 0; i < dienstplanliste.size(); i++) {
			for (int j = 0; j < dienstplanliste.get(i).getDuty().size(); j++) {
				if(dienstplanliste.get(i).getDuty().get(j)==dienstplanliste.get(i+1).getDuty().get(j+1)){
					anzahlGleicheDuty++;
				}
			}
		}
		
		if(anzahlGleicheDuty==0){
			anzahlGleicheDuty=anzahlDutyGesamt;
		}
		
		if(anzahlGleicheDuty==0){
			avgrepeat=0.0;
		}else{
		avgrepeat=anzahlDutyGesamt/anzahlGleicheDuty;
		}
		
		return avgrepeat;
	}

}