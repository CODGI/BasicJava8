import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class main {

    public static void main(String[] args) {

        //Functional Interfaces
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");

        System.out.println(person.firstName + " " + person.lastName);


        //Streams
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("a2");
        stringCollection.add("a1");
        stringCollection.add("b");
        stringCollection.add("c");

        Predicate<String> startsWithA = (s) -> s.startsWith("a");

        System.out.println("ELements starting with 'a':");
        stringCollection
                .stream()
                .filter(startsWithA)
                .forEach(System.out::println);

        System.out.println("Now sorted:");
        stringCollection
                .stream()
                .sorted()
                .filter(startsWithA)
                .forEach(System.out::println);

        System.out.println("All elements as upper-case:");
        stringCollection
                .stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        boolean anyStartWithA =
                stringCollection
                        .stream()
                        .anyMatch(startsWithA);

        System.out.println("Any start with a: " + anyStartWithA);

        boolean allStartWithA =
                stringCollection
                        .stream()
                        .allMatch(startsWithA);

        System.out.println("All start with a: " + allStartWithA);


        long startsWiithA =
                stringCollection
                        .stream()
                        .filter(startsWithA)
                        .count();

        System.out.println("Number of elements starting with a: " + startsWiithA);

        Optional<String> reduced =
                stringCollection
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println);


        //Performance of parallel streams

        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        long t0 = System.nanoTime();
        values.stream().sorted().count();
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("Sequential sort took %d ms", millis));

        t0 = System.nanoTime();
        values.parallelStream().sorted().count();
        t1 = System.nanoTime();
        millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("Parallel sort took %d ms", millis));


    }

}