package de.donhilion.multiclipboard.keyboard;

import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;

import javax.swing.*;

/**
 * Created by Benn on 14.03.2015.
 *
 * Listener for hot key presses.
 */
public class KeyboardListener {
    Provider provider;
    ListenerCallback callback;

    public KeyboardListener() {
        provider = Provider.getCurrentProvider(true);
    }

    /**
     * Registers hot keys.
     */
    public void register() {
        provider.reset();
        for(int i=1; i<5; i++) {
            final int index = i;
            provider.register(KeyStroke.getKeyStroke(String.format("control F%d", i)), new HotKeyListener() {
                @Override
                public void onHotKey(HotKey hotKey) {
                    if(callback != null) {
                        callback.getKeyPressed(index);
                    }
                }
            });
            provider.register(KeyStroke.getKeyStroke(String.format("control shift F%d", i)), new HotKeyListener() {
                @Override
                public void onHotKey(HotKey hotKey) {
                    if(callback != null) {
                        callback.setKeyPressed(index);
                    }
                }
            });
        }
    }

    /**
     * Stops listening.
     */
    public void stop() {
        provider.reset();
        provider.stop();
    }

    /**
     * Sets the callback for hot key presses.
     *
     * @param callback The callback to set.
     */
    public void setCallback(ListenerCallback callback) {
        this.callback = callback;
    }
}
