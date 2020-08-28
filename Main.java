/**
 * Main class which establishes the introduction between Subject and Observers
 * while also instantiating the Gui for user input and the Display for visualizing
 * the generated fractal patterns
 *
 * @author  Joshua Scheck
 * @since   2020-08-20
 */
public class Main {
    /** Graphics panel for displaying fractal pattern */
    private static Observer display;
    /** Generates fractal patterns */
    private static Subject fractalGenerator;
    /** Graphical user interface for manipulating fractal pattern parameters */
    private static Gui gui;

    /**
     * Entry point for application.
     *
     * @param   args    supplied command line arguments
     */
    public static void main(String[] args) {
        fractalGenerator = new FractalGenerator();
        gui = new Gui(fractalGenerator);
        display = new Display(fractalGenerator);
    }
}
