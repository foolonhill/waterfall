package com.iwaimai.qa.waterfall.controller;

import com.iwaimai.qa.waterfall.service.SqlImportAndExport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * test
 * Created by iWM on 2016/9/25.
 */
@Controller
public class HelloController {

    @RequestMapping(value = "/ling")
    public ModelAndView  helloling() {
        ModelAndView modelAndView = new ModelAndView("jsp/hello");
        modelAndView.addObject("name", "yang");
        return modelAndView;
    }

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(Model model, String name){
        System.out.println("From view:" + name);
        model.addAttribute("name", name);
        model.addAttribute("ming", "yang");
        return "jsp/hello";
    }

    @RequestMapping(value = "/hh",method = RequestMethod.GET)
    public String mysqlImportAndExport(Model model, String name){
        return "jsp/hh";
    }

    @RequestMapping(value = "/stream", method = RequestMethod.POST)
    public String mysqlImportAndExport(HttpServletRequest request, Model model) {

        // 源数据库
        String srcTable = request.getParameter("srcTable");

        System.out.println("srcTable: " + srcTable);

        // 目的数据库
        String ip = request.getParameter("ip");
        String port = request.getParameter("port");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String db = request.getParameter("database");

        System.out.println("ip: " + ip + ", port: " + port
                + ", user: " + username + ", pwd: " + password + ", db: " + db);


        // 从源数据库导出数据
        try {
            SqlImportAndExport.sqlExport(srcTable);
        } catch (IOException e) {
            model.addAttribute("status", "从源数据库导出数据出错！");
            e.printStackTrace();
            return "jsp/result";
        }

        // 导入到目的数据库
        try {
            SqlImportAndExport.sqlImport(ip, port, username, password, db);
        } catch (IOException e) {
            model.addAttribute("status", "导入到目的数据库出错！");
            e.printStackTrace();
            return "jsp/result";
        }

        model.addAttribute("status", "数据导入到目的数据库成功！！！");
        return "jsp/result";
    }
}
