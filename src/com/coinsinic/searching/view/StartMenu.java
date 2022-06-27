package com.coinsinic.searching.view;

import com.coinsinic.searching.model.SortData;
import com.coinsinic.searching.service.InsertionSortProgress;
import com.coinsinic.searching.service.SelectionSortProgress;
import com.coinsinic.searching.service.SortProgress;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class StartMenu extends JFrame {
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
        ModeLabel.setLocation(50,30);
        ModeComboBox.setSize(200, 20);
        ModeComboBox.setLocation(160,35);
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
            int[][] arr; //定义数据存储数组

            @Override
            public void actionPerformed(ActionEvent e) {
                int mode = ModeComboBox.getSelectedIndex();
                try {
                    int groupNum = Integer.parseInt(groupNumField.getText()); //获取用户输入
                    //判断是否已经生成数据/输入数据是否发生更改
                    if (data == null||groupNum!=data.length) {
                        data = new SortData[groupNum];

                        arr = new int[groupNum][];
                        for (int i = 0; i < data.length; i++) {
                            data[i] = new SortData((int) (Math.random() * 20 + 10), 300);
                            arr[i] = data[i].arrays;
                        }
                        String record = ""+mode+";"+groupNum+";";
                        String temp = "";
                        for (int i = 0; i < arr.length; i++) {
                            temp += "第" + (i + 1) + "组数据为：";
                            for (int j = 0; j < arr[i].length; j++) {
                                temp += arr[i][j] + " ";
                                record += arr[i][j] + " ";
                            }
                            temp += "\n";
                            record +=";";
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
                                writer.write(record);
                                writer.close();
                            }
                        } catch (IOException error) {
                            error.printStackTrace();
                        }
                        //如果已经生成相关数据，则点击按钮进入排序界面
                    } else {
                        ChooseMode(mode);
                    }
                }
                catch(NumberFormatException exception){
                    JOptionPane.showMessageDialog(null, "输入的数组个数为空或不合法", "出错啦！", JOptionPane. ERROR_MESSAGE);
                }
            }
        });


        HistoryButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./random.txt")));
                    String temp;
                    if ((temp = br.readLine()) != null) {
                        System.out.println(temp);
                        br.close();
                        String tempArr[] = temp.split(";");
                        int mode=Integer.parseInt(tempArr[0]);
                        int groupnumber=Integer.parseInt(tempArr[1]);
                        data = new SortData[groupnumber];
                        for (int i=0;i< tempArr.length-2;i++){
                            String[] strings=tempArr[i+2].split(" ");
                            int[] ints=new int[strings.length];
                            for(int j=0;j<ints.length;j++){
                                ints[j]=Integer.parseInt(strings[j]);
                            }
                            data[i]=new SortData(ints);
                        }
                        ChooseMode(mode);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

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
