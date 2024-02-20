package br.com.fiap.tech.challenge.purchase.adapter.controller.purchase;

import br.com.fiap.tech.challenge.purchase.application.dto.UpdatePaymentDTO;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.PostPurchasePaidUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.PublishPurchaseStatusUseCase;
import br.com.fiap.tech.challenge.purchase.application.usecase.purchase.UpdatePaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
class UpdatePaymentControllerImpl implements UpdatePaymentController {

    private final UpdatePaymentUseCase updateUseCase;
    private final PostPurchasePaidUseCase postPurchasePaidUseCase;
    private final PublishPurchaseStatusUseCase publishPurchaseStatusUseCase;


    @Override
    @Transactional
    public void update(UpdatePaymentDTO dto) {
        var purchase = updateUseCase.update(dto);

        if (purchase.wasPaid()) {
            postPurchasePaidUseCase.post(purchase);
        }

        publishPurchaseStatusUseCase.publish(purchase);
    }
}
