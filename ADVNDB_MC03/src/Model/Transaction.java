/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.TransactionManager;
import java.util.ArrayList;

/**
 *
 * @author Angelo Amadora
 */
public abstract class Transaction {
    private int transactionId;
    private int timestamp;
    private int currStep;
    private String status;
    ArrayList<TranStep> steps;
    String waiting = "not waiting";
    String started = "not started";
    String commited = "not commited";
    String rollback = "not rollback";
    
    public Transaction(int transactionId) {
        this.transactionId = transactionId;
        this.currStep = 0;
        status = "unstarted";
        steps = new ArrayList<TranStep>();
        steps.add(new stepBegin(this));
        
    }

    public int getTransactionId() {
        return transactionId;
    }
    
    
    
    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
    
    
    
    public int getCurrStep() {
        return currStep;
    }

    public void setCurrStep(int currStep) {
        this.currStep = currStep;
    }

    public ArrayList<TranStep> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<TranStep> steps) {
        this.steps = steps; 
    }
    
    public void begin(){
        TransactionManager.instance().registerTransaction(this);
        status = "started";
    }
    
    //execute a step
    public void step(){
        if(!isFinished () && status != "waiting"){
            TranStep ts = steps.get(getCurrStep());
            ts.execute();
            
            if(status != "waiting"){
                currStep++;
            }
            
            if(currStep ==  steps.size()){
                status = "finished";
            }
        }
    }
    
    public String getStatus(){
        return status+" ";
    }
    
    public TranStep getStepAt(int step){
        return steps.get(step);
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    //will return null if it ISNT commiting
    public boolean isCommiting(){
        return steps.get(currStep) instanceof stepCommit;
    }
    
    public void restart(){
        status = "started";
        currStep =1;
    }
    
    public int getTimestamp(){
        return timestamp;
    }
    
    public boolean isFinished(){
        if(status.equals("finished")){
            return true;
        }
        else
            return false;
    }
    
    public String toString(){
        String temp = status + " Transaction: " + transactionId + "\nTimestamp: "
                + timestamp+"\n";
        
        for(int i = 0; i <steps.size();i++){
            if(i > 0 ){
                temp += "\n";
            }
            
            if(i == currStep){
                temp+="> ";
            }
            
            temp+= steps.get(i);
        }
        return temp;
    }
    
    public void commit(){
        
    }
}