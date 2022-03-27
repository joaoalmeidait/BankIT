package com.joaoalmeidait.bankit.service;

import com.joaoalmeidait.bankit.DTO.ConsultaSaldoResponse;
import com.joaoalmeidait.bankit.Exceptions.ContaInativaException;
import com.joaoalmeidait.bankit.model.ContaCorrente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CheckContaInativaService {

    public ResponseEntity<ConsultaSaldoResponse> checkContaInativa(ContaCorrente cc) throws Exception {
        if (cc.isAtiva() == false){
            throw new ContaInativaException("Conta inativa. Tente a operação com uma conta que ainda esteja ativa.", HttpStatus.valueOf(400));
        }
        return null;
    }
}
