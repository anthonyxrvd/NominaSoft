package com.clases.nominasoft.web.Controlador;

import com.clases.nominasoft.dominio.Contratos.IContratoService;
import com.clases.nominasoft.dominio.Entidad.Contrato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins="http://localhost:4200",maxAge=3600)
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
