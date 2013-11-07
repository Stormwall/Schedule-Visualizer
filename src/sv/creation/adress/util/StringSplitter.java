package sv.creation.adress.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


/**
 *
 *Dies ist eine Klasse, die eine .txt-Datei zeilenweise einliest und in einer ArrayList speichert. 
 *Mit dieser ArrayList kˆnnen die Werte in die Datenbank importiert werden.
 *
 */

public class StringSplitter {
	
	private static ArrayList<String> stringList = new ArrayList<String>();

	public static ArrayList<String> convertStringToArraylist(String str) {     
		
		// "\\s*,\\s*" anstatt "," damit Leerzeichen vor und nach dem Komma im urspr¸nglichen String ignoriert werden.
		Collections.addAll(stringList, str.split("\\s*,\\s*"));
		
		System.out.println(stringList);
	    return stringList;
	}
	
	public static void readTxtData(){
		
		try {
			
			//test.txt Datei muss im Workspace im Projektordner liegen. 
			BufferedReader in = new BufferedReader(new FileReader("test.txt"));
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				System.out.println("Gelesene Zeile: " + zeile);
				convertStringToArraylist(zeile);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}