package com.borjabares.myshoppinglist.persistence.service;

import com.borjabares.myshoppinglist.persistence.dao.QuantityDao;
import com.borjabares.myshoppinglist.persistence.model.Quantity;
import com.borjabares.myshoppinglist.persistence.service.util.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuantityServiceImpl extends GenericServiceImpl<Quantity> implements QuantityService {
    private final QuantityDao quantityDao;

    @Autowired
    public QuantityServiceImpl(QuantityDao quantityDao) {
        super(quantityDao);
        this.quantityDao = quantityDao;
    }
}
