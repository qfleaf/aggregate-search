package com.qfleaf.aggregatesearch.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDateTime;

@Entity
@Data
@Document(indexName = "articles")
public class Article {
    @org.springframework.data.annotation.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;
    @MultiField(
            mainField = @Field(type = FieldType.Text, analyzer = "ik_max_word"),
            otherFields = {
                    @InnerField(suffix = "keyword", type = FieldType.Keyword)
            }
    )
    private String title;
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String content;
    @Field(type = FieldType.Keyword)
    private String author;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    @CreationTimestamp
    private LocalDateTime createdTime;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    @UpdateTimestamp
    private LocalDateTime updatedTime;
}
