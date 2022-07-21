package bounce;

import java.util.List;
import java.util.ArrayList;

/**
 * Abstract superclass to represent the general concept of a Shape. This class
 * defines state common to all special kinds of Shape instances and implements
 * a common movement algorithm. Shape subclasses must override method paint()
 * to handle shape-specific painting.
 *
 * @author Ian Warren
 */
public abstract class Shape {
    // === Constants for default values. ===
    protected static final int DEFAULT_X_POS = 0;

    protected static final int DEFAULT_Y_POS = 0;

    protected static final int DEFAULT_DELTA_X = 5;

    protected static final int DEFAULT_DELTA_Y = 5;

    protected static final int DEFAULT_HEIGHT = 35;

    protected static final int DEFAULT_WIDTH = 25;
    // ===

    // === Instance variables, accessible by subclasses.
    protected int x;

    protected int y;

    protected int deltaX;

    protected int deltaY;

    protected int width;

    protected int height;

    protected NestingShape parent;

    protected String text;
    // ===

    /**
     * Creates a Shape object with default values for instance variables.
     */
    public Shape() {
        this(DEFAULT_X_POS, DEFAULT_Y_POS, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Shape(String text) {
        this(DEFAULT_X_POS, DEFAULT_Y_POS, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.text = text;
    }

    /**
     * Creates a Shape object with a specified x and y position.
     */
    public Shape(int x, int y) {
        this(x, y, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Shape(int x, int y, String text) {
        this(x, y, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.text = text;
    }

    /**
     * Creates a Shape instance with specified x, y, deltaX and deltaY values.
     * The Shape object is created with a default width and height.
     */
    public Shape(int x, int y, int deltaX, int deltaY) {
        this(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Shape(int x, int y, int deltaX, int deltaY, String text) {
        this(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.text = text;
    }

    /**
     * Creates a Shape instance with specified x, y, deltaX, deltaY, width and
     * height values.
     */
    public Shape(int x, int y, int deltaX, int deltaY, int width, int height) {
        this.x = x;
        this.y = y;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.width = width;
        this.height = height;
    }

    public Shape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    /**
     * Moves this Shape object within the specified bounds. On hitting a
     * boundary the Shape instance bounces off and back into the two-
     * dimensional world.
     *
     * @param width  width of two-dimensional world.
     * @param height height of two-dimensional world.
     */
    public void move(int width, int height) {
        int nextX = x + deltaX;
        int nextY = y + deltaY;

        if (nextX <= 0) {
            nextX = 0;
            deltaX = -deltaX;
        } else if (nextX + this.width >= width) {
            nextX = width - this.width;
            deltaX = -deltaX;
        }

        if (nextY <= 0) {
            nextY = 0;
            deltaY = -deltaY;

        } else if (nextY + this.height >= height) {
            nextY = height - this.height;
            deltaY = -deltaY;
        }
        x = nextX;
        y = nextY;
    }


    /**
     * Method to be implemented by concrete subclasses to handle subclass
     * specific painting.
     *
     * @param painter the Painter object used for drawing.
     */
    public abstract void paintShape(Painter painter);

    public void paint(Painter painter) {
        paintShape(painter);
        if (this.text != null) {
            painter.drawCenteredText(this.text, getCenterX(), getCenterY());
        }
    }

    /**
     * Returns this Shape object's x position.
     */
    public int x() {
        return x;
    }

    /**
     * Returns this Shape object's y position.
     */
    public int y() {
        return y;
    }

    /**
     * Returns this Shape object's speed and direction.
     */
    public int deltaX() {
        return deltaX;
    }

    /**
     * Returns this Shape object's speed and direction.
     */
    public int deltaY() {
        return deltaY;
    }

    /**
     * Returns this Shape's width.
     */
    public int width() {
        return width;
    }

    /**
     * Returns this Shape's height.
     */
    public int height() {
        return height;
    }

    /**
     * Returns a String whose value is the fully qualified name of this class
     * of object. E.g., when called on a RectangleShape instance, this method
     * will return "bounce.RectangleShape".
     */
    public String toString() {
        return getClass().getName();
    }

    /**
     * Returns the NestingShape that contains the Shape that method parent
     * is called on. If the callee object is not a child within a
     * NestingShape instance this method returns null.
     */
    public NestingShape parent() {return parent;}

    public void setParent(NestingShape parent) {
        this.parent = parent;
    }

    /**
     * Returns an ordered list of Shape objects. The first item within
     * the list is the root NestingShape of the containment hierarchy.
     * The last item within the list is the callee object (hence this
     * method always returns a list with at least one item). Any
     * intermediate items are NestingShapes that connect the root
     * NestingShape to the callee Shape. e.g., given:
     *
     *  NestingShape root = new NestingShape();
     *  NestingShape intermediate = new NestingShape();
     *  Shape oval = new OvalShape();
     *  root.add(intermediate);
     *  intermediate.add(oval);
     *
     *a call to oval.path() yields: [root, intermediate, oval]
     */
    public List<Shape> path() {
        List<Shape> shapeList = new ArrayList<>();
        List<Shape> tempList = new ArrayList<>();
        if (this.parent() != null) {
            tempList = this.parent().path();
        }
        for (Shape shape : tempList) {
            shapeList.add(shape);
        }
        shapeList.add(this);
        return shapeList;
    }

    /**
     * Method to retrieve the x co-ordinate in the center (used for centering text)
     */
    public int getCenterX() {
        return x + (width / 2);
    }

    /**
     * Method to retrieve the y co-ordinate in the center (used for centering text)
     */
    public int getCenterY() {
        return y + (height / 2);
    }
}
