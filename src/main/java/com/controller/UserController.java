package com.controller;

import com.model.entity.User;
import com.service.UserService;
import com.util.IpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户控制器")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册", notes = "用户进行注册")
    @PostMapping("/register")
    public Map<String, Object> register(
            @ApiParam(value = "用户名", required = true) @RequestParam String userName,
            @ApiParam(value = "密码", required = true) @RequestParam String password,
            @ApiParam(value = "电话号码", required = true) @RequestParam String phoneNumber,
            @ApiParam(value = "地址", required = true) @RequestParam String address,
            HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        String IP = IpUtil.getIpAddr(request);
        int flag = userService.insertUser(phoneNumber, password, userName, address, IP);
        switch (flag) {
            case 0:
                result.put("msg", "用户名或手机号已存在");
                result.put("code", 0);
                result.put("data", data);
                break;
            case 1:
                result.put("msg", "IP限制注册");
                result.put("code", 0);
                result.put("data", data);
                break;
            case 2:
                result.put("msg", "注册成功");
                result.put("code", 1);
                data.put("userName", userName);
                data.put("phoneNumber", phoneNumber);
                data.put("address", address);
                data.put("ip", IP);
                result.put("data", data);
                break;
        }
        return result;
    }

    @ApiOperation(value = "用户登录", notes = "用户进行登录")
    @PostMapping("/login")
    public Map<String, Object> login(
            @ApiParam(value = "用户名", required = true) @RequestParam String userName,
            @ApiParam(value = "密码", required = true) @RequestParam String password,
            HttpServletRequest request) {
        User user = userService.selectUserByUserName(userName, password);
        int flag = userService.selectUserByUserName1(userName, password);
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        switch (flag) {
            case 0:
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                result.put("code", 0);
                result.put("msg", "登录成功");
                data.put("id", user.getId());
                data.put("name", user.getUserName());
                data.put("phone", user.getPhoneNumber());
                data.put("address", user.getAddress());
                data.put("ip", IpUtil.getIpAddr(request));
                result.put("data", data);
                break;
            case 1:
                result.put("code", 0);
                result.put("msg", "用户未注册");
                result.put("data", data);
                break;
            default:
                result.put("code", 0);
                result.put("msg", "密码错误");
                result.put("data", data);
                break;
        }
        return result;
    }
}
