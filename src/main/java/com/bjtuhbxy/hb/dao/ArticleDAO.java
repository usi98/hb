package com.bjtuhbxy.hb.dao;

import com.bjtuhbxy.hb.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDAO extends JpaRepository<Article, Integer> {
    Article findById(int id);
}
