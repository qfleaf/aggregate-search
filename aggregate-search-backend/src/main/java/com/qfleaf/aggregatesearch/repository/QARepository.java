package com.qfleaf.aggregatesearch.repository;

import com.qfleaf.aggregatesearch.model.entity.QA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface QARepository extends JpaRepository<QA, Long> {
    List<QA> findByUpdatedTimeAfter(LocalDateTime lastSyncTime);
}
