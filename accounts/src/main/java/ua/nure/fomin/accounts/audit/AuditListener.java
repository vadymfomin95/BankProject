package ua.nure.fomin.accounts.audit;

import jakarta.persistence.PrePersist;
import ua.nure.fomin.accounts.entity.BaseEntity;

public class AuditListener {

    @PrePersist
    void onCreate(BaseEntity entity) {
        entity.setCreatedAt(java.time.LocalDateTime.now());
        entity.setCreatedBy("system");
    }
}
