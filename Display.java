import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Iterator;

/**
 * Subscribes to changes in FractalGenerator and displays the retrieved
 * fractal pattern
 *
 * @author      Joshua Scheck
 * @version     2020-08-09
 */
public class Display extends JFrame implements Observer {

    //----------------------------------------------------------------
    //      INSTANCE DATA
    //----------------------------------------------------------------
    /** Panel for displaying graphics */
    private GPanel panel;
    /** Size of graphics panel */
    private Dimension size;
    /** Object this Display is subscribed to and contains fractal pattern data */
    private Subject subject;

    //----------------------------------------------------------------
    //      CONSTRUCTORS
    //----------------------------------------------------------------
    /**
     * Constructor for Display class
     *
     * @param   subject     object for this Display to subscribe to
     */
    public Display(Subject subject) {
        // this Display subscribes to provided subject
        this.subject = subject;
        this.subject.registerObserver(this);

        // set up graphics panel
        panel = new GPanel();
        size = panel.getBounds().getSize();
        getContentPane().add(panel);
        panel.repaint();
        panel.setLayout(null);

        // listen to changes in gPanel size
        panel.addComponentListener(new ComponentAdapter() {

            /**
             * Called when gPanel size is changed. This does not include
             * maximizing window.
             *
             * @param   e   event to be processed
             */
            public void componentResized(ComponentEvent e) {
                size = e.getComponent().getBounds().getSize();
            }
        });

        // set up default parameters for this JFrame
        setSize(550, 600);
        setTitle("Fractal Pattern Display");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        panel.repaint();
    }

    /**
     * Retrieves fractal data from the subscribed Subject and draws the 
     * provided Circles
     *
     * @param   g   graphics component to draw Circle(s) on
     */
    public void paintFractal(Graphics g) {
        Iterator<Circle> fractalElements = subject.getData();
        while (fractalElements.hasNext()) {
            Circle curr = fractalElements.next();
            // places fractal in center of panel
            curr.draw(g, size.width / 2, size.height / 2);
        }

    }

    //----------------------------------------------------------------
    //      INTERNAL CLASSES
    //----------------------------------------------------------------
    /**
     * Graphics panel displaying the provided fractal pattern
     */
    private class GPanel extends JPanel {

        /**
         * Called by the system to paint this GPanel
         *
         * @param   g   graphics for this GPanel
         */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            paintFractal(g);
        }
    }
}