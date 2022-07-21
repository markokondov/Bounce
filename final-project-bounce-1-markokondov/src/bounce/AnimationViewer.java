package bounce;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * Simple GUI program to show an animation of shapes. Class AnimationViewer is
 * a special kind of GUI component (JPanel), and as such an instance of
 * AnimationViewer can be added to a JFrame object. A JFrame object is a
 * window that can be closed, minimised, and maximised. The state of an
 * AnimationViewer object comprises a list of Shapes and a Timer object. An
 * AnimationViewer instance subscribes to events that are published by a Timer.
 * In response to receiving an event from the Timer, the AnimationViewer iterates
 * through a list of Shapes requesting that each Shape paints and moves itself.
 *
 * @author Ian Warren
 */
@SuppressWarnings("serial")
public class AnimationViewer extends JPanel implements ActionListener {
    // Frequency in milliseconds for the Timer to generate events.
    private static final int DELAY = 20;

    // Collection of Shapes to animate.
    private List<Shape> shapes;

    private Timer timer = new Timer(DELAY, this);

    /**
     * Creates an AnimationViewer instance with a list of Shape objects and
     * starts the animation.
     */
    public AnimationViewer() {
        shapes = new ArrayList<Shape>();

        // Populate the list of Shapes.

        shapes.add(new RectangleShape(0, 0, 2, 3));
        shapes.add(new RectangleShape(10, 10, 5, 7));
        shapes.add(new OvalShape(30, 10, 10, 2, 30, 50));
        shapes.add(new GemShape(100, 100, 1, 1, 70, 70));
        shapes.add(new GemShape(400, 400, 1, 1, 30, 30));
        shapes.add(new BorderShape(new GemShape(60, 250, 5, 5, 70, 100), 4));
        shapes.add(new DynamicRectangleShape(345, 324, 3, 5, Color.BLUE));

        NestingShape outerNest = new NestingShape(0, 0, 3, 3, 450, 400);
        NestingShape middleNest = new NestingShape(30, 15, -4, 2, 200, 300);
        NestingShape anotherMiddleNest = new NestingShape(300, 300, 1, -3, 30, 30);
        NestingShape innerNest = new NestingShape(15, 15, 8, 8, 10, 10);
        Shape innerShape = new DynamicRectangleShape(0, 0, 4, 1, 15, 15, Color.DARK_GRAY);
        Shape anotherInnerShape = new DynamicRectangleShape(0, 0, -1, 1, 6, 6, Color.red);
        Shape oneLastNestedShape = new OvalShape(40, 10, 10, -11, 20, 50);

        innerNest.add(anotherInnerShape);
        middleNest.add(innerNest);
        anotherMiddleNest.add(innerShape);
        middleNest.add(oneLastNestedShape);
        outerNest.add(middleNest);
        outerNest.add(anotherMiddleNest);

        shapes.add(outerNest);

        shapes.add(new RectangleShape("Hello"));
        shapes.add(new GemShape("Welcome to Bounce", 100, 100, 1, -3, 200, 200));

        shapes.add(new DynamicRectangleShape(300, 300, -5, -2, Color.GREEN));
        shapes.add(new BorderShape(new OvalShape()));

        shapes.add(new BorderShape(new DynamicRectangleShape(14, 5, -2, 3, 50, 50, Color.GREEN)));
        shapes.add(new DynamicRectangleShape(14, 50, 1, -3, 50, 50, Color.GREEN));

        // Start the animation.
        timer.start();
    }

    /**
     * Called by the Swing framework whenever this AnimationViewer object
     * should be repainted. This can happen, for example, after an explicit
     * repaint() call or after the window that contains this AnimationViewer
     * object has been opened, exposed or moved.
     */
    public void paintComponent(Graphics g) {
        // Call inherited implementation to handle background painting.
        super.paintComponent(g);

        // Calculate bounds of animation screen area.
        int width = getSize().width;
        int height = getSize().height;

        // Create a GraphicsPainter that Shape objects will use for drawing.
        // The GraphicsPainter delegates painting to a basic Graphics object.
        Painter painter = new GraphicsPainter(g);

        // Progress the animation.
        for (Shape s : shapes) {
            s.paint(painter);
            s.move(width, height);
        }
    }

    /**
     * Notifies this AnimationViewer object of an ActionEvent. ActionEvents are
     * received by the Timer.
     */
    public void actionPerformed(ActionEvent e) {
        // Request that the AnimationViewer repaints itself. The call to
        // repaint() will cause the AnimationViewer's paintComponent() method
        // to be called.
        repaint();
    }


    /**
     * Main program method to create an AnimationViewer object and display this
     * within a JFrame window.
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Animation viewer");
                frame.add(new AnimationViewer());

                // Set window properties.
                frame.setSize(500, 500);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
