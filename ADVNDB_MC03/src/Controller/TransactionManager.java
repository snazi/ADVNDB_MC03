/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Transaction;
import java.util.ArrayList;

/**
 *
 * @author Angelo Amadora
 */
public class TransactionManager {
    
    //there must be only 1 instance of transation manager
    private static TransactionManager instance = null;
    private ArrayList<String> activeTransactions;
    private int timestamp;
    
    //give the manager a timestamp to give to its transactions and initialize
    //the arraylist of transactions to be taken note of
    public TransactionManager(int timestamp) {
        this.timestamp = timestamp;
        activeTransactions = new ArrayList<String>();
    }
    
    //if its a new instance of Transaction manager
    public static TransactionManager instance() {
	if( instance == null ) {
            instance = new TransactionManager(1);
	}

	return instance;
    }
    
    
    public static TransactionManager instance(int startTimestamp) {
	if( instance == null ) {
            instance = new TransactionManager(startTimestamp);
            } else {
                instance.setTimestamp(startTimestamp);
            }

	return instance;
	}
    
    private synchronized void setTimestamp(int timestamp) {
	this.timestamp = timestamp;
    }
    
    //register a transaction
    public synchronized void registerTransaction(Transaction t){
        t.setTimestamp(timestamp);
        timestamp++;
        activeTransactions.add(t.getTransactionId()+"");
    }
    
    public synchronized void unregisterTransaction(Transaction t){
        activeTransactions.remove(t.getTransactionId()+"");
        
    }
    
    //display all transactions
    public synchronized String displayTransactions() {
		String ret = "";
		for(int i = 0; i < activeTransactions.size(); i++) {
			if(i > 0) {
				ret += ",";
			}
			ret += activeTransactions.get(i);
		}
		return ret;
	}
}
