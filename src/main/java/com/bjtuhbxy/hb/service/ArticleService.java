package com.bjtuhbxy.hb.service;


import com.bjtuhbxy.hb.dao.ArticleDAO;
import com.bjtuhbxy.hb.entity.Article;
import com.bjtuhbxy.hb.util.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ArticleService {
    @Autowired
    ArticleDAO articleDAO;

    public MyPage list(int page, int size){

        MyPage<Article> articles;
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Page<Article> articlesInDb = articleDAO.findAll(PageRequest.of(page, size, sort));
        articles = new MyPage<>(articlesInDb);

        return articles;
    }

    public void addArticle(Article article){
        articleDAO.save(article);
    }

    public void delete(int id){
        articleDAO.deleteById(id);
    }

    public Article findById(int id){
        return articleDAO.findById(id);
    }

}
