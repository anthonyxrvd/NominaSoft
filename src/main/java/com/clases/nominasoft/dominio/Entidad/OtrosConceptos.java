package com.clases.nominasoft.dominio.Entidad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    @JsonIgnoreProperties({"conceptos","hibernateLazyInitializer", "handler"})
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(
            name="contrato_id",
            referencedColumnName = "id"
    )
    private Contrato contrato;

    public OtrosConceptos(float montoAdelantos, float montoHorasAusente, float montoHorasExtra, float montoOtrosDescuentos, float montoOtrosIngresos, float montoReintegro, Contrato contrato) {
        this.montoAdelantos = montoAdelantos;
        this.montoHorasAusente = montoHorasAusente;
        this.montoHorasExtra = montoHorasExtra;
        this.montoOtrosDescuentos = montoOtrosDescuentos;
        this.montoOtrosIngresos = montoOtrosIngresos;
        this.montoReintegro = montoReintegro;
        this.contrato = contrato;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

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
    public double calcularTotalConceptosIngresos()
    {
        double monto = montoHorasExtra + montoReintegro + montoOtrosIngresos;
        return monto;
    }

    public double  calcularTotalConceptosDescuentos ()
    {
        float monto = montoHorasAusente + montoAdelantos + montoOtrosDescuentos;
        return monto;
    }

}
