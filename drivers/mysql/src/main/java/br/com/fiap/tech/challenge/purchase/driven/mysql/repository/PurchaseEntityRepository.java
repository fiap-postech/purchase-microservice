package br.com.fiap.tech.challenge.purchase.driven.mysql.repository;

import br.com.fiap.tech.challenge.purchase.driven.mysql.model.PurchaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PurchaseEntityRepository extends JpaRepository<PurchaseEntity, Long> {

    Optional<PurchaseEntity> findByUuid(String uuid);

    @Query("select p " +
             "from PurchaseEntity p " +
            "where p.status <> 'FINISHED' " +
            "order by FIELD(p.status, 'DELIVERED','MADE','MAKING','WAITING_MAKE','PAID')")
    Page<PurchaseEntity> findPurchaseQueue(Pageable page);

    boolean existsByExternalId(String id);
}
