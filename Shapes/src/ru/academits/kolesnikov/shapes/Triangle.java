package ru.academits.kolesnikov.shapes;

public class Triangle implements Shape {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double x3;
    private final double y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }

    public double getX3() {
        return x3;
    }

    public double getY1() {
        return y1;
    }

    public double getY2() {
        return y2;
    }

    public double getY3() {
        return y3;
    }

    private static double getMaxDistanceBetweenNumbers(double number1, double number2, double number3) {
        double max = Math.max(number1, Math.max(number2, number3));
        double min = Math.min(number1, Math.min(number2, number3));
        return max - min;
    }

    @Override
    public double getWidth() {
        return getMaxDistanceBetweenNumbers(x1, x2, x3);
    }

    @Override
    public double getHeight() {
        return getMaxDistanceBetweenNumbers(y1, y2, y3);
    }

    private static double getSideLength(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    @Override
    public double getArea() {
        double sideLength1 = getSideLength(x1, y1, x2, y2);
        double sideLength2 = getSideLength(x2, y2, x3, y3);
        double sideLength3 = getSideLength(x3, y3, x1, y1);

        double triangleSemiPerimeter = (sideLength1 + sideLength2 + sideLength3) / 2;
        return Math.sqrt(triangleSemiPerimeter * (triangleSemiPerimeter - sideLength1) * (triangleSemiPerimeter - sideLength2) * (triangleSemiPerimeter - sideLength3));
    }

    @Override
    public double getPerimeter() {
        return getSideLength(x1, y1, x2, y2) + getSideLength(x2, y2, x3, y3) + getSideLength(x3, y3, x1, y1);
    }

    @Override
    public String toString() {
        return "Треугольник: координаты вершин (" + x1 + "; " + y1 + "), (" + x2 + "; " + y2 + "), (" + x3 + "; " + y3 + "), площадь = " + getArea() + ", периметр = " + getPerimeter();
    }

    @Override
    public int hashCode() {
        final int prime = 5;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1);
        hash = prime * hash + Double.hashCode(y1);
        hash = prime * hash + Double.hashCode(x2);
        hash = prime * hash + Double.hashCode(y2);
        hash = prime * hash + Double.hashCode(x3);
        hash = prime * hash + Double.hashCode(y3);
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

        Triangle t = (Triangle) o;
        return x1 == t.x1 && x2 == t.x2 && x3 == t.x3 && y1 == t.y1 && y2 == t.y2 && y3 == t.y3;
    }
}