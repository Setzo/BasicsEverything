package maine;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import maine.person.Person;

public class Main {

	public static void main(String[] args) throws IOException, URISyntaxException {
		
		Path path = Paths.get(Main.class.getResource("plik.txt").toURI());

		Stream<String> stream = Files.lines(path);
		
		Spliterator<String> lineSpliterator = stream.spliterator();
		
		Spliterator<Person> personSpliterator = new PersonSpliterator(lineSpliterator);
		
		Stream<Person> personStream = StreamSupport.stream(personSpliterator, false);
		
		personStream.forEach(System.out::println);
		
		stream.close();
		personStream.close();
	}

}
