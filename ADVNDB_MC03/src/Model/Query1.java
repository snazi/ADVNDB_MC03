/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.IsolationLevels;
import Controller.LockManager;
import Controller.TransactionManager;

/**
 *
 * @author Angelo Amadora
 */
public class Query1 extends Transaction{

    public Query1(int transactionId, IsolationLevels iso) {
        super(transactionId);
        
        steps.add(new stepWriteLock(this, "hpq_something1"));
        // this creates steps that mimic an Isolation level 
        if(iso.getLevel() > IsolationLevels.readUncommited.getLevel()){
            steps.add(new stepReadLock(this, "hpq_something2"));
            steps.add(new stepReadLock(this, "hpq_something3"));
        }
        
        steps.add(new stepRead(null, new String[]{
            "hpq_something1"
        }));
        
        if(iso.getLevel() > IsolationLevels.readUncommited.getLevel()){
            steps.add(new stepUnlock(this, "hpq_something2"));
            steps.add(new stepUnlock(this, "hpq_something3"));
        }
        
        steps.add(new stepWrite(this, "hpq_something1"));
        steps.add(new stepCommit(this));
        steps.add(new stepUnlock(this, "hpq_something1"));
    }
    
    public void commit(){
        TransactionManager.instance().unregisterTransaction(this);
    }
    
    public void restart(){
        super.restart();
        LockManager.instance().unlock("hpq_something2", this);
        LockManager.instance().unlock("hpq_something3", this);
        LockManager.instance().unlock("hpq_something1", this);
    }
    
    public void rollback(){
        setStatus("finished");
    }
    
}
