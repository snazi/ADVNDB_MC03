package View;

import Controller.IsolationLevels;
import Model.Query1;
import Model.Query2;
import Model.TranStep;
import Model.Transaction;
import Model.stepBegin;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Angelo Amadora
 */
public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        final ArrayList<Transaction> tranList = new ArrayList<Transaction>();
	tranList.add(new Query2(1,IsolationLevels.readCommited));
	tranList.add(new Query1(2,IsolationLevels.readUncommited));
	tranList.add(new Query1(3,IsolationLevels.readCommited));
	tranList.add(new Query2(4,IsolationLevels.readUncommited));

	boolean finished;
        
        do {
            String[][] transactions = new String[tranList.size()][];
            int max = 0;
            for(int i = 0; i < tranList.size(); i++) {
                transactions[i] = tranList.get(i).toString().split("\n");
            
                if( transactions[i].length > max ) {
                    max = transactions[i].length;
                }
            }
            
            for(int i = 0; i < max; i++ ) {
                for(int j = 0; j < transactions.length; j++ ) {
                    if( j > 0 ) {
                        System.out.print("\t");
                    }
                    
                    if( i < transactions[j].length) {
                        System.out.print(transactions[j][i]);
                    } else {
                        System.out.print("\t\t");
                    }
		}
                
                System.out.println();
            }

            Scanner sc = new Scanner(System.in);
            System.out.print("Which transaction? ");
            final int tNo = sc.nextInt() - 1;

            (new Thread(){
                public void run() {
                    tranList.get(tNo).step();
		}
		}).start();
		Thread.sleep(100);

            finished = true;
            for(Transaction t : tranList ) {
                finished = finished && t.isFinished();
            }
	} while(!finished);
    }
    
}
