package com.borjabares.myshoppinglist.persistence.service;

import com.borjabares.myshoppinglist.persistence.bean.Shop;
import com.borjabares.myshoppinglist.persistence.service.util.GenericService;

import java.math.BigDecimal;

public interface ShopService extends GenericService<Shop> {
    Shop addPrice(long idShop, String name, BigDecimal price);
}
