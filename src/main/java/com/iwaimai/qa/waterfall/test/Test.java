package com.iwaimai.qa.waterfall.test;

import com.iwaimai.qa.waterfall.dao.UserDao;
import com.iwaimai.qa.waterfall.dao.impl.UserDaoImpl;
import com.iwaimai.qa.waterfall.entity.User;

import java.io.IOException;

/**
 * Created by iWM on 2016/12/16.
 */
public class Test {

    public static void main(String[] args) throws IOException {
        UserDao userDao = new UserDaoImpl();
        /*System.out.println(userDao.countAll());
        System.out.println(userDao.findUser(1));
        System.out.println(userDao.findAllUser());*/

        User user = new User(7, "yyyy", "女", 90, "1998", 1006);

        //System.out.println(userDao.insertUser(user));

        System.out.println(userDao.updateUser(user));
    }
}
