package com.clases.nominasoft.dominio.Entidad;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
class ContratoTest {

    @Test
     void esValidaFechaFin(){
        Contrato contrato = new Contrato();
        Calendar fechaInicio = Calendar.getInstance();
        Calendar fechaFin = Calendar.getInstance();
        fechaInicio.set(2021,5,6);
        fechaFin.set(2021,7,6);
        contrato.setFechaInicio(fechaInicio);
        contrato.setFechaFin(fechaFin);
        boolean esValidoFechaFin_esperado = false;
        boolean esValidoFechaFin_obtenido = contrato.esValidaFechaFin();
        Assert.assertEquals(esValidoFechaFin_esperado,esValidoFechaFin_obtenido);
    }

    @Test
    void esValidaFechaFin2(){
        Contrato contrato = new Contrato();
        Calendar fechaInicio = Calendar.getInstance();
        Calendar fechaFin = Calendar.getInstance();
        fechaInicio.set(2021,5,6);
        fechaFin.set(2021,11,6);
        contrato.setFechaInicio(fechaInicio);
        contrato.setFechaFin(fechaFin);
        boolean esValidoFechaFin_esperado = true;
        boolean esValidoFechaFin_obtenido = contrato.esValidaFechaFin();
        Assert.assertEquals(esValidoFechaFin_esperado,esValidoFechaFin_obtenido);
    }
    @Test
    public void esValidaFechaFin3(){
        Contrato contrato = new Contrato();
        Calendar fechaInicio = Calendar.getInstance();
        Calendar fechaFin = Calendar.getInstance();
        fechaInicio.set(2021,5,6);
        fechaFin.set(2022,6,6);
        contrato.setFechaInicio(fechaInicio);
        contrato.setFechaFin(fechaFin);
        boolean esValidoFechaFin_esperado = false;
        boolean esValidoFechaFin_obtenido = contrato.esValidaFechaFin();
        Assert.assertEquals(esValidoFechaFin_esperado,esValidoFechaFin_obtenido);
    }
    @Test
    void estaVigente() {
        Contrato contrato = new Contrato();
        Calendar fechaFin = Calendar.getInstance();
        fechaFin.set(2022,6,6);
        contrato.setFechaFin(fechaFin);
        contrato.setActivo(true);
        boolean estaVigente_esperado = true;
        boolean estaVigente_obtenido = contrato.estaVigente();
        Assert.assertEquals(estaVigente_esperado,estaVigente_obtenido);
    }
    @Test
    void estaVigente2() {
        Contrato contrato = new Contrato();
        Calendar fechaFin = Calendar.getInstance();
        fechaFin.set(2022,6,6);
        contrato.setFechaFin(fechaFin);
        contrato.setActivo(false);
        boolean estaVigente_esperado = false;
        boolean estaVigente_obtenido = contrato.estaVigente();
        Assert.assertEquals(estaVigente_esperado,estaVigente_obtenido);
    }
    @Test
    void esValidaFechaInicio() {
        Contrato contratoAnterior = new Contrato();
        Calendar fechaFinContrato1 = Calendar.getInstance();
        fechaFinContrato1.set(2021,5,6);
        contratoAnterior.setFechaFin(fechaFinContrato1);

        Contrato nuevoContrato = new Contrato();
        Calendar fechaInicioContrato2 = Calendar.getInstance();
        fechaInicioContrato2.set(2021,5,7);
        nuevoContrato.setFechaInicio(fechaInicioContrato2);
        boolean esValidoFechaInicio_esperado = true;
        boolean esValidoFechaInicio_obtenido = nuevoContrato.esValidaFechaInicio(contratoAnterior);
        Assert.assertEquals(esValidoFechaInicio_esperado,esValidoFechaInicio_obtenido);
    }
    @Test
    void esValidaFechaInicio2() {
        Contrato contratoAnterior = new Contrato();
        Calendar fechaFinContrato1 = Calendar.getInstance();
        fechaFinContrato1.set(2021,5,7);
        contratoAnterior.setFechaFin(fechaFinContrato1);

        Contrato nuevoContrato = new Contrato();
        Calendar fechaInicioContrato2 = Calendar.getInstance();
        fechaInicioContrato2.set(2021,5,6);
        nuevoContrato.setFechaInicio(fechaInicioContrato2);
        boolean esValidoFechaInicio_esperado = false;
        boolean esValidoFechaInicio_obtenido = nuevoContrato.esValidaFechaInicio(contratoAnterior);
        Assert.assertEquals(esValidoFechaInicio_esperado,esValidoFechaInicio_obtenido);
    }
    @Test
    void esValidoHorasContratadas() {
        Contrato contrato = new Contrato();
        contrato.setHorasContratadas(40);
        boolean esValidoHorasContratadas_esperado = true;
        boolean esValidoHorasContratadas_obtenido = contrato.esValidoHorasContratadas();
        Assert.assertEquals(esValidoHorasContratadas_esperado,esValidoHorasContratadas_obtenido);
    }
    @Test
    void esValidoHorasContratadas2() {
        Contrato contrato = new Contrato();
        contrato.setHorasContratadas(41);
        boolean esValidoHorasContratadas_esperado = false;
        boolean esValidoHorasContratadas_obtenido = contrato.esValidoHorasContratadas();
        Assert.assertEquals(esValidoHorasContratadas_esperado,esValidoHorasContratadas_obtenido);
    }
    @Test
    void esValidoValorHora() {
        Contrato contrato = new Contrato();
        contrato.setValorHora(50);
        boolean esValidoValorHora_esperado = true;
        boolean esValidoValorHora_obtenido = contrato.esValidoValorHora();
        Assert.assertEquals(esValidoValorHora_esperado,esValidoValorHora_obtenido);
    }

    @Test
    void esValidoValorHora2() {
        Contrato contrato = new Contrato();
        contrato.setValorHora(61);
        boolean esValidoValorHora_esperado = false;
        boolean esValidoValorHora_obtenido = contrato.esValidoValorHora();
        Assert.assertEquals(esValidoValorHora_esperado,esValidoValorHora_obtenido);
    }

    @Test
    void calcularSemanasDelContrato(){
        Contrato contrato = new Contrato();
        Calendar fechaInicio = Calendar.getInstance();
        fechaInicio.set(2021,02,02);
        Contrato nuevoContrato = new Contrato();
        Calendar fechaFinContrato = Calendar.getInstance();
        fechaFinContrato.set(2021,03,02);
        contrato.setFechaInicio(fechaInicio);
        contrato.setFechaFin(fechaFinContrato);
        int semanasEsperadas = 4;
        int semanasObtenidas= contrato.calcularSemanasDelContrato();
        Assertions.assertEquals(semanasEsperadas,semanasObtenidas);


    }

}