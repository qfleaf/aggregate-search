package com.qfleaf.aggregatesearch.sync.service;

import com.qfleaf.aggregatesearch.esdao.ArticleEsDAO;
import com.qfleaf.aggregatesearch.model.entity.Article;
import com.qfleaf.aggregatesearch.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ArticleSyncService {
    private final ArticleRepository articleRepository;
    private final ArticleEsDAO articleEsDAO;

    private LocalDateTime lastSyncTime;

    public ArticleSyncService(ArticleRepository articleRepository, ArticleEsDAO articleEsDAO) {
        this.articleRepository = articleRepository;
        this.articleEsDAO = articleEsDAO;
    }

    public void sync() {
        if (lastSyncTime == null) {
            log.info("First sync");
            List<Article> all = articleRepository.findAll();
            articleEsDAO.saveAll(all);
            lastSyncTime = LocalDateTime.now();
            log.info("Finished at: {}", lastSyncTime);
            return;
        }
        log.info("Sync started...");
        List<Article> data = articleRepository.findByUpdatedTimeAfter(lastSyncTime);
        if (data.isEmpty()) {
            log.info("No new data found.");
            return;
        }
        log.info("New data found ({}). Ready to sync...",data.size());
        articleEsDAO.saveAll(data);
        lastSyncTime = LocalDateTime.now();
        log.info("Sync finished.");
    }
}
