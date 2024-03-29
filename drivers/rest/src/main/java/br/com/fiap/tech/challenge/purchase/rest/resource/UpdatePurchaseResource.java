package br.com.fiap.tech.challenge.purchase.rest.resource;

import br.com.fiap.tech.challenge.purchase.adapter.controller.purchase.UpdatePurchaseStatusController;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;
import br.com.fiap.tech.challenge.purchase.rest.mapping.PurchaseResponseMapper;
import br.com.fiap.tech.challenge.purchase.rest.resource.doc.UpdatePurchaseResourceDoc;
import br.com.fiap.tech.challenge.purchase.rest.resource.response.PurchaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class UpdatePurchaseResource implements UpdatePurchaseResourceDoc {

    private final PurchaseResponseMapper purchaseResponseMapper;

    private final UpdatePurchaseStatusController updatePurchaseStatusController;

    @PutMapping("/{uuid}/{status}")
    public PurchaseResponse updatePurchaseStatus(@PathVariable(name = "uuid") String uuid,
                                                 @PathVariable(name = "status") PurchaseStatus status) {
        return purchaseResponseMapper.toResponse(updatePurchaseStatusController.update(uuid, status));
    }

}
