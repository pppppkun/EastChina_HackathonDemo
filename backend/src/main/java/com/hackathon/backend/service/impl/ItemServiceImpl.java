package com.hackathon.backend.service.impl;


import com.hackathon.backend.constant.ResponseCode;
import com.hackathon.backend.constant.ServerException;
import com.hackathon.backend.dao.ItemMapper;
import com.hackathon.backend.entity.ItemEntity;
import com.hackathon.backend.form.ItemForm;
import com.hackathon.backend.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemMapper itemMapper;

    @Override
    public ItemEntity registerItem(ItemForm itemForm) {
        ItemEntity itemEntity = itemMapper.Sel_by_url(itemForm.getUrl());
        if (itemEntity != null) {
            throw new ServerException(ResponseCode.Error, "already have this item");
        }
        itemEntity = new ItemEntity();
        itemEntity.setUrl(itemForm.getUrl());
        itemEntity.setPrice(itemForm.getPrice());
        itemEntity.setSeller(itemForm.getSeller());
        itemEntity.setBuyer(itemForm.getBuyer());
        itemEntity.setAdd_time(itemForm.getAdd_time());
        itemEntity.setCategory(itemForm.getCategory());
        itemEntity.setLongest_time(itemForm.getLongest_time());
        itemEntity.setDelivery(itemForm.getDelivery());
        itemEntity.setDescription(itemForm.getDescription());
        itemMapper.insert(itemEntity);
        return itemEntity;
    }

    @Override
    public void modifyItem(ItemForm itemForm) {
        modifyItem(itemForm);
    }

    @Override
    public List<ItemEntity> getItemBySeller(String seller) {
        return itemMapper.Sel_by_seller(seller);
    }

    @Override
    public List<ItemEntity> getItemByBuyer(String buyer) {
        return itemMapper.Sel_by_buyer(buyer);
    }

    @Override
    public List<ItemEntity> getAll() {
        return itemMapper.Sel_all();
    }

    @Override
    public ItemEntity getItemByUrl(String url) {
        return itemMapper.Sel_by_url(url);
    }

}
