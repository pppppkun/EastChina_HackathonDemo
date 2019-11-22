package com.hackathon.backend.dao;

import com.hackathon.backend.entity.MyFormEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderMapper {

    //TODO
    int insert(MyFormEntity myFormEntity);

    MyFormEntity Sel_by_url(String url);
    MyFormEntity Sel_by_id(Integer id);

    int update(MyFormEntity myFormEntity);

}
