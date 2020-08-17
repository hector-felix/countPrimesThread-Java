/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSC29501_Assignment3No2;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Driver {

    public static void main(String[] args) throws InterruptedException {

        /**
         * The upper limit of the range of integers that is to be tested. (This
         * must be a fairly large multiple of 1000 for the thread pool
         * load-balancing strategy to be effective.)
         */
        int MAX = 10000;

        /**
         * A queue to hold the tasks. Tasks are represented as objects of type
         * Task, a nested class that is defined below. Note that queue
         * operations must be synchronized because the queue is used by multiple
         * threads. A ConcurrentLinkedQueue handles synchronization internally.
         */
        // ConcurrentLinkedQueue<Task> taskQueue = new
        // ConcurrentLinkedQueue<Task>();
        ConcurrentLinkedQueue<Task> taskQueue = new ConcurrentLinkedQueue<Task>();

        /**
         * A queue to hold the results from the tasks. Results are defined by
         * the nested class, Result, which is defined below. This is a blocking
         * queue since the thread that takes results from the queue should block
         * when the queue is empty until a result becomes available. (Note: The
         * Task class could have been used to represent results as well; I am
         * using a separate Result class for clarity in this example.)
         */
        LinkedBlockingQueue<Result> resultQueue = new LinkedBlockingQueue<Result>();

        int numberOfThreads = 4;

        System.out.println("\nLargest Prime Using " + numberOfThreads + " threads...");

        /*
		 * Create the queues and the thread pool, but don't start the threads
		 * yet.
         */
        long startTime = System.nanoTime();

        /*
		 * Create the tasks and add them to the task queue. Each task consists
		 * of a range of 1000 integers.
         */
        int numberOfTasks = MAX / 1000;
        for (int i = 0; i < numberOfTasks; i++) {
            int start = i * 1000 + 1;
            int end = (i + 1) * 1000;
            taskQueue.add(new Task(start, end, resultQueue));
        }

        CountPrimesThread[] workers = new CountPrimesThread[numberOfThreads];
        for (int i = 0; i < workers.length; i++) {
            workers[i] = new CountPrimesThread(taskQueue);
        }

        /* Now that the tasks are in the task queue, start the threads. */
        for (int i = 0; i < numberOfThreads; i++) 
            workers[i].start();
        

//		 for (int i = 0; i < numberOfThreads; i++)
//		 workers[i].join();

        /*
		 * The threads will execute the tasks and results will be placed into
		 * the result queue. This method now goes on to read all the results
		 * from the result queue and combine them to give the overall answer.
         */

        int largestPrime = 0; // Which integer gave that maximum?

        for (int i = 0; i < numberOfTasks; i++) {
            Result result = resultQueue.take();
            if (result.getIntWithMaxFromTask() > largestPrime) {
                largestPrime = result.getIntWithMaxFromTask();
            }
        }
        /* Report the results. */

        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("\nThe largest Prime Number Between 1 and " + MAX + " is " + largestPrime);
        System.out.println("Total elapsed time:  " + elapsedTime * .000001 + " Milliseconds\n");
    }

}
