package com.coinsinic.searching.service;

import com.coinsinic.searching.model.SelectionSortData;

public class InsertionSortProgress extends SortProgress{
    public InsertionSortProgress(SelectionSortData data) {
        super("插入排序",data);
    }

    @Override
    public void run() {
        SelectionSortData data = getData();
        draw();
        //统计在圈内的点数
        for(int i = 1; i < data.arrays.length; i++) {
            data.completedIndex=i-1;
            draw();
            for (int j=i;j>0;j--){
                if (data.arrays[j]<data.arrays[j-1]){
                    data.swap(j,j-1);
                    data.currentIndex = j-1;
                    data.isMove=true;
                    draw();
                }
                else{
                    data.completedIndex=j;
                    data.currentIndex=i;
                    data.isMove=false;
                    draw();
                    break;
                }
            }
        }
        data.completedIndex=data.arrays.length-1;
        data.currentIndex= data.arrays.length-1;
        draw();
    }
}

