package bounce;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;


public class TestBorderShape {
    private MockPainter painter;

    @Before
    public void setUp() {
        this.painter = new MockPainter();
    }

    @Test
    public void testBorderShapePaintedWithOval_1Border() {
        BorderShape shape = new BorderShape(new OvalShape(50, 50));
        shape.paint(painter);
        assertEquals("(oval 50,50,25,35)(rectangle 48,48,29,39)", painter.toString());
    }

    @Test
    public void testBorderShapePaintedWithOval_MultipleBorders() {
        BorderShape shape = new BorderShape(new OvalShape(50, 50), 5);
        shape.paint(painter);
        assertEquals("(oval 50,50,25,35)(rectangle 48,48,29,39)(rectangle 46,46,33,43)(rectangle 44,44,37,47)" +
                "(rectangle 42,42,41,51)(rectangle 40,40,45,55)", painter.toString());
    }

    @Test
    public void testBorderShapePaintedWithRect_1Border() {
        BorderShape shape = new BorderShape(new RectangleShape(50, 50, 10, 10));
        shape.paint(painter);
        assertEquals("(rectangle 50,50,25,35)(rectangle 48,48,29,39)", painter.toString());
    }

    @Test
    public void testBorderShapePaintedWithRect_MultipleBorders() {
        BorderShape shape = new BorderShape(new RectangleShape(50, 50, 10, 10), 3);
        shape.paint(painter);
        assertEquals("(rectangle 50,50,25,35)(rectangle 48,48,29,39)(rectangle 46,46,33,43)(rectangle 44,44,37,47)",
                painter.toString());
    }

    @Test
    public void testBorderShapePaintedWithGem_1Border() {
        BorderShape shape = new BorderShape(new GemShape(50, 50));
        shape.paint(painter);
        assertEquals("(line 50,67,62,50)(line 62,50,75,67)(line 75,67,62,85)(line 62,85,50,67)" +
                "(rectangle 48,48,29,39)", painter.toString());
    }

    @Test
    public void testBorderShapePaintedWithGem_MultipleBorders() {
        BorderShape shape = new BorderShape(new GemShape(50, 50), 5);
        shape.paint(painter);
        assertEquals("(line 50,67,62,50)(line 62,50,75,67)(line 75,67,62,85)(line 62,85,50,67)" +
                "(rectangle 48,48,29,39)(rectangle 46,46,33,43)(rectangle 44,44,37,47)(rectangle 42,42,41,51)" +
                "(rectangle 40,40,45,55)", painter.toString());
    }

    @Test
    public void testBorderShapePaintedWithDynamicRect_1Border() {
        BorderShape shape = new BorderShape(new DynamicRectangleShape(50, 50));
        shape.paint(painter);
        assertEquals("(Rectangle colored java.awt.Color[r=0,g=0,b=0])(rectangle 50,50,25,35)(rectangle 48,48,29,39)", painter.toString());
    }

    @Test
    public void testBorderShapePaintedWithDynamicRect_MultipleBorders() {
        BorderShape shape = new BorderShape(new DynamicRectangleShape(50, 50, Color.BLUE), 5);
        shape.paint(painter);
        assertEquals("(Rectangle colored java.awt.Color[r=0,g=0,b=255])(rectangle 50,50,25,35)" +
                "(rectangle 48,48,29,39)(rectangle 46,46,33,43)(rectangle 44,44,37,47)(rectangle 42,42,41,51)" +
                "(rectangle 40,40,45,55)", painter.toString());
    }

    @Test
    public void testBorderShapeSimpleMove() {
        BorderShape shape = new BorderShape(new OvalShape(10, 10, 10, 10));
        shape.paint(painter);
        shape.move(1000, 1000);
        shape.paint(painter);
        assertEquals("(oval 10,10,25,35)(rectangle 8,8,29,39)" +
                        "(oval 20,20,25,35)(rectangle 18,18,29,39)", painter.toString());
    }

    @Test
    public void testBorderShapeSimpleMove_MultipleBorders() {
        BorderShape shape = new BorderShape(new OvalShape(10, 10, 10, 10), 3);
        shape.paint(painter);
        shape.move(1000, 1000);
        shape.paint(painter);
        assertEquals("(oval 10,10,25,35)(rectangle 8,8,29,39)(rectangle 6,6,33,43)(rectangle 4,4,37,47)" +
                        "(oval 20,20,25,35)(rectangle 18,18,29,39)(rectangle 16,16,33,43)(rectangle 14,14,37,47)",
                painter.toString());
    }

    @Test
    public void testBorderShapeBounceLeftWall() {
        BorderShape shape = new BorderShape(new DynamicRectangleShape(14, 14, -10, 10, null), 2);
        shape.paint(painter);
        shape.move(1000, 1000);
        shape.paint(painter);
        shape.move(1000, 1000);
        shape.paint(painter);
        assertEquals("(rectangle 14,14,25,35)(rectangle 12,12,29,39)(rectangle 10,10,33,43)" +
                        "(Rectangle colored java.awt.Color[r=0,g=0,b=0])(rectangle 4,24,25,35)(rectangle 2,22,29,39)(rectangle 0,20,33,43)" +
                        "(Rectangle colored java.awt.Color[r=0,g=0,b=0])(rectangle 14,34,25,35)(rectangle 12,32,29,39)(rectangle 10,30,33,43)",
                painter.toString());
    }

    @Test
    public void testBorderShapeBounceTopWall() {
        BorderShape shape = new BorderShape(new OvalShape(16, 16, 10, -10), 3);
        shape.paint(painter);
        shape.move(1000, 1000);
        shape.paint(painter);
        shape.move(1000, 1000);
        shape.paint(painter);
        assertEquals("(oval 16,16,25,35)(rectangle 14,14,29,39)(rectangle 12,12,33,43)(rectangle 10,10,37,47)" +
                        "(oval 26,6,25,35)(rectangle 24,4,29,39)(rectangle 22,2,33,43)(rectangle 20,0,37,47)" +
                        "(oval 36,16,25,35)(rectangle 34,14,29,39)(rectangle 32,12,33,43)(rectangle 30,10,37,47)",
                painter.toString());
    }

    @Test
    public void testBorderShapeBounceRightWall() {
        BorderShape shape = new BorderShape(new RectangleShape(128, 100, 10, 10, 10, 10));
        shape.paint(painter);
        shape.move(150, 1000);
        shape.paint(painter);
        shape.move(150, 1000);
        shape.paint(painter);
        assertEquals("(rectangle 128,100,10,10)(rectangle 126,98,14,14)" +
                        "(rectangle 138,110,10,10)(rectangle 136,108,14,14)" +
                        "(rectangle 128,120,10,10)(rectangle 126,118,14,14)",
                painter.toString());
    }

    @Test
    public void testBorderShapeBounceBottomWall() {
        BorderShape shape = new BorderShape(new OvalShape(150, 128, 10, 10, 10, 10));
        shape.paint(painter);
        shape.move(1000, 150);
        shape.paint(painter);
        shape.move(1000, 150);
        shape.paint(painter);
        assertEquals("(oval 150,128,10,10)(rectangle 148,126,14,14)" +
                        "(oval 160,138,10,10)(rectangle 158,136,14,14)" +
                        "(oval 170,128,10,10)(rectangle 168,126,14,14)",
                painter.toString());
    }

    @Test
    public void testBorderShapeBounceTopAndLeft() {
        BorderShape shape = new BorderShape(new RectangleShape(12, 12, -10, -10, 10, 10));
        shape.paint(painter);
        shape.move(150, 150);
        shape.paint(painter);
        shape.move(150, 150);
        shape.paint(painter);
        assertEquals("(rectangle 12,12,10,10)(rectangle 10,10,14,14)" +
                        "(rectangle 2,2,10,10)(rectangle 0,0,14,14)" +
                        "(rectangle 12,12,10,10)(rectangle 10,10,14,14)",
                painter.toString());
    }

    @Test
    public void testBorderShapeBounceTopAndRight() {
        BorderShape shape = new BorderShape(new GemShape(128, 12, 10, -10, 10, 10));
        shape.paint(painter);
        shape.move(150, 150);
        shape.paint(painter);
        shape.move(150, 150);
        shape.paint(painter);
        assertEquals("(line 128,17,133,12)(line 133,12,138,17)(line 138,17,133,22)(line 133,22,128,17)(rectangle 126,10,14,14)" +
                        "(line 138,7,143,2)(line 143,2,148,7)(line 148,7,143,12)(line 143,12,138,7)(rectangle 136,0,14,14)" +
                        "(line 128,17,133,12)(line 133,12,138,17)(line 138,17,133,22)(line 133,22,128,17)(rectangle 126,10,14,14)",
                painter.toString());
    }

    @Test
    public void testBorderShapeBounceBottomAndRight() {
        BorderShape shape = new BorderShape(new OvalShape(128, 128, 10, 10, 10, 10));
        shape.paint(painter);
        shape.move(150, 150);
        shape.paint(painter);
        shape.move(150, 150);
        shape.paint(painter);
        assertEquals("(oval 128,128,10,10)(rectangle 126,126,14,14)" +
                        "(oval 138,138,10,10)(rectangle 136,136,14,14)" +
                        "(oval 128,128,10,10)(rectangle 126,126,14,14)",
                painter.toString());
    }

    @Test
    public void testBorderShapeBounceBottomAndLeft() {
        BorderShape shape = new BorderShape(new OvalShape(12, 128, -10, 10, 10, 10));
        shape.paint(painter);
        shape.move(150, 150);
        shape.paint(painter);
        shape.move(150, 150);
        shape.paint(painter);
        assertEquals("(oval 12,128,10,10)(rectangle 10,126,14,14)" +
                        "(oval 2,138,10,10)(rectangle 0,136,14,14)" +
                        "(oval 12,128,10,10)(rectangle 10,126,14,14)",
                painter.toString());
    }
}
