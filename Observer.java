/**
 * Requirements for Observers
 *
 * @author      Joshua Scheck
 * @version     2020-08-09
 */
public interface Observer {

    /**
     * This Observer is signaled about changes in the subscribed Subject
     */
    public void update();
}
