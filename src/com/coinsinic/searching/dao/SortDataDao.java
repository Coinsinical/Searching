package com.coinsinic.searching.dao;

import com.coinsinic.searching.model.SortData;

import java.io.*;

public class SortDataDao {
    private static final File file=new File("./random.txt");

    public static int parseData(SortData[] data) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("./random.txt")));
        String temp;
        int mode=-1;
        if ((temp = br.readLine()) != null) {
            System.out.println(temp);
            br.close();
            String tempArr[] = temp.split(";");
            mode = Integer.parseInt(tempArr[0]);
            int groupnumber = Integer.parseInt(tempArr[1]);
            data = new SortData[groupnumber];
            for (int i = 0; i < tempArr.length - 2; i++) {
                String[] strings = tempArr[i + 2].split(" ");
                int[] ints = new int[strings.length];
                for (int j = 0; j < ints.length; j++) {
                    ints[j] = Integer.parseInt(strings[j]);
                }
                data[i] = new SortData(ints);
            }
        }
        return mode;
    }

    public static String saveData(int[][] arr,int mode,int groupnum) {
        String record = "" + mode + ";" + groupnum + ";";
        String temp = "";
        for (int i = 0; i < arr.length; i++) {
            temp += "第" + (i + 1) + "组数据为：";
            for (int j = 0; j < arr[i].length; j++) {
                temp += arr[i][j] + " ";
                record += arr[i][j] + " ";
            }
            temp += "\n";
            record += ";";
        }
        if (file.canRead()) {
            file.delete();
        }

        try {
            if (file.createNewFile()) {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                writer.write(record);
                writer.close();
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
        return temp;
    }
}
