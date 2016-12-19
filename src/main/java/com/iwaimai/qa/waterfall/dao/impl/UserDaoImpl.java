package com.iwaimai.qa.waterfall.dao.impl;

import com.iwaimai.qa.waterfall.dao.UserDao;
import com.iwaimai.qa.waterfall.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by zhangailing on 2016/12/16.
 */
public class UserDaoImpl implements UserDao{

    private final static String conf = "mybaits-config.xml";

    public int countAll() {
        Reader reader = null;
        SqlSession sqlSession = null;
        int count = 0;
        try {
            // 使用mybatis的Resources类加载。加载mybaits-config.xml文件的同时加载关联的映射文件
            reader = Resources.getResourceAsReader(conf);
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
            // 创建sqlSession，执行mapper.xml中的sql语句
            sqlSession = ssf.openSession();
            // 执行映射文件中的sql(namespace + select的id)
            count = sqlSession.selectOne("com.iwaimai.qa.waterfall.dao.userMapper.countAllUser");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return count;
    }

    public User findUser(int userId) {
        Reader reader = null;
        SqlSession sqlSession = null;
        User user = null;
        try {
            reader = Resources.getResourceAsReader(conf);
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
            sqlSession = ssf.openSession();
            user = sqlSession.selectOne("com.iwaimai.qa.waterfall.dao.userMapper.getUser", userId);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sqlSession != null) {
                sqlSession.close();
            }

        }
        return user;
    }

    public List<User> findAllUser() {
        Reader reader = null;
        SqlSession sqlSession = null;
        List<User> list = null;
        try {
            reader = Resources.getResourceAsReader(conf);
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
            sqlSession = ssf.openSession();
            list = sqlSession.selectList("com.iwaimai.qa.waterfall.dao.userMapper.getAllUser");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sqlSession != null) {
                sqlSession.close();
            }

        }
        return list;
    }

    public int insertUser(User user) {
        Reader reader = null;
        SqlSession sqlSession = null;
        try {
            reader = Resources.getResourceAsReader(conf);
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
            sqlSession = ssf.openSession();
            sqlSession.insert("com.iwaimai.qa.waterfall.dao.userMapper.addUser", user);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return 0;
    }

    public int updateUser(User user) {
        Reader reader = null;
        SqlSession sqlSession = null;
        try {
            reader = Resources.getResourceAsReader(conf);
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
            sqlSession = ssf.openSession();
            sqlSession.insert("com.iwaimai.qa.waterfall.dao.userMapper.updateUser", user);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return 0;
    }

    public int deleteUser(int userId) {
        Reader reader = null;
        SqlSession sqlSession = null;
        try {
            reader = Resources.getResourceAsReader(conf);
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
            sqlSession = ssf.openSession();
            sqlSession.insert("com.iwaimai.qa.waterfall.dao.userMapper.deleteUser", userId);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return 0;
    }

}
