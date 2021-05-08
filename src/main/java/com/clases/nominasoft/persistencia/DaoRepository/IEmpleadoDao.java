package com.clases.nominasoft.persistencia.DaoRepository;

import com.clases.nominasoft.dominio.Entidad.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IEmpleadoDao extends CrudRepository<Empleado,Long> {
    //@Query
    public Empleado findByDni(Long dni);
}
