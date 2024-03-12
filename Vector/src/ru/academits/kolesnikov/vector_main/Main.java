package ru.academits.kolesnikov.vector_main;

import ru.academits.kolesnikov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] array = {2, 2};
        Vector vector1 = new Vector(array);
        System.out.println("Вектор №1: " + vector1);

        int vectorSize = 3;
        Vector vector2 = new Vector(vectorSize, array);
        System.out.println("Вектор №2: " + vector2);

        Vector vector3 = new Vector(vectorSize);
        System.out.println("Вектор №3 (пустой вектор): " + vector3 + System.lineSeparator());

        int size = vector2.getSize();
        System.out.println("Размерность вектора №2: " + size + System.lineSeparator());

        vector1.add(vector2);
        System.out.println("Вектор №1 после прибавления вектора №2: " + vector1 + size + System.lineSeparator());

        Vector vector4 = new Vector(vector1);
        System.out.println("Вектор №4 (копия вектора №1): " + vector4 + System.lineSeparator());

        vector3.subtract(vector4);
        System.out.println("Вектор №3 после вычитания вектора №4: " + vector3 + System.lineSeparator());

        double scalar = 3;
        vector4.multiply(scalar);
        System.out.println("Вектор №4 после умножение компонентов на " + scalar + ": " + vector4 + System.lineSeparator());

        vector4.unfold();
        System.out.println("Вектор №4 после разворота: " + vector4 + System.lineSeparator());

        System.out.println("Длина вектора №1: " + vector1.getLength() + System.lineSeparator());

        int index1 = 1;
        System.out.println("Компонента №" + (index1 + 1) + " вектора №4: " + vector4.getComponent(index1) + System.lineSeparator());

        int index2 = 1;
        double component1 = 7;
        vector1.setComponent(index2, component1);
        System.out.println("Вектор №1 после замены компоненты №" + (index2 + 1) + " на " + component1 + ": " + vector1 + System.lineSeparator());

        System.out.println("Равны ли векторы №1 и №4: " + vector1.equals(vector4) + System.lineSeparator());

        Vector vector5 = Vector.getSum(vector2, vector3);
        System.out.println("Вектор №5 (сумма векторов №2 и №3): " + vector5 + System.lineSeparator());

        Vector vector6 = Vector.getDifference(vector2, vector3);
        System.out.println("Вектор №6 (разность векторов №2 и №3): " + vector6 + System.lineSeparator());

        System.out.println("Скалярное произведение векторов №2 и №6: " + Vector.getScalarProduct(vector2, vector6));
    }
}