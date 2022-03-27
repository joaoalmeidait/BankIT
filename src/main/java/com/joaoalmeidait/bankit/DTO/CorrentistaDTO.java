package com.joaoalmeidait.bankit.DTO;

import com.joaoalmeidait.bankit.model.Correntista;
import lombok.Data;

import java.util.Date;

@Data
public class CorrentistaDTO {
    private String nome;
    private String cpf;
    private Date nascimento;
}
