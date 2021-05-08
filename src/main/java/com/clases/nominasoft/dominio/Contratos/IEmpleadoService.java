package com.clases.nominasoft.dominio.Contratos;

import com.clases.nominasoft.dominio.Entidad.Contrato;
import com.clases.nominasoft.dominio.Entidad.Empleado;

import java.util.List;


public interface IEmpleadoService {
    public List<Empleado> findAll();
    public Empleado guardar(Empleado empleado);
    public void eliminar(long id);
    public Empleado buscar(long id);
    public Empleado findByDni(Long dni);
}
