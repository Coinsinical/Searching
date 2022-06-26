package com.coinsinic.searching.service;

import com.coinsinic.searching.model.SelectionSortData;
import com.coinsinic.searching.view.SortFrame;

public abstract class SortProgress extends Thread{
    private final SelectionSortData data;
    private final SortFrame sortFrame; // 视图
    private final int sceneWidth = 800;
    private final int sceneHeight = 400;

    public SortProgress(String title,SelectionSortData data) {
        // 初始化数据
        this.data = data;
        // 初始化视图
        sortFrame = new SortFrame(title, sceneWidth, sceneHeight,data);
    }

    public abstract void run();

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

    public SortFrame getSortFrame() {
        return sortFrame;
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
