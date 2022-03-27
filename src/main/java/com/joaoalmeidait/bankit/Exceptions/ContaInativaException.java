package com.joaoalmeidait.bankit.Exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
public class ContaInativaException extends Exception {
    private String mensagem;
    private HttpStatus status;
}
