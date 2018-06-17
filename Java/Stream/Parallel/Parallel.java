import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Parallel {

    @SuppressWarnings( "unused" )
    public static void main(String[] args) {

        System.out.println(1____0_0);

        List<Long> list = new ArrayList<Long>();

        IntStream.range(0, 10_000_000).forEach(x -> {

            list.add(ThreadLocalRandom.current().nextLong());
        });

        Stream<Long> stream = Stream.generate(() -> ThreadLocalRandom.current().nextLong());

        List<Long> list1 = stream.limit(10_000_000).collect(Collectors.toList());

        Stream<Long> stream2 = ThreadLocalRandom.current().longs(10_000_000).mapToObj(Long::new);

        List<Long> list2 = stream2.collect(Collectors.toList());

        //unordered
    }
}
