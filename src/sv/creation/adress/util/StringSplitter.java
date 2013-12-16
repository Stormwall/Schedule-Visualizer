
package sv.creation.adress.util;

/**
 * DESCRIPTION:
 * This class deliveres methods for reading data from txt-files line by line and storeing them into array-lists.
 * With these array-lists, the schedule data can be imported into the database.
 * 
 * CONTENTS:
 * 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

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
	//*************************
	//******* duty roster******
	//*************************
	
	// Array lists of duty
	private static ArrayList<String> dutyDutyID = new ArrayList<String>();
	private static ArrayList<String> dutyDutyType = new ArrayList<String>();

	// Array lists of dutyelement
	private static ArrayList<String> dutyelementDutyType = new ArrayList<String>();
	private static ArrayList<Integer> dutyelementBlockID = new ArrayList<Integer>();
	private static ArrayList<String> dutyelementServiceJourneyID = new ArrayList<String>();
	private static ArrayList<Integer> dutyelementFromStopID = new ArrayList<Integer>();
	private static ArrayList<Integer> dutyelementToStopID = new ArrayList<Integer>();
	private static ArrayList<String> dutyelementDepTime = new ArrayList<String>();
	private static ArrayList<String> dutyelementArrTime = new ArrayList<String>();
	private static ArrayList<Integer> dutyelementElementType = new ArrayList<Integer>();
	private static ArrayList<String> dutyelementServiceJourneyCode = new ArrayList<String>();
	
	//Array list of day id
	private static ArrayList<String> dutyelementDayID = new ArrayList<String>();

	//*************************
	//******* tour plan********
	//*************************

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
	
	
	// Array lists of exceptional blockelements
	
		private static ArrayList<String> exceptionalblockelementServiceJourneyID = new ArrayList<String>();
		private static ArrayList<Integer> exceptionalblockelementBlockID = new ArrayList<Integer>();
		private static ArrayList<Integer> exceptionalblockelementFromStopID = new ArrayList<Integer>();
		private static ArrayList<Integer> exceptionalblockelementToStopID = new ArrayList<Integer>();
		private static ArrayList<String> exceptionalblockelementDepTime = new ArrayList<String>();
		private static ArrayList<String> exceptionalblockelementArrTime = new ArrayList<String>();
		private static ArrayList<Integer> exceptionalblockelementElementType = new ArrayList<Integer>();
		private static ArrayList<Integer> exceptionalblockelementServiceJourneyCode = new ArrayList<Integer>();
	
	//Array list of day id
	private static ArrayList<String> blockelementDayID = new ArrayList<String>();

	//*************************
	//*********schedule********
	//*************************
	
	// Array lists of stoppoint
		private static ArrayList<Integer> stopID = new ArrayList<Integer>();
		private static ArrayList<String> stopCode = new ArrayList<String>();
		private static ArrayList<String> stopName = new ArrayList<String>();

		// Array lists of line
		private static ArrayList<Integer> lineID = new ArrayList<Integer>();
		private static ArrayList<String> lineCode = new ArrayList<String>();
		private static ArrayList<String> lineName = new ArrayList<String>();
		
		/**
		 *Array lists of linebundles
		 */
		
		// Array lists of vehicle type
		private static ArrayList<Integer> vehicleTypeID = new ArrayList<Integer>();
		private static ArrayList<String> vehicleTypeCode = new ArrayList<String>();
		private static ArrayList<String> vehicleTypeName = new ArrayList<String>();
		private static ArrayList<Float> vehicleTypeVehCost = new ArrayList<Float>();
		private static ArrayList<Float> vehicleTypeKmCost = new ArrayList<Float>();
		private static ArrayList<Float> vehicleTypeHourCost = new ArrayList<Float>();
		private static ArrayList<Integer> vehicleTypeCapacity = new ArrayList<Integer>();

		// Array lists of vehicle type group
		private static ArrayList<Integer> vehicleTypeGroupID = new ArrayList<Integer>();
		private static ArrayList<String> vehicleTypeGroupCode = new ArrayList<String>();
		private static ArrayList<String> vehicleTypeGroupName = new ArrayList<String>();

		// Array lists of vehicle to vehicle type group
		private static ArrayList<Integer> vehicleToVehicleTypeGroupVehTypeID = new ArrayList<Integer>();
		private static ArrayList<Integer> vehicleToVehicleTypeGroupVehTypeGroupID = new ArrayList<Integer>();

		// Array lists of vehicle capacity to stoppoint
		private static ArrayList<Integer> vehicleCapToStopVehTypeID = new ArrayList<Integer>();
		private static ArrayList<Integer> vehicleCapToStopStoppointID = new ArrayList<Integer>();
		private static ArrayList<Integer> vehicleCapToStopMin = new ArrayList<Integer>();
		private static ArrayList<Integer> vehicleCapToStopMax = new ArrayList<Integer>();

		// Array lists of service journeys
		private static ArrayList<Integer> serviceJourneyID = new ArrayList<Integer>();
		private static ArrayList<Integer> serviceJourneyLineID = new ArrayList<Integer>();
		private static ArrayList<Integer> serviceJourneyFromStopID = new ArrayList<Integer>();
		private static ArrayList<Integer> serviceJourneyToStopID = new ArrayList<Integer>();
		private static ArrayList<String> serviceJourneyDepTime = new ArrayList<String>();
		private static ArrayList<String> serviceJourneyArrTime = new ArrayList<String>();
		private static ArrayList<Integer> serviceJourneyMinAheadTime = new ArrayList<Integer>();
		private static ArrayList<Integer> serviceJourneyMinLayoverTime = new ArrayList<Integer>();
		private static ArrayList<Integer> serviceJourneyVehTypeGroupID = new ArrayList<Integer>();
		private static ArrayList<Integer> serviceJourneyMaxShiftBackwardSeconds = new ArrayList<Integer>();
		private static ArrayList<Integer> serviceJourneyMaxShiftForwardSeconds = new ArrayList<Integer>();
		private static ArrayList<Integer> serviceJourneyFromStopBreakFacility = new ArrayList<Integer>();
		private static ArrayList<Integer> serviceJourneyToStopBreakFacility = new ArrayList<Integer>();
		private static ArrayList<String> serviceJourneyCode = new ArrayList<String>();

		// Array lists of connections (deadruntime)
		private static ArrayList<Integer> deadruntimeFromStopID = new ArrayList<Integer>();
		private static ArrayList<Integer> deadruntimeToStopID = new ArrayList<Integer>();
		private static ArrayList<String> deadruntimeFromTime = new ArrayList<String>();
		private static ArrayList<String> deadruntimeToTime = new ArrayList<String>();
		private static ArrayList<Integer> deadruntimeDistance = new ArrayList<Integer>();
		private static ArrayList<Integer> deadruntimeRuntime = new ArrayList<Integer>();

		// Array lists of reliefpoints
		private static ArrayList<Integer> reliefpointID = new ArrayList<Integer>();
		private static ArrayList<String> reliefpointServiceJourneyID = new ArrayList<String>();
		private static ArrayList<Integer> reliefpointStoppointID = new ArrayList<Integer>();
		private static ArrayList<String> reliefpointStoptime = new ArrayList<String>();
		
		/**
		 *Array lists of transfertimes
		 */
		//Array lists of DayIDs
		private static ArrayList<Integer> dayID = new ArrayList<Integer>();
		private static ArrayList<String> dayName = new ArrayList<String>();
		
		//Array lists of Trips
		private static ArrayList<Integer> tripID = new ArrayList<Integer>();
		private static ArrayList<Integer> dayOne = new ArrayList<Integer>();
		private static ArrayList<Integer> dayTwo = new ArrayList<Integer>();
		private static ArrayList<Integer> dayThree = new ArrayList<Integer>();
		private static ArrayList<Integer> dayFour = new ArrayList<Integer>();
		private static ArrayList<Integer> dayFive = new ArrayList<Integer>();
		private static ArrayList<Integer> daySix = new ArrayList<Integer>();
		private static ArrayList<Integer> daySeven = new ArrayList<Integer>();
		
		//Array lists of duty type (rules) file
		private static ArrayList<String> name = new ArrayList<String>();
		private static ArrayList<String> startTimeMin = new ArrayList<String>();
		private static ArrayList<String> startTimeMax = new ArrayList<String>();
		private static ArrayList<String> endTimeMin = new ArrayList<String>();
		private static ArrayList<String> endTimeMax = new ArrayList<String>();
		private static ArrayList<String> signOnTime = new ArrayList<String>();
		private static ArrayList<String> signOffTime = new ArrayList<String>();
		private static ArrayList<String> durationMin = new ArrayList<String>();
		private static ArrayList<String> durationMax = new ArrayList<String>();
		private static ArrayList<String> workingTimeTotalMin = new ArrayList<String>();
		private static ArrayList<String> workingTimeTotalMax = new ArrayList<String>();
		private static ArrayList<String> workingTimeBeforeBreakMin = new ArrayList<String>();
		private static ArrayList<String> workingTimeWithoutBreakMin = new ArrayList<String>();
		private static ArrayList<String> workingTimeAfterLastBreakMin = new ArrayList<String>();
		private static ArrayList<String> drivingTimeTotalMin = new ArrayList<String>();
		private static ArrayList<String> drivingTimeTotalMax = new ArrayList<String>();
		private static ArrayList<String> drivingTimeWithoutBreakMin = new ArrayList<String>();
		private static ArrayList<String> drivingTimeWithoutBreakMax = new ArrayList<String>();
		private static ArrayList<String> drivingTimeWithoutBreakMinInterruptionTime = new ArrayList<String>();
		private static ArrayList<String> drivingTimeBeforeFirstBreakMin = new ArrayList<String>();
		private static ArrayList<String> breakType = new ArrayList<String>();
		private static ArrayList<String> breakTimeTotalMin = new ArrayList<String>();
		private static ArrayList<String> breakTimeTotalMax = new ArrayList<String>();
		private static ArrayList<String> breakTimeMin = new ArrayList<String>();
		private static ArrayList<String> breakTimeMax = new ArrayList<String>();
		private static ArrayList<Integer> pieceCountMin = new ArrayList<Integer>();
		private static ArrayList<Integer> pieceCountMax = new ArrayList<Integer>();
		private static ArrayList<String> allowedCumulatedWorkingTimeMax = new ArrayList<String>();
		private static ArrayList<Float> dutyFixCost = new ArrayList<Float>();
		private static ArrayList<Integer> isWorkRateConsidered = new ArrayList<Integer>();
		private static ArrayList<Integer> isBreakRateConsidered = new ArrayList<Integer>();
		private static ArrayList<Float> dutyCostPerMinute = new ArrayList<Float>();
		private static ArrayList<Integer> isVehicleChangeAllowedDuringBreak = new ArrayList<Integer>();
		private static ArrayList<String> breakTimeAllowsStarts = new ArrayList<String>();
		private static ArrayList<String> breakTimeAllowsEnds = new ArrayList<String>();
		private static ArrayList<String> workingtimeWithoutBreakMax = new ArrayList<String>();
		
		//dutyrule name
		private String filename=null;
	//correcting string format
	public static ArrayList<String> convertStringToArraylist(String str) {

		// "\\s*,\\s*" anstatt "," damit Leerzeichen vor und nach dem Komma im
		// urspr��nglichen String ignoriert werden.
		Collections.addAll(stringList, str.split("\\s*,\\s*"));

		System.out.println(stringList);
		return stringList;
	}

	// *************************************************
	// ****** Method to read the duty roster data ******
	// ******                                    *******
	// *************************************************

	public void readTxtDienstplan() {

		try {

			//*************************************************************
			//test.txt Data has to be in the project file in your workspace
			//path and filename will have to be changed dynamically
			// All lines with relevant data will be read
			// The data will be split in seperated array lists
			
			File file =new File("resources/quellen/testdienstplan.txt");
			BufferedReader dienstplan = new BufferedReader(new FileReader(file));
			filename=file.getName();
			
			String zeile = null;
			ArrayList<String> zeilenelemente = new ArrayList<String>();
			boolean day=false;

			//loop for all data lines in txt-file
			while ((zeile = dienstplan.readLine()) != null) {
				
				//checking if day relation exists in the txt-file
				if (zeile.startsWith("$DAY")) {
					day = true;
					continue;
				}
				
				//****************************************************************************************************************
				//reading data from day relation by splitting "zeile" and storing the resulting values into array "zeilenelemente"
				if (!zeile.startsWith("*") && !zeile.startsWith("$")) {
					Collections.addAll(zeilenelemente, zeile.split(";"));

					// The data is split in seperate array lists
					if (zeilenelemente.size() == 8) {
						int blockIDZiffer=Integer.parseInt(zeilenelemente.get(1));
						int fromStopIDZiffer=Integer.parseInt(zeilenelemente.get(3));
						int toStopIDZiffer=Integer.parseInt(zeilenelemente.get(4));
						int elementTypeZiffer=Integer.parseInt(zeilenelemente.get(7));
						dutyelementDutyType.add(zeilenelemente.get(0));
						dutyelementBlockID.add(blockIDZiffer);
						
						//distinction between regular journeys and exceptional journeys
						try{
							String zahl=null;
							zahl=zeilenelemente.get(2);
							if(zahl.matches("[0-9]+")){
								dutyelementServiceJourneyID.add(zahl);
							}	
							else dutyelementServiceJourneyID.add(zeilenelemente.get(2));
							}catch(NumberFormatException e){
								e.printStackTrace();
							}
						/**
						 * muss das nicht in die if?
						 */
						dutyelementFromStopID.add(fromStopIDZiffer);
						dutyelementToStopID.add(toStopIDZiffer);
						dutyelementDepTime.add(zeilenelemente.get(5));
						dutyelementArrTime.add(zeilenelemente.get(6));
						dutyelementElementType.add(elementTypeZiffer);
					}

					// ATTENTION: The data can contain 9 elements including ServiceJourneyCode
					if (zeilenelemente.size() == 9) {
						int blockIDZiffer=Integer.parseInt(zeilenelemente.get(1));
						int fromStopIDZiffer=Integer.parseInt(zeilenelemente.get(3));
						int toStopIDZiffer=Integer.parseInt(zeilenelemente.get(4));
						int elementTypeZiffer=Integer.parseInt(zeilenelemente.get(7));
						dutyelementDutyType.add(zeilenelemente.get(0));
						dutyelementBlockID.add(blockIDZiffer);
						dutyelementServiceJourneyID.add(zeilenelemente.get(2));
						dutyelementFromStopID.add(fromStopIDZiffer);
						dutyelementToStopID.add(toStopIDZiffer);
						dutyelementDepTime.add(zeilenelemente.get(5));
						dutyelementArrTime.add(zeilenelemente.get(6));
						dutyelementElementType.add(elementTypeZiffer);
						dutyelementServiceJourneyCode
								.add(zeilenelemente.get(8));
					}
					//day for which the schedule is valid***********
					if (zeilenelemente.size() == 1) {
						dutyelementDayID.add(zeilenelemente.get(0));
						zeilenelemente.clear();
					}
					if (day==false) {
						String dayId="NULL";
						blockelementDayID.add(dayId);
						zeilenelemente.clear();
					}

					// Size of the zeilenelemente array list will be reset
					else
						zeilenelemente.clear();
				}

			}
			dienstplan.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// *************************************************
	// ****** Method to read the tour plan data ********
	// ******                                   ********
	// *************************************************

	public void readTxtUmlaufplan() {

		try {

			// testumlauf.txt Data has to be in the project file in your workspace
			File file =new File("resources/quellen/testumlauf2.txt");
			BufferedReader umlaufplan = new BufferedReader(new FileReader(file));
			filename=file.getName();
			String zeile = null;
			ArrayList<String> zeilenelemente = new ArrayList<String>();
			boolean day=false;
			while ((zeile = umlaufplan.readLine()) != null) {

				// All lines with relevant data will be read
				if (!zeile.startsWith("*") && !zeile.startsWith("$")) {
					Collections.addAll(zeilenelemente, zeile.split(";"));
					
					if (zeile.startsWith("$DAY")) {
						day = true;
						continue;
					}

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
					if (zeilenelemente.size() >= 7) {
						String zahl=null;
						zahl=zeilenelemente.get(1);
						
						//if-clause checks the ServiceJourneyID. If the serviceJourneyID contains letters the dataset will be added to the exceptionalBlockelement table.
						if(zahl.matches("[0-9]+")){
							int blockID=Integer.parseInt(zeilenelemente.get(0));
							int elementType=Integer.parseInt(zeilenelemente.get(6));
							
							blockelementBlockID.add(blockID);
							blockelementServiceJourneyID.add(zeilenelemente.get(1));
							blockelementElementType.add(elementType);
							
						}else{
							int blockID=Integer.parseInt(zeilenelemente.get(0));
							int fromStopID=Integer.parseInt(zeilenelemente.get(2));
							int toStopID=Integer.parseInt(zeilenelemente.get(3));
							int elementType=Integer.parseInt(zeilenelemente.get(6));
							
							blockelementBlockID.add(blockID);
							blockelementServiceJourneyID.add(zeilenelemente.get(1));
							blockelementElementType.add(elementType);
						
							exceptionalblockelementBlockID.add(blockID);
							exceptionalblockelementServiceJourneyID.add(zahl);
							exceptionalblockelementFromStopID.add(fromStopID);
							exceptionalblockelementToStopID.add(toStopID);
							exceptionalblockelementDepTime.add(changeDateFormat(zeilenelemente.get(4)));
							exceptionalblockelementArrTime.add(changeDateFormat(zeilenelemente.get(5)));
						
						zeilenelemente.clear();
					}}


					
					if (zeilenelemente.size() == 1 && day==true) {
						blockelementDayID.add(zeilenelemente.get(0));
						zeilenelemente.clear();
					}
					if (day==false) {
						String dayId="NULL";
						blockelementDayID.add(dayId);
						zeilenelemente.clear();
					}

					// Size of the zeilenelemente array list will be reset
					else
						zeilenelemente.clear();
				}
			}
			umlaufplan.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ***********************************************
	// ****** Method to read the Fahrplan data *******
	// ******                                  *******
	// ***********************************************

	public void readTxtFahrplan() {

		try {

			// testfahrplan.txt Data has to be in the project file in your
			// workspace
			File file =new File("resources/quellen/testfahrplan2.txt");
			BufferedReader fahrplan = new BufferedReader(new FileReader(file));
			filename=file.getName();
			String zeile = null;
			ArrayList<String> zeilenelemente = new ArrayList<String>();

			// will be true if the specific section in the txt data was read
			boolean stoppoint = false;
			boolean vehicleTypeGroup = false;
			boolean line = false;
			boolean vehicleCapToStop = false;
			boolean vehTypeToVehTypeGroup =false;
			boolean reliefpoint = false;
			boolean days = false;
			boolean deadruntime = false;

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
				
				if (zeile.startsWith("$VEHTYPETOVEHTYPEGROUP")) {
					stoppoint = false;
					line = false;
					vehicleTypeGroup = false;
					vehTypeToVehTypeGroup = true;
					continue;
				}
				
				if (zeile.startsWith("$VEHTYPECAPTOSTOPPOINT")) {
					stoppoint = false;
					line = false;
					vehicleTypeGroup = false;
					vehTypeToVehTypeGroup = false;
					vehicleCapToStop = true;
					continue;
				}
				if (zeile.startsWith("$RELIEFPOINT")) {
					stoppoint = false;
					line = false;
					vehicleTypeGroup = false;
					vehTypeToVehTypeGroup = false;
					vehicleCapToStop = false;
					reliefpoint = true;
					continue;
				}
				
				if (zeile.startsWith("$DEADRUNTIME")) {
					stoppoint = false;
					line = false;
					vehicleTypeGroup = false;
					vehTypeToVehTypeGroup = false;
					vehicleCapToStop = false;
					reliefpoint =false;
					deadruntime=true;
					continue;
				}
				
				if (zeile.startsWith("$DAYS")) {
					stoppoint = false;
					line = false;
					vehicleTypeGroup = false;
					vehTypeToVehTypeGroup = false;
					vehicleCapToStop = false;
					reliefpoint = false;
					deadruntime=false;
					days = true;
					continue;
				}

				// All lines with relevant data will be read
				if (!zeile.startsWith("*") && !zeile.startsWith("$")) {
					Collections.addAll(zeilenelemente, zeile.split(";"));

					// The stoppoint data will be split in separated array
					// lists
					if (zeilenelemente.size() == 3 && stoppoint == true) {
						int stopIDZiffer=Integer.parseInt(zeilenelemente.get(0));
						stopID.add(stopIDZiffer);
						stopCode.add(zeilenelemente.get(1));
						stopName.add(zeilenelemente.get(2));
						zeilenelemente.clear();
					}

					// The lines data will be split in separated array
					// lists
					if (zeilenelemente.size() == 3 && line == true) {
						int lineIDZiffer=Integer.parseInt(zeilenelemente.get(0));
						lineID.add(lineIDZiffer);
						lineCode.add(zeilenelemente.get(1));
						lineName.add(zeilenelemente.get(2));
						zeilenelemente.clear();
					}

					// The vehicle types data will be split in separated array
					// lists
					if (zeilenelemente.size() == 7) {
						int vehTypeIDZiffer=Integer.parseInt(zeilenelemente.get(0));
						float vehTypeVehCost=Float.parseFloat(zeilenelemente.get(3));
						float vehTypeKmCostZiffer=Float.parseFloat(zeilenelemente.get(4));
						float vehTypeHourCostZiffer=Float.parseFloat(zeilenelemente.get(5));
						int vehicleTypeCapacityZiffer=Integer.parseInt(zeilenelemente.get(6));

						vehicleTypeID.add(vehTypeIDZiffer);
						vehicleTypeCode.add(zeilenelemente.get(1));
						vehicleTypeName.add(zeilenelemente.get(2));
						vehicleTypeVehCost.add(vehTypeVehCost);
						vehicleTypeKmCost.add(vehTypeKmCostZiffer);
						vehicleTypeHourCost.add(vehTypeHourCostZiffer);
						vehicleTypeCapacity.add(vehicleTypeCapacityZiffer);
						zeilenelemente.clear();
					}

					// The vehicle type group data will be split in separated
					// array
					// lists
					if (zeilenelemente.size() == 3 && vehicleTypeGroup == true) {
						int vehTypeGroupIDZiffer=Integer.parseInt(zeilenelemente.get(0));
						vehicleTypeGroupID.add(vehTypeGroupIDZiffer);
						vehicleTypeGroupCode.add(zeilenelemente.get(1));
						vehicleTypeGroupName.add(zeilenelemente.get(2));
						zeilenelemente.clear();
					}

					// The vehicle to vehicle type group data will be split in
					// separated array
					// lists
					if (zeilenelemente.size() == 2 && vehTypeToVehTypeGroup) {
						int vehicleToVehicleTypeGroupVehTypeIDZiffer=Integer.parseInt(zeilenelemente.get(0));
						int vehicleToVehicleTypeGroupVehTypeGroupIDZiffer=Integer.parseInt(zeilenelemente.get(1));
						vehicleToVehicleTypeGroupVehTypeID.add(vehicleToVehicleTypeGroupVehTypeIDZiffer);
						vehicleToVehicleTypeGroupVehTypeGroupID
								.add(vehicleToVehicleTypeGroupVehTypeGroupIDZiffer);
						zeilenelemente.clear();
					}

					// The vehicle capacities to stoppoints data will be split
					// in separated array
					// lists
					if (zeilenelemente.size() == 4 && vehicleCapToStop == true) {
						int vehicleCapToStopVehTypeIDZiffer=Integer.parseInt(zeilenelemente.get(0));
						int vehicleCapToStopStoppointIDZiffer=Integer.parseInt(zeilenelemente.get(1));
						int vehicleCapToStopMinZiffer=Integer.parseInt(zeilenelemente.get(2));
						int vehicleCapToStopMaxZiffer=Integer.parseInt(zeilenelemente.get(3));

						vehicleCapToStopVehTypeID.add(vehicleCapToStopVehTypeIDZiffer);
						vehicleCapToStopStoppointID.add(vehicleCapToStopStoppointIDZiffer);
						vehicleCapToStopMin.add(vehicleCapToStopMinZiffer);
						vehicleCapToStopMax.add(vehicleCapToStopMaxZiffer);
						zeilenelemente.clear();
					}

					// The service journey data will be split in separated array
					// lists
					if (zeilenelemente.size() == 14) {
						int serviceJourneyIDZiffer=Integer.parseInt(zeilenelemente.get(0));
						int serviceJourneyLineIDZiffer=Integer.parseInt(zeilenelemente.get(1));
						int serviceJourneyFromStopIDZiffer=Integer.parseInt(zeilenelemente.get(2));
						int serviceJourneyToStopIDZiffer=Integer.parseInt(zeilenelemente.get(3));
						int serviceJourneyMinAheadTimeZiffer=Integer.parseInt(zeilenelemente.get(6));
						int serviceJourneyMinLayoverTimeZiffer=Integer.parseInt(zeilenelemente.get(7));
						int serviceJourneyVehTypeGroupIDZiffer=Integer.parseInt(zeilenelemente.get(8));
						int serviceJourneyMaxShiftBackwardSecondsZiffer=Integer.parseInt(zeilenelemente.get(9));
						int serviceJourneyMaxShiftForwardSecondsZiffer=Integer.parseInt(zeilenelemente.get(10));
						int serviceJourneyFromStopBreakFacilityZiffer=Integer.parseInt(zeilenelemente.get(11));
						int serviceJourneyToStopBreakFacilityZiffer=Integer.parseInt(zeilenelemente.get(12));

						serviceJourneyID.add(serviceJourneyIDZiffer);
						serviceJourneyLineID.add(serviceJourneyLineIDZiffer);
						serviceJourneyFromStopID.add(serviceJourneyFromStopIDZiffer);
						serviceJourneyToStopID.add(serviceJourneyToStopIDZiffer);
						serviceJourneyDepTime.add(changeDateFormat(zeilenelemente.get(4)));
						serviceJourneyArrTime.add(changeDateFormat(zeilenelemente.get(5)));
						serviceJourneyMinAheadTime.add(serviceJourneyMinAheadTimeZiffer);
						serviceJourneyMinLayoverTime.add(serviceJourneyMinLayoverTimeZiffer);
						serviceJourneyVehTypeGroupID.add(serviceJourneyVehTypeGroupIDZiffer);
						serviceJourneyMaxShiftBackwardSeconds
								.add(serviceJourneyMaxShiftBackwardSecondsZiffer);
						serviceJourneyMaxShiftForwardSeconds.add(
								serviceJourneyMaxShiftForwardSecondsZiffer);
						serviceJourneyFromStopBreakFacility.add(
								serviceJourneyFromStopBreakFacilityZiffer);
						serviceJourneyToStopBreakFacility.add(
								serviceJourneyToStopBreakFacilityZiffer);
						serviceJourneyCode.add(zeilenelemente.get(13));
						zeilenelemente.clear();
					}

					// The dead runtime data will be split in separated array
					// lists
					if (zeilenelemente.size() == 6 && deadruntime == true) {
						int deadruntimeFromStopIDZiffer=Integer.parseInt(zeilenelemente.get(0));
						int deadruntimeToStopIDZiffer=Integer.parseInt(zeilenelemente.get(1));
						int deadruntimeDistanceZiffer=Integer.parseInt(zeilenelemente.get(4));
						int deadruntimeRuntimeZiffer=Integer.parseInt(zeilenelemente.get(5));

						
						deadruntimeFromStopID.add(deadruntimeFromStopIDZiffer);
						deadruntimeToStopID.add(deadruntimeToStopIDZiffer);
						deadruntimeFromTime.add(zeilenelemente.get(2));
						deadruntimeToTime.add(zeilenelemente.get(3));
						deadruntimeDistance.add(deadruntimeDistanceZiffer);
						deadruntimeRuntime.add(deadruntimeRuntimeZiffer);
						zeilenelemente.clear();
					}

					// The reliefpoint data will be split in separated array
					// lists
					if (zeilenelemente.size() == 4 && reliefpoint == true) {
						int reliefpointIDZiffer=Integer.parseInt(zeilenelemente.get(0));
						int reliefpointStoppointIDZiffer=Integer.parseInt(zeilenelemente.get(0));

						reliefpointID.add(reliefpointIDZiffer);
						reliefpointServiceJourneyID.add(zeilenelemente.get(1));
						reliefpointStoppointID.add(reliefpointStoppointIDZiffer);
						reliefpointStoptime.add(zeilenelemente.get(3));
						zeilenelemente.clear();
					}
					
					if (zeilenelemente.size() == 2) {
					int dayZahl=Integer.parseInt(zeilenelemente.get(0));
					dayID.add(dayZahl);
					dayName.add(zeilenelemente.get(1));
					zeilenelemente.clear();
					}
					
					if (zeilenelemente.size() == 6 && days == true) {
						int tripIDZiffer=Integer.parseInt(zeilenelemente.get(0));
						int dayOneZiffer=Integer.parseInt(zeilenelemente.get(1));
						int dayTwoZiffer=Integer.parseInt(zeilenelemente.get(2));
						int dayThreeZiffer=Integer.parseInt(zeilenelemente.get(3));
						int dayFourZiffer=Integer.parseInt(zeilenelemente.get(4));
						int dayFiveZiffer=Integer.parseInt(zeilenelemente.get(5));
						
						tripID.add(tripIDZiffer);
						dayOne.add(dayOneZiffer);
						dayTwo.add(dayTwoZiffer);
						dayThree.add(dayThreeZiffer);
						dayFour.add(dayFourZiffer);
						dayFive.add(dayFiveZiffer);
						zeilenelemente.clear();
					}
					
					if (zeilenelemente.size() == 7 && days == true) {
						int tripIDZiffer=Integer.parseInt(zeilenelemente.get(0));
						int dayOneZiffer=Integer.parseInt(zeilenelemente.get(1));
						int dayTwoZiffer=Integer.parseInt(zeilenelemente.get(2));
						int dayThreeZiffer=Integer.parseInt(zeilenelemente.get(3));
						int dayFourZiffer=Integer.parseInt(zeilenelemente.get(4));
						int dayFiveZiffer=Integer.parseInt(zeilenelemente.get(5));
						int daySixZiffer=Integer.parseInt(zeilenelemente.get(6));
						
						tripID.add(tripIDZiffer);
						dayOne.add(dayOneZiffer);
						dayTwo.add(dayTwoZiffer);
						dayThree.add(dayThreeZiffer);
						dayFour.add(dayFourZiffer);
						dayFive.add(dayFiveZiffer);
						daySix.add(daySixZiffer);
						zeilenelemente.clear();
					}
					
					if (zeilenelemente.size() == 8 && days == true) {
						int tripIDZiffer=Integer.parseInt(zeilenelemente.get(0));
						int dayOneZiffer=Integer.parseInt(zeilenelemente.get(1));
						int dayTwoZiffer=Integer.parseInt(zeilenelemente.get(2));
						int dayThreeZiffer=Integer.parseInt(zeilenelemente.get(3));
						int dayFourZiffer=Integer.parseInt(zeilenelemente.get(4));
						int dayFiveZiffer=Integer.parseInt(zeilenelemente.get(5));
						int daySixZiffer=Integer.parseInt(zeilenelemente.get(6));
						int daySevenZiffer=Integer.parseInt(zeilenelemente.get(7));
						
						tripID.add(tripIDZiffer);
						dayOne.add(dayOneZiffer);
						dayTwo.add(dayTwoZiffer);
						dayThree.add(dayThreeZiffer);
						dayFour.add(dayFourZiffer);
						dayFive.add(dayFiveZiffer);
						daySix.add(daySixZiffer);
						daySeven.add(daySevenZiffer);
						zeilenelemente.clear();
					}

				}

			}
			fahrplan.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// ***********************************************
	// ****** Method to read the duty type data *******
	// ******                                  *******
	// ***********************************************
	
	public void readTxtDiensttypen(){
		
		try {

			// testfahrplan.txt Data has to be in the project file in your
			// workspace
			BufferedReader diensttypen = new BufferedReader(new FileReader(
					"resources/quellen/testdiensttypen.txt"));
			String zeile = null;
			ArrayList<String> zeilenelemente = new ArrayList<String>();
			while((zeile=diensttypen.readLine())!=null){
			// All lines with relevant data will be read
			if (!zeile.startsWith("*") && !zeile.startsWith("$")) {
				Collections.addAll(zeilenelemente, zeile.split(";"));
			
				if(zeilenelemente.size()==35){
					
					int pieceCountMinZiffer=Integer.parseInt(zeilenelemente.get(25));
					int pieceCountMaxZiffer=Integer.parseInt(zeilenelemente.get(26));
					int isWorkRateConsideredZiffer = Integer.parseInt(zeilenelemente.get(29));
					int isBreakRateConsideredZiffer=Integer.parseInt(zeilenelemente.get(30));
					int isVehicleChangeAllowedDuringBreakZiffer=Integer.parseInt(zeilenelemente.get(32));
					
					float dutyFixCostZiffer=Float.parseFloat(zeilenelemente.get(28));
					float dutyCostPerMinuteZiffer=Float.parseFloat(zeilenelemente.get(31));
					
					name.add(zeilenelemente.get(0));
					startTimeMin.add(zeilenelemente.get(1));
					startTimeMax.add(zeilenelemente.get(2));
					endTimeMin.add(zeilenelemente.get(3));
					endTimeMax.add(zeilenelemente.get(4));
					signOnTime.add(zeilenelemente.get(5));
					signOffTime.add(zeilenelemente.get(6));
					durationMin.add(zeilenelemente.get(7));
					durationMax.add(zeilenelemente.get(8));
					workingTimeTotalMin.add(zeilenelemente.get(9));
					workingTimeTotalMax.add(zeilenelemente.get(10));
					workingTimeBeforeBreakMin.add(zeilenelemente.get(11));
					workingTimeWithoutBreakMin.add(zeilenelemente.get(12));
					workingTimeAfterLastBreakMin.add(zeilenelemente.get(13));
					drivingTimeTotalMin.add(zeilenelemente.get(14));
					drivingTimeTotalMax.add(zeilenelemente.get(15));
					drivingTimeWithoutBreakMin.add(zeilenelemente.get(16));
					drivingTimeWithoutBreakMax.add(zeilenelemente.get(17));
					drivingTimeWithoutBreakMinInterruptionTime.add(zeilenelemente.get(18));
					drivingTimeBeforeFirstBreakMin.add(zeilenelemente.get(19));
					breakType.add(zeilenelemente.get(20));
					breakTimeTotalMin.add(zeilenelemente.get(21));
					breakTimeTotalMax.add(zeilenelemente.get(22));
					breakTimeMin.add(zeilenelemente.get(23));
					breakTimeMax.add(zeilenelemente.get(24));
					pieceCountMin.add(pieceCountMinZiffer);
					pieceCountMax.add(pieceCountMaxZiffer);
					allowedCumulatedWorkingTimeMax.add(zeilenelemente.get(27));
					dutyFixCost.add(dutyFixCostZiffer);
					isWorkRateConsidered.add(isWorkRateConsideredZiffer);
					isBreakRateConsidered.add(isBreakRateConsideredZiffer);
					dutyCostPerMinute.add(dutyCostPerMinuteZiffer);
					isVehicleChangeAllowedDuringBreak.add(isVehicleChangeAllowedDuringBreakZiffer);
					breakTimeAllowsStarts.add(zeilenelemente.get(33));
					breakTimeAllowsEnds.add(zeilenelemente.get(34));
					//workingtimeWithoutBreakMax.add(zeilenelemente.get(35));
					zeilenelemente.clear();
				}
			}
			}
			diensttypen.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	
	// ************************************************
	// ****** Getter Methods of the array lists *******
	// ******                                   *******
	// ************************************************


	public  ArrayList<String> getStringList() {
		return stringList;
	}

	public ArrayList<String> getDutyDutyID() {
		return dutyDutyID;
	}

	public  ArrayList<String> getDutyDutyType() {
		return dutyDutyType;
	}

	public  ArrayList<String> getDutyelementDutyType() {
		return dutyelementDutyType;
	}

	public ArrayList<Integer> getDutyelementBlockID() {
		return dutyelementBlockID;
	}

	public ArrayList<String> getDutyelementServiceJourneyID() {
		return dutyelementServiceJourneyID;
	}

	public  ArrayList<Integer> getDutyelementFromStopID() {
		return dutyelementFromStopID;
	}

	public  ArrayList<Integer> getDutyelementToStopID() {
		return dutyelementToStopID;
	}

	public  ArrayList<String> getDutyelementDepTime() {
		return dutyelementDepTime;
	}

	public  ArrayList<String> getDutyelementArrTime() {
		return dutyelementArrTime;
	}

	public ArrayList<Integer> getDutyelementElementType() {
		return dutyelementElementType;
	}

	public  ArrayList<String> getDutyelementServiceJourneyCode() {
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

	public ArrayList<Integer> getStopID() {
		return stopID;
	}

	public ArrayList<String> getStopCode() {
		return stopCode;
	}

	public ArrayList<String> getStopName() {
		return stopName;
	}

	public ArrayList<Integer> getLineID() {
		return lineID;
	}

	public ArrayList<String> getLineCode() {
		return lineCode;
	}

	public ArrayList<String> getLineName() {
		return lineName;
	}

	public ArrayList<Integer> getVehicleTypeID() {
		return vehicleTypeID;
	}

	public ArrayList<String> getVehicleTypeCode() {
		return vehicleTypeCode;
	}

	public ArrayList<String> getVehicleTypeName() {
		return vehicleTypeName;
	}

	public ArrayList<Float> getVehicleTypeVehCost() {
		return vehicleTypeVehCost;
	}

	public ArrayList<Float> getVehicleTypeKmCost() {
		return vehicleTypeKmCost;
	}

	public ArrayList<Float> getVehicleTypeHourCost() {
		return vehicleTypeHourCost;
	}

	public  ArrayList<Integer> getVehicleTypeCapacity() {
		return vehicleTypeCapacity;
	}

	public  ArrayList<Integer> getVehicleTypeGroupID() {
		return vehicleTypeGroupID;
	}

	public  ArrayList<String> getVehicleTypeGroupCode() {
		return vehicleTypeGroupCode;
	}

	public  ArrayList<String> getVehicleTypeGroupName() {
		return vehicleTypeGroupName;
	}

	public  ArrayList<Integer> getVehicleToVehicleTypeGroupVehTypeID() {
		return vehicleToVehicleTypeGroupVehTypeID;
	}

	public  ArrayList<Integer> getVehicleToVehicleTypeGroupVehTypeGroupID() {
		return vehicleToVehicleTypeGroupVehTypeGroupID;
	}

	public  ArrayList<Integer> getVehicleCapToStopVehTypeID() {
		return vehicleCapToStopVehTypeID;
	}

	public  ArrayList<Integer> getVehicleCapToStopStoppointID() {
		return vehicleCapToStopStoppointID;
	}

	public  ArrayList<Integer> getVehicleCapToStopMin() {
		return vehicleCapToStopMin;
	}

	public  ArrayList<Integer> getVehicleCapToStopMax() {
		return vehicleCapToStopMax;
	}

	public  ArrayList<Integer> getServiceJourneyID() {
		return serviceJourneyID;
	}

	public  ArrayList<Integer> getServiceJourneyLineID() {
		return serviceJourneyLineID;
	}

	public  ArrayList<Integer> getServiceJourneyFromStopID() {
		return serviceJourneyFromStopID;
	}

	public  ArrayList<Integer> getServiceJourneyToStopID() {
		return serviceJourneyToStopID;
	}

	public  ArrayList<String> getServiceJourneyDepTime() {
		return serviceJourneyDepTime;
	}

	public  ArrayList<String> getServiceJourneyArrTime() {
		return serviceJourneyArrTime;
	}

	public  ArrayList<Integer> getServiceJourneyMinAheadTime() {
		return serviceJourneyMinAheadTime;
	}

	public  ArrayList<Integer> getServiceJourneyMinLayoverTime() {
		return serviceJourneyMinLayoverTime;
	}

	public  ArrayList<Integer> getServiceJourneyVehTypeGroupID() {
		return serviceJourneyVehTypeGroupID;
	}

	public  ArrayList<Integer> getServiceJourneyMaxShiftBackwardSeconds() {
		return serviceJourneyMaxShiftBackwardSeconds;
	}

	public  ArrayList<Integer> getServiceJourneyMaxShiftForwardSeconds() {
		return serviceJourneyMaxShiftForwardSeconds;
	}

	public  ArrayList<Integer> getServiceJourneyFromStopBreakFacility() {
		return serviceJourneyFromStopBreakFacility;
	}

	public  ArrayList<Integer> getServiceJourneyToStopBreakFacility() {
		return serviceJourneyToStopBreakFacility;
	}

	public  ArrayList<String> getServiceJourneyCode() {
		return serviceJourneyCode;
	}

	public ArrayList<Integer> getDeadruntimeFromStopID() {
		return deadruntimeFromStopID;
	}

	public  ArrayList<Integer> getDeadruntimeToStopID() {
		return deadruntimeToStopID;
	}

	public  ArrayList<String> getDeadruntimeFromTime() {
		return deadruntimeFromTime;
	}

	public  ArrayList<String> getDeadruntimeToTime() {
		return deadruntimeToTime;
	}

	public  ArrayList<Integer> getDeadruntimeDistance() {
		return deadruntimeDistance;
	}

	public  ArrayList<Integer> getDeadruntimeRuntime() {
		return deadruntimeRuntime;
	}

	public  ArrayList<Integer> getReliefpointID() {
		return reliefpointID;
	}

	public  ArrayList<String> getReliefpointServiceJourneyID() {
		return reliefpointServiceJourneyID;
	}

	public  ArrayList<Integer> getReliefpointStoppointID() {
		return reliefpointStoppointID;
	}

	public  ArrayList<String> getReliefpointStoptime() {
		return reliefpointStoptime;
	}

	public  ArrayList<String> getName() {
		return name;
	}

	public  ArrayList<String> getStartTimeMin() {
		return startTimeMin;
	}

	public  ArrayList<String> getStartTimeMax() {
		return startTimeMax;
	}

	public  ArrayList<String> getEndTimeMin() {
		return endTimeMin;
	}

	public  ArrayList<String> getEndTimeMax() {
		return endTimeMax;
	}

	public  ArrayList<String> getSignOnTime() {
		return signOnTime;
	}

	public  ArrayList<String> getSignOffTime() {
		return signOffTime;
	}

	public  ArrayList<String> getDurationMin() {
		return durationMin;
	}

	public  ArrayList<String> getDurationMax() {
		return durationMax;
	}

	public  ArrayList<String> getWorkingTimeTotalMin() {
		return workingTimeTotalMin;
	}

	public  ArrayList<String> getWorkingTimeTotalMax() {
		return workingTimeTotalMax;
	}

	public  ArrayList<String> getWorkingTimeBeforeBreakMin() {
		return workingTimeBeforeBreakMin;
	}

	public  ArrayList<String> getWorkingTimeWithoutBreakMin() {
		return workingTimeWithoutBreakMin;
	}

	public ArrayList<String> getWorkingTimeAfterLastBreakMin() {
		return workingTimeAfterLastBreakMin;
	}

	public  ArrayList<String> getDrivingTimeTotalMin() {
		return drivingTimeTotalMin;
	}

	public  ArrayList<String> getDrivingTimeTotalMax() {
		return drivingTimeTotalMax;
	}

	public  ArrayList<String> getDrivingTimeWithoutBreakMin() {
		return drivingTimeWithoutBreakMin;
	}

	public  ArrayList<String> getDrivingTimeWithoutBreakMax() {
		return drivingTimeWithoutBreakMax;
	}

	public  ArrayList<String> getDrivingTimeWithoutBreakMinInterruptionTime() {
		return drivingTimeWithoutBreakMinInterruptionTime;
	}

	public  ArrayList<String> getDrivingTimeBeforeFirstBreakMin() {
		return drivingTimeBeforeFirstBreakMin;
	}

	public  ArrayList<String> getBreakType() {
		return breakType;
	}

	public  ArrayList<String> getBreakTimeTotalMin() {
		return breakTimeTotalMin;
	}

	public  ArrayList<String> getBreakTimeTotalMax() {
		return breakTimeTotalMax;
	}

	public  ArrayList<String> getBreakTimeMin() {
		return breakTimeMin;
	}

	public  ArrayList<String> getBreakTimeMax() {
		return breakTimeMax;
	}

	public  ArrayList<Integer> getPieceCountMin() {
		return pieceCountMin;
	}

	public  ArrayList<Integer> getPieceCountMax() {
		return pieceCountMax;
	}

	public  ArrayList<String> getAllowedCumulatedWorkingTimeMax() {
		return allowedCumulatedWorkingTimeMax;
	}

	public  ArrayList<Float> getDutyFixCost() {
		return dutyFixCost;
	}

	public  ArrayList<Integer> getIsWorkRateConsidered() {
		return isWorkRateConsidered;
	}

	public  ArrayList<Integer> getIsBreakRateConsidered() {
		return isBreakRateConsidered;
	}

	public  ArrayList<Float> getDutyCostPerMinute() {
		return dutyCostPerMinute;
	}

	public  ArrayList<Integer> getIsVehicleChangeAllowedDuringBreak() {
		return isVehicleChangeAllowedDuringBreak;
	}

	public  ArrayList<String> getBreakTimeAllowsStarts() {
		return breakTimeAllowsStarts;
	}

	public  ArrayList<String> getBreakTimeAllowsEnds() {
		return breakTimeAllowsEnds;
	}

	public  ArrayList<String> getWorkingtimeWithoutBreakMax() {
		return workingtimeWithoutBreakMax;
	}

	public ArrayList<Integer> getDayID() {
		return dayID;
	}

	public  ArrayList<String> getDayName() {
		return dayName;
	}

	public  ArrayList<Integer> getTripID() {
		return tripID;
	}

	public  ArrayList<Integer> getDayOne() {
		return dayOne;
	}

	public  ArrayList<Integer> getDayTwo() {
		return dayTwo;
	}

	public  ArrayList<Integer> getDayThree() {
		return dayThree;
	}

	public  ArrayList<Integer> getDayFour() {
		return dayFour;
	}

	public  ArrayList<Integer> getDayFive() {
		return dayFive;
	}

	public  ArrayList<Integer> getDaySix() {
		return daySix;
	}

	public  ArrayList<Integer> getDaySeven() {
		return daySeven;
	}

	public  ArrayList<String> getDutyelementDayID() {
		return dutyelementDayID;
	}

	public  ArrayList<String> getBlockelementDayID() {
		return blockelementDayID;
	}

	public String getFilename() {
		return filename;
	}

	public  ArrayList<Integer> getExceptionalblockelementBlockID() {
		return exceptionalblockelementBlockID;
	}

	public  ArrayList<String> getExceptionalblockelementServiceJourneyID() {
		return exceptionalblockelementServiceJourneyID;
	}

	public  ArrayList<Integer> getExceptionalblockelementFromStopID() {
		return exceptionalblockelementFromStopID;
	}

	public  ArrayList<Integer> getExceptionalblockelementToStopID() {
		return exceptionalblockelementToStopID;
	}

	public  ArrayList<String> getExceptionalblockelementDepTime() {
		return exceptionalblockelementDepTime;
	}

	public  ArrayList<String> getExceptionalblockelementArrTime() {
		return exceptionalblockelementArrTime;
	}

	public  ArrayList<Integer> getExceptionalblockelementElementType() {
		return exceptionalblockelementElementType;
	}

	public  ArrayList<Integer> getExceptionalblockelementServiceJourneyCode() {
		return exceptionalblockelementServiceJourneyCode;
	}
	
	
	public String changeDateFormat(String date){
		String []s1;
		 
		s1=date.split(":");
		//Creates a new date contains hours and minutes
		String newDate=""+s1[1]+":"+s1[2];
		   return newDate;
	}
	
	public int[] intParse(String date){
		
		String [] s1;
		int [] zeit= new int[2];
		s1=date.split(":");
		int hour=Integer.parseInt(s1[0]);
		int minute=Integer.parseInt(s1[1]);
		//Element on position 0 is the hour of the given time
		zeit[0]=hour;
		//Element on position 1 is the minute of the given time
		zeit[1]=minute;
		
		return zeit;
		
	}
	
	public void berechneZeit(String date1, String date2){
		
		int hourDate1=0;
		int minDate1=0;
		int hourDate2;
		int minDate2;
		int hourDuration;
		int minDuration;
		
		String []s1;
		s1=date1.split(":");
		hourDate1=Integer.parseInt(s1[0]);
		minDate1=Integer.parseInt(s1[1]);
		
		String []s2;
		s2=date2.split(":");
		hourDate2=Integer.parseInt(s2[0]);
		minDate2=Integer.parseInt(s2[1]);
		
		if(hourDate1<=hourDate2){
			hourDuration=hourDate2-hourDate1;
			minDuration=minDate2-minDate1;
		}else if(hourDate1>hourDate2){
			hourDuration=(24-hourDate1)+hourDate2;
			
		}
		
	}
	
}

