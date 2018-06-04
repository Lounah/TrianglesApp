package utils;


import java.awt.*;

public class ScreenUtils {

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private ScreenUtils() {}

    public static double getScreenHeight() {
        return screenSize.getHeight();
    }

    public static double getScreenWidth() {
        return screenSize.getHeight();
    }

}
