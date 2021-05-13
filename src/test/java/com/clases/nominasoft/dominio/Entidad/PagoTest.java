package com.clases.nominasoft.dominio.Entidad;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class PagoTest {

    @Test
    void calcularSueldoBasico() {
        Contrato contrato = new Contrato();
        PeriodoPago periodo = new PeriodoPago();
        Pago pago = new Pago();
        Calendar fechaInicio = Calendar.getInstance();
        Calendar fechaFin = Calendar.getInstance();
        fechaInicio.set(2021,1,2);
        fechaFin.set(2021,2,2);
        periodo.setFechaInicio(fechaInicio);
        periodo.setFechaFin(fechaFin);
        contrato.setHorasContratadas(80);
        contrato.setValorHora(40);
        pago.setPeriodo(periodo);
        pago.setContrato(contrato);
        double sueldoBasico_esperado=12800.0;
        double sueldoBasico_obtenido=pago.calcularSueldoBasico();
        Assertions.assertEquals(sueldoBasico_esperado,sueldoBasico_obtenido);

    }

    @Test
    void calcularIngresoTotal() {
        Pago pago = new Pago();
        OtrosConceptos conceptos = new OtrosConceptos(
                30,80,50,
                10,30,80,
                null);
        PeriodoPago periodo = new PeriodoPago();
        Contrato contrato = new Contrato();
        Calendar fechaInicio = Calendar.getInstance();
        Calendar fechaFin = Calendar.getInstance();
        fechaInicio.set(2021,1,2);
        fechaFin.set(2021,2,2);
        periodo.setFechaInicio(fechaInicio);
        periodo.setFechaFin(fechaFin);
        contrato.setAsignacionFamiliar(true);
        contrato.setHorasContratadas(80);
        contrato.setValorHora(40);
        pago.setPeriodo(periodo);
        pago.setContrato(contrato);
        contrato.agregarOtrosConceptos(conceptos);
        double totalDeIngresos_esperado = 14240.0;
        double totalDeIngresos_obtenido = pago.calcularIngresoTotal();
        Assertions.assertEquals(totalDeIngresos_esperado,totalDeIngresos_obtenido);

    }

    @Test
    void calcularTotalIngresosConceptos() {

        Pago pago = new Pago();
        OtrosConceptos conceptos = new OtrosConceptos(
                30,80,50,
                10,30,80,
                null);
        Contrato contrato = new Contrato();
        pago.setContrato(contrato);
        contrato.agregarOtrosConceptos(conceptos);
        double totalDeIngresosConceptos_esperado = 160.0;
        double totalDeIngresosConceptos_obtenido = pago.CalcularTotalIngresosConceptos();
        Assertions.assertEquals(totalDeIngresosConceptos_esperado,totalDeIngresosConceptos_obtenido);
    }

    @Test
    void calcularTotalDescuentosConceptos() {
        Pago pago = new Pago();
        OtrosConceptos conceptos = new OtrosConceptos(
                30,80,50,
                10,30,80,
                null);
        Contrato contrato = new Contrato();
        pago.setContrato(contrato);
        contrato.agregarOtrosConceptos(conceptos);
        double totalConceptosDeDescuentos_esperado = 120.0;
        double totalConceptosDeDescuentos_obtenido = pago.CalcularTotalDescuentosConceptos();
        Assertions.assertEquals(totalConceptosDeDescuentos_esperado,totalConceptosDeDescuentos_obtenido);
    }
    @Test
    void calcularTotalDescuentos() {
        Afp afp = new Afp();
        afp.setDescuento(10);
        Contrato contrato = new Contrato();
        contrato.setAfp(afp);
        double descuento=contrato.getAfp().getDescuento();
        double result =descuento/100;
        System.out.println("descuento de "+descuento+" es: "+ result+" soles");
    }

    @Test
    void calcularMontoAsignacionFamiliar() {
        Contrato contrato = new Contrato();
        PeriodoPago periodo = new PeriodoPago();
        Pago pago = new Pago();
        Calendar fechaInicio = Calendar.getInstance();
        Calendar fechaFin = Calendar.getInstance();
        fechaInicio.set(2021,1,2);
        fechaFin.set(2021,2,2);
        periodo.setFechaInicio(fechaInicio);
        periodo.setFechaFin(fechaFin);
        contrato.setHorasContratadas(80);
        contrato.setValorHora(40);
        pago.setPeriodo(periodo);
        pago.setContrato(contrato);
        contrato.setAsignacionFamiliar(true);
        double montoPorAsignacionFamiliar_esperado = 1280.0;
        double montoPorAsignacionFamiliar_obtenido = pago.calcularMontoAsignacionFamiliar();
        Assertions.assertEquals(montoPorAsignacionFamiliar_esperado,montoPorAsignacionFamiliar_obtenido);
    }

    @Test
    void calcularDescuentoAFP() {
        Contrato contrato = new Contrato();
        PeriodoPago periodo = new PeriodoPago();
        Pago pago = new Pago();
        Afp afp = new Afp();
        afp.setDescuento(10);
        Calendar fechaInicio = Calendar.getInstance();
        Calendar fechaFin = Calendar.getInstance();
        fechaInicio.set(2021,1,2);
        fechaFin.set(2021,2,2);
        periodo.setFechaInicio(fechaInicio);
        periodo.setFechaFin(fechaFin);
        contrato.setHorasContratadas(80);
        pago.setPeriodo(periodo);
        pago.setContrato(contrato);
        contrato.setValorHora(40);
        contrato.setAfp(afp);
        double descuentoAfp_esperado = 1280;
        double descuentoAfp_obtenido = pago.calcularDescuentoAFP();
        Assertions.assertEquals(descuentoAfp_esperado,descuentoAfp_obtenido);

    }

    @Test
    void calcularSueldoNeto() {
        Pago pago = new Pago();
        OtrosConceptos conceptos = new OtrosConceptos(
                30,80,50,
                10,30,80,
                null);
        PeriodoPago periodo = new PeriodoPago();
        Contrato contrato = new Contrato();
        Afp afp = new Afp();
        Calendar fechaInicio = Calendar.getInstance();
        Calendar fechaFin = Calendar.getInstance();
        fechaInicio.set(2021,1,2);
        fechaFin.set(2021,2,2);
        periodo.setFechaInicio(fechaInicio);
        periodo.setFechaFin(fechaFin);
        contrato.setAsignacionFamiliar(true);
        contrato.setHorasContratadas(80);
        contrato.setValorHora(40);
        afp.setDescuento(5);
        contrato.setAfp(afp);
        pago.setPeriodo(periodo);
        pago.setContrato(contrato);
        contrato.agregarOtrosConceptos(conceptos);
        double sueldoNeto_esperado =13480.0;
        double sueldoNeto_obtenido = pago.calcularSueldoNeto();
        Assertions.assertEquals(sueldoNeto_esperado,sueldoNeto_obtenido);

    }

    @Test
    void calcularTotalDeHoras() {
        Contrato contrato = new Contrato();
        PeriodoPago periodo = new PeriodoPago();
        Pago pago = new Pago();
        Calendar fechaInicio = Calendar.getInstance();
        Calendar fechaFin = Calendar.getInstance();
        fechaInicio.set(2021,1,2);
        fechaFin.set(2021,2,2);
        periodo.setFechaInicio(fechaInicio);
        periodo.setFechaFin(fechaFin);
        contrato.setHorasContratadas(40);
        pago.setPeriodo(periodo);
        pago.setContrato(contrato);
        int totalHoras_esperado =160;
        int totalHoras_obtenido=pago.calcularTotalDeHoras();
        Assertions.assertEquals(totalHoras_esperado,totalHoras_obtenido);
    }

    @Test
    void puedeProcesarContrato() {
        PeriodoPago periodo = new PeriodoPago();
        Contrato contrato = new Contrato();
        Pago pago = new Pago();
        Calendar fechaInicioPeriodo = Calendar.getInstance();
        Calendar fechaFinContrato = Calendar.getInstance();
        fechaFinContrato.set(2021,7,28);
        fechaInicioPeriodo.set(2021,6,28);
        contrato.setFechaFin(fechaFinContrato);
        periodo.setFechaInicio(fechaInicioPeriodo);
        periodo.setEstado(true);
        contrato.setActivo(true);
        pago.setContrato(contrato);
        pago.setPeriodo(periodo);
        boolean puedeProcesarContrato_esperado = true;
        boolean puedeProcesarContrato_obtenido = pago.puedeProcesarContrato();
        Assertions.assertEquals(puedeProcesarContrato_esperado,puedeProcesarContrato_obtenido);

    }
    @Test
    void puedeProcesarContrato2() {
        PeriodoPago periodo = new PeriodoPago();
        Contrato contrato = new Contrato();
        Pago pago = new Pago();
        Calendar fechaInicioPeriodo = Calendar.getInstance();
        Calendar fechaFinContrato = Calendar.getInstance();
        fechaFinContrato.set(2021,7,28);
        fechaInicioPeriodo.set(2021,6,28);
        contrato.setFechaFin(fechaFinContrato);
        periodo.setFechaInicio(fechaInicioPeriodo);
        periodo.setEstado(true);
        contrato.setActivo(false);
        pago.setContrato(contrato);
        pago.setPeriodo(periodo);
        boolean puedeProcesarContrato_esperado = false;
        boolean puedeProcesarContrato_obtenido = pago.puedeProcesarContrato();
        Assertions.assertEquals(puedeProcesarContrato_esperado,puedeProcesarContrato_obtenido);
    }
    @Test
    void puedeProcesarContrato3() {
        PeriodoPago periodo = new PeriodoPago();
        Contrato contrato = new Contrato();
        Pago pago = new Pago();
        Calendar fechaInicioPeriodo = Calendar.getInstance();
        Calendar fechaFinContrato = Calendar.getInstance();
        fechaFinContrato.set(2021,7,28);
        fechaInicioPeriodo.set(2021,7,28);
        contrato.setFechaFin(fechaFinContrato);
        periodo.setFechaInicio(fechaInicioPeriodo);
        periodo.setEstado(true);
        contrato.setActivo(true);
        pago.setContrato(contrato);
        pago.setPeriodo(periodo);
        boolean puedeProcesarContrato_esperado = false;
        boolean puedeProcesarContrato_obtenido = pago.puedeProcesarContrato();
        Assertions.assertEquals(puedeProcesarContrato_esperado,puedeProcesarContrato_obtenido);
    }

}