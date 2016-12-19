package com.iwaimai.qa.waterfall.service;

import com.iwaimai.qa.waterfall.util.PropertiesUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import java.util.Properties;

/**
 *
 * Created by zhangailing on 2016/12/17.
 */
public class SqlImportAndExport {

    private static final String PROPERTY_FILE = "jdbc.properties";


    public static void sqlExport(String db) throws IOException {


        Reader reader = Resources.getResourceAsReader("mybaits-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader, "online");
        SqlSession sqlSession = ssf.openSession();


        Runtime runtime = Runtime.getRuntime();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mysqldump -h").append("127.0.0.1")
                .append(" -u").append("root")
                .append(" -p").append("root")
                .append(" --single-transaction")
                .append(" -P").append(" 3306")
                .append(" test").append(" user")
                .append(" --skip-add-locks --skip-lock-tables")
                .append(" -r").append(" dbName.sql");
        String command = stringBuilder.toString();
        runtime.exec(command);

        sqlSession.close();
    }

    public static void sqlImport(String ip, String port, String username,
                                String password, String db) throws IOException {

        String url = "jdbc:mysql:\\" + ip + ":" + port + "/" + db;
        PropertiesUtil.writeData(PROPERTY_FILE, "local.jdbc.url", url);
        PropertiesUtil.writeData(PROPERTY_FILE, "local.jdbc.username", username);
        PropertiesUtil.writeData(PROPERTY_FILE, "local.jdbc.password", password);


        // for test
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(PROPERTY_FILE);
        properties.load(fis);
        String localURL = properties.getProperty("local.jdbc.url");
        System.out.println("localURL in properties：" + localURL);


        Reader reader1 = Resources.getResourceAsReader("mybaits-config.xml");
        SqlSessionFactory ssf1 = new SqlSessionFactoryBuilder().build(reader1, "local");
        SqlSession sqlSession1 = ssf1.openSession();

        Runtime runtime = Runtime.getRuntime();
        String loginCommand = new StringBuffer().append("mysql -h").append(ip)
                .append(" -u").append(username)
                .append(" -p").append(password)
                .append(" -P").append(port).toString();

        String switchCommand = new StringBuffer("use").append(db).toString();
        String importCommand = new StringBuffer("source").append(" dbName.sql").toString();

        String [] arryCommand = {loginCommand, switchCommand, importCommand};

        Process process = runtime.exec(arryCommand[0]);

        OutputStream outputStream = process.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        writer.write(arryCommand[1] + "\r\n" + arryCommand[2]);
        writer.flush();
        writer.close();
        outputStream.close();


        sqlSession1.close();
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
