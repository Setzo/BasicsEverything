package coll;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collector;

import col.Person;

public class AdvCollector {

    @SuppressWarnings( "unused" )
    public static void main(String[] args) {

        //Creates container
        Supplier<List<Person>> supplier = ArrayList<Person>::new;

        //Adds to container
        BiConsumer<Person, List<Person>> accumulator = (person, list) -> list.add(person);

        //Merges containers
        BinaryOperator<List<Person>> combiner = (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };

        Collector<Person, List<Person>, List<Person>> collector = Collector.of(
                ArrayList<Person>::new,
                (list, person) -> list.add(person),
                (list1, list2) -> {
                    list1.addAll(list2);
                    return list1;
                },
                Collector.Characteristics.IDENTITY_FINISH
        );
    }

}
