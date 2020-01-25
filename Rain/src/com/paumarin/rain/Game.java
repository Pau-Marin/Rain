package com.paumarin.rain;

public class Game implements Runnable{
	
	public static int WIDTH = 300;
	public static int HEIGHT = WIDTH / 16 * 9;
	public static int SCALE = 3;
	
	private Thread thread;
	
	private boolean running = false;
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
		
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while(running) {
			
		}
	}

}
