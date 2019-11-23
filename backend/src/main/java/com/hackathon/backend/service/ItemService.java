package com.hackathon.backend.service;

import com.hackathon.backend.entity.ItemEntity;
import com.hackathon.backend.form.ItemForm;

import java.util.List;

public interface ItemService {

    public ItemEntity registerItem(ItemForm itemForm);

    public ItemEntity getItemByUrl(String url);

    public void modifyItem(ItemForm itemForm);

    public List<ItemEntity> getItemBySeller(String seller);

    public List<ItemEntity> getItemByBuyer(String buyer);

    public List<ItemEntity> getAll();

}
