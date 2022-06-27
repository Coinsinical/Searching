package com.coinsinic.searching.service;

import com.coinsinic.searching.model.SortData;

public class InsertionSortProgress extends SortProgress{
    public InsertionSortProgress(int i, SortData data) {
        super("插入排序",i,data);
    }

    @Override
    public void run() {
        SortData data = getData();
        draw();
        for(int i = 1; i < data.arrays.length; i++) {
            data.completedIndex=i-1;
            draw();
            for (int j=i;j>0;j--){
                //判断该元素是否比前一元素小，是否需要继续向前移动
                if (data.arrays[j]<data.arrays[j-1]){
                    data.swap(j,j-1);//如果比前一元素小，交换元素
                    data.currentIndex = j-1;
                    data.isMove=true;
                    draw();
                }
                else{
                    //更新数据
                    data.completedIndex=j;
                    data.currentIndex=i;
                    data.isMove=false;
                    draw();
                    break;
                }
            }
        }
        //更新变量
        data.completedIndex=data.arrays.length-1;
        data.currentIndex= data.arrays.length-1;
        draw();
    }
}

