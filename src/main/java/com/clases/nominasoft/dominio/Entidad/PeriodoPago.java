package com.clases.nominasoft.dominio.Entidad;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;
@Entity
@Table(name = "periodo_de_pago")
public class PeriodoPago implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "secuencia_periodo",
            sequenceName = "secuencia_periodo",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "secuencia_periodo"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private long id;
    @Column(
            name = "estado",
            columnDefinition = "BOOLEAN DEFAULT FALSE"
    )
    private boolean estado;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio")
    private Calendar fechaInicio;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_fin")
    private Calendar fechaFin;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY
    , mappedBy="periodo")
    @JsonIgnoreProperties({"periodo","hibernateLazyInitializer","handler"})
    List<Pago> pagos = new ArrayList<>();

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name="otrosconceptos_id",
            referencedColumnName = "id"
    )*/
    //@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    //private OtrosConceptos conceptos;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Calendar getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Calendar getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Calendar fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }
    public int getSemanasDelPeriodo(){
        return calcularSemanasDelPeriodo();
    }

    //REGLAS DE NEGOCIO
    public boolean esFechaActualMayor(){
        Calendar fechaActual = Calendar.getInstance();
        if (fechaActual.after(fechaFin) || fechaActual.equals(fechaFin)) {
            return true;
        }
        return false;
    }

    private int calcularSemanasDelPeriodo(){
        long milis = fechaFin.getTimeInMillis() - fechaInicio.getTimeInMillis();
        int dias = (int)Math.floor((double)(milis / 86400000));
        int semanas = (int)Math.floor((dias / 7));
        return semanas;
    }
}
