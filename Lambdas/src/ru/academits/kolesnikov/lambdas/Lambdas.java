package ru.academits.kolesnikov.lambdas;

import java.util.*;
import java.util.stream.Collectors;

public class Lambdas {
    public record Person(String name, int age) {
    }

    private static List<String> getUniqueNames(List<Person> persons) {
        List<String> uniqueNames = new ArrayList<>();
        persons.forEach((person) -> {
            if (!uniqueNames.contains(person.name())) {
                uniqueNames.add(person.name());
            }
        });

        uniqueNames.sort(Comparator.naturalOrder());
        return uniqueNames;
    }

    private static List<Person> getPeopleUnder(List<Person> persons, int age) {
        return persons.stream().filter(person -> person.age() < age).toList();
    }

    private static Map<String, Double> averageAgeByNames(List<Person> persons) {
        Map<String, List<Person>> personsByAge = persons.stream().collect(Collectors.groupingBy(Person::name));
        Map<String, Double> personsAverage = new HashMap<>();

        for (String key : personsByAge.keySet()) {
            personsAverage.put(key, personsByAge.get(key).stream().mapToInt(Person::age).average().orElseThrow());
        }
        return personsAverage;
    }

    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Костя", 18),
                new Person("Ира", 16),
                new Person("Паша", 20),
                new Person("Сергей", 22),
                new Person("Дима", 15),
                new Person("Алиса", 30),
                new Person("Ира", 29),
                new Person("Сергей", 31),
                new Person("Вася", 31),
                new Person("Максим", 20),
                new Person("Вася", 70));

        List<String> uniqueNames = getUniqueNames(persons);
        System.out.println(uniqueNames.stream().collect(Collectors.joining(", ", "Имена: ", ".")));

        List<Person> peopleUnder18 = getPeopleUnder(persons, 18);
        double peopleAverageAgeUnder18 = peopleUnder18.stream().mapToInt(Person::age).average().orElseThrow();
        System.out.println("Средний возраст людей младше 18 лет: " + peopleAverageAgeUnder18);

        System.out.println("Средний возраст по именам: " + averageAgeByNames(persons));
        System.out.println("Средний возраст по именам: " + averageAgeByNames(persons).get("Ира"));

        System.out.println(persons.stream().filter(person -> person.age() > 20 && person.age() < 45).sorted((p1, p2) -> p2.age() - p1.age()).toList());
    }
}