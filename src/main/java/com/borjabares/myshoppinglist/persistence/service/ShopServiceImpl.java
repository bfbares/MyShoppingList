package com.borjabares.myshoppinglist.persistence.service;

import com.borjabares.myshoppinglist.persistence.bean.Shop;
import com.borjabares.myshoppinglist.persistence.dao.ShopDao;
import com.borjabares.myshoppinglist.persistence.service.util.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl extends GenericServiceImpl<Shop> implements ShopService {
    private final ShopDao shopDao;

    @Autowired
    public ShopServiceImpl(ShopDao shopDao) {
        super(shopDao);
        this.shopDao = shopDao;
    }
}
