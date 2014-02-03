package sv.creation.adress.util;

import java.io.File;

import sv.creation.adress.database.DBConnection;
import sv.creation.adress.exception.WrongTxtFileException;

public class Import {
	
	public void importFile(File file){
		
		 DBConnection dbc =new DBConnection();
		 StringSplitter ss=new StringSplitter();
		 
		 
		 if(file.getName().startsWith("vs")){
			 ss.readTxtUmlaufplan(file.getPath());
			 dbc.fillUmlaufplanIntoTables(ss);
		 }else if(file.getName().startsWith("cs")){
			 ss.readTxtDienstplan(file.getPath());
			 dbc.fillDienstplanIntoTable();
		 }else if(file.getName().startsWith("sc")){
			 ss.readTxtFromSzenario(file.getPath());
			 dbc.fillSzenarioIntoTables();
		 }else if(file.getName().startsWith("dt")){
			 ss.readTxtDiensttypen(file.getPath());
			 dbc.fillDiensttypenIntoTables();
		 }else{
			 ss.readTxtFahrplan(file.getPath());
			 dbc.fillFahrplanIntoTables(ss);
		 }
		 
	}

}
