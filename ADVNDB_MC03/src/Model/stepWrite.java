/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Angelo Amadora
 */
public class stepWrite implements TranStep{
    
    Transaction t;
    String item;

    public stepWrite(Transaction t, String item) {
        this.t = t;
        this.item = item;
    }
    
    
    @Override
    public void execute() {
       //do something :))
    }
    
    @Override
     public String toString(){
        return "w( "+item+" )";
    }
    
}
