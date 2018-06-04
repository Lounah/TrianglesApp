package entity;

/*
    Entity-класс, описывающий "сущность окружности"
 */
public class Circle {

    private final int x;
    private final int y;
    private final double radius;

    /*
        @param x -- центр окружности по х
        @param у -- центр окружности по y
        @param radius -- радиус окружности
     */
    public Circle(int x, int y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }
}
