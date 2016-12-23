package com.iwaimai.qa.waterfall.entity;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果
 * Created by iWM on 2016/12/21.
 */
public class Response {

    public enum Status {
        SUCCESS(1, "success"),
        FAILURE(0, "failure");

        Status(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        int code;
        String desc;

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }


    public static String response(Status status, String msg){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", status.getCode());
        result.put("msg", msg);

        return JSON.toJSONString(result);
    }

    public static String response(Status status, String msg, Object data){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", status.getCode());
        result.put("msg", msg);
        result.put("data", data);

        return JSON.toJSONString(result);
    }
}
