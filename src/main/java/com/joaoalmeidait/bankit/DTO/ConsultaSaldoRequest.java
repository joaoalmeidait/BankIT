package com.joaoalmeidait.bankit.DTO;

import lombok.Data;

@Data
public class ConsultaSaldoRequest {
    private Integer agencia;
    private Integer conta;
}
