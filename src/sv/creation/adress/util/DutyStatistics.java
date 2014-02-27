package sv.creation.adress.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Fahrplan;

public class DutyStatistics {
	int dutyHilfsID;
	int dutytotalNumberOfTrips = 0;
	int dutynumberOfServiceTrips = 0;
	int dutynumberOfDeadheads = 0;
	int dutynumberOfWaitings = 0;
	int dutynumberOfPullOuts = 0;
	int dutynumberOfPullIns = 0;
	int dutynumberOfPreparations = 0;
	int dutynumberOfWrapUps = 0;
	int dutynumberOfLayovers = 0;
	int dutynumberOfTransfers = 0;
	int dutynumberOfBreaks = 0;

	int dutynumberOfLines = 0;
	int dutynumberOfVehicles = 0;

	double dutyaverageLengthOfServiceTrips = 0;
	double dutyaverageLengthOfDeadheads = 0;
	double dutyaverageLengthOfWaitings = 0;
	double dutyaverageLengthOfPullOuts = 0;
	double dutyaverageLengthOfPullIns = 0;
	double dutyaverageLengthOfPreparations = 0;
	double dutyaverageLengthOfWrapUps = 0;
	double dutyaverageLengthOfLayovers = 0;
	double dutyaverageLengthOfTransfers = 0;
	double dutyaverageLengthOfBreaks = 0;

	double dutyserviceTimetotalDutyTimeRatio = 0;

	long dutytotalRunTime = 0;
	long dutyoveralldurationServicetrips = 0;
	long dutyoveralldurationDeadheads = 0;
	long dutyoveralldurationWaitings = 0;
	long dutyoveralldurationPullouts = 0;
	long dutyoveralldurationPullins = 0;

	long dutyoveralldurationPreparations = 0;
	long dutyoveralldurationWrapUps = 0;
	long dutyoveralldurationLayovers = 0;
	long dutyoveralldurationTransfers = 0;
	long dutyoveralldurationBreaks = 0;

	/****************************************************************************
	 * method for calculating statistics for all duties contained in a crew
	 * schedule
	 *****************************************************************************/
	public ArrayList<DutyStatistics> calculateDutystatistics(Dienstplan dp,
			Fahrplan fp) {

		ArrayList<DutyStatistics> dutystatlist = new ArrayList<DutyStatistics>();
		for (int duty = 0; duty < dp.getDuty().size(); duty++) {

			String dutyiD = dp.getDuty().get(duty).getId();
			int hilfsID = dp.getDuty().get(duty).getHilfsID();
			HashSet dutyblockID = new HashSet();
			HashSet dutylineID = new HashSet();
			// ArrayList<Integer> dutyblockID = new ArrayList<Integer>();
			// ArrayList<Integer> dutylineID = new ArrayList<Integer>();
			DutyStatistics dutystat = new DutyStatistics();

			for (int dutyelement = 0; dutyelement < dp.getDutyelement().size(); dutyelement++) {

				if (dp.getDutyelement().get(dutyelement).getDutyID()
						.equals(dutyiD)) {

					if (dp.getDutyelement().get(dutyelement).getBlockID() > 0) {
						dutyblockID.add(dp.getDutyelement().get(dutyelement)
								.getBlockID());
					}

					long runtime = dutystat.calculateDriveTime(dp
							.getDutyelement().get(dutyelement).getDepTime(), dp
							.getDutyelement().get(dutyelement).getArrTime());
					dutystat.dutyHilfsID = hilfsID;
					dutystat.dutytotalNumberOfTrips++;
					dutystat.dutytotalRunTime = dutystat.dutytotalRunTime
							+ runtime;

					switch (dp.getDutyelement().get(dutyelement)
							.getElementType()) {
					case 1:
						// Service Trip
						dutystat.dutynumberOfServiceTrips++;
						dutystat.dutyoveralldurationServicetrips = dutystat.dutyoveralldurationServicetrips
								+ runtime;

						
						dutylineID.add(fp
								.getServicejourney()
								.get((Integer
										.parseInt(dp.getDutyelement()
												.get(dutyelement)
												.getServiceJourneyID()))-1)
								.getLineID());
						break;
					case 2:
						// Deadheading
						dutystat.dutynumberOfDeadheads++;
						dutystat.dutyoveralldurationDeadheads = dutystat.dutyoveralldurationDeadheads
								+ runtime;
						break;
					case 3:
						// Pullins
						dutystat.dutynumberOfPullIns++;
						dutystat.dutyoveralldurationPullins = dutystat.dutyoveralldurationPullins
								+ runtime;
						break;
					case 4:
						// Pullout
						dutystat.dutynumberOfPullOuts++;
						dutystat.dutyoveralldurationPullouts = dutystat.dutyoveralldurationPullouts
								+ runtime;
						break;
					case 5:
						// Preparation
						dutystat.dutynumberOfPreparations++;
						dutystat.dutyoveralldurationPreparations = dutystat.dutyoveralldurationPreparations
								+ runtime;
						break;
					case 6:
						// Wrap-Ups
						dutystat.dutynumberOfWrapUps++;
						dutystat.dutyoveralldurationWrapUps = dutystat.dutyoveralldurationWrapUps
								+ runtime;
						break;
					case 7:
						// Transfer
						dutystat.dutynumberOfTransfers++;
						dutystat.dutyoveralldurationTransfers = dutystat.dutyoveralldurationTransfers
								+ runtime;
						break;
					case 8:
						// Break
						dutystat.dutynumberOfBreaks++;
						dutystat.dutyoveralldurationBreaks = dutystat.dutyoveralldurationBreaks
								+ runtime;
						break;
					case 9:
						// Waiting
						dutystat.dutynumberOfWaitings++;
						dutystat.dutyoveralldurationWaitings = dutystat.dutyoveralldurationWaitings
								+ runtime;
						break;
					case 10:
						// Layover
						dutystat.dutynumberOfLayovers++;
						dutystat.dutyoveralldurationLayovers = dutystat.dutyoveralldurationLayovers
								+ runtime;
						break;
					}

				}// if
			}
			// Vehicles per Duty
			dutystat.dutynumberOfVehicles = dutyblockID.size();
			// Lines per Duty
			dutystat.dutynumberOfLines = dutylineID.size();
			// Elementtypes
			if (dutystat.dutynumberOfServiceTrips != 0) {
				dutystat.dutyaverageLengthOfServiceTrips = dutystat.dutyoveralldurationServicetrips
						/ dutystat.dutynumberOfServiceTrips;
			} else {
				dutystat.dutyaverageLengthOfServiceTrips = 0;
			}
			// DH
			if (dutystat.dutynumberOfDeadheads != 0) {
				dutystat.dutyaverageLengthOfDeadheads = dutystat.dutyoveralldurationDeadheads
						/ dutystat.dutynumberOfDeadheads;
			} else {
				dutystat.dutyaverageLengthOfDeadheads = 0;
			}
			// PO
			if (dutystat.dutynumberOfPullOuts != 0) {
				dutystat.dutyaverageLengthOfPullOuts = dutystat.dutyoveralldurationPullouts
						/ dutystat.dutynumberOfPullOuts;
			} else {
				dutystat.dutyaverageLengthOfPullOuts = 0;
			}
			// PI
			if (dutystat.dutynumberOfPullIns != 0) {
				dutystat.dutyaverageLengthOfPullIns = dutystat.dutyoveralldurationPullins
						/ dutystat.dutynumberOfPullIns;
			} else {
				dutystat.dutyaverageLengthOfPullIns = 0;
			}
			// Preps
			if (dutystat.dutynumberOfPreparations != 0) {
				dutystat.dutyaverageLengthOfPreparations = dutystat.dutyoveralldurationPreparations
						/ dutystat.dutynumberOfPreparations;
			} else {
				dutystat.dutyaverageLengthOfPreparations = 0;
			}
			// Wraps
			if (dutystat.dutynumberOfWrapUps != 0) {
				dutystat.dutyaverageLengthOfWrapUps = dutystat.dutyoveralldurationWrapUps
						/ dutystat.dutynumberOfWrapUps;
			} else {
				dutystat.dutyaverageLengthOfWrapUps = 0;
			}
			// Transfer
			if (dutystat.dutynumberOfTransfers != 0) {
				dutystat.dutyaverageLengthOfTransfers = dutystat.dutyoveralldurationTransfers
						/ dutystat.dutynumberOfTransfers;
			} else {
				dutystat.dutyaverageLengthOfTransfers = 0;
			}
			// Breaks
			if (dutystat.dutynumberOfBreaks != 0) {
				dutystat.dutyaverageLengthOfBreaks = dutystat.dutyoveralldurationBreaks
						/ dutystat.dutynumberOfBreaks;
			} else {
				dutystat.dutyaverageLengthOfBreaks = 0;
			}
			// Waiting
			if (dutystat.dutynumberOfWaitings != 0) {
				dutystat.dutyaverageLengthOfWaitings = dutystat.dutyoveralldurationWaitings
						/ dutystat.dutynumberOfWaitings;
			} else {
				dutystat.dutyaverageLengthOfWaitings = 0;
			}
			// Layover
			if (dutystat.dutynumberOfLayovers != 0) {
				dutystat.dutyaverageLengthOfLayovers = Math.round(1000.0*(dutystat.dutyoveralldurationLayovers
						/ dutystat.dutynumberOfLayovers))/1000.0;
			} else {
				dutystat.dutyaverageLengthOfLayovers = 0;
			}
			// Ratio
			if (dutystat.dutytotalRunTime != 0) {
				dutystat.dutyserviceTimetotalDutyTimeRatio = Math.round(1000.0*((double)dutystat.dutyoveralldurationServicetrips
						/ (double)dutystat.dutytotalRunTime))/1000.0;
			} else {
				dutystat.dutyserviceTimetotalDutyTimeRatio = 0;
			}

			// Lines

			dutystatlist.add(dutystat);

		}// for duty

		return dutystatlist;
	}

	// Helpermethod for calculating Drivetime
	public long calculateDriveTime(String depTime, String arrtime) {

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

	public int getDutynumberOfLines() {
		return dutynumberOfLines;
	}


}
