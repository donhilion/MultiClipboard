package de.donhilion.multiclipboard.systemtray;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Benn on 19.03.2015.
 *
 * Handler for the tray icon.
 */
public class SystemTrayHandler {
    private SystemTray systemTray;
    private Runnable onClickCallback;

    public SystemTrayHandler() {
        if(SystemTray.isSupported()) {
            systemTray = SystemTray.getSystemTray();
        }
    }

    /**
     * Sets the callback when the icon is clicked.
     *
     * @param callback The callback for the icon.
     */
    public void setOnClickCallback(Runnable callback) {
        this.onClickCallback = callback;
    }

    /**
     * Adds the icon to the system tray.
     *
     * @return true if the icon was added.
     */
    public boolean addIcon() {
        if(systemTray != null) {
            URL url = this.getClass().getResource("/logo.png");

            if (url == null) {
                return false;
            }

            Image img;
            try {
                img = ImageIO.read(url);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            TrayIcon icon = new TrayIcon(img);
            icon.setImageAutoSize(true);
            icon.setToolTip("Multi Clipboard");
            icon.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(onClickCallback != null) {
                        onClickCallback.run();
                    }
                }
            });
            try {
                systemTray.add(icon);
            } catch (AWTException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        return false;
    }
}
