package br.com.fiap.tech.challenge.purchase.rest.resource.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class PaymentConfirmRequest {
    private String action;
    private String api_version;

    private PaymentDataRequest data;

    @JsonProperty("date_created")
    private String dateCreated;

    private BigInteger id;

    @JsonProperty("live_mode")
    private boolean liveMode;

    private String type;

    @JsonProperty("user_id")
    private String userId;


    public String paymentId() {
        return data.getId();
    }
}