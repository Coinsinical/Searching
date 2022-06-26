package com.coinsinic.searching.model;

public class SortData {
    private int[] number;
    public int orderedIndex = -1;   // 在[0,orderedIndex]内是有序的
    public int currentCompareIndex = -1;  // 当前正在比较的元素下标
    public int currentMinIndex = -1;  // 当前找到的最小值的元素下标
    public SortData(int randomBound,int N){
        if(randomBound <=0 || N<=0)
            throw new IllegalArgumentException("SceneHeight or N must larger than 0 ");
        number = new int[N];
        // 生成随机数组
        for(int i=0;i<N;i++){
            number[i] = (int)(Math.random()*randomBound) + 1;
        }
    }
    // 获得数组指定下标的元素
    public int getNumber(int i){
        if ( i < 0 || i >= number.length )
            throw new IllegalArgumentException("out of index ");
        return number[i];
    }
    // 获得数组长度
    public int getN() {
        return number.length;
    }
    // 元素交换
    public void swap(int i,int j){
        int temp = number[i];
        number[i] = number[j];
        number[j] = temp;
    }
}

