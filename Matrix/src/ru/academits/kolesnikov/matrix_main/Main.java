package ru.academits.kolesnikov.matrix_main;

import ru.academits.kolesnikov.matrix.Matrix;
import ru.academits.kolesnikov.vector.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int n = 3;
        int m = 2;
        Matrix matrix1 = new Matrix(n, m);
        System.out.println(matrix1);

        Matrix matrix2 = new Matrix(matrix1);
        System.out.println(matrix2);

        double[][] array = {{1, 2, 4}, {2, 3}};
        Matrix matrix3 = new Matrix(array);
        System.out.println(matrix3);

        double[] array2 = {2, 2};
        Vector vector1 = new Vector(array2);
        int x = 4;
        Vector vector2 = new Vector(x, array2);
        Vector[] arrayVectors = {vector1, vector2};
        Matrix matrix4 = new Matrix(arrayVectors);
        System.out.println(matrix4);

        System.out.println(Arrays.toString(matrix4.getMatrixSize()));

        System.out.println(matrix4.transposeMatrix());

        System.out.println(matrix4.getVectorColumnIndex(0));

        matrix3.multiplicationByScalar(2);
        System.out.println(matrix3);

        Vector vector3 = new Vector(3);
        matrix3.multiplicationByVector(vector3);
        System.out.println(matrix3);

        double[][] array3 = {{1, 2}, {2, 3}};
        Matrix matrix6 = new Matrix(array3);
        Matrix matrix7 = new Matrix(matrix6);
        matrix7.additionMatrix(matrix6);
        System.out.println(matrix7);

        matrix6.subtractionMatrix(matrix7);
        System.out.println(matrix6);

        System.out.println(Matrix.additionMatrix(matrix6, matrix7));

        System.out.println(Matrix.subtractionMatrix(matrix6, matrix7));

        System.out.println(Matrix.multiplicationByMatrix(matrix6, matrix7));

        System.out.println();

        double[][] array4 = {{1, 2, 3, 4, -5, 6}, {4, -3, 2, 4, 6, 7}, {-1, 2, 4, 3, 8, 9}, {2, 4, 6, 8, -5, 6}, {1, 2, 3, 4, 5, 6}, {-3, 2, -2, 4, 5, -7}};
        Matrix matrix5 = new Matrix(array4);
        System.out.println(matrix5.matrixDeterminant());

        System.out.println(matrix5.getVectorIndex(5));
    }
}