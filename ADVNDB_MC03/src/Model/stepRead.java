    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.ResultSet;

/**
 *
 * @author Angelo Amadora
 */
public class stepRead implements TranStep{
    String query;
    String[] items;
    ResultSet rs;
   

    public stepRead(String query, String[] items) {
        this.query = query;
        this.items = items;
    }
    
    @Override
    public void execute() {
        //insert sql codes here and it'll return the result set since read lang naman
    }
    
    public ResultSet getResultSet(){
        return rs;
    }
    
     @Override
    	public String toString() {
		String ret = "";
		for( int i = 0; i < items.length; i++ ) {
			if( i > 0 ) {
				ret += ";";
			}
			ret += "read(" + items[i] + ")";
		}
		return ret;
	}
    
}
