package com.qfleaf.aggregatesearch.adapter;

import com.qfleaf.aggregatesearch.model.SearchRequest;
import org.springframework.data.domain.Page;

public interface SearchService<T> {
    /**
     * 执行搜索操作
     *
     * @param searchRequest 搜索请求参数
     * @return 结果分页
     */
    Page<T> search(SearchRequest searchRequest);
}
