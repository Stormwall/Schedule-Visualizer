package sv.creation.adress.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * Dies ist eine Klasse, die eine .txt-Datei zeilenweise einliest und in einer
 * ArrayList speichert. Mit dieser ArrayList kˆnnen die Werte in die Datenbank
 * importiert werden.
 * 
 */

public class StringSplitter {

	// ***********************************************************
	// ***********************************************************
	// ***********************************************************
	// ****** Array lists to separate the elements of data *******
	// ******                                              *******
	// ***********************************************************
	// ***********************************************************
	// ***********************************************************

	private static ArrayList<String> stringList = new ArrayList<String>();

	// ****** Dienstplan ******

	// Array lists of duty
	private static ArrayList<String> dutyDutyID = new ArrayList<String>();
	private static ArrayList<String> dutyDutyType = new ArrayList<String>();

	// Array lists of dutyelement
	private static ArrayList<String> dutyelementDutyType = new ArrayList<String>();
	private static ArrayList<String> dutyelementBlockID = new ArrayList<String>();
	private static ArrayList<String> dutyelementServiceJourneyID = new ArrayList<String>();
	private static ArrayList<String> dutyelementFromStopID = new ArrayList<String>();
	private static ArrayList<String> dutyelementToStopID = new ArrayList<String>();
	private static ArrayList<String> dutyelementDepTime = new ArrayList<String>();
	private static ArrayList<String> dutyelementArrTime = new ArrayList<String>();
	private static ArrayList<String> dutyelementElementType = new ArrayList<String>();
	private static ArrayList<String> dutyelementServiceJourneyCode = new ArrayList<String>();

	// ****** Umlaufplan ******

	// Array lists of block
	private static ArrayList<String> id = new ArrayList<String>();
	private static ArrayList<String> vehTypeID = new ArrayList<String>();
	private static ArrayList<String> depotID = new ArrayList<String>();

	// Array lists of blockelement
	private static ArrayList<String> blockelementBlockID = new ArrayList<String>();
	private static ArrayList<String> blockelementServiceJourneyID = new ArrayList<String>();
	private static ArrayList<String> blockelementFromStopID = new ArrayList<String>();
	private static ArrayList<String> blockelementToStopID = new ArrayList<String>();
	private static ArrayList<String> blockelementDepTime = new ArrayList<String>();
	private static ArrayList<String> blockelementArrTime = new ArrayList<String>();
	private static ArrayList<String> blockelementElementType = new ArrayList<String>();
	private static ArrayList<String> blockelementServiceJourneyCode = new ArrayList<String>();

	// ****** Fahrplan ******

	// Array lists of stoppoint
	private static ArrayList<String> stopID = new ArrayList<String>();
	private static ArrayList<String> stopCode = new ArrayList<String>();
	private static ArrayList<String> stopName = new ArrayList<String>();

	// Array lists of line
	private static ArrayList<String> lineID = new ArrayList<String>();
	private static ArrayList<String> lineCode = new ArrayList<String>();
	private static ArrayList<String> lineName = new ArrayList<String>();

	// Array lists of vehicle type
	private static ArrayList<String> vehicleTypeID = new ArrayList<String>();
	private static ArrayList<String> vehicleTypeCode = new ArrayList<String>();
	private static ArrayList<String> vehicleTypeName = new ArrayList<String>();
	private static ArrayList<String> vehicleTypeVehCost = new ArrayList<String>();
	private static ArrayList<String> vehicleTypeKmCost = new ArrayList<String>();
	private static ArrayList<String> vehicleTypeHourCost = new ArrayList<String>();
	private static ArrayList<String> vehicleTypeCapacity = new ArrayList<String>();

	// Array lists of vehicle type group
	private static ArrayList<String> vehicleTypeGroupID = new ArrayList<String>();
	private static ArrayList<String> vehicleTypeGroupCode = new ArrayList<String>();
	private static ArrayList<String> vehicleTypeGroupName = new ArrayList<String>();

	// Array lists of vehicle to vehicle type group
	private static ArrayList<String> vehicleToVehicleTypeGroupVehTypeID = new ArrayList<String>();
	private static ArrayList<String> vehicleToVehicleTypeGroupVehTypeGroupID = new ArrayList<String>();

	// Array lists of vehicle capacity to stoppoint
	private static ArrayList<String> vehicleCapToStopVehTypeID = new ArrayList<String>();
	private static ArrayList<String> vehicleCapToStopStoppointID = new ArrayList<String>();
	private static ArrayList<String> vehicleCapToStopMin = new ArrayList<String>();
	private static ArrayList<String> vehicleCapToStopMax = new ArrayList<String>();

	// Array lists of service journeys
	private static ArrayList<String> serviceJourneyID = new ArrayList<String>();
	private static ArrayList<String> serviceJourneyLineID = new ArrayList<String>();
	private static ArrayList<String> serviceJourneyFromStopID = new ArrayList<String>();
	private static ArrayList<String> serviceJourneyToStopID = new ArrayList<String>();
	private static ArrayList<String> serviceJourneyDepTime = new ArrayList<String>();
	private static ArrayList<String> serviceJourneyArrTime = new ArrayList<String>();
	private static ArrayList<String> serviceJourneyMinAheadTime = new ArrayList<String>();
	private static ArrayList<String> serviceJourneyMinLayoverTime = new ArrayList<String>();
	private static ArrayList<String> serviceJourneyVehTypeGroupID = new ArrayList<String>();
	private static ArrayList<String> serviceJourneyMaxShiftBackwardSeconds = new ArrayList<String>();
	private static ArrayList<String> serviceJourneyMaxShiftForwardSeconds = new ArrayList<String>();
	private static ArrayList<String> serviceJourneyFromStopBreakFacility = new ArrayList<String>();
	private static ArrayList<String> serviceJourneyToStopBreakFacility = new ArrayList<String>();
	private static ArrayList<String> serviceJourneyCode = new ArrayList<String>();

	// Array lists of connections (deadruntime)
	private static ArrayList<String> deadruntimeFromStopID = new ArrayList<String>();
	private static ArrayList<String> deadruntimeToStopID = new ArrayList<String>();
	private static ArrayList<String> deadruntimeFromTime = new ArrayList<String>();
	private static ArrayList<String> deadruntimeToTime = new ArrayList<String>();
	private static ArrayList<String> deadruntimeDistance = new ArrayList<String>();
	private static ArrayList<String> deadruntimeRuntime = new ArrayList<String>();

	// Array lists of reliefpoints
	private static ArrayList<String> reliefpointID = new ArrayList<String>();
	private static ArrayList<String> reliefpointServiceJourneyID = new ArrayList<String>();
	private static ArrayList<String> reliefpointStoppointID = new ArrayList<String>();
	private static ArrayList<String> reliefpointStoptime = new ArrayList<String>();

	public static ArrayList<String> convertStringToArraylist(String str) {

		// "\\s*,\\s*" anstatt "," damit Leerzeichen vor und nach dem Komma im
		// urspr¸nglichen String ignoriert werden.
		Collections.addAll(stringList, str.split("\\s*,\\s*"));

		System.out.println(stringList);
		return stringList;
	}

	public static void convertStringToDutyID(String str) {

		ArrayList<String> list = new ArrayList<String>();

		Collections.addAll(list, str.split(":"));
		String wort = list.get(1);
		System.out.println(wort);
		list.clear();
		Collections.addAll(list, wort.split(";"));
		System.out.println(list.get(1).toString());
		dutyDutyID.add(list.get(0));
		dutyDutyType.add(list.get(1));
		System.out.println(dutyDutyID);

	}

	// *************************************************
	// ****** Method to read the Dienstplan data *******
	// ******                                    *******
	// *************************************************

	public static void readTxtDienstplan() {

		try {

			// test.txt Data has to be in the project file in your workspace
			BufferedReader dienstplan = new BufferedReader(new FileReader(
					"test.txt"));
			String zeile = null;
			ArrayList<String> zeilenelemente = new ArrayList<String>();
			while ((zeile = dienstplan.readLine()) != null) {

				// All lines with relevant data will be read
				if (!zeile.startsWith("*") && !zeile.startsWith("$")) {
					Collections.addAll(zeilenelemente, zeile.split(";"));

					// The service types data will be split in separated array
					// lists
					if (zeilenelemente.size() == 2) {
						dutyDutyID.add(zeilenelemente.get(0));
						dutyDutyType.add(zeilenelemente.get(1));
						zeilenelemente.clear();
					}

					// The service data will be split in seperated array lists
					if (zeilenelemente.size() == 8) {
						dutyelementDutyType.add(zeilenelemente.get(0));
						dutyelementBlockID.add(zeilenelemente.get(1));
						dutyelementServiceJourneyID.add(zeilenelemente.get(2));
						dutyelementFromStopID.add(zeilenelemente.get(3));
						dutyelementToStopID.add(zeilenelemente.get(4));
						dutyelementDepTime.add(zeilenelemente.get(5));
						dutyelementArrTime.add(zeilenelemente.get(6));
						dutyelementElementType.add(zeilenelemente.get(7));
					}

					// ATTENTION: The data can contain 9 elements including
					// ServiceJourneyCode
					if (zeilenelemente.size() == 9) {
						dutyelementDutyType.add(zeilenelemente.get(0));
						dutyelementBlockID.add(zeilenelemente.get(1));
						dutyelementServiceJourneyID.add(zeilenelemente.get(2));
						dutyelementFromStopID.add(zeilenelemente.get(3));
						dutyelementToStopID.add(zeilenelemente.get(4));
						dutyelementDepTime.add(zeilenelemente.get(5));
						dutyelementArrTime.add(zeilenelemente.get(6));
						dutyelementElementType.add(zeilenelemente.get(7));
						dutyelementServiceJourneyCode
								.add(zeilenelemente.get(8));
					}

					// Size of the zeilenelemente array list will be reset
					else
						zeilenelemente.clear();
				}

				/**
				 * if(zeile.startsWith("*")){ //convertStringToArraylist(zeile);
				 * 
				 * //continue; }if(zeile.startsWith("$DUTY")){ //int anzahlDaten
				 * = Integer.parseInt(in.readLine());
				 * System.out.println(zeile.length());
				 * //System.out.println(anzahlDaten);
				 * convertStringToDutyID(zeile);
				 * }if(zeile.startsWith("$DUTYELEMENT")){
				 * convertStringToDutyID(zeile); } //
				 * System.out.println("Gelesene Zeile: " + zeile);
				 * //convertStringToArraylist(zeile);
				 */
			}
			System.out.println(dutyDutyID);
			System.out.println(dutyelementServiceJourneyID);
			dienstplan.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// *************************************************
	// ****** Method to read the Umlaufplan data *******
	// ******                                    *******
	// *************************************************

	public static void readTxtUmlaufplan() {

		try {

			// testumlauf.txt Data has to be in the project file in your workspace
			BufferedReader umlaufplan = new BufferedReader(new FileReader(
					"testumlauf.txt"));
			String zeile = null;
			ArrayList<String> zeilenelemente = new ArrayList<String>();
			while ((zeile = umlaufplan.readLine()) != null) {

				// All lines with relevant data will be read
				if (!zeile.startsWith("*") && !zeile.startsWith("$")) {
					Collections.addAll(zeilenelemente, zeile.split(";"));

					// The block data will be split in separated array
					// lists
					if (zeilenelemente.size() == 3) {
						id.add(zeilenelemente.get(0));
						vehTypeID.add(zeilenelemente.get(1));
						depotID.add(zeilenelemente.get(2));
						zeilenelemente.clear();
					}

					// The block element data will be split in separated array lists
					if (zeilenelemente.size() == 7) {
						blockelementBlockID.add(zeilenelemente.get(0));
						blockelementServiceJourneyID.add(zeilenelemente.get(1));
						blockelementFromStopID.add(zeilenelemente.get(2));
						blockelementToStopID.add(zeilenelemente.get(3));
						blockelementDepTime.add(zeilenelemente.get(4));
						blockelementArrTime.add(zeilenelemente.get(5));
						blockelementElementType.add(zeilenelemente.get(6));
					}

					// ATTENTION: The data can contain 9 elements including
					// ServiceJourneyCode
					if (zeilenelemente.size() == 8) {
						blockelementBlockID.add(zeilenelemente.get(0));
						blockelementServiceJourneyID.add(zeilenelemente.get(1));
						blockelementFromStopID.add(zeilenelemente.get(2));
						blockelementToStopID.add(zeilenelemente.get(3));
						blockelementDepTime.add(zeilenelemente.get(4));
						blockelementArrTime.add(zeilenelemente.get(5));
						blockelementElementType.add(zeilenelemente.get(6));
						blockelementServiceJourneyCode.add(zeilenelemente
								.get(7));

					}

					// Size of the zeilenelemente array list will be reset
					else
						zeilenelemente.clear();
				}
			}
			System.out.println(id);
			System.out.println(blockelementArrTime);
			umlaufplan.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ***********************************************
	// ****** Method to read the Fahrplan data *******
	// ******                                  *******
	// ***********************************************

	public static void readTxtFahrplan() {

		try {

			// testfahrplan.txt Data has to be in the project file in your
			// workspace
			BufferedReader fahrplan = new BufferedReader(new FileReader(
					"testfahrplan.txt"));
			String zeile = null;
			ArrayList<String> zeilenelemente = new ArrayList<String>();

			// will be true if the specific section in the txt data was read
			boolean stoppoint = false;
			boolean vehicleTypeGroup = false;
			boolean line = false;
			boolean vehicleCapToStop = false;
			boolean reliefpoint = false;

			while ((zeile = fahrplan.readLine()) != null) {

				// Set the variable true if the section was read

				if (zeile.startsWith("$STOPPOINT")) {
					stoppoint = true;
					continue;
				}
				if (zeile.startsWith("$LINE")) {
					stoppoint = false;
					line = true;
					continue;
				}
				if (zeile.startsWith("$VEHICLETYPEGROUP")) {
					stoppoint = false;
					line = false;
					vehicleTypeGroup = true;
					continue;
				}
				if (zeile.startsWith("$VEHTYPECAPTOSTOPPOINT")) {
					stoppoint = false;
					line = false;
					vehicleTypeGroup = false;
					vehicleCapToStop = true;
					continue;
				}
				if (zeile.startsWith("$RELIEFPOINT")) {
					stoppoint = false;
					line = false;
					vehicleTypeGroup = false;
					vehicleCapToStop = false;
					reliefpoint = true;
					continue;
				}

				// All lines with relevant data will be read
				if (!zeile.startsWith("*") && !zeile.startsWith("$")) {
					Collections.addAll(zeilenelemente, zeile.split(";"));

					// The stoppoint data will be split in separated array
					// lists
					if (zeilenelemente.size() == 3 && stoppoint == true) {
						stopID.add(zeilenelemente.get(0));
						stopCode.add(zeilenelemente.get(1));
						stopName.add(zeilenelemente.get(2));
						zeilenelemente.clear();
					}

					// The lines data will be split in separated array
					// lists
					if (zeilenelemente.size() == 3 && line == true) {
						lineID.add(zeilenelemente.get(0));
						lineCode.add(zeilenelemente.get(1));
						lineName.add(zeilenelemente.get(2));
						zeilenelemente.clear();
					}

					// The vehicle types data will be split in separated array
					// lists
					if (zeilenelemente.size() == 7) {
						vehicleTypeID.add(zeilenelemente.get(0));
						vehicleTypeCode.add(zeilenelemente.get(1));
						vehicleTypeName.add(zeilenelemente.get(2));
						vehicleTypeVehCost.add(zeilenelemente.get(3));
						vehicleTypeKmCost.add(zeilenelemente.get(4));
						vehicleTypeHourCost.add(zeilenelemente.get(5));
						vehicleTypeCapacity.add(zeilenelemente.get(6));
						zeilenelemente.clear();
					}

					// The vehicle type group data will be split in separated
					// array
					// lists
					if (zeilenelemente.size() == 3 && vehicleTypeGroup == true) {
						vehicleTypeGroupID.add(zeilenelemente.get(0));
						vehicleTypeGroupCode.add(zeilenelemente.get(1));
						vehicleTypeGroupName.add(zeilenelemente.get(2));
						zeilenelemente.clear();
					}

					// The vehicle to vehicle type group data will be split in
					// separated array
					// lists
					if (zeilenelemente.size() == 2) {
						vehicleToVehicleTypeGroupVehTypeID.add(zeilenelemente
								.get(0));
						vehicleToVehicleTypeGroupVehTypeGroupID
								.add(zeilenelemente.get(1));
						zeilenelemente.clear();
					}

					// The vehicle capacities to stoppoints data will be split
					// in separated array
					// lists
					if (zeilenelemente.size() == 4 && vehicleCapToStop == true) {
						vehicleCapToStopVehTypeID.add(zeilenelemente.get(0));
						vehicleCapToStopStoppointID.add(zeilenelemente.get(1));
						vehicleCapToStopMin.add(zeilenelemente.get(2));
						vehicleCapToStopMax.add(zeilenelemente.get(3));
						zeilenelemente.clear();
					}

					// The service journey data will be split in separated array
					// lists
					if (zeilenelemente.size() == 14) {
						serviceJourneyID.add(zeilenelemente.get(0));
						serviceJourneyLineID.add(zeilenelemente.get(1));
						serviceJourneyFromStopID.add(zeilenelemente.get(2));
						serviceJourneyToStopID.add(zeilenelemente.get(3));
						serviceJourneyDepTime.add(zeilenelemente.get(4));
						serviceJourneyArrTime.add(zeilenelemente.get(5));
						serviceJourneyMinAheadTime.add(zeilenelemente.get(6));
						serviceJourneyMinLayoverTime.add(zeilenelemente.get(7));
						serviceJourneyVehTypeGroupID.add(zeilenelemente.get(8));
						serviceJourneyMaxShiftBackwardSeconds
								.add(zeilenelemente.get(9));
						serviceJourneyMaxShiftForwardSeconds.add(zeilenelemente
								.get(10));
						serviceJourneyFromStopBreakFacility.add(zeilenelemente
								.get(11));
						serviceJourneyToStopBreakFacility.add(zeilenelemente
								.get(12));
						serviceJourneyCode.add(zeilenelemente.get(13));
						zeilenelemente.clear();
					}

					// The dead runtime data will be split in separated array
					// lists
					if (zeilenelemente.size() == 6) {
						deadruntimeFromStopID.add(zeilenelemente.get(0));
						deadruntimeToStopID.add(zeilenelemente.get(1));
						deadruntimeFromTime.add(zeilenelemente.get(2));
						deadruntimeToTime.add(zeilenelemente.get(3));
						deadruntimeDistance.add(zeilenelemente.get(4));
						deadruntimeRuntime.add(zeilenelemente.get(5));
						zeilenelemente.clear();
					}

					// The reliefpoint data will be split in separated array
					// lists
					if (zeilenelemente.size() == 4 && reliefpoint == true) {
						reliefpointID.add(zeilenelemente.get(0));
						reliefpointServiceJourneyID.add(zeilenelemente.get(1));
						reliefpointStoppointID.add(zeilenelemente.get(2));
						reliefpointStoptime.add(zeilenelemente.get(3));
						zeilenelemente.clear();
					}

				}

			}

			// Testing the filled array lists
			System.out.println(stopID);
			System.out.println(lineCode);
			System.out.println(vehicleTypeKmCost);
			System.out.println(vehicleTypeGroupName);
			System.out.println(vehicleToVehicleTypeGroupVehTypeGroupID);
			System.out.println(vehicleCapToStopStoppointID);
			System.out.println(serviceJourneyLineID);
			System.out.println(deadruntimeDistance);
			System.out.println(reliefpointStoptime);
			fahrplan.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		readTxtDienstplan();
		readTxtUmlaufplan();
		readTxtFahrplan();
	}

}