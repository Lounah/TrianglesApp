package entity;


/*
    Entity-класс, описывающий "сущность" прямой в пространстве.
    Переопределяет equals & hashcode, т.к. элементы данного типа
    хранятся в HashSet
 */
public class Line {

    private final Point a;
    private final Point b;

    /*
        @param a -- точка начала отрезка
        @param b -- точка конца отрезка
     */
    public Line(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    @Override
    public String toString() {
        return "Прямая с координатами: A(" +
                a.getX() + ";" + a.getY() + "); B(" +
                b.getX() + ";" + b.getY() + ");";
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != Line.class) {
            return false;
        }
        final Line l = (Line) o;
        return l.a.equals(((Line) o).getA()) && l.b.equals(((Line) o).getB()) ||
                l.b.equals(((Line) o).getA()) && l.a.equals(((Line) o).getB());
    }

    @Override
    public int hashCode() {
        int result = 11;
        final int aHash = a == null ? 0 : a.hashCode();
        final int bHash = b == null ? 0 : b.hashCode();
        return 37 * result + aHash + bHash;
    }
}
