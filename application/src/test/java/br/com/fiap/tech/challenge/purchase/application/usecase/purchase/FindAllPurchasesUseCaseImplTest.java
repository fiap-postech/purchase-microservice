package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseReaderGateway;
import br.com.fiap.tech.challenge.purchase.application.util.Page;
import br.com.fiap.tech.challenge.purchase.application.util.ResponseList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static br.com.fiap.tech.challenge.purchase.application.fixture.Fixture.create;
import static br.com.fiap.tech.challenge.purchase.application.fixture.PurchaseFixture.purchaseModel;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllPurchasesUseCaseImplTest {

    @Mock
    private PurchaseReaderGateway gateway;

    private FindAllPurchasesUseCase useCase;

    @BeforeEach
    void setup() {
        useCase = new FindAllPurchasesUseCaseImpl(gateway);
    }

    @Test
    void shouldGetAnEmptyResponseToListAllPurchaseAndNotExistsAny() {

        when(gateway.readAll(any(Page.class))).thenReturn(ResponseList.empty());

        var response = useCase.list(new Page(0,10));

        assertThat(response.size()).isZero();
        assertThat(response.numberOfElements()).isZero();
        assertThat(response.number()).isZero();
        assertThat(response.totalElements()).isZero();
        assertThat(response.content()).isNotNull().isEmpty();

        verify(gateway).readAll(any(Page.class));
    }

    @Test
    void shouldGetSuccessWhenTryToGetAllPurchases() {
        var purchase = create(purchaseModel());

        var responseList = new ResponseList<>(
                0,
                10,
                1,
                1L,
                List.of(purchase)
        );


        when(gateway.readAll(any(Page.class))).thenReturn(responseList);

        var response = useCase.list(new Page(0, 10));

        assertThat(response.size()).isEqualTo(10);
        assertThat(response.numberOfElements()).isEqualTo(1);
        assertThat(response.number()).isZero();
        assertThat(response.totalElements()).isEqualTo(1);
        assertThat(response.content()).isNotNull().isNotEmpty().contains(purchase);

        verify(gateway).readAll(any(Page.class));
    }

}
