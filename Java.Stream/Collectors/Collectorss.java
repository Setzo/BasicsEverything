package col;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Collectorss {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException, URISyntaxException {

		Stream<String> lines = 
				Files.lines(Paths.get(Collectorss.class.getResource("names.txt").toURI()));
		
		List<Person> people =
				StreamSupport.stream(new PersonSpliterator(lines.spliterator()), true)
					.collect(Collectors.toList());
		
		lines.close();
		
		Person maxAge = people.stream()
				.collect(
						Collectors.maxBy(Comparator.comparing(Person::getAge))
				).orElseGet(Person::empty);
		
		Double avgAge = people.stream()
				.collect(
						Collectors.averagingDouble(Person::getAge)
				);
		
		String names = people.stream()
				.map(Person::getName)
				.collect(
						Collectors.joining(", ", "[", "]")
				);
		
		Set<String> set = people.stream()
				.parallel()
				.map(Person::getName)
				.collect(Collectors.toSet());
		
		TreeSet<String> treeSet = people.stream()
				.parallel()
				.map(Person::getName)
				.collect(Collectors.toCollection(TreeSet::new));
		
		Map<Boolean, List<Person>> adults = people.stream()
				.collect(Collectors.partitioningBy(person -> person.getAge() > 18));
		
		Map<Integer, List<Person>> peopleByAge = people.stream()
				.collect(Collectors.groupingBy(Person::getAge));
		
//		TreeMap<Integer, TreeSet<String>> namesByAge =
//				people.stream()
//						.collect(
//								Collectors.groupingBy(person -> person.getAge()),
//								() -> new TreeMap(),
//								Collectors.mapping(
//										person -> person.getName(),
//										Collectors.toCollection(TreeSet::new)
//								)
//								
//						);
		
		System.out.println(maxAge);
		System.out.println(avgAge);
		System.out.println(names);
		System.out.println(people.size());
		System.out.println(set.size());
		System.out.println(treeSet.size());
	}

}
