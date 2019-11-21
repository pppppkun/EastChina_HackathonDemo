package com.hackathon.backend.controller;


import com.hackathon.backend.constant.ServerException;
import com.hackathon.backend.constant.SimpleResponse;
import com.hackathon.backend.entity.UserEntity;
import com.hackathon.backend.form.UserForm;
import com.hackathon.backend.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/user/")
public class UserController {


    @Autowired
    UserService userService;


    //TODO 用户注册
    @ApiOperation(value = "用户注册", response = UserEntity.class, notes="返回SimpleResponse,如果成功注册，返回UserEntity")
    @PostMapping("registerUser")
    public SimpleResponse registerUser(HttpSession session, @RequestBody UserForm userForm)
    {
        UserEntity userEntity = null;
        System.out.println(userForm.getUsername());
        try
        {
            userEntity = userService.registerUser(userForm);
        }catch (ServerException sec)
        {
            return SimpleResponse.error(sec);
        }catch (Exception e)
        {
            e.printStackTrace();
            return SimpleResponse.error("register error");
        }
        session.setAttribute("user", userEntity);
        return SimpleResponse.ok(userEntity);
    }


    //TODO 修改密码



    //TODO 修改个人信息

}
