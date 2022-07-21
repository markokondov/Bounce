package bounce;

import java.awt.Color;

/**
 * Interface to represent a type that offers primitive drawing methods.
 *
 * @author Ian Warren
 */
public interface Painter {
    /**
     * Draws a rectangle. Parameters x and y specify the top left corner of the
     * oval. Parameters width and height specify its width and height.
     */
    void drawRect(int x, int y, int width, int height);

    /**
     * Draws an oval. Parameters x and y specify the top left corner of the
     * oval. Parameters width and height specify its width and height.
     */
    void drawOval(int x, int y, int width, int height);

    /**
     * Draws a line. Parameters x1 and y1 specify the starting point of the
     * line, parameters x2 and y2 the ending point.
     */
    void drawLine(int x1, int y1, int x2, int y2);

    void fillRect(int x, int y, int width, int height);

    Color getColor();

    void setColor(Color color);

    void drawCenteredText(String text, int x, int y);

    void translate(int x, int y);
}
