package com.borjabares.myshoppinglist.controller;

import com.borjabares.myshoppinglist.persistence.bean.Shop;
import com.borjabares.myshoppinglist.persistence.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/shops")
public class ShopController extends GenericController<Shop> {
    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        super(shopService);
        this.shopService = shopService;
    }

    @RequestMapping(value = "/{id}/price", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public Shop addPrice(@PathVariable long id, @RequestBody ArticleRequest article) {
        return shopService.addPrice(id, article.getName(), article.getPrice());
    }

    private static class ArticleRequest {
        private String name;
        private BigDecimal price;

        public ArticleRequest() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }
}
