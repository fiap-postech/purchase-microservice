package br.com.fiap.tech.challenge.purchase.application.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequestRemoveCustomerDataDTO implements Serializable {

    private String id;
    private String document;

}
