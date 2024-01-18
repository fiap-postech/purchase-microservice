package br.com.fiap.tech.challenge.purchase.driven.mysql.service;

import br.com.fiap.tech.challenge.purchase.driven.mysql.mapping.DBPaymentMapper;
import br.com.fiap.tech.challenge.purchase.driven.mysql.mapping.DBPurchaseMapper;
import br.com.fiap.tech.challenge.purchase.driven.mysql.model.PaymentEntity;
import br.com.fiap.tech.challenge.purchase.driven.mysql.model.PurchaseEntity;
import br.com.fiap.tech.challenge.purchase.driven.mysql.repository.PaymentEntityRepository;
import br.com.fiap.tech.challenge.purchase.driven.mysql.repository.PurchaseEntityRepository;
import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.adapter.repository.PurchaseWriterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DBPurchaseEntityWriterRepositoryImpl implements PurchaseWriterRepository {

    private final DBPurchaseMapper dbPurchaseMapper;
    private final DBPaymentMapper dbPaymentMapper;
    private final PurchaseEntityRepository purchaseRepository;
    private final PaymentEntityRepository paymentRepository;

    @Override
    @Transactional
    public PurchaseDTO write(PurchaseDTO purchase) {
        var purchaseEntity = savePurchase(purchase);

        var paymentEntity = getPaymentEntity(purchase);
        paymentEntity.setPurchase(purchaseEntity);

        paymentRepository.save(paymentEntity);

        return dbPurchaseMapper.toDTO(purchaseEntity);
    }

    private PaymentEntity getPaymentEntity(PurchaseDTO purchase) {
        var paymentEntity = dbPaymentMapper.toEntity(purchase.getPayment());
        var paymentEntityOpt = paymentRepository.findByPurchaseUuid(purchase.getId());
        paymentEntityOpt.ifPresent(entity -> BeanUtils.copyProperties(entity, paymentEntity, "status"));
        return paymentEntity;
    }

    private PurchaseEntity savePurchase(PurchaseDTO purchase) {
        var purchaseEntity = getPurchaseEntity(purchase);
        return purchaseRepository.save(purchaseEntity);
    }

    private PurchaseEntity getPurchaseEntity(PurchaseDTO purchase) {
        var purchaseEntity = dbPurchaseMapper.toEntity(purchase);

        purchaseRepository.findByUuid(purchase.getId())
                .ifPresent(entity -> BeanUtils.copyProperties(entity, purchaseEntity, "status"));

        return purchaseEntity;
    }
}
