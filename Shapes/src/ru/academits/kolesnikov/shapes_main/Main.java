package ru.academits.kolesnikov.shapes_main;

import ru.academits.kolesnikov.shapes_comparators.ShapeAreaComparator;
import ru.academits.kolesnikov.shapes_comparators.ShapePerimeterComparator;
import ru.academits.kolesnikov.shapes.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Square(5),
                new Square(10),
                new Rectangle(10, 8),
                new Rectangle(5, 10),
                new Circle(5),
                new Circle(1),
                new Triangle(1, 1, 10, 1, 10, 0)
        };

        Arrays.sort(shapes, new ShapeAreaComparator());
        System.out.println("Информация о фигуре с наибольшей площадью:");
        System.out.println(shapes[shapes.length - 1]);

        System.out.println();

        Arrays.sort(shapes, new ShapePerimeterComparator());
        System.out.println("Информация о фигуре со вторым по величине периметром:");
        System.out.println(shapes[shapes.length - 2]);
    }
}