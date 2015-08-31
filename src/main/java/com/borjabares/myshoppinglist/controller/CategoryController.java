package com.borjabares.myshoppinglist.controller;

import com.borjabares.myshoppinglist.persistence.bean.Article;
import com.borjabares.myshoppinglist.persistence.bean.Category;
import com.borjabares.myshoppinglist.persistence.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController extends GenericController<Category> {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        super(categoryService);
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/{name}/articles", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public List<Article> getArticlesFromId(@PathVariable(value = "name") String categoryName) {
        return categoryService.getArticlesFromName(categoryName);
    }
}
