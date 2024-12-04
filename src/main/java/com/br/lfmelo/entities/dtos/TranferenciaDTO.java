package com.br.lfmelo.entities.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TranferenciaDTO {

    @NotEmpty(message = "Value cannot be null")
    private BigDecimal value;

    @NotEmpty(message = "Payer ID cannot be null")
    private Long payer;

    @NotEmpty(message = "Payee ID cannot be null")
    private Long payee;
}
