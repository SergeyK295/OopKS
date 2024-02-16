package ru.academits.kolesnikov.shape;

public class Square implements Shape {
    private double width;

    public Square(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return width;
    }

    public double getArea() {
        return width * width;
    }

    public double getPerimeter() {
        return width * 4;
    }

    public String toString() {
        return "Информация о фигуре:\n" + "Фигура - квадрат\n" + "Ширина = " + getWidth() + "\n" + "Высота = " + getHeight() + "\n" +
                "Площадь = " + getArea() + "\n" + "Периметр = " + getPerimeter();
    }

    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(width);
        return hash;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Square s = (Square) o;
        return width == s.width;
    }
}