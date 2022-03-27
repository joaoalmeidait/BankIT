package com.joaoalmeidait.bankit.service;

import com.joaoalmeidait.bankit.DTO.ContaCorrenteDTO;
import com.joaoalmeidait.bankit.Repository.AgenciaRepository;
import com.joaoalmeidait.bankit.Repository.ContaRepository;
import com.joaoalmeidait.bankit.Repository.CorrentistaRepository;
import com.joaoalmeidait.bankit.model.Agencia;
import com.joaoalmeidait.bankit.model.ContaCorrente;
import com.joaoalmeidait.bankit.model.Correntista;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private AgenciaRepository agenciaRepository;

    @Autowired
    private CorrentistaRepository correntistaRepository;

    public ResponseEntity<ContaCorrente> abrirConta(ContaCorrenteDTO dto) {
        Agencia ag = agenciaRepository.getById(dto.getAgenciaId());
        if (ag.getId() != null) {
            Correntista cr = criarCorrentista(dto);
            ContaCorrente cc = new ContaCorrente();
            cc.setSaldo(dto.getSaldoInicial());
            cc.setAtiva(true);
            cc.setLimite(dto.getLimite());
            cc.setAgenciaId(ag);
            cc.setCorrentistaId(cr);

            contaRepository.save(cc);
            return ResponseEntity.status(HttpStatus.CREATED).body(cc);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    private Correntista criarCorrentista(ContaCorrenteDTO dto) {
        Correntista cr = new Correntista();
        //observar como vai salvar o id
        cr.setCpf(dto.getCorrentistaDTO().getCpf());
        cr.setNome(dto.getCorrentistaDTO().getNome());
        cr.setNascimento(dto.getCorrentistaDTO().getNascimento());

        correntistaRepository.save(cr);

        return cr;
    }

}
