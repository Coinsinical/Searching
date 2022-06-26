package com.coinsinic.searching.view;

import com.coinsinic.searching.model.SortData;
import com.coinsinic.searching.util.DrawUtils;

import javax.swing.*;
import java.awt.*;

public class SelectionSortFrame extends SortFrame {
    private int canvasWidth;
    private int canvasHeight;
    private String[] columnNames = {"当前已排序完成元素总数","正在比较元素序号","正在比较元素数值","当前最小元素序号","当前最小元素数值"};
    private Object[][] tableData = new Object [1][5];

    //private JButton startButton = new JButton("开始");
    public SelectionSortFrame(String title, int canvasWidth, int canvasHeight, SortData data){
        super(title);
        setLayout(new BorderLayout());

        JPanel northpanel = new JPanel(new GridLayout(0,1));
        northpanel.setPreferredSize(new Dimension(200,40));

        //表格
        this.data=data;
        setDataTableData(data);
        JTable dataTable = new JTable(tableData,columnNames);
        dataTable.setRowHeight(15);
        dataTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        JScrollPane scrollTablePane = new JScrollPane(dataTable);
        northpanel.add(scrollTablePane);

        // 画布
        SortCanvas sortCanvas = new SortCanvas();
        add(sortCanvas,BorderLayout.CENTER);
        add(northpanel,BorderLayout.NORTH);
        //setContentPane(sortCanvas);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        //setSize(canvasWidth,canvasHeight);  // 设置窗口大小
        setSize(800,800);
        setResizable(true);  // 窗口可进行缩放
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();  // 窗口大小自适应
        setVisible(true);  // 窗口可见
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
    public void setDataTableData(SortData data){
        this.data=data;
        tableData[0][0]=data.completedIndex;
        tableData[0][1]=data.currentIndex+1;
        tableData[0][2]=data.arrays[data.currentIndex];
        tableData[0][3]=data.minIndex+1;
        tableData[0][4]=data.arrays[data.minIndex];
    }

    private class SortCanvas extends JPanel{
/*
        public SortCanvas(){
            super(true);   // 允许双缓存
            setBackground(Color.BLUE);
        }
 */

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
                DrawUtils.filledRectangle(graphics2D,i*w,canvasHeight-80-data.arrays[i],w-1,data.arrays[i]);
            }
        }
    }
    @Override
    public Dimension getPreferredSize(){
        return  new Dimension(canvasWidth,canvasHeight);
    }
}
