import entity.Circle;
import entity.Point;
import entity.Triangle;
import maths.TrianglesCreator;
import sun.tools.java.Environment;
import ui.MainFrame;
import ui.cartesian.CoordinatesSystemPanel;
import utils.AppUtils;
import utils.ScreenUtils;
import javax.swing.*;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.*;

public class TrianglesApplication {

    private static final String APP_NAME = AppUtils.APP_NAME;
    private static final String FILE_INPUT_PATH = System.getProperty("user.home") + "/Desktop/input.txt";

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

        try {
            Files.lines(FileSystems.getDefault().getPath(FILE_INPUT_PATH))
                    .forEach(line -> {
                        String[] strs = line.trim().split("\\s+");
                        points.add(new Point(Integer.parseInt(strs[0]), Integer.parseInt(strs[1])));
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }


        triangles = TrianglesCreator.create(points, circle);
        triangles.forEach(triangle -> System.out.println(triangle.toString()));

        System.out.println("Всего треугольников: " + triangles.size());

    }

}
