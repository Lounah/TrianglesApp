package maths;

import entity.Circle;
import entity.Line;
import entity.Point;
/*
    Класс, в котором производятся все расчеты, связанные с
    прямыми/окружностями в пространстве. Имеет приватный конструктор,
    т.к. нам не нужен инстанс этого класса, лишь его методы.

    Все классы и методы пакета maths package-protected и видимы лишь внутри этого пакета.

 */
class Geometry {

    private Geometry() {}

    /*
        Расчет расстояния от прямой l до точки о

        d = | (x2*y1 - y2*x1) | / sqrt( (y2 - y1)^2 + (x2 - x1)^2) )
     */
    static double calculateDistance(final Line l, final Point o) {
        return Math.abs(l.getB().getX() * l.getA().getY() - l.getB().getY() * l.getA().getX()) /
                Math.sqrt( (l.getB().getY() - l.getA().getY()) * (l.getB().getY() - l.getA().getY()) +
                        (l.getB().getX() - l.getA().getX()) * (l.getB().getX() - l.getA().getX()));
    }

    static boolean linePassThroughCircle(final Line l, final Circle circle) {


        if (lineIsAxis(l)) {

            // если прямая лежит на оси у
            if (l.getA().getX() == 0 && l.getB().getX() == 0) {
                if (((Math.min(l.getA().getY(), l.getB().getY()) > circle.getRadius())
                        && l.getA().getY() > 0 && l.getB().getY() > 0)
                        || ((Math.max(l.getA().getY(), l.getB().getY()) < -circle.getRadius())
                        && l.getA().getY() < 0 && l.getB().getY() < 0))
                    return false;
            } else
            if (l.getA().getY() == 0 && l.getB().getY() == 0) {
                if (((Math.max(l.getA().getX(), l.getB().getX()) < -circle.getRadius())
                        && l.getA().getX() < 0 && l.getB().getX() < 0)
                        || ((Math.min(l.getA().getX(), l.getB().getX()) > circle.getRadius())
                        && l.getA().getX() > 0 && l.getB().getX() > 0))
                    return false;
            }

        } else {


            // составляем уравнение прямой по l: y = kx + b
            final double k = (l.getB().getX() - l.getA().getX()) / (l.getB().getY() - l.getA().getY());

            final double b = (l.getB().getY() - k);

            final double radius = circle.getRadius();

            // находим точки пересечения прямой с окружностью по х
            final double p_x_1 = (-k * b + Math.sqrt(k * k * b * b - (k * k + 1) * (b * b - radius * radius))) / (k * k + 1);
            final double p_x_2 = (-k * b - Math.sqrt(k * k * b * b - (k * k + 1) * (b * b - radius * radius))) / (k * k + 1);

            // находим точки пересечения с окружностью по у
            final double p_y_1_1 = Math.sqrt(radius * radius - p_x_1 * p_x_1);
            final double p_y_1_2 = -Math.sqrt(radius * radius - p_x_1 * p_x_1);
            final double p_y_2_1 = Math.sqrt(radius * radius - p_x_2 * p_x_2);
            final double p_y_2_2 = -Math.sqrt(radius * radius - p_x_2 * p_x_2);

            final double p_y_1_res;
            final double p_y_2_res;

            // отбираем нужные нам точки пересечения с окружностью по у
            if (p_y_1_1 == (k * p_x_1 + b))
                p_y_1_res = p_y_1_1;
            else p_y_1_res = p_y_1_2;

            if (p_y_2_1 == (k * p_x_2 + b))
                p_y_2_res = p_y_2_1;
            else p_y_2_res = p_y_2_2;


            // получаем 4 точки: 2 точки изначального отрезка и 2 точки пересечения
            // прямой, принадлежащей этому отрезку с окружностью
            final Point cusp_1 = new Point(p_x_1, p_y_1_res);
            final Point cusp_2 = new Point(p_x_2, p_y_2_res);
            final Point predictPoint_1 = l.getA();
            final Point predictPoint_2 = l.getB();

            final Point minCuspPoint;
            final Point maxCuspPoint;

            final Point maxPredictPoint;
            final Point minPredictPoint;

            if (predictPoint_1.getX() != 0 && predictPoint_2.getX() != 0) {
                if (predictPoint_1.getX() < predictPoint_2.getX()) {
                    minPredictPoint = predictPoint_1;
                    maxPredictPoint = predictPoint_2;
                } else {
                    minPredictPoint = predictPoint_2;
                    maxPredictPoint = predictPoint_1;
                }
            } else {
                if (predictPoint_1.getY() < predictPoint_2.getY()) {
                    minPredictPoint = predictPoint_1;
                    maxPredictPoint = predictPoint_2;
                } else {
                    minPredictPoint = predictPoint_2;
                    maxPredictPoint = predictPoint_1;
                }
            }


            if (cusp_1.getX() != 0 && cusp_2.getX() != 0) {
                if (cusp_1.getX() < cusp_2.getX()) {
                    minCuspPoint = cusp_1;
                    maxCuspPoint = cusp_2;
                } else {
                    minCuspPoint = cusp_2;
                    maxCuspPoint = cusp_1;
                }
            } else {
                if (cusp_1.getY() < cusp_2.getY()) {
                    minCuspPoint = cusp_1;
                    maxCuspPoint = cusp_2;
                } else {
                    minCuspPoint = cusp_2;
                    maxCuspPoint = cusp_1;
                }
            }

            System.out.println("min predict: " + minPredictPoint.getX() + " " + minPredictPoint.getY());
            System.out.println("max predict: " + maxPredictPoint.getX() + " " + maxPredictPoint.getY());
            System.out.println("min cusp: " + minCuspPoint.getX() + " " + minCuspPoint.getY());
            System.out.println("max cusp: " + maxCuspPoint.getX() + " " + maxCuspPoint.getY());

            if (cusp_1.getX() == 0 && cusp_2.getX() == 0)
                return !((maxPredictPoint.getY() < minCuspPoint.getY()) || (minPredictPoint.getY() > maxCuspPoint.getY()));
            else
                return !((maxPredictPoint.getX() < minCuspPoint.getX()) || (minPredictPoint.getX() > maxCuspPoint.getX()));
        }

        return true;

    }

    private static boolean lineIsAxis(final Line l) {
        return ((l.getA().getX() == 0 && l.getB().getX() == 0) || (l.getA().getY() == 0 && l.getB().getY() == 0));
    }
}
