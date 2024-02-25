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
            System.out.println("Нет диапазона пересечения");
        } else {
            System.out.println("Интервал диапазона пресечения: " + intersection);
            System.out.println("Длина диапазона пресечения = " + intersection.getLength());
        }

        System.out.println();

        Range[] merge = range1.getUnion(range2);

        if (merge.length == 1) {
            System.out.println("Интервал объединенного диапазона: " + Arrays.toString(merge));
            System.out.println("Длина объединенного диапазона = " + merge[0].getLength());
        } else {
            System.out.println("Нет диапазона пересечения для объединения интервалов");
            System.out.println("Интервалы двух диапазонов: " + Arrays.toString(merge));
            System.out.println("Длина первого диапазона = " + merge[0].getLength());
            System.out.println("Длина второго диапазона = " + merge[1].getLength());
        }

        System.out.println();

        Range[] difference = range1.getDifference(range2);

        if (difference.length == 0) {
            System.out.println("Интервалы диапазонов равны");
            System.out.println("Разность диапазонов: " + Arrays.toString(difference));
        } else if (difference.length == 1) {
            System.out.println("Интервал разности двух диапазонов: " + Arrays.toString(difference));
            System.out.println("Длина разности двух диапазонов  = " + difference[0].getLength());
        } else {
            System.out.println("Результатом вычитания двух диапазонов является 2 диапазона");
            System.out.println("Интервалы двух диапазонов: " + Arrays.toString(difference));
            System.out.println("Длина первого диапазона = " + difference[0].getLength());
            System.out.println("Длина второго диапазона = " + difference[1].getLength());
        }
    }
}