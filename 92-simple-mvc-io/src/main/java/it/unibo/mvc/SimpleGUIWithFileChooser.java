package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser extends SimpleGUI {

    /**
     * Constructor of SimpleGUIWithFileChooser that add a new panel to the
     * original panel with the textfield and a button that make you choose the file
     * whose path is showed into the textField.
     */
    public SimpleGUIWithFileChooser() {
        super();
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        final JTextField txtField = new JTextField();
        txtField.setText(getFileController().getPathCurrentFile());
        final JButton selectFile = new JButton();
        selectFile.setText("Browse...");
        panel.add(selectFile, BorderLayout.EAST);
        panel.add(txtField, BorderLayout.WEST);
        getFrame().add(panel, BorderLayout.NORTH);
        selectFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                final int retValue = jFileChooser.showSaveDialog(null);
                if (retValue == JFileChooser.APPROVE_OPTION) {
                    final File selectedFile = jFileChooser.getSelectedFile();
                    getFileController().setCurrentFile(selectedFile);
                    txtField.setText(getFileController().getPathCurrentFile());
                } else if (retValue != JFileChooser.CANCEL_OPTION) {
                    JOptionPane.showMessageDialog(getFrame(), e, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
