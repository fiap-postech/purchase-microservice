package br.com.fiap.tech.challenge.purchase.enterprise.error;

import br.com.fiap.tech.challenge.exception.error.BaseApplicationError;
import br.com.fiap.tech.challenge.exception.error.ErrorType;

import static br.com.fiap.tech.challenge.exception.error.ErrorType.INTERNAL_SERVER_ERROR;
import static br.com.fiap.tech.challenge.exception.error.ErrorType.INVALID_PARAMETER;
import static br.com.fiap.tech.challenge.exception.error.ErrorType.NOT_FOUND;
import static java.lang.Boolean.TRUE;

public enum ApplicationError implements BaseApplicationError {

    UNKNOWN_ERROR("AE-001", INTERNAL_SERVER_ERROR, TRUE, "Unexpected error [{}]"),
    PRODUCT_NOT_FOUND_BY_UUID("AE-002", INVALID_PARAMETER, TRUE, "Product not found [uuid={}]"),

    PRODUCT_PRICES_IS_INVALID("AE-003", INVALID_PARAMETER, TRUE, "It is not possible to dismember the combo product and keep offered amount [uuid={}]"),
    CUSTOMER_NOT_FOUND_BY_UUID("AE-005", INVALID_PARAMETER, TRUE, "Customer not found [uuid={}]"),
    PAYMENT_NOT_FOUND("AE-011", NOT_FOUND, TRUE, "There are no payment registered for purchase [purchaseUUID={}]"),
    PURCHASE_NOT_FOUND_BY_UUID("AE-012", INVALID_PARAMETER, TRUE, "Purchase not found [uuid={}]"),
    ;

    private final String code;

    private final ErrorType errorType;

    private final boolean acceptParameters;

    private final String description;

    ApplicationError(String code, ErrorType errorType, boolean acceptParameters, String description) {
        this.code = code;
        this.errorType = errorType;
        this.acceptParameters = acceptParameters;
        this.description = description;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public ErrorType getErrorType() {
        return errorType;
    }

    @Override
    public boolean getAcceptParameters() {
        return acceptParameters;
    }

    @Override
    public String getDescription() {
        return description;
    }
}