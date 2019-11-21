package com.hackathon.backend.service;

import com.hackathon.backend.entity.MyFormEntity;
import com.hackathon.backend.form.MyOrderForm;

public interface MyFormService {


    public MyFormEntity registerForm(MyOrderForm myOrderForm);

    public void modifyState(int id, String NewState);

    public MyFormEntity getForm(String url);

    public MyFormEntity getForm(int id);

}
