/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.LockManager;

/**
 *
 * @author Angelo Amadora
 */
public class stepReadLock implements TranStep{
    
    Transaction t;
    String item;

    public stepReadLock(Transaction t, String item) {
        this.t = t;
        this.item = item;
    }

    @Override
    public void execute() {
        LockManager.instance().readLock(item, t);
        
    }
    
    @Override
    public String toString(){
         return "rl( "+item+" )";
    }
    
}
