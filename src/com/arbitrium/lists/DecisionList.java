package com.arbitrium.lists;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;

import com.arbitrium.decisions.Decision;
import com.arbitrium.file.DatabaseFileHandler;

public class DecisionList extends LinkedList<Decision>{

	private static final long serialVersionUID = 1L;
	
	private final DatabaseFileHandler dbFileHandle;
	
	public DecisionList() throws Exception{
		dbFileHandle = new DatabaseFileHandler();
		for(Decision d: dbFileHandle.readDecisionFile()){
			this.add(d);
		}
	}
	
	public Decision getDecisionWithID(int ID) {
		if(containsID(ID)){
			for(Decision d: this){
				if(d.getID() == ID){
					return d;
				}
			}
		} 
		return null;
	}

	public boolean removeDecisionWithID(int ID) {
		for(Decision d: this){
			if(d.getID() == ID){
				if(remove(d)){
					return true;
				} 
			} 
		}
		return false;
	}

	public boolean containsID(int ID){
		for(Decision d: this){
			if(ID == d.getID()){
				return true;
			}
		}
		return false;
	}

	public int getUniqueID(){
		return getLast().getID()+1;
	}
	
	public void updateDatabase() throws FileNotFoundException{
		dbFileHandle.writeDecisions(this);
	}
	
	public boolean checkAllCompleted(){
		for(Decision d: this){
			if(!d.isCompleted()){
				return false;
			}
		}
		return true;
	}

	public boolean updateDecision(int oldID, Decision d) {
		if(containsID(oldID)){
			for(Decision decision: this){
				if(decision.getID() == oldID){
					decision.updateTitle(d.getTitle());
					decision.updateDescription(d.getDescription());
					decision.setCompleted(d.isCompleted());
					decision.setDateCompleted(d.getDateCompleted());
					decision.setTimesPassed(d.getTimesPassed());
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}
	
	public ArrayList<Integer> getIDList(){
		ArrayList<Integer> idList = new ArrayList<Integer>();
		for(Decision d : this){
			idList.add(d.getID());
		}
		return idList;
	}
}
