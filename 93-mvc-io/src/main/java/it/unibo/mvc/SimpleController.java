package it.unibo.mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Implementation of interface Controller.
 */
public final class SimpleController implements Controller {

    private String currentString;
    private final List<String> history;
    /**
     * Constructor of SimpleController.
     */

    public SimpleController() {
        this.currentString = "";
        this.history = new ArrayList<>();
    }

    @Override
    public void setString(final String textToPrint) {
        if (textToPrint.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        this.currentString = textToPrint;
        history.add(textToPrint);
    }

    @Override
    public String getNextString() {
        return this.currentString;
    }

    @Override
    public List<String> getHistory() {
        if (this.history.isEmpty()) {
            throw new NoSuchElementException("List is empty!");
        }
        return new ArrayList<>(this.history);
    }

    @Override
    public void printCurrentString() {
        if (this.currentString.isEmpty()) {
            throw new IllegalStateException("Message is unset!");
        }
        System.out.println(this.currentString); // NOPMD due to the requests
    }

}
