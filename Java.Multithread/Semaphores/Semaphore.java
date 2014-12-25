package semaph;

import java.util.concurrent.Semaphore;

public class Connection {
	
	private static Connection instance = new Connection();
	
	private Semaphore sem = new Semaphore(10, true);
	
	private int count = 0;
	
	private Connection() {
		
	}
	
	public static Connection getInstance() {
		return instance;
	}
	
	public void connect() {
		
		try {
			sem.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			doConnect();
		} finally {
			sem.release();
		}
	}
	
	public void doConnect() {
		
		synchronized (this) {
			count++;
			System.out.println(count);
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		synchronized (this) {
			count--;
		}
	}
}
