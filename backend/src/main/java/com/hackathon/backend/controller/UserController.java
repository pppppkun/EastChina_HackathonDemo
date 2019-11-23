package com.hackathon.backend.controller;


import com.hackathon.backend.constant.ServerException;
import com.hackathon.backend.constant.SimpleResponse;
import com.hackathon.backend.entity.UserEntity;
import com.hackathon.backend.form.UserForm;
import com.hackathon.backend.form.passwordForm;
import com.hackathon.backend.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import sun.nio.ch.SelectorImpl;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/user/")
public class UserController {


    @Autowired
    UserService userService;

    /**
     * 用户注册
     * @param session
     * @param userForm
     * @return
     */
    @ApiOperation(value = "用户注册", response = UserEntity.class, notes="返回SimpleResponse,如果成功注册，返回UserEntity")
    @PostMapping("registerUser/")
    public SimpleResponse registerUser(@ApiIgnore HttpSession session, @RequestBody UserForm userForm)
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
    @ApiOperation(value = "修改密码", response = UserEntity.class,
    notes = "返回SimpleResponse,如果修改成功，返回UserEntity")
    @PostMapping("/modify/")
    public SimpleResponse modify(@ApiIgnore HttpSession session, @RequestBody passwordForm passwordForm)
    {
        userService.modifyPassword(passwordForm.getId(),passwordForm.getOldPassword(),passwordForm.getNewPassword());
        return SimpleResponse.ok("modify success!");
    }


    //TODO 修改个人信息
    @ApiOperation(value = "修改个人信息", response = UserEntity.class,
    notes = "返回SimpleResponse,如果修改成功,返回UserEntity")
    @PostMapping("/modifyAll")
    public SimpleResponse modifyAll(@ApiIgnore HttpSession session, @RequestBody UserForm userForm)
    {
        userService.modify(userForm);
        return SimpleResponse.ok("modify success!");
    }


}
