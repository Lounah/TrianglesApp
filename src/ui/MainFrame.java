package ui;

import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
    Класс, отвечающий за фрейм с панелью координат
 */
public class MainFrame extends JFrame implements Frame {

    /*
        Высота фрейма
     */
    private final int height;

    /*
        Ширина фрейма
     */
    private final int width;

    /*
       Координата левого верхнего угла по х
     */
    private final int start_x;

    /*
        Координата верхнего левого угла по у
     */
    private final int start_y;

    /*
        Панель с координатами
     */
    private final JPanel coordinatesPanel;

    /*
        Заголовок фрейма
     */
    private final String title;

    private MainFrame(@NotNull final MainFrameBuilder builder) {
        this.height = builder.DEFAULT_HEIGHT;
        this.width = builder.DEFAULT_WIDTH;
        this.title  = builder.DEFAULT_TITLE;
        this.start_x = builder.DEFAULT_START_X;
        this.start_y = builder.DEFAULT_START_Y;
        this.coordinatesPanel = builder.DEFAULT_COORDINATE_PANEL;
    }

    @Override
    public void onCreate() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new MainFrameListener());
        setSize(width, height);
        setTitle(title);
        setLocation(start_x, start_y);
        add(coordinatesPanel);
        setLayout(new GridLayout());
        setVisible(true);
    }

    @Override
    public void onDestroy() {
        dispose();
    }

    /*
        Для создания объекта класса-фрейма используется паттерн Builder.
        Он обеспечивает наглядное создание объекта, обладающего большим кол-вом
        свойств
    */
    public static class MainFrameBuilder {

        private int DEFAULT_HEIGHT = 800;
        private int DEFAULT_WIDTH = 800;
        private int DEFAULT_START_X = 0;
        private int DEFAULT_START_Y = 0;
        private JPanel DEFAULT_COORDINATE_PANEL = null;
        private String DEFAULT_TITLE = "null";

        public MainFrameBuilder withWidth(final int width) {
            this.DEFAULT_WIDTH = width;
            return this;
        }

        public MainFrameBuilder withHeight(final int height) {
            this.DEFAULT_HEIGHT = height;
            return this;
        }

        public MainFrameBuilder withTitle(@NotNull final String title) {
            this.DEFAULT_TITLE = title;
            return this;
        }

        public MainFrameBuilder withStartLocation(final int x, final int y) {
            this.DEFAULT_START_X = x;
            this.DEFAULT_START_Y = y;
            return this;
        }

        public MainFrameBuilder add(@NotNull final JPanel panel) {
            this.DEFAULT_COORDINATE_PANEL = panel;
            return this;
        }

        public MainFrame build() {
            return new MainFrame(this);
        }

    }


    /*
        Слушатель событий жизненного цикла фрейма
     */
    private static class MainFrameListener extends WindowAdapter {

        @Override
        public void windowOpened(WindowEvent e) {
            MainFrame frame = (MainFrame) e.getSource();
            frame.onCreate();
        }

        @Override
        public void windowClosed(WindowEvent e) {
            MainFrame frame = (MainFrame) e.getSource();
            frame.onDestroy();
        }
    }

}
