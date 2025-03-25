package com.qfleaf.aggregatesearch.repository;

import com.qfleaf.aggregatesearch.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
