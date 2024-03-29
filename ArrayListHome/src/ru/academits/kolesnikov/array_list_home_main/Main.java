package ru.academits.kolesnikov.array_list_home_main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("strings.txt"))) {
            while (bufferedReader.ready()) {
                strings.add(bufferedReader.readLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка! Файл не найден!");
        } catch (IOException e) {
            System.out.println("Ошибка при вводе/выводе данных из файла!");
        }

        System.out.println("Строки из файла: " + strings);

        ArrayList<Integer> numbers1 = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileReader("numbers.txt"))) {
            while (scanner.hasNextLine()) {
                numbers1.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка! Файл не найден!");
        }

        System.out.println("Изначальный список чисел: " + numbers1);

        for (int i = 0; i < numbers1.size(); ) {
            int number = numbers1.get(i);

            if (number % 2 == 0) {
                numbers1.remove(i);
                continue;
            }

            i++;
        }

        System.out.println("Список без четных чисел: " + numbers1);

        ArrayList<Integer> numbers2 = new ArrayList<>();

        for (int number : numbers1) {
            boolean presence = false;

            for (Integer integer : numbers2) {
                if (integer == number) {
                    presence = true;
                    break;
                }
            }

            if (!presence) {
                numbers2.add(number);
            }
        }

        System.out.println("Список чисел без повторения: " + numbers2);
    }
}