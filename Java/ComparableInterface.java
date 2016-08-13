import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

class Animal implements Comparable<Animal> {
	
	private String name;
	
	Animal (String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Animal an) {
		int len1 = name.length();
		int len2 = an.name.length();
		
		if(len1 > len2) {
			return -1;
		}
		else if(len1 < len2) {
			return 1;
		}
		return -name.compareTo(an.name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}

public class Comp {
	public static void main(String[] args) {
		List<Animal> list = new ArrayList<Animal>();
		SortedSet<Animal> set = new TreeSet<Animal>();
		
		addElements(list);
		addElements(set);
		
		Collections.sort(list);
		
		displayElements(list);
		System.out.println();
		displayElements(set);
	}
	
	private static void addElements(Collection<Animal> col) {
		col.add(new Animal("cat"));
		col.add(new Animal("snake"));
		col.add(new Animal("lion"));
		col.add(new Animal("ant"));
		col.add(new Animal("lizard"));
	}
	
	private static void displayElements(Collection<Animal> col) {
		for (Animal c : col) {
			System.out.println(c);
		}
	}
}
