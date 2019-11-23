package com.hackathon.backend.controller;


import com.hackathon.backend.constant.ServerException;
import com.hackathon.backend.constant.SimpleResponse;
import com.hackathon.backend.entity.ItemEntity;
import com.hackathon.backend.form.ItemForm;
import com.hackathon.backend.service.ItemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/item/")
@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    private static int MIN = 3;

    //TODO 出租物品
    @ApiOperation(value = "注册物品", response = ItemEntity.class,
    notes = "返回SimpleResponse对象，如果注册成功，SimpleResponse对象Data为ItemEntity")
    @PostMapping("/registerItem")
    public SimpleResponse register(@ApiIgnore HttpSession session, @RequestBody ItemForm itemForm)
    {
        ItemEntity itemEntity = new ItemEntity();
        try
        {
            itemEntity = itemService.registerItem(itemForm);
        }
        catch (ServerException sec)
        {
            return SimpleResponse.error(sec.getMessage());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return SimpleResponse.error("register item error");
        }

        return SimpleResponse.ok(itemEntity);
    }


    //TODO 返回物品列表
    @ApiOperation(value = "返回物品列表", response = ItemEntity.class,
    notes = "返回SimpleResponse对象，如果查找成功，SimpleResponse对象Data为ItemEntity的List")
    @GetMapping("/returnAll/")
    public SimpleResponse returnAll(@ApiIgnore HttpSession session)
    {
        List<ItemEntity> itemEntities = null;
        try
        {
            itemEntities = itemService.getAll();
        }
        catch (Exception ex)
        {
            return SimpleResponse.error("return list error");
        }
        return SimpleResponse.ok(itemEntities);
    }


    //TODO 查看物品详情
    @ApiOperation(value = "查看物品详情", response = ItemEntity.class,
    notes = "返回SimpleResponse对象，如果查看成功，SimpleResponse对象Data为ItemEntity")
    @GetMapping("/check/{url}")
    public SimpleResponse check(@ApiIgnore HttpSession session, @RequestBody String url)
    {
        ItemEntity itemEntity = new ItemEntity();
        try
        {
            itemEntity = itemService.getItemByUrl(url);
        }
        catch (Exception ex)
        {
            return SimpleResponse.error("check item error");
        }
        return SimpleResponse.ok(itemEntity);
    }


    //TODO 搜索物品
    @ApiOperation(value = "搜索物品", response = ItemEntity.class,
    notes = "返回SimpleResponse对象，如果登录成功，SimpleResponse对象Data为ItemEntity的List")
    @GetMapping("/find/{description}")
    public SimpleResponse find(@ApiIgnore HttpSession session, @RequestBody String description)
    {
        char[] des = description.toCharArray();
        List<ItemEntity> itemEntities = null;
        try
        {
            itemEntities = itemService.getAll();
        }
        catch (Exception ex)
        {
            return SimpleResponse.error("return list error");
        }

        for(ItemEntity itemEntity : itemEntities)
        {
            char[] real = itemEntity.getDescription().toCharArray();
            int num = 0;
            for(int i = 0;i<des.length;i++)
            {
                for(int j = 0;j<real.length;j++)
                {
                    if(des[i]==real[j]) num++;
                }
            }
            if(num<MIN)
            {
                num=0;
                itemEntities.remove(itemEntity);
            }
        }

        return SimpleResponse.ok(itemEntities);

    }

    //TODO 修改物品
    @ApiOperation(value = "修改物品", response = ItemEntity.class,
    notes = "返回SimpleResponse对象，如果修改成功，SimpleResponse对象Data为ItemEntity")
    @PostMapping("modify")
    public SimpleResponse modifyItem(@ApiIgnore HttpSession session, @RequestBody ItemForm itemForm)
    {
        ItemEntity itemEntity = null;
        try
        {
            itemService.modifyItem(itemForm);
            itemEntity = itemService.getItemByUrl(itemForm.getUrl());
        }
        catch (Exception e)
        {
            return SimpleResponse.error("modify item error");
        }
        return SimpleResponse.ok(itemEntity);
    }



}
