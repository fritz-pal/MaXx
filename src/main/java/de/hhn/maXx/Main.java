package de.hhn.maXx;

import de.hhn.maXx.view.MaXxWindow;

public class Main {
    public static MaXxWindow WINDOW;

    public static void main(String[] args) {
        WINDOW = new MaXxWindow();
        WINDOW.setVisible(true);
    }
}
