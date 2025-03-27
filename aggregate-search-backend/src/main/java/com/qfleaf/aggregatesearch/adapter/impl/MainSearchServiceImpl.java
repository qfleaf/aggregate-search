package com.qfleaf.aggregatesearch.adapter.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.qfleaf.aggregatesearch.adapter.SearchService;
import com.qfleaf.aggregatesearch.adapter.anno.SearchAdapter;
import com.qfleaf.aggregatesearch.common.PageData;
import com.qfleaf.aggregatesearch.model.SearchRequest;
import com.qfleaf.aggregatesearch.model.vo.MainContentVO;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.data.elasticsearch.client.elc.NativeQuery.builder;

@SearchAdapter("main")
public class MainSearchServiceImpl implements SearchService {
    private final ElasticsearchTemplate elasticsearchTemplate;

    public MainSearchServiceImpl(ElasticsearchTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Override
    public PageData search(SearchRequest searchRequest) {
        Integer current = searchRequest.getCurrent();
        Integer size = searchRequest.getSize();
        PageRequest pageRequest = PageRequest.of(current - 1, size);
        String keyword = searchRequest.getKeyword();
        Query query;
        if (keyword != null) {
            query = builder()
                    .withQuery(q -> q.multiMatch(m -> m
                            .fields("title", "content", "question", "answer")
                            .query(keyword)))
                    .build()
                    .getQuery();
        } else {
            query = builder()
                    .withQuery(q -> q.matchAll(m -> m))
                    .build()
                    .getQuery();
        }

        // 查询文章索引
        List<MainContentVO> combinedResults = new ArrayList<>();

        combinedResults.addAll(searchByIndex("articles", query, "article"));
        combinedResults.addAll(searchByIndex("qas", query, "qa"));

        // 合并数据并按 createdTime 倒序排序
        combinedResults.sort(Comparator.comparing(MainContentVO::getCreatedTime).reversed());

        // 手动分页
        int start = (current - 1) * size;
        int end = Math.min(start + size, combinedResults.size());
        List<MainContentVO> pageContent = combinedResults.subList(start, end);

        PageImpl<MainContentVO> page = new PageImpl<>(pageContent, pageRequest, combinedResults.size());
        return new PageData(page);
    }

    private List<MainContentVO> searchByIndex(String index, Query query, String category) {
        NativeQuery nativeQuery = builder().withQuery(query).build();
        var hits = elasticsearchTemplate.search(nativeQuery, Map.class, IndexCoordinates.of(index));
        return hits.stream().map(hit -> mapToContentVO(hit, category)).collect(Collectors.toList());
    }

    @SuppressWarnings("rawtypes")
    private MainContentVO mapToContentVO(SearchHit<Map> hit, String category) {
        MainContentVO vo = new MainContentVO();
        vo.setId(hit.getId());
        vo.setCategory(category);

        // 处理字段映射，确保兼容多索引
        Map source = hit.getContent();

        // 文章（article_index）
        if ("article".equals(category)) {
            vo.setTitle((String) source.get("title"));
            vo.setContent((String) source.get("content"));
        }
        // 问答（qa_index）
        else if ("qa".equals(category)) {
            vo.setTitle((String) source.get("question"));
            vo.setContent((String) source.get("answer"));
        }

        String createdTimeStr = (String) source.get("createdTime");
        // 定义日期时间格式化器，假设日期格式是 'yyyy-MM-dd'T'HH:mm:ss'
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        // 使用 formatter 解析字符串，转换为 LocalDateTime
        LocalDateTime createdTime = LocalDateTime.parse(createdTimeStr, formatter);
        // 处理时间字段
        vo.setCreatedTime(createdTime);

        return vo;
    }
}
