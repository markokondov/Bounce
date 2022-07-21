package bounce;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestGemShape {
    private MockPainter painter;

    @Before
    public void setUp() {
        this.painter = new MockPainter();
    }

    @Test
    public void testRegularGemPaintedCorrectly() {
        GemShape gem = new GemShape(25, 25, 20, 20, 50, 50);
        gem.paint(painter);
        assertEquals("(line 25,55,25,45)(line 25,45,45,25)(line 45,25,55,25)(line 55,25,75,45)" +
                "(line 75,45,75,55)(line 75,55,55,75)(line 55,75,45,75)(line 45,75,25,55)", painter.toString());
    }

    @Test
    public void testSmallGemPaintedCorrectly() {
        GemShape gem = new GemShape();
        gem.paint(painter);
        assertEquals("(line 0,17,12,0)(line 12,0,25,17)(line 25,17,12,35)(line 12,35,0,17)", painter.toString());
    }

    @Test
    public void testSimpleGemMove() {
        GemShape gem = new GemShape(90, 15, 15, 15);
        gem.paint(painter);
        gem.move(500, 500);
        gem.paint(painter);
        assertEquals("(line 90,32,102,15)(line 102,15,115,32)(line 115,32,102,50)(line 102,50,90,32)" +
                "(line 105,47,117,30)(line 117,30,130,47)(line 130,47,117,65)(line 117,65,105,47)", painter.toString());
    }

    @Test
    public void testGemMoveWithBounceOffRight() {
        GemShape gem = new GemShape(100, 20, 12, 15);
        gem.paint(painter);
        gem.move(135, 10000);
        gem.paint(painter);
        gem.move(135, 10000);
        gem.paint(painter);
        assertEquals("(line 100,37,112,20)(line 112,20,125,37)(line 125,37,112,55)(line 112,55,100,37)" +
                "(line 110,52,122,35)(line 122,35,135,52)(line 135,52,122,70)(line 122,70,110,52)" +
                "(line 98,67,110,50)(line 110,50,123,67)(line 123,67,110,85)(line 110,85,98,67)", painter.toString());
    }

    @Test
    public void testGemMoveWithBounceOffLeft() {
        GemShape gem = new GemShape( 10, 20, -12, 15);
        gem.paint(painter);
        gem.move(10000, 10000);
        gem.paint(painter);
        gem.move(10000, 10000);
        gem.paint(painter);
        assertEquals("(line 10,37,22,20)(line 22,20,35,37)(line 35,37,22,55)(line 22,55,10,37)" +
                "(line 0,52,12,35)(line 12,35,25,52)(line 25,52,12,70)(line 12,70,0,52)" +
                "(line 12,67,24,50)(line 24,50,37,67)(line 37,67,24,85)(line 24,85,12,67)", painter.toString());
    }

    @Test
    public void testGemMoveWithBounceOffTop() {
        GemShape gem = new GemShape(20, 20, 5, -20, 10, 10);
        gem.paint(painter);
        gem.move(1000, 120);
        gem.paint(painter);
        gem.move(1000, 120);
        gem.paint(painter);
        assertEquals("(line 20,25,25,20)(line 25,20,30,25)(line 30,25,25,30)(line 25,30,20,25)" +
                "(line 25,5,30,0)(line 30,0,35,5)(line 35,5,30,10)(line 30,10,25,5)(line 30,25,35,20)" + "" +
                "(line 35,20,40,25)(line 40,25,35,30)(line 35,30,30,25)", painter.toString());
    }

    @Test
    public void testGemMoveWithBounceOffBottom() {
        GemShape gem = new GemShape(10, 100, 5, 20, 10, 10);
        gem.paint(painter);
        gem.move(1000, 130);
        gem.paint(painter);
        gem.move(1000, 130);
        gem.paint(painter);
        assertEquals("(line 10,105,15,100)(line 15,100,20,105)(line 20,105,15,110)(line 15,110,10,105)" +
                "(line 15,125,20,120)(line 20,120,25,125)(line 25,125,20,130)(line 20,130,15,125)" +
                "(line 20,105,25,100)(line 25,100,30,105)(line 30,105,25,110)(line 25,110,20,105)", painter.toString());
    }

    @Test
    public void testGemMoveWithBounceOffBottomAndRight(){
        GemShape gem = new GemShape(90, 90, 12, 15);
        gem.paint(painter);
        gem.move(125, 135);
        gem.paint(painter);
        gem.move(125, 135);
        gem.paint(painter);
        assertEquals("(line 90,107,102,90)(line 102,90,115,107)(line 115,107,102,125)(line 102,125,90,107)" +
                "(line 100,117,112,100)(line 112,100,125,117)(line 125,117,112,135)(line 112,135,100,117)" +
                "(line 88,102,100,85)(line 100,85,113,102)(line 113,102,100,120)(line 100,120,88,102)", painter.toString());

    }

    @Test
    public void testGemMoveWithBounceOffTopAndLeft(){
        GemShape gem = new GemShape(10, 15, -10, -15);
        gem.paint(painter);
        gem.move(1000, 1000);
        gem.paint(painter);
        gem.move(1000, 1000);
        gem.paint(painter);
        assertEquals("(line 10,32,22,15)(line 22,15,35,32)(line 35,32,22,50)(line 22,50,10,32)" +
                "(line 0,17,12,0)(line 12,0,25,17)(line 25,17,12,35)(line 12,35,0,17)" +
                "(line 10,32,22,15)(line 22,15,35,32)(line 35,32,22,50)(line 22,50,10,32)", painter.toString());
    }

    @Test
    public void testGemMoveWithBounceOffBottomAndLeft() {
        GemShape gem = new GemShape(10, 100, -10, -15, 10, 10);
        gem.paint(painter);
        gem.move(1000, 1000);
        gem.paint(painter);
        gem.move(1000, 1000);
        gem.paint(painter);
        assertEquals("(line 10,105,15,100)(line 15,100,20,105)(line 20,105,15,110)(line 15,110,10,105)" +
                "(line 0,90,5,85)(line 5,85,10,90)(line 10,90,5,95)(line 5,95,0,90)" +
                "(line 10,75,15,70)(line 15,70,20,75)(line 20,75,15,80)(line 15,80,10,75)", painter.toString());
    }

    @Test
    public void testGemMoveWithBounceOffTopAndRight() {
        GemShape gem = new GemShape(90, 10, 10, -10, 50, 50);
        gem.paint(painter);
        gem.move(150, 1000);
        gem.paint(painter);
        gem.move(150, 1000);
        gem.paint(painter);
        assertEquals("(line 90,40,90,30)(line 90,30,110,10)(line 110,10,120,10)(line 120,10,140,30)" +
                "(line 140,30,140,40)(line 140,40,120,60)(line 120,60,110,60)(line 110,60,90,40)" +
                "(line 100,30,100,20)(line 100,20,120,0)(line 120,0,130,0)(line 130,0,150,20)" +
                "(line 150,20,150,30)(line 150,30,130,50)(line 130,50,120,50)(line 120,50,100,30)" +
                "(line 90,40,90,30)(line 90,30,110,10)(line 110,10,120,10)(line 120,10,140,30)" +
                "(line 140,30,140,40)(line 140,40,120,60)(line 120,60,110,60)(line 110,60,90,40)", painter.toString());
    }

}
