package sv.creation.adress.util;

import java.util.ArrayList;

import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Dutyelement;
import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.model.Umlaufplan;

public class Kennzahlenberechnung {

	public double berechneDurschnittlicheWiederholrateDienstplanAll(
			ArrayList<Dienstplan> dienstplanliste,
			ArrayList<ArrayList<ArrayList<String>>> ListPlaeneGesamt,
			Fahrplan fahrplan) {

		double anzahlDutyGesamt = 0.0;
		double anzahlGleicheDuty = 0.0;
		double avgrepeat;
		boolean alleGleich = false;

		// Anzahl der Dienste von allen Dienstpl������nen
		for (int i = 0; i < dienstplanliste.size(); i++) {
			if (dienstplanliste.get(i).getFahrplanID() == fahrplan.getId()) {
				for (int j = 0; j < dienstplanliste.get(i).getDuty().size(); j++) {
					anzahlDutyGesamt++;
				}
			}
		}
		// Alle Dienstpl������ne
		for (int plan = 0; plan < ListPlaeneGesamt.size() - 1; plan++) {
			alleGleich = false;
			// Alle B������ndel in einem Plan
			for (int buendel = 0; buendel < ListPlaeneGesamt.get(plan).size(); buendel++) {
				// Alle ServiceJounreys im ersten B������ndel im ersten Plan
				for (int buendel2 = 0; buendel2 < ListPlaeneGesamt
						.get(plan + 1).size(); buendel2++) {

					if (ListPlaeneGesamt.get(plan).get(buendel).size() == ListPlaeneGesamt
							.get(plan + 1).get(buendel2).size()) {
						// Alle ServiceJourneys im B������ndel
						for (int serviceJourney = 0; serviceJourney < ListPlaeneGesamt
								.get(plan).get(buendel).size(); serviceJourney++) {

							if (!(ListPlaeneGesamt.get(plan).get(buendel)
									.get(serviceJourney)
									.equals(ListPlaeneGesamt.get(plan + 1)
											.get(buendel2).get(serviceJourney)))) {
								alleGleich = false;
								break;
							}
							alleGleich = true;
						}
						if (alleGleich) {
							anzahlGleicheDuty++;
							break;
						}
					} else {
						alleGleich = false;
					}

				}
			}
		}

		avgrepeat = anzahlDutyGesamt / (anzahlDutyGesamt - anzahlGleicheDuty);

		return avgrepeat;
	}

	public double berechneDurschnittlicheWiederholrateUmlaufplanAll(
			ArrayList<Umlaufplan> umlaufplanliste, Fahrplan fahrplan) {

		int anzahlBlockGesamt = 0;
		int anzahlGleicheBlock = 0;
		double avgrepeat = 0.0;

		// Anzahl der Uml������ufe von allen Umlaufpl������nen, die zu dem
		// Fahrplan geh������ren
		for (int i = 0; i < umlaufplanliste.size(); i++) {
			if (umlaufplanliste.get(i).getFahrplanID() == fahrplan.getId()) {
				anzahlBlockGesamt += umlaufplanliste.get(i).getUmlauf().size();
			}
		}

		// Anzahl gleicher Uml������ufe zwischen zwei Umlaufpl������nen
		for (int i = 0; i < umlaufplanliste.size(); i++) {
			for (int j = 0; j < umlaufplanliste.get(i).getUmlauf().size(); j++) {
				if (umlaufplanliste.get(i).getUmlauf().get(j) == umlaufplanliste
						.get(i + 1).getUmlauf().get(j + 1)) {
					anzahlGleicheBlock++;
				}
			}
		}
		// Man darf nicht durch 0 teilen
		if (anzahlGleicheBlock == 0) {
			anzahlGleicheBlock = anzahlBlockGesamt;
		}

		if (anzahlBlockGesamt == 0) {
			System.out
					.println("Es m������ssen mindestens zwei Umlaufpl������ne mit dem gleichen Fahrplan ausgew������hlt werden!");
		}
		// Wenn Nenner 0, dann wird Durchschnitt auf 0 gesetzt
		if (anzahlGleicheBlock == 0) {
			avgrepeat = 0.0;
		} else {
			// Formel f������r durchschn. Wiederholrate
			avgrepeat = anzahlBlockGesamt
					/ (anzahlBlockGesamt - anzahlGleicheBlock);
		}
		return avgrepeat;
	}

	// Methode bekommt dienstplanliste mit Dienstpl������nen und dem
	// dazugeh������rigen Fahrplan
	public ArrayList<Dutyelement> regelmaessigeDutyelement(
			Dienstplan dienstplan, Fahrplan fahrplan) {

		ArrayList<Dutyelement> dutyelementList = new ArrayList<Dutyelement>();

		for (int j2 = 0; j2 < dienstplan.getDutyelement().size(); j2++) {
			if (dienstplan.getDutyelement().get(j2).getElementType() == 1) {
				for (int k = 0; k < fahrplan.getDays().size(); k++) {
					if (Integer.parseInt(dienstplan.getDutyelement().get(j2)
							.getServiceJourneyID()) == (fahrplan.getDays().get(
							k).getTripID())
							&& fahrplan.getDays().get(k).getD1() == 1
							&& fahrplan.getDays().get(k).getD2() == 1
							&& fahrplan.getDays().get(k).getD3() == 1
							&& fahrplan.getDays().get(k).getD4() == 1
							&& fahrplan.getDays().get(k).getD5() == 1) {
						dutyelementList
								.add(dienstplan.getDutyelement().get(j2));
						break;
					}
				}
			}
		}
		return dutyelementList;
	}

	public double berechneDurschnittlicheWiederholrateDienstplanRegular(
			ArrayList<Dienstplan> dienstplanliste, Fahrplan fahrplan) {

		double anzahlDutyGesamt = 0.0;
		double anzahlGleicheDuty = 0.0;
		double avgrepeat;
		boolean alleGleich = false;

		ArrayList<ArrayList<ArrayList<String>>> regularList = erstelleDutyelementListRegular(
				dienstplanliste, fahrplan);

		// Anzahl der Dienste von allen Dienstpl������nen
		for (int i = 0; i < dienstplanliste.size(); i++) {
			if (dienstplanliste.get(i).getFahrplanID() == fahrplan.getId()) {
				for (int j = 0; j < dienstplanliste.get(i).getDuty().size(); j++) {
					anzahlDutyGesamt++;

				}
			}
		}
		// Alle Dienstpl������ne
		for (int plan = 0; plan < regularList.size() - 1; plan++) {
			alleGleich = false;
			// Alle B������ndel in einem Plan
			for (int buendel = 0; buendel < regularList.get(plan).size(); buendel++) {
				// Alle ServiceJounreys im ersten B������ndel im ersten Plan
				for (int buendel2 = 0; buendel2 < regularList.get(plan + 1)
						.size(); buendel2++) {

					if (regularList.get(plan).get(buendel).size() == regularList
							.get(plan + 1).get(buendel2).size()) {
						// Alle ServiceJourneys im B������ndel
						for (int serviceJourney = 0; serviceJourney < regularList
								.get(plan).get(buendel).size(); serviceJourney++) {

							if (!(regularList.get(plan).get(buendel)
									.get(serviceJourney).equals(regularList
									.get(plan + 1).get(buendel2)
									.get(serviceJourney)))) {
								alleGleich = false;
								break;
							}
							alleGleich = true;
						}
						if (alleGleich) {
							anzahlGleicheDuty++;
							break;
						}
					} else {
						alleGleich = false;
					}

				}
			}
		}

		avgrepeat = anzahlDutyGesamt / (anzahlDutyGesamt - anzahlGleicheDuty);

		return avgrepeat;

	}

	// Erstellt eine ArrayList mit allen B������ndeln an ServiceJourneys zu den
	// einzelnen Dienstpl������nen
	public ArrayList<ArrayList<ArrayList<String>>> erstelleDutyelementListAll(
			ArrayList<Dienstplan> dienstplanliste) {

		ArrayList<ArrayList<ArrayList<String>>> ListPlaeneGesamt = new ArrayList<ArrayList<ArrayList<String>>>();
		int zaehler = 0;

		for (int i = 0; i < dienstplanliste.size(); i++) {
			ArrayList<ArrayList<String>> ListPlan = new ArrayList<ArrayList<String>>();
			zaehler = 0;
			for (int j = 0; j < dienstplanliste.get(i).getDuty().size(); j++) {
				ArrayList<String> serviceJourneyList = new ArrayList<String>();
				for (int j2 = zaehler; j2 < dienstplanliste.get(i)
						.getDutyelement().size(); j2++) {
					String dutyID = dienstplanliste.get(i).getDuty().get(j)
							.getId();
					if (dienstplanliste.get(i).getDutyelement().get(j2)
							.getElementType() == 1) {
						if (dienstplanliste.get(i).getDutyelement().get(j2)
								.getDutyID().equals(dutyID)) {
							serviceJourneyList.add(dienstplanliste.get(i)
									.getDutyelement().get(j2)
									.getServiceJourneyID());
						} else {
							ListPlan.add(serviceJourneyList);
							break;
						}
					}
					zaehler++;
					if (zaehler == dienstplanliste.get(i).getDutyelement()
							.size()) {
						ListPlan.add(serviceJourneyList);
					}
				}
			}
			ListPlaeneGesamt.add(ListPlan);
		}
		return ListPlaeneGesamt;
	}

	public ArrayList<ArrayList<ArrayList<String>>> erstelleDutyelementListRegular(
			ArrayList<Dienstplan> dienstplanliste, Fahrplan fahrplan) {

		ArrayList<ArrayList<ArrayList<String>>> ListPlaeneGesamt = new ArrayList<ArrayList<ArrayList<String>>>();
		int zaehler = 0;

		ArrayList<Dutyelement> dutyelementlist = new ArrayList<Dutyelement>();

		for (int i = 0; i < dienstplanliste.size(); i++) {
			ArrayList<ArrayList<String>> ListPlan = new ArrayList<ArrayList<String>>();
			dutyelementlist = regelmaessigeDutyelement(dienstplanliste.get(i),
					fahrplan);
			zaehler = 0;
			for (int j = 0; j < dienstplanliste.get(i).getDuty().size(); j++) {
				ArrayList<String> serviceJourneyList = new ArrayList<String>();
				for (int j2 = zaehler; j2 < dutyelementlist.size(); j2++) {
					String dutyID = dienstplanliste.get(i).getDuty().get(j)
							.getId();
					if (dutyelementlist.get(j2).getDutyID().equals(dutyID)) {
						serviceJourneyList.add(dutyelementlist.get(j2)
								.getServiceJourneyID());
						zaehler++;
						if (zaehler == dutyelementlist.size()) {
							ListPlan.add(serviceJourneyList);
						}
					} else {
						ListPlan.add(serviceJourneyList);
						break;
					}
				}
			}
			ListPlaeneGesamt.add(ListPlan);
		}
		return ListPlaeneGesamt;
	}

	//
	public int berechneDistanz(ArrayList<Dienstplan> dienstplanliste,
			Fahrplan fahrplan) {

		ArrayList<ArrayList<ArrayList<String>>> regularList = erstelleDutyelementListRegular(
				dienstplanliste, fahrplan);
		ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>> planListe = new ArrayList<ArrayList<ArrayList<ArrayList<Integer>>>>();

		// for each service journey will be set the previous and next service
		// journey
		// Plaene
		for (int i = 0; i < regularList.size(); i++) {
			ArrayList<ArrayList<ArrayList<Integer>>> plaene = new ArrayList<ArrayList<ArrayList<Integer>>>();
			// Buendel
			for (int j = 0; j < regularList.get(i).size(); j++) {
				ArrayList<ArrayList<Integer>> serviceJourneyReihenfolge = new ArrayList<ArrayList<Integer>>();
				// regular service journeys
				for (int j2 = 0; j2 < regularList.get(i).get(j).size(); j2++) {
					ArrayList<Integer> reihenfolge = new ArrayList<Integer>();
					if (j2 == 0) {
						// Vorgaenger
						reihenfolge.add(null);
						// pointer
						reihenfolge.add(Integer.parseInt(regularList.get(i)
								.get(j).get(j2)));
						// nachfolger
						reihenfolge.add(Integer.parseInt(regularList.get(i)
								.get(j).get(j2 + 1)));

					} else if (j2 == regularList.get(i).get(j).size() - 1) {
						// Vorgaenger
						reihenfolge.add(Integer.parseInt(regularList.get(i)
								.get(j).get(j2 - 1)));
						// pointer
						reihenfolge.add(Integer.parseInt(regularList.get(i)
								.get(j).get(j2)));
						// nachfolger
						reihenfolge.add(null);
						serviceJourneyReihenfolge.add(reihenfolge);
						break;
					} else {
						// Vorgaenger
						reihenfolge.add(Integer.parseInt(regularList.get(i)
								.get(j).get(j2 - 1)));
						// pointer
						reihenfolge.add(Integer.parseInt(regularList.get(i)
								.get(j).get(j2)));
						// nachfolger
						reihenfolge.add(Integer.parseInt(regularList.get(i)
								.get(j).get(j2 + 1)));
					}
					serviceJourneyReihenfolge.add(reihenfolge);
				}
				plaene.add(serviceJourneyReihenfolge);
			}
			planListe.add(plaene);
		}

		int unregelmaessigeFahrt = 0;

		ArrayList<Dutyelement> dutyelementRegular = regelmaessigeDutyelement(
				dienstplanliste.get(0), fahrplan);
		ArrayList<ArrayList<Integer>> listDP1 = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> listDP2 = new ArrayList<ArrayList<Integer>>();

		// two lists with regular service journeys of each dienstplan will be
		// filled
		for (int i = 0; i < planListe.size(); i++) {
			for (int j = 0; j < planListe.get(i).size(); j++) {
				for (int j2 = 0; j2 < planListe.get(i).get(j).size(); j2++) {
					if (i == 0) {
						listDP1.add(planListe.get(i).get(j).get(j2));
					} else if (i == 1) {
						listDP2.add(planListe.get(i).get(j).get(j2));
					}
				}
			}
		}

		int[] reihenfolgeDP1 = new int[3];
		int[] reihenfolgeDP2 = new int[3];

		for (int i = 0; i < dutyelementRegular.size(); i++) {

			for (int j = 0; j < listDP1.size(); j++) {
				if (listDP1.get(j).get(1) == Integer
						.parseInt(dutyelementRegular.get(i)
								.getServiceJourneyID())) {
					// if the dutyelement before/after the pointer null the
					// value will be set -1
					if (listDP1.get(j).get(0) == null) {
						reihenfolgeDP1[0] = -1;
					} else {
						reihenfolgeDP1[0] = listDP1.get(j).get(0);
					}
					if (listDP1.get(j).get(2) == null) {
						reihenfolgeDP1[2] = -1;
					} else {
						reihenfolgeDP1[2] = listDP1.get(j).get(2);
					}
					reihenfolgeDP1[1] = listDP1.get(j).get(1);
					break;
				}
			}

			for (int j = 0; j < listDP2.size(); j++) {
				if (listDP2.get(j).get(1) == Integer
						.parseInt(dutyelementRegular.get(i)
								.getServiceJourneyID())) {
					// if the dutyelement before/after the pointer null the
					// value will be set -1
					if (listDP2.get(j).get(0) == null) {
						reihenfolgeDP2[0] = -1;
					} else {
						reihenfolgeDP2[0] = listDP2.get(j).get(0);
					}
					if (listDP2.get(j).get(2) == null) {
						reihenfolgeDP2[2] = -1;
					} else {
						reihenfolgeDP2[2] = listDP2.get(j).get(2);
					}
					reihenfolgeDP2[1] = listDP2.get(j).get(1);
					break;
				}
			}

			// if the previous and next service journey of a regular service
			// journey similar to the regular service journey in the second
			// diesntplan the counter of regular service journeys will be
			// increased
			if (reihenfolgeDP1[0] != reihenfolgeDP2[0]
					&& reihenfolgeDP1[1] == reihenfolgeDP2[1]
					&& reihenfolgeDP1[2] == reihenfolgeDP2[2]) {
				unregelmaessigeFahrt++;
			} else if (reihenfolgeDP1[0] == reihenfolgeDP2[0]
					&& reihenfolgeDP1[1] == reihenfolgeDP2[1]
					&& reihenfolgeDP1[2] != reihenfolgeDP2[2]) {
				unregelmaessigeFahrt++;
			} else if (reihenfolgeDP1[0] != reihenfolgeDP2[0]
					&& reihenfolgeDP1[1] == reihenfolgeDP2[1]
					&& reihenfolgeDP1[2] != reihenfolgeDP2[2]) {
				unregelmaessigeFahrt++;
				unregelmaessigeFahrt++;
			}
		}
		return unregelmaessigeFahrt;
	}

	public double pVergleich(Fahrplan fahrplan) {

		int[][] anzahlFahrten = new int[7][1];

		// Alle regulären Fahrten eines Fahrplans
		ArrayList<Integer> serviceJourneyList = new ArrayList<Integer>();
		for (int i = 0; i < fahrplan.getServicejourney().size(); i++) {
			for (int k = 0; k < fahrplan.getDays().size(); k++) {
				if (fahrplan.getServicejourney().get(i).getiD() == fahrplan
						.getDays().get(k).getTripID()
						&& fahrplan.getDays().get(k).getD1() == 1
						&& fahrplan.getDays().get(k).getD2() == 1
						&& fahrplan.getDays().get(k).getD3() == 1
						&& fahrplan.getDays().get(k).getD4() == 1
						&& fahrplan.getDays().get(k).getD5() == 1) {
					serviceJourneyList.add(fahrplan.getServicejourney().get(i)
							.getiD());
					break;
				}
			}
		}

		// Listen mit allen Fahrten des jeweiligen Tages
		ArrayList<Integer> tripTotalMo = new ArrayList<Integer>();
		ArrayList<Integer> tripTotalDi = new ArrayList<Integer>();
		ArrayList<Integer> tripTotalMi = new ArrayList<Integer>();
		ArrayList<Integer> tripTotalDo = new ArrayList<Integer>();
		ArrayList<Integer> tripTotalFr = new ArrayList<Integer>();
		ArrayList<Integer> tripTotalSa = new ArrayList<Integer>();
		ArrayList<Integer> tripTotalSo = new ArrayList<Integer>();

		int days = 0;

		while (days < 7) {
			for (int i = 0; i < fahrplan.getDays().size(); i++) {

				switch (days) {
				case 0:

					if (fahrplan.getDays().get(i).getD1() == 1) {
						tripTotalMo.add(fahrplan.getDays().get(i).getTripID());
						anzahlFahrten[0][0]++;
					}
					break;
				case 1:
					if (fahrplan.getDays().get(i).getD2() == 1) {
						tripTotalDi.add(fahrplan.getDays().get(i).getTripID());
						anzahlFahrten[1][0]++;
					}
					break;
				case 2:

					if (fahrplan.getDays().get(i).getD3() == 1) {
						tripTotalMi.add(fahrplan.getDays().get(i).getTripID());
						anzahlFahrten[2][0]++;
					}
					break;
				case 3:

					if (fahrplan.getDays().get(i).getD4() == 1) {
						tripTotalDo.add(fahrplan.getDays().get(i).getTripID());
						anzahlFahrten[3][0]++;
					}
					break;
				case 4:

					if (fahrplan.getDays().get(i).getD5() == 1) {
						tripTotalFr.add(fahrplan.getDays().get(i).getTripID());
						anzahlFahrten[4][0]++;
					}
					break;
				case 5:

					if (fahrplan.getDays().get(i).getD6() == 1) {
						tripTotalSa.add(fahrplan.getDays().get(i).getTripID());
						anzahlFahrten[0][0]++;
					}
					break;
				case 6:

					if (fahrplan.getDays().get(i).getD7() == 1) {
						tripTotalSo.add(fahrplan.getDays().get(i).getTripID());
						anzahlFahrten[6][0]++;
					}
					break;
				default:
					break;
				}

			}
			days++;
		}

		// Listen mit allen Fahrten des jeweiligen Tages
		ArrayList<Integer> tripIrregularMo = new ArrayList<Integer>();
		ArrayList<Integer> tripIrregularDi = new ArrayList<Integer>();
		ArrayList<Integer> tripIrregularMi = new ArrayList<Integer>();
		ArrayList<Integer> tripIrregularDo = new ArrayList<Integer>();
		ArrayList<Integer> tripIrregularFr = new ArrayList<Integer>();
		ArrayList<Integer> tripIrregularSa = new ArrayList<Integer>();
		ArrayList<Integer> tripIrregularSo = new ArrayList<Integer>();

		for (int j = 0; j < tripTotalMo.size(); j++) {
			if (!serviceJourneyList.contains(tripTotalMo.get(j))) {
				tripIrregularMo.add(tripTotalMo.get(j));
			}
		}
		for (int j = 0; j < tripTotalDi.size(); j++) {
			if (!serviceJourneyList.contains(tripTotalDi.get(j))) {
				tripIrregularDi.add(tripTotalDi.get(j));
			}
		}
		for (int j = 0; j < tripTotalMi.size(); j++) {
			if (!serviceJourneyList.contains(tripTotalMi.get(j))) {
				tripIrregularMi.add(tripTotalMi.get(j));
			}
		}
		for (int j = 0; j < tripTotalDo.size(); j++) {
			if (!serviceJourneyList.contains(tripTotalDo.get(j))) {
				tripIrregularDo.add(tripTotalDo.get(j));
			}
		}
		for (int j = 0; j < tripTotalFr.size(); j++) {
			if (!serviceJourneyList.contains(tripTotalFr.get(j))) {
				tripIrregularFr.add(tripTotalFr.get(j));
			}
		}
		for (int j = 0; j < tripTotalSa.size(); j++) {
			if (!serviceJourneyList.contains(tripTotalSa.get(j))) {
				tripIrregularSa.add(tripTotalSa.get(j));
			}
		}
		for (int j = 0; j < tripTotalSo.size(); j++) {
			if (!serviceJourneyList.contains(tripTotalSo.get(j))) {
				tripIrregularSo.add(tripTotalSo.get(j));
			}
		}
		
		ArrayList<ArrayList<Integer>> listDays = new ArrayList<ArrayList<Integer>>();
		listDays.add(tripIrregularMo);
		listDays.add(tripIrregularDi);
		listDays.add(tripIrregularMi);
		listDays.add(tripIrregularDo);
		listDays.add(tripIrregularFr);
		listDays.add(tripIrregularSa);
		listDays.add(tripIrregularSo);
		
		int[][] matrix = new int[7][7];
		int count=0;
		for (int tag = 0; tag < listDays.size(); tag++) {	
				for (int tag2 = tag+1; tag2 < listDays.size()-2; tag2++) {
					for (int j = 0; j < listDays.get(tag).size(); j++) {
						for (int j2 = 0; j2 < listDays.get(tag2).size(); j2++) {
							System.out.println(listDays.get(tag).get(j).intValue());
							System.out.println(listDays.get(tag2).get(j2).intValue());
							if(listDays.get(tag).get(j).intValue()==listDays.get(tag2).get(j2).intValue()){
							count++;
							}
							
						}
						if(j==listDays.get(tag).size()-1){
							matrix[tag2][tag]=listDays.get(tag).size()+listDays.get(tag2).size()-count;
							count=0;
					}
				}
			}
		}
		
		double zahlFahrten = 0;
		double zwischenwert=0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if(matrix[i][j]>0){
					zwischenwert+=matrix[i][j];
					zahlFahrten++;
				}
			}
		}
		
		double pWert=zwischenwert/zahlFahrten;
		
		
		
		return pWert;
	}
}