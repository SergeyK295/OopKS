package ru.academits.kolesnikov.array_list_home_main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static ArrayList<String> getStringListFromFile(String fileName) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }

        return lines;
    }

    public static void deleteEvenNumbers(ArrayList<Integer> numbers) {
        for (int i = numbers.size() - 1; i >= 0; i--) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
            }
        }
    }

    public static <T> ArrayList<T> getNonRepetitiveList(ArrayList<T> arrayList) {
        ArrayList<T> nonRepetitiveList = new ArrayList<>(arrayList.size());

        for (T t : arrayList) {
            if (!nonRepetitiveList.contains(t))
                nonRepetitiveList.add(t);
        }

        return nonRepetitiveList;
    }

    public static void main(String[] args) {
        String fileName = "line.txt";
        ArrayList<String> strings = null;

        try {
            strings = getStringListFromFile(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка! Файл не найден!");
        } catch (IOException e) {
            System.out.println("Файл не прочитан." + e.getMessage());
        }

        System.out.println("Строки из файла: " + strings);

        Integer[] numbersArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 1, 12, 13, 4, 6, 7, 3, 17, 9, 10};

        ArrayList<Integer> numbersList1 = new ArrayList<>(List.of(numbersArray));
        System.out.println("Изначальный список чисел: " + numbersList1);
        deleteEvenNumbers(numbersList1);
        System.out.println("Список без четных чисел: " + numbersList1);

        ArrayList<Integer> numbersList2 = getNonRepetitiveList(numbersList1);
        System.out.println("Список чисел без повторения: " + numbersList2);
    }
}