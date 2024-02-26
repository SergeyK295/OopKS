package ru.academits.kolesnikov.shapes;

public class Square implements Shapes {
    private final double sideLength;

    public Square(double width) {
        this.sideLength = width;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public double getHeight() {
        return sideLength;
    }

    @Override
    public double getArea() {
        return sideLength * sideLength;
    }

    @Override
    public double getPerimeter() {
        return sideLength * 4;
    }

    @Override
    public String toString() {
        return "Фигура - квадрат" + System.lineSeparator() + "Длина стороны = " + sideLength + System.lineSeparator() + "Площадь = " + getArea() + System.lineSeparator() + "Периметр = " + getPerimeter();
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(sideLength);
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

        Square s = (Square) o;
        return sideLength == s.sideLength;
    }
}