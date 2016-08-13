import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class StringLengthComparator implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
		Integer l1 = s1.length();
		Integer l2 = s2.length();
		return l1.compareTo(l2);
	}
}

class StringReverseAlphabecitalComparator implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
		return -s1.compareTo(s2);
	}
}

public class CompSortList {
	public static void main(String[] args) {
		List<String> animals = new ArrayList<String>();
		
		animals.add("cat");
		animals.add("spider");
		animals.add("snake");
		animals.add("lion");
		animals.add("ant");
		animals.add("eagle");
		
		Collections.sort(animals, new StringLengthComparator());
		
		for(String a : animals) {
			System.out.println(a);
		}
		
		System.out.println();
		
		List<Integer> numbers = new ArrayList<Integer>();
		
		numbers.add(6);
		numbers.add(45);
		numbers.add(-8);
		numbers.add(0);
		numbers.add(213);
		numbers.add(64);
		
		Collections.sort(numbers);
		
		for(Integer a : numbers) {
			System.out.println(a);
		}
		
		System.out.println();
		
		Collections.sort(animals, new StringReverseAlphabecitalComparator());
		
		for(String a : animals) {
			System.out.println(a);
		}
		
		System.out.println();
		
		Collections.sort(numbers, new Comparator<Integer>() {

			@Override
			public int compare(Integer x, Integer y) {
				return -x.compareTo(y);
			}
			
		});
		
		for(Integer a : numbers) {
			System.out.println(a);
		}
	}
}
