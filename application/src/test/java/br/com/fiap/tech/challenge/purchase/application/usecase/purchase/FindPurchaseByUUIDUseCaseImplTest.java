package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.purchase.application.fixture.Fixture;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseReaderGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static br.com.fiap.tech.challenge.purchase.application.fixture.PurchaseFixture.purchaseModel;
import static br.com.fiap.tech.challenge.purchase.enterprise.error.ApplicationError.PURCHASE_NOT_FOUND_BY_UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindPurchaseByUUIDUseCaseImplTest {

    @Mock
    private PurchaseReaderGateway gateway;

    private FindPurchaseByUUIDUseCase useCase;

    @BeforeEach
    void setup() {
        useCase = new FindPurchaseByUUIDUseCaseImpl(gateway);
    }

    @Test
    void shouldGetAnExceptionWhenTryToGetAPurchaseThatNotExists() {
        var id = UUID.randomUUID();
        var exception = new ApplicationException(PURCHASE_NOT_FOUND_BY_UUID, id);

        when(gateway.readById(id)).thenThrow(exception);

        assertThatThrownBy(() -> useCase.get(id))
                .isInstanceOf(ApplicationException.class)
                .hasMessage(exception.getMessage());

        verify(gateway).readById(id);
    }

    @Test
    void shouldGetSuccessWhenTryToGetAPurchase() {
        var purchase = Fixture.create(purchaseModel());
        var id = purchase.uuid();

        when(gateway.readById(id)).thenReturn(purchase);

        var response = useCase.get(id);

        assertThat(response).isNotNull().isEqualTo(purchase);

        verify(gateway).readById(id);
    }

}
