package de.donhilion.multiclipboard.clipboard;

/**
 * Created by Benn on 14.03.2015.
 *
 * Listener for changes in the clipboard.
 */
public interface ContentChangeListener {
    /**
     * Will be called when the content of the clipboard changes.
     *
     * @param newContent The new content of the clipboard.
     */
    public void contentChanged(String newContent);
}
