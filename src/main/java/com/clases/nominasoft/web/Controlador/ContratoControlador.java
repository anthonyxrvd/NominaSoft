package com.clases.nominasoft.web.Controlador;

import com.clases.nominasoft.dominio.Contratos.IContratoService;
import com.clases.nominasoft.dominio.Entidad.Contrato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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


    @GetMapping("/contratos/{dni}")
    public ResponseEntity<?>filtrarContratoPorDni(@PathVariable Long dni){
        Map<String, Object> response = new HashMap<>();
        List<Contrato> contratos;
        try{
            contratos = contratoService.findContratoByEmpleadoDni(dni);
        }catch(DataAccessException e ){
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(contratos.isEmpty()){
            response.put("mensaje", "No se encontraron contrados registrados para el dni: ".concat(dni.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Contrato>>(contratos,HttpStatus.OK);
    }
    private List<String> obtenerErrores(BindingResult result) {
        List<String> errors = result.getFieldErrors().stream()
                .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                .collect(Collectors.toList());
        return errors;
    }
}
