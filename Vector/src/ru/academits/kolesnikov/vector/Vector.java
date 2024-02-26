package ru.academits.kolesnikov.vector;

import java.util.Arrays;

public class Vector {
    private double[] array;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора равна <= 0");
        }

        array = new double[n];

        for (int i = 0; i < n; i++) {
            array[i] = 0;
        }
    }

    public Vector(Vector vector) {
        this.array = new double[vector.array.length];

        for (int i = 0, n = vector.array.length; i < n; i++) {
            this.array[i] = vector.array[i];
        }
    }

    public Vector(double[] array) {
        this.array = new double[array.length];

        for (int i = 0, n = array.length; i < n; i++) {
            this.array[i] = array[i];
        }
    }

    public Vector(int n, double[] array) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора равна <= 0");
        }

        this.array = new double[n];

        for (int i = 0, k = array.length; i < k; i++) {
            this.array[i] = array[i];
        }
    }

    public int getSize() {
        return array.length;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    public void vectorAddition(Vector vector) {
        if (this.array.length < vector.array.length) {
            Vector vector2 = new Vector(vector.array.length, this.array);
            this.array = vector2.array;
        }

        for (int i = 0; i < vector.array.length; i++) {
            this.array[i] += vector.array[i];
        }
    }

    public void vectorSubtraction(Vector vector) {
        if (this.array.length < vector.array.length) {
            Vector vector2 = new Vector(vector.array.length, this.array);
            this.array = vector2.array;
        }

        for (int i = 0; i < vector.array.length; i++) {
            this.array[i] -= vector.array[i];
        }
    }

    public void vectorMultiplication(double scalar) {
        for (int i = 0; i < array.length; i++) {
            this.array[i] *= scalar;
        }
    }

    public void vectorReverse() {
        for (int i = 0; i < array.length; i++) {
            this.array[i] = array[i] * (-1);
        }
    }

    public double getLength() {
        double vectorSize = 0;

        for (int i = 0; i < array.length; i++) {
            vectorSize += Math.pow(this.array[i], 2);
        }

        return Math.sqrt(vectorSize);
    }

    public double getComponentVectorIndex(int i) {
        return array[i];
    }

    public void setComponentVectorIndex(double x, int i) {
        array[i] = x;
    }

    @Override
    public int hashCode() {
        final int prime = 29;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(array);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Vector v = (Vector) o;

        if (v.array.length != this.array.length) {
            return false;
        }

        for (int i = 0; i < this.array.length; i++) {
            if (v.array[i] != this.array[i]) {
                return false;
            }
        }

        return true;
    }

    public static Vector vectorAddition(Vector vector1, Vector vector2) {
        int maxLengthVector = Math.max(vector1.array.length, vector2.array.length);
        Vector newVector = new Vector(maxLengthVector);

        for (int i = 0; i < vector1.array.length; i++) {
            newVector.array[i] = vector1.array[i];
        }

        for (int i = 0; i < vector2.array.length; i++) {
            newVector.array[i] += vector2.array[i];
        }

        return newVector;
    }

    public static Vector vectorSubtraction(Vector vector1, Vector vector2) {
        int maxLengthVector = Math.max(vector1.array.length, vector2.array.length);
        Vector newVector = new Vector(maxLengthVector);

        for (int i = 0; i < vector1.array.length; i++) {
            newVector.array[i] = vector1.array[i];
        }

        for (int i = 0; i < vector2.array.length; i++) {
            newVector.array[i] -= vector2.array[i];
        }

        return newVector;
    }

    public static double vectorsProductScalar(Vector vector1, Vector vector2) {
        if (vector1.array.length != vector2.array.length) {
            if (vector1.array.length < vector2.array.length) {
                Vector vector = new Vector(vector2.array.length, vector1.array);
                vector1.array = vector.array;
            } else {
                Vector vector = new Vector(vector1.array.length, vector2.array);
                vector2.array = vector.array;
            }
        }

        double vectorsProductScalar = 0;

        for (int i = 0; i < vector1.array.length; i++) {
            vectorsProductScalar += vector1.array[i] * vector2.array[i];
        }

        return vectorsProductScalar;
    }
}