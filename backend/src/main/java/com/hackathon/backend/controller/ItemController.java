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
import java.util.Random;

@RequestMapping("/item/")
@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    private static int MIN = 4;

    //TODO 出租物品
    @ApiOperation(value = "注册物品", response = ItemEntity.class,
    notes = "返回SimpleResponse对象，如果注册成功，SimpleResponse对象Data为ItemEntity")
    @PostMapping("/registerItem")
    public ItemEntity register(@ApiIgnore HttpSession session, @RequestBody ItemForm itemForm)
    {
        ItemEntity itemEntity = new ItemEntity();itemEntity = itemService.registerItem(itemForm);
//        try
//        {
//            itemEntity = itemService.registerItem(itemForm);
//        }
//        catch (ServerException sec)
//        {
//            return SimpleResponse.error(sec.getMessage());
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//            return SimpleResponse.error("register item error");
//        }
        session.setAttribute("item",itemEntity);
        return itemEntity;
    }


    //TODO 返回物品列表
    @ApiOperation(value = "返回物品列表", response = ItemEntity.class,
    notes = "返回SimpleResponse对象，如果查找成功，SimpleResponse对象Data为ItemEntity的List")
    @GetMapping("/returnAll/")
    public List<ItemEntity> returnAll(@ApiIgnore HttpSession session)
    {
        List<ItemEntity> itemEntities = null;itemEntities = itemService.getAll();
//        try
//        {
//            itemEntities = itemService.getAll();
//        }
//        catch (Exception ex)
//        {
//            return SimpleResponse.error("return list error");
//        }

        return itemEntities;
    }


    //TODO 查看物品详情
    @ApiOperation(value = "查看物品详情", response = ItemEntity.class,
    notes = "返回SimpleResponse对象，如果查看成功，SimpleResponse对象Data为ItemEntity")
    @GetMapping("/check/{url}")
    public ItemEntity check(@ApiIgnore HttpSession session, @PathVariable("url") String url)
    {
        ItemEntity itemEntity = new ItemEntity();itemEntity = itemService.getItemByUrl(url);
//        try
//        {
//            itemEntity = itemService.getItemByUrl(url);
//        }
//        catch (Exception ex)
//        {
//            return SimpleResponse.error("check item error");
//        }
//        session.setAttribute("item",itemEntity);
        return itemEntity;
    }


    //TODO 搜索物品
    @ApiOperation(value = "搜索物品", response = ItemEntity.class,
    notes = "返回SimpleResponse对象，如果搜索成功，SimpleResponse对象Data为ItemEntity的List")
    @GetMapping("/find/{description}")
    public List<ItemEntity> find(@ApiIgnore HttpSession session, @PathVariable("description") String description)
    {

        Random r = new Random(1);
        if(description==null||description.length()==0)
        {
            List<ItemEntity> itemEntities = null;itemEntities = itemService.getAll();
            for(int i = 0;i<itemEntities.size();i+=2)
            {
                int j = r.nextInt();
                if(0<=j&&j<=itemEntities.size())
                itemEntities.remove(j);
            }
            return itemEntities;
        }

        char[] des = description.toCharArray();

        List<ItemEntity> itemEntities = null;itemEntities = itemService.getAll();


//        try
//        {
//            itemEntities = itemService.getAll();
//        }
//        catch (Exception ex)
//        {
//            return SimpleResponse.error("return list error");
//        }

        for(int t = 0;t<itemEntities.size();t++)
        {
            ItemEntity itemEntity = itemEntities.get(t);
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



        session.setAttribute("item list", itemEntities);
        return itemEntities;

    }

    //TODO 修改物品
    @ApiOperation(value = "修改物品", response = ItemEntity.class,
    notes = "返回SimpleResponse对象，如果修改成功，SimpleResponse对象Data为ItemEntity")
    @PostMapping("modify")
    public ItemEntity modifyItem(@ApiIgnore HttpSession session, @RequestBody ItemForm itemForm)
    {
        ItemEntity itemEntity = null;itemService.modifyItem(itemForm);
        itemEntity = itemService.getItemByUrl(itemForm.getUrl());
//        try
//        {
//            itemService.modifyItem(itemForm);
//            itemEntity = itemService.getItemByUrl(itemForm.getUrl());
//        }
//        catch (Exception e)
//        {
//            return SimpleResponse.error("modify item error");
//        }
        session.setAttribute("item",itemEntity);
        return itemEntity;
    }

}
