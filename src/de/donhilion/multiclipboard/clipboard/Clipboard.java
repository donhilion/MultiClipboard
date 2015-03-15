package de.donhilion.multiclipboard.clipboard;

import java.awt.*;
import java.awt.datatransfer.*;

/**
 * Created by Benn on 14.03.2015.
 *
 * Class for accessing the system clipboard.
 */
public class Clipboard implements ClipboardOwner {
    private ContentChangeListener listener;

    /**
     * Sets the listener for changes.
     *
     * @param listener The listener to set.
     */
    public void setContentChangeListener(ContentChangeListener listener) {
        this.listener = listener;
    }

    /**
     * Sets the clipboard content.
     *
     * @param text The text to set as content.
     */
    public void setClipboardContent(String text) {
        java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable transferable = new StringSelection(text);
        clipboard.setContents(transferable, this);
    }

    /**
     * Gets the clipboard content, if it is a string.
     *
     * @return The clipboard content if it is a string.
     */
    public String getClipboardContent() {
        java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);

        boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasTransferableText) {
            try {
                return (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return null;
    }

    /**
     * Registers for changes.
     */
    public void registerForListening() {
        java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        clipboard.setContents(contents, this);
    }

    @Override
    public void lostOwnership(java.awt.datatransfer.Clipboard clipboard, Transferable contents) {
        if(listener != null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            listener.contentChanged(getClipboardContent());
            registerForListening();
        }
    }
}
