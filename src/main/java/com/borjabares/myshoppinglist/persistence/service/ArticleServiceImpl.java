package com.borjabares.myshoppinglist.persistence.service;

import com.borjabares.myshoppinglist.persistence.bean.Article;
import com.borjabares.myshoppinglist.persistence.dao.ArticleDao;
import com.borjabares.myshoppinglist.persistence.service.util.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleServiceImpl extends GenericServiceImpl<Article> implements ArticleService {
}
//@Service
//public class ArticleServiceImpl implements ArticleService {
//    @Autowired private ArticleDao articleDao;
//
//    @Override
//    @Transactional
//    public void save(Article article) {
//        articleDao.save(article);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Article find(long id) {
//        return articleDao.find(id);
//    }
//}
