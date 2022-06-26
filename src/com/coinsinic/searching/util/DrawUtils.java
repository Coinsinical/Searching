package com.coinsinic.searching.util;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DrawUtils {
    // 设置颜色
    public static void setColor(Graphics2D g, Color color){
        g.setColor(color);
    }

    // 绘制实心矩形
    public static void filledRectangle(Graphics2D g,int x,int y,int w,int h){
        Rectangle2D rectangle = new Rectangle2D.Double(x,y,w,h);
        g.fill(rectangle);
    }
}
