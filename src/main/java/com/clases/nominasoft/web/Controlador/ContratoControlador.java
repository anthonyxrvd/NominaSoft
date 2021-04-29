package com.clases.nominasoft.web.Controlador;

import com.clases.nominasoft.dominio.Contratos.IContratoService;
import com.clases.nominasoft.dominio.Entidad.Contrato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
