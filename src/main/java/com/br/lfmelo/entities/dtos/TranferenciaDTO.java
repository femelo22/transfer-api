package com.br.lfmelo.entities.dtos;

import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

public class TranferenciaDTO {

    @NotEmpty(message = "Value cannot be null")
    private BigDecimal value;

    @NotEmpty(message = "Payer ID cannot be null")
    private Long payer;

    @NotEmpty(message = "Payee ID cannot be null")
    private Long payee;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Long getPayer() {
        return payer;
    }

    public void setPayer(Long payer) {
        this.payer = payer;
    }

    public Long getPayee() {
        return payee;
    }

    public void setPayee(Long payee) {
        this.payee = payee;
    }
}
