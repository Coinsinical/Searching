package com.coinsinic.searching.util;

import java.awt.*;

public class Screensize {
    public static Dimension ScreenSize(int a, int b){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.setSize(screenSize.getWidth()/a,screenSize.getHeight()/b);
        return screenSize;
    }
}
