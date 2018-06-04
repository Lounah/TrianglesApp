package entity;

/*
    Entity-класс, описывающий точку в пространстве.
    Переопределяет equals & hashcode, т.к. элементы данного типа
    хранятся в HashSet.
 */
public class Point {

    private final double x;
    private final double y;

    /*
        @param x -- координата точки по х
        @param у -- координата точки по у
     */
    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(Point.class))
            return false;
        Point point = (Point) obj;
        return (x == point.getX() && y == point.getY());
    }

    @Override
    public int hashCode() {
        int result = 11;
        int xHash;
        int yHash;

        if (x < 0)
             xHash = Double.valueOf(x).hashCode() * 13;
        else xHash = Double.valueOf(x).hashCode();

        if (y < 0)
            yHash = Double.valueOf(y).hashCode() * 13;
        else yHash = Double.valueOf(y).hashCode();

        return 37 * result + xHash + yHash;
    }
}
