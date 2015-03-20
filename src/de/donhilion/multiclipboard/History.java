package de.donhilion.multiclipboard;

import java.util.LinkedList;

/**
 * Created by Benn on 20.03.2015.
 *
 * Holds the last contents of the clipboard.
 */
public class History {
    private LinkedList<String> history = new LinkedList<String>();
    private int maxHistory = 10;

    public History() {}

    public void setMaxHistory(int maxHistory) {
        this.maxHistory = maxHistory;
        if(history.size() > maxHistory) {
            for(int i=history.size() - 1; i>= maxHistory; i--) {
                history.remove(i);
            }
        }
    }

    public void newContent(String content) {
        history.addFirst(content);
        if(history.size() > maxHistory) {
            history.removeLast();
        }
    }
}
