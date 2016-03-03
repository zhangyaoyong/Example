package david.example.concurrency.thread;

public class ThreadStateExample {
	
	public static void main(String[] arg){
		
		Thread t1=new Thread(){
			public void run(){
				System.out.println("t1 state RUNNING");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					System.out.println("t1 is interrupted"+Thread.currentThread().getState());
				}
				System.out.println("t1 state AWAKEN");
			}
		};
		System.out.println("t1 state "+t1.getState().toString());
		t1.start();	
		int interruptedNum= (int)(Math.random() *1000);
		for(int i=0; i<1000; i++)
		{
			if(i == interruptedNum) {
				System.out.println("interruptNum is "+interruptedNum);
				t1.interrupt();
			}
			System.out.println(t1.getState());
		}
		System.out.println("t1 state "+t1.getState().toString());
		
		
		
		final Thread t0=Thread.currentThread();
		Thread t2=new Thread(){
			public void run(){
				
				System.out.println("t0 state in t2 is "+ t0.getState());
				for(int i=0; i<1000; i++){
				}
				System.out.println("t2 is running");
			}
		};
		
		t2.start();
		try {
			t2.join();
		} catch (InterruptedException e) {
			System.out.println("t2 is interrupted");
		}
		System.out.println("t0 is still running");
		
	}

}
