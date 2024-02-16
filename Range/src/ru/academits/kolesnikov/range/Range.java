package ru.academits.kolesnikov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getRangeIntersect(Range range1, Range range2) {
        if (range2.from < range1.to && range2.to > range1.from) {
            return new Range(Math.max(range1.from, range2.from), Math.min(range1.to, range2.to));
        }

        return null;
    }

    public Range[] getRangeMerge(Range range1, Range range2) {
        if (range2.from < range1.to && range2.to > range1.from) {
            return new Range[]{new Range(Math.min(range1.from, range2.from), Math.max(range1.to, range2.to))};
        }

        return new Range[]{range1, range2};
    }

    public Range[] getRangeDifference(Range range1, Range range2) {
        if (range1.from == range2.from && range1.to == range2.to) {
            return new Range[]{null};
        } else if (range1.from == range2.from) {
            return new Range[]{new Range(Math.min(range1.to, range2.to), Math.max(range1.to, range2.to))};
        } else if (range1.to == range2.to) {
            return new Range[]{new Range(Math.min(range1.to, range2.to), Math.max(range1.to, range2.to))};
        } else if (range2.from > range1.from && range2.from < range1.to) {
            return new Range[]{new Range(range1.from, range2.from), new Range(range1.to, range2.to)};
        } else if (range2.to < range1.to && range2.to > range1.from) {
            return new Range[]{new Range(range2.from, range1.from), new Range(range2.to, range1.to)};
        }

        return new Range[]{range1, range2};
    }
}