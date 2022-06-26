package com.coinsinic.searching;

public class InsertionSortData {
    private int[] numbers;

    private int orderedIndex = -1;	//[0...orderIndex]是有序的
    private int currentIndex = -1; 	//当前正在处理的元素索引

    public InsertionSortData(int n, int randomBound) {
        numbers = new int[n];
        for(int i = 0; i < n; i++)
            numbers[i] = (int) (Math.random() * randomBound) + 1;
    }

    public int getOrderedIndex() {
        return orderedIndex;
    }

    public void setOrderedIndex(int orderedIndex) {
        this.orderedIndex = orderedIndex;
    }


    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    //获取待数据的个数
    public int size() {
        return numbers.length;
    }

    public int get(int index) {
        if(index < 0 || index >= numbers.length)
            throw new IllegalArgumentException("访问的数据下标越界，index：" + index);
        return numbers[index];
    }

    public void set(int index, int value) {
        if(index < 0 || index > numbers.length) {
            throw new IllegalArgumentException("插入的数据下标越界，index：" + index);
        }
        numbers[index] = value;
    }

    public void swap(int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
