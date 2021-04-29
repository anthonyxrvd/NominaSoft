package com.clases.nominasoft.persistencia.DaoRepository;

import com.clases.nominasoft.dominio.Entidad.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContratoDao extends JpaRepository<Contrato,Long> {
}
