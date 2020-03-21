package com.company;

public class Square extends GeometricFigure{
    double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return Math.pow(this.side, 2.0);
    }
}
