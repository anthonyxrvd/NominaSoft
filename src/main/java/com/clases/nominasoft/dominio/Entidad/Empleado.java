package com.clases.nominasoft.dominio.Entidad;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import static javax.persistence.GenerationType.SEQUENCE;
@Entity
@Table(name="empleados")
public class Empleado implements Serializable {

    private static final long serialVersionUID =1L;

    @Id
    @SequenceGenerator(
            name = "secuencia_empleado",
            sequenceName = "secuencia_empleado",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "secuencia_empleado"
    )

    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "dni",
            nullable=false,
            length = 8
    )
    private Long dni;

    @Column(
            name = "nombre",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String nombre;

    @Column(
            name = "direccion",
            columnDefinition = "TEXT"
    )
    private String direccion;

    @Column(
            name = "estado_civil",
            columnDefinition = "TEXT"
    )
    private String EstadoCivil;

    @Column(
            name = "telefono",
            columnDefinition = "TEXT",
            length = 9
    )
    private String telefono;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nacimiento")
    private Calendar fechaNacimiento;

    @JsonIgnoreProperties({"empleado","hibernateLazyInitializer","handler"})
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy="empleado"
    )
     List<Contrato> contratos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDNI() {
        return dni;
    }

    public void setDNI(Long DNI) {
        this.dni = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstadoCivil() {
        return EstadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        EstadoCivil = estadoCivil;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }
}
