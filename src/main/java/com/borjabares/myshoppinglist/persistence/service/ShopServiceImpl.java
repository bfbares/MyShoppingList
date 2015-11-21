package com.borjabares.myshoppinglist.persistence.service;

import com.borjabares.myshoppinglist.persistence.dao.ArticleDao;
import com.borjabares.myshoppinglist.persistence.dao.PriceDao;
import com.borjabares.myshoppinglist.persistence.dao.ShopDao;
import com.borjabares.myshoppinglist.persistence.model.Article;
import com.borjabares.myshoppinglist.persistence.model.Price;
import com.borjabares.myshoppinglist.persistence.model.Shop;
import com.borjabares.myshoppinglist.persistence.service.util.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ShopServiceImpl extends GenericServiceImpl<Shop> implements ShopService {
    private final ShopDao shopDao;
    @Autowired private ArticleDao articleDao;
    @Autowired private PriceDao priceDao;

    @Autowired
    public ShopServiceImpl(ShopDao shopDao) {
        super(shopDao);
        this.shopDao = shopDao;
    }

    @Override
    @Transactional
    public Shop addPrice(long idShop, String name, BigDecimal priceValue) {
        Shop shop = shopDao.findWithArticles(idShop);
        Article article = articleDao.findByName(name);

        if (article == null) {
            article = new Article(name);
            articleDao.save(article);
        }

        Price price = new Price(priceValue, article, shop);
        shop.getPrices().add(price);
        priceDao.save(price);

        return shop;
    }
}
