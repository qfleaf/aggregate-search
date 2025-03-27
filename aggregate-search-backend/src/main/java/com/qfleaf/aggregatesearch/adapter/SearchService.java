package com.qfleaf.aggregatesearch.adapter;

import com.qfleaf.aggregatesearch.common.PageData;
import com.qfleaf.aggregatesearch.model.SearchRequest;

public interface SearchService {
    /**
     * 执行搜索操作
     *
     * @param searchRequest 搜索请求参数
     * @return 结果分页
     */
    PageData search(SearchRequest searchRequest);
}
