package ru.academits.kolesnikov.objective;

import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Objective {
    public static void main(String[] args) {
        DoubleStream squares = IntStream.iterate(0, x -> x + 1)
                .mapToDouble(Math::sqrt);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите количество элементов для вычисления квадратных корней:");
        int itemsCount = scanner.nextInt();

        squares.limit(itemsCount)
                .forEach(System.out::println);
    }
}
