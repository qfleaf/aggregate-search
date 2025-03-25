package com.qfleaf.aggregatesearch.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QAListVO {
    private Long qaId;
    private String question;
    private String description;
    private String author;
    private LocalDateTime createdTime;
}
