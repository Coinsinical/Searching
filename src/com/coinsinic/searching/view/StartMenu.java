package com.coinsinic.searching.view;

import com.coinsinic.searching.model.SelectionSortData;
import com.coinsinic.searching.service.SortProgress;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;

public class StartMenu extends JFrame {
    private SelectionSortData[] data;
    private JPanel jPanel = new JPanel();

    private JLabel groupNumAreaLabel = new JLabel("生成数组个数");
    private JLabel randomArraysLabel = new JLabel("随机生成结果");
    private JTextField groupNumField = new JTextField();
    private JTextArea genResultArea = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(genResultArea);

    private JButton okButton = new JButton("确定");
    private JButton cancelButton = new JButton("取消");
    private JButton HistoryButton = new JButton("回放");

    public StartMenu() {
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle("生成随机数");
        add(jPanel);

        jPanel.setLayout(null);


        groupNumField.setSize(200, 20);
        groupNumAreaLabel.setSize(100, 50);
        groupNumAreaLabel.setLocation(50, 50);
        groupNumField.setLocation(160, 65);

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


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        okButton.addActionListener(new ActionListener() {
            int[][] arr;

            @Override
            public void actionPerformed(ActionEvent e) {
                int groupNum = Integer.parseInt(groupNumField.getText());
                if (data == null) {
                    data = new SelectionSortData[groupNum];

                    arr = new int[groupNum][];
                    for (int i = 0; i < data.length; i++) {
                        data[i] = new SelectionSortData((int) (Math.random() * 20 + 5), 400);
                        arr[i] = data[i].arrays;
                    }
                    String temp = "";
                    for (int i = 0; i < arr.length; i++) {
                        temp += "第" + (i + 1) + "组数据为：";
                        for (int j = 0; j < arr[i].length; j++) {
                            temp += arr[i][j] + " ";
                        }
                        temp += "\n";
                    }
                    genResultArea.setText(temp);

                    //储存生成的数组结果
                    File f = new File("./random.txt");
                    //检测文件是否存在（只保留一次结果）
                    if (f.canRead()) {
                        f.delete();
                    }

                    try {
                        if (f.createNewFile()) {
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
                            writer.write(temp);
                            writer.close();
                        }
                    } catch (IOException error) {
                        error.printStackTrace();
                    }
                } else {
                    SortProgress[] progresses = new SortProgress[data.length];
                    for (int i = 0; i < data.length; i++) {
                        progresses[i] = new SortProgress(800, 400, data[i]);
                        progresses[i].start();
                    }
                }
            }
        });


        HistoryButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./save.txt")));
                    String temp;
                    if ((temp = br.readLine()) != null) {
                        br.close();
                        String tempArr[] = temp.split("：");
                        int arr[] = new int[tempArr.length];
                        for (int i = 0; i < arr.length; i++) {
                            arr[i] = Integer.parseInt(tempArr[i]);
                        }
                        //new ChoiceAlgorithm(arr);
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }

    public static void main(String[] args) {
        StartMenu startMenu = new StartMenu();
    }
}
