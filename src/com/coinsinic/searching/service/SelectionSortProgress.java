package com.coinsinic.searching.service;

import com.coinsinic.searching.model.SelectionSortData;

public class SelectionSortProgress extends SortProgress{
    public SelectionSortProgress(int i,SelectionSortData data) {
        super("选择排序",i,data);
    }

    public void run() {
        SelectionSortData data = getData();
        for(int i = 0; i< data.arrays.length; i++){
            draw();
            data.minIndex =i;
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
}
