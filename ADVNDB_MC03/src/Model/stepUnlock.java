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
public class stepUnlock implements TranStep{
    Transaction t;
    String item;

    public stepUnlock(Transaction t, String item) {
        this.t = t;
        this.item = item;
    }
    
    @Override
    public void execute() {
        LockManager.instance().unlock(item, t);
    }
    
    public String toString(){
        return "UNLOCK( "+item+" )";
    }
    
}
