package com.clases.nominasoft.dominio.Contratos;


import com.clases.nominasoft.dominio.Entidad.Contrato;

import java.util.List;
public interface IContratoService {

    public List<Contrato> findAll();
    public Contrato guardar(Contrato contrato);
    public void eliminar(long id);
    public Contrato buscar(long id);
}
