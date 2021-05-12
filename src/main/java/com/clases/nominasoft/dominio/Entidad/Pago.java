package com.clases.nominasoft.dominio.Entidad;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "pagos")
public class Pago implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_actual")
    private Calendar fechaActual;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name="periododepago_id",
            referencedColumnName = "id"
    )
    @JsonIgnoreProperties({"pagos","hibernateLazyInitializer","handler"})
    private PeriodoPago periodo;

    @JsonIgnoreProperties({"pagos","hibernateLazyInitializer","handler"})
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(
            name="contrato_id",
            referencedColumnName = "id"
    )
     private Contrato contrato;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "otrosconceptos_id",
            referencedColumnName = "id"
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private OtrosConceptos conceptos;




    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public Calendar getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Calendar fechaActual) {
        this.fechaActual = fechaActual;
    }

    public PeriodoPago getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoPago periodo) {
        this.periodo = periodo;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public OtrosConceptos getConceptos() {
        return conceptos;
    }

    public void setConceptos(OtrosConceptos conceptos) {
        this.conceptos = conceptos;
    }
    //REGLAS DE NEGOCIO
    public int calcularSueldoBasico() {

        return calcularTotalDeHoras()* contrato.getValorHora();
    }


    public double calcularIngresoTotal() {
        return calcularSueldoBasico() + calcularMontoAsignacionFamiliar() + conceptos.calcularTotalConceptosIngresos();
    }

    public double calcularTotalDescuentos() {
        return calcularDescuentoAFP() + conceptos.calcularTotalConceptosDescuentos();
    }

    public float calcularMontoAsignacionFamiliar() {
        float monto = 0f;
        if (contrato.isAsignacionFamiliar()) {
            monto = calcularSueldoBasico() * 0.1f;
        }
        return monto;
    }
    public float calcularDescuentoAFP() {
        return calcularSueldoBasico() * contrato.getDescuentoAFP();
    }
    public double calcularSueldoNeto() {

        return calcularIngresoTotal() - calcularTotalDescuentos();
    }
    public int calcularTotalDeHoras(){
        return periodo.getSemanasDelPeriodo() * contrato.calcularHorasContratadasPorSemana();
    }

    public boolean puedeProcesarContrato(){
        if (periodo.isEstado()) {
            if((contrato.getFechaFin().after(contrato.getFechaInicio()) && contrato.isActivo())){
                return true;
            }
        }
        return false;
    }
}
