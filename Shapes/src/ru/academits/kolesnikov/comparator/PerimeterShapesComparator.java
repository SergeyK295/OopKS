package ru.academits.kolesnikov.comparator;

import ru.academits.kolesnikov.shapes.Shapes;
import java.util.Comparator;

public class PerimeterShapesComparator implements Comparator<Shapes> {
    @Override
    public int compare(Shapes o1, Shapes o2) {
        return (int) (o2.getPerimeter() - o1.getPerimeter());
    }
}
