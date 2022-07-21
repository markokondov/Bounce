package bounce;

import java.awt.*;

public class DynamicRectangleShape extends Shape {
    private final Color DEFAULT_COLOR = Color.BLACK;
    private Color color;
    private Color currentColor;

    public DynamicRectangleShape() {
        super();
        this.color = DEFAULT_COLOR;
        this.currentColor = DEFAULT_COLOR;
    }

    public DynamicRectangleShape(String text) {
        super(text);
        this.color = DEFAULT_COLOR;
        this.currentColor = DEFAULT_COLOR;
    }

    public DynamicRectangleShape(int x, int y, int deltaX, int deltaY) {
        super(x, y, deltaX, deltaY);
        this.color = DEFAULT_COLOR;
        this.currentColor = DEFAULT_COLOR;
    }

    public DynamicRectangleShape(int x, int y) {
        super(x, y);
        this.color = DEFAULT_COLOR;
        this.currentColor = DEFAULT_COLOR;
    }

    public DynamicRectangleShape(int x, int y, Color color) {
        super(x, y);
        this.color = color;
        this.currentColor = color;
    }

    public DynamicRectangleShape(String text, int x, int y) {
        super(x, y, text);
        this.color = DEFAULT_COLOR;
        this.currentColor = DEFAULT_COLOR;
    }

    public DynamicRectangleShape(String text, int x, int y, Color color) {
        super(x, y, text);
        this.color = color;
        this.currentColor = color;
    }

    public DynamicRectangleShape(String text, int x, int y, int deltaX, int deltaY) {
        super(x, y, deltaX, deltaY, text);
        this.color = DEFAULT_COLOR;
        this.currentColor = DEFAULT_COLOR;
    }

    public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, Color color) {
        super(x, y, deltaX, deltaY);
        this.color = color;
        this.currentColor = color;
    }

    public DynamicRectangleShape(String text, int x, int y, int deltaX, int deltaY, Color color) {
        super(x, y, deltaX, deltaY, text);
        this.color = color;
        this.currentColor = color;
    }

    public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height) {
        super(x, y, deltaX, deltaY, width, height);
        this.color = DEFAULT_COLOR;
        this.currentColor = color;
    }

    public DynamicRectangleShape(String text, int x, int y, int deltaX, int deltaY, int width, int height) {
        super(x, y, deltaX, deltaY, width, height, text);
        this.color = DEFAULT_COLOR;
        this.currentColor = color;
    }

    public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, Color color) {
        super(x, y, deltaX, deltaY, width, height);
        this.color = color;
        this.currentColor = color;
    }

    public DynamicRectangleShape(String text, int x, int y, int deltaX, int deltaY, int width, int height, Color color) {
        super(x, y, deltaX, deltaY, width, height, text);
        this.color = color;
        this.currentColor = color;
    }

    @Override
    public void paintShape(Painter painter) {
        painter.setColor(currentColor);
        if (currentColor != null) {
            painter.fillRect(x, y, width, height);
        }
        painter.setColor(DEFAULT_COLOR);
        painter.drawRect(x, y, width, height);
    }

    @Override
    public void move(int width, int height) {
        int deltaX = this.deltaX;
        int deltaY = this.deltaY;
        super.move(width, height);
        if (this.deltaX != deltaX && this.deltaY != deltaY) {
            updateColor(true, true);
        } else if (this.deltaX != deltaX) {
            updateColor(true, false);
        } else if (this.deltaY != deltaY) {
            updateColor(false, true);
        }
    }

    public void updateColor(boolean hitVerticalWall, boolean hitHorizontalWall) {
        if (hitVerticalWall) {
            if (color == null) {
                color = DEFAULT_COLOR;
            }
            this.currentColor = color;

        } else if (hitHorizontalWall) {
            this.currentColor = null;
        }
    }
}
