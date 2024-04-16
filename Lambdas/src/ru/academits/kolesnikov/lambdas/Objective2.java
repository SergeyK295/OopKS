package ru.academits.kolesnikov.lambdas;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Objective2 {
    public static void main(String[] args) {
        IntStream squares = IntStream.iterate(0, x -> x + 1).map(x -> x * x);
        System.out.println("Укажите количество элементов для вычисления квадратных корней");
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        squares.limit(count).forEach(System.out::println);
    }
}
