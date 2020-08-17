/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSC29501_Assignment3No2;


import java.util.concurrent.ConcurrentLinkedQueue;

public class CountPrimesThread extends Thread {

	private ConcurrentLinkedQueue<Task> taskQueue;

	public CountPrimesThread(ConcurrentLinkedQueue<Task> taskQueue) {
		this.taskQueue = taskQueue;
	}

	public void run() {
		while (true) {
			Task task = taskQueue.poll();
			if (task == null)
				break;
                        task.compute();
		}
	}
}