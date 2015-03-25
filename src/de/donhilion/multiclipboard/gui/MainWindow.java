package de.donhilion.multiclipboard.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JPanel panel;
    private JTextField fieldCurrent;

    private JFrame window;
    private Runnable onCloseListener;
    private Runnable onHistoryListener;

    JCheckBoxMenuItem alwaysOnTop;

    public MainWindow(boolean inTaskbar) {
        window = new JFrame();
        if(!inTaskbar) {
            window.setType(JFrame.Type.UTILITY);
        }
        window.add(panel);
        window.setTitle("Multi Clipboard");
        window.setAlwaysOnTop(true);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);

                if (onCloseListener != null) {
                    onCloseListener.run();
                }
            }
        });

        // add menu
        JMenuBar menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        JMenu settings = new JMenu("Settings");
        settings.setMnemonic('S');
        menuBar.add(settings);

        alwaysOnTop = new JCheckBoxMenuItem("alwaysOnTop");
        alwaysOnTop.setMnemonic('a');
        alwaysOnTop.setState(true);
        alwaysOnTop.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                window.setAlwaysOnTop(alwaysOnTop.getState());
            }
        });
        settings.add(alwaysOnTop);

        JMenuItem history = new JMenuItem("History");
        history.setMnemonic('H');
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (onHistoryListener != null) {
                    onHistoryListener.run();
                }
            }
        });
        menuBar.add(history);

        window.pack();

        // set icon
        ImageIcon img = new ImageIcon(this.getClass().getResource("/logo.png"));
        window.setIconImage(img.getImage());
    }

    /**
     * Displays the window.
     */
    public void show() {
        window.setState(JFrame.NORMAL);
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
     * Sets the listener which is called when the history button is pressed.
     *
     * @param onHistoryListener The listener
     */
    public void setOnHistoryListener(Runnable onHistoryListener) {
        this.onHistoryListener = onHistoryListener;
    }

    /**
     * Sets the text of current.
     *
     * @param text The text to set.
     */
    public void setCurrent(String text) {
        fieldCurrent.setText(text);
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
     * Returns the JFrame instance.
     *
     * @return The JFrame
     */
    public JFrame getWindow() {
        return window;
    }
}
