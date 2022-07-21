package bounce;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class NestingShape extends Shape{
    List<Shape> nestedShapes;
    NestingShape parent;

    /**
     * Creates a NestingShape object with default values for state.
     */
    public NestingShape() {
        super();
        this.nestedShapes = new ArrayList<>();
    }

    public NestingShape(String text) {
        super(text);
        this.nestedShapes = new ArrayList<>();
    }

    /**
     * Creates a NestingShape object with specified location values
     * @param x location on x-axis
     * @param y location on y-axis
     */
    public NestingShape(int x, int y) {
        super(x, y);
        this.nestedShapes = new ArrayList<>();
    }

    public NestingShape(String text, int x, int y) {
        super(x, y, text);
        this.nestedShapes = new ArrayList<>();
    }

    /**
     * Creates a NestingShape with specified values for location veloxity and direction.
     * Non-specified state items take on default values.
     * @param x x-axis positing
     * @param y y-axis position
     * @param deltaX velocity on x axis
     * @param deltaY velocity on y axis
     */
    public NestingShape(int x, int y, int deltaX, int deltaY) {
        super(x, y, deltaX, deltaY);
        this.nestedShapes = new ArrayList<>();
    }

    public NestingShape(String text, int x, int y, int deltaX, int deltaY) {
        super(x, y, deltaX, deltaY, text);
        this.nestedShapes = new ArrayList<>();
    }

    /**
     * Creates a NestingShape with specified values for location, velocity, direction, width and
     * height.
     * @param x x-axis position
     * @param y y-axis position
     * @param deltaX velocity on x-axis
     * @param deltaY velocity on y-axis
     * @param width width of shape
     * @param height height of shape
     */
    public NestingShape(int x, int y, int deltaX, int deltaY, int width, int height) {
        super(x, y, deltaX, deltaY, width, height);
        this.nestedShapes = new ArrayList<>();
    }

    public NestingShape(String text, int x, int y, int deltaX, int deltaY, int width, int height) {
        super(x, y, deltaX, deltaY, width, height, text);
        this.nestedShapes = new ArrayList<>();
    }

    /**
     * Moves a NestingShape object (including its children) within the
     * bounds specified by arguments width and height.
     * @param width  width of two-dimensional world.
     * @param height height of two-dimensional world.
     */
    @Override
    public void move(int width, int height) {
        super.move(width, height);
        for (Shape shape : nestedShapes) {
            shape.move(this.width, this.height);
        }
    }

    /**
     * Paints a NestingShape object by drawing a rectangle around the edge of
     * its bounding box. The NestingShape object's children are then painted.
     * @param painter the Painter object used for drawing.
     */
    @Override
    public void paintShape(Painter painter) {
        painter.drawRect(x, y, width, height);
        painter.translate(x, y);
        for (Shape shape: nestedShapes) {
            shape.paint(painter);
        }
        painter.translate(-x, -y);
    }

    /**
     * Attempts to add a Shape to a NestingShape object. If successful, a
     * two way link is established between the NestingShape and the newly
     * added Shape. Note that this method has package visibility for reasons
     * that will become apparent in Bounce III.
     * @param shape the shape to be added.
     * @throws IllegalArgumentException if an attempt is made to add a Shape
     * to a NestingShape instance where the Shape argument is already a child
     * within a NestingShape instance. An IllegalArgumentException is also thrown
     * when an attempt is made to add a Shape that will not fit within the bounds
     * of the proposed NestingShape object.
     */
    public void add(Shape shape) throws IllegalArgumentException {
        if (shape.parent() != null) {
            throw new IllegalArgumentException("This shape is already contained inside a NestingShape");
        } else if (shape.x() + shape.width() > this.width || shape.height() + shape.y() >= this.height() || shape.x() < 0 || shape.y() < 0) {
            throw new IllegalArgumentException("This shape is does not fit with specified parameters");
        }
        nestedShapes.add(shape);
        shape.setParent(this);
    }

    /**
     * Removes a particular Shape from a NestingShape instance. Once removed,
     * the two-way link between the NestingShape and its former child is
     * destroyed. This method has no effect if the Shape specified to remove
     * is not a child of the NestingShape. Note that this method has package
     * visibility - for reasons that will become apparent in Bounce III.
     */
    public void remove(Shape shape) {
        shape.setParent(null);
        nestedShapes.remove(shape);
    }

    /**
     * Retrieves the Shape at a specified position within a NestingShape. If
     * the position specified is less than zero or greater than the number of
     * children stored in the NestingShape less less one this method throws an
     * IndexOutOfBoundsException.
     * @param index specified index position
     * @return the shape object at the specified index
     * @throws IndexOutOfBoundsException if invalid index
     */
    public Shape shapeAt(int index) throws IndexOutOfBoundsException {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index below zero");
        } else if (index > nestedShapes.size() - 1) {
            throw new IndexOutOfBoundsException("Index too high");
        }
        return nestedShapes.get(index);
    }

    /**
     * Returns the number of children contained within a NestingShape object.
     * Note this method is not recursive - it simply returns teh number of
     * children at the top level within the callee NestingShape object.
     */
    public int shapeCount() {
        return nestedShapes.size();
    }

    /**
     * Returns the index of a specified child within a NestingShape object.
     * If the Shape specified is not actually a child of the NestingShape
     * this method returns -1; otherwise the value returned is in teh range
     * 0 .. shapeCount() - 1.
     * @param shape the shape whose index position within the NestingShape is
     * requested.
     */
    public int indexOf(Shape shape) {
        return nestedShapes.indexOf(shape);
    }

    /**
     * Returns true if the Shape argument is a child of the NestingShape
     * object on which this method is called, false otherwise.
     */
    public boolean contains(Shape shape) {
        return nestedShapes.contains(shape);
    }
}
