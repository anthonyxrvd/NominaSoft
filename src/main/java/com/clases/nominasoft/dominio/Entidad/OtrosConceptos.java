package com.clases.nominasoft.dominio.Entidad;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="otros_conceptos")
public class OtrosConceptos implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "monto_adelantos")
    private float montoAdelantos;

    @Column(name = "monto_horas_ausente")
    private float montoHorasAusente;

    @Column(name = "monto_horas_extra")
    private float montoHorasExtra;

    @Column(name = "monto_otros_descuentos")
    private float montoOtrosDescuentos;

    @Column(name = "monto_otros_ingresos")
    private float montoOtrosIngresos;

    @Column(name = "monto_reintegro")
    private float montoReintegro;


    public float getMontoAdelantos() {
        return montoAdelantos;
    }

    public void setMontoAdelantos(float montoAdelantos) {
        this.montoAdelantos = montoAdelantos;
    }

    public float getMontoHorasAusente() {
        return montoHorasAusente;
    }

    public void setMontoHorasAusente(float montoHorasAusente) {
        this.montoHorasAusente = montoHorasAusente;
    }

    public float getMontoHorasExtra() {
        return montoHorasExtra;
    }

    public void setMontoHorasExtra(float montoHorasExtra) {
        this.montoHorasExtra = montoHorasExtra;
    }

    public float getMontoOtrosDescuentos() {
        return montoOtrosDescuentos;
    }

    public void setMontoOtrosDescuentos(float montoOtrosDescuentos) {
        this.montoOtrosDescuentos = montoOtrosDescuentos;
    }

    public float getMontoOtrosIngresos() {
        return montoOtrosIngresos;
    }

    public void setMontoOtrosIngresos(float montoOtrosIngresos) {
        this.montoOtrosIngresos = montoOtrosIngresos;
    }

    public float getMontoReintegro() {
        return montoReintegro;
    }

    public void setMontoReintegro(float montoReintegro) {
        this.montoReintegro = montoReintegro;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    //REGLAS DE NEGOCIO
    public float calcularTotalConceptosIngresos()
    {
        float monto = montoHorasExtra + montoReintegro + montoOtrosIngresos;
        return monto;
    }

    public float  calcularTotalConceptosDescuentos ()
    {
        float monto = montoHorasAusente + montoAdelantos + montoOtrosDescuentos;
        return monto;
    }

}
