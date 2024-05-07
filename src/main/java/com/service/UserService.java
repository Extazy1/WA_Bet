package com.service;

import com.model.entity.User;

public interface UserService {
    public int insertUser(String phoneNumber,String password,String userName,String address,String IP);
    public User selectUserByUserName(String username, String password);
    public int selectUserByUserName1(String username, String password);

}
