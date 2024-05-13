package ru.academits.kolesnikov.lambdas_main;

import ru.academits.kolesnikov.lambdas.Person;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import static ru.academits.kolesnikov.lambdas.Person.*;

public class Main {
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

        List<Person> peopleUnder18 = getPeopleYounger(persons, 18);
        OptionalDouble averageAgeUpTo18 = peopleUnder18.stream()
                .mapToInt(Person::age)
                .average();

        if (averageAgeUpTo18.isPresent()) {
            System.out.println("Средний возраст людей младше 18 лет: " + averageAgeUpTo18);
        } else {
            System.out.println("Нет людей младше 18");
        }

        System.out.println("Средний возраст по именам: " + getAveragesAgeByNames(persons));
        System.out.println("Средний возраст для имени \"Ира\": " + getAveragesAgeByNames(persons).get("Ира"));

        List<String> namesPersonsBetween20And45 = persons.stream()
                .filter(p -> p.age() >= 20 && p.age() <= 45)
                .sorted((p1, p2) -> p2.age() - p1.age())
                .map(Person::name)
                .toList();

        System.out.println("Имена людей возраст которых от 20 до 45 в порядке убывания возраста: " + namesPersonsBetween20And45);
    }
}
