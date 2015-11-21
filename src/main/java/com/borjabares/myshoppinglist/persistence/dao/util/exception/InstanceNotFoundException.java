package com.borjabares.myshoppinglist.persistence.dao.util.exception;

public class InstanceNotFoundException extends InstanceException {
    private static final long serialVersionUID = -497390357810683256L;

    public InstanceNotFoundException(Object key, String className) {
        super("Instance not found", key, className);
    }
}