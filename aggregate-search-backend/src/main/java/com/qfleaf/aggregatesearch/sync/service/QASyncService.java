package com.qfleaf.aggregatesearch.sync.service;

import com.qfleaf.aggregatesearch.esdao.QAEsDAO;
import com.qfleaf.aggregatesearch.model.entity.QA;
import com.qfleaf.aggregatesearch.repository.QARepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class QASyncService {
    private final QARepository qaRepository;
    private final QAEsDAO qaEsDAO;

    private LocalDateTime lastSyncTime;

    public QASyncService(QARepository qaRepository, QAEsDAO articleEsDAO) {
        this.qaRepository = qaRepository;
        this.qaEsDAO = articleEsDAO;
    }

    public void sync() {
        if (lastSyncTime == null) {
            log.info("First sync");
            List<QA> all = qaRepository.findAll();
            qaEsDAO.saveAll(all);
            lastSyncTime = LocalDateTime.now();
            log.info("Finished at: {}", lastSyncTime);
            return;
        }
        log.info("Sync started...");
        List<QA> data = qaRepository.findByUpdatedTimeAfter(lastSyncTime);
        if (data.isEmpty()) {
            log.info("No new data found.");
            return;
        }
        log.info("New data found ({}). Ready to sync...",data.size());
        qaEsDAO.saveAll(data);
        lastSyncTime = LocalDateTime.now();
        log.info("Sync finished.");
    }
}
