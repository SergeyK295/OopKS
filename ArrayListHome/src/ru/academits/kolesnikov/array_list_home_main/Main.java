package ru.academits.kolesnikov.array_list_home_main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void readLinesFromFile(ArrayList<String> strings, String file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                strings.add(string);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка! Файл не найден!");
        } catch (IOException e) {
            System.out.println("Файл не прочитан. Ошибка при вводе/выводе данных из файла!");
        }
    }

    public static void deleteEvenNumbers(ArrayList<Integer> list) {
        for (int i = list.size() - 1; i > -1; i--) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
            }
        }
    }

    public static ArrayList<Integer> deleteDuplicate(ArrayList<Integer> list) {
        ArrayList<Integer> result = new ArrayList<>();

        ArrayList<Integer> tamp = (ArrayList<Integer>) list.clone();

        for (int i = 0; !tamp.isEmpty(); i++) {
            result.add(tamp.get(0));

            while (tamp.remove(result.get(i))) {
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        String file = "line.txt";
        readLinesFromFile(strings, file);
        System.out.println("Строки из файла: " + strings);

        Integer[] numberArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 1, 12, 13, 4, 6, 7, 3, 17, 9, 10};

        ArrayList<Integer> numberList1 = new ArrayList<>(List.of(numberArray));
        System.out.println("Изначальный список чисел: " + numberList1);
        deleteEvenNumbers(numberList1);
        System.out.println("Список без четных чисел: " + numberList1);

        ArrayList<Integer> numberList2 = deleteDuplicate(numberList1);
        System.out.println("Список чисел без повторения: " + numberList2);
    }
}