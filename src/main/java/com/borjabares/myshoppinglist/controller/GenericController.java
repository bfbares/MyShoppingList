package com.borjabares.myshoppinglist.controller;

import com.borjabares.myshoppinglist.persistence.dao.util.exception.InstanceNotFoundException;
import com.borjabares.myshoppinglist.persistence.service.util.GenericService;
import com.borjabares.myshoppinglist.util.Joiner;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

public abstract class GenericController<E> {
    private GenericService<E> genericService;

    private Class<E> entityClass;

    @SuppressWarnings("unchecked")
    public GenericController(GenericService<E> genericService) {
        this.genericService = genericService;
        this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public E find(@PathVariable("id") long id, @RequestParam(value = "expand", required = false) String expand) {
        if (StringUtils.isEmpty(expand)) {
            return genericService.find(id);
        } else {
            Joiner<E> joiner = new Joiner<>(expand, entityClass);
            return genericService.find(id, joiner);
        }
    }

    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public List<E> findAll(@RequestParam(value = "expand", required = false) String expand) {
        if (StringUtils.isEmpty(expand)) {
            return genericService.getAll();
        } else {
            Joiner<E> joiner = new Joiner<>(expand, entityClass);
            return genericService.getAll(joiner);
        }
    }

    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public E save(@RequestBody E entity) {
        genericService.save(entity);

        return entity;
    }

    @ExceptionHandler({NoResultException.class, EmptyResultDataAccessException.class, InstanceNotFoundException.class})
    @ResponseStatus(NOT_FOUND)
    public void noResultException() {
        // DO NOTHING
    }
}
