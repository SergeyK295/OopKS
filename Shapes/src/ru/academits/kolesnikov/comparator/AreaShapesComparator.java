package ru.academits.kolesnikov.comparator;

import java.util.Comparator;

import ru.academits.kolesnikov.shapes.Shapes;

public class AreaShapesComparator implements Comparator<Shapes> {
    @Override
    public int compare(Shapes o1, Shapes o2) {
        return (int) (o2.getArea() - o1.getArea());
    }
}
