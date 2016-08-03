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
public class EjemplarDocumentalDto {

    private int NRO_EJEMPLAR;
    private int ID_EJEMPLAR;
    private String CODIGO_BARRAS;
    private String MFN;
    private int ID_DOCUMENTAL;

    public int getNRO_EJEMPLAR() {
        return NRO_EJEMPLAR;
    }

    public void setNRO_EJEMPLAR(int NRO_EJEMPLAR) {
        this.NRO_EJEMPLAR = NRO_EJEMPLAR;
    }

    public int getID_EJEMPLAR() {
        return ID_EJEMPLAR;
    }

    public void setID_EJEMPLAR(int ID_EJEMPLAR) {
        this.ID_EJEMPLAR = ID_EJEMPLAR;
    }

    public String getCODIGO_BARRAS() {
        return CODIGO_BARRAS;
    }

    public void setCODIGO_BARRAS(String CODIGO_BARRAS) {
        this.CODIGO_BARRAS = CODIGO_BARRAS;
    }

    public String getMFN() {
        return MFN;
    }

    public void setMFN(String MFN) {
        this.MFN = MFN;
    }

    public int getID_DOCUMENTAL() {
        return ID_DOCUMENTAL;
    }

    public void setID_DOCUMENTAL(int ID_DOCUMENTAL) {
        this.ID_DOCUMENTAL = ID_DOCUMENTAL;
    }

}
