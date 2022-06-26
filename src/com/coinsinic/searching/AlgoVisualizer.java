package com.coinsinic.searching;

import java.awt.*;

public class AlgoVisualizer {
    private InsertionSortData data;        // 数据
    private AlgoFrame frame;    // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int n){

        // 初始化待排序数据
        data = new InsertionSortData(n, 500);


        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("InsertionSor好t", sceneWidth, sceneHeight, data);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    // 动画逻辑
    private void run(){
        setData(-1, -1);
        //统计在圈内的点数
        for(int i = 1; i < data.size(); i++) {
            int temp = data.get(i);
            int j = i;
            setData(i - 1, j - 1);
            while(j > 0 && temp < data.get(j - 1)) {
                data.set(j, data.get(j - 1));
                j--;
                setData(i - 1, j - 1);
            }
            data.set(j, temp);
            setData(i, j);
        }
        setData(data.size() - 1, -1);
    }

    private void setData(int orderedIndex, int currentIndex) {
        data.setOrderedIndex(orderedIndex);
        data.setCurrentIndex(currentIndex);

        frame.render(data);
        AlgoVisHelper.pause(10);
    }

    public static void main(String[] args) {

        int sceneWidth = 806;
        int sceneHeight = 600;
        int n = 100;

        // TODO: 根据需要设置其他参数，初始化visualizer
        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, n);
    }
}
