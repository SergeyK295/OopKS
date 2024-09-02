package ru.academits.kolesnikov.array_list_home;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static ArrayList<String> getFileLines(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            ArrayList<String> lines = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            return lines;
        }
    }

    public static void deleteEvenNumbers(ArrayList<Integer> numbers) {
        for (int i = numbers.size() - 1; i >= 0; i--) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
            }
        }
    }

    public static <T> ArrayList<T> getUniqueItemsList(ArrayList<T> list) {
        ArrayList<T> uniqueItemsList = new ArrayList<>(list.size());

        for (T item : list) {
            if (!uniqueItemsList.contains(item)) {
                uniqueItemsList.add(item);
            }
        }

        return uniqueItemsList;
    }

    public static void main(String[] args) {
        String fileName = "line.txt";

        try {
            ArrayList<String> fileLines;
            fileLines = getFileLines(fileName);
            System.out.println("Строки из файла: " + fileLines);
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка! Файл не найден!");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        ArrayList<Integer> numbersList1 = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 1, 12, 13, 4, 6, 7, 3, 17, 9, 10));
        System.out.println("Изначальный список чисел: " + numbersList1);
        deleteEvenNumbers(numbersList1);
        System.out.println("Список без четных чисел: " + numbersList1);

        ArrayList<Integer> uniqueNumbersList = getUniqueItemsList(numbersList1);
        System.out.println("Список чисел без повторений: " + uniqueNumbersList);
    }
}