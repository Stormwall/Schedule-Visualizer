package sv.creation.adress.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sv.creation.adress.model.Umlaufplan;

public class BlockStatistics {
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
	double blockserviceTimetotalBlockTimeRatio = 0;
	
	double blockoveralldurationServicetrips = 0;
	double blockoveralldurationDeadheads = 0;
	double blockoveralldurationWaitings = 0;
	double blockoveralldurationPullouts = 0;
	double blockoveralldurationPullins = 0;
	/****************************************************************************
	method for calculating statistics for all duties contained in a crew schedule
	*****************************************************************************/
	public ArrayList<BlockStatistics> calculateBlockStatistics(Umlaufplan up){

		
		ArrayList<BlockStatistics> blockstatlist = new ArrayList<BlockStatistics>();
		for (int block = 0; block < up.getUmlauf().size(); block++){
			

			int blockID = up.getUmlauf().get(block).getId();
			
			BlockStatistics blockstat = new BlockStatistics();
			for (int blockelement = 0; blockelement < up.getFahrtZuUmlauf().size(); blockelement++){
				
				
				
					if(up.getFahrtZuUmlauf().get(blockelement).getBlockID()==blockID){
					
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
						case 9:
							//Waitings
							blockstat.blocknumberOfWaitings++;
							blockstat.blockoveralldurationWaitings = blockstat.blockoveralldurationWaitings + runtime;
							break;
						}
				}//if
					
				
				
				
			}//for dutyelement
			if (blockstat.blocknumberOfServiceTrips != 0) {
				blockstat.blockaverageLengthOfServiceTrips = blockstat.blockoveralldurationServicetrips
						/ blockstat.blocknumberOfServiceTrips;
			} else {
				blockstat.blockaverageLengthOfServiceTrips = 0;
			}
			if (blockstat.blocknumberOfDeadheads != 0) {
				blockstat.blockaverageLengthOfDeadheads = blockstat.blockoveralldurationDeadheads
						/ blockstat.blocknumberOfDeadheads;
			} else {
				blockstat.blocknumberOfDeadheads = 0;
			}
			if (blockstat.blocknumberOfWaitings != 0) {
				blockstat.blockaverageLengthOfWaitings = blockstat.blockoveralldurationWaitings
						/ blockstat.blocknumberOfWaitings;
			} else {
				blockstat.blocknumberOfWaitings = 0;
			}
			if (blockstat.blocknumberOfPullOuts != 0) {
				blockstat.blockaverageLengthOfPullOuts = blockstat.blockoveralldurationPullouts
						/ blockstat.blocknumberOfPullOuts;
			} else {
				blockstat.blocknumberOfPullOuts = 0;
			}
			if (blockstat.blocknumberOfPullIns != 0) {
				blockstat.blockaverageLengthOfPullIns = blockstat.blockoveralldurationPullins
						/ blockstat.blocknumberOfPullIns;
			} else {
				blockstat.blocknumberOfPullIns = 0;
			}

			if (blockstat.blocktotalRunTime != 0) {
				blockstat.blockserviceTimetotalBlockTimeRatio = blockstat.blockoveralldurationServicetrips
						/ blockstat.blocktotalRunTime;
			} else {
				blockstat.blockserviceTimetotalBlockTimeRatio = 0;	
			}
			
			blockstatlist.add(blockstat);
				
		}//for duty
		
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
}
