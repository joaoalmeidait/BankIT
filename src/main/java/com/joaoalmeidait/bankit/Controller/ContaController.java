package com.joaoalmeidait.bankit.Controller;

import com.joaoalmeidait.bankit.DTO.ContaCorrenteDTO;
import com.joaoalmeidait.bankit.DTO.ReturnObjectDTO;
import com.joaoalmeidait.bankit.model.ContaCorrente;
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
    @ApiOperation(value = "Abrir conta corrente")
    public ResponseEntity<ContaCorrente> abrirConta(@RequestBody ContaCorrenteDTO dto) {
        return contaService.abrirConta(dto);
    }

}
