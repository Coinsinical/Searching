package com.coinsinic.searching.view;

import com.coinsinic.searching.model.SortData;
import com.coinsinic.searching.util.DrawUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class SortFrame extends JFrame {
    private int canvasWidth;
    private int canvasHeight;

    //private JButton startButton = new JButton("开始");
    public SortFrame(String title){
        super(title);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//取消主动操作
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();//点击关闭按钮后关闭当前窗口
            }
        });
    }

    // 数据内容渲染
    private SortData data;
    public void render(SortData data){
        this.data = data;
        setDataTableData(data);
        repaint();
    }

    //框架设计
    //表格数据可视化
    //表格数据更新
    public abstract void setDataTableData(SortData data);


    private class SortCanvas extends JPanel{
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D)g;
            // 抗锯齿
            RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.addRenderingHints(renderingHints);

            int w = canvasWidth / data.arrays.length;  // 矩形宽度
            for(int i=0;i<data.arrays.length;i++){
                if(i < data.completedIndex)   // 已有序的数组元素
                    DrawUtils.setColor(graphics2D,Color.RED);
                else   // 无序的数组元素
                    DrawUtils.setColor(graphics2D,Color.GRAY);
                if(i == data.currentIndex)  // 正在进行比较的元素
                    DrawUtils.setColor(graphics2D,Color.BLUE);
                if(i == data.minIndex)  // 当前找到的最小值的元素
                    DrawUtils.setColor(graphics2D,Color.MAGENTA);
                // 绘制矩形
                DrawUtils.filledRectangle(graphics2D,i*w,canvasHeight-data.arrays[i],w-1,data.arrays[i]);
            }
        }
        @Override
        public Dimension getPreferredSize(){
            return  new Dimension(canvasWidth,canvasHeight);
        }

    }

}
