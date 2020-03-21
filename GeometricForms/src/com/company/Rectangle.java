package com.company;

public class Rectangle extends GeometricFigure{
    double sideA;
    double sideB;

    public Rectangle(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }

    @Override
    public double calculateArea() {
        return this.sideA * this.sideB;
    }
}
