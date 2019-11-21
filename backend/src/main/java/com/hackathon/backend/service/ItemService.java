package com.hackathon.backend.service;

import com.hackathon.backend.entity.ItemEntity;
import com.hackathon.backend.form.ItemForm;

public interface ItemService {

    public ItemEntity registerItem(ItemForm itemForm);

    public void modifyItem(ItemForm itemForm);

    public ItemEntity getItemBySeller(String Seller);

    public ItemEntity getItemByBuyer(String Buyer);

}
