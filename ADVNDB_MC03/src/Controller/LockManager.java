/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Transaction;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Angelo Amadora
 */
public class LockManager {
    
    public static LockManager instance = null;
    ArrayList<lock> transactions;
    HashMap<String,lock> locks;

    public LockManager(ArrayList<lock> transactions, HashMap<String, lock> locks) {
        this.transactions = transactions;
        this.locks = locks;
    }
    
    public LockManager(){
        locks = new HashMap<String,lock>();
    }
    
    public synchronized static LockManager instance(){
        if(instance == null){
            instance = new LockManager();
        }
        
        return instance;
    }
    
    public void readLock(String item, Transaction t){
        lock l = locks.get(item);
        // this implements the wound - wait locking
        if(l == null){
            l = new lock();
            l.readLock(t);
            locks.put(item, l);
        }else{
            while(l.isWriter()){
                //wound part
                if(t.getTimestamp() < l.getHead().getTimestamp()){
                    l.getHead().restart();
                    continue;
                }
                
                try{
                    t.setStatus("waiting");
                    wait();
                    t.setStatus("running");
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            l.readLock(t);
        }
            
    }
    
    public void writeLock(String item, Transaction t){
         lock l = locks.get(item);
        
        if(l == null){
            l = new lock();
            l.writeLock(t);
            locks.put(item, l);
        }else{ 
            while(l.isWriter()||l.getReaders()>0){
            //wounding
                if(t.getTimestamp() < l.getHead().getTimestamp()){
                    l.getHead().restart();
                    continue;
                }
                try{
                    t.setStatus("waiting");
                    wait();
                    t.setStatus("running");
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            l.writeLock(t);
        }
    }
    
    public synchronized void unlock(String item, Transaction t){
        lock l = locks.get(item);
        
        if(l != null){
            l.unlock(t);
        }else{
            if(l.isWriter()){
                l.setIsWriter(false);
            }else{
                l.removeReader(t);
            }
            
            notifyAll();
        }
        
//        l.unlock(t);
//        
//        locks.remove(item);
    }

    

    public ArrayList<lock> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<lock> transactions) {
        this.transactions = transactions;
    }
    
    //class of locks
    public class lock{
        
        private boolean writer = false;
        private int readers = 0;
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();

        public lock() {
        }
        
        public boolean isWriter() {
            return writer;
        }

        public int getReaders() {
            return readers;
        }

        public ArrayList<Transaction> getTransactions() {
            return transactions;
        }
        
        public void readLock(Transaction t){
            readers++;
            transactions.add(t);
        }
        
        public void writeLock(Transaction t){
            this.writer = true;
            transactions.add(t);
        }
        
        public void setIsWriter(boolean bool){
            writer = bool;
        }
        
        public void unlock(Transaction t){
            if(writer){
                writer = false;
            }else
                readers--;
            
            transactions.remove(t);
        }
        
        public Transaction getHead(){
            if(transactions.size()== 0){
                return null;
            }else{
                return transactions.get(0);
            }
        }
        
        public void removeReader(Transaction t){
            readers--;
            transactions.remove(t);
        }
    }
    
}
