package com.joaoalmeidait.bankit.service;

import com.joaoalmeidait.bankit.DTO.*;
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

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
public class ContaService {

    @Autowired
    private CheckContaInativaService checkContaInativaService;

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

    public ResponseEntity<ConsultaSaldoResponse> consultaSaldo(Integer agencia, Integer conta) {

        Optional<ContaCorrente> cc = contaRepository.findById(conta);
        if (cc.isPresent()) {
            ConsultaSaldoResponse saldoResponse = new ConsultaSaldoResponse();
            saldoResponse.setSaldo(cc.get().getSaldo());
            return ResponseEntity.ok().body(saldoResponse);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<ConsultaSaldoResponse> deposito(DepositoRequestDTO dto) throws Exception {

        if (dto.getValor().intValueExact() ==0 || dto.getValor().intValueExact() <0){
            return ResponseEntity.status(403).build();
        }

        Optional<ContaCorrente> cc = contaRepository.findById(dto.getConta());
        if (cc.isPresent()){
            checkContaInativaService.checkContaInativa(cc.get());
            BigDecimal saldoAtual = cc.get().getSaldo();
            BigDecimal novoSaldo = (saldoAtual.add(dto.getValor()));
            cc.get().setSaldo(novoSaldo);
            contaRepository.save(cc.get());
            ConsultaSaldoResponse saldoResponse = new ConsultaSaldoResponse();
            saldoResponse.setSaldo(novoSaldo);
            return ResponseEntity.ok().body(saldoResponse);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<ConsultaSaldoResponse> saque(SaqueRequestDTO dto) throws Exception {
        Optional<ContaCorrente> cc = contaRepository.findById(dto.getConta());
        if (cc.isPresent()){
            checkContaInativaService.checkContaInativa(cc.get());
            BigDecimal novoSaldo = saque(cc, dto.getValor());
            ConsultaSaldoResponse saldoResponse = new ConsultaSaldoResponse();
            saldoResponse.setSaldo(novoSaldo);
            return ResponseEntity.ok().body(saldoResponse);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<ConsultaSaldoResponse> transferencia(TransferenciaRequestDTO dto) throws Exception {

        if (dto.getValor().intValueExact() ==0 || dto.getValor().intValueExact() <0){
            return ResponseEntity.status(403).build();
        }

        Optional<ContaCorrente> ccOrigem = contaRepository.findById(dto.getContaOrigem());
        Optional<ContaCorrente> ccDestino = contaRepository.findById(dto.getContaDestino());
        if (ccOrigem.isPresent() && ccDestino.isPresent() ){
            checkContaInativaService.checkContaInativa(ccOrigem.get());
            checkContaInativaService.checkContaInativa(ccDestino.get());

            BigDecimal novoSaldoOrigem = saque(ccOrigem, dto.getValor());

            BigDecimal saldoDestinoAtual = ccDestino.get().getSaldo();
            BigDecimal novoSaldoDestino = (saldoDestinoAtual.add(dto.getValor()));
            ccDestino.get().setSaldo(novoSaldoDestino);
            contaRepository.save(ccDestino.get());

            ConsultaSaldoResponse saldoOrigemResponse = new ConsultaSaldoResponse();
            saldoOrigemResponse.setSaldo(novoSaldoOrigem);
            return ResponseEntity.ok().body(saldoOrigemResponse);
        }
        return ResponseEntity.notFound().build();
    }

    private BigDecimal saque(Optional<ContaCorrente> cc, BigDecimal valor) {
        BigDecimal saldoAtual = cc.get().getSaldo();
        BigDecimal novoSaldo = (saldoAtual.subtract(valor));
        cc.get().setSaldo(novoSaldo);
        contaRepository.save(cc.get());
        return novoSaldo;
    }

    public ResponseEntity<Correntista> consultaContas(String cpf) {
        Correntista cr = (Correntista) correntistaRepository.findByCpf(cpf);
        return ResponseEntity.ok().body(cr);
    }

    public ResponseEntity<ContaStatusDTO> desativarConta(Integer agencia, Integer conta) {
        Optional<ContaCorrente> cc = contaRepository.findById(conta);
        if (cc.isPresent()){
            if (cc.get().isAtiva() == true) {
                cc.get().setAtiva(false);
                contaRepository.save(cc.get());
                ContaStatusDTO dto = new ContaStatusDTO(cc.get());
                return ResponseEntity.ok().body(dto);
            }
            return ResponseEntity.status(304).build();
        }
        return ResponseEntity.status(403).build();
    }
}
