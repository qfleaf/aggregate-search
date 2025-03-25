package com.qfleaf.aggregatesearch.esdao;

import com.qfleaf.aggregatesearch.model.entity.QA;
import com.qfleaf.aggregatesearch.model.vo.QAListVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface QAEsDAO extends ElasticsearchRepository<QA, Long> {
    Page<QAListVO> searchByQuestionOrAnswerOrAuthorOrderByCreatedTime(String question, String answer, String author, Pageable pageable);
}
