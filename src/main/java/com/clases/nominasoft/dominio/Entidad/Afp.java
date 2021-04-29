package com.clases.nominasoft.dominio.Entidad;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Afp implements Serializable{
    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private int Descuento;
    private String nombre;
    private int tipoAfp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDescuento() {
        return Descuento;
    }

    public void setDescuento(int descuento) {
        Descuento = descuento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipoAfp() {
        return tipoAfp;
    }

    public void setTipoAfp(int tipoAfp) {
        this.tipoAfp = tipoAfp;
    }
}
