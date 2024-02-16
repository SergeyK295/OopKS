package ru.academits.kolesnikov.range_main;

import ru.academits.kolesnikov.range.Range;

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

        Range rangeIntersect = range1.getRangeIntersect(range1, range2);

        if (rangeIntersect == null) {
            System.out.println("Нет диапазона пересечения");
        } else {
            System.out.println("Интервал диапазона пресечения: от " + rangeIntersect.getFrom() + ", до " + rangeIntersect.getTo());
            System.out.println("Длина диапазона пресечения = " + (rangeIntersect.getTo() - rangeIntersect.getFrom()));
        }

        System.out.println();

        Range[] rangeMerge = range1.getRangeMerge(range1, range2);

        if (rangeMerge.length == 1) {
            System.out.println("Интервал объединенного диапазона: от " + rangeMerge[0].getFrom() + ", до " + rangeMerge[0].getTo());
            System.out.println("Длина объединенного диапазона = " + (rangeMerge[0].getTo() - rangeMerge[0].getFrom()));
        } else {
            System.out.println("Нет диапазона пересечения для объединения интервалов");
        }

        System.out.println();

        Range[] rangeDifference = range1.getRangeDifference(range1, range2);

        if (rangeDifference [0] == null) {
            System.out.println("Интервалы диапазонов равны");
            System.out.println("Разность диапазонов = 0");
        } else if (rangeDifference.length == 1) {
            System.out.println("Интервал разности двух диапазонов: от " + rangeDifference[0].getFrom() + ", до " + rangeDifference[0].getTo());
            System.out.println("Длина разности двух диапазонов  = " + (rangeDifference[0].getTo() - rangeDifference[0].getFrom()));
        } else {
            System.out.println("Результатом вычитания двух диапазонов является 2 диапазона");
            System.out.println("Интервал первого диапазона: от " + rangeDifference[0].getFrom() + ", до " + rangeDifference[0].getTo());
            System.out.println("Длина первого диапазона = " + (rangeDifference[0].getTo() - rangeDifference[0].getFrom()));
            System.out.println("Интервал второго диапазона: от " + rangeDifference[1].getFrom() + ", до " + rangeDifference[1].getTo());
            System.out.println("Длина второго диапазона = " + (rangeDifference[1].getTo() - rangeDifference[1].getFrom()));
        }
    }
}