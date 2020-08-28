import java.awt.Color;
import java.util.Iterator;

/**
 * Requirements for Subjects
 *
 * @author      Joshua Scheck
 * @version     2020-08-09
 */
public interface Subject {

    /**
     * Observer subscribes from changes in this Subject
     *
     * @param   observer    Observer to subscribe to this Subject
     */
    public void registerObserver(Observer observer);

    /**
     * Observer unsubscribes from changes in this Subject
     *
     * @param   observer    Observer to unsubscribe from this Subject
     */
    public void removeObserver(Observer observer);

    /**
     * Notifies all subscribed Observers that a change was made to this Subject
     */
    public void notifyObservers();

    /**
     * Sets new data for this Subject
     *
     * @param   recursionDepth  number of levels of fractals to draw
     * @param   radiusRatio     ratio of child to parent radius
     * @param   cactusColor     color of the cactus body
     * @param   pearColor       color of the cactus tips
     * @param   childrenAngle   angle of the children
     */
    public void setData(int recursionDepth, int radiusRatio, Color cactusColor, Color pearColor, double childrenAngle);

    /**
     * Retrieves data from this Subject
     * 
     * @return  Circles composing the fractal pattern
     */
    public Iterator<Circle> getData();
}
