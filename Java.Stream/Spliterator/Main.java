package maine;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import maine.person.Person;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {

        Path path = Paths.get(Main.class.getResource("plik.txt").toURI());
        Path path2 = Paths.get(Main.class.getResource("plik2.txt").toURI());

        Main.test(5, 4, 3);

        Stream<String> stream1 = Files.lines(path);
        Stream<String> stream2 = Files.lines(path2);

//		Stream<String> stream = Stream.concat(stream1, stream2);

        Stream<String> stream = Stream.of(stream1, stream2).flatMap(Function.identity());

        Spliterator<String> lineSpliterator = stream.spliterator();

        Spliterator<Person> personSpliterator = new PersonSpliterator(lineSpliterator);

        Stream<Person> personStream = StreamSupport.stream(personSpliterator, false);

        personStream.forEach(System.out::println);

        stream.close();
        personStream.close();
    }

    public static void test(Integer... values) {
        Arrays.asList(values).forEach(System.out::println);
    }

}
