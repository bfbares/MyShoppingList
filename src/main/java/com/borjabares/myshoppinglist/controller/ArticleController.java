package com.borjabares.myshoppinglist.controller;

import com.borjabares.myshoppinglist.persistence.bean.Article;
import com.borjabares.myshoppinglist.persistence.bean.Category;
import com.borjabares.myshoppinglist.persistence.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/articles")
public class ArticleController extends GenericController<Article> {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        super(articleService);
        this.articleService = articleService;
    }

    @RequestMapping(value = "/{id}/category", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public Article saveCategory(@PathVariable(value = "id") long idArticle, @RequestBody Category category) {
        return articleService.addCategory(idArticle, category.getName());
    }
}
