package com.qfleaf.aggregatesearch.esdao;

import com.qfleaf.aggregatesearch.model.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleEsDAO extends ElasticsearchRepository<Article, Long> {
}
