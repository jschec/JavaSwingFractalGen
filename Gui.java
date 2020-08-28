import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JColorChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Graphical user interface that pushes data to the provided Subject
 *
 * @author      Joshua Scheck
 * @version     2020-08-09
 */
public class Gui extends JFrame {

    //----------------------------------------------------------------
    //      INSTANCE DATA
    //----------------------------------------------------------------
    /** Object this Gui pushes new data to */
    private Subject subject;
    /** Panel containing user interface components */
    private JPanel contentPane;
    /** Hex representation of cactusColor */
    private JTextField cactusColorDisplay;
    /** Hex representation of pearColor */
    private JTextField pearColorDisplay;
    /** Color of the cactus bodies */
    private JColorChooser cactusColor;
    /** Color of the cactus tips */
    private JColorChooser pearColor;
    /** Number of levels of fractals to draw */
    private JSpinner recursionDepth;
    /** Ratio of child to parent radius */
    private JSpinner radiusRatio;
    /** Angle from parent to child Circles */
    private JSpinner childrenAngle;

    //----------------------------------------------------------------
    //      CONSTRUCTORS
    //----------------------------------------------------------------
    /**
     * Constructor for Gui class
     * 
     * @param   subject     object for this to push new input data to
     */
    public Gui(Subject subject) {

        this.subject = subject;

        // set default colors
        cactusColor = new JColorChooser(Color.green);
        pearColor = new JColorChooser(Color.PINK);

        // set up panel layout and sizing
        setSize(450, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(5, 2, 20, 20));


        // set up cactusColorDisplay text field
        cactusColorDisplay = new JTextField("#"+Integer.toHexString(
            cactusColor.getColor().getRGB()).substring(2));
        cactusColorDisplay.setColumns(10);
        cactusColorDisplay.setBackground(cactusColor.getColor());
        cactusColorDisplay.setEditable(false);
        contentPane.add(cactusColorDisplay);

        // set up cactusColor color chooser
        JButton cactusColorBtn = new JButton("Cactus color");
        contentPane.add(cactusColorBtn);
        cactusColorBtn.addActionListener(new ActionListener() {
            /**
             * Called with cactusColorBtn button press. Opens JColorChooser
             * dialog for selecting cactusColor. Pushes new data to the 
             * provided Subject
             *
             * @param   e   event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Color currCactusColor = cactusColor.getColor();

                // color chooser dialog
                Color newCactusColor = JColorChooser.showDialog(cactusColor,
                        "Select a color", currCactusColor);
                cactusColor.setColor(newCactusColor);
                cactusColorDisplay.setBackground(newCactusColor);
                cactusColorDisplay.setText("#"+Integer.toHexString(
                    newCactusColor.getRGB()).substring(2));
                submit();
            }
        });

        // set up pearColorDisplay text field
        pearColorDisplay = new JTextField("#"+Integer.toHexString(
            pearColor.getColor().getRGB()).substring(2));
        pearColorDisplay.setColumns(10);
        pearColorDisplay.setBackground(pearColor.getColor());
        pearColorDisplay.setEditable(false);
        contentPane.add(pearColorDisplay);

        // set up pearColor color chooser
        JButton pearColorBtn = new JButton("Pear color");
        contentPane.add(pearColorBtn);
        pearColorBtn.addActionListener(new ActionListener() {

            /**
             * Called with pearColorBtn button press. Opens JColorChooser
             * dialog for selecting pearColor. Pushes new data to the 
             * provided Subject
             *
             * @param   e   event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Color currPearColor = pearColor.getColor();

                // color chooser dialog
                Color newPearColor = JColorChooser.showDialog(pearColor,
                        "Select a color", currPearColor);
                pearColor.setColor(newPearColor);
                pearColorDisplay.setBackground(newPearColor);
                pearColorDisplay.setText("#"+Integer.toHexString(
                    newPearColor.getRGB()).substring(2));
                submit();
            }
        });

        // set up recursionDepth spinner
        contentPane.add(new JLabel("Recursion depth"));
        recursionDepth = new JSpinner(new SpinnerNumberModel(2, 2, 10, 1));
        contentPane.add(recursionDepth);
        recursionDepth.addChangeListener(new ChangeListener() {
            /**
             * Called with recursionDepth state change. Pushes new data 
             * to the provided Subject
             *
             * @param   e   event to be processed
             */
            @Override
            public void stateChanged(ChangeEvent e) {
                submit();
            }
        });

        // set up radiusRatio spinner
        contentPane.add(new JLabel("Child to Parent Radius Ratio "));
        radiusRatio = new JSpinner(new SpinnerNumberModel(40, 40, 70, 1));
        contentPane.add(radiusRatio);
        radiusRatio.addChangeListener(new ChangeListener() {
            /**
             * Called with radiusRatio state change. Pushes new data to 
             * the provided Subject
             *
             * @param   e   event to be processed
             */
            @Override
            public void stateChanged(ChangeEvent e) {
                submit();
            }
        });

        // set up childrenAngle spinner
        contentPane.add(new JLabel("Children angle"));
        childrenAngle = new JSpinner(new SpinnerNumberModel(45.0, 30.0, 
            90.0, 1.0));
        contentPane.add(childrenAngle);
        childrenAngle.addChangeListener(new ChangeListener() {
            /**
             * Called with childrenAngle state change. Pushes new data to 
             * the provided Subject
             *
             * @param   e   event to be processed
             */
            @Override
            public void stateChanged(ChangeEvent e) {
                submit();
            }
        });

        // set up other default panel properties
        setTitle("Fractal Pattern Inputs");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Retrieves data from user input components and pushes the retrieved 
     * data to the provided Subject
     */
    private void submit() {
        int recursionDepthVal = (int) recursionDepth.getValue();
        int radiusVal = (int) radiusRatio.getValue();
        double startingAngleVal = (double) childrenAngle.getValue();

        subject.setData(recursionDepthVal, radiusVal, cactusColor.getColor(), 
            pearColor.getColor(), startingAngleVal);
    }

}