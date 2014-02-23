package sv.creation.adress.util;

import sv.creation.adress.model.Fahrplan;

public class Vergleich {
	
	private int[] anzahlServiceFahrten = new int[1440];

	public int[] vergleicheFahrplan(Fahrplan fahrplan, int id, int day) {

		clearArray(this.anzahlServiceFahrten);
		
		
		switch (day) {
		
		//Montag
		case 1:
			for (int i = 0; i < this.anzahlServiceFahrten.length; i++) {
				for (int j = 0; j < fahrplan.getDays().size(); j++) {
					if (fahrplan.getDays().get(j).getFahrplanID() == id
							&& fahrplan.getDays().get(j).getTripID() == fahrplan
									.getServicejourney().get(j).getiD()
							&& fahrplan.getDays().get(j).getD1() == 1
							&& fahrplan.getServicejourney().get(j).getFahrplanID() == fahrplan
									.getDays().get(j).getFahrplanID()) {
						//prüft, ob Zeit zwischen Abfahrts- und Ankunftszeit liegt
						if (timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) <= i
								&& timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime()) >= i) {
							this.anzahlServiceFahrten[i]++;
							//prüft ob Abfahrtszeit im Tag 0 liegt und Ankunftszeit im Tag 1
						}else if(timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) >
								timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime())) {
							//prüft, ob i kleiner Abfahrtszeit und kleiner Ankunftszeit ist (Bsp: Abfahrt: 23:50 i:00:01 Ankunft: 00:12)
							if(timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) >=i
								&&timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime())>=i){
							this.anzahlServiceFahrten[i]++;
							//prüft, ob i größer Abfahrtszeit aber kleiner Ankunftszeit ist (Bsp: Abfahrt: 23:50 i:23:53 Ankunft: 00:12)
							}else if(timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) <=i
								&&timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime())<=i){
								this.anzahlServiceFahrten[i]++;
							}
						}
					}
				}
				
			}
			break;
			
		//Dienstag
		case 2:
			for (int i = 0; i < this.anzahlServiceFahrten.length; i++) {
				for (int j = 0; j < fahrplan.getDays().size(); j++) {
					if (fahrplan.getDays().get(j).getFahrplanID() == id
							&& fahrplan.getDays().get(j).getTripID() == fahrplan
									.getServicejourney().get(j).getiD()
							&& fahrplan.getDays().get(j).getD2() == 1
							&& fahrplan.getServicejourney().get(j).getFahrplanID() == fahrplan
									.getDays().get(j).getFahrplanID()) {
						//prüft, ob Zeit zwischen Abfahrts- und Ankunftszeit liegt
						if (timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) <= i
								&& timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime()) >= i) {
							this.anzahlServiceFahrten[i]++;
							//prüft ob Abfahrtszeit im Tag 0 liegt und Ankunftszeit im Tag 1
						}else if(timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) >
								timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime())) {
							//prüft, ob i kleiner Abfahrtszeit und kleiner Ankunftszeit ist (Bsp: Abfahrt: 23:50 i:00:01 Ankunft: 00:12)
							if(timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) >=i
								&&timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime())>=i){
							this.anzahlServiceFahrten[i]++;
							//prüft, ob i größer Abfahrtszeit aber kleiner Ankunftszeit ist (Bsp: Abfahrt: 23:50 i:23:53 Ankunft: 00:12)
							}else if(timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) <=i
								&&timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime())<=i){
								this.anzahlServiceFahrten[i]++;
							}
						}
					}
				}
				
			}
			break;
			
		//Mittwoch
		case 3:
			for (int i = 0; i < this.anzahlServiceFahrten.length; i++) {
				for (int j = 0; j < fahrplan.getDays().size(); j++) {
					if (fahrplan.getDays().get(j).getFahrplanID() == id
							&& fahrplan.getDays().get(j).getTripID() == fahrplan
									.getServicejourney().get(j).getiD()
							&& fahrplan.getDays().get(j).getD3() == 1
							&& fahrplan.getServicejourney().get(j).getFahrplanID() == fahrplan
									.getDays().get(j).getFahrplanID()) {
						//prüft, ob Zeit zwischen Abfahrts- und Ankunftszeit liegt
						if (timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) <= i
								&& timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime()) >= i) {
							this.anzahlServiceFahrten[i]++;
							//prüft ob Abfahrtszeit im Tag 0 liegt und Ankunftszeit im Tag 1
						}else if(timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) >
								timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime())) {
							//prüft, ob i kleiner Abfahrtszeit und kleiner Ankunftszeit ist (Bsp: Abfahrt: 23:50 i:00:01 Ankunft: 00:12)
							if(timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) >=i
								&&timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime())>=i){
							this.anzahlServiceFahrten[i]++;
							//prüft, ob i größer Abfahrtszeit aber kleiner Ankunftszeit ist (Bsp: Abfahrt: 23:50 i:23:53 Ankunft: 00:12)
							}else if(timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) <=i
								&&timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime())<=i){
								this.anzahlServiceFahrten[i]++;
							}
						}
					}
				}
				
			}
			break;
			
		//Donnerstag	
		case 4:
			for (int i = 0; i < this.anzahlServiceFahrten.length; i++) {
				for (int j = 0; j < fahrplan.getDays().size(); j++) {
					if (fahrplan.getDays().get(j).getFahrplanID() == id
							&& fahrplan.getDays().get(j).getTripID() == fahrplan
									.getServicejourney().get(j).getiD()
							&& fahrplan.getDays().get(j).getD4() == 1
							&& fahrplan.getServicejourney().get(j).getFahrplanID() == fahrplan
									.getDays().get(j).getFahrplanID()) {
						//prüft, ob Zeit zwischen Abfahrts- und Ankunftszeit liegt
						if (timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) <= i
								&& timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime()) >= i) {
							this.anzahlServiceFahrten[i]++;
							//prüft ob Abfahrtszeit im Tag 0 liegt und Ankunftszeit im Tag 1
						}else if(timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) >
								timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime())) {
							//prüft, ob i kleiner Abfahrtszeit und kleiner Ankunftszeit ist (Bsp: Abfahrt: 23:50 i:00:01 Ankunft: 00:12)
							if(timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) >=i
								&&timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime())>=i){
							this.anzahlServiceFahrten[i]++;
							//prüft, ob i größer Abfahrtszeit aber kleiner Ankunftszeit ist (Bsp: Abfahrt: 23:50 i:23:53 Ankunft: 00:12)
							}else if(timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) <=i
								&&timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime())<=i){
								this.anzahlServiceFahrten[i]++;
							}
						}
					}
				}
				
			}
			break;
			
		//Freitag:
		case 5:
			for (int i = 0; i < this.anzahlServiceFahrten.length; i++) {
				for (int j = 0; j < fahrplan.getDays().size(); j++) {
					if (fahrplan.getDays().get(j).getFahrplanID() == id
							&& fahrplan.getDays().get(j).getTripID() == fahrplan
									.getServicejourney().get(j).getiD()
							&& fahrplan.getDays().get(j).getD5() == 1
							&& fahrplan.getServicejourney().get(j).getFahrplanID() == fahrplan
									.getDays().get(j).getFahrplanID()) {
						//prüft, ob Zeit zwischen Abfahrts- und Ankunftszeit liegt
						if (timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) <= i
								&& timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime()) >= i) {
							this.anzahlServiceFahrten[i]++;
							//prüft ob Abfahrtszeit im Tag 0 liegt und Ankunftszeit im Tag 1
						}else if(timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) >
								timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime())) {
							//prüft, ob i kleiner Abfahrtszeit und kleiner Ankunftszeit ist (Bsp: Abfahrt: 23:50 i:00:01 Ankunft: 00:12)
							if(timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) >=i
								&&timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime())>=i){
							this.anzahlServiceFahrten[i]++;
							//prüft, ob i größer Abfahrtszeit aber kleiner Ankunftszeit ist (Bsp: Abfahrt: 23:50 i:23:53 Ankunft: 00:12)
							}else if(timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) <=i
								&&timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime())<=i){
								this.anzahlServiceFahrten[i]++;
							}
						}
					}
				}
				
			}
			break;
			
		//Samstag
		case 6:
			for (int i = 0; i < this.anzahlServiceFahrten.length; i++) {
				for (int j = 0; j < fahrplan.getDays().size(); j++) {
					if (fahrplan.getDays().get(j).getFahrplanID() == id
							&& fahrplan.getDays().get(j).getTripID() == fahrplan
									.getServicejourney().get(j).getiD()
							&& fahrplan.getDays().get(j).getD6() == 1
							&& fahrplan.getServicejourney().get(j).getFahrplanID() == fahrplan
									.getDays().get(j).getFahrplanID()) {
						//prüft, ob Zeit zwischen Abfahrts- und Ankunftszeit liegt
						if (timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) <= i
								&& timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime()) >= i) {
							this.anzahlServiceFahrten[i]++;
							//prüft ob Abfahrtszeit im Tag 0 liegt und Ankunftszeit im Tag 1
						}else if(timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) >
								timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime())) {
							//prüft, ob i kleiner Abfahrtszeit und kleiner Ankunftszeit ist (Bsp: Abfahrt: 23:50 i:00:01 Ankunft: 00:12)
							if(timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) >=i
								&&timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime())>=i){
							this.anzahlServiceFahrten[i]++;
							//prüft, ob i größer Abfahrtszeit aber kleiner Ankunftszeit ist (Bsp: Abfahrt: 23:50 i:23:53 Ankunft: 00:12)
							}else if(timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) <=i
								&&timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime())<=i){
								this.anzahlServiceFahrten[i]++;
							}
						}
					}
				}
				
			}
			break;
			
		//Sonntag
		case 7:
			for (int i = 0; i < this.anzahlServiceFahrten.length; i++) {
				for (int j = 0; j < fahrplan.getDays().size(); j++) {
					if (fahrplan.getDays().get(j).getFahrplanID() == id
							&& fahrplan.getDays().get(j).getTripID() == fahrplan
									.getServicejourney().get(j).getiD()
							&& fahrplan.getDays().get(j).getD7() == 1
							&& fahrplan.getServicejourney().get(j).getFahrplanID() == fahrplan
									.getDays().get(j).getFahrplanID()) {
						//prüft, ob Zeit zwischen Abfahrts- und Ankunftszeit liegt
						if (timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) <= i
								&& timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime()) >= i) {
							this.anzahlServiceFahrten[i]++;
							//prüft ob Abfahrtszeit im Tag 0 liegt und Ankunftszeit im Tag 1
						}else if(timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) >
								timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime())) {
							//prüft, ob i kleiner Abfahrtszeit und kleiner Ankunftszeit ist (Bsp: Abfahrt: 23:50 i:00:01 Ankunft: 00:12)
							if(timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) >=i
								&&timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime())>=i){
							this.anzahlServiceFahrten[i]++;
							//prüft, ob i größer Abfahrtszeit aber kleiner Ankunftszeit ist (Bsp: Abfahrt: 23:50 i:23:53 Ankunft: 00:12)
							}else if(timeToInt(fahrplan.getServicejourney().get(j)
								.getDepTime()) <=i
								&&timeToInt(fahrplan.getServicejourney().get(j)
										.getArrTime())<=i){
								this.anzahlServiceFahrten[i]++;
							}
						}
					}
				}
				
			}
			break;
			
		default:
			break;
		}
		return this.anzahlServiceFahrten;
	}

	public String timeFormat(int i) {

		String time = "";
		int min = 0;
		int hour = 0;
		if (i >= 60) {
			hour = i / 60;
			min = i - (hour * 60);
		}
		if (hour < 10 && min < 10) {
			time = "0" + hour + ":" + min + "0";
		} else {
			time = hour + ":" + min;
		}
		return time;
	}

	public int timeToInt(String i) {
		int time = 0;
		String[] timeString = i.split(":");
		if (Integer.parseInt(timeString[0]) >= 1) {
			time = Integer.parseInt(timeString[0]) * 60
					+ Integer.parseInt(timeString[1]);
		} else
			time = Integer.parseInt(timeString[1]);
		return time;
	}
	
	//Methode löscht alle Elemente aus einem Array
	public int[] clearArray(int[]array){
		for (int i = 0; i < array.length; i++) {
			array[i]=0;
		}
		return array;
	}

}
