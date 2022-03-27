package com.joaoalmeidait.bankit.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositoRequestDTO {
    private Integer agencia;
    private Integer conta;
    private BigDecimal valor;
}
