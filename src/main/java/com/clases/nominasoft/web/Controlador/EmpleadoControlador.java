package com.clases.nominasoft.web.Controlador;


import com.clases.nominasoft.dominio.Contratos.IEmpleadoService;
import com.clases.nominasoft.dominio.Entidad.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmpleadoControlador {

    @Autowired
    private IEmpleadoService empleadoService;

    @GetMapping("/empleados")
    public List<Empleado> listar(){
        return empleadoService.findAll();
    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@Valid @RequestBody Empleado empleado, BindingResult result){
        Empleado empleadoNew=null;
        Map<String,Object> response = new HashMap<>();
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream().
                    map(error ->"campo "+error.getField()+" "+error.getDefaultMessage()).collect(Collectors.toList());
            response.put("errors",errors);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try{
            empleadoNew=empleadoService.guardar(empleado);
        }catch(DataAccessException e){
            response.put("mensaje","error al realizar inssert en la BD");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El empleado se guardo con exito");
        response.put("empleado",empleadoNew);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<?>delete(@PathVariable Long id){
        Map<String,Object> response = new HashMap<>();
        try{
            Empleado empleado = empleadoService.buscar(id);
            empleadoService.eliminar(id);
        }catch(DataAccessException x){
            response.put("mensaje","error al eliminar al empleado en la base de datos");
            response.put("error",x.getMessage().concat(": ").concat(x.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El empleado se elimino con exito");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }


    @PutMapping("empleados/{id}")
    public ResponseEntity<?>Update(@RequestBody Empleado empleado,@PathVariable Long id){
        Map<String,Object> response = new HashMap<>();
        Empleado e= empleadoService.buscar(id);
        Empleado empleado2=null;

        if(e==null){
            response.put("mensaje","Error: no se pudo editar el empleado con ID: ".concat(id.toString().concat(" no existe en la BD")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        try{
            e.setDireccion(empleado.getDireccion());
            e.setDNI(empleado.getDNI());
            e.setEstadoCivil(empleado.getEstadoCivil());
            empleado2=empleadoService.guardar(e);

        }catch(DataAccessException x){
            response.put("mensaje","error al actualizar al empleado en la BD");
            response.put("error",x.getMessage().concat(": ").concat(x.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("Mensaje","El empleado ha sido actualizado con exito :DDD");
        response.put("Empleado",empleado2);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }

}
