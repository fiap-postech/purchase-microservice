package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.purchase.application.dto.CreatePurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseReaderGateway;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseWriterGateway;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;
import org.instancio.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.stream.Stream;

import static br.com.fiap.tech.challenge.purchase.application.fixture.CreatePurchaseDTOFixture.comboAndSandwichAndBeverageCreatePurchaseDTO;
import static br.com.fiap.tech.challenge.purchase.application.fixture.CreatePurchaseDTOFixture.sandwichAndBeverageCreatePurchaseDTO;
import static br.com.fiap.tech.challenge.purchase.application.fixture.CreatePurchaseDTOFixture.singleBeverageCreatePurchaseDTO;
import static br.com.fiap.tech.challenge.purchase.application.fixture.CreatePurchaseDTOFixture.singleSandwichCreatePurchaseDTO;
import static br.com.fiap.tech.challenge.purchase.application.fixture.CreatePurchaseDTOFixture.singleSideDishCreatePurchaseDTO;
import static br.com.fiap.tech.challenge.purchase.application.fixture.Fixture.create;
import static br.com.fiap.tech.challenge.purchase.enterprise.error.ApplicationError.PURCHASE_DUPLICATED;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePurchaseUseCaseImplTest {

    @Mock
    private PurchaseWriterGateway writerGateway;

    @Mock
    private PurchaseReaderGateway readerGateway;

    private CreatePurchaseUseCase useCase;

    @BeforeEach
    void setup() {
        useCase = new CreatePurchaseUseCaseImpl(readerGateway, writerGateway);
    }

    @ParameterizedTest
    @MethodSource("createPurchaseRequests")
    void shouldCreatePurchase(Model<CreatePurchaseDTO> model) {
        var request = create(model);

        when(readerGateway.existsByExternalId(any(String.class))).thenReturn(false);

        when(writerGateway.write(any(Purchase.class)))
                .thenAnswer(i -> Arrays.stream(i.getArguments()).findFirst().orElseThrow());

        useCase.create(request);

        verify(readerGateway).existsByExternalId(any(String.class));
        verify(writerGateway).write(any(Purchase.class));
    }

    @ParameterizedTest
    @MethodSource("createPurchaseRequests")
    void shouldNotCreatePurchase(Model<CreatePurchaseDTO> model) {
        var request = create(model);
        var exception = new ApplicationException(PURCHASE_DUPLICATED);

        when(readerGateway.existsByExternalId(any(String.class))).thenReturn(true);

        assertThatThrownBy(() -> useCase.create(request))
                .isInstanceOf(ApplicationException.class)
                .hasMessage(exception.getMessage());

        verify(readerGateway).existsByExternalId(any(String.class));
        verify(writerGateway, never()).write(any(Purchase.class));
    }

    static Stream<Model<CreatePurchaseDTO>> createPurchaseRequests() {
        return Stream.of(
                sandwichAndBeverageCreatePurchaseDTO(),
                singleBeverageCreatePurchaseDTO(),
                singleSandwichCreatePurchaseDTO(),
                comboAndSandwichAndBeverageCreatePurchaseDTO(),
                singleSideDishCreatePurchaseDTO()
        );
    }
}
