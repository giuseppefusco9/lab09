package it.unibo.mvc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private static final String DEFAULT_PATH = System.getProperty("user.home")
            + File.separator
            + "Output.txt";

    private File currentFile;

    /**
     * Constructor of Controller.
     * Set a @param DEFAULT_PATH for the initial currentFile
     */
    public Controller() {
        this.currentFile = new File(DEFAULT_PATH);
    }

    /**
     * Sets a File as current file.
     * 
     * @param cFile
     */
    public void setCurrentFile(final File cFile) {
        this.currentFile = cFile;
    }

    /**
     * Gets the current file.
     * 
     * @return the current File
     */
    public File getCurrentFile() {
        return this.currentFile;
    }

    /**
     * Gets the path of the current file.
     * 
     * @return the path of the current File
     */
    public String getPathCurrentFile() {
        return this.currentFile != null ? this.currentFile.getAbsolutePath() : "File nullo";
    }

    /**
     * A method that gets a String as input and saves its content on the current
     * file.
     * This method may throw an IOException.
     * 
     * @param input
     * @throws IOException
     */
    public void writeInput(final String input) throws IOException {
        try (FileWriter fw = new FileWriter(currentFile.getPath(), StandardCharsets.UTF_8, true)) {
            if (input.isEmpty()) {
                fw.close();
                throw new IllegalArgumentException("Input is empty!");
            }
            fw.write(System.lineSeparator() + input);
        }
    }

}
