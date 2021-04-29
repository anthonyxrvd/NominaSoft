package com.clases.nominasoft;

import com.clases.nominasoft.aplicacion.Servicios.EmpleadoImple;
import com.clases.nominasoft.dominio.Entidad.Empleado;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

@SpringBootTest
class NominasoftBackendApplicationTests {


    @Autowired
    private EmpleadoImple empleadoService;
    @Test
    void saveEmpleado(){
        Empleado instance = new Empleado();
        instance.setId(12L);
        instance.setDNI(1234L);
        instance.setEstadoCivil("soltero");
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
