package com.qfleaf.aggregatesearch.service.impl;

import com.qfleaf.aggregatesearch.adapter.SearchService;
import com.qfleaf.aggregatesearch.adapter.anno.SearchAdapter;
import com.qfleaf.aggregatesearch.common.PageData;
import com.qfleaf.aggregatesearch.esdao.ArticleEsDAO;
import com.qfleaf.aggregatesearch.model.SearchRequest;
import com.qfleaf.aggregatesearch.model.vo.ArticleListVO;
import com.qfleaf.aggregatesearch.repository.ArticleRepository;
import com.qfleaf.aggregatesearch.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SearchAdapter("article")
public class ArticleServiceImpl implements ArticleService, SearchService {
    @Override
    public PageData search(SearchRequest searchRequest) {
        PageRequest pageRequest = PageRequest.of(searchRequest.getCurrent() - 1, searchRequest.getSize());
        String keyword = searchRequest.getKeyword();
        Page<ArticleListVO> articleVos = articleEsDAO.searchArticleByContentOrTitleOrAuthorOrderByCreatedTime(keyword, keyword, keyword, pageRequest);
        return new PageData(articleVos);
    }

    private final ArticleRepository articleRepository;
    private final ArticleEsDAO articleEsDAO;

    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleEsDAO articleEsDAO) {
        this.articleRepository = articleRepository;
        this.articleEsDAO = articleEsDAO;
    }

}
