package david.example.util.stream;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.LongStream;

import org.junit.Test;

public class Stream {
	
	@Test
	public void test_normal(){
		List<String> names=Arrays.asList("zhangyayong","liu1","liu2");
		names.stream().forEach(System.out::println);
		
		names.stream().filter(name -> !name.endsWith("ran")).forEach(System.out::println);
		
		names.parallelStream().forEach(System.out::println);
	}
	
	@Test
	public void test_parallelPerformance()
	{
		long max=100000000;
		long start1=new Date().getTime();
		LongStream.rangeClosed(0, max).sum();
		long end1=new Date().getTime();
		System.out.println("sequential time "+(end1- start1));
		
		
		long start2=new Date().getTime();
		LongStream.rangeClosed(0, max).parallel().sum();
		long end2=new Date().getTime();
		System.out.println("parallel time "+(end2- start2));
	}

}
