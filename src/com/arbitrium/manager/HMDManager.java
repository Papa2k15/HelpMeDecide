package com.arbitrium.manager;

import java.io.FileNotFoundException;
import java.util.Random;

import com.arbitrium.decisions.Decision;
import com.arbitrium.lists.DecisionList;

public class HMDManager {
	
	private DecisionList decisionList;
	private Random randomGen = new Random();

	private static HMDManager managerInstance;
	
	public static HMDManager getManagerInstance() throws Exception{
		if(managerInstance == null){
			managerInstance = new HMDManager();
		}
		return managerInstance;
	}
	
	private HMDManager() throws Exception{
		decisionList = new DecisionList();
	}
	
	public boolean newDecision(Decision d){
		return decisionList.add(d);
	}
	
	public boolean updateDecision(int oldID, Decision d){
		return decisionList.updateDecision(oldID,d);
	}
	
	public Decision getDecision(int ID) throws Exception{
		return decisionList.getDecisionWithID(ID);
	}
	
	public DecisionList getAllDecisions(){
		return decisionList;
	}
	
	public Object[][] getAllDecisionsMultiDimensionArray(){
		Object[][] data = new Object[decisionList.size()][3];
		for(int i = 0; i < data.length; i++){
			Decision currentDecision = decisionList.get(i);
			for(int j = 0; j < 3; j++){
				if(j == 0){
					data[i][j] = currentDecision.getID();
				} else if (j ==1) {
					data[i][j] = currentDecision.getTitle();
				} else {
					data[i][j] = currentDecision.getDescription();
				}
			}
		}
		return data;
	}
	
	public boolean removeDecision(int ID) {
		return decisionList.removeDecisionWithID(ID);
	}
	
	public int getUniqueID(){
		return decisionList.getUniqueID();
	}
	
	public void saveDecisions() throws FileNotFoundException{
		decisionList.updateDatabase();
	}
	
	public Decision askHMD() {
		if(!decisionList.checkAllCompleted()){
			boolean found = false;
			while(!found){
				int currentID = randomGen.nextInt(decisionList.getIDList().size());
				Decision get = decisionList.getDecisionWithID(decisionList.getIDList().get(currentID));
				if(get != null){
					if(get.isCompleted()){
						continue;
					} else {
						return get;
					}
				} else {
					continue;
				}
			}
		} 
		return null;
	}
	
	public Decision askHMDCompleted() {
		return decisionList.getDecisionWithID(decisionList.getIDList().get(randomGen.nextInt(decisionList.getIDList().size())));
	}
}
