package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.purchase.application.fixture.Fixture;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseWriterGateway;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

import static br.com.fiap.tech.challenge.purchase.application.fixture.PurchaseFixture.purchaseModel;
import static br.com.fiap.tech.challenge.purchase.enterprise.error.ApplicationError.PURCHASE_NOT_FOUND_BY_UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdatePurchaseStatusUseCaseImplTest {

    @Mock
    private FindPurchaseByUUIDUseCase findPurchaseUseCase;

    @Mock
    private PurchaseWriterGateway gateway;

    private UpdatePurchaseStatusUseCase useCase;

    @BeforeEach
    void setup() {
        useCase = new UpdatePurchaseStatusUseCaseImpl(findPurchaseUseCase, gateway);
    }

    @Test
    void shouldGetAnExceptionWhenTryToUpdatePurchaseThatNotExists() {
        var id = UUID.randomUUID();
        var exception = new ApplicationException(PURCHASE_NOT_FOUND_BY_UUID, id);

        when(findPurchaseUseCase.get(id)).thenThrow(exception);

        assertThatThrownBy(() -> useCase.update(id, PurchaseStatus.WAITING_MAKE))
                .isInstanceOf(ApplicationException.class)
                .hasMessage(exception.getMessage());

        verify(findPurchaseUseCase).get(id);
        verify(gateway, never()).write(any(Purchase.class));
    }


    @ParameterizedTest
    @MethodSource("possibleStatus")
    void testUpdateThroughPurchaseObject(PurchaseStatus status) {
        var purchase = Fixture.create(purchaseModel());

        when(gateway.write(any(Purchase.class)))
                .thenAnswer(i -> Arrays.stream(i.getArguments()).findFirst().orElseThrow());

        var response = useCase.update(purchase, status);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(status);
        assertThat(response.date()).isEqualTo(purchase.date());
        assertThat(response.customer()).isEqualTo(purchase.customer());
        assertThat(response.items()).isEqualTo(purchase.items());
        assertThat(response.payment()).isEqualTo(purchase.payment());

        verify(gateway).write(any(Purchase.class));
        verify(findPurchaseUseCase, never()).get(any(UUID.class));
    }

    @ParameterizedTest
    @MethodSource("possibleStatus")
    void testUpdateThroughUUID(PurchaseStatus status) {

        var purchase = Fixture.create(purchaseModel());
        var uuid = purchase.uuid();

        when(findPurchaseUseCase.get(uuid)).thenReturn(purchase);
        when(gateway.write(any(Purchase.class)))
                .thenAnswer(i -> Arrays.stream(i.getArguments()).findFirst().orElseThrow());

        var response = useCase.update(uuid, status);

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(status);
        assertThat(response.date()).isEqualTo(purchase.date());
        assertThat(response.customer()).isEqualTo(purchase.customer());
        assertThat(response.items()).isEqualTo(purchase.items());
        assertThat(response.payment()).isEqualTo(purchase.payment());

        verify(gateway).write(any(Purchase.class));
        verify(findPurchaseUseCase).get(any(UUID.class));
    }

    static Stream<PurchaseStatus> possibleStatus() {
        return Stream.of(
                PurchaseStatus.WAITING_MAKE,
                PurchaseStatus.MAKING,
                PurchaseStatus.MADE,
                PurchaseStatus.DELIVERED
        );
    }



}
