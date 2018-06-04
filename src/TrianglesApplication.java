import entity.Circle;
import entity.Point;
import entity.Triangle;
import maths.TrianglesCreator;
import ui.MainFrame;
import ui.cartesian.CoordinatesSystemPanel;
import utils.AppUtils;
import utils.ScreenUtils;
import javax.swing.*;
import java.util.*;

public class TrianglesApplication {

    private static final String APP_NAME = AppUtils.APP_NAME;

    private static final int START_LOCATION_X = (int) ScreenUtils.getScreenWidth() / 3;
    private static final int START_LOCATION_Y = (int) ScreenUtils.getScreenHeight() / 8;

    private static final Set<Point> points;
    private static final Circle circle;

    private static List<Triangle> triangles;

    // масштаб 1:35
    private static MainFrame frame;
    private static CoordinatesSystemPanel coordinatesSystemPanel;

    static {
        circle = new Circle(375, 375, 4);
        points = new HashSet<>();
        triangles = new ArrayList<>();
    }

    public static void main(String... args) {
        init();
        onCreateMainFrame();
    }

    private static void onCreateMainFrame() {

        coordinatesSystemPanel = createCoordinatesPanel();
        frame = createMainFrame();

        SwingUtilities.invokeLater(() -> frame.onCreate());

    }

    private static CoordinatesSystemPanel createCoordinatesPanel() {
        return new CoordinatesSystemPanel.Builder()
                .withCenterCoordinates(375, 375)
                .withOriginPointRadius(7)
                .withStringMargin(20)
                .withXAxisLength(750)
                .withYAxisLength(750)
                .withPoints(points)
                .withCircle(circle)
                .withTriangles(triangles)
                .withScale(35)
                .build();
    }

    private static MainFrame createMainFrame() {
        return new MainFrame.MainFrameBuilder()
                .withHeight(770)
                .withWidth(770)
                .withStartLocation(START_LOCATION_X, START_LOCATION_Y)
                .withTitle(APP_NAME)
                .add(coordinatesSystemPanel)
                .build();
    }

    private static void init() {

        // deprecated
        points.add(new Point(2, 1));
        points.add(new Point(6, 1));
        points.add(new Point(4, 4));
        points.add(new Point(0, 2));
        points.add(new Point(2, 4));
        points.add(new Point(-6, 2));
        points.add(new Point(-7, 3));


        points.add(new Point(2, 4));
        points.add(new Point(-2.5, 0));
        points.add(new Point(0, 9));
        points.add(new Point(-5, 0));
        points.add(new Point(4, 1));
        points.add(new Point(0, -5));
        points.add(new Point(5, 2));
        points.add(new Point(6, 3));
        points.add(new Point(7, 4));

        points.add(new Point(-2.5, 0));
        points.add(new Point(-5, 0));
        points.add(new Point(2.5, 0));
        points.add(new Point(5, 0));
        points.add(new Point(0, 7));
        points.add(new Point(0, -4));


        // deprecated
//        points.add(new Point(-7, 2));
//        points.add(new Point(-8, 3));
//        points.add(new Point(-9, 4));
//        points.add(new Point(-10, 5));

//        points.add(new Point(-3.4, -0.15));
//        points.add(new Point(-0.15, -3.4));
//        points.add(new Point(-6, -6));
//        points.add(new Point(1, 3));

        triangles = TrianglesCreator.create(points, circle);
        triangles.forEach(triangle -> System.out.println(triangle.toString()));

        System.out.println("Всего треугольников: " + triangles.size());

    }

}
