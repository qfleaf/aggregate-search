package com.qfleaf.aggregatesearch.esdao;

import com.qfleaf.aggregatesearch.model.entity.QA;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface QAEsDAO extends ElasticsearchRepository<QA, Long> {
}
