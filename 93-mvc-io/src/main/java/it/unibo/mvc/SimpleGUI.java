package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final String TITLE = "93-mvc-io";

    private static final int PROPORTION = 5;

    private final JFrame frame = new JFrame();

    private final Controller controller = new SimpleController();

    /**
     * Constructor that sets up the whole view.
     */
    public SimpleGUI() {
        final JPanel canvas = new JPanel(new BorderLayout());

        final JTextField txtField = new JTextField();
        canvas.add(txtField, BorderLayout.NORTH);

        final JTextArea txtArea = new JTextArea();
        txtArea.setEditable(false);
        canvas.add(txtArea, BorderLayout.CENTER);

        final JPanel canvasSouth = new JPanel(new FlowLayout());
        final JButton btnPrint = new JButton();
        btnPrint.setText("Print");
        canvasSouth.add(btnPrint, BorderLayout.SOUTH);
        btnPrint.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.setString(txtField.getText());
                    controller.printCurrentString();
                    txtField.setText("");
                } catch (IllegalArgumentException | IllegalStateException e1) {
                    JOptionPane.showMessageDialog(frame, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        final JButton btnShowHistory = new JButton();
        btnShowHistory.setText("Show History");
        canvasSouth.add(btnShowHistory, BorderLayout.SOUTH);
        btnShowHistory.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    for (final String text : controller.getHistory()) {
                        txtArea.append(text + "\n");
                    }
                } catch (NoSuchElementException e2) {
                    JOptionPane.showMessageDialog(frame, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(canvas, BorderLayout.NORTH);
        frame.add(canvasSouth, BorderLayout.SOUTH);
    }

    /**
     * Method that manage the showing panel, setting the dimension in proportion
     * of the screen.
     */
    public void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);

        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle(TITLE);
        frame.setVisible(true);
    }

    /**
     * Main method that starts the graphical application.
     * @param args
     */
    public static void main(final String[] args) {
        new SimpleGUI().display();
    }

}
