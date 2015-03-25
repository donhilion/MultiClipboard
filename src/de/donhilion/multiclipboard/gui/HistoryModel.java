package de.donhilion.multiclipboard.gui;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.List;

/**
 * Created by Benn on 22.03.2015.
 *
 * Model for formatting the history content.
 */
public class HistoryModel implements ListModel {
    List<String> list;

    public HistoryModel(List<String> list) {
        this.list = list;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public Object getElementAt(int index) {
        return String.format("%d: %s", index+1, list.get(index));
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        // data does not change
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        // data does not change
    }
}
