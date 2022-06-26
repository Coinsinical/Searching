package com.coinsinic.searching;

import javax.swing.*;
import java.awt.*;

public class AlgoFrame extends JFrame {
    public AlgoFrame(String title, int width, int height, InsertionSortData data){

        super(title);
        this.data = data;

        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public AlgoFrame(String title){

        this(title, 1024, 768, null);
    }

    // 存储坐标（窗口中的位置）
    private InsertionSortData data;
    public void render(InsertionSortData data){
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel{

        public AlgoCanvas(){
            // 双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = getWidth();
            int height = getHeight();

            Graphics2D g2d = (Graphics2D)g;
            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            // 具体绘制
            // TODO： 绘制自己的数据data
            // 在画布中先绘制一个圆
            AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
            double w = width / data.size() * 1.0;
            for(int i = 0; i < data.size(); i++) {
                if(i <= data.getOrderedIndex()) {
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.DeepOrange);
                    if(i == data.getCurrentIndex())
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
                } else {
                    AlgoVisHelper.setColor(g2d, AlgoVisHelper.Grey);
                }

                AlgoVisHelper.fillRectangle(g2d, i * w, height - data.get(i), w - 1, data.get(i));
            }

        }
    }
}
