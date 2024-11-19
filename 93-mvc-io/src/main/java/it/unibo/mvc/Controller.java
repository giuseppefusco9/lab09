package it.unibo.mvc;

import java.util.List;

/**
 * Model a simple controller responsible of I/O access. 
 * It considers only the standard output, and it is able to print on it.
 */
public interface Controller {

    /**
     * A method for setting the next string to print. 
     * Null values are not acceptable, and an exception should be produced.
     * @param textToPrint
     */
    void setString(String textToPrint);

    /**
     * A method for getting the next string to print.
     * @return the next string to print
     */
    String getNextString();

    /**
     * A method for getting the history of the printed strings 
     * (in form of a List of Strings).
     * @return the history of written strings
     */
    List<String> getHistory();

    /**
     * A method that prints the current string. 
     * If the current string is unset, an @exception IllegalStateException should be thrown.
     */
    void printCurrentString();
}
