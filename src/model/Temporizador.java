package model;

import java.util.Timer;

public class Temporizador extends Thread{
	
	 private int minute = 0;
	 private int second = 11;
	 private Timer timer;
	 private boolean isTimerRunning;
	 //private Display display;

	public Temporizador() {
		timer = new Timer();
	}
	
	public void run() {
		isTimerRunning = true;
        if(second > 0) {
            second--;
        } else {
            second = 59;
            if(minute > 0) 
            	minute--;
            else {
                minute = 59;
            } 
             if(second == 0 & minute == 0) {
            	 isTimerRunning = false;
                 /*timer.cancel();
                 timer.purge();*/
             }
            
        }
	}
	
	public void start() {
		
	}

}
