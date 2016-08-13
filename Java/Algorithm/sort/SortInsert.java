package sort;

import java.util.LinkedList;

public class SortInsert {

	public LinkedList<Integer> insertionSort(LinkedList<Integer> toSortList) {
		
		for(int i = 1; i < toSortList.size(); i++) {
			
			Integer tmp = toSortList.get(i);
			int j = i - 1;
			
			while(j >= 0 && toSortList.get(j) > tmp) {

				toSortList.set(j + 1, toSortList.get(j));
				j--;
			}
			
			toSortList.set(j + 1, tmp);
		}
		
		return toSortList;
	}
}
