package bounce;

public class GemShape extends Shape {

    public GemShape() {
        super();
    }

    public GemShape(String text) {
        super(text);
    }

    public GemShape(int x, int y) {
        super(x, y);
    }

    public GemShape(String text, int x, int y) {
        super(x, y, text);
    }

    public  GemShape(int x, int y, int deltaX, int deltaY) {
        super(x, y, deltaX, deltaY);
    }

    public  GemShape(String text, int x, int y, int deltaX, int deltaY) {
        super(x, y, deltaX, deltaY, text);
    }

    public GemShape(int x, int y, int deltaX, int deltaY, int width, int height) {
        super(x, y, deltaX, deltaY, width, height);
    }

    public GemShape(String text, int x, int y, int deltaX, int deltaY, int width, int height) {
        super(x, y, deltaX, deltaY, width, height, text);
    }

    @Override
    public void paintShape(Painter painter) {
        if (width < 40 || height < 40) {
            painter.drawLine(x, y + height / 2, x + width / 2, y);
            painter.drawLine(x + width / 2, y, x + width, y + height / 2);
            painter.drawLine(x + width, y + height / 2, x + width / 2, y + height);
            painter.drawLine(x + width / 2, y + height, x, y + height / 2);
        } else {
            painter.drawLine(x, y + height - 20, x, y + 20);
            painter.drawLine(x, y + 20, x + 20, y);
            painter.drawLine(x + 20, y, x + width - 20, y);
            painter.drawLine(x + width - 20, y, x + width, y + 20);
            painter.drawLine(x + width, y + 20, x + width, y + height - 20);
            painter.drawLine(x + width, y + height - 20, x + width - 20, y + height);
            painter.drawLine(x  + width - 20, y + height, x + 20, y + height);
            painter.drawLine(x + 20, y + height, x, y + height - 20);
        }
    }
}
