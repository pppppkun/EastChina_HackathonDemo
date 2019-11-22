package com.hackathon.backend.controller;


import com.hackathon.backend.constant.ServerException;
import com.hackathon.backend.constant.SimpleResponse;
import com.hackathon.backend.entity.ItemEntity;
import com.hackathon.backend.entity.MyFormEntity;
import com.hackathon.backend.form.MyOrderForm;
import com.hackathon.backend.service.ItemService;
import com.hackathon.backend.service.MyFormService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/myform/")
public class MyFormController {

    @Autowired
    MyFormService myFormService;
    @Autowired
    ItemService itemService;

    /**
     * 下订单
     * @param session
     * @param myOrderForm
     * @return
     */
    @ApiOperation( value = "注册订单", response = MyFormEntity.class,
    notes = "返回SimpleResponse对象，如果登录成功，SimpleResponse对象Data为MyFormEntity")
    @PostMapping("register")
    public SimpleResponse register(@ApiIgnore HttpSession session, @RequestBody MyOrderForm myOrderForm)
    {
        MyFormEntity myFormEntity = new MyFormEntity();
        try
        {
            myFormEntity = myFormService.registerForm(myOrderForm);
        }catch (ServerException sec)
        {
            return SimpleResponse.error(sec.getMessage());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return SimpleResponse.error("register order error");
        }

        return SimpleResponse.ok(myFormEntity);

    }


    //TODO 查看订单
    @ApiOperation(value = "查看订单", response = MyFormEntity.class,
    notes = "返回SimpleResponse对象，如果查看成功，则SimpleResponse对象Data为MyFormEntiry")
    @GetMapping("check/{seller}")
    public SimpleResponse checkForm(@ApiIgnore HttpSession session, @PathVariable("seller") String seller)
    {

        List<ItemEntity> items = null;

        try
        {
            items = itemService.getItemBySeller(seller);
        }
        catch (Exception ex)
        {
            return SimpleResponse.exception(ex);
        }



        List<MyFormEntity> forms = new LinkedList<>();
        try
        {
            for(ItemEntity itemEntity : items)
            {
                forms.add(myFormService.getForm(itemEntity.getUrl()));
            }
        }
        catch (Exception ex)
        {
            return SimpleResponse.exception(ex);
        }

        return SimpleResponse.ok(forms);

    }

    //TODO 修改状态




}
