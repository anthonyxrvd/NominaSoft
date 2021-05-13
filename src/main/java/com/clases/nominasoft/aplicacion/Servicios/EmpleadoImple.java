package com.clases.nominasoft.aplicacion.Servicios;

import com.clases.nominasoft.dominio.Contratos.IEmpleadoService;
import com.clases.nominasoft.dominio.Entidad.Empleado;
import com.clases.nominasoft.persistencia.DaoRepository.IEmpleadoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpleadoImple implements IEmpleadoService {

    @Autowired
    private IEmpleadoDao empleadoDao;

    @Transactional(readOnly = true)
    @Override
    public List<Empleado> findAll() {

        return (List<Empleado>)empleadoDao.findAll();
    }

    @Override
    @Transactional
    public Empleado guardar(Empleado empleado) {
        return  empleadoDao.save(empleado);
    }
    @Override
    public void eliminar(long id) {
        empleadoDao.deleteById(id);
    }
    @Override
    public Empleado buscar(long id) {

        return empleadoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly=true)
    public Empleado findByDni(Long dni) {
        return empleadoDao.findByDni(dni);
    }
}
