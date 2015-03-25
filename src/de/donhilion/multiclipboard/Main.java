package de.donhilion.multiclipboard;

import de.donhilion.multiclipboard.clipboard.Clipboard;
import de.donhilion.multiclipboard.clipboard.ContentChangeListener;
import de.donhilion.multiclipboard.gui.HistoryCallback;
import de.donhilion.multiclipboard.gui.HistoryWindow;
import de.donhilion.multiclipboard.gui.MainWindow;
import de.donhilion.multiclipboard.keyboard.KeyboardListener;
import de.donhilion.multiclipboard.keyboard.ListenerCallback;
import de.donhilion.multiclipboard.systemtray.SystemTrayHandler;

/**
 * Created by Benn on 15.03.2015.
 *
 * The main class.
 */
public class Main {
    private static MainWindow window;
    private static String[] fields = new String[5];
    private static History history = new History();

    // clipboard
    private static Clipboard clipboard;
    private static ContentChangeListener clipboardListener = new ContentChangeListener() {
        @Override
        public void contentChanged(String newContent) {
            Main.contentChanged(newContent);
        }
    };

    // keyboard
    private static KeyboardListener keyboard;
    private static ListenerCallback keyboardCallback = new ListenerCallback() {
        @Override
        public void getKeyPressed(int key) {
            get(key);
        }

        @Override
        public void setKeyPressed(int key) {
            set(key);
        }
    };

    /**
     * Entry point of the application.
     *
     * @param args The program arguments.
     */
    public static void main(String[] args) {
        SystemTrayHandler tray = new SystemTrayHandler();
        tray.setOnClickCallback(new Runnable() {
            @Override
            public void run() {
                window.show();
            }
        });
        window = new MainWindow(!tray.addIcon());

        window.setOnCloseListener(new Runnable() {
            @Override
            public void run() {
                stop();
            }
        });
        window.setOnHistoryListener(new Runnable() {
            @Override
            public void run() {
                HistoryWindow historyWindow = new HistoryWindow(history.getHistory(), window.getWindow());
                historyWindow.setCallback(new HistoryCallback() {
                    @Override
                    public void picked(String content) {
                        if(content != null) {
                            fields[0] = content;
                            clipboard.setClipboardContent(content);
                            window.setCurrent(content);
                        }
                    }
                });
            }
        });

        clipboard = new Clipboard();
        clipboard.setContentChangeListener(clipboardListener);
        String current = clipboard.getClipboardContent();
        if(current == null) {
            current = "";
        }
        fields[0] = current;
        window.setCurrent(current);
        clipboard.registerForListening();

        keyboard = new KeyboardListener();
        keyboard.setCallback(keyboardCallback);
        keyboard.register();

        window.show();
    }

    /**
     * Callback for a changed content.
     *
     * @param content The new content.
     */
    private static void contentChanged(String content) {
        if(content == null) {
            content = "";
        }
        fields[0] = content;
        window.setCurrent(content);
        if(content.length() > 0) {
            history.newContent(content);
        }
    }

    /**
     * Stops the application.
     */
    private static void stop() {
        keyboard.stop();
        System.exit(0);
    }

    /**
     * Callback for get button pressed.
     *
     * @param index The index of the key.
     */
    private static void get(int index) {
        String text = fields[index];
        if(text != null) {
            fields[0] = text;
            clipboard.setClipboardContent(text);
            window.setCurrent(text);
        }
    }

    /**
     * Callback for set button pressed.
     *
     * @param index The index of the key.
     */
    private static void set(int index) {
        String text = fields[index] = fields[0];
        switch (index) {
            case 1:
                window.setField1(text);
                break;
            case 2:
                window.setField2(text);
                break;
            case 3:
                window.setField3(text);
                break;
            case 4:
                window.setField4(text);
                break;
            default:
                break;
        }
    }
}
