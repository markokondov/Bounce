package bounce;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertEquals;

public class TestDynamicRectangle {
    private MockPainter painter;

    @Before
    public void setUp() {
        this.painter = new MockPainter();
    }

    @Test
    public void testBounceOffRightWall_Empty() {
        DynamicRectangleShape shape = new DynamicRectangleShape(100, 25, 10, 10, 10, 10, null);
        shape.paint(painter);
        shape.move(120, 1000);
        shape.paint(painter);
        shape.move(120, 1000);
        shape.paint(painter);
        assertEquals("(rectangle 100,25,10,10)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=0])(rectangle 110,35,10,10)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=0])(rectangle 100,45,10,10)", painter.toString());
    }

    @Test
    public void testBounceOffRightWall_Full(){
        DynamicRectangleShape shape = new DynamicRectangleShape(100, 25, 10, 10, 10, 10, Color.BLUE);
        shape.paint(painter);
        shape.move(120, 1000);
        shape.paint(painter);
        shape.move(120, 1000);
        shape.paint(painter);
        assertEquals("(Rectangle colored java.awt.Color[r=0,g=0,b=255])(rectangle 100,25,10,10)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=255])(rectangle 110,35,10,10)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=255])(rectangle 100,45,10,10)", painter.toString());
    }

    @Test
    public void testBounceOffLeftWall_Empty() {
        DynamicRectangleShape shape = new DynamicRectangleShape(10, 25, -10, 0, 10, 10, null);
        shape.paint(painter);
        shape.move(1000, 1000);
        shape.paint(painter);
        shape.move(1000, 1000);
        shape.paint(painter);
        assertEquals("(rectangle 10,25,10,10)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=0])(rectangle 0,25,10,10)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=0])(rectangle 10,25,10,10)", painter.toString());
    }

    @Test
    public void testBounceOffLeftWall_Full(){
        DynamicRectangleShape shape = new DynamicRectangleShape(10, 25, -10, 0, 10, 10, Color.BLUE);
        shape.paint(painter);
        shape.move(1000, 1000);
        shape.paint(painter);
        shape.move(1000, 1000);
        shape.paint(painter);
        assertEquals("(Rectangle colored java.awt.Color[r=0,g=0,b=255])(rectangle 10,25,10,10)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=255])(rectangle 0,25,10,10)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=255])(rectangle 10,25,10,10)", painter.toString());
    }

    @Test
    public void testBounceOffTopWall_Empty() {
        DynamicRectangleShape shape = new DynamicRectangleShape(100, 10, 0, -10, 10, 10, null);
        shape.paint(painter);
        shape.move(1000, 110);
        shape.paint(painter);
        shape.move(1000, 110);
        shape.paint(painter);
        assertEquals("(rectangle 100,10,10,10)" +
                "(rectangle 100,0,10,10)" +
                "(rectangle 100,10,10,10)", painter.toString());
    }

    @Test
    public void testBounceOffTopWall_Full(){
        DynamicRectangleShape shape = new DynamicRectangleShape(100, 10, 0, -10, 10, 10, Color.BLUE);
        shape.paint(painter);
        shape.move(1000, 110);
        shape.paint(painter);
        shape.move(1000, 110);
        shape.paint(painter);
        assertEquals("(Rectangle colored java.awt.Color[r=0,g=0,b=255])(rectangle 100,10,10,10)" +
                "(rectangle 100,0,10,10)" +
                "(rectangle 100,10,10,10)", painter.toString());
    }

    @Test
    public void testBounceOffBottomWall_Empty() {
        DynamicRectangleShape shape = new DynamicRectangleShape(100, 90, 0, 10, 10, 10, null);
        shape.paint(painter);
        shape.move(1000, 110);
        shape.paint(painter);
        shape.move(1000, 110);
        shape.paint(painter);
        assertEquals("(rectangle 100,90,10,10)" +
                "(rectangle 100,100,10,10)" +
                "(rectangle 100,90,10,10)", painter.toString());
    }

    @Test
    public void testBounceOffBottomWall_Full() {
        DynamicRectangleShape shape = new DynamicRectangleShape(100, 90, 0, 10, 10, 10, Color.BLUE);
        shape.paint(painter);
        shape.move(1000, 110);
        shape.paint(painter);
        shape.move(1000, 110);
        shape.paint(painter);
        assertEquals("(Rectangle colored java.awt.Color[r=0,g=0,b=255])(rectangle 100,90,10,10)" +
                "(rectangle 100,100,10,10)" +
                "(rectangle 100,90,10,10)", painter.toString());
    }

    @Test
    public void testBounceOffTopAndRightWall_Empty() {
        DynamicRectangleShape shape = new DynamicRectangleShape(130, 10, 10, -10, 10, 10, null);
        shape.paint(painter);
        shape.move(150, 1000);
        shape.paint(painter);
        shape.move(150, 1000);
        shape.paint(painter);
        Assert.assertEquals("(rectangle 130,10,10,10)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=0])(rectangle 140,0,10,10)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=0])(rectangle 130,10,10,10)", painter.toString());
    }

    @Test
    public void testBounceOffTopAndRightWall_Full() {
        DynamicRectangleShape shape = new DynamicRectangleShape(130, 10, 10, -10, 10, 10, Color.BLUE);
        shape.paint(painter);
        shape.move(150, 1000);
        shape.paint(painter);
        shape.move(150, 1000);
        shape.paint(painter);
        Assert.assertEquals("(Rectangle colored java.awt.Color[r=0,g=0,b=255])(rectangle 130,10,10,10)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=255])(rectangle 140,0,10,10)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=255])(rectangle 130,10,10,10)", painter.toString());
    }

    @Test
    public void testBounceOffRightAndBottomWalls_Empty() {
        DynamicRectangleShape shape = new DynamicRectangleShape(130, 130, 10, 10, 10, 10, null);
        shape.paint(painter);
        shape.move(150, 150);
        shape.paint(painter);
        shape.move(150, 150);
        shape.paint(painter);
        Assert.assertEquals("(rectangle 130,130,10,10)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=0])(rectangle 140,140,10,10)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=0])(rectangle 130,130,10,10)", painter.toString());
    }

    @Test
    public void testBounceOffRightAndBottomWalls_Full() {
        DynamicRectangleShape shape = new DynamicRectangleShape(130, 130, 10, 10, 10, 10, Color.BLUE);
        shape.paint(painter);
        shape.move(150, 150);
        shape.paint(painter);
        shape.move(150, 150);
        shape.paint(painter);
        Assert.assertEquals("(Rectangle colored java.awt.Color[r=0,g=0,b=255])(rectangle 130,130,10,10)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=255])(rectangle 140,140,10,10)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=255])(rectangle 130,130,10,10)", painter.toString());
    }

    @Test
    public void testBounceOffLeftAndBottomWalls_Empty() {
        DynamicRectangleShape shape = new DynamicRectangleShape(10, 100, -10, 10, 10, 10, null);
        shape.paint(painter);
        shape.move(1000, 120);
        shape.paint(painter);
        shape.move(1000, 120);
        shape.paint(painter);
        Assert.assertEquals("(rectangle 10,100,10,10)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=0])(rectangle 0,110,10,10)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=0])(rectangle 10,100,10,10)", painter.toString());
    }

    @Test
    public void testBounceOffLeftAndBottomWalls_Full() {
        DynamicRectangleShape shape = new DynamicRectangleShape(10, 100, -10, 10, 10, 10);
        shape.paint(painter);
        shape.move(1000, 120);
        shape.paint(painter);
        shape.move(1000, 120);
        shape.paint(painter);
        Assert.assertEquals("(Rectangle colored java.awt.Color[r=0,g=0,b=0])(rectangle 10,100,10,10)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=0])(rectangle 0,110,10,10)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=0])(rectangle 10,100,10,10)", painter.toString());
    }

    @Test
    public void testBounceOffLeftAndTopWalls_Empty() {
        DynamicRectangleShape shape = new DynamicRectangleShape(10, 15, -10, -15, null);
        shape.paint(painter);
        shape.move(1000, 1000);
        shape.paint(painter);
        shape.move(1000, 1000);
        shape.paint(painter);
        Assert.assertEquals("(rectangle 10,15,25,35)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=0])(rectangle 0,0,25,35)" +
                "(Rectangle colored java.awt.Color[r=0,g=0,b=0])(rectangle 10,15,25,35)", painter.toString());
    }

    @Test
    public void testBounceOffLeftAndTopWalls_Full() {
        DynamicRectangleShape shape = new DynamicRectangleShape(10, 15, -10, -15, Color.GREEN);
        shape.paint(painter);
        shape.move(1000, 1000);
        shape.paint(painter);
        shape.move(1000, 1000);
        shape.paint(painter);
        Assert.assertEquals("(Rectangle colored java.awt.Color[r=0,g=255,b=0])(rectangle 10,15,25,35)" +
                "(Rectangle colored java.awt.Color[r=0,g=255,b=0])(rectangle 0,0,25,35)" +
                "(Rectangle colored java.awt.Color[r=0,g=255,b=0])(rectangle 10,15,25,35)", painter.toString());
    }

}
