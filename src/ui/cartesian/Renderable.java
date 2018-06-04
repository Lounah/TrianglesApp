package ui.cartesian;

import java.awt.*;

/*
    Интерфейс, от которого должны наследоваться все
    сложно отрисовываемые объекты
 */
public interface Renderable {

    /*
        вызывается при инициализации панели отрисовки
     */
    void onPrepare();

    /*
        рисует все нужные панели компоненты
     */
    void onDraw(Graphics g);


    /*
        вызывается при изменении масштаба компонента
     */
    void onResize(final int newScale);

}
