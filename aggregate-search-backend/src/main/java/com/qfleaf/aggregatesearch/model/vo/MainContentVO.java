package com.qfleaf.aggregatesearch.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MainContentVO {
    private String id;
    private String title; // 文章标题或问答问题
    private String content; // 文章内容或问答答案
    private String category; // article or qa
    private LocalDateTime createdTime;
}
