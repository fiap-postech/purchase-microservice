package br.com.fiap.tech.challenge.purchase.driven.mysql.repository;

import br.com.fiap.tech.challenge.purchase.driven.mysql.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByUuid(String uuid);
}
