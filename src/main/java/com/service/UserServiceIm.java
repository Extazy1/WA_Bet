package com.service;

import com.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.entity.User;

import java.util.List;

@Service
public class UserServiceIm implements UserService {
    @Autowired
    UserMapper userDao;
    //添加新用户
    @Override
    public int insertUser(String phoneNumber,String password,String userName,String address,String IP) {
        User user1=userDao.selectUserByUserName(userName);
        User user2=userDao.selectUserByPhoneNumber(phoneNumber);
        //User user3=userDao.selectUserByIP(IP);
        if (user1!=null) {
            //用户名已存在
            return 0;
        }
        if (user2!=null) {
            //手机号码已存在
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
        else if ((user.getAuthority() != 0) && (user.getAuthority() != 1)){
            return 3; // 用户被封禁
        }
        else{
            return 0; //登录成功
        }
    }

    // 获取所有用户信息
    @Override
    public List<User> getAllUsers() {
        return userDao.selectAllUsers();
    }

    @Override
    public boolean banUserById(Integer userId) {
        User user = userDao.selectUserById(userId);
        if (user != null) {
            user.setAuthority(2); // 将用户权限设置为 2 表示封禁
            userDao.updateUser(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean freeUserById(Integer userId) {
        User user = userDao.selectUserById(userId);
        if (user != null) {
            user.setAuthority(0); // 将用户权限设置为 0 表示解封
            userDao.updateUser(user);
            return true;
        }
        return false;
    }
}
