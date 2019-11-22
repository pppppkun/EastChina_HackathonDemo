package com.hackathon.backend.service.impl;

import com.hackathon.backend.constant.ResponseCode;
import com.hackathon.backend.constant.ServerException;
import com.hackathon.backend.dao.OrderMapper;
import com.hackathon.backend.dao.UserMapper;
import com.hackathon.backend.entity.MyFormEntity;
import com.hackathon.backend.form.MyOrderForm;
import com.hackathon.backend.service.MyFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MyFormServiceImpl implements MyFormService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderMapper orderMapper;

    @Override
    public MyFormEntity registerForm(MyOrderForm myOrderForm)
    {
        MyFormEntity myFormEntity = orderMapper.Sel_by_url(myOrderForm.getUrl());
        if(myFormEntity != null)
        {
            throw new ServerException(ResponseCode.Error, "already have this order");
        }
        myFormEntity = new MyFormEntity();
        myFormEntity.setCreate_time(myOrderForm.getCreate_time());
        myFormEntity.setState(myOrderForm.getState());
        myFormEntity.setTotal_price(myOrderForm.getTotal_price());
        myFormEntity.setUrl(myOrderForm.getUrl());

        orderMapper.insert(myFormEntity);
        return myFormEntity;
    }

    @Override
    public void modifyState(int id, String NewState)
    {
        MyFormEntity myFormEntity = orderMapper.Sel_by_id(id);
        myFormEntity.setState(NewState);
        orderMapper.update(myFormEntity);
    }

    @Override
    public MyFormEntity getForm(String url)
    {
        return orderMapper.Sel_by_url(url);
    }

    @Override
    public MyFormEntity getForm(int id)
    {
        return orderMapper.Sel_by_id(id);
    }

}
