package sv.creation.adress.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sv.creation.adress.model.Umlaufplan;

public class BlockStatistics {
	int blockID;
	double blocktotalNumberOfTrips = 0;
	double blocktotalRunTime = 0;
	double blocknumberOfServiceTrips = 0;
	double blockaverageLengthOfServiceTrips = 0;
	double blocknumberOfDeadheads = 0;
	double blockaverageLengthOfDeadheads = 0;
	double blocknumberOfWaitings = 0;
	double blockaverageLengthOfWaitings = 0;
	double blocknumberOfPullOuts = 0;
	double blockaverageLengthOfPullOuts = 0;
	double blocknumberOfPullIns = 0;
	double blockaverageLengthOfPullIns = 0;
	//int blocknumberOfLines = 0;
	
	double blocknumberOfPreparations = 0;
	double blockaverageLengthOfPreparations = 0;
	double blocknumberOfWrapUps = 0;
	double blockaverageLengthOfWrapUps = 0;
	double blocknumberOfLayovers = 0;
	double blockaverageLengthOfLayovers = 0;

	
	double blockserviceTimetotalBlockTimeRatio = 0;
	
	double blockoveralldurationServicetrips = 0;
	double blockoveralldurationDeadheads = 0;
	double blockoveralldurationWaitings = 0;
	double blockoveralldurationPullouts = 0;
	double blockoveralldurationPullins = 0;
	
	double blockoveralldurationPreparations = 0;
	double blockoveralldurationWrapUps = 0;
	double blockoveralldurationLayovers = 0;
	/****************************************************************************
	method for calculating statistics for all duties contained in a crew schedule
	*****************************************************************************/
	public ArrayList<BlockStatistics> calculateBlockStatistics(Umlaufplan up){

		
		ArrayList<BlockStatistics> blockstatlist = new ArrayList<BlockStatistics>();
		for (int block = 0; block < up.getUmlauf().size(); block++){
			

			blockID = up.getUmlauf().get(block).getId();
						
			BlockStatistics blockstat = new BlockStatistics();
			for (int blockelement = 0; blockelement < up.getFahrtZuUmlauf().size(); blockelement++){
				
				
				
					if(up.getFahrtZuUmlauf().get(blockelement).getBlockID()==blockID){
						blockstat.blockID = blockID;
						long runtime = blockstat.calculateDriveTime(up.getFahrtZuUmlauf().get(blockelement).getDepTime(), up.getFahrtZuUmlauf().get(blockelement).getArrTime());
								
						blockstat.blocktotalNumberOfTrips++;
						blockstat.blocktotalRunTime = blockstat.blocktotalRunTime + runtime;
						switch (up.getFahrtZuUmlauf().get(blockelement).getElementType()){
						case 1:
							//Service Trip
							blockstat.blocknumberOfServiceTrips++;
							blockstat.blockoveralldurationServicetrips = blockstat.blockoveralldurationServicetrips + runtime;
							break;
						case 2:
							//Deadheading
							blockstat.blocknumberOfDeadheads++;
							blockstat.blockoveralldurationDeadheads = blockstat.blockoveralldurationDeadheads + runtime;
							break;
						case 3:
							//Pullins
							blockstat.blocknumberOfPullIns++;
							blockstat.blockoveralldurationPullins = blockstat.blockoveralldurationPullins + runtime;
							break;
						case 4:
							//Pullout
							blockstat.blocknumberOfPullOuts++;
							blockstat.blockoveralldurationPullouts = blockstat.blockoveralldurationPullouts + runtime;
							break;
						case 5:
							//Preparation
							blockstat.blocknumberOfPreparations++;
							blockstat.blockoveralldurationPreparations = blockstat.blockoveralldurationPreparations + runtime;
							break;
						case 6:
							//Wrap-Ups
							blockstat.blocknumberOfWrapUps++;
							blockstat.blockoveralldurationWrapUps = blockstat.blockoveralldurationWrapUps + runtime;
							break;
						case 9:
							//Waiting
							blockstat.blocknumberOfWaitings++;
							blockstat.blockoveralldurationWaitings = blockstat.blockoveralldurationWaitings + runtime;
							break;
						case 10:
							//Layover
							blockstat.blocknumberOfLayovers++;
							blockstat.blockoveralldurationLayovers = blockstat.blockoveralldurationLayovers + runtime;
							break;
						}
				}
					
			}
			//Elementtypes
			if (blockstat.blocknumberOfServiceTrips != 0) {
				blockstat.blockaverageLengthOfServiceTrips = blockstat.blockoveralldurationServicetrips
						/ blockstat.blocknumberOfServiceTrips;
			} else {
				blockstat.blockaverageLengthOfServiceTrips = 0;
			}
			//DH
			if (blockstat.blocknumberOfDeadheads != 0) {
				blockstat.blockaverageLengthOfDeadheads = blockstat.blockoveralldurationDeadheads
						/ blockstat.blocknumberOfDeadheads;
			} else {
				blockstat.blockaverageLengthOfDeadheads = 0;
			}
			//PO
			if (blockstat.blocknumberOfPullOuts != 0) {
				blockstat.blockaverageLengthOfPullOuts = blockstat.blockoveralldurationPullouts
						/ blockstat.blocknumberOfPullOuts;
			} else {
				blockstat.blockaverageLengthOfPullOuts = 0;
			}
			//PI
			if (blockstat.blocknumberOfPullIns != 0) {
				blockstat.blockaverageLengthOfPullIns = blockstat.blockoveralldurationPullins
						/ blockstat.blocknumberOfPullIns;
			} else {
				blockstat.blockaverageLengthOfPullIns = 0;
			}
			//Preps
			if (blockstat.blocknumberOfPreparations != 0) {
				blockstat.blockaverageLengthOfPreparations = blockstat.blockoveralldurationPreparations
						/ blockstat.blocknumberOfPreparations;
			} else {
				blockstat.blockaverageLengthOfPreparations = 0;
			}
			//Wraps
			if (blockstat.blocknumberOfWrapUps != 0) {
				blockstat.blockaverageLengthOfWrapUps = blockstat.blockoveralldurationWrapUps
						/ blockstat.blocknumberOfWrapUps;
			} else {
				blockstat.blockaverageLengthOfWrapUps = 0;
			}
			//Waiting
			if (blockstat.blocknumberOfWaitings != 0) {
				blockstat.blockaverageLengthOfWaitings = blockstat.blockoveralldurationWaitings
						/ blockstat.blocknumberOfWaitings;
			} else {
				blockstat.blockaverageLengthOfWaitings = 0;
			}
			//Layover
			if (blockstat.blocknumberOfLayovers != 0) {
				blockstat.blockaverageLengthOfLayovers = blockstat.blockoveralldurationLayovers
						/ blockstat.blocknumberOfLayovers;
			} else {
				blockstat.blockaverageLengthOfLayovers = 0;
			}
			//Ratio
			if (blockstat.blocktotalRunTime != 0) {
				blockstat.blockserviceTimetotalBlockTimeRatio = blockstat.blockoveralldurationServicetrips
						/ blockstat.blocktotalRunTime;
			} else {
				blockstat.blockserviceTimetotalBlockTimeRatio = 0;	
			}
			
			blockstatlist.add(blockstat);
				
		}//
		
		return blockstatlist;
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
	

	public int getBlockID() {
		return blockID;
	}

	public double getBlocktotalNumberOfTrips() {
		return blocktotalNumberOfTrips;
	}

	public double getBlocktotalRunTime() {
		return blocktotalRunTime;
	}

	public double getBlocknumberOfServiceTrips() {
		return blocknumberOfServiceTrips;
	}

	public double getBlockaverageLengthOfServiceTrips() {
		return blockaverageLengthOfServiceTrips;
	}

	public double getBlocknumberOfDeadheads() {
		return blocknumberOfDeadheads;
	}

	public double getBlockaverageLengthOfDeadheads() {
		return blockaverageLengthOfDeadheads;
	}

	public double getBlocknumberOfWaitings() {
		return blocknumberOfWaitings;
	}

	public double getBlockaverageLengthOfWaitings() {
		return blockaverageLengthOfWaitings;
	}

	public double getBlocknumberOfPullOuts() {
		return blocknumberOfPullOuts;
	}

	public double getBlockaverageLengthOfPullOuts() {
		return blockaverageLengthOfPullOuts;
	}

	public double getBlocknumberOfPullIns() {
		return blocknumberOfPullIns;
	}

	public double getBlockaverageLengthOfPullIns() {
		return blockaverageLengthOfPullIns;
	}

	public double getBlocknumberOfPreparations() {
		return blocknumberOfPreparations;
	}

	public double getBlockaverageLengthOfPreparations() {
		return blockaverageLengthOfPreparations;
	}

	public double getBlocknumberOfWrapUps() {
		return blocknumberOfWrapUps;
	}

	public double getBlockaverageLengthOfWrapUps() {
		return blockaverageLengthOfWrapUps;
	}

	public double getBlocknumberOfLayovers() {
		return blocknumberOfLayovers;
	}

	public double getBlockaverageLengthOfLayovers() {
		return blockaverageLengthOfLayovers;
	}

	public double getBlockserviceTimetotalBlockTimeRatio() {
		return blockserviceTimetotalBlockTimeRatio;
	}

	public double getBlockoveralldurationServicetrips() {
		return blockoveralldurationServicetrips;
	}

	public double getBlockoveralldurationDeadheads() {
		return blockoveralldurationDeadheads;
	}

	public double getBlockoveralldurationWaitings() {
		return blockoveralldurationWaitings;
	}

	public double getBlockoveralldurationPullouts() {
		return blockoveralldurationPullouts;
	}

	public double getBlockoveralldurationPullins() {
		return blockoveralldurationPullins;
	}

	public double getBlockoveralldurationPreparations() {
		return blockoveralldurationPreparations;
	}

	public double getBlockoveralldurationWrapUps() {
		return blockoveralldurationWrapUps;
	}

	public double getBlockoveralldurationLayovers() {
		return blockoveralldurationLayovers;
	}
}
