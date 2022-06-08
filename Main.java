import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long count = persons.stream().filter(person -> person.getAge() < 18).count();
        System.out.println("Количество несовершеннолетних: ");
        System.out.println(count);

        List<String> conscripts = persons.stream().filter(person -> (person.getAge() >= 18 && person.getAge() <= 27))
                .filter(person -> person.getSex() == Sex.MAN).map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Призывники: ");
        System.out.println(conscripts);

        Collection<Person> ableBodied = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getSex() == Sex.MAN ? person.getAge() >= 18 && person.getAge() <= 65
                        : person.getAge() >= 18 && person.getAge() <= 60)
                .sorted(Comparator.comparing(Person::getFamily)).collect(Collectors.toList());
        System.out.println("Трудоспособные люди: ");
        System.out.println(ableBodied);

    }
}