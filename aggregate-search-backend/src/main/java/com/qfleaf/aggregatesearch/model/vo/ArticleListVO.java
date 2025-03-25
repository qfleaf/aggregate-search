package com.qfleaf.aggregatesearch.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleListVO {
    private Long articleId;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdTime;
}
