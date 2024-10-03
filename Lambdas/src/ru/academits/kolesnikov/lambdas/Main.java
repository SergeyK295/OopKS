package ru.academits.kolesnikov.lambdas;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static List<String> getUniqueNames(List<Person> persons) {
        return persons.stream()
                .map(Person::name)
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Person> getPeopleYounger(List<Person> persons, int age) {
        return persons.stream()
                .filter(p -> p.age() < age)
                .toList();
    }

    public static Map<String, Double> getAverageAgesByNames(List<Person> persons) {
        return persons.stream().collect(Collectors.groupingBy(Person::name, Collectors.averagingInt(Person::age)));
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

        List<Person> peopleAgeUpTo18 = getPeopleYounger(persons, 18);
        OptionalDouble averagePeopleAgeUpTo18 = peopleAgeUpTo18.stream()
                .mapToDouble(Person::age)
                .average();

        if (averagePeopleAgeUpTo18.isPresent()) {
            System.out.println("Средний возраст людей младше 18 лет: " + averagePeopleAgeUpTo18.getAsDouble());
        } else {
            System.out.println("Нет людей младше 18");
        }

        Map<String, Double> averageAgesByNames = getAverageAgesByNames(persons);
        System.out.println("Средний возраст по именам: " + averageAgesByNames);

        String name = "Ира";
        System.out.println("Средний возраст для имени \"" + name + "\": " + averageAgesByNames.get(name));

        List<String> personsBetween20And45Names = persons.stream()
                .filter(p -> p.age() >= 20 && p.age() <= 45)
                .sorted((p1, p2) -> p2.age() - p1.age())
                .map(Person::name)
                .toList();

        System.out.println("Имена людей возраст которых от 20 до 45 в порядке убывания возраста: " + personsBetween20And45Names);
    }
}
