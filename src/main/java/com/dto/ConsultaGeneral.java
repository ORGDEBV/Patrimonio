/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dto;

import java.io.Serializable;

/**
 *
 * @author virtual
 */
public class ConsultaGeneral implements Serializable {

    private String MFN;
    private String NRO_INGRESO;
    private String CODIGO_BARRAS;
    private String CODIGO_MEMO;
    private String ESTADO;
    private String AREA;
    private String DEPOSITO;
    private int ID_CAJA;
    private int ID_DOCUMENTAL;
    private int ID_EJEMPLAR;
    private int ID_ESTADO_PROCESO;

    public String getMFN() {
        return MFN;
    }

    public void setMFN(String MFN) {
        this.MFN = MFN;
    }

    public String getNRO_INGRESO() {
        return NRO_INGRESO;
    }

    public void setNRO_INGRESO(String NRO_INGRESO) {
        this.NRO_INGRESO = NRO_INGRESO;
    }

    public String getCODIGO_BARRAS() {
        return CODIGO_BARRAS;
    }

    public void setCODIGO_BARRAS(String CODIGO_BARRAS) {
        this.CODIGO_BARRAS = CODIGO_BARRAS;
    }

    public String getCODIGO_MEMO() {
        return CODIGO_MEMO;
    }

    public void setCODIGO_MEMO(String CODIGO_MEMO) {
        this.CODIGO_MEMO = CODIGO_MEMO;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public String getAREA() {
        return AREA;
    }

    public void setAREA(String AREA) {
        this.AREA = AREA;
    }

    public String getDEPOSITO() {
        return DEPOSITO;
    }

    public void setDEPOSITO(String DEPOSITO) {
        this.DEPOSITO = DEPOSITO;
    }

    public int getID_CAJA() {
        return ID_CAJA;
    }

    public void setID_CAJA(int ID_CAJA) {
        this.ID_CAJA = ID_CAJA;
    }

    public int getID_DOCUMENTAL() {
        return ID_DOCUMENTAL;
    }

    public void setID_DOCUMENTAL(int ID_DOCUMENTAL) {
        this.ID_DOCUMENTAL = ID_DOCUMENTAL;
    }

    public int getID_EJEMPLAR() {
        return ID_EJEMPLAR;
    }

    public void setID_EJEMPLAR(int ID_EJEMPLAR) {
        this.ID_EJEMPLAR = ID_EJEMPLAR;
    }

    public int getID_ESTADO_PROCESO() {
        return ID_ESTADO_PROCESO;
    }

    public void setID_ESTADO_PROCESO(int ID_ESTADO_PROCESO) {
        this.ID_ESTADO_PROCESO = ID_ESTADO_PROCESO;
    }

}
