package bounce;

public class OvalShape extends Shape{

    //Default constructor - utilises default values in superclass
    public OvalShape() {
        super();
    }

    public OvalShape(String text) {
        super(text);
    }

    public OvalShape(int x, int y) {
        super(x, y);
    }

    public OvalShape(String text, int x, int y) {
        super(x, y, text);
    }

    //Constructs default size, although specifies size and speed + direction.
    public OvalShape(int x, int y, int deltaX, int deltaY) {
        super(x, y, deltaX, deltaY);
    }

    public OvalShape(String text, int x, int y, int deltaX, int deltaY) {
        super(x, y, deltaX, deltaY, text);
    }

    //Creates OvalShape with all specified values
    public OvalShape(int x, int y, int deltaX, int deltaY, int width, int height) {
        super(x, y, deltaX, deltaY, width, height);
    }

    public OvalShape(String text, int x, int y, int deltaX, int deltaY, int width, int height) {
        super(x, y, deltaX, deltaY, width, height, text);
    }

    @Override
    public void paintShape(Painter painter) {
        painter.drawOval(x, y, width, height);
    }
}
