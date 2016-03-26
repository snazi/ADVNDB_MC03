/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author Angelo Amadora
 */
public class TimestampManager {
    private int currTime;
    private static TimestampManager tsm;
    
    private TimestampManager(int currTime){
        this.currTime = currTime;
    }
    
    public static synchronized TimestampManager getTimetampManager(){
        if(tsm == null){
            tsm = new TimestampManager(0);
            return tsm;
        }
        return tsm;
    }
    public synchronized int timeStamp(){
        currTime++;
        return currTime;
    }
    
    
}
