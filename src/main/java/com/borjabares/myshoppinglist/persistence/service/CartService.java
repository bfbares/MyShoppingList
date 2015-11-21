package com.borjabares.myshoppinglist.persistence.service;

import com.borjabares.myshoppinglist.persistence.model.Cart;
import com.borjabares.myshoppinglist.persistence.service.exception.CartBoughtException;
import com.borjabares.myshoppinglist.persistence.service.util.GenericService;

public interface CartService extends GenericService<Cart> {
    Cart addArticle(long idCart, String name, int quantity) throws CartBoughtException;

    Cart buy(long idCart);
}
