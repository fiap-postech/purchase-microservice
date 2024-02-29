package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.purchase.application.fixture.Fixture;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseWriterGateway;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.CREATED;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.DELIVERED;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.MADE;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.MAKING;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.PAID_ERROR;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.PAID_SUCCESS;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.WAITING_MAKE;
import static br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus.WAITING_PAYMENT;
import static br.com.fiap.tech.challenge.purchase.enterprise.error.ApplicationError.INVALID_PURCHASE_STATUS_CHANGE;
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

        assertThatThrownBy(() -> useCase.update(id, WAITING_MAKE))
                .isInstanceOf(ApplicationException.class)
                .hasMessage(exception.getMessage());

        verify(findPurchaseUseCase).get(id);
        verify(gateway, never()).write(any(Purchase.class));
    }


    @ParameterizedTest
    @MethodSource("allowedStatusMoving")
    void testUpdateThroughPurchaseObject(StatusMoving statusMoving) {
        var purchase = Fixture.create(purchaseModel(statusMoving.getFrom()));

        when(gateway.write(any(Purchase.class)))
                .thenAnswer(i -> Arrays.stream(i.getArguments()).findFirst().orElseThrow());

        var response = useCase.update(purchase, statusMoving.getTo());

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(statusMoving.getTo());
        assertThat(response.date()).isEqualTo(purchase.date());
        assertThat(response.customer()).isEqualTo(purchase.customer());
        assertThat(response.items()).isEqualTo(purchase.items());
        assertThat(response.payment()).isEqualTo(purchase.payment());

        verify(gateway).write(any(Purchase.class));
        verify(findPurchaseUseCase, never()).get(any(UUID.class));
    }

    @ParameterizedTest
    @MethodSource("allowedStatusMoving")
    void testUpdateThroughUUID(StatusMoving statusMoving) {

        var purchase = Fixture.create(purchaseModel(statusMoving.getFrom()));

        var uuid = purchase.uuid();

        when(findPurchaseUseCase.get(uuid)).thenReturn(purchase);
        when(gateway.write(any(Purchase.class)))
                .thenAnswer(i -> Arrays.stream(i.getArguments()).findFirst().orElseThrow());

        var response = useCase.update(uuid, statusMoving.getTo());

        assertThat(response).isNotNull();
        assertThat(response.status()).isEqualTo(statusMoving.getTo());
        assertThat(response.date()).isEqualTo(purchase.date());
        assertThat(response.customer()).isEqualTo(purchase.customer());
        assertThat(response.items()).isEqualTo(purchase.items());
        assertThat(response.payment()).isEqualTo(purchase.payment());

        verify(gateway).write(any(Purchase.class));
        verify(findPurchaseUseCase).get(any(UUID.class));
    }

    @ParameterizedTest
    @MethodSource("notAllowedStatusMoving")
    void testNotAllowedUpdateThroughUUID(StatusMoving statusMoving) {

        var purchase = Fixture.create(purchaseModel(statusMoving.getFrom()));
        var exception = new ApplicationException(
                INVALID_PURCHASE_STATUS_CHANGE,
                statusMoving.getFrom(),
                statusMoving.getTo()
        );

        var uuid = purchase.uuid();

        when(findPurchaseUseCase.get(uuid)).thenReturn(purchase);

        var to = statusMoving.getTo();

        assertThatThrownBy(() -> useCase.update(uuid, to))
                .isInstanceOf(ApplicationException.class)
                .hasMessage(exception.getMessage());


        verify(gateway, never()).write(any(Purchase.class));
        verify(findPurchaseUseCase).get(any(UUID.class));
    }

    static Stream<StatusMoving> allowedStatusMoving() {
        return Stream.of(
                new StatusMoving(CREATED, WAITING_PAYMENT),
                new StatusMoving(WAITING_PAYMENT, PAID_SUCCESS),
                new StatusMoving(WAITING_PAYMENT, PAID_ERROR),
                new StatusMoving(PAID_SUCCESS, WAITING_MAKE),
                new StatusMoving(WAITING_MAKE, MAKING),
                new StatusMoving(MAKING, MADE),
                new StatusMoving(MADE, DELIVERED)
        );
    }

    static Stream<StatusMoving> notAllowedStatusMoving() {
        return Stream.of(
                new StatusMoving(CREATED, CREATED),
                new StatusMoving(CREATED, WAITING_MAKE),
                new StatusMoving(CREATED, MAKING),
                new StatusMoving(CREATED, MADE),
                new StatusMoving(CREATED, DELIVERED),
                new StatusMoving(CREATED, PAID_ERROR),
                new StatusMoving(CREATED, PAID_SUCCESS),
                new StatusMoving(PAID_SUCCESS, MAKING),
                new StatusMoving(PAID_SUCCESS, WAITING_PAYMENT),
                new StatusMoving(PAID_SUCCESS, MADE),
                new StatusMoving(PAID_SUCCESS, DELIVERED),
                new StatusMoving(PAID_SUCCESS, CREATED),
                new StatusMoving(PAID_SUCCESS, PAID_SUCCESS),
                new StatusMoving(PAID_ERROR, CREATED),
                new StatusMoving(PAID_ERROR, WAITING_PAYMENT),
                new StatusMoving(PAID_ERROR, PAID_SUCCESS),
                new StatusMoving(PAID_ERROR, WAITING_MAKE),
                new StatusMoving(PAID_ERROR, MAKING),
                new StatusMoving(PAID_ERROR, MADE),
                new StatusMoving(PAID_ERROR, DELIVERED),
                new StatusMoving(WAITING_MAKE, WAITING_MAKE),
                new StatusMoving(WAITING_MAKE, WAITING_PAYMENT),
                new StatusMoving(WAITING_MAKE, CREATED),
                new StatusMoving(WAITING_MAKE, MADE),
                new StatusMoving(WAITING_MAKE, DELIVERED),
                new StatusMoving(WAITING_MAKE, PAID_SUCCESS),
                new StatusMoving(WAITING_MAKE, PAID_ERROR),
                new StatusMoving(MAKING, MAKING),
                new StatusMoving(MAKING, WAITING_PAYMENT),
                new StatusMoving(MAKING, CREATED),
                new StatusMoving(MAKING, PAID_SUCCESS),
                new StatusMoving(MAKING, PAID_ERROR),
                new StatusMoving(MAKING, DELIVERED),
                new StatusMoving(MADE, MADE),
                new StatusMoving(MADE, WAITING_PAYMENT),
                new StatusMoving(MADE, CREATED),
                new StatusMoving(MADE, PAID_SUCCESS),
                new StatusMoving(MADE, PAID_ERROR),
                new StatusMoving(MADE, WAITING_MAKE),
                new StatusMoving(DELIVERED, CREATED),
                new StatusMoving(DELIVERED, WAITING_PAYMENT),
                new StatusMoving(DELIVERED, WAITING_MAKE),
                new StatusMoving(DELIVERED, PAID_SUCCESS),
                new StatusMoving(DELIVERED, PAID_ERROR),
                new StatusMoving(DELIVERED, MAKING),
                new StatusMoving(DELIVERED, MADE),
                new StatusMoving(WAITING_PAYMENT, DELIVERED),
                new StatusMoving(WAITING_PAYMENT, WAITING_PAYMENT),
                new StatusMoving(WAITING_PAYMENT, WAITING_MAKE),
                new StatusMoving(WAITING_PAYMENT, CREATED),
                new StatusMoving(WAITING_PAYMENT, MAKING),
                new StatusMoving(WAITING_PAYMENT, MADE),
                new StatusMoving(WAITING_PAYMENT, DELIVERED)
        );
    }

    @RequiredArgsConstructor
    @Getter
    private static class StatusMoving {
        private final PurchaseStatus from;
        private final PurchaseStatus to;
    }


}
