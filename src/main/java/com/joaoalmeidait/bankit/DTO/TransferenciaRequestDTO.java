package com.joaoalmeidait.bankit.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferenciaRequestDTO {
    private Integer agenciaOrigem;
    private Integer contaOrigem;
    private Integer agenciaDestino;
    private Integer contaDestino;
    private BigDecimal valor;
}
