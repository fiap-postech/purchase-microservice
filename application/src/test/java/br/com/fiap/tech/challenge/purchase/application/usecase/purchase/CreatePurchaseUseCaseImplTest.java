package br.com.fiap.tech.challenge.purchase.application.usecase.purchase;

import br.com.fiap.tech.challenge.purchase.application.dto.CreatePurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.gateway.PurchaseCreatedGateway;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePurchaseUseCaseImplTest {

    @Mock
    private PurchaseWriterGateway writerGateway;

    @Mock
    private PurchaseCreatedGateway createdGateway;

    private CreatePurchaseUseCase useCase;

    @BeforeEach
    void setup() {
        useCase = new CreatePurchaseUseCaseImpl(writerGateway, createdGateway);
    }

    @ParameterizedTest
    @MethodSource("createPurchaseRequests")
    void shouldCreatePurchase(Model<CreatePurchaseDTO> model) {
        var request = create(model);

        when(writerGateway.write(any(Purchase.class)))
                .thenAnswer(i -> Arrays.stream(i.getArguments()).findFirst().orElseThrow());

        useCase.create(request);

        verify(createdGateway).notify(any(Purchase.class));
        verify(writerGateway).write(any(Purchase.class));
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
