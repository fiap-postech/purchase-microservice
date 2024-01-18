package br.com.fiap.tech.challenge.purchase.driven.mysql.repository;

import br.com.fiap.tech.challenge.purchase.driven.mysql.model.ProductEntity;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {

    boolean existsByUuid(String uuid);

    Optional<ProductEntity> findByUuid(String uuid);

    Page<ProductEntity> findAllByEnabledTrue(Pageable pageable);

    Page<ProductEntity> findAllByEnabledTrueAndCategory(ProductCategory category, Pageable pageable);
}
