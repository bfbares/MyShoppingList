package com.borjabares.myshoppinglist.controller;

import com.borjabares.myshoppinglist.persistence.service.util.GenericService;
import com.borjabares.myshoppinglist.util.Expander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

public class GenericController<E> {
    @Autowired private GenericService<E> genericService;

    private Class<E> entityClass;

    @SuppressWarnings("unchecked")
    public GenericController() {
        this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public E find(@PathVariable("id") long id, @RequestParam(value = "expand", defaultValue = "") String expand) {
        Expander<E> expander = new Expander<>(expand, entityClass);
        return genericService.find(id, expander);
    }

    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public E save(@RequestBody E entity) {
        genericService.save(entity);

        return entity;
    }
}
