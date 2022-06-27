package com.coinsinic.searching.view;

import com.coinsinic.searching.dao.SortDataDao;
import com.coinsinic.searching.model.SortData;
import com.coinsinic.searching.service.InsertionSortProgress;
import com.coinsinic.searching.service.SelectionSortProgress;
import com.coinsinic.searching.service.SortProgress;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;

public class StartMenu extends JFrame {
    private int[][] arr; //定义数据存储数组
    private SortData[] data;
    private JPanel jPanel = new JPanel();

    private JLabel ModeLabel = new JLabel("模式");
    private JLabel groupNumAreaLabel = new JLabel("生成数组个数");
    private JLabel randomArraysLabel = new JLabel("随机生成结果");
    private JTextField groupNumField = new JTextField();
    private JTextArea genResultArea = new JTextArea();
    private JComboBox ModeComboBox=new JComboBox();
    private JScrollPane scrollPane = new JScrollPane(genResultArea);

    private JButton okButton = new JButton("确定");
    private JButton cancelButton = new JButton("取消");
    private JButton HistoryButton = new JButton("回放");

    public StartMenu() {
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 窗口居中

        setTitle("排序算法可视化");
        add(jPanel);

        jPanel.setLayout(null);

        //下拉框添加
        ModeLabel.setSize(100, 20);
        ModeLabel.setLocation(50, 30);
        ModeComboBox.setSize(200, 20);
        ModeComboBox.setLocation(160, 35);
        ModeComboBox.setBackground(Color.WHITE);
        ModeComboBox.setForeground(Color.BLACK);
        ModeComboBox.addItem("选择排序");
        ModeComboBox.addItem("插入排序");
        ModeComboBox.addItem("并行模式");

        //数组个数输入模块参数设置
        groupNumField.setSize(200, 20);//输入框大小
        groupNumAreaLabel.setSize(100, 50);//输入标签大小
        groupNumAreaLabel.setLocation(50, 70);//输入标签位置
        groupNumField.setLocation(160, 85);//输入框位置

        //显示生成数组数据模块设置
        genResultArea.setEditable(false);
        genResultArea.setBackground(Color.WHITE);
        randomArraysLabel.setSize(100, 50);
        randomArraysLabel.setLocation(50, 170);
        scrollPane.setSize(200, 150);
        scrollPane.setLocation(160, 130);

        //Jpanel添加
        jPanel.add(groupNumField);
        jPanel.add(groupNumAreaLabel);
        jPanel.add(randomArraysLabel);
        jPanel.add(scrollPane);
        jPanel.add(ModeLabel);
        jPanel.add(ModeComboBox);

        //配置按钮大写
        //HistoryButton.setFont(new Font("微软雅黑",Font.PLAIN,15));
        Dimension buttonSize = new Dimension(80, 50);// 统一按钮大小
        int xp = 50, yp = 300, x_interval = buttonSize.width + 30;//设置初始位置与间隔
        //设置按钮大小
        HistoryButton.setSize(buttonSize);
        okButton.setSize(buttonSize);
        cancelButton.setSize(buttonSize);
        //设置按钮位置
        okButton.setLocation(xp, yp);
        cancelButton.setLocation(xp + x_interval, yp);
        HistoryButton.setLocation(xp + x_interval * 2, yp);
        //面板添加按钮
        jPanel.add(okButton);
        jPanel.add(cancelButton);
        jPanel.add(HistoryButton);

        //设置取消按钮功能
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //设置确认按钮功能
        okButton.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                int mode = ModeComboBox.getSelectedIndex();
                try {
                    int groupNum = Integer.parseInt(groupNumField.getText()); //获取用户输入
                    //判断是否已经生成数据/输入数据是否发生更改
                    if (data == null || groupNum != data.length) {
                        data = new SortData[groupNum];

                        arr = new int[groupNum][];
                        //生成随机数组
                        for (int i = 0; i < data.length; i++) {
                            data[i] = new SortData((int) (Math.random() * 20 + 10), 300);
                            arr[i] = data[i].arrays;
                        }
                        genResultArea.setText(SortDataDao.saveData(arr, mode, groupNum));

                        //如果已经生成相关数据，则点击按钮进入排序界面
                    } else {
                        ChooseMode(mode);
                    }
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "输入的数组个数为空或不合法", "出错啦！", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        HistoryButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ChooseMode(SortDataDao.parseData(data));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        ModeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(!groupNumField.getText().equals(""))
                SortDataDao.saveData(arr,ModeComboBox.getSelectedIndex(),Integer.parseInt(groupNumField.getText()));
            }
        });
    }



    public void ChooseMode(int mode){
        SortProgress[] progresses = new InsertionSortProgress[data.length];
        SortProgress[] progresses1 = new SelectionSortProgress[data.length];
        switch (mode) {
            case 0:
                for (int i = 0; i < data.length; i++) {
                    progresses1[i] = new SelectionSortProgress(i + 1, (SortData) data[i].clone());
                    progresses1[i].start();
                }
                break;
            case 1:
                for (int i = 0; i < data.length; i++) {
                    progresses[i] = new InsertionSortProgress(i + 1, data[i]);
                    progresses[i].start();
                }
                break;
            case 2:
                for (int i = 0; i < data.length; i++) {
                    progresses[i] = new InsertionSortProgress(i + 1, data[i]);
                    progresses1[i] = new SelectionSortProgress(i + 1, (SortData) data[i].clone());
                    progresses1[i].start();
                    progresses[i].start();
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "请选择模式", "错误", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
}
