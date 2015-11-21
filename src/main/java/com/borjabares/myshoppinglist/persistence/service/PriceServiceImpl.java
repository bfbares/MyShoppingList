package com.borjabares.myshoppinglist.persistence.service;

import com.borjabares.myshoppinglist.persistence.dao.PriceDao;
import com.borjabares.myshoppinglist.persistence.model.Price;
import com.borjabares.myshoppinglist.persistence.service.util.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl extends GenericServiceImpl<Price> implements PriceService {
    private final PriceDao priceDao;

    @Autowired
    public PriceServiceImpl(PriceDao priceDao) {
        super(priceDao);
        this.priceDao = priceDao;
    }
}
