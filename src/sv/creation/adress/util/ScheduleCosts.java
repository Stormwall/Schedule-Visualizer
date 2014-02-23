package sv.creation.adress.util;

import java.util.ArrayList;

import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Fahrplan;
import sv.creation.adress.model.Umlaufplan;

public class ScheduleCosts {
	private double fixedschedulecosts = 0;
	private double variableschedulecosts = 0;
	private double schedulecosts = 0;

	public ArrayList<ScheduleCosts> calculateCrewScheduleCosts(
			ArrayList<Dienstplan> dp, Fahrplan fp, double Kf, double kvhour) {

		ArrayList<ScheduleCosts> scostslist = new ArrayList<ScheduleCosts>();

		// calculate runtimes using schedule statistcs class
		ScheduleStatistics stat = new ScheduleStatistics();
		ArrayList<ScheduleStatistics> sstats = new ArrayList<ScheduleStatistics>();
		sstats = stat.calculateCrewScheduleStatistics(dp,fp);

		for (int i = 0; i < sstats.size(); i++) {
			ScheduleCosts scosts = new ScheduleCosts();
			scosts.fixedschedulecosts = Kf;
			scosts.variableschedulecosts = kvhour
					* (sstats.get(i).getTotalRunTime() / 60 / 60);
			scosts.schedulecosts = scosts.fixedschedulecosts
					+ scosts.variableschedulecosts;
			scostslist.add(scosts);
		}
		return scostslist;
	}

	public ArrayList<ScheduleCosts> calculateVehicleScheduleCosts(
			ArrayList<Umlaufplan> up, Fahrplan fp, double Kf, double kvhour) {

		ArrayList<ScheduleCosts> scostslist = new ArrayList<ScheduleCosts>();

		// calculate runtimes using schedule statistcs class
		ScheduleStatistics stat = new ScheduleStatistics();
		ArrayList<ScheduleStatistics> sstats = new ArrayList<ScheduleStatistics>();
		sstats = stat.calculateVehicleScheduleStatistics(up,fp);

		for (int i = 0; i < sstats.size(); i++) {
			ScheduleCosts scosts = new ScheduleCosts();
			scosts.fixedschedulecosts = Kf;
			scosts.variableschedulecosts = kvhour
					* ((sstats.get(i).getOveralldurationServiceTrips()
							+ sstats.get(i).getOveralldurationDeadHeads()
							+ sstats.get(i).getOveralldurationPullOuts() + sstats
							.get(i).getOveralldurationPullIns()) / 60 / 60);
			scosts.schedulecosts = scosts.fixedschedulecosts
					+ scosts.variableschedulecosts;
			scostslist.add(scosts);
		}
		return scostslist;
	}

	public double getFixedschedulecosts() {
		return fixedschedulecosts;
	}

	public double getVariableschedulecosts() {
		return variableschedulecosts;
	}

	public double getSchedulecosts() {
		return schedulecosts;
	}

	public void setSchedulecosts(double schedulecosts) {
		this.schedulecosts = schedulecosts;
	}

	public void setFixedschedulecosts(double fixedschedulecosts) {
		this.fixedschedulecosts = fixedschedulecosts;
	}

	public void setVariableschedulecosts(double variableschedulecosts) {
		this.variableschedulecosts = variableschedulecosts;
	}

}
