package maths;

import entity.Line;
import entity.Circle;
import entity.Point;
import entity.Triangle;
import utils.AppUtils;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/*
    Класс, отвечающий за генерирование треугольников, исходя из
    начальных данных: набора точек и круга с определенным радиусом.
 */
public class TrianglesCreator {

    private TrianglesCreator() {}


    /*
        Создает треугольники следующим образом:
        Имея основания, проходящие через окружность,
        алгоритм проходит по каждой из точек из заданного набора и
        составляет треугольник, "соединяя" основание и точку

        Наверное, можно сделать как-то эффективнее, но здесь -- О(n*m + m^2),
        где n -- кол-во линий (оснований), m -- кол-во точек

        @param points -- набор точек
        @param circle -- круг заданного радиуса
        @return составленные треугольники
     */
    public static List<Triangle> create(final Set<Point> points, final Circle circle) {

        List<Triangle> triangles = new ArrayList<>();

        Set<Line> basements = createBasements(points, circle);

        basements.forEach(line -> {
            points.forEach(point -> {
                if (!point.equals(line.getA()) && !point.equals(line.getB())) {
                    final Color color = AppUtils.generateRandomColor();
                    if (!(line.getA().getX() == 0 && line.getB().getX() == 0 && point.getX() == 0) &&
                            !(line.getA().getY() == 0 && line.getB().getY() == 0 && point.getY() == 0))
                    triangles.add(new Triangle(line.getA(), line.getB(), point, color));
                }
            });
        });
        return triangles;
    }

    /*
        Создает основания для треугольников. Каждое основание обладает
        следующим свойством: оно обязательно пересекает заданную окружность

        @param points -- набор уникальных неповторяющихся точек
        @param circle -- окружность заданного радиуса
        @return набор линий, оснований треугольника

     */
    private static Set<Line> createBasements(final Set<Point> points, final Circle circle) {

        final Set<Line> lines = new HashSet<>();
        final Point center = new Point(circle.getX(), circle.getY());
        final double radius = circle.getRadius();

        points.forEach(point -> {

            points.forEach(anotherPoint -> {

                if (!point.equals(anotherPoint)) {

                    final Line line = new Line(point, anotherPoint);

                    final double d = Geometry.calculateDistance(line, center);

                    if ((d < radius) && (Geometry.linePassThroughCircle(line, circle))) {
                        lines.add(line);
                    }
                }
            });
        });
        return lines;
    }

}
