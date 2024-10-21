package ru.academits.kolesnikov.temperature.controller;

public interface Controller {
    void convert(String convertFrom, String convertTo, double temperature);

    String[] getScalesNames();
}
