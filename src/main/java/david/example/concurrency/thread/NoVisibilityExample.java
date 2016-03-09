package david.example.concurrency.thread;

public class NoVisibilityExample {

	private static boolean ready;
	private static int number;
	
	public  NoVisibilityExample(){
		
	}

	private static class ReaderThread extends Thread {
		public void run() {
			while (!ready)
				Thread.yield();
			System.out.println(number);
		}
	}

	public static void main(String[] args) {
		new ReaderThread().start();
		number = 42;
		ready = true;
	}

}
