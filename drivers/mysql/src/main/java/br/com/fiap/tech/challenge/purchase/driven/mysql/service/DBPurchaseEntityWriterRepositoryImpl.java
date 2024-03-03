package br.com.fiap.tech.challenge.purchase.driven.mysql.service;

import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseWriterRepository;
import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.driven.mysql.mapping.DBPaymentMapper;
import br.com.fiap.tech.challenge.purchase.driven.mysql.mapping.DBPurchaseMapper;
import br.com.fiap.tech.challenge.purchase.driven.mysql.model.PurchaseEntity;
import br.com.fiap.tech.challenge.purchase.driven.mysql.repository.PurchaseEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class DBPurchaseEntityWriterRepositoryImpl implements PurchaseWriterRepository {

    private final DBPurchaseMapper dbPurchaseMapper;
    private final DBPaymentMapper dbPaymentMapper;
    private final PurchaseEntityRepository purchaseRepository;

    @Override
    public PurchaseDTO write(PurchaseDTO purchase) {
        var purchaseEntity = getPurchaseEntity(purchase);

        if (isNull(purchaseEntity.getPayment()) && nonNull(purchase.getPayment())) {
            purchaseEntity.setPayment(dbPaymentMapper.toEntity(purchase.getPayment()));
        }

        return dbPurchaseMapper.toDTO(purchaseRepository.save(purchaseEntity));
    }

    private PurchaseEntity getPurchaseEntity(PurchaseDTO purchase) {
        return purchaseRepository.findByUuid(purchase.getId())
                .map(updateFields(purchase))
                .orElseGet(() -> dbPurchaseMapper.toEntity(purchase));
    }

    private Function<PurchaseEntity, PurchaseEntity> updateFields(PurchaseDTO dto) {
        return entity -> {
            entity.setStatus(dto.getStatus());

            if (dto.getPayment().getStatus() != entity.getPayment().getStatus()) {
                entity.getPayment().setStatus(dto.getPayment().getStatus());
            }


            return entity;
        };
    }
}
