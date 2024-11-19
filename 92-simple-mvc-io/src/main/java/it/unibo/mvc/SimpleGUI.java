package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public class SimpleGUI {

    private static final String FRAME_TITLE = "92-simple-mvc-io";
    private static final String BUTTON_CONTENT = "Save";
    private static final int PROPORTION = 2;
    private final JFrame frame;
    private final Controller fileController = new Controller();

    /**
     * Constructor of SimpleGUI(), that sets up the whole view.
     */
    public SimpleGUI() {
        frame = new JFrame();
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JTextArea text = new JTextArea(10, 20);
        canvas.add(text, BorderLayout.NORTH);
        final JButton save = new JButton(BUTTON_CONTENT);
        canvas.add(save, BorderLayout.SOUTH);
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    fileController.writeInput(text.getText());
                    JOptionPane.showMessageDialog(frame, "File modified!", "Modifying file...", JOptionPane.INFORMATION_MESSAGE);
                    text.setText("");
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(frame, "File writing error: " + e1.getMessage(),
                            "Error IO", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException e2) {
                    JOptionPane.showMessageDialog(frame, "non-valid argument: " + e2.getMessage(),
                            "Error argument", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    /**
     * Method that returns the fileController.
     * @return the file Controller of this class
     */
    protected Controller getFileController() {
        return fileController;
    }

    /**
     * Method that returns the main frame.
     * @return the frame of this class
    */
    protected JFrame getFrame() {
        return frame;
    }

    /**
     * Method that manage the showing panel, setting the dimension in proportion
     * of the screen.
     */
    protected void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.pack();

        frame.setLocationByPlatform(true);
        frame.setTitle(FRAME_TITLE);
        frame.setVisible(true);
    }

    /**
     * Main method that starts the graphical application.
     * @param args
     */
    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser().display();
    }

}
