package opt;

import java.util.List;
import java.util.OptionalDouble;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Optionals {

    public static void main(String[] args) {

        List<Integer> list = ThreadLocalRandom
                .current()
                .ints(100, 0, 1000)
                .boxed()
                .collect(Collectors.toList());

        System.out.println(list.size());

        OptionalDouble avg = list.stream()
                .mapToInt(x -> x)
                .average();

        System.out.println(avg.orElseGet(() -> 0));
    }

}
