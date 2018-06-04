package utils;

import java.awt.*;
import java.util.Collection;
import java.util.Random;

public class AppUtils {


    public static final String APP_NAME = "Решение задачи о треугольниках";

    private AppUtils() {}

    public static Color generateRandomColor() {

        final Random random = new Random();
        final int c = random.nextInt(10);

        switch (c) {
            case 0:
                return Color.BLUE;
            case 1:
                return Color.CYAN;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.BLACK;
            case 4:
                return Color.ORANGE;
            case 5:
                return Color.GREEN;
            case 6:
                return Color.GRAY;
            case 7:
                return Color.magenta;
            case 8:
                return Color.PINK;
            default: return Color.BLUE;

        }
    }
}
