package ru.academits.kolesnikov.arrayListHome_main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Integer> numbers1 = new ArrayList<>();
        Scanner scanner = new Scanner(new FileInputStream("numbers.txt"));

        while (scanner.hasNextLine()) {
            numbers1.add(scanner.nextInt());
        }

        System.out.println("Изначальный список чисел: " + numbers1);

        int number;

        for (int i = 0; i < numbers1.size(); ) {
            number = numbers1.get(i);

            if (number % 2 == 0) {
                numbers1.remove(i);
                continue;
            }

            i++;
        }

        System.out.println("Список без четных чисел: " + numbers1);

        ArrayList<Integer> numbers2 = new ArrayList<>(numbers1);


        for (int i = 0; i < numbers2.size(); ) {
            number = numbers2.get(i);

            if (numbers2.lastIndexOf(number) != numbers2.indexOf(number)) {
                numbers2.remove(numbers2.lastIndexOf(number));
                continue;
            }

            i++;
        }

        System.out.println("Список чисел без повторения: " + numbers2);
    }
}