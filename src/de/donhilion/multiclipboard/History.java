package de.donhilion.multiclipboard;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Benn on 20.03.2015.
 *
 * Holds the last contents of the clipboard.
 */
public class History {
    private LinkedList<String> history = new LinkedList<String>();
    private int maxHistory = 10;

    public History() {}

    /**
     * Sets the maximum size of the history.
     *
     * @param maxHistory The maximum size of the history.
     */
    public void setMaxHistory(int maxHistory) {
        this.maxHistory = maxHistory;
        if(history.size() > maxHistory) {
            for(int i=history.size() - 1; i>= maxHistory; i--) {
                history.remove(i);
            }
        }
    }

    /**
     * Adds new content to the history.
     *
     * @param content The new content.
     */
    public void newContent(String content) {
        if(maxHistory > 0) {
            history.addFirst(content);
            if (history.size() > maxHistory) {
                history.removeLast();
            }
        }
    }

    /**
     * Returns the history.
     *
     * @return The history.
     */
    public List<String> getHistory() {
        return history;
    }
}
