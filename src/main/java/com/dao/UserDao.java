package com.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.model.entity.User;

@Mapper
@Repository
public interface UserDao {
    //添加新用户
    public void insertUser(String phoneNumber,String password,String userName,String address,String IP);
    //根据手机号查找用户
    public User selectUserByPhoneNumber(String phoneNumber);
    //根据用户名查找用户
    public User selectUserByUserName(String userName);
    //根据IP查找用户
    public User selectUserByIP(String IP);
}
