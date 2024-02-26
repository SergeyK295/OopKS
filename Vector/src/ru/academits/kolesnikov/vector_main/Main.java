package ru.academits.kolesnikov.vector_main;

import ru.academits.kolesnikov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] array = {2, 2};
        Vector vector1 = new Vector(array);
        System.out.println("Информация о векторе №1: " + vector1);

        int n = 3;
        Vector vector2 = new Vector(n, array);
        System.out.println("Информация о векторе №2: " + vector2);

        int size = vector2.getSize();
        System.out.println("Размерность вектора №2: " + size);

        vector1.vectorAddition(vector2);
        System.out.println("Информация о векторе №1 после прибавления к нему вектора №2: " + vector1);

        Vector vector3 = new Vector(n);
        System.out.println("Информация о векторе №3 (пустой вектор): " + vector3);

        Vector vector4 = new Vector(vector1);
        System.out.println("Информация о векторе №4 (копия вектора №1): " + vector4);

        vector3.vectorSubtraction(vector4);
        System.out.println("Информация о векторе №3 после вычитания из него вектора №4: " + vector3);

        double scalar = 3;
        vector4.vectorMultiplication(scalar);
        System.out.println("Информация о векторе №4 после умножение его компонентов на " + scalar + " : " + vector4);

        vector4.vectorReverse();
        System.out.println("Информация о векторе №4 после его разворота: " + vector4);

        System.out.println("Длина вектора №1: " + vector1.getLength());

        int index1 = 1;
        System.out.println("Компонента №" + (index1 + 1) + " вектора №4: " + vector4.getComponentVectorIndex(index1));

        int index2 = 1;
        double component1 = 7;
        vector1.setComponentVectorIndex(component1, index2);
        System.out.println("Информация о векторе №1 после замены компоненты №" + (index2 + 1) + " на " + component1 + ":" + vector1);

        System.out.println("Равны ли векторы №1 и №4: " + vector1.equals(vector4));

        Vector vector5 = Vector.vectorAddition(vector2, vector3);
        System.out.println("Информация о векторе №5 (сумма векторов №2 и №3): " + vector5);

        Vector vector6 = Vector.vectorSubtraction(vector2, vector3);
        System.out.println("Информация о векторе №6 (разность векторов №2 и №3): " + vector6);

        System.out.println("Скалярное произведение векторов №2 и №6: " + Vector.vectorsProductScalar(vector2, vector6));
    }
}