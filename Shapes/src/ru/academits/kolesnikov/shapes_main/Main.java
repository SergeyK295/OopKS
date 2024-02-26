package ru.academits.kolesnikov.shapes_main;

import java.util.Comparator;

import ru.academits.kolesnikov.comparator.AreaShapesComparator;
import ru.academits.kolesnikov.shapes.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Square square1 = new Square(5);
        Square square2 = new Square(10);

        Rectangle rectangle1 = new Rectangle(10, 8);
        Rectangle rectangle2 = new Rectangle(5, 10);

        Circle circle1 = new Circle(5);
        Circle circle2 = new Circle(1);

        Triangle triangle = new Triangle(1, 1, 10, 1, 10, 1);

        Shapes[] shapes = {square1, square2, rectangle1, rectangle2, circle1, circle2, triangle};

        Comparator<Shapes> sortArea = new AreaShapesComparator();
        Arrays.sort(shapes, sortArea);
        System.out.println("Информация о фигуре с наибольшей площадью:");
        System.out.println(shapes[0]);

        System.out.println();

        Comparator<Shapes> sortPerimeter = new AreaShapesComparator();
        Arrays.sort(shapes, sortPerimeter);
        System.out.println("Информация о фигуре со вторым по величине периметром:");
        System.out.println(shapes[1]);
    }
}
