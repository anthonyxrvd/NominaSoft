package com.clases.nominasoft.dominio.Entidad;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.*;
@Entity
@Table(name="contratos")
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "asignacion_familiar")
    private boolean asignacionFamiliar;
    @Column(
            name = "cargo",
            columnDefinition = "TEXT"
    )
    private String cargo;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(
            name = "fecha_inicio",
            nullable = false
    )
    private Calendar fechaInicio;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(
            name = "fecha_fin",
            nullable = false
    )
    private Calendar fechaFin;
    @Column(name = "horas_contratadas")
    private int horasContratadas;
    @Column(name = "valor_hora")
    private int valorHora;
    @Column(name = "activo")
    private boolean activo = true;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "afp_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Afp afp;

    @JsonIgnoreProperties({"contratos","hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id",
            referencedColumnName = "id")
     private Empleado empleado;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "otrosconceptos_id",
            referencedColumnName = "id"
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private OtrosConceptos conceptos;

    @JsonIgnoreProperties({"contrato","hibernateLazyInitializer", "handler"})
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy="contrato"
    )
    List<Pago> pagos = new ArrayList<>();



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

    public int getHorasContratadas() {
        return horasContratadas;
    }

    public void setHorasContratadas(int horasContratadas) {
        this.horasContratadas = horasContratadas;
    }

    public int getValorHora() {
        return valorHora;
    }

    public void setValorHora(int valorHora) {
        this.valorHora = valorHora;
    }

    public Afp getAfp() {
        return afp;
    }

    public void setAfp(Afp afp) {
        this.afp = afp;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public OtrosConceptos getConceptos() {
        return conceptos;
    }

    public void setConceptos(OtrosConceptos conceptos) {
        this.conceptos = conceptos;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }
//REGLAS DE NEGOCIO

    public boolean estaVigente() {
        if ((fechaFin.after(fechaInicio) || fechaFin.equals(fechaInicio)) && activo) {
            return true;
        }
        return false;
    }

    public boolean esValidaFechaInicio() {
        List<Contrato> listaContratos = empleado.getContratos();
        Contrato ultimoContrato = listaContratos.get(listaContratos.size() - 1);
        if (listaContratos.isEmpty()) {
            return true;
        } else {
            if (fechaInicio.after(ultimoContrato.getFechaFin())) {
                return true;
            }
        }
        return false;
    }

    public boolean esValidaFechaFin() {
        if (minimo3Meses() && maximo12Meses()) {
            return true;
        }
        return false;
    }

    public boolean esValidoHorasContratadas() {
        if (horasContratadas >= 8 && horasContratadas <= 40) {
            if (horasContratadas % 4 == 0)
                return true;
        }
        return false;
    }

    public boolean esValidoValorHora() {
        if (valorHora >= 10 && valorHora <= 60) {
            return true;
        }
        return false;
    }

    public float getDescuentoAFP() {
        float descuento = 0;
        descuento = afp.getDescuento();

        return descuento / 100;
    }

    public int getSueldoBasico() {

        return valorHora * horasContratadas;
    }

    public float calcularMontoAsignacionFamiliar() {
        float monto = 0f;
        if (asignacionFamiliar) {
            monto = getSueldoBasico() * 0.1f;
        }
        return monto;
    }

    public float calcularTotalIngresos() {
        return getSueldoBasico() + calcularMontoAsignacionFamiliar() + conceptos.calcularTotalConceptosIngresos();
    }

    public float calcularDescuentoAFP() {
        return getSueldoBasico() * getDescuentoAFP();
    }

    public float calcularTotalDescuentos() {
        return calcularDescuentoAFP() + conceptos.calcularTotalConceptosDescuentos();
    }

    public float calcularSueldoNeto() {
        return calcularTotalIngresos() - calcularTotalDescuentos();
    }

    public int calcularHorasContratadasPorSemana() {
        return (int) Math.ceil((horasContratadas / calcularSemanasDelContrato()));
    }
    //OTROS METODOS

    private boolean minimo3Meses() {
        Calendar fechaInicioCopia = (Calendar) fechaInicio.clone();
        fechaInicioCopia.add(Calendar.MONTH, 3);
        if (fechaInicioCopia.before(fechaFin) || fechaInicioCopia.equals(fechaFin)) {
            return true;
        }
        return false;
    }

    private boolean maximo12Meses() {
        Calendar fechaInicioCopia = (Calendar) fechaInicio.clone();
        fechaInicioCopia.add(Calendar.MONTH, 12);
        if (fechaInicioCopia.after(fechaFin) || fechaInicioCopia.equals(fechaFin)) {
            return true;
        }
        return false;
    }

    private int calcularSemanasDelContrato() {
        long milis = fechaFin.getTimeInMillis() - fechaInicio.getTimeInMillis();
        int dias = (int) Math.floor((double) (milis / 86400000));
        int semanas = (int) Math.floor((dias / 7));
        return semanas;
    }


}
