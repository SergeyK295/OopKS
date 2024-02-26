package ru.academits.kolesnikov.matrix;


import java.util.Arrays;

import ru.academits.kolesnikov.vector.Vector;

public class Matrix {
    private Vector[] matrix;

    public Matrix(int n, int m) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность матрицы <= 0");
        }

        matrix = new Vector[n];

        for (int i = 0; i < n; i++) {
            matrix[i] = new Vector(m);
        }
    }

    public Matrix(Matrix matrix) {
        this.matrix = new Vector[matrix.matrix.length];

        for (int i = 0; i < matrix.matrix.length; i++) {
            this.matrix[i] = new Vector(matrix.matrix[i]);
        }
    }

    public Matrix(double[][] array) {
        matrix = new Vector[array.length];

        int maxLengthVector = array[0].length;

        for (int i = 1; i < array.length; i++) {
            if (maxLengthVector < array[i].length) {
                maxLengthVector = array[i].length;
            }
        }

        for (int i = 0; i < array.length; i++) {
            double[] newArray = new double[maxLengthVector];

            for (int k = 0; k < array[i].length; k++) {
                newArray[k] = array[i][k];
            }

            for (int k = array[i].length; k < maxLengthVector; k++) {
                newArray[k] = 0;
            }

            matrix[i] = new Vector(newArray);
        }
    }

    public Matrix(Vector[] vectors) {
        matrix = new Vector[vectors.length];

        int maxLengthVector = vectors[0].getSize();

        for (int i = 1; i < vectors.length; i++) {
            if (maxLengthVector < vectors[i].getSize()) {
                maxLengthVector = vectors[i].getSize();
            }
        }

        for (int i = 0; i < vectors.length; i++) {
            double[] newArray = new double[maxLengthVector];

            for (int k = 0; k < vectors[i].getSize(); k++) {
                newArray[k] = vectors[i].getComponentVectorIndex(k);
            }

            for (int k = vectors[i].getSize(); k < maxLengthVector; k++) {
                newArray[k] = 0;
            }

            matrix[i] = new Vector(newArray);
        }
    }

    @Override
    public String toString() {
        return Arrays.deepToString(matrix);
    }

    public int[] getMatrixSize() {
        int string = matrix.length;
        int column = matrix[0].getSize();

        return new int[]{string, column};
    }

    public Vector getVectorIndex(int i) {
        return matrix[i];
    }

    public void setVectorStringIndex(Vector vector, int i) {
        matrix[i] = vector;
    }

    public Vector setVectorColumnIndex(Vector vector, int i) {
        int sizeVector = matrix[0].getSize();
        Vector newVector = new Vector(matrix.length);

        for (int k = 0; k < matrix.length; k++) {
            newVector.setComponentVectorIndex(matrix[k].getComponentVectorIndex(i), k);
        }

        return newVector;
    }


}
