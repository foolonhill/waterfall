package com.iwaimai.qa.waterfall.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;

/**
 * Created by zhangailing on 2016/12/17.
 */
public class MySqlImportAndExport {

    public static void main(String[] args) throws IOException {
        Reader reader = Resources.getResourceAsReader("mybaits-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader, "online");
        SqlSession sqlSession = ssf.openSession();

        sqlExport();
        sqlSession.close();
        reader.close();

        Reader reader1 = Resources.getResourceAsReader("mybaits-config.xml");
        SqlSessionFactory ssf1 = new SqlSessionFactoryBuilder().build(reader1, "local");
        SqlSession sqlSession1 = ssf.openSession();

        sqlImport();
        sqlSession1.close();
        reader1.close();

        boolean ret = deleteFile("E:\\workbench\\waterfall\\dbName.sql");
        System.out.println("========ret========="+ret);
    }

    public static void sqlExport() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("mysqldump -h").append("127.0.0.1")
                .append(" -u").append("root")
                .append(" -p").append("root")
                .append(" --single-transaction")
                .append(" -P").append(" 3306")
                .append(" test").append(" user")
                .append(" --skip-add-locks --skip-lock-tables")
                .append(" -r").append(" dbName.sql");
        String command = stringBuffer.toString();
        runtime.exec(command);
    }

    public static void sqlImport() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        String loginCommand = new StringBuffer().append("mysql -h").append("127.0.0.1")
                .append(" -u").append("root")
                .append(" -p").append("root")
                .append(" -P").append(" 3306").toString();

        String switchCommand = new StringBuffer("use").append(" test1").toString();
        String importCommand = new StringBuffer("source").append(" dbName.sql").toString();

        String [] arryCommand = {loginCommand, switchCommand, importCommand};

        Process process = runtime.exec(arryCommand[0]);

        OutputStream outputStream = process.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        writer.write(arryCommand[1] + "\r\n" + arryCommand[2]);
        writer.flush();
        writer.close();
        outputStream.close();
    }

    public static boolean deleteFile(String path) {
        boolean ret = false;
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            ret = file.delete();
        }

        return ret;
    }
}
