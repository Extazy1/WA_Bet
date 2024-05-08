package com.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import com.model.entity.User;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    //添加新用户
    public void insertUser(String phoneNumber,String password,String userName,String address,String IP);
    //根据手机号查找用户
    public User selectUserByPhoneNumber(String phoneNumber);
    //根据用户名查找用户
    public User selectUserByUserName(String userName);
    //根据IP查找用户
    public User selectUserByIP(String IP);

    @Select("SELECT * FROM user")
    List<User> selectAllUsers();

    @Select("SELECT * FROM user WHERE id = #{userId}")
    User selectUserById(@Param("userId") Integer userId);

    @Update("UPDATE user SET username = #{user.userName}, phoneNumber = #{user.phoneNumber}, address = #{user.address}, authority = #{user.authority}, point = #{user.point} WHERE id = #{user.id}")
    void updateUser(@Param("user") User user);

}
