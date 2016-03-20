package david.example.util.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class ExecutorServiceExample {
	@Test
	public void test_invokeAll() throws InterruptedException {
		ExecutorService es = Executors.newFixedThreadPool(10);

		List<Callable<Object>> l = new ArrayList<Callable<Object>>();
		Callable<Object> c = () -> {
			for (long j = 0; j < 1000000000; j++)
				;
			return null;

		};
		for (int i = 0; i < 100; i++) {
			l.add(c);
		}

		List<Future<Object>> futures = es.invokeAll(l);

		futures.stream().forEach((f) -> {
			System.out.println(f.isDone());
		});

		System.out.println("Completed");
	}

	@Test
	public void test_futureCancell() {
		ExecutorService es = Executors.newSingleThreadExecutor();

		Future<?> f = es.submit(() -> {
			try {
				Thread.currentThread().sleep(30000);
			} catch (Exception e) {
				System.out.println("interruped");
			}
			System.out.println("continuing");
		});
//		try {
//			TimeUnit.SECONDS.sleep(3);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		System.out.println(f.cancel(true));

//		Future<?> f2 = es.submit(() -> {
//			for (;;) {
//				System.out.println("continuing");
//			}
//		});
//		try {
//			TimeUnit.SECONDS.sleep(3);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println(f2.cancel(true));
	}

}
