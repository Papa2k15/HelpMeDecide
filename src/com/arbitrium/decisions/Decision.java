package com.arbitrium.decisions;

/**
 * Decision class is the minimal object that holds everything for
 * making a simple decision.
 * 
 * @author Gregory Daniels
 * @version 1.0
 */
public class Decision {
	
	private int ID;
	
	private String title;
	
	private String description;
	
	private String dateCompleted;
	
	private boolean completed;
	
	private int timesPassed;
	
	public Decision(int ID, String title, String description){
		this.ID = ID;
		this.title = title;
		this.description = description;
		completed = false;
		timesPassed = 0;
	}
	
	public int getID(){
		return ID;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void updateTitle(String title){
		this.title = title;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void updateDescription(String description){
		this.description = description;
	}
	
	public String getDateCompleted(){
		if(!completed){
			dateCompleted = "NAN";
		}
		return dateCompleted;
	}

	public void setDateCompleted(String dateCompleted){
		if(completed){
			this.dateCompleted = dateCompleted;
		}
	}
	
	public boolean isCompleted(){
		return completed;
	}
	
	public void setCompleted(boolean completed){
		this.completed = completed;
	}
	
	public int getTimesPassed(){
		return timesPassed;
	}
	
	public void setTimesPassed(int passed){
		timesPassed = passed;
	}
	
	public void pass(){
		timesPassed++;
	}
}
