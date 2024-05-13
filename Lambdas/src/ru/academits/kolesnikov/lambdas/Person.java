package ru.academits.kolesnikov.lambdas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record Person(String name, int age) {
    public static List<String> getUniqueNames(List<Person> persons) {
        return persons.stream()
                .map(p -> p.name)
                .distinct().
                collect(Collectors.toList());
    }

    public static List<Person> getPeopleYounger(List<Person> persons, int age) {
        return persons.stream()
                .filter(p -> p.age() < age)
                .toList();
    }

    public static Map<String, Double> getAveragesAgeByNames(List<Person> persons) {
        Map<String, List<Person>> personsByNames = persons.stream()
                .collect(Collectors
                        .groupingBy(Person::name));
        Map<String, Double> averageAgeByNames = new HashMap<>();

        for (String key : personsByNames.keySet()) {
            averageAgeByNames.put(key, personsByNames.get(key).stream().mapToInt(Person::age).average().orElseThrow());
        }

        return averageAgeByNames;
    }
}