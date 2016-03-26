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
public enum IsolationLevels {
    readUncommited(1),readCommited(2),readRepeatable(3),serializable(4);
    
    private final int level;
    
    IsolationLevels(int level){
        this.level = level;
    }
    
    public int getLevel(){
        return level;
    }
}
