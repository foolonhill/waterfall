package com.iwaimai.qa.waterfall.controller;

import com.alibaba.fastjson.JSON;
import com.iwaimai.qa.waterfall.entity.DbInfo;
import com.iwaimai.qa.waterfall.entity.Response;
import com.iwaimai.qa.waterfall.service.SqlImportAndExport;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * main controller，restful接口
 * Created by iWM on 2016/12/20.
 */

@RestController
public class StreamController {

    @RequestMapping(value = "/stream", method = RequestMethod.POST)
    public String importAndExport(@RequestBody DbInfo dbInfo) {

        String srcDb = dbInfo.getSrcDb();
        System.out.println("SrcDB: " + srcDb);

        String destIp = dbInfo.getDestIp();
        String destPort = dbInfo.getDestPort();
        String destUser = dbInfo.getDestUser();
        String destPassword = dbInfo.getDestPassword();
        String destDb = dbInfo.getDestDb();

        System.out.println("Dest ip: " + destIp + ", port: " + destPort
                + ", user: " + destUser + ", pwd: " + destPassword + ", db: " + destDb);

        // 从源数据库导出数据
        try {
            SqlImportAndExport.sqlExport(srcDb);
        } catch (IOException e) {
            e.printStackTrace();
            return Response.response(Response.Status.FAILURE, "从源数据库导出数据出错！");
        }

        // 导入到目的数据库
        try {
            SqlImportAndExport.sqlImport(destIp, destPort, destUser, destPassword, destDb);
        } catch (IOException e) {
            e.printStackTrace();
            return Response.response(Response.Status.FAILURE, "导入到目的数据库出错！");
        }

        return Response.response(Response.Status.SUCCESS, "数据导入到目的数据库成功！！！");
    }

    @RequestMapping(value = "/few", method = RequestMethod.GET)
    public String few() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", "Failure");

        return JSON.toJSONString(result);
    }


}
