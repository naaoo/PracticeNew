package com.company;

public class Main {

    public static void main(String[] args) {
        Square square = new Square(7.56);
        Rectangle rectangle = new Rectangle(2.65, 4.98);

        System.out.println(square.calculateArea());
        System.out.println(rectangle.calculateArea());

    }
}
