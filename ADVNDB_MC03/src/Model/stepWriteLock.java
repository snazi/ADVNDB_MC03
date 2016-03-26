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
public class stepWriteLock implements TranStep{
    Transaction t;
    String item;

    public stepWriteLock(Transaction t, String item) {
        this.t = t;
        this.item = item;
    }
    
    @Override
    public void execute() {
        LockManager.instance().writeLock(item, t);
    }
    
    @Override
    public String toString(){
        return "wl( "+ item+" )";
    }
}
