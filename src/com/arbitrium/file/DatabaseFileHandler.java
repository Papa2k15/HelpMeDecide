package com.arbitrium.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;

import com.arbitrium.decisions.Decision;
import com.arbitrium.lists.DecisionList;

public class DatabaseFileHandler {
	
	private static final String decisionFile = "decisiondb.txt";
	
///	private static final String iniFile = "ini.txt";
	
	private static final String dbPreamble = "DecisionDB v. 1.0";
	private Scanner reader;
	
	private PrintStream writer;
	
	private File fileHandler;
		
	public DatabaseFileHandler(){}
	
	public LinkedList<Decision> readDecisionFile() throws Exception{
		fileHandler = new File(decisionFile);
		reader = new Scanner(fileHandler);
		String preamble = reader.nextLine();
		if(!preamble.equals(dbPreamble)){
			throw new Exception("Invalid decision file.");
		}
		LinkedList<Decision> decisionList = new LinkedList<Decision>();
		while(reader.hasNextLine()){
			String line = reader.nextLine();
			String[] decisionParts = line.split("\\|");
			int ID = Integer.parseInt(decisionParts[0]);
			String title = decisionParts[1];
			String description = decisionParts[2];
			String dateCompleted = decisionParts[4];
			String completed = decisionParts[3];
			String timesPassed = decisionParts[5];
			Decision decision = new Decision(ID,title,description);
			if(completed.equals("0")){
				decision.setCompleted(false);
			} else {
				decision.setCompleted(true);
			}
			decision.setDateCompleted(dateCompleted);
			decision.setTimesPassed(Integer.parseInt(timesPassed));
			decisionList.add(decision);
		}
		reader.close();
		return decisionList;
	}
	
	public void writeDecisions(DecisionList decisionList) throws FileNotFoundException{
		fileHandler = new File(decisionFile);
		writer = new PrintStream(fileHandler);
		writer.println(dbPreamble);
		for(Decision d: decisionList){
			if(d.isCompleted()){
				writer.println(d.getID()+"|"+d.getTitle()+"|"+d.getDescription()+"|1|"+d.getDateCompleted()+"|"+d.getTimesPassed());
			} else {
				writer.println(d.getID()+"|"+d.getTitle()+"|"+d.getDescription()+"|0|"+d.getDateCompleted()+"|"+d.getTimesPassed());
			}
		}
		writer.close();
	}
	
}
