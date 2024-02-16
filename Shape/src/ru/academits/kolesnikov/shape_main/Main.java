package ru.academits.kolesnikov.shape_main;

import ru.academits.kolesnikov.shape.*;

import java.util.*;

public class Main {

    public static void areaSortArea(Shape[] arrayShapes) {
        Arrays.sort(arrayShapes, (first, second) -> {
            if (first.getArea() != second.getArea()) {
                return (int) (second.getArea() - first.getArea());
            }
            return 0;
        });
    }

    public static void areaSortPerimeter(Shape[] arrayShapes) {
        Arrays.sort(arrayShapes, (first, second) -> {
            if (first.getPerimeter() != second.getPerimeter()) {
                return (int) (second.getPerimeter() - first.getPerimeter());
            }
            return 0;
        });
    }

    public static void main(String[] args) {
        Square square1 = new Square(5);
        Square square2 = new Square(10);

        Rectangle rectangle1 = new Rectangle(10, 8);
        Rectangle rectangle2 = new Rectangle(5, 10);

        Circle circle1 = new Circle(5);
        Circle circle2 = new Circle(1);

        Triangle triangle = new Triangle(1, 1, 10, 1, 10, 1);

        Shape[] arrayShapes = {square1, square2, rectangle1, rectangle2, circle1, circle2, triangle};

        areaSortArea(arrayShapes);
        System.out.println("Информация о фигуре с наибольшей площадью:");
        System.out.println(arrayShapes[0]);

        areaSortPerimeter(arrayShapes);
        System.out.println("Информация о фигуре со вторым по величине периметром:");
        System.out.println(arrayShapes[1]);
    }
}
