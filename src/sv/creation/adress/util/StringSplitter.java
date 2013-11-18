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
	private static ArrayList<Integer> id = new ArrayList<Integer>();
	private static ArrayList<Integer> vehTypeID = new ArrayList<Integer>();
	private static ArrayList<Integer> depotID = new ArrayList<Integer>();

	// Array lists of blockelement
	private static ArrayList<Integer> blockelementBlockID = new ArrayList<Integer>();
	private static ArrayList<String> blockelementServiceJourneyID = new ArrayList<String>();
	private static ArrayList<Integer> blockelementFromStopID = new ArrayList<Integer>();
	private static ArrayList<Integer> blockelementToStopID = new ArrayList<Integer>();
	private static ArrayList<String> blockelementDepTime = new ArrayList<String>();
	private static ArrayList<String> blockelementArrTime = new ArrayList<String>();
	private static ArrayList<Integer> blockelementElementType = new ArrayList<Integer>();
	private static ArrayList<Integer> blockelementServiceJourneyCode = new ArrayList<Integer>();

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
					"testumlauf.txt"));
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

	public void readTxtUmlaufplan() {

		try {

			// testumlauf.txt Data has to be in the project file in your workspace
			BufferedReader umlaufplan = new BufferedReader(new FileReader(
					"resources/quellen/testumlauf.txt"));
			String zeile = null;
			ArrayList<String> zeilenelemente = new ArrayList<String>();
			while ((zeile = umlaufplan.readLine()) != null) {

				// All lines with relevant data will be read
				if (!zeile.startsWith("*") && !zeile.startsWith("$")) {
					Collections.addAll(zeilenelemente, zeile.split(";"));

					// The block data will be split in separated array
					// lists
					if (zeilenelemente.size() == 3) {
						int idZahl=Integer.parseInt(zeilenelemente.get(0));
						int vehTypeIDZahl=Integer.parseInt(zeilenelemente.get(1));
						int depotIDZahl=Integer.parseInt(zeilenelemente.get(2));
						id.add(idZahl);
						vehTypeID.add(vehTypeIDZahl);
						depotID.add(depotIDZahl);
						zeilenelemente.clear();
					}

					// The block element data will be split, parsed and saved in separated array lists
					if (zeilenelemente.size() == 7) {
						int blockID=Integer.parseInt(zeilenelemente.get(0));
						//int serviceJourneyID =Integer.parseInt(zeilenelemente.get(1));
						int fromStopID=Integer.parseInt(zeilenelemente.get(2));
						int toStopID=Integer.parseInt(zeilenelemente.get(3));
						//int depTime=Integer.parseInt(zeilenelemente.get(4));
						//int arrTime=Integer.parseInt(zeilenelemente.get(5));
						int elementType=Integer.parseInt(zeilenelemente.get(6));
			
						blockelementBlockID.add(blockID);
						blockelementServiceJourneyID.add(zeilenelemente.get(1));
						blockelementFromStopID.add(fromStopID);
						blockelementToStopID.add(toStopID);
						blockelementDepTime.add(zeilenelemente.get(4));
						blockelementArrTime.add(zeilenelemente.get(5));
						blockelementElementType.add(elementType);
					}

//					// ATTENTION: The data can contain 9 elements including
//					// ServiceJourneyCode
//					if (zeilenelemente.size() == 8) {
//						blockelementBlockID.add(zeilenelemente.get(0));
//						blockelementServiceJourneyID.add(zeilenelemente.get(1));
//						blockelementFromStopID.add(zeilenelemente.get(2));
//						blockelementToStopID.add(zeilenelemente.get(3));
//						blockelementDepTime.add(zeilenelemente.get(4));
//						blockelementArrTime.add(zeilenelemente.get(5));
//						blockelementElementType.add(zeilenelemente.get(6));
//						blockelementServiceJourneyCode.add(zeilenelemente
//								.get(7));
//
//					}

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

		//readTxtDienstplan();
		//readTxtUmlaufplan();
		//readTxtFahrplan();
	}
	
	// ************************************************
	// ****** Getter Methods of the array lists *******
	// ******                                   *******
	// ************************************************


	public static ArrayList<String> getStringList() {
		return stringList;
	}

	public static ArrayList<String> getDutyDutyID() {
		return dutyDutyID;
	}

	public static ArrayList<String> getDutyDutyType() {
		return dutyDutyType;
	}

	public static ArrayList<String> getDutyelementDutyType() {
		return dutyelementDutyType;
	}

	public static ArrayList<String> getDutyelementBlockID() {
		return dutyelementBlockID;
	}

	public static ArrayList<String> getDutyelementServiceJourneyID() {
		return dutyelementServiceJourneyID;
	}

	public static ArrayList<String> getDutyelementFromStopID() {
		return dutyelementFromStopID;
	}

	public static ArrayList<String> getDutyelementToStopID() {
		return dutyelementToStopID;
	}

	public static ArrayList<String> getDutyelementDepTime() {
		return dutyelementDepTime;
	}

	public static ArrayList<String> getDutyelementArrTime() {
		return dutyelementArrTime;
	}

	public static ArrayList<String> getDutyelementElementType() {
		return dutyelementElementType;
	}

	public static ArrayList<String> getDutyelementServiceJourneyCode() {
		return dutyelementServiceJourneyCode;
	}

	public ArrayList<Integer> getId() {
		return id;
	}

	public ArrayList<Integer> getVehTypeID() {
		return vehTypeID;
	}

	public ArrayList<Integer> getDepotID() {
		return depotID;
	}

	public ArrayList<Integer> getBlockelementBlockID() {
		return blockelementBlockID;
	}

	public ArrayList<String> getBlockelementServiceJourneyID() {
		return blockelementServiceJourneyID;
	}

	public ArrayList<Integer> getBlockelementFromStopID() {
		return blockelementFromStopID;
	}

	public ArrayList<Integer> getBlockelementToStopID() {
		return blockelementToStopID;
	}

	public ArrayList<String> getBlockelementDepTime() {
		return blockelementDepTime;
	}

	public ArrayList<String> getBlockelementArrTime() {
		return blockelementArrTime;
	}

	public ArrayList<Integer> getBlockelementElementType() {
		return blockelementElementType;
	}

	public ArrayList<Integer> getBlockelementServiceJourneyCode() {
		return blockelementServiceJourneyCode;
	}

	public ArrayList<String> getStopID() {
		return stopID;
	}

	public ArrayList<String> getStopCode() {
		return stopCode;
	}

	public ArrayList<String> getStopName() {
		return stopName;
	}

	public ArrayList<String> getLineID() {
		return lineID;
	}

	public ArrayList<String> getLineCode() {
		return lineCode;
	}

	public ArrayList<String> getLineName() {
		return lineName;
	}

	public ArrayList<String> getVehicleTypeID() {
		return vehicleTypeID;
	}

	public ArrayList<String> getVehicleTypeCode() {
		return vehicleTypeCode;
	}

	public ArrayList<String> getVehicleTypeName() {
		return vehicleTypeName;
	}

	public ArrayList<String> getVehicleTypeVehCost() {
		return vehicleTypeVehCost;
	}

	public static ArrayList<String> getVehicleTypeKmCost() {
		return vehicleTypeKmCost;
	}

	public static ArrayList<String> getVehicleTypeHourCost() {
		return vehicleTypeHourCost;
	}

	public static ArrayList<String> getVehicleTypeCapacity() {
		return vehicleTypeCapacity;
	}

	public static ArrayList<String> getVehicleTypeGroupID() {
		return vehicleTypeGroupID;
	}

	public static ArrayList<String> getVehicleTypeGroupCode() {
		return vehicleTypeGroupCode;
	}

	public static ArrayList<String> getVehicleTypeGroupName() {
		return vehicleTypeGroupName;
	}

	public static ArrayList<String> getVehicleToVehicleTypeGroupVehTypeID() {
		return vehicleToVehicleTypeGroupVehTypeID;
	}

	public static ArrayList<String> getVehicleToVehicleTypeGroupVehTypeGroupID() {
		return vehicleToVehicleTypeGroupVehTypeGroupID;
	}

	public static ArrayList<String> getVehicleCapToStopVehTypeID() {
		return vehicleCapToStopVehTypeID;
	}

	public static ArrayList<String> getVehicleCapToStopStoppointID() {
		return vehicleCapToStopStoppointID;
	}

	public static ArrayList<String> getVehicleCapToStopMin() {
		return vehicleCapToStopMin;
	}

	public static ArrayList<String> getVehicleCapToStopMax() {
		return vehicleCapToStopMax;
	}

	public static ArrayList<String> getServiceJourneyID() {
		return serviceJourneyID;
	}

	public static ArrayList<String> getServiceJourneyLineID() {
		return serviceJourneyLineID;
	}

	public static ArrayList<String> getServiceJourneyFromStopID() {
		return serviceJourneyFromStopID;
	}

	public static ArrayList<String> getServiceJourneyToStopID() {
		return serviceJourneyToStopID;
	}

	public static ArrayList<String> getServiceJourneyDepTime() {
		return serviceJourneyDepTime;
	}

	public static ArrayList<String> getServiceJourneyArrTime() {
		return serviceJourneyArrTime;
	}

	public static ArrayList<String> getServiceJourneyMinAheadTime() {
		return serviceJourneyMinAheadTime;
	}

	public static ArrayList<String> getServiceJourneyMinLayoverTime() {
		return serviceJourneyMinLayoverTime;
	}

	public static ArrayList<String> getServiceJourneyVehTypeGroupID() {
		return serviceJourneyVehTypeGroupID;
	}

	public static ArrayList<String> getServiceJourneyMaxShiftBackwardSeconds() {
		return serviceJourneyMaxShiftBackwardSeconds;
	}

	public static ArrayList<String> getServiceJourneyMaxShiftForwardSeconds() {
		return serviceJourneyMaxShiftForwardSeconds;
	}

	public static ArrayList<String> getServiceJourneyFromStopBreakFacility() {
		return serviceJourneyFromStopBreakFacility;
	}

	public static ArrayList<String> getServiceJourneyToStopBreakFacility() {
		return serviceJourneyToStopBreakFacility;
	}

	public static ArrayList<String> getServiceJourneyCode() {
		return serviceJourneyCode;
	}

	public static ArrayList<String> getDeadruntimeFromStopID() {
		return deadruntimeFromStopID;
	}

	public static ArrayList<String> getDeadruntimeToStopID() {
		return deadruntimeToStopID;
	}

	public static ArrayList<String> getDeadruntimeFromTime() {
		return deadruntimeFromTime;
	}

	public static ArrayList<String> getDeadruntimeToTime() {
		return deadruntimeToTime;
	}

	public static ArrayList<String> getDeadruntimeDistance() {
		return deadruntimeDistance;
	}

	public static ArrayList<String> getDeadruntimeRuntime() {
		return deadruntimeRuntime;
	}

	public static ArrayList<String> getReliefpointID() {
		return reliefpointID;
	}

	public static ArrayList<String> getReliefpointServiceJourneyID() {
		return reliefpointServiceJourneyID;
	}

	public static ArrayList<String> getReliefpointStoppointID() {
		return reliefpointStoppointID;
	}

	public static ArrayList<String> getReliefpointStoptime() {
		return reliefpointStoptime;
	}
	
	

}