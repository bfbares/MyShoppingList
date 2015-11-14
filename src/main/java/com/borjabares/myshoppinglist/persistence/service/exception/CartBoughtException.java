package com.borjabares.myshoppinglist.persistence.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CartBoughtException extends Exception {
    public CartBoughtException() {
        super();
    }
}
