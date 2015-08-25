package com.borjabares.myshoppinglist.persistence.dao.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InstanceNotFoundException extends InstanceException {
    public InstanceNotFoundException(Object key, String className) {
        super("Instance not found", key, className);
    }
}