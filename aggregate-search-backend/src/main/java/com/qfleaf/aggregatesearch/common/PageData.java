package com.qfleaf.aggregatesearch.common;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class PageData {
    private Object records;
    private Long total;
    private int pages;
    private int current;
    private int size;

    public PageData(Page<?> page) {
        this.records = page.getContent();
        this.total = page.getTotalElements();
        this.pages = page.getTotalPages();
        this.current = page.getNumber();
        this.size = page.getSize();
    }
}
