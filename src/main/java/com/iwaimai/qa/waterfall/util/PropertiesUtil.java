package com.iwaimai.qa.waterfall.util;

import java.io.*;
import java.util.Properties;

/**
 * 配置文件工具类
 * Created by iWM on 2016/12/18.
 */
public class PropertiesUtil {
    /**
     * 修改键值对
     * @param filePath 文件路径，即文件所在包的路径，例如：java/util/config.properties
     * @param key 键
     * @param value 键对应的值
     */
    public static void writeData(String filePath, String key, String value) {
        //获取绝对路径
        String path = PropertiesUtil.class.getClassLoader().getResource("/" + filePath).getPath();
//        截掉路径的”file:/“前缀
//        filePath = filePath.substring(6);
        Properties prop = new Properties();
        try {
            File file = new File(path);
            if (!file.exists())
                return;
            InputStream fis = new FileInputStream(file);
            prop.load(fis);
            //一定要在修改值之前关闭fis
            fis.close();
            OutputStream fos = new FileOutputStream(path);
            prop.setProperty(key, value);
            //保存，并加入注释
            prop.store(fos, "Update '" + key + "' value");
            fos.close();
        } catch (IOException e) {
            System.err.println("Visit " + filePath + " for updating " + value + " value error");
        }
    }
}
