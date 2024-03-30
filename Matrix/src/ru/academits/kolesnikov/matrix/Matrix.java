package ru.academits.kolesnikov.matrix;

import ru.academits.kolesnikov.vector.Vector;

import static ru.academits.kolesnikov.vector.Vector.getScalarProduct;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowCount, int columnCount) {
        if (columnCount <= 0) {
            throw new IllegalArgumentException("Количество столбцов матрицы: " + columnCount + " должно быть больше 0");
        }

        if (rowCount <= 0) {
            throw new IllegalArgumentException("Количество строк матрицы: " + rowCount + " должно быть больше 0");
        }

        rows = new Vector[rowCount];

        for (int i = 0; i < rowCount; i++) {
            rows[i] = new Vector(columnCount);
        }
    }

    public Matrix(Matrix matrix) {
        this.rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            this.rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Количество строк матрицы: " + array.length + " должно быть больше 0");
        }

        int maxRowsCount = 0;

        for (double[] doubles : array) {
            if (maxRowsCount < doubles.length) {
                maxRowsCount = doubles.length;
            }
        }

        if (maxRowsCount == 0) {
            throw new IllegalArgumentException("Количество столбцов матрицы: " + array[0].length + " должно быть больше 0");
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Количество строк матрицы: " + vectors.length + " должно быть больше 0");
        }

        int maxRowsCount = 0;

        for (Vector doubles : vectors) {
            if (maxRowsCount < doubles.getSize()) {
                maxRowsCount = doubles.getSize();
            }
        }

        if (maxRowsCount == 0) {
            throw new IllegalArgumentException("Количество столбцов матрицы: " + vectors[0].getSize() + " должно быть больше 0");
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            int size = vectors[i].getSize();
            double[] components = new double[size];

            for (int j = 0; j < size; j++) {
                components[j] = vectors[i].getComponent(j);
            }

            rows[i] = new Vector(maxRowsCount, components);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        char comma = ',';
        int size = rows.length - 1;

        for (int i = 0; i < size; i++) {
            stringBuilder.append(rows[i]).append(comma);
        }

        stringBuilder.append(rows[size]).append("}");
        return stringBuilder.toString();
    }

    public int getRowsCount() {
        return rows.length;
    }

    public int getColumnsCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("В матрице нет строки с индексом: " + index + System.lineSeparator() + "Допустимое значение от 0 по " + index);
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("В матрице нет строки с индексом: " + index + System.lineSeparator() + "Допустимое значение от 0 по " + index);
        }

        if (vector.getSize() != rows[0].getSize()) {
            throw new IndexOutOfBoundsException("Размер передаваемого вектора (" + vector.getSize() + ") должен совпадать с размером строки матрицы: " + rows[0].getSize());
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("В матрице нет столбца с индексом: " + index + System.lineSeparator() + "Допустимое значение от 0 по " + index);
        }

        Vector column = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            column.setComponent(i, rows[i].getComponent(index));
        }

        return column;
    }

    public void transpose() {
        Vector[] tamp = new Vector[getColumnsCount()];

        for (int i = 0; i < getColumnsCount(); i++) {
            tamp[i] = getColumn(i);
        }

        rows = new Vector[getColumnsCount()];

        for (int i = 0; i < tamp.length; i++) {
            rows[i] = new Vector(tamp[i]);
        }
    }

    public void multiplyByScalar(double scalar) {
        for (Vector row : rows) {
            row.multiply(scalar);
        }
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Количество столбцов матрицы (" + getColumnsCount() + "), должно соответствовать размеру вектора: " + vector.getSize());
        }

        Vector result = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            result.setComponent(i, getScalarProduct(rows[i], vector));
        }

        return result;
    }

    private static void checkMatricesSizeEquality(Matrix matrix1, Matrix matrix2) {
        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Матрицы разного размера. Матрица №1: " + matrix1.rows.length + ", " + matrix1.getColumnsCount() +
                    ". Матрица №1: " + matrix2.rows.length + ", " + matrix2.getColumnsCount());
        }
    }

    public void add(Matrix matrix) {
        checkMatricesSizeEquality(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkMatricesSizeEquality(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public double getDeterminant() {
        if (getColumnsCount() != rows.length) {
            throw new IllegalStateException("Матрица не квадратная: количество строк " + rows.length + ", количество столбцов " + getColumnsCount());
        }

        Matrix matrixCopy = new Matrix(this);
        final double epsilon = 0.00001;
        boolean isDeterminantZero = false;

        for (int i = 0; i < matrixCopy.rows.length - 1; i++) {
            if (Math.abs(matrixCopy.rows[i].getComponent(i)) <= epsilon) {
                isDeterminantZero = true;

                for (int j = i + 1; j < matrixCopy.getColumnsCount(); j++) {
                    if (Math.abs(matrixCopy.rows[j].getComponent(i)) > epsilon) {
                        matrixCopy.rows[i].add(matrixCopy.rows[j]);

                        isDeterminantZero = false;
                        break;
                    }
                }
            }
        }

        if (isDeterminantZero) {
            return 0;
        }

        for (int i = 0; i < matrixCopy.rows.length; i++) {
            Vector initialRow = matrixCopy.rows[i];
            Vector row = matrixCopy.rows[i];
            row.multiply(1 / matrixCopy.rows[i].getComponent(i));
            matrixCopy.setRow(i, row);

            for (int j = i + 1; j < matrixCopy.rows.length; j++) {
                row.multiply(matrixCopy.rows[i].getComponent(i));
                Vector nextRow = matrixCopy.rows[i];
                nextRow.subtract(row);
                matrixCopy.setRow(j, nextRow);
                row = matrixCopy.rows[i];
            }

            matrixCopy.setRow(i, initialRow);
        }

        double determinant = 1;

        for (int i = 0; i < matrixCopy.rows.length; i++) {
            determinant *= matrixCopy.rows[i].getComponent(i);
        }

        if (Math.abs(determinant) <= epsilon || determinant != determinant) {
            return 0;
        }

        return determinant;
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkMatricesSizeEquality(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);

        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkMatricesSizeEquality(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);

        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("Число столбцов в первой матрице множителя (" + matrix1.getColumnsCount() +
                    ") должно совпадать с числом строк второй матрицы множителя (" + matrix2.rows.length + ")");
        }

        double[][] result = new double[matrix1.rows.length][matrix2.getColumnsCount()];

        for (int i = 0; i < matrix1.rows.length; i++) {
            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                result[i][j] = Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j));
            }
        }

        return new Matrix(result);
    }
}