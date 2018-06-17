package maine;

import java.util.Spliterator;
import java.util.function.Consumer;

import maine.person.Person;

public class PersonSpliterator implements Spliterator<Person> {

    private Spliterator<String> lineSpliterator;

    private String name;
    private int age;
    private String city;

    public PersonSpliterator(Spliterator<String> lineSpliterator) {

        this.lineSpliterator = lineSpliterator;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Person> action) {

        if (this.lineSpliterator.tryAdvance(line -> this.name = line)
                && this.lineSpliterator.tryAdvance(line -> this.age = Integer.parseInt(line.trim()))
                && this.lineSpliterator.tryAdvance(line -> this.city = line)) {

            Person person = new Person(this.name, this.age, this.city);

            action.accept(person);
            return true;
        }

        return false;
    }

    /**
     * Only for parallel processing
     *
     * @return null
     */
    @Override
    public Spliterator<Person> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return this.lineSpliterator.estimateSize() / 3;
    }

    @Override
    public int characteristics() {
        return this.lineSpliterator.characteristics();
    }

}
