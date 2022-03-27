package com.joaoalmeidait.bankit.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Accessors(chain = true)
@Getter
@Setter
@AllArgsConstructor
@Entity
public class ContaCorrente  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "correntista_id")
    private Correntista correntistaId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "agencia_id")
    private Agencia agenciaId;

    private BigDecimal limite;

    private BigDecimal saldo;

    private boolean ativa;

    public ContaCorrente()
    {
        super();
    }

}
