package de.donhilion.multiclipboard.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.List;

/**
 * Created by Benn on 22.03.2015.
 *
 * View displaying the history.
 */
public class HistoryWindow {
    private JList historyList;
    private JPanel panel;
    private JButton selectButton;
    private JButton cancelButton;
    private JScrollPane scrollPane;
    private JDialog dialog;

    private List<String> history;
    private HistoryCallback callback;
    private int selected = -1;

    public HistoryWindow(final List<String> history, JFrame parent) {
        this.history = history;
        //noinspection unchecked
        historyList.setModel(new HistoryModel(history));
        historyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        historyList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selected = e.getFirstIndex();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                if(callback != null) {
                    callback.picked(null);
                }
            }
        });
        cancelButton.setMnemonic('c');
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
                if (callback != null) {
                    if (selected >= 0 && selected < HistoryWindow.this.history.size()) {
                        callback.picked(HistoryWindow.this.history.get(selected));
                    } else {
                        callback.picked(null);
                    }
                }
            }
        });
        selectButton.setMnemonic('s');

        dialog = new JDialog(parent);
        dialog.add(panel);
        dialog.pack();
        dialog.setVisible(true);

        KeyListener keyListener = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);

                char key = e.getKeyChar();
                if (key >= '0' && key <= '9') {
                    int index = key - 49;
                    if (index < 0) {
                        index = 9;
                    }

                    if (index < history.size()) {
                        selected = index;
                        historyList.setSelectedIndex(index);
                    }
                } else {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER || key == '\n') {
                        dialog.setVisible(false);
                        if (callback != null) {
                            if (selected >= 0 && selected < HistoryWindow.this.history.size()) {
                                callback.picked(HistoryWindow.this.history.get(selected));
                            } else {
                                callback.picked(null);
                            }
                        }
                    }
                }
            }
        };
        historyList.addKeyListener(keyListener);
        selectButton.addKeyListener(keyListener);
        cancelButton.addKeyListener(keyListener);
        panel.addKeyListener(keyListener);
        dialog.addKeyListener(keyListener);
    }

    /**
     * Sets the callback when a content was picked.
     *
     * @param callback The callback.
     */
    public void setCallback(HistoryCallback callback) {
        this.callback = callback;
    }
}
