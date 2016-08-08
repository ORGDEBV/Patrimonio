/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dto;

/**
 *
 * @author virtual
 */
public class BandejaDto {

    private String ID_CAJA;
    private String CODIGO_MEMO;
    private String NRO_CAJA;
    private String CODIGO_LISTADO;
    private String NRO_EJEMPLARES;
    private String NRO_VOLUMENES;
    private String AREA;
    private String SALA;
    private String FECHA;
    private String ID_DEPOSITO;
    private String DEPOSITO;

    public BandejaDto() {
    }

    public String getID_DEPOSITO() {
        return ID_DEPOSITO;
    }

    public void setID_DEPOSITO(String ID_DEPOSITO) {
        this.ID_DEPOSITO = ID_DEPOSITO;
    }

    public String getDEPOSITO() {
        return DEPOSITO;
    }

    public void setDEPOSITO(String DEPOSITO) {
        this.DEPOSITO = DEPOSITO;
    }

    public String getID_CAJA() {
        return ID_CAJA;
    }

    public void setID_CAJA(String ID_CAJA) {
        this.ID_CAJA = ID_CAJA;
    }

    public String getCODIGO_MEMO() {
        return CODIGO_MEMO;
    }

    public void setCODIGO_MEMO(String CODIGO_MEMO) {
        this.CODIGO_MEMO = CODIGO_MEMO;
    }

    public String getNRO_CAJA() {
        return NRO_CAJA;
    }

    public void setNRO_CAJA(String NRO_CAJA) {
        this.NRO_CAJA = NRO_CAJA;
    }

    public String getCODIGO_LISTADO() {
        return CODIGO_LISTADO;
    }

    public void setCODIGO_LISTADO(String CODIGO_LISTADO) {
        this.CODIGO_LISTADO = CODIGO_LISTADO;
    }

    public String getNRO_EJEMPLARES() {
        return NRO_EJEMPLARES;
    }

    public void setNRO_EJEMPLARES(String NRO_EJEMPLARES) {
        this.NRO_EJEMPLARES = NRO_EJEMPLARES;
    }

    public String getNRO_VOLUMENES() {
        return NRO_VOLUMENES;
    }

    public void setNRO_VOLUMENES(String NRO_VOLUMENES) {
        this.NRO_VOLUMENES = NRO_VOLUMENES;
    }

    public String getAREA() {
        return AREA;
    }

    public void setAREA(String AREA) {
        this.AREA = AREA;
    }

    public String getSALA() {
        return SALA;
    }

    public void setSALA(String SALA) {
        this.SALA = SALA;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

}
