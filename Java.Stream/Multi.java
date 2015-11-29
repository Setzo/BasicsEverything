package fjp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Multi {

	public static void main(String[] args) {

		System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "2");
		
		List<String> list = new ArrayList<String>();
		
		Stream.iterate("+", x -> x + "+")
			.limit(1000)
			.forEach(list::add);
		
		List<String> concurrentList = new CopyOnWriteArrayList<String>();
		
		/**
		 * Parallel use with limit is not the best idea,
		 * though I wanted to test something.
		 */
		Stream.iterate("+", x -> x + "+")
			.parallel()
			.limit(1000)
			.forEach(concurrentList::add);
		
		List<String> parallelList = new ArrayList<String>();
		
		parallelList = Stream.iterate("+", x -> x + "+")
			.parallel()
			.limit(1000)
//			.peek(x -> System.out.println(x + " : " +  Thread.currentThread().getName()))
			.collect(Collectors.toList());
		
		System.out.println(list.size());
		
		System.out.println(concurrentList.size());
		
		System.out.println(parallelList.size());
	}

}
