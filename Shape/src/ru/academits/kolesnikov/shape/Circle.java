package ru.academits.kolesnikov.shape;

import static java.lang.Math.PI;

public class Circle implements Shape {

    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getWidth() {
        return radius * 2;
    }

    public double getHeight() {
        return radius * 2;
    }

    public double getArea() {
        return PI * (radius * radius);
    }

    public double getPerimeter() {
        return 2 * PI * radius;
    }

    public String toString() {
        return "Информация о фигуре:\n" + "Фигура - круг\n" + "Ширина = " + getWidth() + "\n" + "Высота = " + getHeight() + "\n" +
                "Площадь = " + getArea() + "\n" + "Периметр = " + getPerimeter();
    }

    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(radius);
        return hash;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Circle c = (Circle) o;
        return radius == c.radius;
    }
}