package com.clases.nominasoft.dominio.Entidad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean asignacionFamiliar;
    private String cargo;
    private Date fechaInicio;
    private Date fechaFin;
    private int horasContratadas;
    private Double valorHora;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="afp_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Afp afp;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="empleado_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Empleado empleado;

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAsignacionFamiliar() {
        return asignacionFamiliar;
    }

    public void setAsignacionFamiliar(boolean asignacionFamiliar) {
        this.asignacionFamiliar = asignacionFamiliar;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getHorasContratadas() {
        return horasContratadas;
    }

    public void setHorasContratadas(int horasContratadas) {
        this.horasContratadas = horasContratadas;
    }

    public Double getValorHora() {
        return valorHora;
    }

    public void setValorHora(Double valorHora) {
        this.valorHora = valorHora;
    }

    public Afp getAfp() {
        return afp;
    }

    public void setAfp(Afp afp) {
        this.afp = afp;
    }

    //REGLAS DE NEGOCIO :u
    public float getDescuentoAFP(){
        float descuento=0;
        descuento= afp.getDescuento();

        return descuento/100;
    }
    public Double getSueldoBasico(){
        return valorHora.doubleValue()*horasContratadas;
    }




}
