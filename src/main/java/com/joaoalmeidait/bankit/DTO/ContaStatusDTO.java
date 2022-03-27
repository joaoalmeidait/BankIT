package com.joaoalmeidait.bankit.DTO;

import com.joaoalmeidait.bankit.model.ContaCorrente;
import lombok.Data;

@Data
public class ContaStatusDTO {
    private Integer agencia;
    private Integer conta;
    private Boolean ativa;

    public ContaStatusDTO(ContaCorrente dto) {
        this.agencia = dto.getAgenciaId().getId();
        this.conta = dto.getId();
        this.ativa = dto.isAtiva();
    }
}
