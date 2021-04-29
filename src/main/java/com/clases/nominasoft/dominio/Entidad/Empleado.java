package com.clases.nominasoft.dominio.Entidad;


import javax.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="empleados")
public class Empleado implements Serializable {

    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String direccion;
    @NotEmpty(message="no puede estar vacio")
    @Column(nullable=false)
    private Long DNI;
    private String EstadoCivil;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getDNI() {
        return DNI;
    }
    public void setDNI(Long DNI) {
        this.DNI = DNI;
    }
    public String getEstadoCivil() {
        return EstadoCivil;
    }
    public void setEstadoCivil(String estadoCivil) {
        EstadoCivil = estadoCivil;
    }
}
