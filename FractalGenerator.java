import java.awt.Color;
import java.util.Iterator;

/**
 * Generates a fractal pattern representing a cactus
 *
 * @author      Joshua Scheck
 * @version     2020-08-09
 */
public class FractalGenerator implements Subject {

    //----------------------------------------------------------------
    //      CONSTANT DATA
    //----------------------------------------------------------------
    /** Starting angle to place to draw circles from */
    public final double STARTING_ANGLE = Math.PI/2;

    //----------------------------------------------------------------
    //      INSTANCE DATA
    //----------------------------------------------------------------
    /** Angle from parent to child Circles */
    private double angleIncrement;
    /** Number of levels of fractals to draw */
    private int recursionDepth;
    /** Ratio of child to parent radius */
    private int radiusRatio;
    /** Color of the cactus bodies */
    private Color cactusColor;
    /** Color of the cactus tips */
    private Color pearColor;
    /** List of subscribed observers */
    private ArrayList<Observer> observers;

    //----------------------------------------------------------------
    //      CONSTRUCTORS
    //----------------------------------------------------------------
    /**
     * Constructor for FractalGenerator class
     */
    public FractalGenerator() {
        observers = new ArrayList<Observer>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setData(int depth, int radius, Color cactusColor, 
    Color pearColor, double childrenAngle) {
        this.recursionDepth = depth;
        this.radiusRatio = radius;
        this.cactusColor = cactusColor;
        this.pearColor = pearColor;
        this.angleIncrement = Math.toRadians(childrenAngle);
        notifyObservers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<Circle> getData() {
        return generateFractal();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeObserver(Observer observer) {
        int idx = observers.indexOf(observer);
        observers.remove(idx);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyObservers() {
        Iterator<Observer> observerElements = observers.iterator();
        while (observerElements.hasNext()) {
            Observer currentObserver = observerElements.next();
            currentObserver.update();
        }
    }

    /**
     * Generates cactus fractal pattern
     *
     * @return      Circles that compose the generated fractal pattern
     */
    public Iterator<Circle> generateFractal(){
        ArrayList<Circle> fractal = new ArrayList<Circle>();

        // size is arbitrarily set to 100. Could be any size.
        fractal = generateFractal(STARTING_ANGLE, recursionDepth, 0, 0, 
        100, fractal);
        Iterator<Circle> circleElements = fractal.iterator();
        return circleElements;
    }

    /**
     * Recursively generates Circles composing the cactus fractal pattern
     *
     * @param   angle           angle from parent to child Circles
     * @param   recursionNum    number of levels of fractals to draw
     * @param   x               x-coordinate for the generated Circle
     * @param   y               y-coordinate for the generated Circle
     * @param   size            size for the generated Circle
     * @param   fractal         generated ArrayList of Circles
     * @return                  a list of Circles composing the cactus 
     *                          fractal pattern
     */
    private ArrayList<Circle> generateFractal(double angle, int recursionNum, 
        double x, double y, int size, ArrayList<Circle> fractal){
        if( size != 0 && recursionNum != 0 ) {
            Circle cactusElement;
            if (recursionNum == 1) {
                cactusElement = new Circle((int) x, (int) y, size, pearColor);
            } else {
                cactusElement = new Circle((int) x, (int) y, size, cactusColor);
            }
            fractal.add(cactusElement);
            int relativeSize = (int) (size * (radiusRatio / 100.00) );
            int hypotenuse = (int) ( (size + relativeSize) / 2.0);

            int leftYShift = (int) (Math.sin(angle + angleIncrement)*(hypotenuse));
            int leftXShift = (int) (Math.cos(angle + angleIncrement)*(hypotenuse));
            int rightYShift = (int) (Math.sin(angle - angleIncrement)*(hypotenuse));
            int rightXShift = (int) (Math.cos(angle - angleIncrement)*(hypotenuse));

            generateFractal(angle + angleIncrement, recursionNum - 1, x-leftXShift, 
                y-leftYShift, relativeSize, fractal);
            generateFractal(angle - angleIncrement, recursionNum - 1, x-rightXShift, 
                y-rightYShift, relativeSize, fractal);
        }
        return fractal;
    }

}
