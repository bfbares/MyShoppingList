package com.borjabares.myshoppinglist.controller;

import com.borjabares.myshoppinglist.persistence.bean.Cart;
import com.borjabares.myshoppinglist.persistence.service.CartService;
import com.borjabares.myshoppinglist.persistence.service.exception.CartBoughtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/carts")
public class CartController extends GenericController<Cart> {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        super(cartService);
        this.cartService = cartService;
    }

    @RequestMapping(value = "/{id}/article", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public Cart saveArticle(@PathVariable(value = "id") long idCart, @RequestBody ArticleRequest article) throws CartBoughtException {
        return cartService.addArticle(idCart, article.getName(), article.getQuantity());
    }

    @RequestMapping(value = "/{id}/buy", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Cart buy(@PathVariable(value = "id") long idCart) {
        return cartService.buy(idCart);
    }


    private static class ArticleRequest {
        private String name;
        private int quantity;

        public ArticleRequest() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
