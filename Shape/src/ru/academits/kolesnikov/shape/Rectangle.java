package ru.academits.kolesnikov.shape;

public class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getArea() {
        return height * width;
    }

    public double getPerimeter() {
        return (width + height) * 2;
    }

    public String toString() {
        return "Информация о фигуре:\n" + "Фигура - прямоугольник\n" + "Ширина = " + getWidth() + "\n" + "Высота = " + getHeight() + "\n" +
                "Площадь = " + getArea() + "\n" + "Периметр = " + getPerimeter();
    }

    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(width) + Double.hashCode(height);
        return hash;
    }

    public boolean equals(Object o) {
        if (o == this) return true;

        if (o == null || o.getClass() != this.getClass()) return false;

        Rectangle r = (Rectangle) o;
        return width == r.width && height == r.height;
    }
}