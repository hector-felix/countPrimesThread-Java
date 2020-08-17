/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSC29501_Assignment3No2;

import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author mac
 */
public class Task {

    int min, max; // Start and end of the range of integers for this task.
     private static int currentMax;
    LinkedBlockingQueue<Result> resultQueue;

    Task(int min, int max, LinkedBlockingQueue<Result> resultQueue) {
        this.min = min;
        this.max = max;
        this.resultQueue = resultQueue;
    }

    public void compute() {        
        int runcurrentMax = 0;

        for (int i = min; i <= max; i++) {
            if (checkIfPrime(i) && lastDigit(i) == 7 && i > runcurrentMax) {
                    runcurrentMax = i;
            }
            newMax(runcurrentMax);
        }
        
        resultQueue.add(new Result(runcurrentMax));
    }
    
        synchronized public void newMax(int currentMax) {
        Task.currentMax = currentMax;
    }
    
    public static boolean checkIfPrime(int n) {
        int root = (int) Math.sqrt(n);
        for (int i = 2; i <= root; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public static int lastDigit(int n) {
        return (n % 10);
    }
}
