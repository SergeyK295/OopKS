package ru.academits.kolesnikov.matrix;

import ru.academits.kolesnikov.vector.Vector;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

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

        rows = new Vector[columnCount];

        for (int i = 0; i < columnCount; i++) {
            rows[i] = new Vector(rowCount);
        }
    }

    public Matrix(Matrix rows) {
        this.rows = new Vector[rows.rows.length];

        for (int i = 0; i < rows.rows.length; i++) {
            this.rows[i] = new Vector(rows.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Количество строк матрицы: " + array.length + " должно быть больше 0");
        }

        if (array[0].length == 0) {
            throw new IllegalArgumentException("Количество столбцов матрицы: " + array[0].length + " должно быть больше 0");
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        rows = Arrays.copyOf(vectors, vectors.length);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (int i = 0; i < getRowCount() - 1; i++) {
            stringBuilder.append(rows[i].toString()).append(", \n");
        }

        stringBuilder.append(rows[getRowCount() - 1]).append("}");

        return stringBuilder.toString();
    }

    public int getRowCount() {
        return rows.length;
    }

    public int getColumnCount() {
        return rows[0].getSize();
    }

    public Vector getRow(int index) {
        if (index >= rows.length || index < 0) {
            throw new IndexOutOfBoundsException("В матрице нет строки с индексом: " + index);
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (index >= rows.length || index < 0) {
            throw new IndexOutOfBoundsException("В матрице нет строки с индексом: " + index);
        }

        Vector vectorCopy = new Vector(vector);

        rows[index] = vectorCopy;
    }

    public Vector getColumn(int index) {
        if (index >= getColumnCount() || index < 0) {
            throw new IndexOutOfBoundsException("В матрице нет столбца с индексом: " + index);
        }

        Vector column = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            column.setComponent(i, rows[i].getComponent(index));
        }

        return column;
    }

    public void transpose() {
        Matrix copy = new Matrix(this);

        for (int i = 0; i < getRowCount(); i++) {
            setRow(i, copy.getColumn(i));
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < getRowCount(); i++) {
            rows[i].multiply(scalar);
        }
    }

    public Vector multiplyByVector(Vector vector) {
        if (vector.getSize() != getColumnCount()) {
            throw new IllegalArgumentException("Размер матрицы (" + getColumnCount() + "), не равен размеру вектора: " + vector.getSize());
        }

        Vector resultant = new Vector(getRowCount());

        for (int i = 0; i < getRowCount(); i++) {
            resultant.setComponent(i, getScalarProduct(getRow(i), vector));
        }

        return resultant;
    }

    public static void verifyMatricesSizes(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowCount() != matrix2.getRowCount() || matrix1.getColumnCount() != matrix2.getColumnCount()) {
            throw new IllegalArgumentException("Матрицы разного размера. Количество строк: " + matrix1.getRowCount() + ", " + matrix2.getRowCount() +
                    ". Количество столбов: " + matrix1.getColumnCount() + ", " + matrix2.getColumnCount());
        }
    }

    public void add(Matrix matrix) {
        verifyMatricesSizes(this, matrix);

        for (int i = 0; i < getRowCount(); i++) {
            rows[i].add(matrix.getRow(i));
        }
    }

    public void subtract(Matrix matrix) {
        verifyMatricesSizes(this, matrix);

        for (int i = 0; i < getRowCount(); i++) {
            rows[i].subtract(matrix.getRow(i));
        }
    }

    public double getDeterminant() {
        if (getColumnCount() != getRowCount()) {
            throw new IllegalArgumentException("Матрица не квадратная: количество строк " + getRowCount() + ", количество столбцов " + getColumnCount());
        }

        Matrix matrixCopy = new Matrix(this);
        double epsilon = 0.00001;
        boolean isDeterminantZero = false;

        for (int i = 0; i < matrixCopy.rows.length - 1; i++) {
            if (Math.abs(matrixCopy.rows[i].getComponent(i)) <= epsilon) {
                isDeterminantZero = true;

                for (int j = i + 1; j < matrixCopy.getColumnCount(); j++) {
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

        Vector temp;
        Vector row;
        Vector nextRow;

        for (int i = 0; i < matrixCopy.getRowCount(); i++) {
            temp = matrixCopy.getRow(i);
            row = matrixCopy.getRow(i);
            row.multiply(1 / matrixCopy.getRow(i).getComponent(i));
            matrixCopy.setRow(i, row);

            for (int j = i + 1; j < matrixCopy.getRowCount(); j++) {
                row.multiply(matrixCopy.getRow(j).getComponent(i));
                nextRow = matrixCopy.getRow(j);
                nextRow.subtract(row);
                matrixCopy.setRow(j, nextRow);
                row = matrixCopy.getRow(i);
            }

            matrixCopy.setRow(i, temp);
        }

        double determinant = 1;

        for (int i = 0; i < matrixCopy.getRowCount(); i++) {
            determinant *= matrixCopy.getRow(i).getComponent(i);
        }

        if (Math.abs(determinant) <= epsilon || determinant != determinant) {
            return 0;
        }

        MathContext context = new MathContext(5, RoundingMode.HALF_UP);
        determinant = new BigDecimal(determinant, context).doubleValue();

        return determinant;
    }


    public static Matrix addMatrix(Matrix matrix1, Matrix matrix2) {
        verifyMatricesSizes(matrix1, matrix2);

        Matrix resultant = new Matrix(matrix1);
        resultant.add(matrix2);

        return resultant;
    }

    public static Matrix subtractMatrix(Matrix matrix1, Matrix matrix2) {
        verifyMatricesSizes(matrix1, matrix2);

        Matrix resultant = new Matrix(matrix1);
        resultant.subtract(matrix2);

        return resultant;
    }

    public static Matrix multiplyByMatrix(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnCount() != matrix2.getRowCount()) {
            throw new IllegalArgumentException("Число столбцов в первой матрице множителя (" + matrix1.getColumnCount() +
                    ") должно совпадать с числом строк второй матрицы множителя (" + matrix2.getRowCount() + ")");
        }

        Matrix resultant = new Matrix(matrix1.getRowCount(), matrix2.getColumnCount());

        for (int i = 0; i < matrix1.getRowCount(); i++) {
            resultant.setRow(i, matrix1.multiplyByVector(matrix2.getColumn(i)));
        }

        resultant.transpose();

        return resultant;
    }
}