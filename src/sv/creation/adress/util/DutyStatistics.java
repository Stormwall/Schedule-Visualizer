package sv.creation.adress.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sv.creation.adress.model.Dienstplan;

public class DutyStatistics{
	int dutyHilfsID;
	double dutytotalNumberOfTrips = 0;
	double dutytotalRunTime = 0;
	double dutynumberOfServiceTrips = 0;
	double dutyaverageLengthOfServiceTrips = 0;
	double dutynumberOfDeadheads = 0;
	double dutyaverageLengthOfDeadheads = 0;
	double dutynumberOfWaitings = 0;
	double dutyaverageLengthOfWaitings = 0;
	double dutynumberOfPullOuts = 0;
	double dutyaverageLengthOfPullOuts = 0;
	double dutynumberOfPullIns = 0;
	double dutyaverageLengthOfPullIns = 0;
	
	//int dutynumberOfLines = 0;
	double dutynumberOfVehicles = 0;
	
	double dutynumberOfPreparations = 0;
	double dutyaverageLengthOfPreparations = 0;
	double dutynumberOfWrapUps = 0;
	double dutyaverageLengthOfWrapUps = 0;
	double dutynumberOfLayovers = 0;
	double dutyaverageLengthOfLayovers = 0;
	double dutynumberOfTransfers = 0;
	double dutyaverageLengthOfTransfers = 0;
	double dutynumberOfBreaks = 0;
	double dutyaverageLengthOfBreaks = 0;
	
	double dutyserviceTimetotalDutyTimeRatio = 0;
	
	double dutyoveralldurationServicetrips = 0;
	double dutyoveralldurationDeadheads = 0;
	double dutyoveralldurationWaitings = 0;
	double dutyoveralldurationPullouts = 0;
	double dutyoveralldurationPullins = 0;
	
	double dutyoveralldurationPreparations = 0;
	double dutyoveralldurationWrapUps = 0;
	double dutyoveralldurationLayovers = 0;
	double dutyoveralldurationTransfers = 0;
	double dutyoveralldurationBreaks = 0;
	/****************************************************************************
	method for calculating statistics for all duties contained in a crew schedule
	*****************************************************************************/
	public ArrayList<DutyStatistics> calculateDutystatistics(Dienstplan dp){

		
		ArrayList<DutyStatistics> dutystatlist = new ArrayList<DutyStatistics>();
		for (int duty = 0; duty < dp.getDuty().size(); duty++){
			
			
			String dutyiD = dp.getDuty().get(duty).getId();
			int hilfsID = dp.getDuty().get(duty).getHilfsID();
			ArrayList<Integer> dutyblockID = new ArrayList<Integer>();
			DutyStatistics dutystat = new DutyStatistics();
			for (int dutyelement = 0; dutyelement < dp.getDutyelement().size(); dutyelement++){
							
					if(dp.getDutyelement().get(dutyelement).getDutyID().equals(dutyiD)){
						
						dutyblockID.add(dp.getDutyelement().get(dutyelement).getBlockID());
						
						long runtime = dutystat.calculateDriveTime(dp.getDutyelement().get(dutyelement).getDepTime(), dp.getDutyelement().get(dutyelement).getArrTime());
						dutystat.dutyHilfsID=hilfsID;	
						dutystat.dutytotalNumberOfTrips++;
						dutystat.dutytotalRunTime = dutystat.dutytotalRunTime + runtime;

						
						switch (dp.getDutyelement().get(dutyelement).getElementType()){
						case 1:
							//Service Trip
							dutystat.dutynumberOfServiceTrips++;
							dutystat.dutyoveralldurationServicetrips = dutystat.dutyoveralldurationServicetrips + runtime;
							break;
						case 2:
							//Deadheading
							dutystat.dutynumberOfDeadheads++;
							dutystat.dutyoveralldurationDeadheads = dutystat.dutyoveralldurationDeadheads + runtime;
							break;
						case 3:
							//Pullins
							dutystat.dutynumberOfPullIns++;
							dutystat.dutyoveralldurationPullins = dutystat.dutyoveralldurationPullins + runtime;
							break;
						case 4:
							//Pullout
							dutystat.dutynumberOfPullOuts++;
							dutystat.dutyoveralldurationPullouts = dutystat.dutyoveralldurationPullouts + runtime;
							break;
						case 5:
							//Preparation
							dutystat.dutynumberOfPreparations++;
							dutystat.dutyoveralldurationPreparations = dutystat.dutyoveralldurationPreparations + runtime;
							break;
						case 6:
							//Wrap-Ups
							dutystat.dutynumberOfWrapUps++;
							dutystat.dutyoveralldurationWrapUps = dutystat.dutyoveralldurationWrapUps + runtime;
							break;
						case 7:
							//Transfer
							dutystat.dutynumberOfTransfers++;
							dutystat.dutyoveralldurationTransfers = dutystat.dutyoveralldurationTransfers + runtime;
							break;
						case 8:
							//Break
							dutystat.dutynumberOfBreaks++;
							dutystat.dutyoveralldurationBreaks = dutystat.dutyoveralldurationBreaks + runtime;
							break;
						case 9:
							//Waiting
							dutystat.dutynumberOfWaitings++;
							dutystat.dutyoveralldurationWaitings = dutystat.dutyoveralldurationWaitings + runtime;
							break;
						case 10:
							//Layover
							dutystat.dutynumberOfLayovers++;
							dutystat.dutyoveralldurationLayovers = dutystat.dutyoveralldurationLayovers + runtime;
							break;
						}
						//TODO
						//Vehicles per Duty
						int countvehicles = 0;
						for (int i=0; i < dutyblockID.size();i++){
							
							if(dutyblockID.get(i)>0){
								if(dutyblockID.get(i)!=dutyblockID.get(i-1)){
									countvehicles++;
								}
							}
						}
						dutystat.dutynumberOfVehicles = countvehicles;
				}//if
					
				
				
				
			}
			//Elementtypes
			if (dutystat.dutynumberOfServiceTrips != 0) {
				dutystat.dutyaverageLengthOfServiceTrips = dutystat.dutyoveralldurationServicetrips
						/ dutystat.dutynumberOfServiceTrips;
			} else {
				dutystat.dutyaverageLengthOfServiceTrips = 0;
			}
			//DH
			if (dutystat.dutynumberOfDeadheads != 0) {
				dutystat.dutyaverageLengthOfDeadheads = dutystat.dutyoveralldurationDeadheads
						/ dutystat.dutynumberOfDeadheads;
			} else {
				dutystat.dutyaverageLengthOfDeadheads = 0;
			}
			//PO
			if (dutystat.dutynumberOfPullOuts != 0) {
				dutystat.dutyaverageLengthOfPullOuts = dutystat.dutyoveralldurationPullouts
						/ dutystat.dutynumberOfPullOuts;
			} else {
				dutystat.dutyaverageLengthOfPullOuts = 0;
			}
			//PI
			if (dutystat.dutynumberOfPullIns != 0) {
				dutystat.dutyaverageLengthOfPullIns = dutystat.dutyoveralldurationPullins
						/ dutystat.dutynumberOfPullIns;
			} else {
				dutystat.dutyaverageLengthOfPullIns = 0;
			}
			//Preps
			if (dutystat.dutynumberOfPreparations != 0) {
				dutystat.dutyaverageLengthOfPreparations = dutystat.dutyoveralldurationPreparations
						/ dutystat.dutynumberOfPreparations;
			} else {
				dutystat.dutyaverageLengthOfPreparations = 0;
			}
			//Wraps
			if (dutystat.dutynumberOfWrapUps != 0) {
				dutystat.dutyaverageLengthOfWrapUps = dutystat.dutyoveralldurationWrapUps
						/ dutystat.dutynumberOfWrapUps;
			} else {
				dutystat.dutyaverageLengthOfWrapUps = 0;
			}
			//Transfer
			if (dutystat.dutynumberOfTransfers != 0) {
				dutystat.dutyaverageLengthOfTransfers = dutystat.dutyoveralldurationTransfers
						/ dutystat.dutynumberOfTransfers;
			} else {
				dutystat.dutyaverageLengthOfTransfers = 0;
			}
			//Breaks
			if (dutystat.dutynumberOfBreaks != 0) {
				dutystat.dutyaverageLengthOfBreaks = dutystat.dutyoveralldurationBreaks
						/ dutystat.dutynumberOfBreaks;
			} else {
				dutystat.dutyaverageLengthOfBreaks = 0;
			}
			//Waiting
			if (dutystat.dutynumberOfWaitings != 0) {
				dutystat.dutyaverageLengthOfWaitings = dutystat.dutyoveralldurationWaitings
						/ dutystat.dutynumberOfWaitings;
			} else {
				dutystat.dutyaverageLengthOfWaitings = 0;
			}
			//Layover
			if (dutystat.dutynumberOfLayovers != 0) {
				dutystat.dutyaverageLengthOfLayovers = dutystat.dutyoveralldurationLayovers
						/ dutystat.dutynumberOfLayovers;
			} else {
				dutystat.dutyaverageLengthOfLayovers = 0;
			}
			//Ratio
			if (dutystat.dutytotalRunTime != 0) {
				dutystat.dutyserviceTimetotalDutyTimeRatio = dutystat.dutyoveralldurationServicetrips
						/ dutystat.dutytotalRunTime;
			} else {
				dutystat.dutyserviceTimetotalDutyTimeRatio = 0;	
			}
			
			//Lines
			
			dutystatlist.add(dutystat);
				
		}//for duty
		
		return dutystatlist;
	}
	
	//Helpermethod for calculating Drivetime
		public long calculateDriveTime(String depTime,String arrtime){
			
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date1 = null;
			Date date2 = null;
			long deptimemilsec = 0;
			long arrtimemilsec = 0;
			long differenz = 0;
			try {
				date1 = format.parse(depTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				date2 = format.parse(arrtime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			deptimemilsec = date1.getTime();
			arrtimemilsec = date2.getTime();
			if (arrtimemilsec < deptimemilsec) {
				arrtimemilsec = arrtimemilsec + 86400000;
			}
			differenz = arrtimemilsec - deptimemilsec;
			differenz = (differenz / 1000);

			return differenz;
		}



		public int getDutyHilfsID() {
			return dutyHilfsID;
		}

		public double getDutytotalNumberOfTrips() {
			return dutytotalNumberOfTrips;
		}

		public double getDutytotalRunTime() {
			return dutytotalRunTime;
		}

		public double getDutynumberOfServiceTrips() {
			return dutynumberOfServiceTrips;
		}

		public double getDutyaverageLengthOfServiceTrips() {
			return dutyaverageLengthOfServiceTrips;
		}

		public double getDutynumberOfDeadheads() {
			return dutynumberOfDeadheads;
		}

		public double getDutyaverageLengthOfDeadheads() {
			return dutyaverageLengthOfDeadheads;
		}

		public double getDutynumberOfWaitings() {
			return dutynumberOfWaitings;
		}

		public double getDutyaverageLengthOfWaitings() {
			return dutyaverageLengthOfWaitings;
		}

		public double getDutynumberOfPullOuts() {
			return dutynumberOfPullOuts;
		}

		public double getDutyaverageLengthOfPullOuts() {
			return dutyaverageLengthOfPullOuts;
		}

		public double getDutynumberOfPullIns() {
			return dutynumberOfPullIns;
		}

		public double getDutyaverageLengthOfPullIns() {
			return dutyaverageLengthOfPullIns;
		}

		public double getDutynumberOfVehicles() {
			return dutynumberOfVehicles;
		}

		public double getDutynumberOfPreparations() {
			return dutynumberOfPreparations;
		}

		public double getDutyaverageLengthOfPreparations() {
			return dutyaverageLengthOfPreparations;
		}

		public double getDutynumberOfWrapUps() {
			return dutynumberOfWrapUps;
		}

		public double getDutyaverageLengthOfWrapUps() {
			return dutyaverageLengthOfWrapUps;
		}

		public double getDutynumberOfLayovers() {
			return dutynumberOfLayovers;
		}

		public double getDutyaverageLengthOfLayovers() {
			return dutyaverageLengthOfLayovers;
		}

		public double getDutynumberOfTransfers() {
			return dutynumberOfTransfers;
		}

		public double getDutyaverageLengthOfTransfers() {
			return dutyaverageLengthOfTransfers;
		}

		public double getDutynumberOfBreaks() {
			return dutynumberOfBreaks;
		}

		public double getDutyaverageLengthOfBreaks() {
			return dutyaverageLengthOfBreaks;
		}

		public double getDutyserviceTimetotalDutyTimeRatio() {
			return dutyserviceTimetotalDutyTimeRatio;
		}

		public double getDutyoveralldurationServicetrips() {
			return dutyoveralldurationServicetrips;
		}

		public double getDutyoveralldurationDeadheads() {
			return dutyoveralldurationDeadheads;
		}

		public double getDutyoveralldurationWaitings() {
			return dutyoveralldurationWaitings;
		}

		public double getDutyoveralldurationPullouts() {
			return dutyoveralldurationPullouts;
		}

		public double getDutyoveralldurationPullins() {
			return dutyoveralldurationPullins;
		}

		public double getDutyoveralldurationPreparations() {
			return dutyoveralldurationPreparations;
		}

		public double getDutyoveralldurationWrapUps() {
			return dutyoveralldurationWrapUps;
		}

		public double getDutyoveralldurationLayovers() {
			return dutyoveralldurationLayovers;
		}

		public double getDutyoveralldurationTransfers() {
			return dutyoveralldurationTransfers;
		}

		public double getDutyoveralldurationBreaks() {
			return dutyoveralldurationBreaks;
		}
		
		
}
