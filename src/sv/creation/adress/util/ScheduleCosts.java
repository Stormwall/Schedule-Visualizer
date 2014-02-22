package sv.creation.adress.util;

import java.util.ArrayList;

import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Umlaufplan;

public class ScheduleCosts {
private double fixedschedulecosts = 0;
private double variableschedulecosts = 0;
private double schedulecosts = 0;

public ArrayList<ScheduleCosts> calculateCrewScheduleCosts(ArrayList<Dienstplan> dp, double Kf, double kvhour){
	
	ArrayList<ScheduleCosts> scostslist = new ArrayList<ScheduleCosts>();
	
	//calculate runtimes using schedule statistcs class
			ScheduleStatistics stat = new ScheduleStatistics();
			ArrayList<ScheduleStatistics> sstats = new ArrayList<ScheduleStatistics>();
			sstats = stat.calculateCrewScheduleStatistics(dp);

	for(int i = 0; i < sstats.size();i++){
		ScheduleCosts scosts = new ScheduleCosts();
		scosts.fixedschedulecosts = Kf;
		scosts.variableschedulecosts = kvhour * (sstats.get(i).getTotalRunTime()/60/60);
		scosts.schedulecosts = scosts.fixedschedulecosts+scosts.variableschedulecosts;
		scostslist.add(scosts);
	}
	return scostslist;
}

public ArrayList<ScheduleCosts> calculateVehicleScheduleCosts(ArrayList<Umlaufplan> up, double Kf, double kvhour){
	
	ArrayList<ScheduleCosts> scostslist = new ArrayList<ScheduleCosts>();
	
	//calculate runtimes using schedule statistcs class
			ScheduleStatistics stat = new ScheduleStatistics();
			ArrayList<ScheduleStatistics> sstats = new ArrayList<ScheduleStatistics>();
			sstats = stat.calculateVehicleScheduleStatistics(up);

	for(int i = 0; i < sstats.size();i++){
		ScheduleCosts scosts = new ScheduleCosts();
		scosts.fixedschedulecosts = Kf;
		scosts.variableschedulecosts = kvhour * ((sstats.get(i).getOveralldurationServiceTrips()+sstats.get(i).getOveralldurationDeadHeads()+sstats.get(i).getOveralldurationPullOuts()+sstats.get(i).getOveralldurationPullIns())/60/60);
		scosts.schedulecosts = scosts.fixedschedulecosts+scosts.variableschedulecosts;
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

}
