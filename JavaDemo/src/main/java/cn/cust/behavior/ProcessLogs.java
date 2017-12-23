package cn.cust.behavior;

import com.mysql.jdbc.PreparedStatement;

import java.io.*;
import java.sql.Connection;

/**
 * 处理日志。
 * Created by upnoob on 2017/12/22.
 */
public class ProcessLogs {
    public static void main(String[] args) throws Exception{
//        preProcess("d:/jyxm/logs");
//        combineAllBehavior("d:/jyxm/logs2/");
//        removeLogsSelfInfo("d:/jyxm/all_behavior.txt");
//        judgeBehavior("d:/jyxm/all_behavior_2.txt");
        saveInMysql("d:/jyxm/all_behavior_2.txt");
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

        BufferedWriter bw  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(allbehavior), "UTF-8"));
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

    /**
     * 将log日志自带信息中除了时间节点的其他无用信息剔除，格式化数据格式
     * @param path all_behavior
     */
    public static void removeLogsSelfInfo(String path) throws Exception{
        File file = new File(path);
        File file2 = new File("d:/jyxm/all_behavior_2.txt");
        if (!file2.exists()) {
            file2.createNewFile();
        }
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        BufferedWriter bw  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2), "UTF-8"));
        String line = null;
        while ((line = bf.readLine()) != null) {
            String[] strs = line.split("  : ");
            String newline = strs[0].substring(0, 23).concat("{-*-}").concat(strs[1]);
            bw.write(newline);
            bw.newLine();
            bw.flush();
        }
        bw.close();
        bf.close();
    }


    /**
     * 将数据文件保存一份在数据库里
     * @param path
     */
    public static void saveInMysql(String path) throws Exception{
        Connection conn = JDBCUtils.connMySQL();
        PreparedStatement ps = null;

        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), "UTF-8"));
        String line = null;
        String sql = "insert into all_behaviors(id, content) values(?, ?)";
        while ((line = bf.readLine()) != null) {
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, null);
            ps.setString(2, line);
            ps.executeUpdate();
        }
        ps.close();
        conn.close();
        bf.close();
    }


    /**
     * 判断每行行为是否合法
     * 该方法只是用来检测每一行的数据格式是否一致，有没有漏掉的某个拦截值
     */
    public static void judgeBehavior(String path) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), "UTF-8"));
        String line = null;
        while ((line = bf.readLine()) != null) {
            String[] strs = line.split("\\{\\-\\*\\-\\}");
            if (strs.length != 23){
                System.out.println(strs.length);
            }
        }
        bf.close();
    }
}
