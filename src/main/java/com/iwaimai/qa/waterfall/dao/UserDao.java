package com.iwaimai.qa.waterfall.dao;

import com.iwaimai.qa.waterfall.entity.User;

import java.util.List;

/**
 * Created by zhangailing on 2016/12/16.
 */
public interface UserDao {

    int countAll();
    User findUser(int userId);
    List<User> findAllUser();
    int insertUser(User user);
    int deleteUser(int userId);
    int updateUser(User user);

}
