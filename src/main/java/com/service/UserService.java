package com.service;

import com.model.entity.User;

import java.util.List;

public interface UserService {
    public int insertUser(String phoneNumber,String password,String userName,String address,String IP);
    public User selectUserByUserName(String username, String password);
    public int selectUserByUserName1(String username, String password);
    List<User> getAllUsers();
}

