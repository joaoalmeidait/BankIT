package com.joaoalmeidait.bankit.DTO;

import com.joaoalmeidait.bankit.model.ContaCorrente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaCorrenteDTO {
    private Integer agenciaId;
    private CorrentistaDTO correntistaDTO;
    private BigDecimal limite;
    private BigDecimal saldoInicial;

    public ContaCorrenteDTO(ContaCorrente cc) {
    }
}
