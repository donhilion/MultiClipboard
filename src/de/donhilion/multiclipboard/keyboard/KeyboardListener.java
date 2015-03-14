package de.donhilion.multiclipboard.keyboard;

import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;
import de.donhilion.multiclipboard.gui.MainWindow;

import javax.swing.*;

/**
 * Created by Benn on 14.03.2015.
 */
public class KeyboardListener {
    Provider provider;

    public KeyboardListener() {
        provider = Provider.getCurrentProvider(true);
    }

    public void register() {
        provider.reset();
        for(int i=1; i<5; i++) {
            final int index = i;
            provider.register(KeyStroke.getKeyStroke(String.format("control F%d", i)), new HotKeyListener() {
                @Override
                public void onHotKey(HotKey hotKey) {
                    getKeyPressed(index);
                }
            });
            provider.register(KeyStroke.getKeyStroke(String.format("control shift F%d", i)), new HotKeyListener() {
                @Override
                public void onHotKey(HotKey hotKey) {
                    setKeyPressed(index);
                }
            });
        }
    }

    public void stop() {
        provider.reset();
        provider.stop();
    }

    // -- private --
    private void getKeyPressed(int key) {

    }

    private void setKeyPressed(int key) {

    }
}
