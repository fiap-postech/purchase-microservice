package br.com.fiap.tech.challenge.purchase.adapter.controller.purchase;

import br.com.fiap.tech.challenge.exception.ApplicationException;
import br.com.fiap.tech.challenge.purchase.adapter.dto.PurchaseInputDTO;
import br.com.fiap.tech.challenge.purchase.adapter.util.CreatePurchaseBuilder;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.CreatePurchaseUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.PostPurchaseCreatedUseCase;
import br.com.fiap.tech.challenge.purchase.enterprise.error.ApplicationError;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
class CreatePurchaseControllerImpl implements CreatePurchaseController {

    private final CreatePurchaseUseCase createUseCase;
    private final PostPurchaseCreatedUseCase postCreatedUseCase;

    @Override
    @Transactional
    public void create(PurchaseInputDTO inputDTO) {
        try {
            var dto = CreatePurchaseBuilder.get().build(inputDTO);
            var purchase = createUseCase.create(dto);

            postCreatedUseCase.post(purchase);
        } catch (ApplicationException e) {
            if (e.getError() == ApplicationError.PURCHASE_DUPLICATED) {
                return;
            }

            throw e;
        }
    }
}
