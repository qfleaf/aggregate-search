package com.qfleaf.aggregatesearch.adapter;

import com.qfleaf.aggregatesearch.adapter.anno.SearchAdapter;
import com.qfleaf.aggregatesearch.common.PageData;
import com.qfleaf.aggregatesearch.model.SearchRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SearchServiceAdapter implements SearchService {
    @Override
    public PageData search(SearchRequest searchRequest) {
        return searchServicesMap.get(searchRequest.getCategory()).search(searchRequest);
    }

    private final Map<String, SearchService> searchServicesMap = new ConcurrentHashMap<>();

    public SearchServiceAdapter(List<SearchService> searchServices) {
        searchServices.forEach(searchService -> {
            SearchAdapter annotation = searchService.getClass().getAnnotation(SearchAdapter.class);
            if (annotation != null) {
                searchServicesMap.put(annotation.value(), searchService);
            }
        });
    }
}
