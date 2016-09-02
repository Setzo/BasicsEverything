package adv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Movies {

    public static void main(String[] args) throws IOException {

        Set<Movie> movies =
                StreamSupport.stream(
                        new MovieSpliterator(
                                Files.lines(Paths.get("src/adv/movies.txt")).spliterator()
                        ),
                        false
                )
                        .collect(Collectors.toSet());

        System.out.println(movies.size());

        /**
         * Overall actor count.
         */
        long countActors = movies.stream()
                .flatMap(movie -> movie.getActors().stream())
                .distinct()
                .count();

        System.out.println(countActors);

        /**
         * Actor that played in the most movies
         */
        Map.Entry<Actor, Long> actorEntry = movies.stream()
                .flatMap(movie -> movie.getActors().stream())
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .max(
                        Map.Entry.comparingByValue()
                )
                .get();

        System.out.println(actorEntry);

        /**
         * Actor that played in the greatest amount of movies during a given year.
         */
        Map.Entry<Integer, Map.Entry<Actor, AtomicLong>> actorThatPlayedInTheMostMoviesInAGivenYear =
                movies.stream()
                        .collect(
                                Collectors.groupingBy(
                                        movie -> movie.getReleaseYear(),
                                        TreeMap<Integer, HashMap<Actor, AtomicLong>>::new,
                                        Collector.of(
                                                HashMap<Actor, AtomicLong>::new,
                                                (map, movie) -> {
                                                    movie.getActors().forEach(
                                                            actor -> map.computeIfAbsent(actor, a -> new AtomicLong()).incrementAndGet()
                                                    );
                                                },
                                                (map1, map2) -> {
                                                    map2.entrySet().forEach(
                                                            entry2 -> map1.merge(entry2.getKey(), entry2.getValue(),
                                                                    (atomic1, atomic2) -> {
                                                                        atomic1.addAndGet(atomic2.get());
                                                                        return atomic1;
                                                                    }
                                                            )
                                                    );
                                                    return map1;
                                                },
                                                Collector.Characteristics.IDENTITY_FINISH
                                        )
                                )
                        )
                        .entrySet()
                        .stream()
                        .collect(
                                Collectors.toMap(
                                        Map.Entry::getKey,
                                        entry -> entry.getValue()
                                                .entrySet()
                                                .stream()
                                                .max(
                                                        Map.Entry.comparingByValue(
                                                                Comparator.comparing(AtomicLong::get)
                                                        )
                                                )
                                                .get()
                                )
                        )
                        .entrySet()
                        .stream()
                        .max(
                                Map.Entry.comparingByValue(
                                        Comparator.comparing(
                                                entry -> entry.getValue().get()
                                        )
                                )
                        )
                        .get();

        System.out.println(actorThatPlayedInTheMostMoviesInAGivenYear);

    }

}
