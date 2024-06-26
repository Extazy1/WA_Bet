package com.controller;

import com.annotation.RequiresAuthority;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            @ApiParam(value = "HTTP 请求对象", required = false) HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        String IP = IpUtil.getIpAddr(request);
        int flag = userService.insertUser(phoneNumber, password, userName, address, IP);
        switch (flag) {
            case 0:
                result.put("msg", "用户名已存在");
                result.put("code", 1);
                result.put("data", data);
                break;
            case 1:
                result.put("msg", "手机号已存在");
                result.put("code", 1);
                result.put("data", data);
                break;
            case 2:
                result.put("msg", "注册成功");
                result.put("code", 0);
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
            @ApiParam(value = "HTTP 请求对象", required = false) HttpServletRequest request) {
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
                data.put("authority", user.getAuthority());
                data.put("point", user.getPoint());
                result.put("data", data);
                break;
            case 1:
                result.put("code", 1);
                result.put("msg", "用户未注册");
                result.put("data", data);
                break;
            case 2:
                result.put("code", 1);
                result.put("msg", "密码错误");
                result.put("data", data);
                break;
            default:
                result.put("code", 1);
                result.put("msg", "用户被封禁，请联系管理员");
                result.put("data", data);
                break;
        }
        return result;
    }

    @ApiOperation(value = "获取用户信息", notes = "通过Session获取当前登录用户信息")
    @GetMapping("/info")
    public Map<String, Object> getUserInfo(
            @ApiParam(value = "HTTP 请求对象", required = false) HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        HttpSession session = request.getSession();
        User user1 = (User) session.getAttribute("user");
        Integer userId = (int)user1.getId();

        if (userId != null) {
            User user = userService.getUserById(userId);
            result.put("code", 0);
            result.put("msg", "获取用户信息成功");
            data.put("id", user.getId());
            data.put("name", user.getUserName());
            data.put("phone", user.getPhoneNumber());
            data.put("address", user.getAddress());
            data.put("ip", IpUtil.getIpAddr(request));
            data.put("authority", user.getAuthority());
            data.put("point", user.getPoint());
            result.put("data", data);
        } else {
            result.put("code", 3);
            result.put("msg", "用户未登录或会话已过期");
            result.put("data", data);
        }

        return result;
    }

    @ApiOperation(value = "获取所有用户信息", notes = "获取所有用户信息 需要权限")
    @RequiresAuthority(value = 1) // 需要管理员权限
    @GetMapping("/all")
    public Map<String, Object> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<Map<String, Object>> userDTOs = users.stream()
                .map(user -> {
                    Map<String, Object> userDTO = new HashMap<>();
                    userDTO.put("id", user.getId());
                    userDTO.put("name", user.getUserName());
                    userDTO.put("phone", user.getPhoneNumber());
                    userDTO.put("address", user.getAddress());
                    userDTO.put("authority", user.getAuthority());
                    userDTO.put("point", user.getPoint());
                    return userDTO;
                })
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "查询成功");
        result.put("data", userDTOs);
        return result;
    }

    @ApiOperation(value = "判断用户是否登录", notes = "通过Session判断用户是否登录")
    @GetMapping("/isLoggedIn")
    public Map<String, Object> isLoggedIn(
            @ApiParam(value = "HTTP 请求对象", required = false) HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            result.put("code", 0);
            result.put("msg", "用户已登录");
            result.put("data", true);
        } else {
            result.put("code", 3);
            result.put("msg", "用户未登录或会话已过期");
            result.put("data", false);
        }

        return result;
    }

    @ApiOperation(value = "封禁用户", notes = "管理员封禁用户")
    @RequiresAuthority(value = 1) // 需要管理员权限
    @PostMapping("/ban")
    public Map<String, Object> banUser(
            @ApiParam(value = "用户ID", required = true) @RequestParam Integer userId) {
        Map<String, Object> result = new HashMap<>();
        boolean success = userService.banUserById(userId);

        if (success) {
            result.put("code", 0);
            result.put("msg", "用户已被封禁");
        } else {
            result.put("code", 1);
            result.put("msg", "未找到用户或封禁失败");
        }

        return result;
    }

    @ApiOperation(value = "解除封禁用户", notes = "管理员解除封禁用户")
    @RequiresAuthority(value = 1) // 需要管理员权限
    @PostMapping("/free")
    public Map<String, Object> freeUser(
            @ApiParam(value = "用户ID", required = true) @RequestParam Integer userId) {
        Map<String, Object> result = new HashMap<>();
        boolean success = userService.freeUserById(userId);

        if (success) {
            result.put("code", 0);
            result.put("msg", "用户已被解封");
        } else {
            result.put("code", 1);
            result.put("msg", "未找到用户或解封失败");
        }

        return result;
    }
}
