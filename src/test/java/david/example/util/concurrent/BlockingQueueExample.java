package david.example.util.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

public class BlockingQueueExample {

	private static class Task {

		private int max;

		public Task() {
			max = (int) (Math.random() * 10000);
		}

		public void run() {
			int i = 0;
			while (i > max) {
				i++;
			}
			System.out.println(Thread.currentThread().getName()
					+ " completed run");
		}
	}

	@Test
	public void test_arrayBlockingQueue_basic() {
		//BlockingQueue<Task> bq = new LinkedBlockingQueue<Task>();
		BlockingQueue<Task> bq = new ArrayBlockingQueue<Task>(100);

		AtomicInteger taskNumProduced = new AtomicInteger(0);
		AtomicInteger taskNumConsumed = new AtomicInteger(0);

		ExecutorService producers = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			producers.execute(() -> {
				for (int j = 0; j < 20; j++) {
					try {
						bq.put(new Task());
						System.out.println(Thread.currentThread().getName()
								+ " put Task " + j);
						taskNumProduced.getAndIncrement();
					} catch (InterruptedException e) {
						throw new RuntimeException("awake from put");
					}
				}

			});
		}

		ExecutorService consumers = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++) {
			consumers.execute(() -> {
				int count = 0;
				while (!Thread.interrupted()) {
					try {
						Task t = bq.take();
						t.run();
						System.out.println(Thread.currentThread().getName()
								+ " take Task " + count);
						taskNumConsumed.getAndIncrement();
					} catch (InterruptedException e) {
						throw new RuntimeException("awake from take");
					}
				}
			});
		}

		try {
			TimeUnit.SECONDS.sleep(20);;
		} catch (InterruptedException e) {
			System.out.println("awake from main");
		}
		System.out.println(taskNumProduced.get());
		System.out.println(taskNumProduced.get() == taskNumConsumed.get());
	}
}
