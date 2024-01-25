package br.com.fiap.tech.challenge.purchase.driven.mysql.mapping;

import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.purchase.application.dto.FullCustomerDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.PaymentDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.driven.mysql.model.CustomerEntity;
import br.com.fiap.tech.challenge.purchase.driven.mysql.model.PurchaseEntity;
import br.com.fiap.tech.challenge.purchase.driven.mysql.repository.CustomerEntityRepository;
import br.com.fiap.tech.challenge.purchase.driven.mysql.repository.PaymentEntityRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import static br.com.fiap.tech.challenge.purchase.enterprise.error.ApplicationError.PAYMENT_NOT_FOUND;
import static java.util.Objects.isNull;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, uses = { DBCustomerMapper.class, DBPurchaseItemMapper.class })
public abstract class DBPurchaseMapper {

    @Autowired
    protected PaymentEntityRepository paymentRepository;

    @Autowired
    private CustomerEntityRepository customerRepository;

    @Autowired
    private DBPaymentMapper paymentMapper;

    @Autowired
    private DBCustomerMapper customerMapper;

    @Mapping(target = "uuid", source = "id")
    @Mapping(target = "customer", source = "customer", qualifiedByName = "getCustomerEntity")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    public abstract PurchaseEntity toEntity(PurchaseDTO source);

    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "payment", source = "source", qualifiedByName = "getPayment")
    public abstract PurchaseDTO toDTO(PurchaseEntity source);


    @Named("getPayment")
    PaymentDTO getPayment(PurchaseEntity purchase) {
        var entity = paymentRepository.findByPurchaseUuid(purchase.getUuid())
                .orElseThrow(() -> new ApplicationException(PAYMENT_NOT_FOUND));

        return paymentMapper.toDTO(entity);
    }

    @Named("getCustomerEntity")
    CustomerEntity getCustomerEntity(FullCustomerDTO source) {
        if (isNull(source)) return null;

        return customerRepository.findByDocument(source.getDocument())
                .orElseGet(() -> customerRepository.save(customerMapper.toEntity(source)));
    }
}
