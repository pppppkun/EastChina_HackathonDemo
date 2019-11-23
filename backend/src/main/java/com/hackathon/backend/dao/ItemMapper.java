package com.hackathon.backend.dao;

import com.hackathon.backend.entity.ItemEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemMapper {

    ItemEntity Sel_by_url(String url);
    List<ItemEntity> Sel_all();
    List<ItemEntity> Sel_by_seller(String seller);
    List<ItemEntity> Sel_by_buyer(String buyer);

    int insert(ItemEntity itemEntity);
    int update_selective(ItemEntity itemEntity);

}
