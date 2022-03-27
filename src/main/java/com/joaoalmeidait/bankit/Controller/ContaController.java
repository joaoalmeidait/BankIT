package com.joaoalmeidait.bankit.Controller;

import com.joaoalmeidait.bankit.DTO.*;
import com.joaoalmeidait.bankit.model.ContaCorrente;
import com.joaoalmeidait.bankit.model.Correntista;
import com.joaoalmeidait.bankit.service.ContaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/conta")
@Api(value = "API REST BankIt")
@CrossOrigin(origins = "*")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping(value = "/abrir-conta")
    public ResponseEntity<ContaCorrente> abrirConta(@RequestBody ContaCorrenteDTO dto) {
        return contaService.abrirConta(dto);
    }

    @GetMapping(value = "/consulta-saldo")
    public ResponseEntity<ConsultaSaldoResponse>consultaSaldo(
            @RequestParam(value = "agencia", defaultValue = "") Integer agencia,
            @RequestParam(value = "conta", defaultValue = "") Integer conta ){
        return contaService.consultaSaldo(agencia, conta);
    }

    @PostMapping(value = "/deposito")
    public ResponseEntity<ConsultaSaldoResponse> deposito(@RequestBody DepositoRequestDTO dto) throws Exception {
        return contaService.deposito(dto);
    }

    @PostMapping(value = "/saque")
    public ResponseEntity<ConsultaSaldoResponse> saque(@RequestBody SaqueRequestDTO dto) throws Exception {
        return contaService.saque(dto);
    }

    @PostMapping(value = "/transferencia")
    public ResponseEntity<ConsultaSaldoResponse> transferencia(@RequestBody TransferenciaRequestDTO dto) throws Exception {
        return contaService.transferencia(dto);
    }

    @GetMapping(value = "/consulta-dados")
    public ResponseEntity<Correntista> consultaDados (@RequestParam String cpf){
        return contaService.consultaContas(cpf);
    }

    @DeleteMapping(value = "/desativar-conta")
    public ResponseEntity<ContaStatusDTO> desativarConta (
            @RequestParam(value = "agencia", defaultValue = "") Integer agencia,
            @RequestParam(value = "conta", defaultValue = "") Integer conta ){
        return contaService.desativarConta(agencia, conta);
    }


}
