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

@Data
@Entity
@Document(indexName = "qas")
public class QA {
    @org.springframework.data.annotation.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qaId;
    @MultiField(
            mainField = @Field(type = FieldType.Text, analyzer = "ik_max_word"),
            otherFields = {
                    @InnerField(suffix = "keyword", type = FieldType.Keyword)
            }
    )
    private String question;
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String description;
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String answer;
    @Field(type = FieldType.Keyword)
    private String author;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    @CreationTimestamp
    private LocalDateTime createdTime;
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second)
    @UpdateTimestamp
    private LocalDateTime updatedTime;
}