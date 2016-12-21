package com.iwaimai.qa.waterfall.controller;

import com.alibaba.fastjson.JSON;
import com.iwaimai.qa.waterfall.entity.DbInfo;
import com.iwaimai.qa.waterfall.service.SqlImportAndExport;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * main controller
 * Created by iWM on 2016/12/20.
 */

@RestController
public class StreamController {

    @RequestMapping(value = "/water", method = RequestMethod.POST)
    public String importAndExport(@PathVariable DbInfo dbInfo) {

        String srcDb = dbInfo.getSrcDb();
        System.out.println("SrcDB: " + srcDb);

        String destIp = dbInfo.getDestIp();
        String destPort = dbInfo.getDestPort();
        String destUser = dbInfo.getDestUser();
        String destPassword = dbInfo.getDestPassword();
        String destDb = dbInfo.getDestDb();

        System.out.println("Dest ip: " + destIp + ", port: " + destPort
                + ", user: " + destUser + ", pwd: " + destPassword + ", db: " + destDb);

        Map<String, Object> result = new HashMap<String, Object>();

        // 从源数据库导出数据
        try {
            SqlImportAndExport.sqlExport(srcDb);
        } catch (IOException e) {
            result.put("status", "从源数据库导出数据出错！");
            e.printStackTrace();
            return JSON.toJSONString(result);
        }

        // 导入到目的数据库
        try {
            SqlImportAndExport.sqlImport(destIp, destPort, destUser, destPassword, destDb);
        } catch (IOException e) {
            result.put("status", "导入到目的数据库出错！");
            e.printStackTrace();
            return JSON.toJSONString(result);
        }

        result.put("status", "数据导入到目的数据库成功！！！");
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/few", method = RequestMethod.GET)
    public String few() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "Failure");

        return JSON.toJSONString(result);
    }
}
