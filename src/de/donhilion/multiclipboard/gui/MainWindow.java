package de.donhilion.multiclipboard.gui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Benn on 14.03.2015.
 *
 * The main window of the application.
 */
public class MainWindow {
    private JTextField field1;
    private JTextField field2;
    private JTextField field3;
    private JTextField field4;
    private JTextField field5;
    private JTextField field6;
    private JTextField field7;
    private JTextField field8;
    private JTextField field9;
    private JPanel panel;

    private JFrame window;
    private Runnable onCloseListener;

    public MainWindow() {
        window = new JFrame();
        window.add(panel);
        window.pack();
        window.setTitle("Multi Clipboard");

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);

                if(onCloseListener != null) {
                    onCloseListener.run();
                }
            }
        });
    }

    /**
     * Displays the window.
     */
    public void show() {
        window.setVisible(true);
    }

    /**
     * Sets the listener which is called when this window is closed.
     *
     * @param onCloseListener The on-close listener
     */
    public void setOnCloseListener(Runnable onCloseListener) {
        this.onCloseListener = onCloseListener;
    }

    /**
     * Sets the text of field 1.
     * 
     * @param text The text to set.
     */
    public void setField1(String text) {
        field1.setText(text);
    }

    /**
     * Sets the text of field 2.
     *
     * @param text The text to set.
     */
    public void setField2(String text) {
        field2.setText(text);
    }

    /**
     * Sets the text of field 3.
     *
     * @param text The text to set.
     */
    public void setField3(String text) {
        field3.setText(text);
    }

    /**
     * Sets the text of field 4.
     *
     * @param text The text to set.
     */
    public void setField4(String text) {
        field4.setText(text);
    }

    /**
     * Sets the text of field 5.
     *
     * @param text The text to set.
     */
    public void setField5(String text) {
        field5.setText(text);
    }

    /**
     * Sets the text of field 6.
     *
     * @param text The text to set.
     */
    public void setField6(String text) {
        field6.setText(text);
    }

    /**
     * Sets the text of field 7.
     *
     * @param text The text to set.
     */
    public void setField7(String text) {
        field7.setText(text);
    }

    /**
     * Sets the text of field 8.
     *
     * @param text The text to set.
     */
    public void setField8(String text) {
        field8.setText(text);
    }

    /**
     * Sets the text of field 9.
     *
     * @param text The text to set.
     */
    public void setField9(String text) {
        field9.setText(text);
    }

    /**
     * For testing the window.
     *
     * @param args The arguments.
     */
    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        window.setOnCloseListener(new Runnable() {
            @Override
            public void run() {
                System.exit(0);
            }
        });
        window.show();
    }
}
