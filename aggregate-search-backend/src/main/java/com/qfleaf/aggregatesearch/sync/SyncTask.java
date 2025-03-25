package com.qfleaf.aggregatesearch.sync;

import com.qfleaf.aggregatesearch.sync.service.ArticleSyncService;
import com.qfleaf.aggregatesearch.sync.service.QASyncService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SyncTask {
    private final ArticleSyncService articleSyncService;
    private final QASyncService qaSyncService;

    public SyncTask(ArticleSyncService articleSyncService, QASyncService qaSyncService) {
        this.articleSyncService = articleSyncService;
        this.qaSyncService = qaSyncService;
    }

    @Scheduled(fixedRate = 60 * 1000)
    public void sync() {
        articleSyncService.sync();
        qaSyncService.sync();
    }
}
