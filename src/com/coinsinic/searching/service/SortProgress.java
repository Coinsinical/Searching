package com.coinsinic.searching.service;

import com.coinsinic.searching.model.SelectionSortData;
import com.coinsinic.searching.view.SortFrame;

public class SortProgress extends Thread{
    private final SelectionSortData data;
    private final SortFrame sortFrame; // 视图
    public SortProgress(int sceneWidth,int sceneHeight,SelectionSortData data) {
        // 初始化数据
        this.data = data;
        // 初始化视图
        sortFrame = new SortFrame("选择排序", sceneWidth, sceneHeight,data);
    }

    public void run() {
        for(int i = 0; i< data.arrays.length; i++){
            draw();
            data.minIndex =i;
            System.out.println(i);
            for (int j = i+1; j< data.arrays.length; j++){
                data.currentIndex =j;
                if (data.arrays[j]< data.arrays[data.minIndex]){
                    data.minIndex =j;
                }
                draw();
            }
            data.swap(i,data.minIndex);
            data.completedIndex =i;
        }
        draw();
    }

    public void draw(){
        sortFrame.render(data);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public SelectionSortData getData() {
        return data;
    }

    /*
    public static void main(int N) {
        SortProgress[] progress = new SortProgress[N];
        for (int i =0;i<progress.length;i++){
            progress[i]=new SortProgress(800,400,(int)(Math.random()*20+10));
            progress[i].start();
        }
        /*
        for (int i =0;i<progress.length;i++){
            try {
                progress[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //progress[0].sortFrame.dispose();
    }
    */
}
