package com.qfleaf.aggregatesearch.esdao;

import com.qfleaf.aggregatesearch.model.entity.Article;
import com.qfleaf.aggregatesearch.model.vo.ArticleListVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleEsDAO extends ElasticsearchRepository<Article, Long> {
    Page<ArticleListVO> searchArticleByContentOrTitleOrAuthorOrderByCreatedTime(String content, String title, String author, Pageable pageable);
}
