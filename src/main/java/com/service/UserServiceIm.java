package com.service;

import com.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.entity.User;

@Service
public class UserServiceIm implements UserService {
    @Autowired
    UserDao userDao;
    //添加新用户
    @Override
    public int insertUser(String phoneNumber,String password,String userName,String address,String IP) {
        User user1=userDao.selectUserByUserName(userName);
        User user2=userDao.selectUserByPhoneNumber(phoneNumber);
        User user3=userDao.selectUserByIP(IP);
        if (user1!=null||user2!=null) {
            //用户名或者手机号码已存在
            return 0;
        }
        if (user3!=null) {
            //IP已存在
            return 1;
        }
        //可以正确注册
        else{
            userDao.insertUser(phoneNumber,password,userName,address,IP);
            return 2;
        }
    }

    //根据用户名查找用户
    @Override
    public User selectUserByUserName(String username,String password){
        User user=userDao.selectUserByUserName(username);
        if(user!=null&&user.getPassword().equals(password)){
            return user;
        }
        else{
            return null;
        }
    }

    //根据用户名查找用户
    public int selectUserByUserName1(String username,String password){
        User user=userDao.selectUserByUserName(username);
        if(user==null){
            return 1; //用户名不存在
        }
        else if(!user.getPassword().equals(password)){
            return 2; //密码错误
        }
        else{
            return 0; //登录成功
        }

    }


}
