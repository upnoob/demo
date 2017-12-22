package cn.cust.behavior;

import java.io.*;
/**
 * 处理日志。
 * Created by upnoob on 2017/12/22.
 */
public class ProcessLogs {
    public static void main(String[] args) throws Exception{
//        preProcess("d:/jyxm/logs");
        combineAllBehavior("d:/jyxm/logs2/");
    }

    /**
     * 预处理日志文件夹，将文件夹下所有文件中的冗余数据剔除
     * @param path
     */
    public static void preProcess(String path) throws Exception {
        File dir = new File(path);   //日志目录
        File[] files = dir.listFiles();  //获取目录下所有日志文件

        int num = 0;
        for (int i = files.length; i > 0; i--){
            File file = files[i-1];
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));

            File f = new File("d:/jyxm/logs2/behavior_" + (++num) + ".txt");
            if (!f.exists()){
                f.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
            String line = null;
            while (null != (line = bf.readLine())) {
                if (line.contains("{-*-}")) {
                    bw.write(line);
                    bw.newLine();
                    bw.flush();
                }
            }
            bw.close();
            bf.close();
        }

    }

    /**
     * 融合所有用户行为数据，即将所有预处理完的日志数据整合到一个文件中。
     * @param path
     */
    public static void combineAllBehavior(String path) throws Exception{
        File[] files = new File(path).listFiles();  //提取预处理完的日志目录下的所有日志文件

        String line = null;


        File allbehavior = new File("d:/jyxm/all_behavior.txt");
        if (!allbehavior.exists()){
            allbehavior.createNewFile();
        }

        BufferedWriter bw  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(allbehavior)));
        for (File file: files){
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            while ((line = bf.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
            bf.close();
        }
        bw.close();
    }
}
