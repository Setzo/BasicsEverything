package maine;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Test {

    public static void main(String[] args) throws IOException, URISyntaxException {

        (StreamSupport.stream(new PersonSpliterator(Stream.of(Files.lines(Paths.get(Test.class.getResource("plik.txt").toURI())), Files.lines(Paths.get(Test.class.getResource("plik2.txt").toURI()))).flatMap(Function.identity()).spliterator()), false)).forEach(System.out::println);
    }

}
