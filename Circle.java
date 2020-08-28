import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents a single circle in the fractal pattern
 *
 * @author      Joshua Scheck
 * @version     2020-08-09
 */
public class Circle {

    //----------------------------------------------------------------
    //      INSTANCE DATA
    //----------------------------------------------------------------
    /** x-coordinate for this Circle */
    private int x;
    /** y-coordinate for this Circle */
    private int y;
    /** Height and width for this Circle; height and width are equal */
    private int size;
    /** Color for this Circle */
    private Color color;

    //----------------------------------------------------------------
    //      CONSTRUCTORS
    //----------------------------------------------------------------
    /**
     * Constructor for Circle class
     *
     * @param   x       x-coordinate for this Circle
     * @param   y       y-coordinate for this Circle
     * @param   size    size for this Circle
     * @param   color   color for this Circle
     */
    public Circle(int x, int y, int size, Color color){
        this.x = x - (size / 2);
        this.y = y - (size / 2);
        this.size = size;
        this.color = color;
    }

    /**
     * Draws this Circle on the provided graphical component
     *
     * @param   g           graphics object to draw this Circle on
     * @param   xOffset     pixels on the x-axis to offset this Circle by
     * @param   yOffset     pixels on the y-axis to offset this Circle by
     */
    public void draw(Graphics g, int xOffset, int yOffset){
        g.setColor(color);
        g.fillOval(x + xOffset, y + yOffset, size, size);
    }

}