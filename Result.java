/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSC29501_Assignment3No2;

/**
 *
 * @author mac
 */
public class Result {
	private int intWithMaxFromTask; // Which integer gave that maximum number.

        
        Result(int whichInt) {
		intWithMaxFromTask = whichInt;
	}
     
	public int getIntWithMaxFromTask() {
		return intWithMaxFromTask;
	}

	public void setIntWithMaxFromTask(int intWithMaxFromTask) {
		this.intWithMaxFromTask = intWithMaxFromTask;
	}
}