package sort;

import java.util.LinkedList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		SortInsert si = new SortInsert();
		
		LinkedList<Integer> toSortList = new LinkedList<Integer>();
		
		Random rng = new Random();
		
		for(int i = 0; i < 100; i++) {
			toSortList.add(rng.nextInt(100));
		}
		
		System.out.println(toSortList);
		
		toSortList = si.insertionSort(toSortList);
		
		System.out.println(toSortList);
	}
}
