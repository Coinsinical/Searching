package com.coinsinic.searching.service;

import com.coinsinic.searching.model.SelectionSortData;
import com.coinsinic.searching.view.InsertionSortFrame;
import com.coinsinic.searching.view.SelectionSortFrame;
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
        if (title.equals("选择排序")){
            sortFrame = new SelectionSortFrame(title, sceneWidth, sceneHeight,data);
        }
        else{
            sortFrame = new InsertionSortFrame(title,sceneWidth, sceneHeight,data);
        }

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
}
