package com.borjabares.myshoppinglist.persistence.service;

import com.borjabares.myshoppinglist.persistence.bean.Article;
import com.borjabares.myshoppinglist.persistence.bean.Cart;
import com.borjabares.myshoppinglist.persistence.bean.Quantity;
import com.borjabares.myshoppinglist.persistence.dao.ArticleDao;
import com.borjabares.myshoppinglist.persistence.dao.CartDao;
import com.borjabares.myshoppinglist.persistence.dao.QuantityDao;
import com.borjabares.myshoppinglist.persistence.service.util.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartServiceImpl extends GenericServiceImpl<Cart> implements CartService {
    private final CartDao cartDao;
    @Autowired private ArticleDao articleDao;
    @Autowired private QuantityDao quantityDao;

    @Autowired
    public CartServiceImpl(CartDao cartDao) {
        super(cartDao);
        this.cartDao = cartDao;
    }

    @Override
    @Transactional
    public Cart addArticle(long idCart, String name, int qty) {
        Quantity quantity = null;
        Cart cart = cartDao.findWithArticles(idCart);
        Article article = articleDao.findByName(name);

        if (article == null) {
            article = new Article(name);
            articleDao.save(article);
        } else {
            quantity = quantityDao.findByArticleAndCart(article.getId(), idCart);
        }

        if (quantity == null) {
            quantity = new Quantity(qty, cart, article);
            cart.getQuantities().add(quantity);
        } else {
            quantity.setQuantity(qty);
        }

        quantityDao.save(quantity);

        return cart;
    }
}
