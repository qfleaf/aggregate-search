package com.qfleaf.aggregatesearch.service.impl;

import com.qfleaf.aggregatesearch.adapter.SearchService;
import com.qfleaf.aggregatesearch.adapter.anno.SearchAdapter;
import com.qfleaf.aggregatesearch.common.PageData;
import com.qfleaf.aggregatesearch.esdao.QAEsDAO;
import com.qfleaf.aggregatesearch.model.SearchRequest;
import com.qfleaf.aggregatesearch.model.vo.QAListVO;
import com.qfleaf.aggregatesearch.service.QAService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SearchAdapter("qa")
public class QAServiceImpl implements QAService, SearchService {
    @Override
    public PageData search(SearchRequest searchRequest) {
        PageRequest pageRequest = PageRequest.of(searchRequest.getCurrent() - 1, searchRequest.getSize());
        String keyword = searchRequest.getKeyword();
        Page<QAListVO> qaListVos = qaesDAO.searchByQuestionOrAnswerOrAuthorOrderByCreatedTime(keyword, keyword, keyword, pageRequest);
        return new PageData(qaListVos);
    }

    private final QAEsDAO qaesDAO;

    public QAServiceImpl(QAEsDAO qaesDAO) {
        this.qaesDAO = qaesDAO;
    }

}
