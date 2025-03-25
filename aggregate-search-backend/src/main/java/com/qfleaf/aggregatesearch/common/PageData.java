package com.qfleaf.aggregatesearch.common;

import lombok.Data;

@Data
public class PageData {
    private Object records;
    private Long total;
    private Long pages;
    private Long current;
    private Integer size;
}
