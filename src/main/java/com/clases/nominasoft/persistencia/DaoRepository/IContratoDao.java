package com.clases.nominasoft.persistencia.DaoRepository;

import com.clases.nominasoft.dominio.Entidad.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IContratoDao extends JpaRepository<Contrato,Long> {

    public Optional<List<Contrato>> findContratoByEmpleadoDni(Long dni);
}
