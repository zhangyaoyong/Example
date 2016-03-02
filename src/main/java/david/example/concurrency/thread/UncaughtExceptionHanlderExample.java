package david.example.concurrency.thread;


/**
 * 1 run方法中的runtimeException，calling Thread是无法直接处理的，而是直接定向到了console
 * 2 如果有per-thread的uncaughtExceptionHandler，default是不会执行的
 * @author david
 *
 */
public class UncaughtExceptionHanlderExample {
	
	private static class InnerThread extends Thread{
		public void run()
		{
			throw new RuntimeException("checking uncaughtException");
		}
	}
	
	public static void main(String[] arg){
		
		
		
		InnerThread t0= new InnerThread();
		try{
			t0.start();	
		}
		catch(RuntimeException e)
		{
			System.out.println("should never catch this exception");
		}
		
		
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("Defualt unCaughtException is working. Thread name "+t.getName()+" "+e.getMessage());
				
			}
		});
		
		Thread t1=new InnerThread();
		
		t1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("Thread name "+t.getName()+" "+e.getMessage());
			}
		});
		
		t1.start();
		
		Thread t2=new InnerThread();
		t2.start();
	}

}
