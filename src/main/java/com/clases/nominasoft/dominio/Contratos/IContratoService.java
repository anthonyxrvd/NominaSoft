package com.clases.nominasoft.dominio.Contratos;


import com.clases.nominasoft.dominio.Entidad.Contrato;

import java.util.List;
import java.util.Optional;

public interface IContratoService {

    public List<Contrato> findAll();
    public Contrato guardar(Contrato contrato);
    public void eliminar(long id);
    public Contrato buscar(long id);
    public List<Contrato> findContratoByEmpleadoDni(Long dni);

}
