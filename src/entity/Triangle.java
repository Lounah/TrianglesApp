package entity;

import java.awt.*;

/*
    Entity-класс, описывающий треугольник в пространстве
 */
public class Triangle {

    private final Point a;
    private final Point b;
    private final Point c;

    private final Color color;

    /*
        @param a -- точка А
        @param b -- точка B
        @param c -- точка С
        @param color -- цвет треугольника (лишнее здесь)
     */
    public Triangle(Point a, Point b, Point c, Color color) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.color = color;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getC() {
        return c;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Треугольник с координатами: А(" +
                a.getX() + ";" + a.getY() + "); "
                + "B(" +
                b.getX() + ";" + b.getY() + "); "
                + "C(" +
                c.getX() + ";" + c.getY() + "); ";
    }
}
