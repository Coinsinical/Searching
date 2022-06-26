package com.coinsinic.searching.service;

import com.coinsinic.searching.model.SortData;

public class SelectionSortProgress extends SortProgress{
    public SelectionSortProgress(int i, SortData data) {
        super("选择排序",i,data);
    }

    public void run() {
        SortData data = getData();
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
