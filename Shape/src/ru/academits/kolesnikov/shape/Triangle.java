package ru.academits.kolesnikov.shape;

public class Triangle implements Shape {

    private double x1;
    private double x2;
    private double x3;

    private double y1;
    private double y2;
    private double y3;

    public Triangle(double x1, double x2, double x3, double y1, double y2, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }


    public double getWidth() {
        double maxX = Math.max(x1, Math.max(x2, x3));
        double minX = Math.min(x1, Math.min(x2, x3));
        return maxX - minX;
    }

    public double getHeight() {
        double maxX = Math.max(y1, Math.max(y2, y3));
        double minX = Math.min(y1, Math.min(y2, y3));
        return maxX - minX;
    }

    public double getArea() {
        double side1Length = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        double side2Length = Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
        double side3Length = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));

        double triangleSemiPerimeter = (side1Length + side2Length + side3Length) / 2;
        return Math.sqrt(triangleSemiPerimeter * (triangleSemiPerimeter - side1Length) *
                (triangleSemiPerimeter - side2Length) * (triangleSemiPerimeter - side3Length));
    }

    public double getPerimeter() {
        double side1Length = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        double side2Length = Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
        double side3Length = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));

        return side1Length + side2Length + side3Length;
    }

    public String toString() {
        return "Информация о фигуре:\n" + "Фигура - треугольник\n" + "Ширина = " + getWidth() + "\n" + "Высота = " + getHeight() + "\n" +
                "Площадь = " + getArea() + "\n" + "Периметр = " + getPerimeter();
    }

    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Double.hashCode(x1) + Double.hashCode(x2) + Double.hashCode(x3) + Double.hashCode(y1) + Double.hashCode(y2) + Double.hashCode(y3);
        return hash;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        Triangle t = (Triangle) o;
        return x1 == t.x1 && x2 == t.x2 && x3 == t.x3;
    }
}