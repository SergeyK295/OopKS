package ru.academits.kolesnikov.root_numbers_stream;

import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class RootNumbersStream {
    public static void main(String[] args) {
        DoubleStream squareRoots = IntStream.iterate(0, x -> x + 1).mapToDouble(Math::sqrt);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Укажите количество элементов для вычисления квадратных корней:");
        int itemsCount = scanner.nextInt();

        squareRoots.limit(itemsCount).forEach(System.out::println);
    }
}
