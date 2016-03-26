/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.TimestampManager;

/**
 *
 * @author Angelo Amadora
 */
public class stepBegin implements TranStep {
    Transaction t;

    public stepBegin(Transaction t) {
        this.t = t;
    }
    
    @Override
    public void execute() {
        t.setTimestamp(TimestampManager.getTimetampManager().timeStamp());
        t.started = "started";
    }
    
    @Override
    public String toString(){
        return "BEGIN()";
    }
    
}
