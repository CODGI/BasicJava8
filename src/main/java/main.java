import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class main {

    public static void main(String[] args) {

        //Functional Interfaces
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter","Parker");

        System.out.println(person.firstName+" "+person.lastName);


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

        System.out.println("Any start with a: "+anyStartWithA);

        boolean allStartWithA =
                stringCollection
                .stream()
                .allMatch(startsWithA);

        System.out.println("All start with a: "+allStartWithA);


        long startsWiithA =
                stringCollection
                .stream()
                .filter(startsWithA)
                .count();

        System.out.println("Number of elements starting with a: "+startsWiithA);

        Optional<String> reduced =
                stringCollection
                .stream()
                .sorted()
                .reduce((s1,s2) -> s1+"#"+s2);

        reduced.ifPresent(System.out::println);

        
    }

}