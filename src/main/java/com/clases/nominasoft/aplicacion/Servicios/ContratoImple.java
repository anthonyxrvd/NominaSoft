package com.clases.nominasoft.aplicacion.Servicios;

import com.clases.nominasoft.aplicacion.Exceptions.DataException;
import com.clases.nominasoft.dominio.Contratos.IContratoService;
import com.clases.nominasoft.dominio.Entidad.Contrato;
import com.clases.nominasoft.persistencia.DaoRepository.IContratoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContratoImple implements IContratoService {


    @Autowired
    private IContratoDao contratoDao;

    @Transactional(readOnly = true)
    @Override
    public List<Contrato> findAll() {
        return (List<Contrato>)contratoDao.findAll();
    }

    @Override
    public Contrato guardar(Contrato contrato) {
        return null;
    }

    @Override
    public void eliminar(long id) {

    }
    @Override
    public Contrato buscar(long id) {
        return null;
    }

    @Override
    public List<Contrato> findContratoByEmpleadoDni(Long dni) {
        return contratoDao.findContratoByEmpleadoDni(dni).
                orElseThrow(()->new DataException(
                        "No se encontraron contrados del empleado con dni "+dni+"."));
    }

}
