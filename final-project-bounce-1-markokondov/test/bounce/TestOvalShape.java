package bounce;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestOvalShape {
    private MockPainter painter;

    @Before
    public void setUp(){
        painter = new MockPainter();
    }

    @Test
    public void testOvalShapeDrawn() {
        OvalShape oval = new OvalShape(50, 50, 15, 15, 50, 50);
        oval.paint(painter);
        assertEquals("(oval 50,50,50,50)", painter.toString());
    }

    @Test
    public void testSimpleOvalMove() {
        OvalShape oval = new OvalShape(90, 15, 15, 15);
        oval.paint(painter);
        oval.move(500, 500);
        oval.paint(painter);
        assertEquals("(oval 90,15,25,35)(oval 105,30,25,35)", painter.toString());
    }

    @Test
    public void testOvalMoveWithBounceOffRight() {
        OvalShape oval = new OvalShape(100, 20, 12, 15);
        oval.paint(painter);
        oval.move(135, 10000);
        oval.paint(painter);
        oval.move(135, 10000);
        oval.paint(painter);
        assertEquals("(oval 100,20,25,35)(oval 110,35,25,35)(oval 98,50,25,35)", painter.toString());
    }

    @Test
    public void testOvalMoveWithBounceOffLeft() {
        OvalShape oval = new OvalShape( 10, 20, -12, 15);
        oval.paint(painter);
        oval.move(10000, 10000);
        oval.paint(painter);
        oval.move(10000, 10000);
        oval.paint(painter);
        assertEquals("(oval 10,20,25,35)(oval 0,35,25,35)"
                + "(oval 12,50,25,35)", painter.toString());
    }

    @Test
    public void testOvalMoveWithBounceOffTop() {
        OvalShape oval = new OvalShape(20, 20, 5, -20, 10, 10);
        oval.paint(painter);
        oval.move(1000, 120);
        oval.paint(painter);
        oval.move(1000, 120);
        oval.paint(painter);
        assertEquals("(oval 20,20,10,10)(oval 25,0,10,10)" +
                "(oval 30,20,10,10)", painter.toString());
    }

    @Test
    public void testOvalMoveWithBounceOffBottom() {
        OvalShape oval = new OvalShape(10, 100, 5, 20, 10, 10);
        oval.paint(painter);
        oval.move(1000, 130);
        oval.paint(painter);
        oval.move(1000, 130);
        oval.paint(painter);
        assertEquals("(oval 10,100,10,10)(oval 15,120,10,10)" +
                "(oval 20,100,10,10)", painter.toString());
    }

    @Test
    public void testOvalMoveWithBounceOffBottomAndRight(){
        OvalShape oval = new OvalShape(90, 90, 12, 15);
        oval.paint(painter);
        oval.move(125, 135);
        oval.paint(painter);
        oval.move(125, 135);
        oval.paint(painter);
        assertEquals("(oval 90,90,25,35)(oval 100,100,25,35)"
                + "(oval 88,85,25,35)", painter.toString());

    }

    @Test
    public void testOvalMoveWithBounceOffTopAndLeft(){
        OvalShape oval = new OvalShape(10, 15, -10, -15);
        oval.paint(painter);
        oval.move(1000, 1000);
        oval.paint(painter);
        oval.move(1000, 1000);
        oval.paint(painter);
        assertEquals("(oval 10,15,25,35)(oval 0,0,25,35)" +
                "(oval 10,15,25,35)", painter.toString());
    }

    @Test
    public void testOvalMoveWithBounceOffBottomAndLeft() {
        OvalShape oval = new OvalShape(10, 100, -10, 15, 10, 10);
        oval.paint(painter);
        oval.move(1000, 125);
        oval.paint(painter);
        oval.move(1000, 125);
        oval.paint(painter);
        assertEquals("(oval 10,100,10,10)(oval 0,115,10,10)" +
                "(oval 10,100,10,10)", painter.toString());
    }

    @Test
    public void testOvalMoveWithBounceOffTopAndRight() {
        OvalShape oval = new OvalShape(130, 10, 10, -10, 10, 10);
        oval.paint(painter);
        oval.move(150, 1000);
        oval.paint(painter);
        oval.move(150, 1000);
        oval.paint(painter);
        assertEquals("(oval 130,10,10,10)(oval 140,0,10,10)" +
                "(oval 130,10,10,10)", painter.toString());
    }
}
