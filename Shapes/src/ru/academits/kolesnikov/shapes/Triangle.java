package ru.academits.kolesnikov.shapes;

public class Triangle implements Shapes {
    private final double x1;
    private final double y1;
    private final double x2;
    private final double y2;
    private final double x3;
    private final double y3;

    public Triangle(double x1, double x2, double x3, double y1, double y2, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
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

    public double getSideLength(double n1, double n2, double n3) {
        double max = Math.max(n1, Math.max(n2, n3));
        double min = Math.min(n1, Math.min(n2, n3));
        return max - min;
    }

    @Override
    public double getWidth() {
        return getSideLength(x1, x2, x3);
    }

    @Override
    public double getHeight() {
        return getSideLength(y1, y2, y3);
    }

    @Override
    public double getArea() {
        double side1Length = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        double side2Length = Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
        double side3Length = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));

        double triangleSemiPerimeter = (side1Length + side2Length + side3Length) / 2;
        return Math.sqrt(triangleSemiPerimeter * (triangleSemiPerimeter - side1Length) *
                (triangleSemiPerimeter - side2Length) * (triangleSemiPerimeter - side3Length));
    }

    @Override
    public double getPerimeter() {
        double side1Length = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        double side2Length = Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
        double side3Length = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));

        return side1Length + side2Length + side3Length;
    }

    @Override
    public String toString() {
        String vertexCoordinates = "(" + x1 + ";" + y1 + "), " + "(" + x2 + ";" + y2 + "), " + "(" + x3 + ";" + y3 + ")";
        return "Фигура - треугольник" + System.lineSeparator() + "Координаты вершин: " + vertexCoordinates + System.lineSeparator() + "Площадь = " + getArea() + System.lineSeparator() + "Периметр = " + getPerimeter();
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