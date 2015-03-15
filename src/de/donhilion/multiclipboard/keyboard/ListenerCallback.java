package de.donhilion.multiclipboard.keyboard;

/**
 * Created by Benn on 15.03.2015.
 *
 * Callback for key presses.
 */
public interface ListenerCallback {

    /**
     * Will be called when a "get" key is pressed.
     *
     * @param key The index of the pressed key.
     */
    public void getKeyPressed(int key);

    /**
     * Will be called when a "set" key is pressed.
     *
     * @param key The index of the pressed key.
     */
    public void setKeyPressed(int key);
}
