package ru.academits.kolesnikov.matrix_main;

import ru.academits.kolesnikov.matrix.Matrix;
import ru.academits.kolesnikov.vector.Vector;

import static ru.academits.kolesnikov.matrix.Matrix.addMatrix;
import static ru.academits.kolesnikov.matrix.Matrix.subtractMatrix;
import static ru.academits.kolesnikov.matrix.Matrix.multiplyByMatrix;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3, 2);
        System.out.println("Матрица №1: матрица нулей размером 3*2\n" + matrix1);

        Matrix matrix2 = new Matrix(matrix1);
        System.out.println("Матрица №2: копия матрицы №1\n" + matrix2);

        double[][] array1 = {{3, 2}, {4, -3}};
        Matrix matrix3 = new Matrix(array1);
        System.out.println("Матрица №3: матрица двумерного массива\n" + matrix3);

        double[] array2 = {5, 7, 9};
        Vector vector1 = new Vector(array2);
        double[] array3 = {3, 2, 5};
        Vector vector2 = new Vector(array3);
        Vector[] vectors = {vector1, vector2};
        Matrix matrix4 = new Matrix(vectors);
        System.out.println("Матрица №4: матрица векторов\n" + matrix4);

        System.out.println();

        System.out.println("Матрица №3:");
        System.out.println("количество строк " + matrix3.getRowCount() + " количество столбцов " + matrix3.getColumnCount());
        System.out.println("строка №2 " + matrix3.getRow(1) + " столбик №2 " + matrix3.getColumn(1));

        System.out.println();

        matrix4.multiplyByScalar(3);
        System.out.println("Матрица №4 умноженная на 3\n" + matrix4);

        matrix4.multiplyByVector(vector1);
        System.out.println("Матрица №4 умноженная на вектор {5, 7, 9}\n" + matrix4);

        Matrix matrix5 = addMatrix(matrix2, matrix2);
        System.out.println("Матрица №5: к матрица №2 прибавили матрицу №2\n" + matrix5);

        Matrix matrix6 = subtractMatrix(matrix5, matrix2);
        System.out.println("Матрица №6: от матрица №5 вычли матрицу №2\n" + matrix6);

        Matrix matrix7 = multiplyByMatrix(matrix3, matrix3);
        System.out.println("Матрица №7: умножение матрицы №3 на матрицу №3\n" + matrix7);

        System.out.println();

        double[][] array = {{3, 2, 4, 5}, {4, -3, 2, -4}, {5, -2, -3, -7}, {-3, 4, 2, 9}};
        Matrix matrix8 = new Matrix(array);
        System.out.println("Матрица №8:\n" + matrix8);
        System.out.println("Определитель матрицы №8:\n" + matrix8.getDeterminant());
    }
}
