package ru.academits.kolesnikov.vector;

import java.util.Arrays;

public class Vector {
    private double[] vectorComponents;

    public Vector(int vectorSize) {
        if (vectorSize <= 0) {
            throw new IllegalArgumentException("Размерность вектора: " + vectorSize + " должна быть больше 0");
        }

        vectorComponents = new double[vectorSize];
    }

    public Vector(Vector vector) {
        vectorComponents = Arrays.copyOf(vector.vectorComponents, vector.vectorComponents.length);
    }

    public Vector(double[] vectorComponents) {
        if (vectorComponents.length == 0) {
            throw new IllegalArgumentException("Размер передаваемого массива = 0");
        }

        this.vectorComponents = new double[vectorComponents.length];

        for (int i = 0; i < vectorComponents.length; i++) {
            this.vectorComponents[i] = vectorComponents[i];
        }
    }

    public Vector(int vectorSize, double[] vectorComponents) {
        if (vectorSize <= 0) {
            throw new IllegalArgumentException("Размерность вектора равна <= 0");
        }

        this.vectorComponents = Arrays.copyOf(vectorComponents, vectorSize);
    }

    public int getSize() {
        return vectorComponents.length;
    }

    @Override
    public String toString() {
        StringBuilder vectorComponentsString = new StringBuilder("{" + vectorComponents[0]);

        for (int i = 1; i < vectorComponents.length - 1; i++) {
            vectorComponentsString.append(", ").append(vectorComponents[i]);
        }

        vectorComponentsString.append("}");

        return vectorComponentsString.toString();
    }

    public void add(Vector vector) {
        if (vectorComponents.length < vector.vectorComponents.length) {
            vectorComponents = Arrays.copyOf(vectorComponents, vector.vectorComponents.length);
        }

        for (int i = 0; i < vector.vectorComponents.length; i++) {
            vectorComponents[i] += vector.vectorComponents[i];
        }
    }

    public void subtract(Vector vector) {
        if (vectorComponents.length < vector.vectorComponents.length) {
            vectorComponents = Arrays.copyOf(vectorComponents, vector.vectorComponents.length);
        }

        for (int i = 0; i < vector.vectorComponents.length; i++) {
            vectorComponents[i] -= vector.vectorComponents[i];
        }
    }

    public void multiply(double scalar) {
        for (int i = 0; i < vectorComponents.length; i++) {
            vectorComponents[i] *= scalar;
        }
    }

    public void unfold() {
        multiply(-1);
    }

    public double getLength() {
        double vectorLength = 0;

        for (double vectorComponent : vectorComponents) {
            vectorLength += vectorComponent * vectorComponent;
        }

        return Math.sqrt(vectorLength);
    }

    public double getComponent(int index) {
        return vectorComponents[index];
    }

    public void setComponent(int index, double Component) {
        vectorComponents[index] = Component;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(vectorComponents);
    }

    @Override
    public boolean equals(Object o) {
        Vector v = (Vector) o;

        return Arrays.equals(this.vectorComponents, v.vectorComponents);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector resultantVector = new Vector(vector1);
        resultantVector.add(vector2);

        return resultantVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector resultantVector = new Vector(vector1);
        resultantVector.subtract(vector2);

        return resultantVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        double scalarProduct = 0;

        for (int i = 0; i < Math.min(vector1.vectorComponents.length, vector2.vectorComponents.length); i++) {
            scalarProduct += vector1.vectorComponents[i] * vector2.vectorComponents[i];
        }

        return scalarProduct;
    }
}