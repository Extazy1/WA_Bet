package com.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long id;               //主键，自增
    private String phoneNumber;    //手机号
    private String password;       //密码
    private String userName;       //姓名
    private String address;        //地址
    private String IP;             //IP地址
    private long point;             //票数
    private long authority;         //权限
}
