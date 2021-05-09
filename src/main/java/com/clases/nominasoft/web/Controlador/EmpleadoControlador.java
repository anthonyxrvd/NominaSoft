package com.clases.nominasoft.web.Controlador;


import com.clases.nominasoft.dominio.Contratos.IEmpleadoService;
import com.clases.nominasoft.dominio.Entidad.Contrato;
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
@CrossOrigin(origins="http://localhost:4200",maxAge=3600)
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
            e.setDni(empleado.getDni());
            e.setEstadoCivil(empleado.getEstadoCivil());
            e.setNombre(empleado.getNombre());
            e.setGradoAcademico(empleado.getGradoAcademico());
            e.setTelefono(empleado.getTelefono());
            e.setFechaNacimiento(empleado.getFechaNacimiento());
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

    @GetMapping("/empleados/{dni}")
    public ResponseEntity<?> filtrarPorDni(@PathVariable Long dni){
        Empleado empleado = null;
        Map<String, Object> response = new HashMap<>();
        try{
            empleado = empleadoService.findByDni(dni);
        }catch(DataAccessException e){

            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(empleado==null) {
            response.put("mensaje", "El empleado con dni: ".concat(dni.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
    }

}
