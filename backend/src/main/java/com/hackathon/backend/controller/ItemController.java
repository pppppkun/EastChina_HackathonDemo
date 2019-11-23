package com.hackathon.backend.controller;


import com.hackathon.backend.constant.SimpleResponse;
import com.hackathon.backend.entity.ItemEntity;
import com.hackathon.backend.form.ItemForm;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import javax.servlet.http.HttpSession;
@RequestMapping("/item/")
@RestController
public class ItemController {


    //TODO 出租物品
    @ApiOperation(value = "注册物品", response = ItemEntity.class,
    notes = "返回SimpleResponse对象，如果登录成功，SimpleResponse对象Data为ItemEntity")
    @PostMapping("/registerItem")
    public SimpleResponse register(@ApiIgnore HttpSession session, @RequestBody ItemForm itemForm)
    {
        return SimpleResponse.ok("OK");
    }


    //TODO 返回物品列表
    @ApiOperation(value = "返回物品列表", response = ItemEntity.class,
    notes = "返回SimpleResponse对象，如果登录成功，SimpleResponse对象Data为ItemEntity的List")
    @GetMapping("/returnAll/")
    public SimpleResponse returnAll(@ApiIgnore HttpSession session)
    {
        return SimpleResponse.ok("OK");
    }


    //TODO 查看物品详情
    @ApiOperation(value = "查看物品详情", response = ItemEntity.class,
    notes = "返回SimpleResponse对象，如果登录成功，SimpleResponse对象Data为ItemEntity")
    @GetMapping("/check/{url}")
    public SimpleResponse check(@ApiIgnore HttpSession session, @RequestBody String url)
    {
        return SimpleResponse.ok("OK");
    }


    //TODO 搜索物品
    @ApiOperation(value = "搜索物品", response = ItemEntity.class,
    notes = "返回SimpleResponse对象，如果登录成功，SimpleResponse对象Data为ItemEntity")
    @GetMapping("/find/{description}")
    public SimpleResponse find(@ApiIgnore HttpSession session, @RequestBody String description)
    {
        return SimpleResponse.ok("OK");
    }



}
