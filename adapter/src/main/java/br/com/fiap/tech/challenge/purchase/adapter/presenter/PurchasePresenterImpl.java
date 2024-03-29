package br.com.fiap.tech.challenge.purchase.adapter.presenter;

import br.com.fiap.tech.challenge.purchase.application.dto.PurchaseDTO;
import br.com.fiap.tech.challenge.purchase.application.mapper.PurchaseMapper;
import br.com.fiap.tech.challenge.purchase.application.util.ResponseList;
import br.com.fiap.tech.challenge.purchase.enterprise.entity.Purchase;

class PurchasePresenterImpl implements PurchasePresenter{
    @Override
    public PurchaseDTO present(Purchase purchase) {
        return PurchaseMapper.INSTANCE.toDTO(purchase);
    }

    @Override
    public ResponseList<PurchaseDTO> present(ResponseList<Purchase> list) {
        return ResponseList.from(list, PurchaseMapper.INSTANCE::toDTO);
    }
}
