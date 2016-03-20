package david.example.lang;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

import org.junit.Test;

public class Object {
	
	/**
	 * 1 先使用sleep使得main线程休眠，同时使得t可以得到obj的monitor
	 * 2 在main线程中obj.notify唤醒t
	 * 3 notify和wait都需要先获得lock
	 */
	@Test
	public void test_CorrectlyWaitAndNotify() {
		final Object obj = new Object();
		Thread t = new Thread() {
			public void run() {
				try {
					synchronized (obj) {
						System.out.println("waiting in t");
						obj.wait();
					}
				} catch (InterruptedException e) {
					System.out.println(" in t " + e.getMessage());
				}
				System.out.println("after wait in t");
			}
		};
		t.start();

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			System.out.println("main thread is interrupted");
		}

		try {
			synchronized (obj) {
				obj.notify();
			}
			// t.interrupt();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("after wait in main");
	}
	
	/**
	 * notify和wait都需要先获得lock
	 */
	
	@Test
	public void test_notAccquireLockBeforeWait()
	{
		Object obj=new Object();
		try {
			obj.wait(1000);
			fail("did\'t throw IllegalMonitorStateException");
		} catch (InterruptedException e) {
		}
		catch(IllegalMonitorStateException e)
		{
		}
		try {
			obj.notify();
			fail("did\'t throw IllegalMonitorStateException");
		}
		catch(IllegalMonitorStateException e)
		{
		}
	}
	
	
	@Test
	public void test_waitInSelf()
	{
		class Inner
		{
			public synchronized void waitInSelf()
			{
				try {
					System.out.println("waiting");
					wait();
				} catch (InterruptedException e) {
					
				}
				System.out.println("after waiting");
			}
		}
		
		final Inner i=new Inner();
		
		Thread t=new Thread(){
			public void run(){
				
				i.waitInSelf();
			}
		};
		
	     t.start();
	     
	     try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
		}
	     synchronized(i)
	     {
	    	 i.notify();
	     }
	     
	}

}
