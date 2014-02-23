package sv.creation.adress.util;

import java.util.ArrayList;

import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.model.Umlaufplan;

/****************************************************************************
 * the statistics for a vehicle or crew schedule are calculated in this class
 ****************************************************************************/

public class ScheduleStatistics {
	/*
	 * The following values are calculated for vehicle or crew schedules length
	 * and time values are given in seconds bundle stands for Duty or Block
	 * numberOfVehiclesPerBundle:???
	 */

	// absolute numbers for both
	double totalNumberOfTrips = 0;
	double numberOfServiceTrips = 0;
	double numberOfDeadheads = 0;
	double numberOfWaitings = 0;
	double numberOfPullOuts = 0;
	double numberOfPullIns = 0;

	// int numberOfLinesPerBundle = 0;
	// number of bundles
	double numberOfBundles = 0;

	// additional numbers for crew schedules
	double numberOfTransfers = 0;
	double numberOfBreaks = 0;

	// additional numbers for both
	double numberOfPreparations = 0;
	double numberOfWrapUps = 0;
	double numberOfLayovers = 0;

	// averages for both
	double averageLengthOfTotalTrips = 0;
	double averageLengthOfServiceTrips = 0;
	double averageLengthOfDeadheads = 0;
	double averageLengthOfWaitings = 0;
	double averageLengthOfPullOuts = 0;
	double averageLengthOfPullIns = 0;

	// additional averages for crew schedules
	double averageLengthOfTranfers = 0;
	double averageLengthOfBreaks = 0;
	double avgnumberOfVehiclesPerBundle = 0;

	// additional averages for both
	double averageLengthOfPreparations = 0;
	double averageLengthOfWrapUpss = 0;
	double averageLengthOfLayovers = 0;

	// sums for duty/block averages
	double sumavgServicetrips = 0;
	double sumavgDeadheads = 0;
	double sumavgWaitings = 0;
	double sumavgPullouts = 0;
	double sumavgPullins = 0;
	double sumavgPreps = 0;
	double sumavgWraps = 0;
	double sumavgLayovers = 0;
	double sumavgTransfers = 0;
	double sumavgBreaks = 0;
	double sumavgnumberOfVehiclesPerBundle = 0;

	// overall durations for both
	double totalRunTime = 0;
	double overalldurationServiceTrips = 0;
	double overalldurationDeadHeads = 0;
	double overalldurationWaitings = 0;
	double overalldurationPullIns = 0;
	double overalldurationPullOuts = 0;
	double overalldurationPreparations = 0;
	double overalldurationWrapUps = 0;
	double overalldurationLayovers = 0;
	double overalldurationTransfers = 0;
	double overalldurationBreaks = 0;
	// service time ratio
	double serviceTimetotalTimeRatio = 0;
	double sumserviceTimetotalTimeRatio = 0;

	/*****************************************************************
	 * method for calculating statistics for given list of crew schedules max up
	 * to five schedules at a time********************************
	 *****************************************************************/

	public ArrayList<ScheduleStatistics> calculateCrewScheduleStatistics(
			ArrayList<Dienstplan> dienstplanliste, Fahrplan fp) {
		ArrayList<ScheduleStatistics> crewschedulestatisticslist = new ArrayList<ScheduleStatistics>();

		for (int i = 0; i < dienstplanliste.size(); i++) {
			ScheduleStatistics schedulestats = new ScheduleStatistics();

			DutyStatistics stat = new DutyStatistics();
			ArrayList<DutyStatistics> dutystats = new ArrayList<DutyStatistics>();
			dutystats = stat.calculateDutystatistics(dienstplanliste.get(i), fp);
			for (int j = 0; j < dutystats.size(); j++) {
				// Total Trips
				schedulestats.totalNumberOfTrips = roundValue(schedulestats.totalNumberOfTrips
						+ dutystats.get(j).dutytotalNumberOfTrips);
				schedulestats.totalRunTime = roundValue(schedulestats.totalRunTime
						+ dutystats.get(j).dutytotalRunTime);
				// Service Trips
				schedulestats.numberOfServiceTrips = roundValue(schedulestats.numberOfServiceTrips
						+ dutystats.get(j).dutynumberOfServiceTrips);
				schedulestats.sumavgServicetrips = roundValue(schedulestats.sumavgServicetrips
						+ dutystats.get(j).dutyaverageLengthOfServiceTrips);
				schedulestats.overalldurationServiceTrips = roundValue(schedulestats.overalldurationServiceTrips
						+ dutystats.get(j).dutyoveralldurationServicetrips);
				// DeadHeads
				schedulestats.numberOfDeadheads = roundValue(schedulestats.numberOfDeadheads
						+ dutystats.get(j).dutynumberOfDeadheads);
				schedulestats.sumavgDeadheads = roundValue(schedulestats.sumavgDeadheads
						+ dutystats.get(j).dutyaverageLengthOfDeadheads);
				schedulestats.overalldurationDeadHeads = roundValue(schedulestats.overalldurationDeadHeads
						+ dutystats.get(j).dutyoveralldurationDeadheads);
				// Waitings
				schedulestats.numberOfWaitings = roundValue(schedulestats.numberOfWaitings
						+ dutystats.get(j).dutynumberOfWaitings);
				schedulestats.sumavgWaitings = roundValue(schedulestats.sumavgWaitings
						+ dutystats.get(j).dutyaverageLengthOfWaitings);
				schedulestats.overalldurationWaitings = roundValue(schedulestats.overalldurationWaitings
						+ dutystats.get(j).dutyoveralldurationWaitings);
				// POs
				schedulestats.numberOfPullOuts = roundValue(schedulestats.numberOfPullOuts
						+ dutystats.get(j).dutynumberOfPullOuts);
				schedulestats.sumavgPullouts = roundValue(schedulestats.sumavgPullouts
						+ dutystats.get(j).dutyaverageLengthOfPullOuts);
				schedulestats.overalldurationPullOuts = roundValue(schedulestats.overalldurationPullOuts
						+ dutystats.get(j).dutynumberOfPullOuts);
				// PIs
				schedulestats.numberOfPullIns = roundValue(schedulestats.numberOfPullIns
						+ dutystats.get(j).dutynumberOfPullIns);
				schedulestats.sumavgPullins = roundValue(schedulestats.sumavgPullins
						+ dutystats.get(j).dutyaverageLengthOfPullIns);
				schedulestats.overalldurationPullIns = roundValue(schedulestats.overalldurationPullIns
						+ dutystats.get(j).dutyoveralldurationPullins);
				// Preps
				schedulestats.numberOfPreparations = roundValue(schedulestats.numberOfPreparations
						+ dutystats.get(j).dutynumberOfPreparations);
				schedulestats.sumavgPreps = roundValue(schedulestats.sumavgPreps
						+ dutystats.get(j).dutyaverageLengthOfPreparations);
				schedulestats.overalldurationPreparations = roundValue(schedulestats.overalldurationPreparations
						+ dutystats.get(j).dutyoveralldurationPreparations);
				// Wraps
				schedulestats.numberOfWrapUps = roundValue(schedulestats.numberOfWrapUps
						+ dutystats.get(j).dutynumberOfWrapUps);
				schedulestats.sumavgWraps = roundValue(schedulestats.sumavgWraps
						+ dutystats.get(j).dutyaverageLengthOfWrapUps);
				schedulestats.overalldurationWrapUps = roundValue(schedulestats.overalldurationWrapUps
						+ dutystats.get(j).dutyoveralldurationWrapUps);
				// Layovers
				schedulestats.numberOfLayovers = roundValue(schedulestats.numberOfLayovers
						+ dutystats.get(j).dutynumberOfLayovers);
				schedulestats.sumavgLayovers = roundValue(schedulestats.sumavgLayovers
						+ dutystats.get(j).dutyaverageLengthOfLayovers);
				schedulestats.overalldurationLayovers = roundValue(schedulestats.overalldurationLayovers
						+ dutystats.get(j).dutyoveralldurationLayovers);
				// Transfers
				schedulestats.numberOfTransfers = roundValue(schedulestats.numberOfTransfers
						+ dutystats.get(j).dutynumberOfTransfers);
				schedulestats.sumavgTransfers = roundValue(schedulestats.sumavgTransfers
						+ dutystats.get(j).dutyaverageLengthOfTransfers);
				schedulestats.overalldurationTransfers = roundValue(schedulestats.overalldurationTransfers
						+ dutystats.get(j).dutyoveralldurationTransfers);
				// Breaks
				schedulestats.numberOfBreaks = roundValue(schedulestats.numberOfBreaks
						+ dutystats.get(j).dutynumberOfBreaks);
				schedulestats.sumavgBreaks = roundValue(schedulestats.sumavgBreaks
						+ dutystats.get(j).dutyaverageLengthOfBreaks);
				schedulestats.overalldurationBreaks = roundValue(schedulestats.overalldurationBreaks
						+ dutystats.get(j).dutyoveralldurationBreaks);
				// Vehicles geht noch nicht!!!!!!
				schedulestats.sumavgnumberOfVehiclesPerBundle = roundValue(schedulestats.sumavgnumberOfVehiclesPerBundle
						+ dutystats.get(j).dutynumberOfVehicles);
				// Ratio
				schedulestats.sumserviceTimetotalTimeRatio = roundValue(schedulestats.sumserviceTimetotalTimeRatio
						+ dutystats.get(j).dutyserviceTimetotalDutyTimeRatio);
			}
			schedulestats.averageLengthOfTotalTrips = roundValue(schedulestats.totalRunTime
					/ schedulestats.totalNumberOfTrips);
			schedulestats.averageLengthOfServiceTrips = roundValue(schedulestats.sumavgServicetrips
					/ dutystats.size());
			schedulestats.averageLengthOfDeadheads = roundValue(schedulestats.sumavgDeadheads
					/ dutystats.size());
			schedulestats.averageLengthOfWaitings = roundValue(schedulestats.sumavgWaitings
					/ dutystats.size());
			schedulestats.averageLengthOfPullOuts = roundValue(schedulestats.sumavgPullouts
					/ dutystats.size());
			schedulestats.averageLengthOfPreparations = roundValue(schedulestats.sumavgPullins
					/ dutystats.size());
			schedulestats.averageLengthOfPreparations = roundValue(schedulestats.overalldurationPreparations
					/ dutystats.size());
			schedulestats.averageLengthOfWrapUpss = roundValue(schedulestats.overalldurationWrapUps
					/ dutystats.size());
			schedulestats.averageLengthOfLayovers = roundValue(schedulestats.overalldurationLayovers
					/ dutystats.size());
			schedulestats.averageLengthOfTranfers = roundValue(schedulestats.overalldurationTransfers
					/ dutystats.size());
			schedulestats.averageLengthOfBreaks = roundValue(schedulestats.overalldurationBreaks
					/ dutystats.size());

			schedulestats.sumserviceTimetotalTimeRatio = roundValue(schedulestats.sumserviceTimetotalTimeRatio
					/ dutystats.size());
			schedulestats.numberOfBundles = dutystats.size();
			schedulestats.avgnumberOfVehiclesPerBundle = roundValue(schedulestats.sumavgnumberOfVehiclesPerBundle
					/ dutystats.size());
			crewschedulestatisticslist.add(schedulestats);
		}

		return crewschedulestatisticslist;
	}

	/************************************************************************
	 * //method for calculating statistics for given list of crew schedules max
	 * up to five schedules at a time***************************************
	 ************************************************************************/

	public ArrayList<ScheduleStatistics> calculateVehicleScheduleStatistics(
			ArrayList<Umlaufplan> umlaufplanliste,Fahrplan fp) {
		ArrayList<ScheduleStatistics> blockchedulestatisticslist = new ArrayList<ScheduleStatistics>();

		for (int i = 0; i < umlaufplanliste.size(); i++) {
			ScheduleStatistics schedulestats = new ScheduleStatistics();

			BlockStatistics stat = new BlockStatistics();
			ArrayList<BlockStatistics> blockstats = new ArrayList<BlockStatistics>();
			blockstats = stat.calculateBlockStatistics(umlaufplanliste.get(i),fp);
			for (int j = 0; j < blockstats.size(); j++) {

				// Total Trips
				schedulestats.totalNumberOfTrips = schedulestats.totalNumberOfTrips
						+ blockstats.get(j).blocktotalNumberOfTrips;
				schedulestats.totalRunTime = schedulestats.totalRunTime
						+ blockstats.get(j).blocktotalRunTime;
				// Service Trips
				schedulestats.numberOfServiceTrips = schedulestats.numberOfServiceTrips
						+ blockstats.get(j).blocknumberOfServiceTrips;
				schedulestats.sumavgServicetrips = schedulestats.sumavgServicetrips
						+ blockstats.get(j).blockaverageLengthOfServiceTrips;
				schedulestats.overalldurationServiceTrips = schedulestats.overalldurationServiceTrips
						+ blockstats.get(j).blockoveralldurationServicetrips;
				// DeadHeads
				schedulestats.numberOfDeadheads = schedulestats.numberOfDeadheads
						+ blockstats.get(j).blocknumberOfDeadheads;
				schedulestats.sumavgDeadheads = schedulestats.sumavgDeadheads
						+ blockstats.get(j).blockaverageLengthOfDeadheads;
				schedulestats.overalldurationDeadHeads = schedulestats.overalldurationDeadHeads
						+ blockstats.get(j).blockoveralldurationDeadheads;
				// Waitings
				schedulestats.numberOfWaitings = schedulestats.numberOfWaitings
						+ blockstats.get(j).blocknumberOfWaitings;
				schedulestats.sumavgWaitings = schedulestats.sumavgWaitings
						+ blockstats.get(j).blockaverageLengthOfWaitings;
				schedulestats.overalldurationWaitings = schedulestats.overalldurationWaitings
						+ blockstats.get(j).blockoveralldurationWaitings;
				// POs
				schedulestats.numberOfPullOuts = schedulestats.numberOfPullOuts
						+ blockstats.get(j).blocknumberOfPullOuts;
				schedulestats.sumavgPullouts = schedulestats.sumavgPullouts
						+ blockstats.get(j).blockaverageLengthOfPullOuts;
				schedulestats.overalldurationPullOuts = schedulestats.overalldurationPullOuts
						+ blockstats.get(j).blocknumberOfPullOuts;
				// PIs
				schedulestats.numberOfPullIns = schedulestats.numberOfPullIns
						+ blockstats.get(j).blocknumberOfPullIns;
				schedulestats.sumavgPullins = schedulestats.sumavgPullins
						+ blockstats.get(j).blockaverageLengthOfPullIns;
				schedulestats.overalldurationPullIns = schedulestats.overalldurationPullIns
						+ blockstats.get(j).blockoveralldurationPullins;
				// Preps
				schedulestats.numberOfPreparations = schedulestats.numberOfPreparations
						+ blockstats.get(j).blocknumberOfPreparations;
				schedulestats.sumavgPreps = schedulestats.sumavgPreps
						+ blockstats.get(j).blockaverageLengthOfPreparations;
				schedulestats.overalldurationPreparations = schedulestats.overalldurationPreparations
						+ blockstats.get(j).blockoveralldurationPreparations;
				// Wraps
				schedulestats.numberOfWrapUps = schedulestats.numberOfWrapUps
						+ blockstats.get(j).blocknumberOfWrapUps;
				schedulestats.sumavgWraps = schedulestats.sumavgWraps
						+ blockstats.get(j).blockaverageLengthOfWrapUps;
				schedulestats.overalldurationWrapUps = schedulestats.overalldurationWrapUps
						+ blockstats.get(j).blockoveralldurationWrapUps;
				// Layovers
				schedulestats.numberOfLayovers = schedulestats.numberOfLayovers
						+ blockstats.get(j).blocknumberOfLayovers;
				schedulestats.sumavgLayovers = schedulestats.sumavgLayovers
						+ blockstats.get(j).blockaverageLengthOfLayovers;
				schedulestats.overalldurationLayovers = schedulestats.overalldurationLayovers
						+ blockstats.get(j).blockoveralldurationLayovers;
				// Ratio
				schedulestats.sumserviceTimetotalTimeRatio = schedulestats.sumserviceTimetotalTimeRatio
						+ blockstats.get(j).blockserviceTimetotalBlockTimeRatio;
			}
			schedulestats.averageLengthOfTotalTrips = roundValue(schedulestats.totalRunTime
					/ schedulestats.totalNumberOfTrips);
			schedulestats.averageLengthOfServiceTrips = schedulestats.sumavgServicetrips
					/ blockstats.size();
			schedulestats.averageLengthOfDeadheads = schedulestats.sumavgDeadheads
					/ blockstats.size();
			schedulestats.averageLengthOfWaitings = schedulestats.sumavgWaitings
					/ blockstats.size();
			schedulestats.averageLengthOfPullOuts = schedulestats.sumavgPullouts
					/ blockstats.size();
			schedulestats.averageLengthOfPullIns = schedulestats.sumavgPullins
					/ blockstats.size();
			schedulestats.averageLengthOfPreparations = schedulestats.overalldurationPreparations
					/ blockstats.size();
			schedulestats.averageLengthOfWrapUpss = schedulestats.overalldurationWrapUps
					/ blockstats.size();
			schedulestats.averageLengthOfLayovers = schedulestats.overalldurationLayovers
					/ blockstats.size();
			schedulestats.averageLengthOfTranfers = schedulestats.overalldurationTransfers
					/ blockstats.size();
			schedulestats.averageLengthOfBreaks = schedulestats.overalldurationBreaks
					/ blockstats.size();

			schedulestats.sumserviceTimetotalTimeRatio = schedulestats.sumserviceTimetotalTimeRatio
					/ blockstats.size();

			blockchedulestatisticslist.add(schedulestats);
		}

		return blockchedulestatisticslist;
	}

	public double getTotalNumberOfTrips() {
		return totalNumberOfTrips;
	}

	public double getNumberOfServiceTrips() {
		return numberOfServiceTrips;
	}

	public double getNumberOfDeadheads() {
		return numberOfDeadheads;
	}

	public double getNumberOfWaitings() {
		return numberOfWaitings;
	}

	public double getNumberOfPullOuts() {
		return numberOfPullOuts;
	}

	public double getNumberOfPullIns() {
		return numberOfPullIns;
	}

	public double getNumberOfBundles() {
		return numberOfBundles;
	}

	public double getNumberOfTransfers() {
		return numberOfTransfers;
	}

	public double getNumberOfBreaks() {
		return numberOfBreaks;
	}

	public double getNumberOfPreparations() {
		return numberOfPreparations;
	}

	public double getNumberOfWrapUps() {
		return numberOfWrapUps;
	}

	public double getNumberOfLayovers() {
		return numberOfLayovers;
	}

	public double getAverageLengthOfTotalTrips() {
		return averageLengthOfTotalTrips;
	}

	public double getAverageLengthOfServiceTrips() {
		return averageLengthOfServiceTrips;
	}

	public double getAverageLengthOfDeadheads() {
		return averageLengthOfDeadheads;
	}

	public double getAverageLengthOfWaitings() {
		return averageLengthOfWaitings;
	}

	public double getAverageLengthOfPullOuts() {
		return averageLengthOfPullOuts;
	}

	public double getAverageLengthOfPullIns() {
		return averageLengthOfPullIns;
	}

	public double getAverageLengthOfTranfers() {
		return averageLengthOfTranfers;
	}

	public double getAverageLengthOfBreaks() {
		return averageLengthOfBreaks;
	}

	public double getAvgnumberOfVehiclesPerBundle() {
		return avgnumberOfVehiclesPerBundle;
	}

	public double getAverageLengthOfPreparations() {
		return averageLengthOfPreparations;
	}

	public double getAverageLengthOfWrapUpss() {
		return averageLengthOfWrapUpss;
	}

	public double getAverageLengthOfLayovers() {
		return averageLengthOfLayovers;
	}

	public double getSumavgServicetrips() {
		return sumavgServicetrips;
	}

	public double getSumavgDeadheads() {
		return sumavgDeadheads;
	}

	public double getSumavgWaitings() {
		return sumavgWaitings;
	}

	public double getSumavgPullouts() {
		return sumavgPullouts;
	}

	public double getSumavgPullins() {
		return sumavgPullins;
	}

	public double getSumavgPreps() {
		return sumavgPreps;
	}

	public double getSumavgWraps() {
		return sumavgWraps;
	}

	public double getSumavgLayovers() {
		return sumavgLayovers;
	}

	public double getSumavgTransfers() {
		return sumavgTransfers;
	}

	public double getSumavgBreaks() {
		return sumavgBreaks;
	}

	public double getSumavgnumberOfVehiclesPerBundle() {
		return sumavgnumberOfVehiclesPerBundle;
	}

	public double getTotalRunTime() {
		return totalRunTime;
	}

	public double getOveralldurationServiceTrips() {
		return overalldurationServiceTrips;
	}

	public double getOveralldurationDeadHeads() {
		return overalldurationDeadHeads;
	}

	public double getOveralldurationWaitings() {
		return overalldurationWaitings;
	}

	public double getOveralldurationPullIns() {
		return overalldurationPullIns;
	}

	public double getOveralldurationPullOuts() {
		return overalldurationPullOuts;
	}

	public double getOveralldurationPreparations() {
		return overalldurationPreparations;
	}

	public double getOveralldurationWrapUps() {
		return overalldurationWrapUps;
	}

	public double getOveralldurationLayovers() {
		return overalldurationLayovers;
	}

	public double getOveralldurationTransfers() {
		return overalldurationTransfers;
	}

	public double getOveralldurationBreaks() {
		return overalldurationBreaks;
	}

	public double getServiceTimetotalTimeRatio() {
		return serviceTimetotalTimeRatio;
	}

	public double getSumserviceTimetotalTimeRatio() {
		return sumserviceTimetotalTimeRatio;
	}
	
	//Methode rundet ein double Wert auf 3 Nachkommastellen
	public double roundValue(double wert){
		
		return Math.round(1000.0*wert)/1000.0;
	}

}
