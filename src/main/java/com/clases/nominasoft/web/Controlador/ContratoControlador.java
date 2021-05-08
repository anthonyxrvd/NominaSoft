package com.clases.nominasoft.web.Controlador;

import com.clases.nominasoft.dominio.Contratos.IContratoService;
import com.clases.nominasoft.dominio.Entidad.Contrato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ContratoControlador {

    @Autowired
    private IContratoService contratoService;

    @GetMapping("/contratos")
    public List<Contrato> listar(){

        return contratoService.findAll();
    }



}
