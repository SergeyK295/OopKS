package ru.academits.kolesnikov.matrix;

import java.util.Arrays;

import ru.academits.kolesnikov.vector.Vector;

public class Matrix {
    private final Vector[] matrix;

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
                newArray[k] = vectors[i].getComponent(k);
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
        if (i >= matrix.length) {
            throw new IllegalArgumentException("Нет вектора с таким индексом в матрице");
        }

        return matrix[i];
    }

    public void setVectorStringIndex(Vector vector, int i) {
        if (i >= matrix.length) {
            throw new IllegalArgumentException("Нет вектора с таким индексом в матрице");
        }

        matrix[i] = vector;
    }

    public Vector getVectorColumnIndex(int i) {
        if (i >= matrix.length) {
            throw new IllegalArgumentException("Нет вектора с таким индексом в матрице");
        }

        int sizeVector = matrix[0].getSize();
        Vector newVector = new Vector(matrix.length);

        for (int k = 0; k < matrix.length; k++) {
            newVector.setComponent(k, matrix[k].getComponent(i));
        }

        return newVector;
    }

    public Matrix transposeMatrix() {
        Matrix newMatrix = new Matrix(matrix[0].getSize(), matrix.length);

        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[i].getSize(); k++) {
                newMatrix.getVectorIndex(k).setComponent(i, getVectorIndex(i).getComponent(k));
            }
        }

        return newMatrix;
    }

    public void multiplicationByScalar(double scalar) {
        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[i].getSize(); k++) {
                matrix[i].setComponent(k, getVectorIndex(i).getComponent(k) * scalar);
            }
        }
    }

    public void multiplicationByVector(Vector vector) {
        if (vector.getSize() != matrix.length + 1) {
            throw new IllegalArgumentException("Векторы разной длины");
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < vector.getSize(); j++) {
                double component = matrix[i].getComponent(j) * vector.getComponent(j);
                matrix[i].setComponent(j, component);
            }
        }
    }

    public void additionMatrix(Matrix matrix) {
        if (this.matrix.length != matrix.matrix.length && this.matrix[0].getSize() != matrix.matrix[0].getSize()) {
            throw new IllegalArgumentException("Матрицы разного размера");
        }

        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].getSize(); j++) {
                double component = this.matrix[i].getComponent(j) + matrix.matrix[i].getComponent(j);
                this.matrix[i].setComponent(j, component);
            }
        }
    }

    public void subtractionMatrix(Matrix matrix) {
        if (this.matrix.length != matrix.matrix.length && this.matrix[0].getSize() != matrix.matrix[0].getSize()) {
            throw new IllegalArgumentException("Матрицы разного размера");
        }

        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].getSize(); j++) {
                double component = this.matrix[i].getComponent(j) - matrix.matrix[i].getComponent(j);
                this.matrix[i].setComponent(j, component);
            }
        }
    }

    public double matrixDeterminant() {
        Matrix matrixCopy = new Matrix(this);

        double matrixDeterminant = 1;

        for (int i = 0; i < matrixCopy.getMatrixSize()[0]; i++) {
            if (matrixCopy.getVectorIndex(i).getComponent(i) == 0) {
                for (int j = i; ; j++) {
                    if (matrixCopy.getVectorIndex(j).getComponent(i) == 0) {
                        continue;
                    }

                    Vector temp = new Vector(matrixCopy.getVectorIndex(i));
                    matrixCopy.setVectorStringIndex(matrixCopy.getVectorIndex(j), i);
                    matrixCopy.setVectorStringIndex(temp, j);

                    matrixDeterminant *= -1;

                    i = j;
                    break;
                }
            }

            Vector temp2 = new Vector(matrixCopy.getVectorIndex(i));
            double scalar = matrixCopy.getVectorIndex(i).getComponent(i);

            matrixCopy.getVectorIndex(i).multiply(1 / scalar);

            for (int k = i + 1; k < getMatrixSize()[0]; k++) {
                if (matrixCopy.getVectorIndex(k).getComponent(i) == 0) {
                    continue;
                }

                scalar = matrixCopy.getVectorIndex(k).getComponent(i);

                Vector temp3 = new Vector(matrixCopy.getVectorIndex(i));
                matrixCopy.getVectorIndex(i).multiply(scalar);
                matrixCopy.getVectorIndex(k).subtract(matrixCopy.getVectorIndex(i));
                matrixCopy.setVectorStringIndex(temp3, i);
            }

            matrixCopy.setVectorStringIndex(temp2, i);
        }

        for (int l = 0; l < matrixCopy.getMatrixSize()[0]; l++) {
            matrixDeterminant *= matrixCopy.getVectorIndex(l).getComponent(l);
        }

        return matrixDeterminant;
    }

    public static Matrix additionMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrix.length != matrix2.matrix.length && matrix1.matrix[0].getSize() != matrix2.matrix[0].getSize()) {
            throw new IllegalArgumentException("Матрицы разного размера");
        }

        Matrix newMatrix = new Matrix(matrix1.matrix.length, matrix1.matrix[0].getSize());

        for (int i = 0; i < matrix1.matrix.length; i++) {
            for (int j = 0; j < matrix1.matrix[i].getSize(); j++) {
                double component = matrix1.matrix[i].getComponent(j) + matrix2.matrix[i].getComponent(j);
                newMatrix.matrix[i].setComponent(j, component);
            }
        }

        return newMatrix;
    }

    public static Matrix subtractionMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrix.length != matrix2.matrix.length && matrix1.matrix[0].getSize() != matrix2.matrix[0].getSize()) {
            throw new IllegalArgumentException("Матрицы разного размера");
        }

        Matrix newMatrix = new Matrix(matrix1.matrix.length, matrix1.matrix[0].getSize());

        for (int i = 0; i < matrix1.matrix.length; i++) {
            for (int j = 0; j < matrix1.matrix[i].getSize(); j++) {
                double component = matrix1.matrix[i].getComponent(j) - matrix2.matrix[i].getComponent(j);
                newMatrix.matrix[i].setComponent(j, component);
            }
        }

        return newMatrix;
    }

    public static Matrix multiplicationByMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.matrix.length != matrix2.matrix.length && matrix1.matrix[0].getSize() != matrix2.matrix[0].getSize()) {
            throw new IllegalArgumentException("Матрицы разного размера");
        }

        Matrix newMatrix = new Matrix(matrix1.matrix.length, matrix1.matrix[0].getSize());

        for (int i = 0; i < matrix1.matrix.length; i++) {
            for (int j = 0; j < matrix1.matrix[i].getSize(); j++) {
                double component = matrix1.matrix[i].getComponent(j) * matrix2.matrix[i].getComponent(j);
                newMatrix.matrix[i].setComponent(j, component);
            }
        }

        return newMatrix;
    }
}