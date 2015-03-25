package de.donhilion.multiclipboard.gui;

/**
 * Created by Benn on 24.03.2015.
 *
 * Callback for the history view.
 */
public interface HistoryCallback {

    /**
     * Will be called when an entry of the history view was picked.
     *
     * @param content The picked content or null if nothing was picked.
     */
    public void picked(String content);
}
