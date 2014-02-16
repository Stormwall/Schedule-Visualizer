package sv.creation.adress.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sv.creation.adress.model.Dienstplan;

public class DutyStatistics{
		
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
	double dutyserviceTimetotalDutyTimeRatio = 0;
	
	double dutyoveralldurationServicetrips = 0;
	double dutyoveralldurationDeadheads = 0;
	double dutyoveralldurationWaitings = 0;
	double dutyoveralldurationPullouts = 0;
	double dutyoveralldurationPullins = 0;
	/****************************************************************************
	method for calculating statistics for all duties contained in a crew schedule
	*****************************************************************************/
	public ArrayList<DutyStatistics> calculateDutystatistics(Dienstplan dp){

		
		ArrayList<DutyStatistics> dutystatlist = new ArrayList<DutyStatistics>();
		for (int duty = 0; duty < dp.getDuty().size(); duty++){
			

			String dutyID = dp.getDuty().get(duty).getId();
			
			DutyStatistics dutystat = new DutyStatistics();
			for (int dutyelement = 0; dutyelement < dp.getDutyelement().size(); dutyelement++){
				
				
				
					if(dp.getDutyelement().get(dutyelement).getDutyID().equals(dutyID)){
					
						long runtime = dutystat.calculateDriveTime(dp.getDutyelement().get(dutyelement).getDepTime(), dp.getDutyelement().get(dutyelement).getArrTime());
								
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
						case 5:
							//Pullins
							dutystat.dutynumberOfPullIns++;
							dutystat.dutyoveralldurationPullins = dutystat.dutyoveralldurationPullins + runtime;
							break;
						case 6:
							//Pullout
							dutystat.dutynumberOfPullOuts++;
							dutystat.dutyoveralldurationPullouts = dutystat.dutyoveralldurationPullouts + runtime;
							break;
						case 9:
							//Waitings
							dutystat.dutynumberOfWaitings++;
							dutystat.dutyoveralldurationWaitings = dutystat.dutyoveralldurationWaitings + runtime;
							break;
						}
				}//if
					
				
				
				
			}//for dutyelement
			if (dutystat.dutynumberOfServiceTrips != 0) {
				dutystat.dutyaverageLengthOfServiceTrips = dutystat.dutyoveralldurationServicetrips
						/ dutystat.dutynumberOfServiceTrips;
			} else {
				dutystat.dutyaverageLengthOfServiceTrips = 0;
			}
			if (dutystat.dutynumberOfDeadheads != 0) {
				dutystat.dutyaverageLengthOfDeadheads = dutystat.dutyoveralldurationDeadheads
						/ dutystat.dutynumberOfDeadheads;
			} else {
				dutystat.dutynumberOfDeadheads = 0;
			}
			if (dutystat.dutynumberOfWaitings != 0) {
				dutystat.dutyaverageLengthOfWaitings = dutystat.dutyoveralldurationWaitings
						/ dutystat.dutynumberOfWaitings;
			} else {
				dutystat.dutynumberOfWaitings = 0;
			}
			if (dutystat.dutynumberOfPullOuts != 0) {
				dutystat.dutyaverageLengthOfPullOuts = dutystat.dutyoveralldurationPullouts
						/ dutystat.dutynumberOfPullOuts;
			} else {
				dutystat.dutynumberOfPullOuts = 0;
			}
			if (dutystat.dutynumberOfPullIns != 0) {
				dutystat.dutyaverageLengthOfPullIns = dutystat.dutyoveralldurationPullins
						/ dutystat.dutynumberOfPullIns;
			} else {
				dutystat.dutynumberOfPullIns = 0;
			}

			if (dutystat.dutytotalRunTime != 0) {
				dutystat.dutyserviceTimetotalDutyTimeRatio = dutystat.dutyoveralldurationServicetrips
						/ dutystat.dutytotalRunTime;
			} else {
				dutystat.dutyserviceTimetotalDutyTimeRatio = 0;	
			}
			
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
}
