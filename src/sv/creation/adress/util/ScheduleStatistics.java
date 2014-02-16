package sv.creation.adress.util;

import java.util.ArrayList;



import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Umlaufplan;



/****************************************************************************
 * the statistics for a vehicle or crew schedule are calculated in this class
 ****************************************************************************/

public class ScheduleStatistics {
	/*The following values are calculated for vehicle or crew schedules
	length and time values are given in seconds
	bundle stands for Duty or Block
	numberOfVehiclesPerBundle is always the total number of blocks in vehicle schedules*/
	
	double totalNumberOfTrips = 0;
	double totalRunTime = 0;
	double numberOfServiceTrips = 0;
	double averageLengthOfServiceTrips = 0;
	double numberOfDeadheads = 0;
	double averageLengthOfDeadheads = 0;
	double numberOfWaitings = 0;
	double averageLengthOfWaitings = 0;
	double numberOfPullOuts = 0;
	double averageLengthOfPullOuts = 0;
	double numberOfPullIns = 0;
	double averageLengthOfPullIns = 0;
	double serviceTimetotalDutyTimeRatio = 0;
	double serviceTimetotalBlockTimeRatio = 0;
	//int numberOfLinesPerBundle = 0;
	//int numberOfVehiclesPerBundle = 0;
	

	double sumavgServicetrips = 0;
	double sumavgDeadheads = 0;
	double sumavgWaitings = 0;
	double sumavgPullouts = 0;
	double sumavgPullins = 0;
	double sumserviceTimetotalDutyTimeRatio = 0;

	
	/************************************************************************
	//method for calculating statistics for given list of crew schedules
	max up to five schedules at a time***************************************
	************************************************************************/
	
	public ArrayList<ScheduleStatistics> calculateCrewScheduleStatistics(ArrayList<Dienstplan> dienstplanliste){
		ArrayList<ScheduleStatistics> crewschedulestatisticslist = new ArrayList<ScheduleStatistics>();
		
		for (int i = 0; i < dienstplanliste.size(); i++){
			ScheduleStatistics schedulestats = new ScheduleStatistics();
			
			DutyStatistics stat = new DutyStatistics();
			ArrayList<DutyStatistics> dutystats = new ArrayList<DutyStatistics>();
			dutystats =stat.calculateDutystatistics(dienstplanliste.get(i));
			for (int j=0;j < dutystats.size(); j++){
				
				schedulestats.totalNumberOfTrips = schedulestats.totalNumberOfTrips + dutystats.get(j).dutytotalNumberOfTrips;
				schedulestats.totalRunTime = schedulestats.totalRunTime + dutystats.get(j).dutytotalRunTime;
				schedulestats.numberOfServiceTrips = schedulestats.numberOfServiceTrips + dutystats.get(j).dutynumberOfServiceTrips;
				schedulestats.sumavgServicetrips = schedulestats.sumavgServicetrips + dutystats.get(j).dutyaverageLengthOfServiceTrips;
				schedulestats.numberOfDeadheads = schedulestats.numberOfDeadheads + dutystats.get(j).dutynumberOfDeadheads;
				schedulestats.sumavgDeadheads = schedulestats.sumavgDeadheads + dutystats.get(j).dutyaverageLengthOfDeadheads;
				schedulestats.numberOfWaitings = schedulestats.numberOfWaitings + dutystats.get(j).dutynumberOfWaitings;
				schedulestats.sumavgWaitings = schedulestats.sumavgWaitings + dutystats.get(j).dutyaverageLengthOfWaitings;
				schedulestats.numberOfPullOuts = schedulestats.numberOfPullOuts + dutystats.get(j).dutynumberOfPullOuts;
				schedulestats.sumavgPullouts = schedulestats.sumavgPullouts + dutystats.get(j).dutyaverageLengthOfPullOuts;				
				schedulestats.numberOfPullIns = schedulestats.numberOfPullIns + dutystats.get(j).dutynumberOfPullIns;
				schedulestats.sumavgPullins = schedulestats.sumavgPullins + dutystats.get(j).dutyaverageLengthOfPullIns;
				schedulestats.sumserviceTimetotalDutyTimeRatio = schedulestats.sumserviceTimetotalDutyTimeRatio + dutystats.get(j).dutyserviceTimetotalDutyTimeRatio;

				/*System.out.println(dutystats.get(j).dutytotalNumberOfTrips);
				System.out.println(dutystats.get(j).dutytotalRunTime);
				System.out.println(dutystats.get(j).dutynumberOfServiceTrips);
				System.out.println(dutystats.get(j).dutyaverageLengthOfServiceTrips);
				System.out.println(dutystats.get(j).dutynumberOfDeadheads);
				System.out.println(dutystats.get(j).dutyaverageLengthOfDeadheads);
				System.out.println(dutystats.get(j).dutynumberOfWaitings);
				System.out.println(dutystats.get(j).dutyaverageLengthOfWaitings);
				System.out.println(dutystats.get(j).dutynumberOfPullOuts);
				System.out.println(dutystats.get(j).dutyaverageLengthOfPullOuts);
				System.out.println(dutystats.get(j).dutynumberOfPullIns);
				System.out.println(dutystats.get(j).dutyaverageLengthOfPullIns);
				System.out.println(dutystats.get(j).dutyserviceTimetotalDutyTimeRatio);
				System.out.println("**********************************************");*/
			}
			
			schedulestats.averageLengthOfServiceTrips = schedulestats.sumavgServicetrips/dutystats.size();
			schedulestats.averageLengthOfDeadheads = schedulestats.sumavgDeadheads/dutystats.size();
			schedulestats.averageLengthOfWaitings = schedulestats.sumavgWaitings/dutystats.size();
			schedulestats.averageLengthOfPullOuts = schedulestats.sumavgPullouts/dutystats.size();
			schedulestats.averageLengthOfPullIns = schedulestats.sumavgPullins/dutystats.size();
			schedulestats.sumserviceTimetotalDutyTimeRatio = schedulestats.sumserviceTimetotalDutyTimeRatio/dutystats.size();
			
			crewschedulestatisticslist.add(schedulestats);
		}
		
		return crewschedulestatisticslist;
	}
	/************************************************************************
	//method for calculating statistics for given list of crew schedules
	max up to five schedules at a time***************************************
	************************************************************************/
	
	public ArrayList<ScheduleStatistics> calculateVehicleScheduleStatistics(ArrayList<Umlaufplan> umlaufplanliste){
		ArrayList<ScheduleStatistics> blockchedulestatisticslist = new ArrayList<ScheduleStatistics>();
		
		for (int i = 0; i < umlaufplanliste.size(); i++){
			ScheduleStatistics schedulestats = new ScheduleStatistics();
			
			BlockStatistics stat = new BlockStatistics();
			ArrayList<BlockStatistics> blockstats = new ArrayList<BlockStatistics>();
			blockstats =stat.calculateBlockStatistics(umlaufplanliste.get(i));
			for (int j=0;j < blockstats.size(); j++){
				
				schedulestats.totalNumberOfTrips = schedulestats.totalNumberOfTrips + blockstats.get(j).blocktotalNumberOfTrips;
				schedulestats.totalRunTime = schedulestats.totalRunTime + blockstats.get(j).blocktotalRunTime;
				schedulestats.numberOfServiceTrips = schedulestats.numberOfServiceTrips + blockstats.get(j).blocknumberOfServiceTrips;
				schedulestats.sumavgServicetrips = schedulestats.sumavgServicetrips + blockstats.get(j).blockaverageLengthOfServiceTrips;
				schedulestats.numberOfDeadheads = schedulestats.numberOfDeadheads + blockstats.get(j).blocknumberOfDeadheads;
				schedulestats.sumavgDeadheads = schedulestats.sumavgDeadheads + blockstats.get(j).blockaverageLengthOfDeadheads;
				schedulestats.numberOfWaitings = schedulestats.numberOfWaitings + blockstats.get(j).blocknumberOfWaitings;
				schedulestats.sumavgWaitings = schedulestats.sumavgWaitings + blockstats.get(j).blockaverageLengthOfWaitings;
				schedulestats.numberOfPullOuts = schedulestats.numberOfPullOuts + blockstats.get(j).blocknumberOfPullOuts;
				schedulestats.sumavgPullouts = schedulestats.sumavgPullouts + blockstats.get(j).blockaverageLengthOfPullOuts;				
				schedulestats.numberOfPullIns = schedulestats.numberOfPullIns + blockstats.get(j).blocknumberOfPullIns;
				schedulestats.sumavgPullins = schedulestats.sumavgPullins + blockstats.get(j).blockaverageLengthOfPullIns;
				schedulestats.sumserviceTimetotalDutyTimeRatio = schedulestats.sumserviceTimetotalDutyTimeRatio + blockstats.get(j).blockserviceTimetotalBlockTimeRatio;
//Testausgabe
				/*System.out.println(blockstats.get(j).blocktotalNumberOfTrips);
				System.out.println(blockstats.get(j).blocktotalRunTime);
				System.out.println(blockstats.get(j).blocknumberOfServiceTrips);
				System.out.println(blockstats.get(j).blockaverageLengthOfServiceTrips);
				System.out.println(blockstats.get(j).blocknumberOfDeadheads);
				System.out.println(blockstats.get(j).blockaverageLengthOfDeadheads);
				System.out.println(blockstats.get(j).blocknumberOfWaitings);
				System.out.println(blockstats.get(j).blockaverageLengthOfWaitings);
				System.out.println(blockstats.get(j).blocknumberOfPullOuts);
				System.out.println(blockstats.get(j).blockaverageLengthOfPullOuts);
				System.out.println(blockstats.get(j).blocknumberOfPullIns);
				System.out.println(blockstats.get(j).blockaverageLengthOfPullIns);
				System.out.println(blockstats.get(j).blockserviceTimetotalBlockTimeRatio);
				System.out.println("**********************************************");*/
			}
			
			schedulestats.averageLengthOfServiceTrips = schedulestats.sumavgServicetrips/blockstats.size();
			schedulestats.averageLengthOfDeadheads = schedulestats.sumavgDeadheads/blockstats.size();
			schedulestats.averageLengthOfWaitings = schedulestats.sumavgWaitings/blockstats.size();
			schedulestats.averageLengthOfPullOuts = schedulestats.sumavgPullouts/blockstats.size();
			schedulestats.averageLengthOfPullIns = schedulestats.sumavgPullins/blockstats.size();
			schedulestats.sumserviceTimetotalDutyTimeRatio = schedulestats.sumserviceTimetotalDutyTimeRatio/blockstats.size();
			
			blockchedulestatisticslist.add(schedulestats);
		}
		
		return blockchedulestatisticslist;
	}

	public double getTotalNumberOfTrips() {
		return totalNumberOfTrips;
	}


	public double getTotalRunTime() {
		return totalRunTime;
	}


	public double getNumberOfServiceTrips() {
		return numberOfServiceTrips;
	}


	public double getAverageLengthOfServiceTrips() {
		return averageLengthOfServiceTrips;
	}


	public double getNumberOfDeadheads() {
		return numberOfDeadheads;
	}


	public double getAverageLengthOfDeadheads() {
		return averageLengthOfDeadheads;
	}


	public double getNumberOfWaitings() {
		return numberOfWaitings;
	}


	public double getAverageLengthOfWaitings() {
		return averageLengthOfWaitings;
	}


	public double getNumberOfPullOuts() {
		return numberOfPullOuts;
	}


	public double getAverageLengthOfPullOuts() {
		return averageLengthOfPullOuts;
	}


	public double getNumberOfPullIns() {
		return numberOfPullIns;
	}


	public double getAverageLengthOfPullIns() {
		return averageLengthOfPullIns;
	}


	public double getServiceTimetotalDutyTimeRatio() {
		return serviceTimetotalDutyTimeRatio;
	}
	public double getServiceTimetotalBlockTimeRatio() {
		return serviceTimetotalBlockTimeRatio;
	}
}
