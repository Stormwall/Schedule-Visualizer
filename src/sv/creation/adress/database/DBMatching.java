package sv.creation.adress.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sv.creation.adress.model.Block;
import sv.creation.adress.model.Blockelement;
import sv.creation.adress.model.Dienstplan;
import sv.creation.adress.model.Duty;
import sv.creation.adress.model.Dutyelement;
import sv.creation.adress.model.Umlaufplan;

/**
 * 
 * This class implements the sql queries which will sent to the database
 *
 */

public class DBMatching {
	 
    // **********************************************************************
    // ****** Array lists to create objects of the schedule elements  *******
    // ******                                                         *******
    // **********************************************************************
   

   
    ArrayList<Block> umlauf = new ArrayList<Block>();
    ArrayList<Blockelement> blockelement = new ArrayList<Blockelement>();
    ArrayList<Duty> duty = new ArrayList<Duty>();
    ArrayList<Dutyelement> dutyelement = new ArrayList<Dutyelement>();
    
    // ********************************
    // ****** Plan objects	    *******
    // ********************************
    
    Umlaufplan umlaufplan;
    Dienstplan dienstplan;

    //Object of a database statement
    Statement stmt;
    Statement stmt2;
    Statement stmt3;

    // **********************************************************************
    // ****** In this method an block object will be created          *******
    // ******                                                         *******
    // **********************************************************************
   
    public void createBlock() {

          DBConnection db = new DBConnection();
          db.initDBConnection();

          //Creating a sql query
          try {
                 stmt = db.getConnection().createStatement();

                 ResultSet rest1 = stmt
                               .executeQuery("SELECT  DISTINCT * FROM Block");

                 //All resulted datasets of the sql query will be added to the block array list
                 while (rest1.next()) {
                        int id = Integer.parseInt(rest1.getString("BlockID"));
                        int code = Integer.parseInt(rest1.getString("Code"));
                        String name = rest1.getString("Name");
                        umlauf.add(new Block(id, code, name));
                 }
          } catch (SQLException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
          }
    }

    // **********************************************************************
    // ****** In this method an blockelement object will be created   *******
    // ******                                                         *******
    // **********************************************************************
   
    public void createBlockelement() {
         
          DBConnection db = new DBConnection();
          db.initDBConnection();

          try {
        	  
        	  //Creating separated statements for the sql queries
                 stmt = db.getConnection().createStatement();
                 stmt2=db.getConnection().createStatement();
                 stmt3=db.getConnection().createStatement();

                 //Two different datasets will be created
                 ResultSet rest3 = stmt.executeQuery("SELECT * FROM Blockelement;");
                 ResultSet rest2;

                 // All resulted datasets of the sql query will be added to the
                 // blockelement array list

                 while (rest3.next()) { 
                        int zahl=Integer.parseInt(rest3.getString("ElementType"));
                        
                        //if the blockelement is a service journey the first sql query will be execute
                        if (zahl==1) {

                               rest2 = stmt2
                                            .executeQuery("SELECT be.ID,be.BlockID, be.ServiceJourneyID, sj.FromStopID, sj.ToStopID, sj.DepTime, sj.ArrTime,be.ElementType FROM Blockelement AS be, ServiceJourney AS sj WHERE sj.serviceJourneyID='"
                                                         + rest3.getString("ServiceJourneyID")+"' AND be.ID='"+rest3.getString("ID")+"' AND be.BlockID='"+rest3.getString("BlockID")+"';");
                        //if the read blockelement is a exceptional servie journey the second sql query will be execute
                        } else {
                               rest2 = stmt3
                                            .executeQuery("SELECT be.ID, be.BlockID, be.ServiceJourneyID, ex.FromStopID, ex.ToStopID, ex.DepTime, ex.ArrTime, be.ElementType FROM Blockelement AS be, ExceptionalBlockelement AS ex WHERE ex.ServiceJourneyID='"
                                                          + rest3.getString("ServiceJourneyID")+"' AND be.ID='"+rest3.getString("ID")+"' AND be.BlockID='"+rest3.getString("BlockID")+"';");
                        }
                       
                        //the attributes will be read and save in variables
                        int id = Integer.parseInt(rest2.getString("ID"));
                        int blockID = Integer.parseInt(rest2.getString("BlockID"));
                        String serviceJourneyID = rest3.getString("ServiceJourneyID");
                        int fromStopID = Integer
                                     .parseInt(rest2.getString("FromStopID"));
                        int toStopID = Integer.parseInt(rest2.getString("ToStopID"));
                        String depTime = rest2.getString("DepTime");
                        String arrTime = rest2.getString("ArrTime");
                        int elementType = Integer.parseInt(rest2
                                     .getString("ElementType"));
                        
                

                        //all variables will be sum up to an umlaufelement
                        blockelement.add(new Blockelement(id, blockID,
                                     serviceJourneyID, fromStopID, toStopID, depTime,
                                     arrTime, elementType));

                 }
          } catch (SQLException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
          }

    }

    // ***********************************************************************************************
    // ****** In this method both array lists will be integrated in an umlaufplan object.      *******
    // ****** This method returns an completely object of a umlaufplan, which can be modified. *******                                                                   *******
    // ***********************************************************************************************
   
    public void createUmlaufplanObject() {
    	
    	createBlock();
    	createBlockelement();
    	System.out.println("Umlaufplan Objekt erstellt");
    	
    	/**
    	 * WICHTIG!!!! Es muss noch die FahrplanID ausgelesen werden. DB Verknüpfung!!!!
    	 */
    	
          umlaufplan = new Umlaufplan(1, umlauf, blockelement);
          
          
    }
    
    // **********************************************************************
    // ****** In this method an duty object will be created          *******
    // ******                                                         *******
    // **********************************************************************
   
    public void createDuty() {

          DBConnection db = new DBConnection();
          db.initDBConnection();

          //Creating a sql query
          try {
                 stmt = db.getConnection().createStatement();

                 ResultSet rest1 = stmt
                               .executeQuery("SELECT  DISTINCT * FROM Duty");

                 //All resulted datasets of the sql query will be added to the block array list
                 while (rest1.next()) {
                        int id = Integer.parseInt(rest1.getString("DutyID"));
                        String type = rest1.getString("DutyType");
                        duty.add(new Duty(id, type));
                 }
          } catch (SQLException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
          }
    }
    
    // **********************************************************************
    // ****** In this method an blockelement object will be created   *******
    // ******                                                         *******
    // **********************************************************************
   
    public void createDutyelement() {
         
          DBConnection db = new DBConnection();
          db.initDBConnection();

          try {
        	  
        	  //Creating separated statements for the sql queries
                 stmt = db.getConnection().createStatement();
                 stmt2=db.getConnection().createStatement();
                 stmt3=db.getConnection().createStatement();

                 //Two different datasets will be created
                 ResultSet rest3 = stmt.executeQuery("SELECT * FROM Dutyelement;");
                 ResultSet rest2;

                 // All resulted datasets of the sql query will be added to the
                 // blockelement array list

                 while (rest3.next()) {
                        int zahl=Integer.parseInt(rest3.getString("ElementType"));
                        
                        //if the blockelement is a service journey the first sql query will be execute
                        if (zahl==1) {

                               rest2 = stmt2
                                            .executeQuery("SELECT de.ID, de.DutyID, de.BlockID, de.ServiceJourneyID, sj.FromStopID, sj.ToStopID, sj.DepTime, sj.ArrTime, de.elementType FROM Dutyelement AS de, ServiceJourney AS sj WHERE sj.serviceJourneyID='"
                                                         + rest3.getString("ServiceJourneyID")+ "'  AND de.ID='"+rest3.getString("ID")+"' AND de.BlockID='"+rest3.getString("BlockID")+"' AND de.DutyID='"+rest3.getString("DutyID")+"';");
                        //if the read blockelement is a exceptional servie journey the second sql query will be execute
                        } else {
                               rest2 = stmt3
                                            .executeQuery("SELECT de.ID, de.DutyID, de.BlockID, de.ServiceJourneyID, ex.FromStopID, ex.ToStopID, ex.DepTime, ex.ArrTime, de.ElementType FROM Dutyelement AS de, ExceptionalDutyelement AS ex WHERE ex.ServiceJourneyID='"
                                                          + rest3.getString("ServiceJourneyID")+"' AND de.ID='"+rest3.getString("ID")+"' AND de.BlockID='"+rest3.getString("BlockID")+"' AND de.DutyID='"+rest3.getString("DutyID")+"' AND de.ID=ex.DutyelementID;");
                        }
                       
                        //the attributes will be read and save in variables
                        int id = Integer.parseInt(rest2.getString("ID"));
                        String dutyID = rest2.getString("DutyID");
                        int blockID = Integer.parseInt(rest2.getString("BlockID"));
                        String serviceJourneyID = rest3.getString("ServiceJourneyID");
                        int fromStopID = Integer
                                     .parseInt(rest2.getString("FromStopID"));
                        int toStopID = Integer.parseInt(rest2.getString("ToStopID"));
                        String depTime = rest2.getString("DepTime");
                        String arrTime = rest2.getString("ArrTime");
                        int elementType = Integer.parseInt(rest2
                                     .getString("ElementType"));
                

                        //all variables will be sum up to an umlaufelement
                        dutyelement.add(new Dutyelement(id, dutyID, blockID,
                                     serviceJourneyID, fromStopID, toStopID, depTime,
                                     arrTime, elementType));

                     
                 }
          } catch (SQLException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
          }

    }
    
    // ***********************************************************************************************
    // ****** In this method both array lists will be integrated in an umlaufplan object.      *******
    // ****** This method returns an completely object of a umlaufplan, which can be modified. *******                                                                   *******
    // ***********************************************************************************************
   
    public void createDienstplanObject() {
    	
    	createDuty();
    	createDutyelement();
    	
    	/**
    	 * WICHTIG!!!! Es muss noch die FahrplanID ausgelesen werden. DB Verknüpfung!!!!
    	 */
          dienstplan = new Dienstplan(1, duty, dutyelement);
          
          System.out.println("Dienstplan objekt erstellt");
          
    }

    
    // *****************************
    // ****** Getter methods *******                                                                   
    // *****************************
    
	public Umlaufplan getUmlaufplan() {
		return umlaufplan;
	}
	
	public Dienstplan getDienstplan(){
		return dienstplan;
	}
    
    
}
