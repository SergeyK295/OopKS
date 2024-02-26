package ru.academits.kolesnikov.shapes;

public class Rectangle implements Shapes {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return height * width;
    }

    @Override
    public double getPerimeter() {
        return (width + height) * 2;
    }

    @Override
    public String toString() {
        String newString = System.lineSeparator();
        return "Фигура - прямоугольник" + newString + "Ширина = " + getWidth() + newString + "Высота = " + getHeight() + newString + "Площадь = " + getArea() + newString + "Периметр = " + getPerimeter();
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(width);
        hash = prime * hash + Double.hashCode(height);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        Rectangle r = (Rectangle) o;
        return width == r.width && height == r.height;
    }
}