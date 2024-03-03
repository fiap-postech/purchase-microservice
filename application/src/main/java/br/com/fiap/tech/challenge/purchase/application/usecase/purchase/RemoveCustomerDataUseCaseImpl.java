package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.application.dto.RemoveCustomerDataDTO;
import br.com.fiap.tech.challenge.purchase.application.dto.RequestRemoveCustomerDataDTO;
import br.com.fiap.tech.challenge.purchase.application.gateway.CustomerReaderGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.CustomerWriterGateway;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Customer;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.DataRemovalStatus;
import br.com.fiap.tech.challenge.purchase.enterprise.valueobject.Document;
import br.com.fiap.tech.challenge.purchase.enterprise.valueobject.EmailRegistration;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class RemoveCustomerDataUseCaseImpl implements RemoveCustomerDataUseCase {

    private final CustomerReaderGateway customerReaderGateway;
    private final CustomerWriterGateway customerWriterGateway;

    @Override
    public RemoveCustomerDataDTO remove(RequestRemoveCustomerDataDTO dto) {
        return customerReaderGateway.read(dto.getDocument())
                .map(this::anonymize)
                .map(this::save)
                .map(customer -> buildResponse(dto))
                .orElseGet(() -> buildResponse(dto));
    }

    private Customer anonymize(Customer customer) {
        return customer.toBuilder()
                .enabled(false)
                .document(Document.of(null))
                .email(EmailRegistration.of("consumidor.removido@techchallenge.com.br"))
                .name("CONSUMIDOR REMOVIDO")
                .build();
    }

    private Customer save(Customer customer) {
        return customerWriterGateway.write(customer);
    }

    private RemoveCustomerDataDTO buildResponse(RequestRemoveCustomerDataDTO dto) {
        return new RemoveCustomerDataDTO()
                .setApplication("purchase-service")
                .setId(dto.getId())
                .setStatus(DataRemovalStatus.FINISHED);
    }
}
