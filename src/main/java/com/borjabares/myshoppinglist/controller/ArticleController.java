package com.borjabares.myshoppinglist.controller;

import com.borjabares.myshoppinglist.persistence.bean.Article;
import com.borjabares.myshoppinglist.persistence.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/v1/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(value = "/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public Article getArticle(@PathVariable("id") long id, @RequestParam(value = "expand", defaultValue = "") String expand) {
        Article article = null;
        if (expand.isEmpty()) {
            article = articleService.find(id);
        }

//        if (expand.equalsIgnoreCase("items")) {
//            article = articleService.findArticleWithItems(id);
//        }
        return article;
    }

    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public Article saveArticle(@RequestBody Article article) {
        articleService.save(article);

        return article;
    }
}
