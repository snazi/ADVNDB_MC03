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
public class stepCommit implements TranStep{
    
    Transaction t;
    @Override
    
    
    public void execute() {
        t.commit();
    }

    public stepCommit(Transaction t) {
        this.t = t;
    }
    
    @Override
    public String toString(){
        return "COMMIT";
    }
}
