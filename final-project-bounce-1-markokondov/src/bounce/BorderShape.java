package bounce;

public class BorderShape extends Shape {
    protected Shape innerShape;
    protected int amountBorders;
    private final int DEFAULT_BORDERS = 1;

    public BorderShape(Shape innerShape) {
        this.innerShape = innerShape;
        this.amountBorders = DEFAULT_BORDERS;
        this.x = innerShape.x - 2;
        this.y = innerShape.y - 2;
        this.width = innerShape.width + 4;
        this.height = innerShape.height + 4;
        this.deltaY = innerShape.deltaY;
        this.deltaX = innerShape.deltaX;
    }

    public BorderShape(String text, Shape innerShape) {
        this.innerShape = innerShape;
        this.amountBorders = DEFAULT_BORDERS;
        this.x = innerShape.x - 2;
        this.y = innerShape.y - 2;
        this.width = innerShape.width + 4;
        this.height = innerShape.height + 4;
        this.deltaY = innerShape.deltaY;
        this.deltaX = innerShape.deltaX;
        this.text = text;
    }

    public BorderShape(Shape innerShape, int amountBorders) {
        this.innerShape = innerShape;
        this.amountBorders = amountBorders;
        this.x = innerShape.x - (2 * amountBorders);
        this.y = innerShape.y - (2 * amountBorders);
        this.width = innerShape.width + (4 * amountBorders);
        this.height = innerShape.height + (4 * amountBorders);
        this.deltaY = innerShape.deltaY;
        this.deltaX = innerShape.deltaX;
    }

    public BorderShape(String text, Shape innerShape, int amountBorders) {
        this.innerShape = innerShape;
        this.amountBorders = amountBorders;
        this.x = innerShape.x - (2 * amountBorders);
        this.y = innerShape.y - (2 * amountBorders);
        this.width = innerShape.width + (4 * amountBorders);
        this.height = innerShape.height + (4 * amountBorders);
        this.deltaY = innerShape.deltaY;
        this.deltaX = innerShape.deltaX;
        this.text = text;
    }

    @Override
    public void paintShape(Painter painter) {
        innerShape.paintShape(painter);
        for (int i = amountBorders - 1; i >= 0; i--) {
            painter.drawRect(x + (2 * i), y + (2 * i), width - (4 * i), height - (4 * i));
        }
    }

    public int borderShapeRoofHit(int outerY) {
        this.innerShape.deltaY = deltaY;
        int borderShapeNextY = outerY + (this.amountBorders * 2);
        if (this.innerShape instanceof DynamicRectangleShape) {
            ((DynamicRectangleShape) this.innerShape).updateColor(false, true);
        }
        return borderShapeNextY;
    }

    public int borderShapeWallHit(int outerX) {
        this.innerShape.deltaX = deltaX;
        int borderShapeNextX = outerX + (this.amountBorders * 2);
        if (this.innerShape instanceof DynamicRectangleShape) {
            ((DynamicRectangleShape) this.innerShape).updateColor(true, false);
        }
        return borderShapeNextX;
    }

    public void move(int width, int height) {
        super.move(width, height);
        moveInnerShape(this.x, this.y, this.amountBorders, width, height);
    }

    public void moveInnerShape(int outerX, int outerY, int amountBorders, int width, int height) {
        int nextX = innerShape.x + deltaX;
        int nextY = innerShape.y + deltaY;

        if (outerX <= 0 || outerX + this.width >= width) {
            nextX = this.borderShapeWallHit(outerX);
        }
        if (outerY <= 0 || outerY + this.height >= height) {
            nextY = this.borderShapeRoofHit(outerY);
        }

        this.innerShape.x = nextX;
        this.innerShape.y = nextY;
    }
}
