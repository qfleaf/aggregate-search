package com.qfleaf.aggregatesearch.repository;

import com.qfleaf.aggregatesearch.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByUpdatedTimeAfter(LocalDateTime updatedTimeAfter);
}
