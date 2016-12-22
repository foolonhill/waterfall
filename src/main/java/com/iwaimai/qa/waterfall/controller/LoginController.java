package com.iwaimai.qa.waterfall.controller;

import com.alibaba.fastjson.JSONObject;
import com.iwaimai.qa.waterfall.entity.Response;
import com.iwaimai.qa.waterfall.entity.UserLogin;
import java.util.Map;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 登录校验
 * Created by iWM on 2016/12/22.
 */
@RestController
public class LoginController {

    @RequestMapping(value = "loginCheck", method = RequestMethod.POST)
    public String loginCheck(@RequestBody UserLogin user) {
        String userEmail = user.getEmail();
        String password = user.getPassword();

        System.out.println("email: " + userEmail + ", password: " + password);

        if (StringUtils.isEmpty(userEmail) || StringUtils.isEmpty(password)) {
            return Response.response(Response.Status.FAILURE, "user or pwd is empty");
        }

        // other logistic

        return Response.response(Response.Status.SUCCESS, "login success");
    }
}
