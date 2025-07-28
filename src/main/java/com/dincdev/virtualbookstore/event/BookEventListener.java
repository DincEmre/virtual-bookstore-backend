package com.dincdev.virtualbookstore.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class BookEventListener {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(BookSyncEvent event) {
        log.info("{} kitap Elasticsearch'e başarıyla kaydedildi ve senkronizasyon işlemi tamamlandı.", event.getSize());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void afterRollback(BookSyncEvent event) {
        log.warn("Rollback oldu: {}", event.getMessage());
    }
}
