package ru.academits.kolesnikov.range_main;

import ru.academits.kolesnikov.range.Range;

import java.util.Arrays;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ведите начальное число диапазона:");
        double from1 = scanner.nextDouble();

        System.out.println("Ведите конечное число диапазона:");
        double to1 = scanner.nextDouble();

        System.out.println("Введите число для проверки принадлежности к диапазону:");
        double verifiedNumber = scanner.nextDouble();

        Range range1 = new Range(from1, to1);

        System.out.println("Длина диапазона = " + range1.getLength());
        System.out.println(range1.isInside(verifiedNumber) ? "Число принадлежит диапазону" : "Число не принадлежит диапазону");

        System.out.println();

        System.out.println("Ведите начальное число следующего диапазона:");
        double from2 = scanner.nextDouble();

        System.out.println("Ведите конечное число следующего диапазона:");
        double to2 = scanner.nextDouble();

        Range range2 = new Range(from2, to2);

        Range intersection = range1.getIntersection(range2);

        if (intersection == null) {
            System.out.println("Нет пересечения");
        } else {
            System.out.println("Результат пересечения: " + intersection);
            System.out.println("Длина диапазона пресечения = " + intersection.getLength());
        }

        System.out.println();

        Range[] union = range1.getUnion(range2);

        if (union.length == 1) {
            System.out.println("Объединенный диапазон: " + Arrays.toString(union));
            System.out.println("Длина объединенного диапазона = " + union[0].getLength());
        } else {
            System.out.println("Нет пересечения");
            System.out.println("Диапазоны: " + Arrays.toString(union));
            System.out.println("Длина первого диапазона = " + union[0].getLength());
            System.out.println("Длина второго диапазона = " + union[1].getLength());
        }

        System.out.println();

        Range[] difference = range1.getDifference(range2);
        System.out.println("Результат разности двух интервалов: " + Arrays.toString(difference));
    }
}