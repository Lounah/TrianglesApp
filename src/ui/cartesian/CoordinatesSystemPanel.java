package ui.cartesian;

import com.sun.istack.internal.NotNull;
import entity.Circle;
import entity.Point;
import entity.Triangle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;
import java.util.Set;

/*
    Класс, отрисовывающий координатную панель
 */
public class CoordinatesSystemPanel extends JPanel implements Renderable {

    /*
        Масштаб всего компонента
     */
    private final int SCALE;

    /*
        Координата начала оси Х по х относительно панели
     */
    private final int X_AXIS_FIRST_X_COORDINATE;

    /*
        Координата конца оси Х по х относительно панели
     */
    private final int X_AXIS_SECOND_X_COORDINATE;

    /*
        Координата начала оси Х по у относительно панели
     */
    private final int X_AXIS_Y_COORDINATE;

    /*
        Координата начала оси У по у относительно панели
     */
    private final int Y_AXIS_FIRST_Y_COORDINATE;

    /*
        Координата конца оси У по у относительно панели
     */
    private final int Y_AXIS_SECOND_Y_COORDINATE;

    /*
        Координата оси У по х относительно панели
     */
    private final int Y_AXIS_X_COORDINATE;

    /*
        Радиус точки начала координат О(0;0)
     */
    private final int ORIGIN_POINT_RADIUS;

    /*
        Отступ цифр-меток, обозначающих координаты от осей
     */
    private final int AXIS_STRING_DISTANCE;

    /*
        Отрисовываемый круг
     */
    private final Circle CIRCLE;

    /*
        Отрисовываемый набор точек
     */
    private final Set<Point> points;

    /*
        Отрисовываемый набор треугольников
     */
    private final List<Triangle> triangles;


    /*
        Длина "штришков"-отметок, верхняя граница
     */
    private static final int FIRST_LENGTH = 2;

    /*
        Длина "штришков"-отметок, нижняя граница
     */
    private static final int SECOND_LENGTH = 2;

    /*
        Отступ по умолчанию
     */
    private static final int DEFAULT_MARGIN = 7;

    private CoordinatesSystemPanel(@NotNull final Builder builder) {
        this.SCALE = builder.DEFAULT_SCALE;
        this.X_AXIS_FIRST_X_COORDINATE = builder.DEFAULT_CENTER_X;
        this.Y_AXIS_FIRST_Y_COORDINATE = builder.DEFAULT_CENTER_Y;
        this.X_AXIS_SECOND_X_COORDINATE = builder.DEFAULT_X_AXIS_LENGTH;
        this.Y_AXIS_SECOND_Y_COORDINATE = builder.DEFAULT_Y_AXIS_LENGTH;
        this.X_AXIS_Y_COORDINATE = builder.DEFAULT_CENTER_Y;
        this.Y_AXIS_X_COORDINATE = builder.DEFAULT_CENTER_X;
        this.ORIGIN_POINT_RADIUS = builder.DEFAULT_ORIGIN_POINT_RADIUS;
        this.AXIS_STRING_DISTANCE = builder.DEFAULT_STRING_MARGIN;
        this.CIRCLE = builder.DEFAULT_CIRCLE;
        this.triangles = builder.DEFAULT_TRIANGLES;
        this.points = builder.DEFAULT_SET_OF_POINTS;

        onPrepare();

    }

    @Override
    public void onPrepare() {
        setBorder(new EmptyBorder(50, 50, 50, 50));
        this.setBackground(Color.white);
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        onDraw(g);
    }

    @Override
    public void onDraw(Graphics g) {
        createCoordinateSystem(g);
        drawPoints(g);
        drawCircle(g);
        drawTriangles(g);
    }

    @Override
    public void onResize(int newScale) {

    }

    /*
        Отрисовка координатных осей
     */
    private void createCoordinateSystem(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // ось х
        g2D.drawLine(10, X_AXIS_Y_COORDINATE,
                X_AXIS_SECOND_X_COORDINATE, X_AXIS_Y_COORDINATE);

        // ось у
        g2D.drawLine(Y_AXIS_X_COORDINATE, DEFAULT_MARGIN,
                Y_AXIS_X_COORDINATE, Y_AXIS_SECOND_Y_COORDINATE - DEFAULT_MARGIN);

        // стрелка по оси х
        g2D.drawLine(X_AXIS_SECOND_X_COORDINATE - FIRST_LENGTH,
                X_AXIS_Y_COORDINATE - SECOND_LENGTH,
                X_AXIS_SECOND_X_COORDINATE, X_AXIS_Y_COORDINATE);
        g2D.drawLine(X_AXIS_SECOND_X_COORDINATE - FIRST_LENGTH,
                X_AXIS_Y_COORDINATE + SECOND_LENGTH,
                X_AXIS_SECOND_X_COORDINATE, X_AXIS_Y_COORDINATE);

        // стрелка по оси у
        g2D.drawLine(Y_AXIS_X_COORDINATE, DEFAULT_MARGIN,
                Y_AXIS_X_COORDINATE - FIRST_LENGTH,
                DEFAULT_MARGIN + FIRST_LENGTH);

        g2D.drawLine(Y_AXIS_X_COORDINATE, DEFAULT_MARGIN,
                Y_AXIS_X_COORDINATE + FIRST_LENGTH, DEFAULT_MARGIN + FIRST_LENGTH);

        // точка О
        g2D.fillOval(
                X_AXIS_FIRST_X_COORDINATE - (ORIGIN_POINT_RADIUS / 2),
                X_AXIS_FIRST_X_COORDINATE - (ORIGIN_POINT_RADIUS / 2),
                ORIGIN_POINT_RADIUS, ORIGIN_POINT_RADIUS);

        // названия осей
        g2D.drawString("X", X_AXIS_SECOND_X_COORDINATE - AXIS_STRING_DISTANCE / 2 + 15,
                X_AXIS_Y_COORDINATE + AXIS_STRING_DISTANCE);
        g2D.drawString("Y", Y_AXIS_X_COORDINATE + 10,
                DEFAULT_MARGIN + 5);


        // размерность и обозначения координат на осях
        int xPointsQuantity = 21;
        int yPointsQuantity = 21;

        int xLength = (X_AXIS_SECOND_X_COORDINATE - DEFAULT_MARGIN * 2)
                / xPointsQuantity;

        int yLength = (Y_AXIS_SECOND_Y_COORDINATE - DEFAULT_MARGIN * 2)
                / yPointsQuantity;




        for (int i = -10; i < 0; i++) {
            g2D.drawLine(X_AXIS_FIRST_X_COORDINATE + (i * xLength),
                    X_AXIS_Y_COORDINATE - SECOND_LENGTH,
                    X_AXIS_FIRST_X_COORDINATE + (i * xLength),
                    X_AXIS_Y_COORDINATE + SECOND_LENGTH);

            g2D.drawString(Integer.toString(i),
                    X_AXIS_FIRST_X_COORDINATE + (i * xLength) - xLength / 3,
                    X_AXIS_Y_COORDINATE + AXIS_STRING_DISTANCE);
        }

        for (int i = 1; i < 11; i++) {
            g2D.drawLine(X_AXIS_FIRST_X_COORDINATE + (i * xLength),
                    X_AXIS_Y_COORDINATE - SECOND_LENGTH,
                    X_AXIS_FIRST_X_COORDINATE + (i * xLength),
                    X_AXIS_Y_COORDINATE + SECOND_LENGTH);

            g2D.drawString(Integer.toString(i),
                    X_AXIS_FIRST_X_COORDINATE + (i * xLength),
                    X_AXIS_Y_COORDINATE + AXIS_STRING_DISTANCE);
        }


        for (int i = 1; i < 11; i++) {

            g2D.drawLine(Y_AXIS_X_COORDINATE - SECOND_LENGTH,
                    Y_AXIS_FIRST_Y_COORDINATE + (i * yLength),
                    Y_AXIS_X_COORDINATE + SECOND_LENGTH,
                    Y_AXIS_FIRST_Y_COORDINATE + (i * yLength));

            g2D.drawString(Integer.toString(-i),
                    Y_AXIS_X_COORDINATE - AXIS_STRING_DISTANCE - yLength / 4,
                    Y_AXIS_FIRST_Y_COORDINATE + (i * yLength));

            g2D.drawLine(Y_AXIS_X_COORDINATE - SECOND_LENGTH,
                    Y_AXIS_FIRST_Y_COORDINATE - (i * yLength),
                    Y_AXIS_X_COORDINATE + SECOND_LENGTH,
                    Y_AXIS_FIRST_Y_COORDINATE - (i * yLength));

            g2D.drawString(Integer.toString(i),
                    Y_AXIS_X_COORDINATE - AXIS_STRING_DISTANCE,
                    Y_AXIS_FIRST_Y_COORDINATE - (i * yLength));

        }
    }

    /*
        Отрисовка точек
    */
    private void drawPoints(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2D.setColor(Color.RED);

        points.forEach(point -> {
            Ellipse2D.Double shape = new Ellipse2D.Double(X_AXIS_FIRST_X_COORDINATE + point.getX()*SCALE-2,
                    Y_AXIS_FIRST_Y_COORDINATE - point.getY()*SCALE - 1, ORIGIN_POINT_RADIUS,
                    ORIGIN_POINT_RADIUS);
            g2D.fill(shape);
        });
    }

    /*
        Отрисовка круга
     */
    private void drawCircle(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;

        Ellipse2D.Double shape = new Ellipse2D.Double(X_AXIS_FIRST_X_COORDINATE - (CIRCLE.getRadius()*SCALE),
                X_AXIS_FIRST_X_COORDINATE - (CIRCLE.getRadius()*SCALE),
                CIRCLE.getRadius() * 2*SCALE, CIRCLE.getRadius()*2*SCALE);

        g2D.draw(shape);

    }

    /*
        Отрисовка треугольников
     */
    private void drawTriangles(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;

        triangles.forEach(triangle -> {

            Line2D.Double AB = new Line2D.Double(X_AXIS_FIRST_X_COORDINATE + triangle.getB().getX()*SCALE,
                    Y_AXIS_FIRST_Y_COORDINATE - triangle.getB().getY()*SCALE,
                    X_AXIS_FIRST_X_COORDINATE + triangle.getA().getX()*SCALE,
                    Y_AXIS_FIRST_Y_COORDINATE - triangle.getA().getY()*SCALE);

            Line2D.Double AC = new Line2D.Double(X_AXIS_FIRST_X_COORDINATE + triangle.getC().getX()*SCALE,
                    Y_AXIS_FIRST_Y_COORDINATE - triangle.getC().getY()*SCALE,
                    X_AXIS_FIRST_X_COORDINATE + triangle.getA().getX()*SCALE,
                    Y_AXIS_FIRST_Y_COORDINATE - triangle.getA().getY()*SCALE);

            Line2D.Double BC = new Line2D.Double(X_AXIS_FIRST_X_COORDINATE + triangle.getC().getX()*SCALE,
                    Y_AXIS_FIRST_Y_COORDINATE - triangle.getC().getY()*SCALE,
                    X_AXIS_FIRST_X_COORDINATE + triangle.getB().getX()*SCALE,
                    Y_AXIS_FIRST_Y_COORDINATE - triangle.getB().getY()*SCALE);

            g2D.setColor(triangle.getColor());
            g2D.draw(AB);
            g2D.draw(AC);
            g2D.draw(BC);
        });
    }

    /*
        Для создания объекта класса-панели используется паттерн Builder.
        Он обеспечивает наглядное создание объекта, обладающего большим кол-вом
        свойств
     */
    public static class Builder {

        private int DEFAULT_SCALE = 1;
        private int DEFAULT_CENTER_X = 0;
        private int DEFAULT_CENTER_Y = 0;
        private int DEFAULT_X_AXIS_LENGTH = 0;
        private int DEFAULT_Y_AXIS_LENGTH = 0;
        private int DEFAULT_ORIGIN_POINT_RADIUS = 0;
        private int DEFAULT_STRING_MARGIN = 0;
        private List<Triangle> DEFAULT_TRIANGLES = null;
        private Circle DEFAULT_CIRCLE = null;
        private Set<Point> DEFAULT_SET_OF_POINTS = null;

        public Builder withScale(final int scale) {
            this.DEFAULT_SCALE = scale;
            return this;
        }

        public Builder withCenterCoordinates(final int x, final int y) {
            this.DEFAULT_CENTER_X = x;
            this.DEFAULT_CENTER_Y = y;
            return this;
        }

        public Builder withXAxisLength(final int length) {
            this.DEFAULT_X_AXIS_LENGTH = length;
            return this;
        }


        public Builder withYAxisLength(final int length) {
            this.DEFAULT_Y_AXIS_LENGTH = length;
            return this;
        }

        public Builder withOriginPointRadius(final int radius) {
            this.DEFAULT_ORIGIN_POINT_RADIUS = radius;
            return this;
        }

        public Builder withStringMargin(final int margin) {
            this.DEFAULT_STRING_MARGIN = margin;
            return this;
        }

        public Builder withPoints(final Set<Point> points) {
            this.DEFAULT_SET_OF_POINTS = points;
            return this;
        }

        public Builder withCircle(final Circle circle) {
            this.DEFAULT_CIRCLE = circle;
            return this;
        }

        public Builder withTriangles(final List<Triangle> triangles) {
            this.DEFAULT_TRIANGLES = triangles;
            return this;
        }


        public CoordinatesSystemPanel build() {
            return new CoordinatesSystemPanel(this);
        }

    }

}
