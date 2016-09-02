import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.IntSummaryStatistics;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

        Main.getStream().forEach(System.out::println);

        IntSummaryStatistics stats = getStream().summaryStatistics();

        System.out.printf("\n%s%d\n%s%d\n%s%f\n%s%d\n%s%d\n",
                "Max: ", stats.getMax(),
                "Min: ", stats.getMin(),
                "Avg: ", stats.getAverage(),
                "Sum: ", stats.getSum(),
                "Cnt: ", stats.getCount());
    }

    public static IntStream getStream() throws IOException {

        Stream<String> stream1 = Files.lines(Paths.get("src/sawyer2.txt"));
        Stream<String> stream2 = Files.lines(Paths.get("src/sawyer3.txt"));
        Stream<String> stream3 = Files.lines(Paths.get("src/sawyer4.txt"));
        Stream<String> stream4 = Files.lines(Paths.get("src/sawyer5.txt"));

        return Stream.of(stream1, stream2, stream3, stream4)
                .flatMap(Function.identity())
                .flatMap(line -> Pattern.compile(" ").splitAsStream(line))
                .map(word -> word.toLowerCase())
                .filter(word -> Pattern.matches("-?[0-9]+", word))
                .distinct()
                .mapToInt(word -> Integer.parseInt(word));
    }

}
