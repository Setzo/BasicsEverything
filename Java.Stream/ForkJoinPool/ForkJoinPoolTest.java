package fjp;

import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class ForkJoinPoolTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        List<Integer> intList = ThreadLocalRandom.current()
                .ints()
                .limit(10000)
                .boxed()
                .collect(Collectors.toList());

        intList.forEach(System.out::println);

        ForkJoinPool fjp = new ForkJoinPool(2);

        OptionalInt o = fjp.submit(
                () ->
                        intList.stream().parallel()
                                .filter(x -> x < 0)
                                .mapToInt(x -> x)
                                .max()
        ).get();

        System.out.println(o.orElse(0));
    }

}
