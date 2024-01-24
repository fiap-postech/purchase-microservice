package br.com.fiap.tech.challenge.purchase.rest.resource;

import br.com.fiap.tech.challenge.purchase.adapter.controller.purchase.FindPurchaseByUUIDController;
import br.com.fiap.tech.challenge.purchase.rest.mapping.PurchaseResponseMapper;
import br.com.fiap.tech.challenge.purchase.rest.resource.doc.GetPurchaseResourceDoc;
import br.com.fiap.tech.challenge.purchase.rest.resource.response.PurchaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class GetPurchaseResource implements GetPurchaseResourceDoc {

    private final PurchaseResponseMapper purchaseResponseMapper;

    private final FindPurchaseByUUIDController findPurchaseByUUIDController;

    @GetMapping("/{uuid}")
    public PurchaseResponse getByUUID(@PathVariable String uuid) {
        return purchaseResponseMapper.toResponse(findPurchaseByUUIDController.get(uuid));
    }

}
