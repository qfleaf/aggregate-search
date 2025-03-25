package com.qfleaf.aggregatesearch.controller;

import com.qfleaf.aggregatesearch.adapter.SearchServiceAdapter;
import com.qfleaf.aggregatesearch.common.PageData;
import com.qfleaf.aggregatesearch.model.SearchRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
@Tag(name = "搜索")
public class SearchController {

    private final SearchServiceAdapter searchServiceAdapter;

    public SearchController(SearchServiceAdapter searchServiceAdapter) {
        this.searchServiceAdapter = searchServiceAdapter;
    }

    @GetMapping
    public PageData search(SearchRequest searchRequest) {
        log.debug("params: {}", searchRequest);
        return searchServiceAdapter.search(searchRequest);
    }
}
