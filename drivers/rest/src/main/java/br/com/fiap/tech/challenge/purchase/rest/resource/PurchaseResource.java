package br.com.fiap.tech.challenge.purchase.rest.resource;

import br.com.fiap.tech.challenge.purchase.adapter.controller.purchase.FindAllPurchasesController;
import br.com.fiap.tech.challenge.purchase.adapter.controller.purchase.FindPurchaseByUUIDController;
import br.com.fiap.tech.challenge.purchase.adapter.controller.purchase.UpdatePurchaseStatusController;
import br.com.fiap.tech.challenge.purchase.application.util.ResponseList;
import br.com.fiap.tech.challenge.purchase.enterprise.enums.PurchaseStatus;
import br.com.fiap.tech.challenge.purchase.rest.mapping.PurchaseResponseMapper;
import br.com.fiap.tech.challenge.purchase.rest.resource.doc.PurchaseResourceDoc;
import br.com.fiap.tech.challenge.purchase.rest.resource.response.PurchseResponse;
import br.com.fiap.tech.challenge.purchase.rest.util.Pages;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseResource implements PurchaseResourceDoc {

    private final PurchaseResponseMapper purchaseResponseMapper;

    private final FindAllPurchasesController findAllPurchasesController;
    private final FindPurchaseByUUIDController findPurchaseByUUIDController;
    private final UpdatePurchaseStatusController updatePurchaseStatusController;

    @GetMapping
    public ResponseList<PurchseResponse> getAllAvailable(@ParameterObject Pageable pageable) {
        return ResponseList.from(
                findAllPurchasesController.list(Pages.of(pageable)),
                purchaseResponseMapper::toResponse
        );
    }

    @GetMapping("/{uuid}")
    public PurchseResponse getByUUID(@PathVariable String uuid) {
        return purchaseResponseMapper.toResponse(findPurchaseByUUIDController.get(uuid));
    }

    @PatchMapping("/{uuid}/{status}")
    public PurchseResponse updatePurchaseStatus(@PathVariable String uuid, @PathVariable PurchaseStatus status) {
        return purchaseResponseMapper.toResponse(updatePurchaseStatusController.update(uuid, status));
    }
}
