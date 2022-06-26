package com.coinsinic.searching.service;

import com.coinsinic.searching.model.SortData;
import com.coinsinic.searching.view.SelectionSortFrame;

public class SortVisualizer {
    private SortData data; // 数据
    private SelectionSortFrame sortFrame; // 视图
    public SortVisualizer(int sceneWidth,int sceneHeight,int N){
        // 初始化数据
        //data = new SortData(sceneHeight,N);
        // 初始化视图
        //sortFrame = new SelectionSortFrame("选择排序",sceneWidth,sceneHeight);
        /*
        EventQueue.invokeLater(()->{
            new Thread(()->{
                run();
            }).start();
        });
         */
    }
    // 动画逻辑
    private void run(){
        // 更新绘制
        setData(0,-1,-1);
        // 选择排序算法
        for(int i = 0;i < data.getN();i++){
            int minIndex = i;
            setData(i,-1,minIndex);
            for(int j = i+1;j < data.getN();j++){
                setData(i,j,minIndex);
                if(data.getNumber(j) < data.getNumber(minIndex))
                    minIndex = j;
                setData(i,j,minIndex);
            }
            data.swap(i,minIndex);
            setData(i+1,-1,-1);
        }
        setData(data.getN(),-1,-1);
    }
    // 实时更新元素状态
    public void setData(int orderedIndex,int currentCompareIndex,int currentMinIndex){
        data.orderedIndex = orderedIndex;
        data.currentCompareIndex = currentCompareIndex;
        data.currentMinIndex = currentMinIndex;
        //sortFrame.render(data);
        //DrawUtils.pause(1000);
    }

    public static void main(String[] args) {
        SortVisualizer s = new SortVisualizer(300,400,20);
        s.run();
    }
}
