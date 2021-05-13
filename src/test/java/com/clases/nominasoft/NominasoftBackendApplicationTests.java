package com.clases.nominasoft;

import com.clases.nominasoft.aplicacion.Servicios.EmpleadoImple;
import com.clases.nominasoft.dominio.Entidad.Empleado;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Calendar;

@SpringBootTest
class NominasoftBackendApplicationTests {


    @Autowired
    private EmpleadoImple empleadoService;
    @Test
    void saveEmpleado(){
        Calendar fecha= Calendar.getInstance();
        fecha.set(2000,7,7);
        Empleado instance = new Empleado();
        instance.setId(12L);
        instance.setDni(1234L);
        instance.setEstadoCivil("soltero");
        instance.setNombre("Antho");
        instance.setTelefono("12432");
        instance.setGradoAcademico("bachiller");
        instance.setFechaNacimiento(fecha);
        instance.setDireccion("trujillo");
        try{
            empleadoService.guardar(instance);
        }catch(Exception x){
            x.printStackTrace();
        }
        Assert.isNull(empleadoService.buscar(instance.getId()),"no se guardo el id");

    }
    //void contextLoads() {
   // }

}
