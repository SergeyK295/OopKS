package ru.academits.kolesnikov.matrix_main;

import ru.academits.kolesnikov.matrix.Matrix;
import ru.academits.kolesnikov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        int n = 3;
        int m = 2;
        Matrix matrix1 = new Matrix(n, m);
        System.out.println(matrix1.toString());

        Matrix matrix2 = new Matrix(matrix1);
        System.out.println(matrix2.toString());

        double[][] array = {{1, 2, 4}, {2, 3}};
        Matrix matrix3 = new Matrix(array);
        System.out.println(matrix3.toString());

        double[] arrayVector = {2, 2};
        Vector vector1 = new Vector(arrayVector);
        int x = 3;
        Vector vector2 = new Vector(x, arrayVector);
        Vector[] arrayVectors = {vector1, vector2};
        Matrix matrix4 = new Matrix(arrayVectors);
        System.out.println(matrix4.toString());

    }
}
