package com.qfleaf.aggregatesearch.model;

import lombok.Data;

@Data
public class SearchRequest {
    private String keyword;
    private String category;
    private Integer current;
    private Integer size;
}
