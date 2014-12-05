import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedListVSArrayList {

	public static void main(String[] args) {
		List<Integer> arrayList = new ArrayList<Integer>();
		List<Integer> linkedList = new LinkedList<Integer>();

		doTimings("ArrayList", arrayList);
		doTimings("LinkedList", linkedList);
	}

	private static void doTimings(String type, List<Integer> list) {

		long start1 = System.currentTimeMillis();
		for (int i = 0; i < 1E5; i++) {
			list.add(i);
		}
		long end1 = System.currentTimeMillis();
		System.out.println("From the beginning of the list. Time taken: " + (end1 - start1) + " ms for " + type);
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1E5; i++) {
			list.add(0, i);;
		}

		long end = System.currentTimeMillis();

		System.out.println("From the end of the list. Time taken: " + (end - start) + " ms for " + type);
	}
}
