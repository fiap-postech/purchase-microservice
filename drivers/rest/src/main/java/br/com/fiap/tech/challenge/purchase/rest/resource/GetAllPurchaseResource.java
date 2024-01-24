package br.com.fiap.tech.challenge.purchase.rest.resource;

import br.com.fiap.tech.challenge.purchase.adapter.controller.purchase.FindAllPurchasesController;
import br.com.fiap.tech.challenge.purchase.application.util.ResponseList;
import br.com.fiap.tech.challenge.purchase.rest.mapping.PurchaseResponseMapper;
import br.com.fiap.tech.challenge.purchase.rest.resource.doc.GetAllPurchaseResourceDoc;
import br.com.fiap.tech.challenge.purchase.rest.resource.response.PurchaseResponse;
import br.com.fiap.tech.challenge.purchase.rest.util.Pages;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class GetAllPurchaseResource implements GetAllPurchaseResourceDoc {

    private final PurchaseResponseMapper purchaseResponseMapper;

    private final FindAllPurchasesController findAllPurchasesController;

    @GetMapping
    public ResponseList<PurchaseResponse> getAllAvailable(@ParameterObject Pageable pageable) {
        return ResponseList.from(
                findAllPurchasesController.list(Pages.of(pageable)),
                purchaseResponseMapper::toResponse
        );
    }
}
